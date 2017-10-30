/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.radiology.fhir.procedurerequest;

import org.openmrs.annotation.Authorized;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.radiology.RadiologyPrivileges;

public interface ProcedureRequestService extends OpenmrsService {
    
    
    /**
     * Get the {@code ProcedureRequest} by it's {@code requestId}
     *
     * @param requestId, the requestId of the ProcedureRequest
     * @return the ProcedureRequest
     * @return null if no match was found
     * @throws IllegalArgumentException if requestId is null
     * @should throw IllegalArgumentException given null
     * @should return null if no match was found
     */
    @Authorized(RadiologyPrivileges.GET_RADIOLOGY_PROCEDURE_REQUESTS)
    ProcedureRequest getProcedureRequest(Integer requestId);
    
    /**
     * Get the {@code ProcedureRequest} by its {@code UUID}.
     *
     * @param uuid, the uuid of the ProcedureRequest
     * @return the ProcedureRequest
     * @return null if no match was found
     * @throws IllegalArgumentException if uuid is null
     * @should throw IllegalArgumentException given null
     */
    @Authorized(RadiologyPrivileges.GET_RADIOLOGY_PROCEDURE_REQUESTS)
    ProcedureRequest getProcedureRequestByUuid(String uuid);
    
    /**
     * Adds a new {@code ProcedureRequest} to the database
     *
     * @param procedureRequest new ProcedureRequest to be added
     * @return the newly created ProcedureRequest
     * @throws IllegalArgumentException if procedureRequest is null
     * @throws APIException on saving an existing ProcedureRequest
     * @should add a new ProcedureRequest
     * @should throw IllegalArgumentException given null
     * @should throw APIException if ProcedureRequest already exists
     */
    @Authorized(RadiologyPrivileges.ADD_RADIOLOGY_PROCEDURE_REQUESTS)
    ProcedureRequest addProcedureRequest(ProcedureRequest procedureRequest);
    
    /**
     * Updates an existing {@code ProcedureRequest} in the database
     *
     * @param procedureRequest the existing ProcedureRequest to be updated
     * @return the newly created ProcedureRequest
     * @throws IllegalArgumentException if procedureRequest is null
     * @throws IllegalArgumentException if procedureRequest requestId is null
     * @should update existing ProcedureRequest
     * @should throw IllegalArgumentException given null
     * @should throw IllegalArgumentException given procedureRequest requestId null
     */
    @Authorized(RadiologyPrivileges.EDIT_RADIOLOGY_PROCEDURE_REQUESTS)
    ProcedureRequest updateProcedureRequest(ProcedureRequest procedureRequest);
    
}
