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


package org.ni2.v01.api.generated;

import org.ni2.v01.api.Class3EventApi;
import org.ni2.v01.model.ActionBody;
import org.ni2.v01.model.AddDocumentationCreateBody;
import org.ni2.v01.model.DocumentationCreateBody;
import org.ni2.v01.model.DocumentationUpdateBody;
import org.ni2.v01.model.Event;
import org.ni2.v01.model.EventBaseResultResponse;
import org.ni2.v01.model.EventCreateBody;
import org.ni2.v01.model.EventExtended;
import org.ni2.v01.model.EventExtendedResultResponse;
import org.ni2.v01.model.EventHierarchical;
import org.ni2.v01.model.EventHierarchicalResultResponse;
import org.ni2.v01.model.EventSearchBody;
import org.ni2.v01.model.EventUpdateBody;
import org.ni2.v01.model.InstanceURL;
import org.ni2.v01.model.Minimal;
import org.ni2.v01.model.MinimalResultResponse;
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
 * API tests for Class3EventApi
 */
public class Class3EventApiTest {


    private Class3EventApi api;

    @Before
    public void setup() {
        JacksonJsonProvider provider = new JacksonJsonProvider();
        List providers = new ArrayList();
        providers.add(provider);

        api = JAXRSClientFactory.create("http://localhost", Class3EventApi.class, providers);
        org.apache.cxf.jaxrs.client.Client client = WebClient.client(api);

        ClientConfiguration config = WebClient.getConfig(client);
    }

    
    /**
     * Add document to the Event identified by the given universalId
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void eventControllerAddDocumentTest() {
        String eventId = null;
        AddDocumentationCreateBody addDocumentationCreateBody = null;
        //api.eventControllerAddDocument(eventId, addDocumentationCreateBody);
        
        // TODO: test validations


    }
    
    /**
     * Creates an Event
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void eventControllerCreateEventTest() {
        EventCreateBody eventCreateBody = null;
        //InstanceURL response = api.eventControllerCreateEvent(eventCreateBody);
        //assertNotNull(response);
        // TODO: test validations


    }
    
    /**
     * Documents the Event identified by the given universalId
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void eventControllerDocumentTest() {
        String eventId = null;
        DocumentationCreateBody documentationCreateBody = null;
        //api.eventControllerDocument(eventId, documentationCreateBody);
        
        // TODO: test validations


    }
    
    /**
     * Executes an action on the Event identified by the given universalId
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void eventControllerExecuteLifecycleActionTest() {
        String eventId = null;
        ActionBody actionBody = null;
        //api.eventControllerExecuteLifecycleAction(eventId, actionBody);
        
        // TODO: test validations


    }
    
    /**
     * Retrieves the base information of the Event identified by the given universal id
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void eventControllerGetEventBaseTest() {
        String eventId = null;
        //Event response = api.eventControllerGetEventBase(eventId);
        //assertNotNull(response);
        // TODO: test validations


    }
    
    /**
     * Retrieves the extended information of the Event identified by the given universal id
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void eventControllerGetEventExtendedTest() {
        String eventId = null;
        //EventExtended response = api.eventControllerGetEventExtended(eventId);
        //assertNotNull(response);
        // TODO: test validations


    }
    
    /**
     * Retrieves the extended information of the Event identified by the given universal id
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void eventControllerGetEventHierarchicalTest() {
        String eventId = null;
        //EventHierarchical response = api.eventControllerGetEventHierarchical(eventId);
        //assertNotNull(response);
        // TODO: test validations


    }
    
    /**
     * Retrieves the minimal information of the Event identified by the given universal id
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void eventControllerGetEventMinimalTest() {
        String eventId = null;
        //Minimal response = api.eventControllerGetEventMinimal(eventId);
        //assertNotNull(response);
        // TODO: test validations


    }
    
    /**
     * Retrieves the base information of the Events resulting from the search
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void eventControllerSearchEventBaseTest() {
        EventSearchBody eventSearchBody = null;
        //EventBaseResultResponse response = api.eventControllerSearchEventBase(eventSearchBody);
        //assertNotNull(response);
        // TODO: test validations


    }
    
    /**
     * Retrieves the extended information of the Events resulting from the search
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void eventControllerSearchEventExtendedTest() {
        EventSearchBody eventSearchBody = null;
        //EventExtendedResultResponse response = api.eventControllerSearchEventExtended(eventSearchBody);
        //assertNotNull(response);
        // TODO: test validations


    }
    
    /**
     * Retrieves the extended information of the Events resulting from the search
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void eventControllerSearchEventHierarchicalTest() {
        EventSearchBody eventSearchBody = null;
        //EventHierarchicalResultResponse response = api.eventControllerSearchEventHierarchical(eventSearchBody);
        //assertNotNull(response);
        // TODO: test validations


    }
    
    /**
     * Retrieves the minimal information of the Events resulting from the search
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void eventControllerSearchEventMinimalTest() {
        EventSearchBody eventSearchBody = null;
        //MinimalResultResponse response = api.eventControllerSearchEventMinimal(eventSearchBody);
        //assertNotNull(response);
        // TODO: test validations


    }
    
    /**
     * Updates the Documentation identified by the given universal Id
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void eventControllerUpdateDocumentTest() {
        String documentId = null;
        DocumentationUpdateBody documentationUpdateBody = null;
        //api.eventControllerUpdateDocument(documentId, documentationUpdateBody);
        
        // TODO: test validations


    }
    
    /**
     * Updates the Event identified by the given universal Id
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void eventControllerUpdateEventTest() {
        String eventId = null;
        EventUpdateBody eventUpdateBody = null;
        //api.eventControllerUpdateEvent(eventId, eventUpdateBody);
        
        // TODO: test validations


    }
    
}
