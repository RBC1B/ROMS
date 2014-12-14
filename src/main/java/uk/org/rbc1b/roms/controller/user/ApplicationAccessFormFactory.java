/*
 * The MIT License
 *
 * Copyright 2014 RBC1B.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package uk.org.rbc1b.roms.controller.user;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import uk.org.rbc1b.roms.db.application.Application;
import uk.org.rbc1b.roms.db.application.ApplicationAccess;
import uk.org.rbc1b.roms.security.AccessLevel;

/**
 * Generates the list of application access to use in form.
 */
public final class ApplicationAccessFormFactory {

    private static final ApplicationAccess NO_ACCESS = new ApplicationAccess();
    static {
        NO_ACCESS.setDepartmentAccess(AccessLevel.NOACCESS.getCode());
        NO_ACCESS.setNonDepartmentAccess(AccessLevel.NOACCESS.getCode());
    }

    /**
     * This stops anyone creating this object.
     */
    private ApplicationAccessFormFactory() {
    }

    /**
     * Generates the list.
     *
     * @param applications the list of all applications
     * @param applicationAccesses the current list of access for the user
     * @return application access form list with values set
     */
    public static List<ApplicationAccessForm> generateApplicationAccessForm(List<Application> applications,
            List<ApplicationAccess> applicationAccesses) {
        List<ApplicationAccessForm> applicationAccessForms;
        applicationAccessForms = new ArrayList<ApplicationAccessForm>();
        for (Application application : applications) {
            ApplicationAccessForm applicationAccessForm = new ApplicationAccessForm();
            applicationAccessForm.setCode(application.getCode());
            applicationAccessForm.setName(application.getName());

            ApplicationAccess access = findApplicationAccess(application, applicationAccesses);
            if (access == null) {
                access = NO_ACCESS;
            }

            applicationAccessForm.setApplicationAccessId(access.getApplicationAccessId());
            applicationAccessForm.setDeptPermission(access.getDepartmentAccess());
            applicationAccessForm.setNonDeptPermission(access.getNonDepartmentAccess());

            applicationAccessForms.add(applicationAccessForm);
        }
        return applicationAccessForms;
    }

    private static ApplicationAccess findApplicationAccess(Application application,
            List<ApplicationAccess> applicationAccesses) {
        if (CollectionUtils.isEmpty(applicationAccesses)) {
            return null;
        }
        for (ApplicationAccess access : applicationAccesses) {
            if (access.getApplication().getCode().equalsIgnoreCase(application.getCode())) {
                return access;
            }
        }
        return null;
    }

}
