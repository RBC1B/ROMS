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
package uk.org.rbc1b.roms.controller.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.org.rbc1b.roms.controller.common.model.EntityModel;
import uk.org.rbc1b.roms.controller.common.model.PersonModelFactory;
import uk.org.rbc1b.roms.db.Person;
import uk.org.rbc1b.roms.db.PersonDao;
import uk.org.rbc1b.roms.db.volunteer.Assignment;
import uk.org.rbc1b.roms.db.volunteer.Department;

/**
 * Generate models for the departments.
 * @author oliver.elder.esq
 */
@Component
public class DepartmentModelFactory {
    private static final String BASE_URI = "/departments";
    private PersonDao personDao;
    private PersonModelFactory personModelFactory;

    /**
     * Generate the uri used to access the department pages.
     * @param departmentId optional department id
     * @return uri
     */
    public String generateUri(Integer departmentId) {
        return departmentId != null ? BASE_URI + "/" + departmentId : BASE_URI;
    }

    /**
     * Generate the model to be used in the department list.
     * @param department department
     * @param overseerAssignment details of assigned overseer
     * @return model
     */
    public DepartmentListModel generateDepartmentListModel(Department department, Assignment overseerAssignment) {
        DepartmentListModel model = new DepartmentListModel();
        model.setName(department.getName());

        if (department.getSuperDepartment() != null) {
            EntityModel superDepartment = new EntityModel();
            superDepartment.setId(department.getSuperDepartment().getDepartmentId());
            superDepartment.setName(department.getSuperDepartment().getName());
            superDepartment.setUri(generateUri(department.getSuperDepartment().getDepartmentId()));
            model.setSuperDepartment(superDepartment);
        }

        if (overseerAssignment != null) {
            Person person = personDao.findPerson(overseerAssignment.getPersonId());
            model.setOverseer(personModelFactory.generatePersonModel(person));
        }

        model.setUri(generateUri(department.getDepartmentId()));
        model.setEditUri(generateUri(department.getDepartmentId()) + "/edit");

        return model;
    }

    /**
     * Generate the department overview model.
     * @param department department
     * @param overseerAssignment details of assigned overseer
     * @return model
     */
    public DepartmentModel generateDepartmentModel(Department department, Assignment overseerAssignment) {
        DepartmentModel model = new DepartmentModel();
        model.setDescription(department.getDescription());
        model.setEditUri(generateUri(department.getDepartmentId()) + "/edit");
        model.setName(department.getName());

        if (overseerAssignment != null) {
            Person person = personDao.findPerson(overseerAssignment.getPersonId());
            model.setOverseer(personModelFactory.generatePersonModel(person));
        }

        if (department.getSuperDepartment() != null) {
            EntityModel superDepartment = new EntityModel();
            superDepartment.setId(department.getSuperDepartment().getDepartmentId());
            superDepartment.setName(department.getSuperDepartment().getName());
            superDepartment.setUri(generateUri(department.getSuperDepartment().getDepartmentId()));
            model.setSuperDepartment(superDepartment);
        }

        return model;
    }

    @Autowired
    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Autowired
    public void setPersonModelFactory(PersonModelFactory personModelFactory) {
        this.personModelFactory = personModelFactory;
    }

}
