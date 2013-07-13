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
package uk.org.rbc1b.roms.controller.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;
import uk.org.rbc1b.roms.db.project.Project;
import uk.org.rbc1b.roms.db.project.ProjectDao;
import uk.org.rbc1b.roms.db.reference.ReferenceDao;

/**
 * Control access to the underlying person data.
 *
 * @author oliver
 */
@Controller
@RequestMapping("/projects")
public class ProjectsController {

    private ProjectDao projectDao;
    private ReferenceDao referenceDao;
    private ProjectModelFactory projectModelFactory;

    /**
     * Display the list of projects.
     *
     * @param model mvc model
     * @return view
     */
    @RequestMapping(method = RequestMethod.GET, headers = "Accept=text/html")
    public String showProjectList(ModelMap model) {

        List<Project> projects = projectDao.findProjects();
        Map<Integer, String> types = referenceDao.findProjectTypeValues();
        Map<Integer, String> statuses = referenceDao.findProjectStatusValues();
        List<ProjectListModel> modelList = new ArrayList<ProjectListModel>(projects.size());
        for (Project project : projects) {
            modelList.add(projectModelFactory.generateProjectListModel(project, types, statuses));
        }

        model.addAttribute("projects", modelList);

        return "projects/list";
    }

    /**
     * @param projectId project primary key
     * @param model model
     * @return view name
     * @throws NoSuchRequestHandlingMethodException when no project matching the id is found
     */
    @RequestMapping(value = "{projectId}", method = RequestMethod.GET)
    public String showProject(@PathVariable Integer projectId, ModelMap model) throws NoSuchRequestHandlingMethodException {

        Project project = projectDao.findProject(projectId);
        if (project == null) {
            throw new NoSuchRequestHandlingMethodException("No project with id [" + projectId + "]", this.getClass());
        }

        ProjectModel projectModel = projectModelFactory.generateProjectModel(project);
        projectModel.setStages(projectModelFactory.generateProjectStages(projectDao.findProjectStages(projectId)));

        model.addAttribute("project", projectModel);

        return "projects/show";
    }

    @Autowired
    public void setProjectDao(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    @Autowired
    public void setProjectModelFactory(ProjectModelFactory projectModelFactory) {
        this.projectModelFactory = projectModelFactory;
    }

    @Autowired
    public void setReferenceDao(ReferenceDao referenceDao) {
        this.referenceDao = referenceDao;
    }
}
