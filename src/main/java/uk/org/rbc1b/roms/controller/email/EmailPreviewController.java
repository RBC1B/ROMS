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
package uk.org.rbc1b.roms.controller.email;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;
import uk.org.rbc1b.roms.controller.BadRequestException;
import uk.org.rbc1b.roms.controller.project.ProjectAvailabilityEmailGenerator;
import uk.org.rbc1b.roms.db.email.Email;
import uk.org.rbc1b.roms.db.project.ProjectAvailability;
import uk.org.rbc1b.roms.db.project.ProjectAvailabilityDao;
import freemarker.template.TemplateException;

/**
 * Preview system emails.
 */
@Controller
@RequestMapping("/email-preview")
public class EmailPreviewController {
    @Autowired
    private ProjectAvailabilityDao projectAvailabilityDao;
    @Autowired
    private ProjectAvailabilityEmailGenerator projectAvailabilityEmailGenerator;

    /**
     * Preview the project availability request email we send to the volunteer.
     * @param projectAvailabilityId id
     * @param model model
     * @return jsp path
     * @throws NoSuchRequestHandlingMethodException on failure to find the project availability id
     */
    @RequestMapping(value = "project-availability/{projectAvailabilityId}/request", method = RequestMethod.GET)
    @PreAuthorize("hasPermission('DATABASE','READ')")
    public String previewVolunteerRequestEmail(@PathVariable Integer projectAvailabilityId, ModelMap model)
            throws NoSuchRequestHandlingMethodException {
        ProjectAvailability projectAvailability = projectAvailabilityDao.findById(projectAvailabilityId);
        if (projectAvailability == null) {
            throw new NoSuchRequestHandlingMethodException("No ProjectAvailability#" + projectAvailabilityId,
                    this.getClass());
        }
        Email email;
        try {
            email = projectAvailabilityEmailGenerator.generateVolunteerAvailabilityRequestEmail(projectAvailability);
        } catch (IOException | TemplateException e) {
            throw new IllegalStateException("Failed to generate the email", e);
        }

        if (email == null) {
            throw new BadRequestException("No email available");
        }

        model.addAttribute("email", email);

        return "emails/preview";
    }

    /**
     * Preview the project availability confirmation email we send to the volunteer.
     * @param projectAvailabilityId id
     * @param model model
     * @return jsp path
     * @throws NoSuchRequestHandlingMethodException on failure to find the project availability id
     */
    @RequestMapping(value = "project-availability/{projectAvailabilityId}/confirmation", method = RequestMethod.GET)
    @PreAuthorize("hasPermission('DATABASE','READ')")
    public String previewVolunteerConfirmationEmail(@PathVariable Integer projectAvailabilityId, ModelMap model)
            throws NoSuchRequestHandlingMethodException {
        ProjectAvailability projectAvailability = projectAvailabilityDao.findById(projectAvailabilityId);
        if (projectAvailability == null) {
            throw new NoSuchRequestHandlingMethodException("No ProjectAvailability#" + projectAvailabilityId,
                    this.getClass());
        }
        Email email;
        try {
            email = projectAvailabilityEmailGenerator
                    .generateVolunteerAvailabilityConfirmationEmail(projectAvailability);
        } catch (IOException | TemplateException e) {
            throw new IllegalStateException("Failed to generate the email", e);
        }

        if (email == null) {
            throw new BadRequestException("No email available");
        }

        model.addAttribute("email", email);

        return "emails/preview";
    }

}
