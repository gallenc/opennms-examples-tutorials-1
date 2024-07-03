package org.ni2.v01.api.impl;

import org.ni2.v01.api.*;
import org.ni2.v01.model.DefaultSearchBody;
import org.ni2.v01.model.InstanceURL;
import org.ni2.v01.model.Minimal;
import org.ni2.v01.model.MinimalResultResponse;
import org.ni2.v01.model.Party;
import org.ni2.v01.model.PartyBaseResultResponse;
import org.ni2.v01.model.PartyCreateBody;
import org.ni2.v01.model.PartyExtended;
import org.ni2.v01.model.PartyExtendedResultResponse;
import org.ni2.v01.model.PartyUpdateBody;

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
public class Class2PartyApiServiceImpl implements Class2PartyApi {
    /**
     * Creates a Party
     *
     */
    public InstanceURL partyControllerCreateParty(PartyCreateBody partyCreateBody) {
        // TODO: Implement...

        return null;
    }

    /**
     * Deletes the Party identified by the given universal Id
     *
     */
    public void partyControllerDeleteParty(String partyId) {
        // TODO: Implement...

        
    }

    /**
     * Retrieves the base information of the Party identified by the given universal id
     *
     */
    public Party partyControllerGetPartyBase(String partyId) {
        // TODO: Implement...

        return null;
    }

    /**
     * Retrieves the extended information of the Address identified by the given universal id
     *
     */
    public PartyExtended partyControllerGetPartyExtended(String partyId) {
        // TODO: Implement...

        return null;
    }

    /**
     * Retrieves the minimal information of the Party identified by the given universal id
     *
     */
    public Minimal partyControllerGetPartyMinimal(String partyId) {
        // TODO: Implement...

        return null;
    }

    /**
     * Retrieves the base information of the Parties resulting from the search
     *
     */
    public PartyBaseResultResponse partyControllerSearchPartyBase(DefaultSearchBody defaultSearchBody) {
        // TODO: Implement...

        return null;
    }

    /**
     * Retrieves the extended information of the Addresses resulting from the search
     *
     */
    public PartyExtendedResultResponse partyControllerSearchPartyExtended(DefaultSearchBody defaultSearchBody) {
        // TODO: Implement...

        return null;
    }

    /**
     * Retrieves the minimal information of the Parties resulting from the search
     *
     */
    public MinimalResultResponse partyControllerSearchPartyMinimal(DefaultSearchBody defaultSearchBody) {
        // TODO: Implement...

        return null;
    }

    /**
     * Updates the Party identified by the given universal Id
     *
     */
    public void partyControllerUpdateParty(String partyId, PartyUpdateBody partyUpdateBody) {
        // TODO: Implement...

        
    }

}
