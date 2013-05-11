/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db.project;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

/**
 * Implements ProjectDao.
 *
 * @author oliver
 */
@Repository
public class HibernateProjectDao implements ProjectDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Project> findProjects() {
        Session session = this.sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(Project.class)
                .createAlias("contactPerson", "contactPerson", JoinType.LEFT_OUTER_JOIN);
        return criteria.list();
    }

    @Override
    public Project findProject(Integer projectId) {
        Project project = (Project) this.sessionFactory.getCurrentSession().get(Project.class, projectId);
        if (project == null) {
            return null;
        }
        Hibernate.initialize(project.getContactPerson());
        Hibernate.initialize(project.getCoordinator());
        Hibernate.initialize(project.getKingdomHall());

        return project;
    }

    @Override
    @Cacheable("project.stage")
    public Map<Integer, ProjectStage> findProjectStages() {
        Session session = this.sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(ProjectStage.class).addOrder(Order.asc("projectStageId"));
        List<ProjectStage> stages = criteria.list();

        Map<Integer, ProjectStage> resultMap = new LinkedHashMap<Integer, ProjectStage>(stages.size());
        for (ProjectStage stage : stages) {
            resultMap.put(stage.getProjectStageId(), stage);
        }
        return resultMap;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
