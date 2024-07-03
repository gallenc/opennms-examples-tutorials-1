
package org.ni2.v01.api.tt.opennms.plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.common.util.ProxyClassLoader;
import org.apache.cxf.jaxrs.client.ClientConfiguration;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean;
import org.apache.cxf.jaxrs.client.WebClient;
import org.ni2.v01.api.Class0AuthenticationApi;
import org.ni2.v01.api.Class3EventApi;
import org.ni2.v01.api.CreateApi;
import org.ni2.v01.api.DeleteApi;
import org.ni2.v01.api.GetApi;
import org.ni2.v01.api.SearchApi;
import org.ni2.v01.api.UpdateApi;
import org.ni2.v01.api.utils.TLSUtils;
import org.ni2.v01.model.AddressCreateBodyCustomAttributesValue;
import org.ni2.v01.model.AuthenticationControllerLogin200Response;
import org.ni2.v01.model.AuthenticationControllerLoginRequest;
import org.ni2.v01.model.EventCreateBody;
import org.ni2.v01.model.EventExtended;
import org.ni2.v01.model.InstanceURL;
import org.opennms.integration.api.v1.ticketing.Ticket;
import org.opennms.integration.api.v1.ticketing.Ticket.State;
import org.opennms.integration.api.v1.ticketing.TicketingPlugin;
import org.opennms.integration.api.v1.ticketing.immutables.ImmutableTicket;
import org.opennms.integration.api.v1.ticketing.immutables.ImmutableTicket.Builder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

public class Ni2TicketerPlugin implements TicketingPlugin {
   private static final Logger LOG = LoggerFactory.getLogger(Ni2TicketerPlugin.class);
   
   public static final String TT_SERVER_URL_PROPERTY="ni2.tt.server.url";   
   public static final String TT_USERNAME_PROPERTY="ni2.tt.server.username";
   public static final String TT_PASSWORD_PROPERTY="ni2.tt.server.password";
   public static final  String TT_OPENNMS_INSTANCE_PROPERTY="ni2.tt.opennms.instance";
   
   public static final String DEFAULT_TT_SERVER_URL_PROPERTY="http://localhost:8080";   
   public static final String DEFAULT_TT_USERNAME_PROPERTY="username";
   public static final String DEFAULT_TT_PASSWORD_PROPERTY="password";
   public static final  String DEFAULT_TT_OPENNMS_INSTANCE_PROPERTY="OpenNMS-Instance-not-set";

   private String ttServerUrl = DEFAULT_TT_SERVER_URL_PROPERTY;
   private String ttUsername = DEFAULT_TT_USERNAME_PROPERTY;
   private String ttPassword = DEFAULT_TT_PASSWORD_PROPERTY;
   private String onmsInstanceId = DEFAULT_TT_OPENNMS_INSTANCE_PROPERTY;

   private Class0AuthenticationApi authenticationApi;
   private Class3EventApi eventApi;

   public void setTtServerUrl(String ttServerUrl) {
      this.ttServerUrl = ttServerUrl;
   }

   public void setTtUsername(String ttUsername) {
      this.ttUsername = ttUsername;
   }

   public void setTtPassword(String ttPassword) {
      this.ttPassword = ttPassword;
   }

   public void setOnmsInstanceId(String onmsInstanceId) {
      this.onmsInstanceId = onmsInstanceId;
   }
   
   public void init() {
      LOG.info("Ni2 Trouble Ticket Plugin initialised ttServerUrl={}  ttUsername={} onmsInstanceId={}",ttServerUrl,ttUsername,onmsInstanceId);
   }
   
   
   /**
    * does the same as JAXRSClientFactory.create(String baseAddress, Class<T> cls, List<?> providers)
    * except that it allows a class loader to work in OSGi
    */
   private <T> T create(String baseAddress, Class<T> cls, List<?> providers) {
      
      // see https://stackoverflow.com/questions/10458378/how-can-i-make-a-java-lang-reflect-proxy-from-two-separate-classloaders
      // solves problem of separate class loaders in different bundles
      ProxyClassLoader classLoader = new ProxyClassLoader(JAXRSClientFactory.class.getClassLoader());
      classLoader.addLoader(cls.getClassLoader());
      
      JAXRSClientFactoryBean bean = new JAXRSClientFactoryBean();
      bean.setClassLoader(classLoader);
      bean.setServiceClass(cls);
      bean.setProviders(providers);
      bean.setAddress(baseAddress);
      return bean.create(cls);
  }

   private void setUpEventApi() {
      JacksonJsonProvider provider = new JacksonJsonProvider();
      List providers = new ArrayList();
      providers.add(provider);

      authenticationApi = create(ttServerUrl, Class0AuthenticationApi.class, providers);
      org.apache.cxf.jaxrs.client.Client authenticationClient = WebClient.client(authenticationApi);
      ClientConfiguration authenticationConfig = WebClient.getConfig(authenticationClient);
      TLSUtils.addX509TrustManager(authenticationConfig);

      String bearerToken = getBearerToken(); // uses authentication api

      eventApi = create(ttServerUrl, Class3EventApi.class, providers);
      org.apache.cxf.jaxrs.client.Client eventClient = WebClient.client(eventApi).header("Authorization", "Bearer " + bearerToken);
      ClientConfiguration eventClientConfig = WebClient.getConfig(eventClient);
      TLSUtils.addX509TrustManager(eventClientConfig);

   }

   private String getBearerToken() {
      AuthenticationControllerLoginRequest authenticationControllerLoginRequest = new AuthenticationControllerLoginRequest()
               .username(ttUsername)
               .password(ttPassword);
      AuthenticationControllerLogin200Response response = authenticationApi.authenticationControllerLogin(authenticationControllerLoginRequest);
      String accessToken = response.getAccessToken();
      return accessToken;

   }

   @Override
   public synchronized Ticket get(String ticketId) {
      LOG.debug("get ticketId {}", ticketId);
      if (ticketId == null)  {
         LOG.error("No ni2 ticketID (ni2 eventId) available in OpenNMS Ticket");
         throw new Ni2TicketerException("No ni2 ticketID (ni2 eventId) available in OpenNMS Ticket");
     }

      setUpEventApi();

      EventExtended extendedEventResponse = eventApi.eventControllerGetEventExtended(ticketId);
      LOG.debug("received ticket {}", extendedEventResponse);

      String summary = extendedEventResponse.getDescription();
      String details = extendedEventResponse.getLongDescription();
      Map<String, AddressCreateBodyCustomAttributesValue> customAttributes = extendedEventResponse.getCustomAttributes();

      AddressCreateBodyCustomAttributesValue customAttribute = customAttributes.get("AlarmId");
      Integer alarmId = (customAttribute==null) ? null : Integer.valueOf(customAttribute.getValue());

      customAttribute = customAttributes.get("Status");
      State onmsState = ni2StateToONMSState(customAttribute.getValue());

      customAttribute = customAttributes.get("Submitter");
      String user = (customAttribute==null) ? null :customAttribute.getValue();

      final Builder builder = ImmutableTicket.newBuilder();
      builder.setSummary(summary);
      builder.setDetails(details);
      builder.setState(onmsState);
      builder.setUser(user);
      builder.setAlarmId(alarmId);
      Ticket newTicket = builder.build();

      return newTicket;
   }



   @Override
   public synchronized String saveOrUpdate(Ticket ticket) {
      LOG.debug("saveOrUpdate ticket {}", ticket);
      setUpEventApi();
      if ((ticket.getId() == null)) {
         return save(ticket);
      } else {
         update(ticket);
      }
      return ticket.getId();
   }

   private String save(Ticket ticket) {
      LOG.debug("save ticket {}", ticket);
      setUpEventApi();

      EventCreateBody eventCreateBody = new EventCreateBody();

      Map<String, AddressCreateBodyCustomAttributesValue> customAttributeValue = new LinkedHashMap<String, AddressCreateBodyCustomAttributesValue>();

      customAttributeValue.put("Category", new AddressCreateBodyCustomAttributesValue("Network"));

      customAttributeValue.put("AlarmSource", new AddressCreateBodyCustomAttributesValue(onmsInstanceId));

      // "AlarmId": "{{alarmId}}"
      customAttributeValue.put("AlarmId", new AddressCreateBodyCustomAttributesValue(ticket.getAlarmId().toString()));

      eventCreateBody.classificationPath("Event(\"Event/Support/Incident/Monitoring Incident\")")
               .description(ticket.getSummary())
               .longDescription(ticket.getDetails())
               .customAttributes(customAttributeValue)
               .resourceIds(Arrays.asList("monaco_01"));

      InstanceURL instanceUrlResponse = eventApi.eventControllerCreateEvent(eventCreateBody);
      LOG.debug("received  instanceUrlResponse:" + instanceUrlResponse);

      return instanceUrlResponse.getUniversalId();
   }

   private void update(Ticket ticket) {
      LOG.debug("update ticket {}", ticket);

   }
   
   private State ni2StateToONMSState(String ni2Status) {

      switch (ni2Status) {
      case "Open":
         return State.OPEN;
      case "Closed":
         return State.CLOSED;
      case "Cancelled":
         return State.CANCELLED;
      default:
         return State.OPEN;
      }

   }

}
