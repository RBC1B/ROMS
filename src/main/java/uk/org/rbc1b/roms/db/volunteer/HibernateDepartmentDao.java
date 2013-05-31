/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db.volunteer;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

/**
 * Hibernate implementation of the department dao.
 *
 * @author oliver.elder.esq
 */
@Repository
public class HibernateDepartmentDao implements DepartmentDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Cacheable("department.team")
    public Team findTeam(Integer teamId) {
        return (Team) this.sessionFactory.getCurrentSession().get(Team.class, teamId);
    }

    @Override
    @Cacheable("department.department")
    public Department findDepartment(Integer departmentId) {
        return (Department) this.sessionFactory.getCurrentSession().get(Department.class, departmentId);
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
