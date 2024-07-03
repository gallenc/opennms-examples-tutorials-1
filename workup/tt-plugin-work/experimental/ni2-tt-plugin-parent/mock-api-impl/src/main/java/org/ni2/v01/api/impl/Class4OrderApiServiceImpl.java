package org.ni2.v01.api.impl;

import org.ni2.v01.api.*;
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

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import org.apache.cxf.jaxrs.model.wadl.Description;
import org.apache.cxf.jaxrs.model.wadl.DocTarget;

import org.apache.cxf.jaxrs.ext.multipart.*;

import io.swagger.annotations.Api;

/**
 * Ni2 Deck API Gateway
 *
 * <p>Ni2 Deck API Gateway Description
 *
 */
public class Class4OrderApiServiceImpl implements Class4OrderApi {
    /**
     * Creates an Order for an Add On on an existing Service
     *
     */
    public InstanceURL orderControllerCreateOrderForAddOnExistingService(OrderAddOnExistingServiceCreateBody orderAddOnExistingServiceCreateBody) {
        // TODO: Implement...

        return null;
    }

    /**
     * Creates an Order for an existing Service
     *
     */
    public InstanceURL orderControllerCreateOrderForExistingService(OrderExistingServiceCreateBody orderExistingServiceCreateBody) {
        // TODO: Implement...

        return null;
    }

    /**
     * Creates an Order for a new Service
     *
     */
    public InstanceURL orderControllerCreateOrderForNewService(OrderNewServiceCreateBody orderNewServiceCreateBody) {
        // TODO: Implement...

        return null;
    }

    /**
     * Retrieves the base information of the Order identified by the given universal id
     *
     */
    public Order orderControllerGetOrderBase(String orderId) {
        // TODO: Implement...

        return null;
    }

    /**
     * Retrieves the extended information of the Address identified by the given universal id
     *
     */
    public OrderExtended orderControllerGetOrderExtended(String orderId) {
        // TODO: Implement...

        return null;
    }

    /**
     * Retrieves the extended information of the Address identified by the given universal id
     *
     */
    public OrderHierarchical orderControllerGetOrderHierarchical(String orderId) {
        // TODO: Implement...

        return null;
    }

    /**
     * Retrieves the minimal information of the Order identified by the given universal id
     *
     */
    public Minimal orderControllerGetOrderMinimal(String orderId) {
        // TODO: Implement...

        return null;
    }

    /**
     * Retrieves the base information of the Orders resulting from the search
     *
     */
    public OrderBaseResultResponse orderControllerSearchOrderBase(OrderSearchBody orderSearchBody) {
        // TODO: Implement...

        return null;
    }

    /**
     * Retrieves the extended information of the Orders resulting from the search
     *
     */
    public OrderExtendedResultResponse orderControllerSearchOrderExtended(OrderSearchBody orderSearchBody) {
        // TODO: Implement...

        return null;
    }

    /**
     * Retrieves the extended information of the Orders resulting from the search
     *
     */
    public OrderHierarchicalResultResponse orderControllerSearchOrderHierarchical(OrderSearchBody orderSearchBody) {
        // TODO: Implement...

        return null;
    }

    /**
     * Retrieves the minimal information of the Orders resulting from the search
     *
     */
    public MinimalResultResponse orderControllerSearchOrderMinimal(OrderSearchBody orderSearchBody) {
        // TODO: Implement...

        return null;
    }

    /**
     * Updates the Order identified by the given universal Id
     *
     */
    public void orderControllerUpdateOrder(String orderId, OrderUpdateBody orderUpdateBody) {
        // TODO: Implement...

        
    }

}
