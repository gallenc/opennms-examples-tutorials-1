/**
 * Ni2 Deck API Gateway
 * Ni2 Deck API Gateway Description
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.ni2.v01.api;

import org.ni2.v01.model.AuthenticationControllerLogin200Response;
import org.ni2.v01.model.AuthenticationControllerLoginRequest;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import javax.ws.rs.core.Response;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.ClientConfiguration;
import org.apache.cxf.jaxrs.client.WebClient;


import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




/**
 * Ni2 Deck API Gateway
 *
 * <p>Ni2 Deck API Gateway Description
 *
 * API tests for Class0AuthenticationApi
 */
public class Class0AuthenticationApiTest {


    private Class0AuthenticationApi api;

    @Before
    public void setup() {
        JacksonJsonProvider provider = new JacksonJsonProvider();
        List providers = new ArrayList();
        providers.add(provider);

        api = JAXRSClientFactory.create("http://localhost", Class0AuthenticationApi.class, providers);
        org.apache.cxf.jaxrs.client.Client client = WebClient.client(api);

        ClientConfiguration config = WebClient.getConfig(client);
    }

    
    /**
     * Authenticate to the application
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void authenticationControllerLoginTest() {
        AuthenticationControllerLoginRequest authenticationControllerLoginRequest = null;
        //AuthenticationControllerLogin200Response response = api.authenticationControllerLogin(authenticationControllerLoginRequest);
        //assertNotNull(response);
        // TODO: test validations


    }
    
}
