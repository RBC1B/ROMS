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
package uk.org.rbc1b.roms.controller.project;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.org.rbc1b.roms.db.Person;
import uk.org.rbc1b.roms.db.PersonDao;
import uk.org.rbc1b.roms.db.project.ProjectAttendance;
import uk.org.rbc1b.roms.db.project.ProjectAvailability;
import uk.org.rbc1b.roms.db.project.ProjectDepartmentSession;
import uk.org.rbc1b.roms.db.project.ProjectDepartmentSessionDao;
import uk.org.rbc1b.roms.db.volunteer.department.Department;
import uk.org.rbc1b.roms.db.volunteer.department.DepartmentDao;

/**
 * Generates the list of models for gate lists.
 */
@Component
public class ProjectGateListModelFactory {

    @Autowired
    private PersonDao personDao;
    @Autowired
    private ProjectDepartmentSessionDao projectDepartmentSessionDao;
    @Autowired
    private DepartmentDao departmentDao;

    /**
     * Generates a list of gate list row for a list of attendances.
     *
     * @param attendances the list of attendance for a date
     * @return list
     */
    public List<ProjectGateListModel> generateModels(List<ProjectAttendance> attendances) {
        List<ProjectGateListModel> models = new ArrayList<>();

        for (ProjectAttendance attendance : attendances) {
            ProjectAvailability availability = attendance.getProjectAvailability();
            Person person = personDao.findPerson(availability.getPerson().getPersonId());
            ProjectDepartmentSession session = projectDepartmentSessionDao
                    .findByProjectDepartmentSessionId(availability.getProjectDepartmentSession().getProjectDepartmentSessionId());
            Department department = departmentDao.findDepartment(session.getDepartment().getDepartmentId());
            ProjectGateListModel model = new ProjectGateListModel();
            model.setPersonId(person.getPersonId());
            model.setForename(person.getForename());
            model.setSurname(person.getSurname());
            model.setDepartment(department.getName());
            models.add(model);
        }
        return models;
    }
}
