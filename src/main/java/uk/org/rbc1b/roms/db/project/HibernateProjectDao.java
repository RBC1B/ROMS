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
 * @author oliver
 */
@Repository
public class HibernateProjectDao implements ProjectDao {

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
    public List<ProjectStage> findProjectStages(Integer projectId) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(ProjectStage.class);
        criteria.add(Restrictions.eq("project.id", projectId));
        @SuppressWarnings("unchecked")
        List<ProjectStage> stages = criteria.list();

        for (ProjectStage stage : stages) {
            for (ProjectStageActivity activity : stage.getActivities()) {
                Hibernate.initialize(activity.getTasks());
            }
        }

        criteria = session.createCriteria(ProjectStageOrder.class);
        criteria.add(Restrictions.eq("projectId", projectId));

        @SuppressWarnings("unchecked")
        List<ProjectStageOrder> stageOrders = criteria.list();

        ProjectStageOrder.sortProjectStages(stages, stageOrders);

        return stages;
    }

    @Override
    public void updateProjectStageOrder(Integer projectId, List<Integer> stageIds) {
        final Session session = this.sessionFactory.getCurrentSession();
        List<ProjectStageOrder> incoming = ProjectStageOrder.createProjectStageOrders(projectId, stageIds);

        Criteria criteria = session.createCriteria(ProjectStageOrder.class);
        criteria.add(Restrictions.eq("projectId", projectId));

        @SuppressWarnings("unchecked")
        List<ProjectStageOrder> existing = criteria.list();

        MergeUtil.sortAndMerge(existing, incoming, new Comparator<ProjectStageOrder>() {
            @Override
            public int compare(ProjectStageOrder o1, ProjectStageOrder o2) {
                return o1.getProjectStageId().compareTo(o2.getProjectStageId());
            }
        }, new MergeUtil.Callback<ProjectStageOrder, ProjectStageOrder>() {
            @Override
            public void output(ProjectStageOrder existingOrder, ProjectStageOrder incomingOrder) {
                if (existingOrder == null) {
                    session.save(incomingOrder);
                } else if (incomingOrder == null) {
                    session.delete(existingOrder);
                } else if (!ObjectUtils.equals(existingOrder.getPreviousProjectStageId(),
                        incomingOrder.getPreviousProjectStageId())
                        || !ObjectUtils.equals(existingOrder.getNextProjectStageId(),
                                incomingOrder.getNextProjectStageId())) {
                    existingOrder.setNextProjectStageId(incomingOrder.getNextProjectStageId());
                    existingOrder.setPreviousProjectStageId(incomingOrder.getPreviousProjectStageId());
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

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
