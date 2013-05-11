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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;
import uk.org.rbc1b.roms.controller.common.model.EntityModel;
import uk.org.rbc1b.roms.controller.common.model.PersonModelFactory;
import uk.org.rbc1b.roms.controller.kingdomhall.KingdomHallsController;
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
    private PersonModelFactory personModelFactory;

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
            modelList.add(generateProjectListModel(project, stages, types, statuses));
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
    public String handleProject(@PathVariable Integer projectId, ModelMap model) throws NoSuchRequestHandlingMethodException {

        Project project = projectDao.findProject(projectId);
        if (project == null) {
            throw new NoSuchRequestHandlingMethodException("No project with id [" + projectId + "]", this.getClass());
        }

        model.addAttribute("project", generateProjectModel(project));

        return "projects/show";
    }

    private ProjectListModel generateProjectListModel(Project project, Map<Integer, ProjectStage> stages,
            Map<Integer, String> types, Map<Integer, String> statuses) {
        ProjectListModel model = new ProjectListModel();
        model.setProjectId(project.getProjectId());
        model.setCompletedDate(project.getCompletedDate());

        if (project.getContactPerson() != null) {
            EntityModel contactPerson = new EntityModel();
            contactPerson.setId(project.getContactPerson().getPersonId());
            contactPerson.setName(project.getContactPerson().getForename() + " "
                    + project.getContactPerson().getSurname());
            contactPerson.setUri(personModelFactory.generateUri(project.getContactPerson().getPersonId()));

            model.setContactPerson(contactPerson);
        }
        model.setName(project.getName());
        model.setRequestDate(project.getRequestDate());
        model.setStage(stages.get(project.getProjectStageId()));
        model.setStatus(statuses.get(project.getProjectStatusId()));
        model.setType(types.get(project.getProjectTypeId()));
        model.setUri(generateUri(project.getProjectId()));

        return model;
    }

    private ProjectModel generateProjectModel(Project project) {

        Map<Integer, ProjectStage> stages = projectDao.findProjectStages();
        Map<Integer, String> types = referenceDao.findProjectTypeValues();
        Map<Integer, String> statuses = referenceDao.findProjectStatusValues();

        ProjectModel model = new ProjectModel();
        model.setCompletedDate(project.getCompletedDate());
        model.setConstraints(project.getConstraints());
        model.setContactPerson(personModelFactory.generatePersonModel(project.getContactPerson()));
        model.setCoordinator(personModelFactory.generatePersonModel(project.getCoordinator()));
        model.setEstimateCost(project.getEstimateCost());

        if (project.getKingdomHall() != null) {
            EntityModel kingdomHallModel = new EntityModel();
            kingdomHallModel.setId(project.getKingdomHall().getKingdomHallId());
            kingdomHallModel.setName(project.getKingdomHall().getName());
            kingdomHallModel.setUri(KingdomHallsController.generateUri(project.getKingdomHall().getKingdomHallId()));
            model.setKingdomHall(kingdomHallModel);
        }
        model.setName(project.getName());
        model.setPriority(project.getPriority());
        model.setProjectId(project.getProjectId());
        model.setRequestDate(project.getRequestDate());
        model.setStage(stages.get(project.getProjectStageId()));
        model.setStatus(statuses.get(project.getProjectStatusId()));
        model.setSupportingCongregation(project.getSupportingCongregation());
        model.setTelephone(project.getTelephone());
        model.setType(types.get(project.getProjectTypeId()));
        model.setVisitDate(project.getVisitDate());

        return model;
    }

    @Autowired
    public void setProjectDao(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    @Autowired
    public void setPersonModelFactory(PersonModelFactory personModelFactory) {
        this.personModelFactory = personModelFactory;
    }

    @Autowired
    public void setReferenceDao(ReferenceDao referenceDao) {
        this.referenceDao = referenceDao;
    }
}
