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
import java.sql.Date;

/**
 * DAO for ProjectAttendance.
 *
 * @author ramindursingh
 */
public interface ProjectAttendanceDao {

    /**
     * Finds the project attendance by id.
     *
     * @param projectAttendanceId the id to find
     * @return projectAttendance row data
     */
    @Transactional(readOnly = true)
    ProjectAttendance find(Integer projectAttendanceId);

    /**
     * Finds all volunteers for a project and specific date, confirmed as
     * invited.
     *
     * @param projectId the project Id
     * @param date the date
     * @return list of projectAttendance
     */
    @Transactional(readOnly = true)
    List<ProjectAttendance> findConfirmedVolunteersForProjectByDate(Integer projectId, Date date);

    /**
     * Finds all volunteers who are available for a project on a specific date,
     * invited or otherwise.
     *
     * @param projectId the project Id
     * @param date the date
     * @return list of projectAttendance
     */
    @Transactional(readOnly = true)
    List<ProjectAttendance> findAllAvailableVolunteersForProjectByDate(Integer projectId, Date date);

    /**
     * Update volunteers attendance.
     *
     * @param projectAttendance the row
     */
    @Transactional(readOnly = false)
    void updateProjectAttendance(ProjectAttendance projectAttendance);

    /**
     * Gets a list of confirmed dates for a volunteer to attend.
     *
     * @param projectAvailability the availability
     * @return list of dates
     */
    @Transactional(readOnly = true)
    List<ProjectAttendance> getConfirmedDates(ProjectAvailability projectAvailability);

    /**
     * Gets a list of dates for which the volunteer is available.
     *
     * @param projectAvailability the availability
     * @return list of dates from attendance
     */
    @Transactional(readOnly = false)
    List<ProjectAttendance> getDatesForVolunteer(ProjectAvailability projectAvailability);

    /**
     * Gets an attendance date by availability id and date.
     *
     * @param projectAvailability the availability
     * @param date the date
     * @return ProjectAttendance matching attendance
     */
    @Transactional(readOnly = true)
    ProjectAttendance getAvailableDate(ProjectAvailability projectAvailability, Date date);

    /**
     * Inserts a new attendance record into the database.
     *
     * @param projectAttendance the attendance object
     */
    @Transactional(readOnly = false)
    void save(ProjectAttendance projectAttendance);

    /**
     * Deletes the attendance record from the database.
     *
     * @param projectAttendance the attendance to delete
     */
    @Transactional(readOnly = false)
    void delete(ProjectAttendance projectAttendance);

    /**
     * Updates an existing attendance record.
     *
     * @param projectAttendance the row to update
     */
    @Transactional(readOnly = false)
    void update(ProjectAttendance projectAttendance);
}
