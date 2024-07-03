package org.ni2.v01.api.impl;

import org.ni2.v01.api.*;
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
public class Class3EventApiServiceImpl implements Class3EventApi {
    /**
     * Add document to the Event identified by the given universalId
     *
     */
    public void eventControllerAddDocument(String eventId, AddDocumentationCreateBody addDocumentationCreateBody) {
        // TODO: Implement...

        
    }

    /**
     * Creates an Event
     *
     */
    public InstanceURL eventControllerCreateEvent(EventCreateBody eventCreateBody) {
        // TODO: Implement...

        return null;
    }

    /**
     * Documents the Event identified by the given universalId
     *
     */
    public void eventControllerDocument(String eventId, DocumentationCreateBody documentationCreateBody) {
        // TODO: Implement...

        
    }

    /**
     * Executes an action on the Event identified by the given universalId
     *
     */
    public void eventControllerExecuteLifecycleAction(String eventId, ActionBody actionBody) {
        // TODO: Implement...

        
    }

    /**
     * Retrieves the base information of the Event identified by the given universal id
     *
     */
    public Event eventControllerGetEventBase(String eventId) {
        // TODO: Implement...

        return null;
    }

    /**
     * Retrieves the extended information of the Event identified by the given universal id
     *
     */
    public EventExtended eventControllerGetEventExtended(String eventId) {
        // TODO: Implement...

        return null;
    }

    /**
     * Retrieves the extended information of the Event identified by the given universal id
     *
     */
    public EventHierarchical eventControllerGetEventHierarchical(String eventId) {
        // TODO: Implement...

        return null;
    }

    /**
     * Retrieves the minimal information of the Event identified by the given universal id
     *
     */
    public Minimal eventControllerGetEventMinimal(String eventId) {
        // TODO: Implement...

        return null;
    }

    /**
     * Retrieves the base information of the Events resulting from the search
     *
     */
    public EventBaseResultResponse eventControllerSearchEventBase(EventSearchBody eventSearchBody) {
        // TODO: Implement...

        return null;
    }

    /**
     * Retrieves the extended information of the Events resulting from the search
     *
     */
    public EventExtendedResultResponse eventControllerSearchEventExtended(EventSearchBody eventSearchBody) {
        // TODO: Implement...

        return null;
    }

    /**
     * Retrieves the extended information of the Events resulting from the search
     *
     */
    public EventHierarchicalResultResponse eventControllerSearchEventHierarchical(EventSearchBody eventSearchBody) {
        // TODO: Implement...

        return null;
    }

    /**
     * Retrieves the minimal information of the Events resulting from the search
     *
     */
    public MinimalResultResponse eventControllerSearchEventMinimal(EventSearchBody eventSearchBody) {
        // TODO: Implement...

        return null;
    }

    /**
     * Updates the Documentation identified by the given universal Id
     *
     */
    public void eventControllerUpdateDocument(String documentId, DocumentationUpdateBody documentationUpdateBody) {
        // TODO: Implement...

        
    }

    /**
     * Updates the Event identified by the given universal Id
     *
     */
    public void eventControllerUpdateEvent(String eventId, EventUpdateBody eventUpdateBody) {
        // TODO: Implement...

        
    }

}
