package org.ni2.v01.spring.config;

import java.util.Optional;
import java.util.UUID;

import javax.annotation.Generated;
import javax.validation.Valid;

import org.ni2.v01.spring.api.ApiApi;
import org.ni2.v01.spring.api.ApiUtil;
import org.ni2.v01.spring.model.ActionBody;
import org.ni2.v01.spring.model.AuthenticationControllerLogin200Response;
import org.ni2.v01.spring.model.AuthenticationControllerLoginRequest;
import org.ni2.v01.spring.model.EventCreateBody;
import org.ni2.v01.spring.model.EventExtended;
import org.ni2.v01.spring.model.InstanceURL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * controller for implementation
 */
@Controller
@RequestMapping("${openapi.ni2DeckAPIGateway.base-path:}")
public class MockTroubleTicketController implements ApiApi {
   private  Logger LOG = LoggerFactory.getLogger(MockTroubleTicketController.class);
   
   @Autowired AuthTokens authTokens;

   {
      LOG.debug("************************ MockTRoubleTicketController created");
   }

   private final NativeWebRequest request;

   @Autowired
   public MockTroubleTicketController(NativeWebRequest request) {
      this.request = request;
   }

   @Override
   public Optional<NativeWebRequest> getRequest() {
      return Optional.ofNullable(request);
   }

   @Override
   public ResponseEntity<InstanceURL> eventControllerCreateEvent(EventCreateBody eventCreateBody) {

      LOG.debug("************************ eventControllerCreateEvent called eventCreateBody=" + eventCreateBody);

      InstanceURL instanceUrl = new InstanceURL();
      instanceUrl.setUniversalId("1");
      instanceUrl.setUrl("http://localhost/");

      ResponseEntity<InstanceURL> response = ResponseEntity.status(HttpStatus.OK).body(instanceUrl);
      
      return response;

   }

   @Override
   public ResponseEntity<Void> eventControllerExecuteLifecycleAction(String eventId, ActionBody actionBody) {

      LOG.debug("************************ eventControllerExecuteLifecycleAction eventCreateBody=" + actionBody);

      return new ResponseEntity<>(HttpStatus.OK);

   }

   @Override
   public ResponseEntity<EventExtended> eventControllerGetEventExtended(String eventId) {

      LOG.debug("************************ eventControllerGetEventExtended eventId=" + eventId);

      EventExtended event = new EventExtended();
      event.setUniversalId(eventId);

      ResponseEntity<EventExtended> response = ResponseEntity.status(HttpStatus.OK).body(event);

      return response;

   }

   @Override
   public ResponseEntity<AuthenticationControllerLogin200Response> authenticationControllerLogin(
            AuthenticationControllerLoginRequest authenticationControllerLoginRequest) {

      LOG.debug("************************ authenticationControllerLogin authenticationControllerLoginRequest=" + authenticationControllerLoginRequest);

      AuthenticationControllerLogin200Response authResponse = new AuthenticationControllerLogin200Response();
      
      String accessToken =  authTokens.createAuthToken();
      authResponse.setAccessToken(accessToken);

      ResponseEntity<AuthenticationControllerLogin200Response> response = ResponseEntity.status(HttpStatus.OK).body(authResponse);

      return response;

   }

}