/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uk.org.rbc1b.roms.controller.person.PersonsController;
import uk.org.rbc1b.roms.db.project.Project;
import uk.org.rbc1b.roms.db.project.ProjectDao;
import uk.org.rbc1b.roms.db.project.ProjectStage;
import uk.org.rbc1b.roms.reference.ReferenceDao;

/**
 * Control access to the underlying person data.
 *
 * @author oliver
 */
@Controller
@RequestMapping("/projects")
public class ProjectsController {

    private static final String BASE_URI = "/projects/";
    private ProjectDao projectDao;
    private ReferenceDao referenceDao;

    /**
     * Generate the uri used to access the project pages.
     *
     * @param projectId optional project id
     * @return uri
     */
    public static String generateUri(Integer projectId) {
        return projectId != null ? BASE_URI + projectId : BASE_URI;
    }

    /**
     * Display the list of projects.
     *
     * @param model mvc model
     * @return view
     */
    @RequestMapping(method = RequestMethod.GET, headers = "Accept=text/html")
    public String handleList(ModelMap model) {

        List<Project> projects = projectDao.findProjects();
        Map<Integer, ProjectStage> stages = projectDao.findProjectStages();
        Map<Integer, String> types = referenceDao.findProjectTypeValues();
        Map<Integer, String> statuses = referenceDao.findProjectStatusValues();
        List<ProjectListModel> modelList = new ArrayList<ProjectListModel>(projects.size());
        for (Project project : projects) {
            modelList.add(generateProjectModel(project, stages, types, statuses));
        }

        model.addAttribute("projects", modelList);

        return "projects/list";
    }



    private ProjectListModel generateProjectModel(Project project, Map<Integer, ProjectStage> stages,
            Map<Integer, String> types, Map<Integer, String> statuses) {
        ProjectListModel model = new ProjectListModel();
        model.setProjectId(project.getProjectId());
        model.setCompletedDate(project.getCompletedDate());

        if (project.getContactPerson() != null) {
            model.setContactPersonName(project.getContactPerson().getForename() + " "
                    + project.getContactPerson().getSurname());
            model.setContactPersonUri(PersonsController.generateUri(project.getContactPerson().getPersonId()));
        }
        model.setName(project.getName());
        model.setRequestDate(project.getRequestDate());

        ProjectStage stage = stages.get(project.getProjectStageId());
        model.setStageName(stage.getName());
        model.setStageDescription(stage.getDescription());
        model.setStageAssignedTo(stage.getAssignedTo());
        model.setStatus(statuses.get(project.getProjectStatusId()));
        model.setType(types.get(project.getProjectTypeId()));
        model.setUri(generateUri(project.getProjectId()));

        return model;
    }

    @Autowired
    public void setProjectDao(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    @Autowired
    public void setReferenceDao(ReferenceDao referenceDao) {
        this.referenceDao = referenceDao;
    }


}
