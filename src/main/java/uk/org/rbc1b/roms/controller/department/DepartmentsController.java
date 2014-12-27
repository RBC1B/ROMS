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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import uk.org.rbc1b.roms.controller.ResourceNotFoundException;
import uk.org.rbc1b.roms.controller.common.datatable.AjaxDataTableRequestData;
import uk.org.rbc1b.roms.controller.common.datatable.AjaxDataTableResult;
import uk.org.rbc1b.roms.controller.skill.SkillModel;
import uk.org.rbc1b.roms.controller.skill.SkillModelFactory;
import uk.org.rbc1b.roms.controller.volunteer.AssignmentModel;
import uk.org.rbc1b.roms.controller.volunteer.AssignmentModelFactory;
import uk.org.rbc1b.roms.db.volunteer.department.Assignment;
import uk.org.rbc1b.roms.db.volunteer.department.AssignmentSearchCriteria;
import uk.org.rbc1b.roms.db.volunteer.department.Department;
import uk.org.rbc1b.roms.db.volunteer.department.DepartmentDao;
import uk.org.rbc1b.roms.db.volunteer.skill.Skill;
import uk.org.rbc1b.roms.db.volunteer.skill.SkillDao;
import uk.org.rbc1b.roms.db.volunteer.skill.SkillSearchCriteria;

/**
 * Control the display and editing of departments.
 */
@Controller
@RequestMapping("/departments")
public class DepartmentsController {
    private static final String OVERSEER_ROLE_CODE = "OV";
    private static final String ASSISTANT_ROLE_CODE = "AT";
    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private DepartmentModelFactory departmentModelFactory;
    @Autowired
    private AssignmentModelFactory assignmentModelFactory;
    @Autowired
    private SkillModelFactory skillModelFactory;
    @Autowired
    private SkillDao skillDao;

    /**
     * Display the list of persons.
     * @param model mvc model
     * @return view
     */
    @RequestMapping(method = RequestMethod.GET, headers = "Accept=text/html")
    @PreAuthorize("hasPermission('VOLUNTEER','READ')")
    public String showDepartmentList(ModelMap model) {

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

    /**
     * Search for a department by name.
     *
     * @param name partial name match
     * @return list of matching departments
     */
    @RequestMapping(value = "search", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<DepartmentSearchResult> findDepartments(@RequestParam(value = "name", required = true) String name) {
        List<Department> departments = departmentDao.findDepartments(name);
        List<DepartmentSearchResult> results = new ArrayList<DepartmentSearchResult>();
        if (!departments.isEmpty()) {
            for (Department department : departments) {
                DepartmentSearchResult result = new DepartmentSearchResult();
                result.setId(department.getDepartmentId());
                result.setName(department.getName());
                results.add(result);
            }
        }
        return results;
    }

    /**
     * @param departmentId person primary key
     * @param model model
     * @return view name
     */
    @RequestMapping(value = "{departmentId}", method = RequestMethod.GET)
    @PreAuthorize("hasPermission('VOLUNTEER','READ')")
    public String showDepartment(@PathVariable Integer departmentId, ModelMap model) {
        Department department = departmentDao.findDepartment(departmentId);
        if (department == null) {
            throw new ResourceNotFoundException("No department#" + departmentId);
        }

        Assignment overseerAssignment = findDepartmentAssignment(departmentId, OVERSEER_ROLE_CODE);
        Assignment assistantAssignment = findDepartmentAssignment(departmentId, ASSISTANT_ROLE_CODE);

        model.addAttribute("department",
                departmentModelFactory.generateDepartmentModel(department, overseerAssignment, assistantAssignment));

        List<Department> childDepartments = departmentDao.findChildDepartments(departmentId);
        Map<Integer, Assignment> departmentOverseers = fetchDepartmentOverseers();
        List<DepartmentListModel> childDepartmentModelList = new ArrayList<DepartmentListModel>(childDepartments.size());
        for (Department childDepartment : childDepartments) {
            childDepartmentModelList.add(departmentModelFactory.generateDepartmentListModel(childDepartment,
                    departmentOverseers.get(childDepartment.getDepartmentId())));
        }

        model.addAttribute("childDepartments", childDepartmentModelList);

        SkillSearchCriteria searchCriteria = new SkillSearchCriteria();
        searchCriteria.setDepartmentId(departmentId);
        List<Skill> skills = skillDao.findSkills(searchCriteria);

        List<SkillModel> skillModelList = new ArrayList<SkillModel>();
        for (Skill skill : skills) {
            skillModelList.add(skillModelFactory.generateSkillModel(skill));
        }
        model.addAttribute("skills", skillModelList);

        return "departments/show";
    }

    private Assignment findDepartmentAssignment(Integer departmentId, String roleCode) {
        AssignmentSearchCriteria assignmentSearchCriteria = new AssignmentSearchCriteria();
        assignmentSearchCriteria.setRoleCode(roleCode);
        assignmentSearchCriteria.setDepartmentId(departmentId);
        assignmentSearchCriteria.setMaxResults(1);

        List<Assignment> assignments = departmentDao.findAssignments(assignmentSearchCriteria);

        return assignments.isEmpty() ? null : assignments.get(0);
    }

    private Map<Integer, Assignment> fetchDepartmentOverseers() {
        AssignmentSearchCriteria assignmentSearchCriteria = new AssignmentSearchCriteria();
        assignmentSearchCriteria.setRoleCode(OVERSEER_ROLE_CODE);
        assignmentSearchCriteria.setMaxResults(null); // no limit to the results

        List<Assignment> assignments = departmentDao.findAssignments(assignmentSearchCriteria);

        Map<Integer, Assignment> overseerMap = new HashMap<Integer, Assignment>(assignments.size());
        for (Assignment assignment : assignments) {
            overseerMap.put(assignment.getDepartmentId(), assignment);
        }
        return overseerMap;
    }

    /**
     * Display the list of department assignments.
     * @param departmentId department id
     * @param requestData data tables request data
     * @return view
     */
    @RequestMapping(value = "{departmentId}/assignments", method = RequestMethod.GET, headers = "Accept=application/json")
    @PreAuthorize("hasPermission('VOLUNTEER','READ')")
    @ResponseBody
    public AjaxDataTableResult<AssignmentModel> showDatatableAjaxAssignmentList(@PathVariable Integer departmentId,
            AjaxDataTableRequestData requestData) {
        AssignmentSearchCriteria searchCriteria = new AssignmentSearchCriteria();
        requestData.populateSearchCriteria(searchCriteria);
        searchCriteria.setDepartmentId(departmentId);

        AjaxDataTableResult<AssignmentModel> result = new AjaxDataTableResult<AssignmentModel>();
        result.setEcho(requestData.getEcho());

        int totalFilteredResults = departmentDao.findAssignmentsCount(searchCriteria);
        if (searchCriteria.isFiltered()) {
            AssignmentSearchCriteria noFilterCriteria = searchCriteria.clone();
            noFilterCriteria.clearFilter();
            result.setTotalRecords(departmentDao.findAssignmentsCount(searchCriteria));
        } else {
            result.setTotalRecords(totalFilteredResults);
        }

        if (totalFilteredResults > 0) {
            List<Assignment> assignments = departmentDao.findAssignments(searchCriteria);
            List<AssignmentModel> modelList = new ArrayList<AssignmentModel>(assignments.size());
            for (Assignment assignment : assignments) {
                modelList.add(assignmentModelFactory.generateAssignmentModel(assignment));
            }
            result.setRecords(modelList);
            result.setTotalDisplayRecords(totalFilteredResults);
        } else {
            result.setRecords(Collections.<AssignmentModel>emptyList());
        }

        return result;

    }

}
