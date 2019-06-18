/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.radiology.fhir.procedurerequest.web.controller;

import org.apache.commons.beanutils.PropertyUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.api.context.Context;
import org.openmrs.module.radiology.fhir.procedurerequest.ProcedureRequest;
import org.openmrs.module.radiology.fhir.procedurerequest.ProcedureRequestService;
import org.openmrs.module.webservices.rest.SimpleObject;
import org.openmrs.module.webservices.rest.test.Util;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.response.ResourceDoesNotSupportOperationException;
import org.openmrs.module.webservices.rest.web.v1_0.controller.MainResourceControllerTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class ProcedureRequestControllerTest extends MainResourceControllerTest {


    private final String TEST_DATASET = "ProcedureRequestControllerTestDataset.xml";

    private final String EXISTING_PROCEDUR_REQUEST_NOTE = "none";

    @Before
    public void before() throws Exception {
        executeDataSet(TEST_DATASET);
    }

    @Override
    public String getURI() {
        return "procedurerequest";
    }

    @Override
    public String getUuid() {
        return "f689a577-eb63-4e6b-9941-13c7880f5590";
    }

    @Override
    public long getAllCount() {
        return 1;
    }

    /**
     * @throws Exception
     */
    @Test
    public void getProcedureRequest_shouldGetADefaultRepresentationOfAProcedureRequest() throws Exception {

        MockHttpServletRequest req = request(RequestMethod.GET, getURI() + "/" + getUuid());
        SimpleObject result = deserialize(handle(req));

        assertNotNull(result);
        Util.log("ProcedureRequest retrieved (default)", result);
        Assert.assertEquals(getUuid(), PropertyUtils.getProperty(result, "uuid"));
    }

    /**
     * @throws Exception
     */
    @Test
    public void getUser_shouldGetAFullRepresentationOfAPatient() throws Exception {

        MockHttpServletRequest req = request(RequestMethod.GET, getURI() + "/" + getUuid());
        req.addParameter(RestConstants.REQUEST_PROPERTY_FOR_REPRESENTATION, RestConstants.REPRESENTATION_FULL);

        SimpleObject result = deserialize(handle(req));
        Util.log("ProcedureRequest retrieved (full)", result);

        assertNotNull(result);
        assertEquals(getUuid(), PropertyUtils.getProperty(result, "uuid"));

        assertNotNull(PropertyUtils.getProperty(result, "note"));
        assertEquals(EXISTING_PROCEDUR_REQUEST_NOTE, PropertyUtils.getProperty(result, "note"));
    }

    /**
     * @see org.openmrs.module.webservices.rest.web.v1_0.controller.MainResourceControllerTest#shouldGetAll()
     */
    @Override
    @Test(expected = ResourceDoesNotSupportOperationException.class)
    public void shouldGetAll() throws Exception {
        super.shouldGetAll();
    }

    @Test
    public void createProcedureRequest_shouldCreateNewProcedureRequest() throws Exception {
        SimpleObject request = new SimpleObject();

        request.add("identifier", new Date().toString());
        request.add("doNotPerform", false);
        request.add("requester", "53f7a3ee-39e8-487d-ac02-3888ef2a6d62");
        request.add("subject", "d2c1adbf-d9fa-11e5-90c3-08002719a237");
        request.add("priority", "ROUTINE");
        request.add("intent", "ORDER");
        request.add("status", "COMPLETED");

        String json = new ObjectMapper().writeValueAsString(request);

        MockHttpServletRequest req = request(RequestMethod.POST, getURI());
        req.setContent(json.getBytes());

        SimpleObject newRequest = deserialize(handle(req));

        Util.log("Created Request", newRequest);
        assertNotNull(PropertyUtils.getProperty(newRequest, "uuid"));

        ProcedureRequest savedRequest = Context.getService(ProcedureRequestService.class)
                .getProcedureRequestByUuid(newRequest.get("uuid"));
        assertNotNull(savedRequest);
        assertNotNull(savedRequest.getIdentifier());
        assertNotNull(savedRequest.getIntent());
        assertNotNull(savedRequest.getPriority());
        assertNotNull(savedRequest.getStatus());
        assertNotNull(savedRequest.getSubject());
        assertNotNull(savedRequest.getRequester());

        assertThat(savedRequest.getRequester()
                .getUuid(),
            is("53f7a3ee-39e8-487d-ac02-3888ef2a6d62"));
        assertThat(savedRequest.getRequester()
                .getId(),
            is(2));
        assertThat(savedRequest.getSubject()
                .getUuid(),
            is("d2c1adbf-d9fa-11e5-90c3-08002719a237"));
        assertThat(savedRequest.getSubject()
                .getId(),
            is(70011));
    }
}
