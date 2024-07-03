package org.ni2.v01.api.tt.opennms.plugin.test;

import org.junit.Before;
import org.junit.Test;
import org.ni2.v01.api.tt.opennms.plugin.Ni2TicketerPlugin;
import org.opennms.integration.api.v1.ticketing.Ticket;
import org.opennms.integration.api.v1.ticketing.Ticket.State;
import org.opennms.integration.api.v1.ticketing.TicketingPlugin;
import org.opennms.integration.api.v1.ticketing.immutables.ImmutableTicket;
import org.opennms.integration.api.v1.ticketing.immutables.ImmutableTicket.Builder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Ni2TicketerPluginTest {
   private static final Logger LOG = LoggerFactory.getLogger(Ni2TicketerPluginTest.class);
   
   public static final String DEFAULT_TT_SERVER_URL = "http://localhost:8080";
   public static final String DEFAULT_TT_USERNAME = "username";
   public static final String DEFAULT_TT_PASSWORD = "password";

   public String ttServerUrl = DEFAULT_TT_SERVER_URL;
   public String ttUsername = DEFAULT_TT_USERNAME;
   public String ttPassword = DEFAULT_TT_PASSWORD;


   private Ni2TicketerPlugin ttplugin;

   public void setTtServerUrl(String ttServerUrl) {
      this.ttServerUrl = ttServerUrl;
   }

   public void setTtUsername(String ttUsername) {
      this.ttUsername = ttUsername;
   }

   public void setTtPassword(String ttPassword) {
      this.ttPassword = ttPassword;
   }

   @Before
   public void setup() {
      ttplugin = new Ni2TicketerPlugin();
      ttplugin.setTtPassword(ttPassword);
      ttplugin.setTtUsername(ttUsername);
      ttplugin.setTtServerUrl(ttServerUrl);
      
      ttplugin.init();

   }

   @Test
   public void testTicket() {

      final Builder builder = ImmutableTicket.newBuilder();
      builder.setSummary("test ticket");
      builder.setDetails("test ticket details");
      builder.setState(State.OPEN);
      builder.setUser("opennms");
      builder.setAlarmId(1);
      Ticket newTicket = builder.build();

      String ticketId = ttplugin.saveOrUpdate(newTicket);
      System.out.println("testTicket created ticketId: " + ticketId);
      
      Ticket createdTicket = ttplugin.get(ticketId);
      System.out.println("testTicket createdTicket: " + createdTicket);

   }

}
