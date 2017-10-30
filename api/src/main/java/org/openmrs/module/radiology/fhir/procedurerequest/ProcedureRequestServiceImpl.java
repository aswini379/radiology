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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.APIException;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.springframework.transaction.annotation.Transactional;

/**
 * This is a default implementation of {@link ProcedureRequestService
 */
@Transactional(readOnly = true)
class ProcedureRequestServiceImpl extends BaseOpenmrsService implements ProcedureRequestService {
    
    
    private static final Log log = LogFactory.getLog(ProcedureRequestServiceImpl.class);
    
    private ProcedureRequestDAO procedureRequestDAO;
    
    /**
     * @return the procedureRequestDAO
     */
    public ProcedureRequestDAO getProcedureRequestDAO() {
        return procedureRequestDAO;
    }
    
    /**
     * @param procedureRequestDAO, the procedureRequestDAO to be set
     */
    public void setProcedureRequestDAO(ProcedureRequestDAO procedureRequestDAO) {
        this.procedureRequestDAO = procedureRequestDAO;
    }
    
    /**
     * @see ProcedureRequestService#getProcedureRequest(Integer)
     */
    @Override
    public ProcedureRequest getProcedureRequest(Integer requestId) {
        
        if (requestId == null) {
            throw new IllegalArgumentException("requestId cannot be null");
        }
        return procedureRequestDAO.getProcedureRequest(requestId);
    }
    
    /**
     * @see ProcedureRequestService#getProcedureRequestByUuid(String)
     */
    @Override
    public ProcedureRequest getProcedureRequestByUuid(String uuid) {
        
        if (uuid == null) {
            throw new IllegalArgumentException("ProcedureRequest uuid cannot be null");
        }
        return procedureRequestDAO.getProcedureRequestByUuid(uuid);
    }
    
    /**
     * @see ProcedureRequestService#addProcedureRequest(ProcedureRequest)
     */
    @Override
    public ProcedureRequest addProcedureRequest(ProcedureRequest procedureRequest) {
        
        if (procedureRequest == null) {
            throw new IllegalArgumentException("procedureRequest cannot be null");
        }
        if (procedureRequest.getRequestId() != null) {
            throw new APIException("ProcedureRequest.cannot.save.existing");
        }
        return procedureRequestDAO.saveProcedureRequest(procedureRequest);
    }
    
    /**
     * @see ProcedureRequestService#updateProcedureRequest(ProcedureRequest)
     */
    @Override
    public ProcedureRequest updateProcedureRequest(ProcedureRequest procedureRequest) {
        
        if (procedureRequest == null) {
            throw new IllegalArgumentException("procedureRequest cannot be null");
        }
        if (procedureRequest.getRequestId() == null) {
            throw new IllegalArgumentException("procedureRequest.requestId cannot be null");
        }
        return procedureRequestDAO.saveProcedureRequest(procedureRequest);
    }
}
