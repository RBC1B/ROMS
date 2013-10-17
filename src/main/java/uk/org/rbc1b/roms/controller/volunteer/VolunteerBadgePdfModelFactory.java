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

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.org.rbc1b.roms.db.volunteer.Volunteer;
import uk.org.rbc1b.roms.db.volunteer.VolunteerDao;
import uk.org.rbc1b.roms.db.volunteer.department.Assignment;
import uk.org.rbc1b.roms.db.volunteer.department.Department;
import uk.org.rbc1b.roms.db.volunteer.department.DepartmentDao;
import uk.org.rbc1b.roms.db.volunteer.skill.Skill;
import uk.org.rbc1b.roms.db.volunteer.skill.SkillCategory;
import uk.org.rbc1b.roms.db.volunteer.skill.SkillDao;
import uk.org.rbc1b.roms.db.volunteer.skill.VolunteerSkill;

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
    private VolunteerDao volunteerDao;
    private DepartmentDao departmentDao;
    private SkillDao skillDao;

    /**
     * Generate the badge uri for a volunteer.
     *
     * @param volunteerId id of the volunteer
     * @return String uri
     */
    public static String generateUri(Integer volunteerId) {
        return volunteerId != null ? BASE_URI + volunteerId + MIDDLE_URI + volunteerId
                + END_URI : BASE_URI + volunteerId;
    }

    /**
     * Generate a Set of skills for a volunteer to appear on the badge.
     *
     * @param volunteer volunteer
     * @return Set
     */
    public Set<String> generateSkillsSet(Volunteer volunteer) {
        Set<Skill> skills = findVolunteerSkills(volunteer.getPersonId());
        Set<String> skillsSet = new HashSet<String>();
        if (!skills.isEmpty()) {
            for (Skill skill : skills) {
                if (skills.size() < 8) {
                    SkillCategory skillCategory = skillDao.findSkillCategory(skill.getCategory().getSkillCategoryId());
                    if (skillCategory.isAppearOnBadge()) {
                        skillsSet.add(skill.getName());
                    }
                }
            }
        }
        return skillsSet;
    }

    /**
     * Generate the colour band of the badge depending on the volunteer's
     * skills.
     *
     * @param volunteer volunteer
     * @return String colour
     */
    public String generateColourBand(Volunteer volunteer) {
        Set<Skill> skills = findVolunteerSkills(volunteer.getPersonId());
        String otherColour = null;
        if (!skills.isEmpty()) {
            for (Skill skill : skills) {
                SkillCategory skillCategory = skillDao.findSkillCategory(skill.getCategory().getSkillCategoryId());
                if (skillCategory.getColour().equals("RED")) {
                    return "RED";
                } else if (skillCategory.getColour().equals("GREEN")) {
                    otherColour = "GREEN";
                }
            }
        }
        return otherColour;
    }

    /**
     * Find the primary assignment of a volunteer. This appears on the badge.
     *
     * @param volunteer volunteer
     * @return String
     */
    public String generatePrimaryAssignment(Volunteer volunteer) {
        Assignment primaryAssignment = volunteerDao.findPrimaryAssignment(volunteer.getPersonId());
        Department department = departmentDao.findDepartment(primaryAssignment.getDepartmentId());
        return department.getName();
    }

    /**
     * Helper method to return a set of skills.
     *
     * @param personId volunteer id
     * @return set of skills
     */
    private Set<Skill> findVolunteerSkills(Integer personId) {
        Set<Skill> skills = new HashSet<Skill>();
        List<VolunteerSkill> volunteerSkills = volunteerDao.findSkills(personId);
        if (!volunteerSkills.isEmpty()) {
            for (VolunteerSkill volunteerSkill : volunteerSkills) {
                Skill skill = skillDao.findSkill(volunteerSkill.getSkillId());
                skills.add(skill);
            }
        }
        return skills;
    }

    @Autowired
    public void setVolunteerDao(VolunteerDao volunteerDao) {
        this.volunteerDao = volunteerDao;
    }

    @Autowired
    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Autowired
    public void setSkillDao(SkillDao skillDao) {
        this.skillDao = skillDao;
    }
}
