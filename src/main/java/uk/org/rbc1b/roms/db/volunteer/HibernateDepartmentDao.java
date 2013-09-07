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
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import uk.org.rbc1b.roms.controller.common.SortDirection;

/**
 * Hibernate implementation of the department dao.
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
    public List<Department> findDepartments() {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Department.class);
        return criteria.addOrder(Order.asc("name")).list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Department> findChildDepartments(Integer departmentId) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Department.class);
        criteria.add(Restrictions.eq("superDepartment.departmentId", departmentId));

        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Assignment> findAssignments(AssignmentSearchCriteria searchCriteria) {

        Criteria criteria = createAssigmentsSearchCriteria(searchCriteria);

        if (searchCriteria.getStartIndex() != null && searchCriteria.getMaxResults() != null) {
            criteria.setFirstResult(searchCriteria.getStartIndex());
            criteria.setMaxResults(searchCriteria.getMaxResults());
        }

        if (searchCriteria.getSearch() != null) {
            criteria.createAlias("person", "person", JoinType.LEFT_OUTER_JOIN);
            criteria.createAlias("person.congregation", "congregation", JoinType.LEFT_OUTER_JOIN);
            criteria.createAlias("team", "team", JoinType.LEFT_OUTER_JOIN);

            String searchValue = "%" + searchCriteria.getSearch() + "%";

            criteria.add(Restrictions.or(Restrictions.like("person.forename", searchValue),
                    Restrictions.like("person.surname", searchValue),
                    Restrictions.like("congregation.name", searchValue),
                    Restrictions.like("team.description", searchValue)));
        }

        if (searchCriteria.getSortValue() != null) {
            // we may need to join into the values of the sort column
            if (searchCriteria.getSearch() == null) {
                if (searchCriteria.getSortValue().startsWith("person")) {
                    criteria.createAlias("person", "person", JoinType.LEFT_OUTER_JOIN);
                }

                if (searchCriteria.getSortValue().startsWith("congregation")) {
                    criteria.createAlias("person.congregation", "congregation", JoinType.LEFT_OUTER_JOIN);
                }

                if (searchCriteria.getSortValue().startsWith("team")) {
                    criteria.createAlias("team", "team", JoinType.LEFT_OUTER_JOIN);
                }
            }

            String sortValue = searchCriteria.getSortValue();
            if (sortValue.equals("team.name")) {
                sortValue = "team.description";
            } else if (sortValue.equals("tradeNumber")) {
                sortValue = "tradeNumberId";
            } else if (sortValue.equals("role")) {
                sortValue = "roleId";
            }

            criteria.addOrder(searchCriteria.getSortDirection() == SortDirection.ASCENDING ? Order.asc(sortValue)
                    : Order.desc(sortValue));
        }

        return criteria.list();
    }

    @Override
    public int findAssignmentsCount(AssignmentSearchCriteria searchCriteria) {
        Criteria criteria = createAssigmentsSearchCriteria(searchCriteria);

        criteria.setProjection(Projections.rowCount());
        return ((Long) criteria.uniqueResult()).intValue();

    }

    private Criteria createAssigmentsSearchCriteria(AssignmentSearchCriteria searchCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Assignment.class);
        if (searchCriteria.getDepartmentId() != null) {
            criteria.add(Restrictions.eq("departmentId", searchCriteria.getDepartmentId()));
        }
        if (searchCriteria.getPersonId() != null) {
            criteria.add(Restrictions.eq("personId", searchCriteria.getPersonId()));
        }
        if (searchCriteria.getRoleId() != null) {
            criteria.add(Restrictions.eq("roleId", searchCriteria.getRoleId()));
        }
        if (searchCriteria.getTeamId() != null) {
            criteria.add(Restrictions.eq("teamId", searchCriteria.getTeamId()));
        }
        if (searchCriteria.getTradeNumberId() != null) {
            criteria.add(Restrictions.eq("tradeNumberId", searchCriteria.getTradeNumberId()));
        }
        return criteria;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
