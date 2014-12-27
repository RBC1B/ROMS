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
package uk.org.rbc1b.roms.db.volunteer;

import java.util.List;
import java.util.Set;
import org.springframework.transaction.annotation.Transactional;
import uk.org.rbc1b.roms.db.volunteer.department.Assignment;
import uk.org.rbc1b.roms.db.volunteer.qualification.VolunteerQualification;
import uk.org.rbc1b.roms.db.volunteer.skill.VolunteerSkill;
import uk.org.rbc1b.roms.db.volunteer.trade.VolunteerTrade;
import uk.org.rbc1b.roms.db.volunteer.trade.VolunteerTradeSearchCriteria;

/**
 * Look up volunteer information.
 */
public interface VolunteerDao {
    /**
     * Find the volunteer with matching id, or null with no match.
     *
     * @param volunteerId id
     * @param data additional data to populate
     * @return volunteer
     */
    @Transactional(readOnly = true)
    Volunteer findVolunteer(Integer volunteerId, Set<VolunteerData> data);

    /**
     * Find all matching volunteers.
     *
     * @param searchCriteria search criteria
     * @return list of matching volunteers
     */
    @Transactional(readOnly = true)
    List<Volunteer> findVolunteers(VolunteerSearchCriteria searchCriteria);

    /**
     * Look up the number of volunteers matching the criteria.
     *
     * @param searchCriteria search criteria
     * @return list of people
     */
    @Transactional(readOnly = true)
    int findVolunteersCount(VolunteerSearchCriteria searchCriteria);

    /**
     * Save a volunteer.
     *
     * @param volunteer volunteer to
     */
    @Transactional
    void createVolunteer(Volunteer volunteer);

    /**
     * Update a volunteer.
     *
     * @param volunteer volunteer to
     */
    @Transactional
    void updateVolunteer(Volunteer volunteer);

    /**
     * Find the volunteer assignments.
     *
     * @param volunteerId id
     * @return list of assignments
     */
    @Transactional(readOnly = true)
    List<Assignment> findAssignments(Integer volunteerId);

    /**
     * Find the first or primary assignment of a volunteer.
     *
     * @param volunteerId id
     * @return assignment
     */
    @Transactional(readOnly = true)
    Assignment findPrimaryAssignment(Integer volunteerId);

    /**
     * Find the volunteer skills.
     *
     * @param volunteerId id
     * @return list of skills
     */
    @Transactional(readOnly = true)
    List<VolunteerSkill> findSkills(Integer volunteerId);

    /**
     * Find the volunteer skill.
     *
     * @param volunteerSkillId primary key
     * @return volunteer skill, or null if no match
     */
    @Transactional(readOnly = true)
    VolunteerSkill findSkill(Integer volunteerSkillId);

    /**
     * Delete a volunteer skill.
     *
     * @param volunteerSkill linked skill
     */
    @Transactional
    void deleteSkill(VolunteerSkill volunteerSkill);

    /**
     * Update a volunteer skill.
     *
     * @param volunteerSkill linked skill
     */
    @Transactional
    void updateSkill(VolunteerSkill volunteerSkill);

    /**
     * Update a volunteer skill.
     *
     * @param volunteerSkill linked skill
     */
    @Transactional
    void createSkill(VolunteerSkill volunteerSkill);

    /**
     * Find the volunteer qualifications.
     *
     * @param volunteerId id
     * @return list of qualifications
     */
    @Transactional(readOnly = true)
    List<VolunteerQualification> findQualifications(Integer volunteerId);

    /**
     * Find the volunteer qualification.
     *
     * @param volunteerQualificationId id
     * @return single qualification
     */
    @Transactional(readOnly = true)
    VolunteerQualification findQualification(Integer volunteerQualificationId);

    /**
     * Look up the list of volunteer trades matching the criteria. All criteria
     * are optional.
     *
     * @param searchCriteria search criteria
     * @return list of people
     */
    @Transactional(readOnly = true)
    List<VolunteerTrade> findVolunteerTrades(VolunteerTradeSearchCriteria searchCriteria);

    /**
     * Look up the number of volunteer trades matching the criteria.
     *
     * @param searchCriteria search criteria
     * @return list of people
     */
    @Transactional(readOnly = true)
    int findVolunteerTradesCount(VolunteerTradeSearchCriteria searchCriteria);

    /**
     * Delete a volunteer linked trade.
     *
     * @param volunteerTrade volunteer trade
     */
    @Transactional
    void deleteVolunteerTrade(VolunteerTrade volunteerTrade);

    /**
     * Saves a volunteer trade/experience.
     *
     * @param volunteerTrade a trade to save
     */
    @Transactional
    void createTrade(VolunteerTrade volunteerTrade);

    /**
     * Updates a volunteer trade/experience.
     *
     * @param volunteerTrade the trade experience to save
     */
    @Transactional
    void updateTrade(VolunteerTrade volunteerTrade);

    /**
     * Finds a volunteer trade/experience.
     *
     * @param volunteerTradeId the volunteer trade id
     * @return volunteerTrade the trade
     */
    @Transactional
    VolunteerTrade findTrade(Integer volunteerTradeId);

    /**
     * Update an existing volunteer qualification.
     *
     * @param volunteerQualification a volunteer qualification to save
     */
    @Transactional
    void updateVolunteerQualification(VolunteerQualification volunteerQualification);

    /**
     * Update an existing volunteer qualification.
     *
     * @param volunteerQualification a volunteer qualification to save
     */
    @Transactional
    void deleteVolunteerQualification(VolunteerQualification volunteerQualification);

    /**
     * Update a volunteer skill.
     *
     * @param volunteerQualification qualification
     */
    @Transactional
    void createQualification(VolunteerQualification volunteerQualification);

    /**
     * Additional data to pull in when generating the volunteer details.
     */
    public static enum VolunteerData {

        SPOUSE, EMERGENCY_CONTACT, TRADES, INTERVIEWER;
    }
}
