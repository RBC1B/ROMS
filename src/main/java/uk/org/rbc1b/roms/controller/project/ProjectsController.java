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

import au.com.bytecode.opencsv.CSVWriter;
import com.google.common.net.MediaType;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import uk.org.rbc1b.roms.controller.ForbiddenRequestException;
import uk.org.rbc1b.roms.controller.ResourceNotFoundException;
import uk.org.rbc1b.roms.controller.common.DataConverterUtil;
import uk.org.rbc1b.roms.db.Person;
import uk.org.rbc1b.roms.db.PersonDao;
import uk.org.rbc1b.roms.db.application.User;
import uk.org.rbc1b.roms.db.application.UserDao;
import uk.org.rbc1b.roms.db.kingdomhall.KingdomHall;
import uk.org.rbc1b.roms.db.kingdomhall.KingdomHallDao;
import uk.org.rbc1b.roms.db.project.Project;
import uk.org.rbc1b.roms.db.project.ProjectAttendance;
import uk.org.rbc1b.roms.db.project.ProjectAttendanceDao;
import uk.org.rbc1b.roms.db.project.ProjectDao;
import uk.org.rbc1b.roms.db.project.ProjectDepartmentSession;
import uk.org.rbc1b.roms.db.project.ProjectDepartmentSessionDao;
import uk.org.rbc1b.roms.db.volunteer.VolunteerDao;
import uk.org.rbc1b.roms.db.volunteer.department.Assignment;
import uk.org.rbc1b.roms.db.volunteer.department.AssignmentSearchCriteria;
import uk.org.rbc1b.roms.db.volunteer.department.Department;
import uk.org.rbc1b.roms.db.volunteer.department.DepartmentDao;
import uk.org.rbc1b.roms.security.AccessLevel;
import uk.org.rbc1b.roms.security.Application;
import uk.org.rbc1b.roms.security.ROMSGrantedAuthority;
import uk.org.rbc1b.roms.security.ROMSUserDetails;
import uk.org.rbc1b.roms.security.RomsPermissionEvaluator;

/**
 *
 * @author ramindursingh
 */
@Controller
@RequestMapping("/projects")
public class ProjectsController {

    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private KingdomHallDao kingdomHallDao;
    @Autowired
    private PersonDao personDao;
    @Autowired
    private ProjectModelFactory projectModelFactory;
    @Autowired
    private ProjectWorkSessionModelFactory workSessionModelFactory;
    @Autowired
    private VolunteerDao volunteerDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ProjectDepartmentSessionDao projectDepartmentSessionDao;
    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private ProjectAttendanceDao projectAttendanceDao;
    @Autowired
    private ProjectGateListModelFactory projectGateListModelFactory;

    /**
     * Handles the project list.
     *
     * @param model the mvc model
     * @return list jsp page
     */
    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasPermission('PROJECT', 'READ')")
    public String showProjects(ModelMap model) {
        List<Project> projects = projectDao.findProjects();

        List<ProjectModel> modelList = new ArrayList<>();
        for (Project project : projects) {
            modelList.add(projectModelFactory.generateProjectModel(project));
        }

        model.addAttribute("projects", modelList);
        model.addAttribute("newUri", ProjectModelFactory.generateUri(null) + "/new");
        return "projects/list";
    }

    /**
     * Displays the specified project.
     *
     * @param projectId the project id
     * @param model the mvc model
     * @return view name
     */
    @RequestMapping(value = "/{projectId}", method = RequestMethod.GET)
    @PreAuthorize("hasPermission('PROJECT', 'READ')")
    public String showProject(@PathVariable Integer projectId, ModelMap model) {
        Project project = projectDao.findProject(projectId);
        if (project == null) {
            throw new ResourceNotFoundException("No project #" + projectId);
        }

        // Project tab
        model.addAttribute("project", projectModelFactory.generateProjectModel(project));

        // Invitation tab
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User me = userDao.findUser(userDetails.getUsername());
        List<Assignment> assignments = volunteerDao.findAssignments(me.getPersonId());
        if (assignments == null || assignments.isEmpty()) {
            // There is nothing to show as I do not have any assignments.
            model.addAttribute("assignment", false);
        } else {
            List<ProjectDepartmentSession> workSessions = new ArrayList<>();
            List<Assignment> allDepartmentVolunteers = new ArrayList<>();
            List<Department> departments = new ArrayList<>();
            AssignmentSearchCriteria assignmentSearchCriteria = new AssignmentSearchCriteria();
            for (Assignment assignment : assignments) {
                List<ProjectDepartmentSession> sessionsForDept = projectDepartmentSessionDao
                        .findProjectSessionsForDepartment(projectId, assignment.getDepartmentId());
                if (sessionsForDept != null) {
                    workSessions.addAll(sessionsForDept);
                }
                Department department = departmentDao.findDepartment(assignment.getDepartmentId());
                departments.add(department);
            }
            List<ProjectWorkSessionModel> workSessionModels = workSessionModelFactory.generate(workSessions);

            ROMSGrantedAuthority attendanceAccess = findAttendanceAccess();
            model.addAttribute("application", attendanceAccess);
            model.addAttribute("assignment", true);
            model.addAttribute("departments", departments);
            model.addAttribute("workSessions", workSessionModels);
        }

        return "projects/show";
    }

    /**
     * Displays the form to create new project.
     *
     * @param model mvc model
     * @return view
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    @PreAuthorize("hasPermission('PROJECT', 'ADD')")
    public String showCreateProjectForm(ModelMap model) {
        if (!RomsPermissionEvaluator.hasPermission(uk.org.rbc1b.roms.security.Application.PROJECT, AccessLevel.ADD)) {
            throw new ForbiddenRequestException("Access to add new project is required to show the new project form");
        }

        model.addAttribute("projectForm", new ProjectForm());
        model.addAttribute("submitUri", ProjectModelFactory.generateUri(null));
        model.addAttribute("submitMethod", "POST");

        return "projects/edit";
    }

    /**
     * Handles the request to add a new project.
     *
     * @param form the project form
     * @return the redirect
     */
    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasPermission('PROJECT', 'ADD')")
    public String createProject(@Valid ProjectForm form) {
        Project project = new Project();
        KingdomHall kingdomHall = kingdomHallDao.findKingdomHall(form.getKingdomHallId());

        project.setName(form.getName());
        if (form.getCoordinatorId() != null) {
            project.setCoordinator(personDao.findPerson(form.getCoordinatorId()));
        }
        project.setKingdomHall(kingdomHall);
        project.setMinorWork(form.isMinorWork());
        project.setRequestDate(DataConverterUtil.toSqlDate(form.getRequestDate()));
        if (form.getCompletedDate() != null) {
            project.setCompletedDate(DataConverterUtil.toSqlDate(form.getCompletedDate()));
        }

        projectDao.createProject(project);

        return "redirect:" + ProjectModelFactory.generateUri(project.getProjectId());
    }

    /**
     * Handles request to create new project department work sessions.
     *
     * @param response the http response
     * @param form the valid form
     */
    @RequestMapping(value = "{projectId}/project-work-session", method = RequestMethod.POST)
    @PreAuthorize("hasPermission('ATTENDANCE', 'EDIT')")
    public void createProjectDepartmentSessionHandler(HttpServletResponse response,
            @Valid ProjectDepartmentSessionForm form) {
        ProjectDepartmentSession workSession = new ProjectDepartmentSession();
        Project project = projectDao.findProject(form.getProjectId());
        Department department = departmentDao.findDepartment(form.getDepartmentId());
        if (project != null && department != null) {
            workSession.setProject(project);
            workSession.setDepartment(department);
            workSession.setFromDate(DataConverterUtil.toSqlDate(form.getFromDate()));
            if (form.getToDate() == null) {
                workSession.setToDate(DataConverterUtil.toSqlDate(form.getFromDate()));
            } else {
                workSession.setToDate(DataConverterUtil.toSqlDate(form.getToDate()));
            }
            workSession.setSunday(form.isSunday());
            workSession.setUpdatedBy(findUserId());
            workSession.setUpdateTime(new java.sql.Date(new java.util.Date().getTime()));
            projectDepartmentSessionDao.save(workSession);
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    /**
     * Returns a list of matching names.
     *
     * @param name the name to match
     * @return list of projects
     */
    @RequestMapping(value = "search", method = RequestMethod.GET, produces = "application/json")
    @PreAuthorize("hasPermission('PROJECT', 'READ')")
    @ResponseBody
    public List<ProjectSearchResult> findProjects(@RequestParam(value = "name", required = true) String name) {
        List<Project> projects = projectDao.findProject(name);
        List<ProjectSearchResult> results = new ArrayList<>();

        if (!projects.isEmpty()) {
            for (Project project : projects) {
                ProjectSearchResult result = new ProjectSearchResult();
                result.setProjectName(project.getName());
                KingdomHall kingdomHall = kingdomHallDao.findKingdomHall(project.getKingdomHall().getKingdomHallId());
                result.setKingdomHallName(kingdomHall.getName());
                results.add(result);
            }
        }

        return results;
    }

    /**
     * Shows the project edit form.
     *
     * @param projectId project id
     * @param model mvc model
     * @return view name
     */
    @RequestMapping(value = "{projectId}/edit", method = RequestMethod.GET)
    @PreAuthorize("hasPermission('PROJECT','EDIT')")
    public String showProjectForm(@PathVariable Integer projectId, ModelMap model) {

        Project project = projectDao.findProject(projectId);
        if (project == null) {
            throw new ResourceNotFoundException("No project #" + projectId);
        }
        project.setKingdomHall(kingdomHallDao.findKingdomHall(project.getKingdomHall().getKingdomHallId()));
        if (project.getCoordinator() != null) {
            project.setCoordinator(personDao.findPerson(project.getCoordinator().getPersonId()));
        }
        ProjectForm form = new ProjectForm(project);

        model.addAttribute("projectForm", form);
        model.addAttribute("submitUri", ProjectModelFactory.generateUri(projectId));
        model.addAttribute("submitMethod", "PUT");

        return "projects/edit";
    }

    /**
     * Updates the project using the form bean.
     *
     * @param projectId the project id
     * @param form the form bean
     * @return view exist
     */
    @RequestMapping(value = "{projectId}", method = RequestMethod.PUT)
    @PreAuthorize("hasPermission('PROJECT','EDIT')")
    public String updateProject(@PathVariable Integer projectId, @Valid ProjectForm form) {
        Project project = projectDao.findProject(projectId);

        if (project == null) {
            throw new ResourceNotFoundException("No project #" + projectId);
        }

        KingdomHall kingdomHall = kingdomHallDao.findKingdomHall(form.getKingdomHallId());
        project.setKingdomHall(kingdomHall);

        if (form.getCoordinatorId() != null) {
            Person person = personDao.findPerson(form.getCoordinatorId());
            project.setCoordinator(person);
        }
        project.setName(form.getName());
        if (form.isMinorWork()) {
            project.setMinorWork(true);
        } else {
            project.setMinorWork(false);
        }
        project.setRequestDate(DataConverterUtil.toSqlDate(form.getRequestDate()));
        if (form.getCompletedDate() != null) {
            project.setCompletedDate(DataConverterUtil.toSqlDate(form.getCompletedDate()));
        }

        projectDao.updateProject(project);

        return "redirect:" + ProjectModelFactory.generateUri(projectId);
    }

    /**
     * Returns the gate list in downloadable CSV format.
     *
     * @param projectId the project id
     * @param projectDate the date
     * @param response the servlet response
     * @throws IOException failure to write to output stream
     * @throws ParseException failure to parse date
     */
    @RequestMapping(value = "gate-list/{projectId}/{projectDate}")
    @PreAuthorize("hasPermission('PROJECT','READ')")
    public void downloadGateList(@PathVariable Integer projectId, @PathVariable String projectDate, HttpServletResponse response)
            throws IOException, ParseException {
        FastDateFormat format = FastDateFormat.getInstance("dd-MM-yyyy");
        java.util.Date dateParser = format.parse(projectDate);
        java.sql.Date sqlDate = new java.sql.Date(dateParser.getTime());

        List<ProjectAttendance> attendances = projectAttendanceDao.findConfirmedVolunteersForProjectByDate(projectId, sqlDate);
        List<ProjectGateListModel> models = projectGateListModelFactory.generateModels(attendances);

        String[] headers = new String[]{"RBC ID", "Surname", "Forename", "Department"};

        String fileName = "gatelist-" + projectId + "-" + projectDate + ".csv";

        response.setContentType(MediaType.CSV_UTF_8.toString());
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

        OutputStream output = response.getOutputStream();
        CSVWriter writer = new CSVWriter(new OutputStreamWriter(output), '\u0009');

        writer.writeNext(headers);
        writer.writeAll(convertModelArray(models));
        writer.close();
    }

    private List<String[]> convertModelArray(List<ProjectGateListModel> models) {
        List<String[]> gateList = new ArrayList<String[]>();
        for (ProjectGateListModel model : models) {
            String[] rowData = new String[4];
            rowData[0] = Integer.toString(model.getPersonId().intValue());
            rowData[1] = model.getSurname();
            rowData[2] = model.getForename();
            rowData[3] = model.getDepartment();
            gateList.add(rowData);
        }
        return gateList;
    }

    private Integer findUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ROMSUserDetails user = (ROMSUserDetails) authentication.getPrincipal();
        return user.getUserId();
    }

    private ROMSGrantedAuthority findAttendanceAccess() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ROMSUserDetails user = (ROMSUserDetails) authentication.getPrincipal();
        return user.findAuthority(Application.ATTENDANCE);
    }
}
