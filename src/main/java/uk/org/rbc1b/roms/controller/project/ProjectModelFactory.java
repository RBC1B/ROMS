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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.org.rbc1b.roms.controller.common.model.EntityModel;
import uk.org.rbc1b.roms.controller.common.model.PersonModelFactory;
import uk.org.rbc1b.roms.controller.kingdomhall.KingdomHallModelFactory;
import uk.org.rbc1b.roms.db.Person;
import uk.org.rbc1b.roms.db.PersonDao;
import uk.org.rbc1b.roms.db.project.Project;
import uk.org.rbc1b.roms.db.project.ProjectDao;
import uk.org.rbc1b.roms.db.project.ProjectStage;
import uk.org.rbc1b.roms.db.project.ProjectStageActivity;
import uk.org.rbc1b.roms.db.project.ProjectStageActivityTask;
import uk.org.rbc1b.roms.db.project.ProjectStageActivityType;
import uk.org.rbc1b.roms.db.project.ProjectStageType;
import uk.org.rbc1b.roms.db.reference.ReferenceDao;

/**
 * Create the project (list, details) model.
 */
@Component
public class ProjectModelFactory {

    private static final String BASE_URI = "/projects/";
    private static final ProjectStageTaskModelComparator TASK_COMPARATOR = new ProjectStageTaskModelComparator();
    private PersonModelFactory personModelFactory;
    private ReferenceDao referenceDao;
    private ProjectDao projectDao;
    private PersonDao personDao;

    /**
     * Generate the uri used to access the project pages.
     * @param projectId optional project id
     * @return uri
     */
    public static String generateUri(Integer projectId) {
        return projectId != null ? BASE_URI + projectId : BASE_URI;
    }

    /**
     * Generate the model used in the project list.
     * @param project project
     * @param types project types reference data
     * @param statuses project status reference data
     * @return model
     */
    public ProjectListModel generateProjectListModel(Project project, Map<Integer, String> types,
            Map<Integer, String> statuses) {
        ProjectListModel model = new ProjectListModel();
        model.setProjectId(project.getProjectId());
        model.setCompletedDate(project.getCompletedDate());

        if (project.getContactPerson() != null) {
            Person person = personDao.findPerson(project.getContactPerson().getPersonId());
            EntityModel contactPerson = new EntityModel();
            contactPerson.setId(person.getPersonId());
            contactPerson.setName(person.formatDisplayName());
            contactPerson.setUri(PersonModelFactory.generateUri(person.getPersonId()));

            model.setContactPerson(contactPerson);
        }
        model.setName(project.getName());
        model.setRequestDate(project.getRequestDate());
        model.setStatus(statuses.get(project.getStatusId()));
        model.setType(types.get(project.getProjectTypeId()));
        model.setUri(generateUri(project.getProjectId()));

        return model;
    }

    /**
     * Project details model.
     * @param project project
     * @return model
     */
    public ProjectModel generateProjectModel(Project project) {

        Map<Integer, String> types = referenceDao.findProjectTypeValues();
        Map<Integer, String> statuses = referenceDao.findProjectStatusValues();

        ProjectModel model = new ProjectModel();
        model.setCompletedDate(project.getCompletedDate());
        model.setConstraints(project.getConstraints());

        if (project.getContactPerson() != null) {
            Person person = personDao.findPerson(project.getContactPerson().getPersonId());
            model.setContactPerson(personModelFactory.generatePersonModel(person));
        }

        if (project.getCoordinator() != null) {
            Person person = personDao.findPerson(project.getCoordinator().getPersonId());
            model.setCoordinator(personModelFactory.generatePersonModel(person));
        }
        model.setEstimateCost(project.getEstimateCost());

        if (project.getKingdomHall() != null) {
            EntityModel kingdomHallModel = new EntityModel();
            kingdomHallModel.setId(project.getKingdomHall().getKingdomHallId());
            kingdomHallModel.setName(project.getKingdomHall().getName());
            kingdomHallModel.setUri(KingdomHallModelFactory.generateUri(project.getKingdomHall().getKingdomHallId()));
            model.setKingdomHall(kingdomHallModel);
        }
        model.setName(project.getName());
        model.setPriority(project.getPriority());
        model.setProjectId(project.getProjectId());
        model.setRequestDate(project.getRequestDate());
        model.setStatus(statuses.get(project.getStatusId()));
        model.setSupportingCongregation(project.getSupportingCongregation());
        model.setTelephone(project.getTelephone());
        model.setType(types.get(project.getProjectTypeId()));
        model.setVisitDate(project.getVisitDate());

        return model;
    }

    /**
     * Generate the list of project stages.
     * @param stages stages
     * @return stage model list
     */
    public List<ProjectStageModel> generateProjectStages(List<ProjectStage> stages) {
        if (stages.isEmpty()) {
            return Collections.emptyList();
        }

        Map<Integer, ProjectStageType> stageTypes = projectDao.findProjectStageTypes();
        Map<Integer, String> statuses = referenceDao.findProjectStatusValues();

        List<ProjectStageModel> modelList = new ArrayList<ProjectStageModel>();

        for (ProjectStage stage : stages) {
            ProjectStageModel model = new ProjectStageModel();
            model.setProjectStageId(stage.getProjectStageId());
            model.setType(stageTypes.get(stage.getProjectStageTypeId()));
            model.setStatus(statuses.get(stage.getStatusId()));
            model.setCreatedTime(stage.getCreatedTime());
            model.setStartedTime(stage.getStartedTime());
            model.setCompletedTime(stage.getCompletedTime());

            model.setActivities(generateProjectStageActivities(stage, statuses));

            modelList.add(model);
        }
        return modelList;
    }

    private List<ProjectStageActivityModel> generateProjectStageActivities(ProjectStage stage,
            Map<Integer, String> statuses) {

        Map<Integer, ProjectStageActivityType> activityTypes = projectDao.findProjectStageActivityTypes();

        List<ProjectStageActivityModel> modelList = new ArrayList<ProjectStageActivityModel>();
        for (ProjectStageActivity activity : stage.getActivities()) {

            // we make use of the person dao caching, otherwise we would look these up first
            // to prevent duplicate lookups.
            Person person = personDao.findPerson(activity.getAssignedVolunteer().getPersonId());

            ProjectStageActivityModel model = new ProjectStageActivityModel();
            model.setAssignedVolunteer(personModelFactory.generatePersonModel(person));
            model.setComments(activity.getComments());
            model.setCompletedTime(activity.getCompletedTime());
            model.setCreatedTime(activity.getCreatedTime());
            model.setProjectedCompletion(activity.getProjectedCompletion());
            model.setProjectedStart(activity.getProjectedStart());
            model.setProjectStageActivityId(activity.getProjectStageActivityId());
            model.setStartedTime(activity.getStartedTime());
            model.setStatus(statuses.get(activity.getStatusId()));
            model.setType(activityTypes.get(activity.getProjectStageActivityType().getProjectStageActivityTypeId()));

            model.setTasks(generateProjectStageActivityTasks(activity, statuses));

            modelList.add(model);

        }
        return modelList;
    }

    private List<ProjectStageActivityTaskModel> generateProjectStageActivityTasks(ProjectStageActivity activity,
            Map<Integer, String> statuses) {

        List<ProjectStageActivityTaskModel> modelList = new ArrayList<ProjectStageActivityTaskModel>();
        for (ProjectStageActivityTask task : activity.getTasks()) {

            // we make use of the person dao caching, otherwise we would look these up first
            // to prevent duplicate lookups.
            Person person = personDao.findPerson(task.getAssignedVolunteer().getPersonId());

            ProjectStageActivityTaskModel model = new ProjectStageActivityTaskModel();

            model.setAssignedVolunteer(personModelFactory.generatePersonModel(person));
            model.setComments(task.getComments());
            model.setCompletedTime(task.getCompletedTime());
            model.setCreatedTime(task.getCreatedTime());
            model.setName(task.getName());
            model.setProjectStageActivityTaskId(task.getProjectStageActivityTaskId());
            model.setStartedTime(task.getStartedTime());
            model.setStatus(statuses.get(activity.getStatusId()));
            modelList.add(model);

        }

        Collections.sort(modelList, TASK_COMPARATOR);

        return modelList;
    }

    @Autowired
    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Autowired
    public void setPersonModelFactory(PersonModelFactory personModelFactory) {
        this.personModelFactory = personModelFactory;
    }

    @Autowired
    public void setProjectDao(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    @Autowired
    public void setReferenceDao(ReferenceDao referenceDao) {
        this.referenceDao = referenceDao;
    }

    /**
     * Task comparator, sort by task creation date.
     */
    private static class ProjectStageTaskModelComparator implements Comparator<ProjectStageActivityTaskModel>,
            Serializable {
        private static final long serialVersionUID = 5200865793008893390L;

        @Override
        public int compare(ProjectStageActivityTaskModel t, ProjectStageActivityTaskModel t1) {
            return t.getCreatedTime().compareTo(t1.getCreatedTime());
        }
    }
}
