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

import org.ni2.v01.api.Class4OrderApi;
import org.ni2.v01.model.InstanceURL;
import org.ni2.v01.model.Minimal;
import org.ni2.v01.model.MinimalResultResponse;
import org.ni2.v01.model.Order;
import org.ni2.v01.model.OrderAddOnExistingServiceCreateBody;
import org.ni2.v01.model.OrderBaseResultResponse;
import org.ni2.v01.model.OrderExistingServiceCreateBody;
import org.ni2.v01.model.OrderExtended;
import org.ni2.v01.model.OrderExtendedResultResponse;
import org.ni2.v01.model.OrderHierarchical;
import org.ni2.v01.model.OrderHierarchicalResultResponse;
import org.ni2.v01.model.OrderNewServiceCreateBody;
import org.ni2.v01.model.OrderSearchBody;
import org.ni2.v01.model.OrderUpdateBody;
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
 * API tests for Class4OrderApi
 */
public class Class4OrderApiTest {


    private Class4OrderApi api;

    @Before
    public void setup() {
        JacksonJsonProvider provider = new JacksonJsonProvider();
        List providers = new ArrayList();
        providers.add(provider);

        api = JAXRSClientFactory.create("http://localhost", Class4OrderApi.class, providers);
        org.apache.cxf.jaxrs.client.Client client = WebClient.client(api);

        ClientConfiguration config = WebClient.getConfig(client);
    }

    
    /**
     * Creates an Order for an Add On on an existing Service
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void orderControllerCreateOrderForAddOnExistingServiceTest() {
        OrderAddOnExistingServiceCreateBody orderAddOnExistingServiceCreateBody = null;
        //InstanceURL response = api.orderControllerCreateOrderForAddOnExistingService(orderAddOnExistingServiceCreateBody);
        //assertNotNull(response);
        // TODO: test validations


    }
    
    /**
     * Creates an Order for an existing Service
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void orderControllerCreateOrderForExistingServiceTest() {
        OrderExistingServiceCreateBody orderExistingServiceCreateBody = null;
        //InstanceURL response = api.orderControllerCreateOrderForExistingService(orderExistingServiceCreateBody);
        //assertNotNull(response);
        // TODO: test validations


    }
    
    /**
     * Creates an Order for a new Service
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void orderControllerCreateOrderForNewServiceTest() {
        OrderNewServiceCreateBody orderNewServiceCreateBody = null;
        //InstanceURL response = api.orderControllerCreateOrderForNewService(orderNewServiceCreateBody);
        //assertNotNull(response);
        // TODO: test validations


    }
    
    /**
     * Retrieves the base information of the Order identified by the given universal id
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void orderControllerGetOrderBaseTest() {
        String orderId = null;
        //Order response = api.orderControllerGetOrderBase(orderId);
        //assertNotNull(response);
        // TODO: test validations


    }
    
    /**
     * Retrieves the extended information of the Address identified by the given universal id
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void orderControllerGetOrderExtendedTest() {
        String orderId = null;
        //OrderExtended response = api.orderControllerGetOrderExtended(orderId);
        //assertNotNull(response);
        // TODO: test validations


    }
    
    /**
     * Retrieves the extended information of the Address identified by the given universal id
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void orderControllerGetOrderHierarchicalTest() {
        String orderId = null;
        //OrderHierarchical response = api.orderControllerGetOrderHierarchical(orderId);
        //assertNotNull(response);
        // TODO: test validations


    }
    
    /**
     * Retrieves the minimal information of the Order identified by the given universal id
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void orderControllerGetOrderMinimalTest() {
        String orderId = null;
        //Minimal response = api.orderControllerGetOrderMinimal(orderId);
        //assertNotNull(response);
        // TODO: test validations


    }
    
    /**
     * Retrieves the base information of the Orders resulting from the search
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void orderControllerSearchOrderBaseTest() {
        OrderSearchBody orderSearchBody = null;
        //OrderBaseResultResponse response = api.orderControllerSearchOrderBase(orderSearchBody);
        //assertNotNull(response);
        // TODO: test validations


    }
    
    /**
     * Retrieves the extended information of the Orders resulting from the search
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void orderControllerSearchOrderExtendedTest() {
        OrderSearchBody orderSearchBody = null;
        //OrderExtendedResultResponse response = api.orderControllerSearchOrderExtended(orderSearchBody);
        //assertNotNull(response);
        // TODO: test validations


    }
    
    /**
     * Retrieves the extended information of the Orders resulting from the search
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void orderControllerSearchOrderHierarchicalTest() {
        OrderSearchBody orderSearchBody = null;
        //OrderHierarchicalResultResponse response = api.orderControllerSearchOrderHierarchical(orderSearchBody);
        //assertNotNull(response);
        // TODO: test validations


    }
    
    /**
     * Retrieves the minimal information of the Orders resulting from the search
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void orderControllerSearchOrderMinimalTest() {
        OrderSearchBody orderSearchBody = null;
        //MinimalResultResponse response = api.orderControllerSearchOrderMinimal(orderSearchBody);
        //assertNotNull(response);
        // TODO: test validations


    }
    
    /**
     * Updates the Order identified by the given universal Id
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void orderControllerUpdateOrderTest() {
        String orderId = null;
        OrderUpdateBody orderUpdateBody = null;
        //api.orderControllerUpdateOrder(orderId, orderUpdateBody);
        
        // TODO: test validations


    }
    
}
