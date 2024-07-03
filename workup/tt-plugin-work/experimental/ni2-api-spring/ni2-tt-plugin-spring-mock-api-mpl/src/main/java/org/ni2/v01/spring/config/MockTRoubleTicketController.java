package org.ni2.v01.spring.config;

import java.util.Optional;

import javax.annotation.Generated;
import javax.validation.Valid;

import org.ni2.v01.spring.api.ApiApi;
import org.ni2.v01.spring.api.ApiUtil;
import org.ni2.v01.spring.model.EventCreateBody;
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

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * controller for implementation
 */
@Controller
@RequestMapping("${openapi.ni2DeckAPIGateway.base-path:}")
public class MockTRoubleTicketController implements ApiApi {

   {
      System.out.println("************************ MockTRoubleTicketController created");

   }

   private final NativeWebRequest request;

   @Autowired
   public MockTRoubleTicketController(NativeWebRequest request) {
      this.request = request;
   }

   @Override
   public Optional<NativeWebRequest> getRequest() {
      return Optional.ofNullable(request);
   }

   @Override
   public ResponseEntity<InstanceURL> eventControllerCreateEvent(EventCreateBody eventCreateBody) {

      System.out.println("************************ eventControllerCreateEvent called eventCreateBody=" + eventCreateBody);

      getRequest().ifPresent(request -> {
         for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
            if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
               String exampleString = "{ \"universalId\" : \"universalId\", \"url\" : \"url\" }";
               ApiUtil.setExampleResponse(request, "application/json", exampleString);
               break;
            }
         }
      });
      return new ResponseEntity<>(HttpStatus.CREATED);

   }

}