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
 *
 * @author ramindursingh
 */
public interface ProjectDepartmentSessionDao {

    /**
     * Gets the projectDepartmentSession.
     *
     * @param projectDepartmentSessionId the projectDepartmentSessionId
     * @return projectDepartmentSession
     */
    @Transactional(readOnly = true)
    ProjectDepartmentSession findByProjectDepartmentSessionId(Integer projectDepartmentSessionId);

    /**
     * Gets the list of project department sessions for a department.
     *
     * @param projectId the project Id
     * @param departmentId the department Id
     * @return list of sessions
     */
    @Transactional(readOnly = true)
    List<ProjectDepartmentSession> findProjectSessionsForDepartment(Integer projectId, Integer departmentId);

    /**
     * Finds all sessions for a project.
     *
     * @param projectId the project Id
     * @return list of sessions
     */
    @Transactional(readOnly = true)
    List<ProjectDepartmentSession> findAllProjectSessions(Integer projectId);

    /**
     * Saves a new project department work session.
     *
     * @param workSession the session to save
     */
    @Transactional(readOnly = false)
    void save(ProjectDepartmentSession workSession);
}
