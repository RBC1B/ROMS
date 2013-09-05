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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uk.org.rbc1b.roms.db.PersonSearchCriteria;
import uk.org.rbc1b.roms.db.volunteer.Assignment;
import uk.org.rbc1b.roms.db.volunteer.AssignmentSearchCriteria;
import uk.org.rbc1b.roms.db.volunteer.Department;
import uk.org.rbc1b.roms.db.volunteer.DepartmentDao;

/**
 * Control the display and editing of departments.
 */
@Controller
@RequestMapping("/departments")
public class DepartmentsController {
    private static final int OVERSEER_ROLE_ID = 5;
    private DepartmentDao departmentDao;
    private DepartmentModelFactory departmentModelFactory;

    /**
     * Display the list of persons.
     * @param model mvc model
     * @param searchCriteria search criteria passed in the form
     * @return view
     */
    @RequestMapping(method = RequestMethod.GET, headers = "Accept=text/html")
    public String showDepartmentList(ModelMap model, PersonSearchCriteria searchCriteria) {

        List<Department> departments = departmentDao.findDepartments();
        Map<Integer, Assignment> departmentOverseers = fetchDepartmentOverseers();
        List<DepartmentListModel> modelList = new ArrayList<DepartmentListModel>(departments.size());
        for (Department department : departments) {
            modelList.add(departmentModelFactory.generateDepartmentListModel(department,
                    departmentOverseers.get(department.getDepartmentId())));
        }

        model.addAttribute("departments", modelList);

        return "departments/list";
    }

    private Map<Integer, Assignment> fetchDepartmentOverseers() {
        AssignmentSearchCriteria assignmentSearchCriteria = new AssignmentSearchCriteria();
        assignmentSearchCriteria.setRoleId(OVERSEER_ROLE_ID);

        List<Assignment> assignments = departmentDao.findAssignments(assignmentSearchCriteria);

        Map<Integer, Assignment> overseerMap = new HashMap<Integer, Assignment>(assignments.size());
        for (Assignment assignment : assignments) {
            overseerMap.put(assignment.getDepartmentId(), assignment);
        }
        return overseerMap;
    }

    @Autowired
    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Autowired
    public void setDepartmentModelFactory(DepartmentModelFactory departmentModelFactory) {
        this.departmentModelFactory = departmentModelFactory;
    }

}
