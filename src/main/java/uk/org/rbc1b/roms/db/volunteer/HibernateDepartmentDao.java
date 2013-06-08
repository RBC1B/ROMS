/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db.volunteer;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
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

    @Override
    public Department findDepartmentByName(String name) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Department.class);
        return (Department) criteria.add(Restrictions.naturalId().set("name", name)).setCacheable(true).uniqueResult();
    }

    @Override
    @Cacheable("department.departmentList")
    public List<Department> getAllDepartments() {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Department.class);
        return criteria.addOrder(Order.asc("name")).list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
