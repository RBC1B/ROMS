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
package uk.org.rbc1b.roms.db.project;

import static uk.org.rbc1b.roms.db.project.ProjectStageSortable.ProjectStageOrderType.PROJECT_STAGE;
import static uk.org.rbc1b.roms.db.project.ProjectStageSortable.ProjectStageOrderType.PROJECT_STAGE_ACTIVITY;
import static uk.org.rbc1b.roms.db.project.ProjectStageSortable.ProjectStageOrderType.PROJECT_STAGE_ACTIVITY_TASK;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import uk.org.rbc1b.roms.db.common.MergeUtil;

/**
 * Implements ProjectDao.
 *
 * @author oliver
 */
@Repository
public class HibernateProjectDao implements ProjectDao {
    private static final String CREATED_STATUS_CODE = "CR";
    private static final String CREATED_EVENT_CODE = "CR";

    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    public List<Project> findProjects() {
        Session session = this.sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(Project.class).createAlias("contactPerson", "contactPerson",
                JoinType.LEFT_OUTER_JOIN);
        return criteria.list();
    }

    @Override
    public Project findProject(Integer projectId) {
        Project project = (Project) this.sessionFactory.getCurrentSession().get(Project.class, projectId);
        if (project == null) {
            return null;
        }
        Hibernate.initialize(project.getKingdomHall());

        return project;
    }

    @Override
    public Project findProject(String name) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Project.class);
        criteria.add(Restrictions.eq("name", name));

        Project project = (Project) criteria.uniqueResult();

        if (project == null) {
            return null;
        }
        Hibernate.initialize(project.getKingdomHall());

        return project;
    }

    @Override
    public void createProject(Project project) {
        project.setStatusCode(CREATED_STATUS_CODE);

        for (ProjectStage stage : project.getStages()) {
            stage.setStatusCode(CREATED_STATUS_CODE);
            stage.setProject(project);

            for (ProjectStageActivity activity : stage.getActivities()) {
                activity.setStatusCode(CREATED_STATUS_CODE);
                activity.setProjectStage(stage);
            }
        }

        Session session = this.sessionFactory.getCurrentSession();

        session.save(project);

        // save events associated with the creation of the project objects
        for (ProjectStage stage : project.getStages()) {
            ProjectStageEvent projectStageEvent = new ProjectStageEvent();
            projectStageEvent.setCreateTime(stage.getCreatedTime());
            projectStageEvent.setProjectStage(stage);
            projectStageEvent.setProjectStageEventTypeCode(CREATED_EVENT_CODE);
            session.save(projectStageEvent);

            for (ProjectStageActivity activity : stage.getActivities()) {
                ProjectStageActivityEvent projectStageActivityEvent = new ProjectStageActivityEvent();
                projectStageActivityEvent.setCreateTime(activity.getCreatedTime());
                projectStageActivityEvent.setProjectStageActivity(activity);
                projectStageActivityEvent.setProjectStageActivityEventTypeCode(CREATED_EVENT_CODE);
                session.save(projectStageEvent);
            }
        }

        // save the stage orders in the order they are defined in the type map
        List<ProjectTypeStageType> stageTypes = findProjectTypeStageTypes(project.getProjectTypeId());
        List<ProjectStageOrder> stageOrders = new ArrayList<ProjectStageOrder>();
        int index = 1;
        for (ProjectTypeStageType stageType : stageTypes) {
            ProjectStage stage = project.findStage(stageType.getProjectStageTypeId());

            ProjectStageOrder stageOrder = new ProjectStageOrder();
            stageOrder.setNextProjectStageSortableId(index + 1);
            stageOrder.setPreviousProjectStageSortableId(index > 1 ? index - 1 : null);
            stageOrder.setProjectId(project.getProjectId());
            stageOrder.setProjectStageSortableId(stage.getProjectStageId());
            stageOrders.add(stageOrder);
        }

        // delete the last stage order next id. Empty check should not be required
        // once all project types have their activity mappings defined
        if (!stageOrders.isEmpty()) {
            stageOrders.get(stageOrders.size() - 1).setNextProjectStageSortableId(null);

            for (ProjectStageOrder stageOrder : stageOrders) {
                session.save(stageOrder);
            }
        }

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ProjectTypeStageType> findProjectTypeStageTypes(Integer projectTypeId) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(ProjectTypeStageType.class);
        criteria.add(Restrictions.eq("projectTypeId", projectTypeId));

        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ProjectStageTypeActivityType> findProjectStageTypeActivityType(Integer stageTypeId) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(ProjectStageTypeActivityType.class);
        criteria.add(Restrictions.eq("projectStageTypeId", stageTypeId));

        return criteria.list();
    }

    @Override
    public List<ProjectStage> findProjectStages(Integer projectId) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(ProjectStage.class);
        criteria.add(Restrictions.eq("project.id", projectId));
        @SuppressWarnings("unchecked")
        List<ProjectStage> stages = criteria.list();

        for (ProjectStage stage : stages) {
            Hibernate.initialize(stage.getEvents());
        }

        ProjectStageOrder.sortProjectStages(stages,
                filterProjectStageOrder(session, projectId, PROJECT_STAGE.getValue()));

        return stages;
    }

    @Override
    public List<ProjectStageActivity> findProjectStageActivities(Integer projectStageId) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(ProjectStageActivity.class);
        criteria.add(Restrictions.eq("projectStage.id", projectStageId));
        @SuppressWarnings("unchecked")
        List<ProjectStageActivity> stageActivities = criteria.list();

        for (ProjectStageActivity activity : stageActivities) {
            Hibernate.initialize(activity.getEvents());
        }

        ProjectStageOrder.sortProjectStages(stageActivities,
                filterProjectStageOrder(session, projectStageId, PROJECT_STAGE_ACTIVITY.getValue()));

        return stageActivities;
    }

    @Override
    public List<ProjectStageActivityTask> findProjectStageActivityTasks(Integer projectStageActivityId) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(ProjectStageActivityTask.class);
        criteria.add(Restrictions.eq("projectStageActivity.id", projectStageActivityId));
        @SuppressWarnings("unchecked")
        List<ProjectStageActivityTask> stageActivityTasks = criteria.list();

        for (ProjectStageActivityTask task : stageActivityTasks) {
            Hibernate.initialize(task.getEvents());
        }

        ProjectStageOrder.sortProjectStages(stageActivityTasks,
                filterProjectStageOrder(session, projectStageActivityId, PROJECT_STAGE_ACTIVITY_TASK.getValue()));

        return stageActivityTasks;
    }

    @Override
    public ProjectStage findProjectStage(Integer projectStageId) {
        ProjectStage stage = (ProjectStage) this.sessionFactory.getCurrentSession().get(ProjectStage.class,
                projectStageId);
        if (stage == null) {
            return null;
        }

        Hibernate.initialize(stage.getEvents());
        for (ProjectStageActivity activity : stage.getActivities()) {
            Hibernate.initialize(activity.getEvents());

            for (ProjectStageActivityTask task : activity.getTasks()) {
                Hibernate.initialize(task.getEvents());
            }
        }

        return stage;
    }

    @Override
    public ProjectStageActivity findProjectStageActivity(Integer projectStageActivityId) {
        ProjectStageActivity activity = (ProjectStageActivity) this.sessionFactory.getCurrentSession().get(
                ProjectStageActivity.class, projectStageActivityId);
        if (activity == null) {
            return null;
        }

        Hibernate.initialize(activity.getEvents());

        for (ProjectStageActivityTask task : activity.getTasks()) {
            Hibernate.initialize(task.getEvents());
        }

        return activity;
    }

    @Override
    public void updateProjectStageOrder(Integer projectId, Integer projectStageOrderTypeId, List<Integer> stageIds) {
        final Session session = this.sessionFactory.getCurrentSession();
        List<ProjectStageOrder> incoming = ProjectStageOrder.createProjectStageOrders(projectId,
                projectStageOrderTypeId, stageIds);

        List<ProjectStageOrder> existing = filterProjectStageOrder(session, projectId, projectStageOrderTypeId);

        MergeUtil.sortAndMerge(existing, incoming, new Comparator<ProjectStageOrder>() {
            @Override
            public int compare(ProjectStageOrder o1, ProjectStageOrder o2) {
                return o1.getProjectStageSortableId().compareTo(o2.getProjectStageSortableId());
            }
        }, new MergeUtil.Callback<ProjectStageOrder, ProjectStageOrder>() {
            @Override
            public void output(ProjectStageOrder existingOrder, ProjectStageOrder incomingOrder) {
                if (existingOrder == null) {
                    session.save(incomingOrder);
                } else if (incomingOrder == null) {
                    session.delete(existingOrder);
                } else if (!ObjectUtils.equals(existingOrder.getPreviousProjectStageSortableId(),
                        incomingOrder.getPreviousProjectStageSortableId())
                        || !ObjectUtils.equals(existingOrder.getNextProjectStageSortableId(),
                                incomingOrder.getNextProjectStageSortableId())) {
                    existingOrder.setNextProjectStageSortableId(incomingOrder.getNextProjectStageSortableId());
                    existingOrder.setPreviousProjectStageSortableId(incomingOrder.getPreviousProjectStageSortableId());
                    session.update(existingOrder);
                }
            }
        });

    }

    @Override
    @Cacheable("project.stageType")
    public Map<Integer, ProjectStageType> findProjectStageTypes() {
        Session session = this.sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(ProjectStageType.class).addOrder(Order.asc("projectStageTypeId"));
        @SuppressWarnings("unchecked")
        List<ProjectStageType> stages = criteria.list();

        Map<Integer, ProjectStageType> resultMap = new LinkedHashMap<Integer, ProjectStageType>(stages.size());
        for (ProjectStageType stage : stages) {
            resultMap.put(stage.getProjectStageTypeId(), stage);
        }
        return resultMap;
    }

    @Override
    @Cacheable("project.stageActivityType")
    public Map<Integer, ProjectStageActivityType> findProjectStageActivityTypes() {
        Session session = this.sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(ProjectStageActivityType.class).addOrder(
                Order.asc("projectStageActivityTypeId"));
        @SuppressWarnings("unchecked")
        List<ProjectStageActivityType> activities = criteria.list();

        Map<Integer, ProjectStageActivityType> resultMap = new LinkedHashMap<Integer, ProjectStageActivityType>(
                activities.size());
        for (ProjectStageActivityType activity : activities) {
            resultMap.put(activity.getProjectStageActivityTypeId(), activity);
        }
        return resultMap;
    }

    @Override
    public ProjectStageActivityTask findTask(Integer taskId) {
        ProjectStageActivityTask task = (ProjectStageActivityTask) this.sessionFactory.getCurrentSession().get(
                ProjectStageActivityTask.class, taskId);

        if (task == null) {
            return null;
        }

        Hibernate.initialize(task.getEvents());

        return task;
    }

    @Override
    public void createTask(ProjectStageActivityTask task) {
        Session session = this.sessionFactory.getCurrentSession();
        task.setStatusCode(CREATED_STATUS_CODE);
        session.save(task);

        ProjectStageActivityTaskEvent event = new ProjectStageActivityTaskEvent();
        event.setCreateTime(task.getCreatedTime());
        event.setProjectStageActivityTask(task);
        event.setProjectStageActivityTaskEventTypeCode(CREATED_STATUS_CODE);
        session.save(event);

    }

    @Override
    public void updateTask(ProjectStageActivityTask task) {
        this.sessionFactory.getCurrentSession().merge(task);
    }

    @SuppressWarnings("unchecked")
    private List<ProjectStageOrder> filterProjectStageOrder(Session session, Integer projectId,
            Integer projectStageOrderTypeId) {

        Criteria criteria = session.createCriteria(ProjectStageOrder.class);
        criteria.add(Restrictions.eq("projectId", projectId));
        criteria.add(Restrictions.eq("projectStageOrderTypeId", projectStageOrderTypeId));

        return criteria.list();
    }
}
