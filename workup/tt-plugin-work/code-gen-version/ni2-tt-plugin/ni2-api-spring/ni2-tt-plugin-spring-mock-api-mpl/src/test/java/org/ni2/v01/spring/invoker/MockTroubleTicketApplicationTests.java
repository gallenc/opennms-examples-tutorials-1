package org.ni2.v01.spring.invoker;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Arrays;
import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;
import org.ni2.v01.spring.config.AuthTokens;
import org.ni2.v01.spring.config.TroubleTicketCustomAttributesValueImpl;
import org.ni2.v01.spring.model.AddressCreateBodyCustomAttributesValue;
import org.ni2.v01.spring.model.EventCreateBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class MockTroubleTicketApplicationTests {
   
   @Autowired
   AuthTokens authTokens;
   
   @Autowired
   private ObjectMapper jacksonObjectMapper;

    @Test
    void contextLoads() {
    }
    
    @Test
    void testAuthTokens() {
       
       assertNotNull (authTokens);
       
       String token1 = authTokens.createAuthToken();
       //System.out.println("token1:"+token1);
       String token2 = authTokens.createAuthToken();
       //System.out.println("token2:"+token2);
       
       assertTrue(authTokens.checkAuthToken(token1));
       assertTrue(authTokens.checkAuthToken(token2));
       
       assertFalse(authTokens.checkAuthToken("bad string"));
       
       authTokens.voidAuthToken(token1);
       
       assertFalse(authTokens.checkAuthToken(token1));
       
       authTokens.voidAllTokens();
       assertFalse(authTokens.checkAuthToken(token2));
       
    }
    
    @Test
    void testObjectMappers() throws JsonProcessingException {
      
       EventCreateBody eventCreateBody = new EventCreateBody();
       
       
       TroubleTicketCustomAttributesValueImpl customAttributeValue = new TroubleTicketCustomAttributesValueImpl("categoryUpdated");
       
      Map<String, AddressCreateBodyCustomAttributesValue> customAttributeValue2 = new LinkedHashMap<String, AddressCreateBodyCustomAttributesValue>();
      customAttributeValue2.put("Category", customAttributeValue);

      eventCreateBody.classificationPath("Event(\"Event/Support/Incident/Monitoring Incident\")")
       .description("description")
       .longDescription("long description")
       .customAttributes(customAttributeValue2)
       .resourceIds(Arrays.asList("resourceUID"));

       
       String eventCreateAsString = jacksonObjectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(eventCreateBody);
       
       System.out.println(eventCreateAsString);
       
       EventCreateBody eventCreateBodyReturned = jacksonObjectMapper.readValue(eventCreateAsString, EventCreateBody.class);
       
       System.out.println(eventCreateBodyReturned);
       
    }
    
    

}