package org.ni2.v01.api.impl;

import org.ni2.v01.api.*;
import org.ni2.v01.model.QualificationsResponse;
import org.ni2.v01.model.ServiceTagsBody;

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
public class Class7EligibilityApiServiceImpl implements Class7EligibilityApi {
    /**
     * Establishes if and how a service could be delivered to an existing address
     *
     * There are different ways in which a service is valid at a location. * Automatic Design - Automatic Provisioning (intact service) * Automatic Design - Manual Provisioning (engineer installation required) * Manual Design - Manual Provisioning (design required) * No Service (shortfall)  
     *
     */
    public QualificationsResponse serviceEligibilityControllerQualifyAddress(String addressId, ServiceTagsBody serviceTagsBody) {
        // TODO: Implement...

        return null;
    }

}
