/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.radiology.report.template.web.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.module.radiology.report.template.MrrtReportTemplateSearchCriteria;
import org.openmrs.module.radiology.report.template.MrrtReportTemplateService;
import org.openmrs.module.radiology.report.template.web.search.MrrtReportTemplateSearchHandler;
import org.openmrs.module.webservices.rest.SimpleObject;
import org.openmrs.module.webservices.rest.test.Util;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.response.ResourceDoesNotSupportOperationException;
import org.openmrs.module.webservices.rest.web.v1_0.controller.MainResourceControllerTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.bind.annotation.RequestMethod;

public class MrrtReportTemplateControllerTest extends MainResourceControllerTest {
    
    
    private static final String MRRT_REPORT_TEMPLATE_UUID = "aa551445-def0-4f93-9047-95f0a9afbdce";
    
    private static final String MRRT_REPORT_TEMPLATE_HTML = "";
    
    private static final String MRRT_REPORT_TEMPLATE_DCTERMS_IDENTIFIER = "org/radrep/0001";
    
    private static final String TEST_DATASET = "MrrtReportTemplateControllerTestDataset.xml";
    
    private static final String TITLE_QUERY = "Cardiac MRI";
    
    @Autowired
    MrrtReportTemplateService mrrtReportTemplateService;
    
    @Before
    public void setUp() throws Exception {
        executeDataSet(TEST_DATASET);
    }
    
    @Override
    public String getURI() {
        return "mrrtreporttemplate";
    }
    
    @Override
    public String getUuid() {
        return MRRT_REPORT_TEMPLATE_UUID;
    }
    
    @Override
    public long getAllCount() {
        return 1;
    }
    
    private static String getMrrtReportTemplateHtml() {
        return MRRT_REPORT_TEMPLATE_HTML;
    }
    
    private static String getMrrtReportTemplateDctermsIdentifier() {
        return MRRT_REPORT_TEMPLATE_DCTERMS_IDENTIFIER;
    }
    
    @Test
    public void getMrrtReportTemplate_shouldGETdefaultRepresentationOfMrrtReportTemplate() throws Exception {
        
        MockHttpServletRequest req = request(RequestMethod.GET, getURI() + "/" + getUuid());
        SimpleObject result = deserialize(handle(req));
        
        assertNotNull(result);
        Util.log("MrrtReportTemplate retrieved (default)", result);
        
        assertThat(PropertyUtils.getProperty(result, "uuid"), is(getUuid()));
        
        assertThat(PropertyUtils.getProperty(result, "html"), is(getMrrtReportTemplateHtml()));
        assertThat(PropertyUtils.getProperty(result, "dcTermsIdentifier"), is(getMrrtReportTemplateDctermsIdentifier()));
        assertThat(PropertyUtils.getProperty(result, "dcTermsTitle"), is(TITLE_QUERY));
    }
    
    @Test
    public void getMrrtReportTemplate_shouldGETfullRepresentationOfMrrtReportTemplate() throws Exception {
        
        MockHttpServletRequest req = request(RequestMethod.GET, getURI() + "/" + getUuid());
        req.addParameter(RestConstants.REQUEST_PROPERTY_FOR_REPRESENTATION, RestConstants.REPRESENTATION_FULL);
        
        SimpleObject result = deserialize(handle(req));
        Util.log("MrrtReportTemplate retrieved (full)", result);
        
        assertNotNull(result);
        assertEquals(getUuid(), PropertyUtils.getProperty(result, "uuid"));
        
        assertNotNull(PropertyUtils.getProperty(result, "html"));
        assertThat(PropertyUtils.getProperty(result, "html"), is(MRRT_REPORT_TEMPLATE_HTML));
    }
    
    @Test
    public void getMrrtReportTemplate_shouldSearchForTemplateWithTitle() throws Exception {
        
        MockHttpServletRequest mrrtReportTemplateRequest = request(RequestMethod.GET, getURI());
        mrrtReportTemplateRequest.setParameter(MrrtReportTemplateSearchHandler.REQUEST_PARAM_TITLE, TITLE_QUERY);
        mrrtReportTemplateRequest.setParameter("v", Representation.FULL.getRepresentation());
        SimpleObject resultMrrtReportTemplate = deserialize(handle(mrrtReportTemplateRequest));
        
        assertNotNull(resultMrrtReportTemplate);
        List<Object> hits = (List<Object>) resultMrrtReportTemplate.get("results");
        MrrtReportTemplateSearchCriteria searchCriteria =
                new MrrtReportTemplateSearchCriteria.Builder().withTitle(TITLE_QUERY)
                        .build();
        assertThat(hits.size(), is(1));
        assertThat(PropertyUtils.getProperty(hits.get(0), "uuid"),
            is(mrrtReportTemplateService.getMrrtReportTemplates(searchCriteria)
                    .get(0)
                    .getUuid()));
        assertThat(PropertyUtils.getProperty(resultMrrtReportTemplate, "totalCount"), is(nullValue()));
    }
    
    /**
     * @see org.openmrs.module.webservices.rest.web.v1_0.controller.MainResourceControllerTest#shouldGetAll()
     */
    @Override
    @Test(expected = ResourceDoesNotSupportOperationException.class)
    public void shouldGetAll() throws Exception {
        super.shouldGetAll();
    }
    
}
