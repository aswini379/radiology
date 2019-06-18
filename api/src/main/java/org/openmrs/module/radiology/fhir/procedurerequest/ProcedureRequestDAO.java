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

/**
 * {@code ProcedureRequest} related database methods.
 *
 * @see ProcedureRequestService
 * @see ProcedureRequest
 */
public interface ProcedureRequestDAO {


    /**
     * @see ProcedureRequestService#getProcedureRequest(Integer)
     */
    ProcedureRequest getProcedureRequest(Integer requestId);

    /**
     * @see ProcedureRequestService#getProcedureRequestByUuid(String)
     */
    ProcedureRequest getProcedureRequestByUuid(String uuid);

    /**
     * @see ProcedureRequestService#createProcedureRequest(ProcedureRequest)
     */
    ProcedureRequest saveProcedureRequest(ProcedureRequest procedureRequest);

}
