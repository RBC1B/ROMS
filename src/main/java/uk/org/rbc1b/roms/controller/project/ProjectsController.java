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
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;
import uk.org.rbc1b.roms.controller.ForbiddenRequestException;
import uk.org.rbc1b.roms.controller.common.DataConverterUtil;
import uk.org.rbc1b.roms.db.Person;
import uk.org.rbc1b.roms.db.PersonDao;
import uk.org.rbc1b.roms.db.kingdomhall.KingdomHall;
import uk.org.rbc1b.roms.db.kingdomhall.KingdomHallDao;
import uk.org.rbc1b.roms.db.project.Project;
import uk.org.rbc1b.roms.db.project.ProjectDao;
import uk.org.rbc1b.roms.security.AccessLevel;
import uk.org.rbc1b.roms.security.RomsPermissionEvaluator;

/**
 *
 * @author ramindursingh
 */
@Controller
@RequestMapping("/projects")
public class ProjectsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectsController.class);
    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private KingdomHallDao kingdomHallDao;
    @Autowired
    private PersonDao personDao;
    @Autowired
    private ProjectModelFactory projectModelFactory;

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

        List<ProjectModel> modelList = new ArrayList<ProjectModel>();
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
     * @throws NoSuchRequestHandlingMethodException if project does not exist
     */
    @RequestMapping(value = "{projectId}", method = RequestMethod.GET)
    @PreAuthorize("hasPermission('PROJECT', 'READ')")
    public String showProject(@PathVariable Integer projectId, ModelMap model)
            throws NoSuchRequestHandlingMethodException {
        Project project = projectDao.findProject(projectId);
        if (project == null) {
            throw new NoSuchRequestHandlingMethodException("No project #" + projectId, this.getClass());
        }

        model.addAttribute("project", projectModelFactory.generateProjectModel(project));

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
            throw new ForbiddenRequestException(
                    "Access to add new project is required to show the new project form");
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
        List<ProjectSearchResult> results = new ArrayList<ProjectSearchResult>();

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
     * @throws NoSuchRequestHandlingMethodException if project not found
     */
    @RequestMapping(value = "{projectId}/edit", method = RequestMethod.GET)
    @PreAuthorize("hasPermission('PROJECT','EDIT')")
    public String showProjectForm(@PathVariable Integer projectId, ModelMap model)
            throws NoSuchRequestHandlingMethodException {

        Project project = projectDao.findProject(projectId);
        if (project == null) {
            throw new NoSuchRequestHandlingMethodException("No project #" + projectId, this.getClass());
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
     * @return view
     * @throws NoSuchRequestHandlingMethodException if the project does not
     * exist
     */
    @RequestMapping(value = "{projectId}", method = RequestMethod.PUT)
    @PreAuthorize("hasPermission('PROJECT','EDIT')")
    public String updateProject(@PathVariable Integer projectId, @Valid ProjectForm form)
            throws NoSuchRequestHandlingMethodException {
        Project project = projectDao.findProject(projectId);

        if (project == null) {
            throw new NoSuchRequestHandlingMethodException("No project #" + projectId, this.getClass());
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
}
