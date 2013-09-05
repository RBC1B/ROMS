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
import org.springframework.transaction.annotation.Transactional;

/**
 * Access the department, team and assignment information.
 * @author oliver.elder.esq
 */
public interface DepartmentDao {

    /**
     * Find the team.
     * @param teamId id
     * @return team information, or null if not found
     */
    @Transactional(readOnly = true)
    Team findTeam(Integer teamId);

    /**
     * Find the department by id.
     * @param departmentId id
     * @return department information, or null if not found
     */
    @Transactional(readOnly = true)
    Department findDepartment(Integer departmentId);

    /**
     * Find the department by name.
     * @param name department name
     * @return Department, or null if not found
     */
    @Transactional(readOnly = true)
    Department findDepartmentByName(String name);

    /**
     * Get all Departments.
     * @return List of departments
     */
    @Transactional(readOnly = true)
    List<Department> findDepartments();

    /**
     * Find the assignments based on search criteria.
     * @param searchCriteria criteria
     * @return list of matched assignments
     */
    @Transactional(readOnly = true)
    List<Assignment> findAssignments(AssignmentSearchCriteria searchCriteria);
}
