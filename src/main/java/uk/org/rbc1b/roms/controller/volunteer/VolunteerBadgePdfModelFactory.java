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
package uk.org.rbc1b.roms.controller.volunteer;

import java.util.List;
import org.joda.time.LocalDate;
import org.joda.time.Years;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.org.rbc1b.roms.db.volunteer.Volunteer;
import uk.org.rbc1b.roms.db.volunteer.VolunteerDao;
import uk.org.rbc1b.roms.db.volunteer.department.Assignment;
import uk.org.rbc1b.roms.db.volunteer.department.Department;
import uk.org.rbc1b.roms.db.volunteer.department.DepartmentDao;

/**
 * Generate the Volunteer Badge PDF model.
 *
 * @author rahulsingh
 */
@Component
public class VolunteerBadgePdfModelFactory {

    private static final String BASE_URI = "/volunteers/";
    private static final String MIDDLE_URI = "/rbc-";
    private static final String END_URI = "-badge.pdf";
    @Autowired
    private VolunteerDao volunteerDao;
    @Autowired
    private DepartmentDao departmentDao;

    /**
     * Generate the badge uri for a volunteer.
     *
     * @param volunteerId id of the volunteer
     * @return String uri
     */
    public static String generateUri(Integer volunteerId) {
        return volunteerId != null ? BASE_URI + volunteerId + MIDDLE_URI + volunteerId + END_URI : BASE_URI
                + volunteerId;
    }

    /**
     * Determines the colour of the badge depending on the volunteer details
     * such as their age and departmental assignment.
     *
     * @param volunteer volunteer
     * @return VolunteerBadgeColour the colour
     */
    public VolunteerBadgeColour determineBadgeColour(Volunteer volunteer) {
        LocalDate birthDate = LocalDate.fromDateFields(volunteer.getPerson().getBirthDate());
        LocalDate now = new LocalDate();
        Years age = Years.yearsBetween(birthDate, now);

        // volunteer badge colour depends on age or assignment
        if (age.getYears() >= 13 && age.getYears() <= 15) {
            return VolunteerBadgeColour.GREEN;
        } else if (age.getYears() >= 16 && age.getYears() <= 17) {
            return VolunteerBadgeColour.ORANGE;
        } else if (isVolunteerAssignmentDangerous(volunteer)) {
            return VolunteerBadgeColour.RED;
        } else {
            return VolunteerBadgeColour.GREY;
        }
    }

    /**
     * Find the primary assignment of a volunteer. This appears on the badge as
     * the volunteer's departmental assignment.
     *
     * @param volunteer volunteer
     * @return String
     */
    public String generatePrimaryAssignment(Volunteer volunteer) {
        List<Assignment> assignments = volunteerDao.findAssignments(volunteer.getPersonId());

        // This should be the assignment with greatest priority
        Assignment primaryAssignment = assignments.get(0);
        Department department = departmentDao.findDepartment(primaryAssignment.getDepartmentId());
        return department.getName();
    }

    /**
     * Helper method that determines whether the volunteer has an assignment
     * that is deemed as Dangerous. If this is the case, the volunteer must get
     * a Red coloured badge.
     *
     * @param volunteer the volunteer
     * @return boolean dangerous assignment or not
     */
    private boolean isVolunteerAssignmentDangerous(Volunteer volunteer) {
        List<Assignment> assignments = volunteerDao.findAssignments(volunteer.getPersonId());

        for (Assignment assignment : assignments) {
            Department department = departmentDao.findDepartment(assignment.getDepartmentId());
            String departmentName = department.getName();

            if (departmentName.equals("Roofing") || departmentName.equals("Roof Trusses")
                    || departmentName.equals("Scaffolding")) {
                return true;
            }
        }
        return false;
    }
}
