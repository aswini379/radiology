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

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

/**
 * Hibernate specific ProcedureRequest related functions. This class should not be used directly. All
 * calls should go through the {@link ProcedureRequestService} methods.
 *
 * @see ProcedureRequestDAO
 * @see ProcedureRequestService
 */
class HibernateProcedureRequestDAO implements ProcedureRequestDAO {


    private SessionFactory sessionFactory;

    /**
     * Set session factory that allows us to connect to the database that Hibernate knows about.
     *
     * @param sessionFactory
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * @return the sessionFactory
     */
    public SessionFactory getSessionFactory() {

        return sessionFactory;
    }

    /**
     * @see ProcedureRequestService#getProcedureRequest(Integer)
     */
    @Override
    public ProcedureRequest getProcedureRequest(Integer requestId) {

        return (ProcedureRequest) sessionFactory.getCurrentSession()
                .get(ProcedureRequest.class, requestId);
    }

    /**
     * @see ProcedureRequestService#getProcedureRequestByUuid(String)
     */
    @Override
    public ProcedureRequest getProcedureRequestByUuid(String uuid) {

        return (ProcedureRequest) sessionFactory.getCurrentSession()
                .createCriteria(ProcedureRequest.class)
                .add(Restrictions.eq("uuid", uuid))
                .uniqueResult();
    }

    /**
     * Save a new ProcedureRequest or Update an existing ProcedureRequest
     * @see ProcedureRequestService#saveProcedureRequest(ProcedureRequest)
     */
    @Override
    public ProcedureRequest saveProcedureRequest(ProcedureRequest procedureRequest) {

        sessionFactory.getCurrentSession()
                .saveOrUpdate(procedureRequest);
        return procedureRequest;
    }
}
