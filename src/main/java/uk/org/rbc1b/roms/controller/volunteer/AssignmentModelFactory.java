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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.org.rbc1b.roms.controller.common.model.EntityModel;
import uk.org.rbc1b.roms.controller.common.model.PersonModelFactory;
import uk.org.rbc1b.roms.db.PersonDao;
import uk.org.rbc1b.roms.db.reference.ReferenceDao;
import uk.org.rbc1b.roms.db.volunteer.department.Assignment;
import uk.org.rbc1b.roms.db.volunteer.department.Department;
import uk.org.rbc1b.roms.db.volunteer.department.DepartmentDao;
import uk.org.rbc1b.roms.db.volunteer.department.Team;

/**
 * Factory class to create the assignment model.
 * @author oliver.elder.esq
 */
@Component
public class AssignmentModelFactory {

    private static final String BASE_TEAMS_URI = "/teams/";
    private static final String BASE_DEPARTMENTS_URI = "/departments/";
    @Autowired
    private ReferenceDao referenceDao;
    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private PersonModelFactory personModelFactory;
    @Autowired
    private PersonDao personDao;

    /**
     * Generate the uri used to access the team pages.
     * @param teamId optional team id
     * @return uri
     */
    public String generateTeamUri(Integer teamId) {
        return teamId != null ? BASE_TEAMS_URI + teamId : BASE_TEAMS_URI;
    }

    /**
     * Generate the uri used to access the department pages.
     * @param departmentId optional department id
     * @return uri
     */
    public String generateDepartmentUri(Integer departmentId) {
        return departmentId != null ? BASE_DEPARTMENTS_URI + departmentId : BASE_DEPARTMENTS_URI;
    }

    /**
     * Create the assignment model.
     * @param assignment assignment
     * @return model
     */
    public AssignmentModel generateAssignmentModel(Assignment assignment) {

        AssignmentModel model = new AssignmentModel();
        model.setAssignedDate(assignment.getAssignedDate());
        model.setDepartment(createDepartmentModel(departmentDao.findDepartment(assignment.getDepartmentId())));
        model.setId(assignment.getAssignmentId());
        model.setPerson(personModelFactory.generatePersonModel(personDao.findPerson(assignment.getPerson()
                .getPersonId())));
        model.setRole(referenceDao.findAssignmentRoleValues().get(assignment.getRole().getAssignmentRoleCode()));
        if (assignment.getTeam() != null) {
            model.setTeam(createTeamModel(departmentDao.findTeam(assignment.getTeam().getTeamId())));
        }
        model.setTradeNumber(referenceDao.findTradeNumbers().get(assignment.getTradeNumberId()));
        model.setUri(VolunteerModelFactory.generateUri(assignment.getPerson().getPersonId()) + "/assignment/"
                + assignment.getAssignmentId());
        return model;
    }

    private EntityModel createDepartmentModel(Department department) {
        EntityModel model = new EntityModel();
        model.setId(department.getDepartmentId());
        model.setName(department.getName());
        model.setUri(generateDepartmentUri(department.getDepartmentId()));
        return model;
    }

    private EntityModel createTeamModel(Team team) {
        EntityModel model = new EntityModel();
        model.setId(team.getTeamId());
        model.setName(team.getName());
        model.setUri(generateTeamUri(team.getTeamId()));
        return model;
    }

}
