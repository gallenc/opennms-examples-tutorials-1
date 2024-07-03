package org.ni2.v01.api.impl;

import org.ni2.v01.api.*;
import org.ni2.v01.model.Address;
import org.ni2.v01.model.AddressBaseResultResponse;
import org.ni2.v01.model.AddressCreateBody;
import org.ni2.v01.model.AddressSearchBody;
import org.ni2.v01.model.AddressUpdateBody;
import org.ni2.v01.model.InstanceURL;
import org.ni2.v01.model.Minimal;
import org.ni2.v01.model.MinimalResultResponse;

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
public class Class1LocationApiServiceImpl implements Class1LocationApi {
    /**
     * Creates an Address
     *
     */
    public InstanceURL addressControllerCreateAddress(AddressCreateBody addressCreateBody) {
        // TODO: Implement...

        return null;
    }

    /**
     * Deletes the Address identified by the given universal Id
     *
     */
    public void addressControllerDeleteAddress(String addressId) {
        // TODO: Implement...

        
    }

    /**
     * Retrieves the base information of the Address identified by the given universal id
     *
     */
    public Address addressControllerGetAddressBase(String addressId) {
        // TODO: Implement...

        return null;
    }

    /**
     * Retrieves the minimal information of the Address identified by the given universal id
     *
     */
    public Minimal addressControllerGetAddressMinimal(String addressId) {
        // TODO: Implement...

        return null;
    }

    /**
     * Retrieves the base information of the Addresses resulting from the search
     *
     */
    public AddressBaseResultResponse addressControllerSearchAddressBase(AddressSearchBody addressSearchBody) {
        // TODO: Implement...

        return null;
    }

    /**
     * Retrieves the minimal information of the Addresses resulting from the search
     *
     */
    public MinimalResultResponse addressControllerSearchAddressMinimal(AddressSearchBody addressSearchBody) {
        // TODO: Implement...

        return null;
    }

    /**
     * Updates the Address identified by the given universal Id
     *
     */
    public void addressControllerUpdateAddress(String addressId, AddressUpdateBody addressUpdateBody) {
        // TODO: Implement...

        
    }

}
