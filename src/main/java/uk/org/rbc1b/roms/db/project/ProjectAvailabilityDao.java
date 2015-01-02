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
package uk.org.rbc1b.roms.db.project;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dao for project availability.
 *
 * @author ramindursingh
 */
public interface ProjectAvailabilityDao {

    /**
     * Get list of ones who have not been sent email.
     *
     * @return list
     */
    @Transactional(readOnly = true)
    List<ProjectAvailability> findUnnotifiedVolunteers();

    /**
     * Update volunteers who have been sent an email.
     *
     * @param projectAvailability the row
     */
    @Transactional(readOnly = false)
    void update(ProjectAvailability projectAvailability);

    /**
     * Gets the list of volunteers who have been selected for dates to attend
     * who have not been notified.
     *
     * @return list
     */
    @Transactional(readOnly = true)
    List<ProjectAvailability> findUnconfirmedVolunteers();

    /**
     * Finds the availability by id.
     *
     * @param id the id to find
     * @return project availability
     */
    @Transactional(readOnly = true)
    ProjectAvailability findById(Integer id);

    /**
     * Finds the availability for a given volunteer and work session.
     *
     * @param personId the person
     * @param workSession the work session
     * @return the availability
     */
    @Transactional(readOnly = true)
    ProjectAvailability findVolunteerAvailabilityByWorkSession(Integer personId, ProjectDepartmentSession workSession);

    /**
     * Finds all availabilities for a work session.
     *
     * @param projectDepartmentSessionId the session id
     * @return list of availabilities
     */
    @Transactional(readOnly = true)
    List<ProjectAvailability> findForDepartmentSession(Integer projectDepartmentSessionId);

    /**
     * Finds the availability by person and work session id.
     *
     * @param personId the person id
     * @param projectDepartmentSessionId the work session id
     * @return the availability
     */
    @Transactional(readOnly = true)
    ProjectAvailability find(Integer personId, Integer projectDepartmentSessionId);

    /**
     * Deletes the availability from the db.
     *
     * @param projectAvailability the availability to delete
     */
    @Transactional(readOnly = false)
    void delete(ProjectAvailability projectAvailability);

    /**
     * Saves/inserts into the database.
     *
     * @param projectAvailability the availability to save
     */
    @Transactional(readOnly = false)
    void save(ProjectAvailability projectAvailability);
}
