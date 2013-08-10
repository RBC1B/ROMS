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

    @SuppressWarnings("unchecked")
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
