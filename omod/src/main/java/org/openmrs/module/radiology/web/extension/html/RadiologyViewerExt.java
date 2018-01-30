/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.radiology.web.extension.html;

import org.openmrs.api.context.Context;
import org.openmrs.module.web.extension.LinkExt;
import static org.openmrs.module.radiology.RadiologyPrivileges.VIEW_GUTTERLIST_RADIOLOGY_LINK;

/**
 * @author surap
 */
public class RadiologyViewerExt extends LinkExt {
    
    
    @Override
    public String getLabel() {
        return "Radiology";
    }
    
    @Override
    public String getUrl() {
        return "/module/radiology/ohif.htm";
    }
    
    @Override
    public String getRequiredPrivilege() {
        return VIEW_GUTTERLIST_RADIOLOGY_LINK;
    }
    
}
