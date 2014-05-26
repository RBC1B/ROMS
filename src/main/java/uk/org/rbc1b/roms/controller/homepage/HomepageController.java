/*
 * The MIT License
 *
 * Copyright 2013 RBC1B.
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
package uk.org.rbc1b.roms.controller.homepage;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uk.org.rbc1b.roms.controller.volunteer.AssignmentModel;
import uk.org.rbc1b.roms.controller.volunteer.AssignmentModelFactory;
import uk.org.rbc1b.roms.controller.volunteer.VolunteerModelFactory;
import uk.org.rbc1b.roms.db.volunteer.Volunteer;
import uk.org.rbc1b.roms.db.volunteer.VolunteerDao;
import uk.org.rbc1b.roms.db.volunteer.VolunteerDao.VolunteerData;
import uk.org.rbc1b.roms.db.volunteer.department.Assignment;
import uk.org.rbc1b.roms.security.ROMSUserDetails;

/**
 * Application home page.
 */
@Controller
@RequestMapping("/")
public class HomepageController {

    @Autowired
    private VolunteerDao volunteerDao;

    @Autowired
    private VolunteerModelFactory volunteerModelFactory;

    @Autowired
    private AssignmentModelFactory assignmentModelFactory;

    /**
     * Display the home page.
     * @param model page model to populate
     * @return view name
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showHomepage(ModelMap model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ROMSUserDetails user = (ROMSUserDetails) authentication.getPrincipal();

        // we always expect the user to be linked to a volunteer
        Volunteer volunteer = volunteerDao.findVolunteer(user.getUserId(), EnumSet.of(VolunteerData.SPOUSE,
                VolunteerData.EMERGENCY_CONTACT, VolunteerData.TRADES, VolunteerData.INTERVIEWER));
        if (volunteer != null) {
            model.addAttribute("volunteer", volunteerModelFactory.generateVolunteerModel(volunteer));
            List<Assignment> assignments = volunteerDao.findAssignments(user.getUserId());
            model.addAttribute("assignments", generateAssignments(assignments));
        }

        return "homepage";
    }

    /**
     * Generate the models for the volunteer assignments.
     *
     * @param assignments assignments
     * @return model list
     */
    private List<AssignmentModel> generateAssignments(List<Assignment> assignments) {
        if (CollectionUtils.isEmpty(assignments)) {
            return null;
        }

        List<AssignmentModel> modelList = new ArrayList<AssignmentModel>(assignments.size());
        for (Assignment assignment : assignments) {
            modelList.add(assignmentModelFactory.generateAssignmentModel(assignment));
        }

        return modelList;
    }
}
