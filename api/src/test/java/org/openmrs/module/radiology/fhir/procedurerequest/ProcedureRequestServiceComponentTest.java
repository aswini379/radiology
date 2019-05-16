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

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.openmrs.api.APIException;
import org.openmrs.test.BaseModuleContextSensitiveTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Tests {@link ProcedureRequestService
 * }
 */
public class ProcedureRequestServiceComponentTest extends BaseModuleContextSensitiveTest {


    private static final String TEST_DATASET =
            "org/openmrs/module/radiology/include/ProcedureRequestServiceComponentTestDataset.xml";

    private static final int EXISTING_PROCEDURE_REQUEST_REQUEST_ID = 1;

    private static final int NON_EXISTING_PROCEDURE_REQUEST_REQUEST_ID = 30012;

    @Autowired
    private ProcedureRequestService procedureRequestService;

    @Mock
    ProcedureRequest procedureRequest;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        executeDataSet(TEST_DATASET);
    }

    /**
     * @see ProcedureRequestService#getProcedureRequest(Integer)
     * @verifies throw IllegalArgumentException given null
     */
    @Test
    public void getProcedureRequest_shouldThrowIllegalArgumentExceptionGivenNull() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("requestId cannot be null");
        procedureRequestService.getProcedureRequest(null);
    }

    /**
     * @see ProcedureRequestService#getProcedureRequest(Integer)
     * @verifies return null if no match found
     */
    @Test
    public void getProcedureRequest_shouldReturnNullIfNoMatchFound() throws Exception {
        ProcedureRequest notARequest =
                procedureRequestService.getProcedureRequest(NON_EXISTING_PROCEDURE_REQUEST_REQUEST_ID);

        assertNull(notARequest);
    }

    /**
     * @see ProcedureRequestService#getProcedureRequestByUuid(String)
     * @verifies throw IllegalArgumentException given null
     */
    @Test
    public void getProcedureRequestByUuid_shouldThrowIllegalArgumentExceptionGivenNull() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("ProcedureRequest uuid cannot be null");
        procedureRequestService.getProcedureRequestByUuid(null);
    }

    /**
     * @see ProcedureRequestService#addProcedureRequest(ProcedureRequest)
     * @verifies add a new ProcedureRequest
     */
    @Test
    public void addProcedureRequest_shouldAddNewProcedureRequest() throws Exception {
        ProcedureRequest procedureRequest = new ProcedureRequest();

        procedureRequest.setIntent(ProcedureRequest.Intent.ORDER);
        procedureRequest.setPriority(ProcedureRequest.Priority.ROUTINE);
        procedureRequest.setDoNotPerform(false);

        ProcedureRequest saved = procedureRequestService.addProcedureRequest(procedureRequest);
        ProcedureRequest newRequest = procedureRequestService.getProcedureRequest(saved.getId());

        assertNotNull(saved);
        assertNotNull(newRequest);
        assertEquals(procedureRequest.getIntent(), newRequest.getIntent());
        assertEquals(procedureRequest.getPriority(), newRequest.getPriority());
    }

    /**
     * @see ProcedureRequestService#addProcedureRequest(ProcedureRequest)
     * @verifies throw IllegalArgumentException given null
     */
    @Test
    public void addProcedureRequest_shouldThrowIllegalArgumentExceptionGivenNull() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("procedureRequest cannot be null");
        procedureRequestService.addProcedureRequest(null);
    }

    /**
     * @see ProcedureRequestService#addProcedureRequest(ProcedureRequest)
     * @verifies throw APIException if ProcedureRequest already exists
     */
    @Test
    public void addProcedureRequest_shouldThrowAPIExceptionIfProcedureRequestExists() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("procedureRequest cannot be null");
        procedureRequestService.addProcedureRequest(null);
    }

    /**
     * @see ProcedureRequestService#updateProcedureRequest(ProcedureRequest)
     * @verifies update existing ProcedureRequest
     */
    @Test
    public void updateProcedureRequest_shouldUpdateExistingProcedureRequest() throws Exception {
        ProcedureRequest existingRequest =
                procedureRequestService.getProcedureRequest(EXISTING_PROCEDURE_REQUEST_REQUEST_ID);

        assertNotNull(existingRequest);

        existingRequest.setIntent(ProcedureRequest.Intent.ORDER);
        procedureRequestService.updateProcedureRequest(existingRequest);

        ProcedureRequest savedRequest = procedureRequestService.getProcedureRequest(EXISTING_PROCEDURE_REQUEST_REQUEST_ID);

        assertEquals(existingRequest.getId(), savedRequest.getId());
        assertEquals(ProcedureRequest.Intent.ORDER, savedRequest.getIntent());

    }

    /**
     * @see ProcedureRequestService#updateProcedureRequest(ProcedureRequest)
     * @verifies throw IllegalArgumentException given null
     */
    @Test
    public void updateProcedureRequest_shouldThrowIllegalArgumentExceptionGivenNull() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("procedureRequest cannot be null");
        procedureRequestService.updateProcedureRequest(null);
    }

    /**
     * @see ProcedureRequestService#updateProcedureRequest(ProcedureRequest)
     * @verifies throw IllegalArgumentException given procedureRequest requestId null
     */
    @Test
    public void updateProcedureRequest_shouldThrowIllegalArgumentExceptionGivenProcedureRequestWithRequestIdNull()
            throws Exception {
        when(procedureRequest.getRequestId()).thenReturn(null);

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("procedureRequest.requestId cannot be null");
        procedureRequestService.updateProcedureRequest(procedureRequest);
        procedureRequestService.updateProcedureRequest(procedureRequest);
    }

}
