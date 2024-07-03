package org.ni2.v01.api.impl;

import org.ni2.v01.api.*;
import org.ni2.v01.model.DefaultSearchBody;
import org.ni2.v01.model.Described;
import org.ni2.v01.model.DescribedResultResponse;
import org.ni2.v01.model.Minimal;
import org.ni2.v01.model.MinimalResultResponse;
import org.ni2.v01.model.ResourceControllerGetResourceExtended200Response;
import org.ni2.v01.model.ResourceExtendedResultResponse;
import org.ni2.v01.model.ResourceIdsBody;
import org.ni2.v01.model.UpdateDescribedBody;

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
public class Class5ResourceApiServiceImpl implements Class5ResourceApi {
    /**
     * Retrieves the base information of the Resource identified by the given universal id
     *
     */
    public Described resourceControllerGetResourceBase(String resourceId) {
        // TODO: Implement...

        return null;
    }

    /**
     * Retrieves the extended information of the Resource identified by the given universal id
     *
     */
    public ResourceControllerGetResourceExtended200Response resourceControllerGetResourceExtended(String resourceId) {
        // TODO: Implement...

        return null;
    }

    /**
     * Retrieves the minimal information of the Resource identified by the given universal id
     *
     */
    public Minimal resourceControllerGetResourceMinimal(String resourceId) {
        // TODO: Implement...

        return null;
    }

    /**
     * Releases all the Resources linked to the common Reservation Id
     *
     */
    public void resourceControllerReleaseAllResource(String reservationId) {
        // TODO: Implement...

        
    }

    /**
     * Releases the Resources linked to the common Reservation Id and identified by the Resources Ids
     *
     */
    public void resourceControllerReleaseResource(String reservationId, ResourceIdsBody resourceIdsBody) {
        // TODO: Implement...

        
    }

    /**
     * Reserves Resources
     *
     */
    public void resourceControllerReserveResource(String body) {
        // TODO: Implement...

        
    }

    /**
     * Retrieves the base information of the Resources resulting from the search
     *
     */
    public DescribedResultResponse resourceControllerSearchResourceBase(DefaultSearchBody defaultSearchBody) {
        // TODO: Implement...

        return null;
    }

    /**
     * Retrieves the extended information of the Resources resulting from the search
     *
     */
    public ResourceExtendedResultResponse resourceControllerSearchResourceExnteded(DefaultSearchBody defaultSearchBody) {
        // TODO: Implement...

        return null;
    }

    /**
     * Retrieves the minimal information of the Resources resulting from the search
     *
     */
    public MinimalResultResponse resourceControllerSearchResourceMinimal(DefaultSearchBody defaultSearchBody) {
        // TODO: Implement...

        return null;
    }

    /**
     * Updates the Resource identified by the given universal Id
     *
     */
    public void resourceControllerUpdateResource(String resourceId, UpdateDescribedBody updateDescribedBody) {
        // TODO: Implement...

        
    }

}
