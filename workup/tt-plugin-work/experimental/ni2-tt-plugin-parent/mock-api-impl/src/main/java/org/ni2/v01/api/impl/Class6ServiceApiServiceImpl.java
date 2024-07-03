package org.ni2.v01.api.impl;

import org.ni2.v01.api.*;
import org.ni2.v01.model.Minimal;
import org.ni2.v01.model.MinimalResultResponse;
import org.ni2.v01.model.Service;
import org.ni2.v01.model.ServiceBaseResultResponse;
import org.ni2.v01.model.ServiceExtended;
import org.ni2.v01.model.ServiceExtendedResultResponse;
import org.ni2.v01.model.ServiceHierarchical;
import org.ni2.v01.model.ServiceHierarchicalResultResponse;
import org.ni2.v01.model.ServiceSearchBody;
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
public class Class6ServiceApiServiceImpl implements Class6ServiceApi {
    /**
     * Retrieves the base information of the Service identified by the given universal id
     *
     */
    public Service serviceControllerGetServiceBase(String serviceId) {
        // TODO: Implement...

        return null;
    }

    /**
     * Retrieves the extended information of the Service identified by the given universal id
     *
     */
    public ServiceExtended serviceControllerGetServiceExtended(String serviceId) {
        // TODO: Implement...

        return null;
    }

    /**
     * Retrieves the extended information of the Service identified by the given universal id
     *
     */
    public ServiceHierarchical serviceControllerGetServiceHierarchical(String serviceId) {
        // TODO: Implement...

        return null;
    }

    /**
     * Retrieves the minimal information of the Service identified by the given universal id
     *
     */
    public Minimal serviceControllerGetServiceMinimal(String serviceId) {
        // TODO: Implement...

        return null;
    }

    /**
     * Retrieves the base information of the Services resulting from the search
     *
     */
    public ServiceBaseResultResponse serviceControllerSearchServiceBase(ServiceSearchBody serviceSearchBody) {
        // TODO: Implement...

        return null;
    }

    /**
     * Retrieves the extended information of the Services resulting from the search
     *
     */
    public ServiceExtendedResultResponse serviceControllerSearchServiceExtended(ServiceSearchBody serviceSearchBody) {
        // TODO: Implement...

        return null;
    }

    /**
     * Retrieves the extended information of the Services resulting from the search
     *
     */
    public ServiceHierarchicalResultResponse serviceControllerSearchServiceHierarchical(ServiceSearchBody serviceSearchBody) {
        // TODO: Implement...

        return null;
    }

    /**
     * Retrieves the minimal information of the Services resulting from the search
     *
     */
    public MinimalResultResponse serviceControllerSearchServiceMinimal(ServiceSearchBody serviceSearchBody) {
        // TODO: Implement...

        return null;
    }

    /**
     * Updates the Service identified by the given universal Id
     *
     */
    public void serviceControllerUpdateService(String serviceId, UpdateDescribedBody updateDescribedBody) {
        // TODO: Implement...

        
    }

}
