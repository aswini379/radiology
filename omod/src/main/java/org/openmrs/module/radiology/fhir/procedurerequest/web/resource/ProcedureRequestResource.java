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

import org.openmrs.api.context.Context;
import org.openmrs.module.radiology.fhir.procedurerequest.ProcedureRequest;
import org.openmrs.module.radiology.fhir.procedurerequest.ProcedureRequestService;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.PropertyGetter;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.DefaultRepresentation;
import org.openmrs.module.webservices.rest.web.representation.FullRepresentation;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.impl.DataDelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.response.ResourceDoesNotSupportOperationException;
import org.openmrs.module.webservices.rest.web.v1_0.resource.openmrs2_0.RestConstants2_0;

/**
 * {@link Resource} for {@link ProcedureRequest}, supporting Get, Create and Update operations.
 */
@Resource(name = RestConstants.VERSION_1 + "/procedurerequest", supportedClass = ProcedureRequest.class,
        supportedOpenmrsVersions = { "2.*.*" })
public class ProcedureRequestResource extends DataDelegatingCrudResource<ProcedureRequest> {
    
    
    /**
     * @see org.openmrs.module.webservices.rest.web.resource.impl.DelegatingCrudResource#getRepresentationDescription(org.openmrs.module.webservices.rest.web.representation.Representation)
     * @should return default representation given instance of DefaultRepresentation
     * @should return full representation given instance of FullRepresentation
     * @should return null for representation other then default or full
     */
    @Override
    public DelegatingResourceDescription getRepresentationDescription(Representation rep) {
        
        if (rep instanceof DefaultRepresentation) {
            final DelegatingResourceDescription description = new DelegatingResourceDescription();
            
            description.addProperty("uuid");
            description.addProperty("display");
            description.addSelfLink();
            description.addLink("full", ".?v=" + RestConstants.REPRESENTATION_FULL);
            return description;
            
        } else if (rep instanceof FullRepresentation) {
            final DelegatingResourceDescription description = new DelegatingResourceDescription();
            
            description.addProperty("uuid");
            description.addProperty("identifier");
            description.addProperty("status");
            description.addProperty("intent");
            description.addProperty("priority");
            description.addProperty("doNotPerform");
            description.addProperty("category", Representation.REF);
            description.addProperty("code", Representation.REF);
            description.addProperty("subject", Representation.REF);
            description.addProperty("context", Representation.REF);
            description.addProperty("authoredOn");
            description.addProperty("requester", Representation.REF);
            description.addProperty("reasonCode", Representation.REF);
            description.addProperty("note");
            description.addProperty("display");
            description.addSelfLink();
            return description;
            
        }
        return null;
    }
    
    /**
     * Display string for {@link ProcedureRequest}
     *
     * @param procedureRequest ProcedureRequest of which display string shall be returned
     * @return identifier of given mrrtReportTemplate
     * @should return identifier of given procedureRequest
     */
    @PropertyGetter("display")
    public String getDisplayString(ProcedureRequest procedureRequest) {
        return procedureRequest.getIdentifier();
    }
    
    /**
     * @see org.openmrs.module.webservices.rest.web.resource.impl.BaseDelegatingResource#getByUniqueId(java.lang.String)
     * @should return ProcedureRequest given it's Uuid
     */
    @Override
    public ProcedureRequest getByUniqueId(String uuid) {
        return Context.getService(ProcedureRequestService.class)
                .getProcedureRequestByUuid(uuid);
    }
    
    /**
     * @see org.openmrs.module.webservices.rest.web.resource.impl.BaseDelegatingResource#newDelegate()
     * @should throw ResourceDoesNotSupportOperationException
     */
    @Override
    public ProcedureRequest newDelegate() throws ResourceDoesNotSupportOperationException {
        throw new ResourceDoesNotSupportOperationException();
    }
    
    /**
     * @see org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceHandler#save(java.lang.Object)
     * @should throw ResourceDoesNotSupportOperationException
     */
    @Override
    public ProcedureRequest save(ProcedureRequest procedureRequest) throws ResourceDoesNotSupportOperationException {
        throw new ResourceDoesNotSupportOperationException();
    }
    
    /**
     * @see org.openmrs.module.webservices.rest.web.resource.impl.BaseDelegatingResource#delete(java.lang.Object,
     *      java.lang.String, org.openmrs.module.webservices.rest.web.RequestContext)
     * @should throw ResourceDoesNotSupportOperationException
     */
    @Override
    protected void delete(ProcedureRequest procedureRequest, String s, RequestContext requestContext)
            throws ResourceDoesNotSupportOperationException {
        throw new ResourceDoesNotSupportOperationException();
    }
    
    /**
     * @see org.openmrs.module.webservices.rest.web.resource.impl.BaseDelegatingResource#purge(java.lang.Object,
     *      org.openmrs.module.webservices.rest.web.RequestContext)
     * @should throw ResourceDoesNotSupportOperationException
     */
    @Override
    public void purge(ProcedureRequest procedureRequest, RequestContext requestContext)
            throws ResourceDoesNotSupportOperationException {
        throw new ResourceDoesNotSupportOperationException();
    }
    
    /**
     * @see org.openmrs.module.webservices.rest.web.resource.impl.BaseDelegatingResource#getResourceVersion()
     */
    @Override
    public String getResourceVersion() {
        return RestConstants2_0.RESOURCE_VERSION;
    }
    
}
