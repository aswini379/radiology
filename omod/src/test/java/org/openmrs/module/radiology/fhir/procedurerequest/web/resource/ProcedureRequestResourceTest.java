/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.radiology.fhir.procedurerequest.web.resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.openmrs.api.context.Context;
import org.openmrs.module.radiology.fhir.procedurerequest.ProcedureRequest;
import org.openmrs.module.radiology.fhir.procedurerequest.ProcedureRequestService;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestUtil;
import org.openmrs.module.webservices.rest.web.representation.CustomRepresentation;
import org.openmrs.module.webservices.rest.web.representation.DefaultRepresentation;
import org.openmrs.module.webservices.rest.web.representation.FullRepresentation;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.response.ResourceDoesNotSupportOperationException;
import org.openmrs.module.webservices.rest.web.v1_0.resource.openmrs2_0.RestConstants2_0;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ Context.class, RestUtil.class })
public class ProcedureRequestResourceTest {
    
    
    private static final String PROCEDURE_REQUEST_UUID = "067a85fc-1316-45a3-848d-69ba192e64c4";
    
    private static final String PROCEDURE_REQUEST_IDENTIFIER = "12345";
    
    @Mock
    ProcedureRequestService procedureRequestService;
    
    @Mock
    ProcedureRequestResource procedureRequestResource = new ProcedureRequestResource();
    
    ProcedureRequest procedureRequest = new ProcedureRequest();
    
    @Before
    public void setUp() throws Exception {
        procedureRequest.setUuid(PROCEDURE_REQUEST_UUID);
        procedureRequest.setIdentifier(PROCEDURE_REQUEST_IDENTIFIER);
        
        PowerMockito.mockStatic(RestUtil.class);
        PowerMockito.mockStatic(Context.class);
        when(Context.getService(ProcedureRequestService.class)).thenReturn(procedureRequestService);
        when(procedureRequestService.getProcedureRequestByUuid(PROCEDURE_REQUEST_UUID)).thenReturn(procedureRequest);
    }
    
    /**
     * @see ProcedureRequestResource#getRepresentationDescription(Representation)
     * @verifies return default representation given instance of defaultrepresentation
     */
    @Test
    public void getRepresentationDescription_shouldReturnDefaultRepresentationGivenInstanceOfDefaultrepresentation()
            throws Exception {
        DefaultRepresentation defaultRepresentation = new DefaultRepresentation();
        
        DelegatingResourceDescription resourceDescription =
                procedureRequestResource.getRepresentationDescription(defaultRepresentation);
        assertThat(resourceDescription.getProperties()
                .keySet(),
            contains("uuid", "display"));
    }
    
    /**
     * @see ProcedureRequestResource#getRepresentationDescription(Representation)
     * @verifies return full representation given instance of FullRepresentation
     */
    @Test
    public void getRepresentationDescription_shouldReturn_shouldReturnFullRepresentationGivenInstanceOfFullrepresentation()
            throws Exception {
        FullRepresentation fullRepresentation = new FullRepresentation();
        
        DelegatingResourceDescription resourceDescription =
                procedureRequestResource.getRepresentationDescription(fullRepresentation);
        assertThat(resourceDescription.getProperties()
                .keySet(),
            contains("uuid", "identifier", "status", "intent", "priority", "doNotPerform", "category", "code", "subject",
                "context", "authoredOn", "requester", "reasonCode", "note", "display"));
        
    }
    
    /**
     * @see ProcedureRequestResource#getRepresentationDescription(Representation)
     * @verifies return null for representation other then default or full
     */
    @Test
    public void getRepresentationDescription_shouldReturnNullForRepresentationOtherThenDefaultOrFull() throws Exception {
        CustomRepresentation customRepresentation = new CustomRepresentation("some");
        
        assertThat(procedureRequestResource.getRepresentationDescription(customRepresentation), is(nullValue()));
        
    }
    
    /**
     * @see ProcedureRequestResource#getDisplayString(ProcedureRequest)
     * @verifies return Identifier of Given ProcedureRequest
     */
    @Test
    public void getDisplayString_shouldReturnIdentifierOfGivenProcedureRequest() throws Exception {
        assertThat(procedureRequestResource.getDisplayString(procedureRequest), is(PROCEDURE_REQUEST_IDENTIFIER));
    }
    
    /**
     * @see ProcedureRequestResource#getByUniqueId(String)
     * @verifies return ProcedureRequest given uuid
     */
    @Test
    public void getByUniqueId_shouldReturnProcedureRequestGivenUuid() throws Exception {
        assertNotNull(procedureRequestResource.getByUniqueId(PROCEDURE_REQUEST_UUID));
    }
    
    /**
     * @see ProcedureRequestResource#newDelegate()
     * @verifies throw ResourceDoesNotSupportOperationException
     */
    @Test(expected = ResourceDoesNotSupportOperationException.class)
    public void newDelegate_shouldThrowResourceDoesNotSupportOperationException() throws Exception {
        procedureRequestResource.newDelegate();
    }
    
    /**
     * @see ProcedureRequestResource#save(ProcedureRequest)
     * @verifies throw ResourceDoesNotSupportOperationException
     */
    @Test(expected = ResourceDoesNotSupportOperationException.class)
    public void save_shouldThrowResourceDoesNotSupportOperationException() throws Exception {
        procedureRequestResource.save(procedureRequest);
    }
    
    /**
     * @see ProcedureRequestResource#delete(ProcedureRequest, String, RequestContext)
     * @verifies throw ResourceDoesNotSupportOperationException
     */
    @Test(expected = ResourceDoesNotSupportOperationException.class)
    public void delete_shouldThrowResourceDoesNotSupportOperationException() throws Exception {
        RequestContext context = new RequestContext();
        procedureRequestResource.delete(procedureRequest, "Not a Request", context);
    }
    
    /**
     * @see ProcedureRequestResource#purge(ProcedureRequest, RequestContext)
     * @verifies throw ResourceDoesNotSupportOperationException
     */
    @Test(expected = ResourceDoesNotSupportOperationException.class)
    public void purge_shouldThrowResourceDoesNotSupportOperationException() throws Exception {
        RequestContext context = new RequestContext();
        procedureRequestResource.purge(procedureRequest, context);
    }
    
    /**
     * @see ProcedureRequestResource#getResourceVersion()
     * @verifies return supported resource version
     */
    @Test
    public void getResourceVersion_shouldReturnSupportedResourceVersion() throws Exception {
        assertThat(procedureRequestResource.getResourceVersion(), is(RestConstants2_0.RESOURCE_VERSION));
    }
}
