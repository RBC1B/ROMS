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
package uk.org.rbc1b.roms.db.volunteer.interview;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import uk.org.rbc1b.roms.db.volunteer.Volunteer;

/**
 * Access the volunteer interview session information.
 */
public interface InterviewSessionDao {

    /**
     * Get all interview sessions.
     * @return List of sessions
     */
    @PreAuthorize("hasPermission('VOLUNTEER', 'READ')")
    @Transactional(readOnly = true)
    List<InterviewSession> findInterviewSessions();

    /**
     * Get a single interview sessions.
     * @param interviewSessionId primary key
     * @return interview session
     */
    @PreAuthorize("hasPermission('VOLUNTEER', 'READ')")
    @Transactional(readOnly = true)
    InterviewSession findInterviewSession(Integer interviewSessionId);

    /**
     * Update an interview session.
     * @param interviewSession session
     */
    @PreAuthorize("hasPermission('VOLUNTEER', 'ADD')")
    @Transactional
    void createInterviewSession(InterviewSession interviewSession);

    /**
     * Update an interview session.
     * @param interviewSession session
     */
    @PreAuthorize("hasPermission('VOLUNTEER', 'EDIT')")
    @Transactional
    void updateInterviewSession(InterviewSession interviewSession);

    /**
     * Find the number of volunteers for each given status for each session.
     * @return map of interview status volunteer counts, keyed against the session id
     */
    @PreAuthorize("hasPermission('VOLUNTEER', 'READ')")
    @Transactional(readOnly = true)
    Map<Integer, Map<String, Integer>> findInterviewSessionVolunteerCounts();

    /**
     * Find the number of volunteers for each given status for a session.
     * @param interviewSessionId session id
     * @return map of interview status volunteer counts
     */
    @PreAuthorize("hasPermission('VOLUNTEER', 'READ')")
    @Transactional(readOnly = true)
    Map<String, Integer> findInterviewSessionVolunteerCounts(Integer interviewSessionId);

    /**
     * Find all the volunteers linked to the interview session.
     * @param interviewSessionId session id
     * @return list of {@code VolunteerInterviewSession} instances
     */
    @PreAuthorize("hasPermission('VOLUNTEER', 'READ')")
    @Transactional(readOnly = true)
    List<VolunteerInterviewSession> findVolunteerInterviewSessions(Integer interviewSessionId);

    /**
     * Find all the interview sessions for a given volunteer.
     * @param volunteerId volunteer id
     * @return list of {@code VolunteerInterviewSession} instances
     */
    @PreAuthorize("hasPermission('VOLUNTEER', 'READ')")
    @Transactional(readOnly = true)
    List<VolunteerInterviewSession> findVolunteerInterviewSessionsByVolunteer(Integer volunteerId);

    /**
     * Find an individual volunteer interview.
     * @param volunteerInterviewSessionId volunteer interview id
     * @return {@code VolunteerInterviewSession} instance
     */
    @PreAuthorize("hasPermission('VOLUNTEER', 'READ')")
    @Transactional(readOnly = true)
    VolunteerInterviewSession findVolunteerInterviewSession(Integer volunteerInterviewSessionId);

    /**
     * Invite volunteers to an interview.
     * @param volunteerIds volunteer ids
     * @param interviewSessionId session
     */
    @PreAuthorize("hasPermission('VOLUNTEER', 'EDIT')")
    @Transactional
    void addVolunteerInterviewSessions(Set<Integer> volunteerIds, Integer interviewSessionId);

    /**
     * Update an existing interview session.
     * @param volunteerInterviewSession volunteer interview session
     */
    @PreAuthorize("hasPermission('VOLUNTEER', 'EDIT')")
    @Transactional
    void updateVolunteerInterviewSession(VolunteerInterviewSession volunteerInterviewSession);

    /**
     * Find the list of all volunteers who are eligible for an interview session.
     * To be elligible they have to be
     * <ul>
     *      <li>Pending or active</li>
     *      <li>Not completed, confirmed or invited to another session</li>
     * </ul>
     * @return list of volunteers
     */
    @PreAuthorize("hasPermission('VOLUNTEER', 'READ')")
    @Transactional(readOnly = true)
    List<Volunteer> findInterviewSessionEligibleVolunteers();

}
