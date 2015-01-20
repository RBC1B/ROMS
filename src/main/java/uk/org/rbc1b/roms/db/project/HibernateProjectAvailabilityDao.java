/*
 * The MIT License
 *
 * Copyright 2014 RBC1B.
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

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ramindursingh
 */
@Repository
public class HibernateProjectAvailabilityDao implements ProjectAvailabilityDao {

    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    public List<ProjectAvailability> findUnnotifiedVolunteers() {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ProjectAvailability.class);
        criteria.add(Restrictions.eq("emailSent", Boolean.FALSE));
        return criteria.list();
    }

    @Override
    public void update(ProjectAvailability projectAvailability) {
        this.sessionFactory.getCurrentSession().merge(projectAvailability);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ProjectAvailability> findUnconfirmedVolunteers() {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ProjectAvailability.class);
        criteria.add(Restrictions.eq("personResponded", Boolean.TRUE));
        criteria.add(Restrictions.eq("overseerConfirmed", Boolean.TRUE));
        criteria.add(Restrictions.eq("confirmationEmail", Boolean.FALSE));

        return criteria.list();
    }

    @Override
    public ProjectAvailability findById(Integer id) {
        return (ProjectAvailability) this.sessionFactory.getCurrentSession().get(ProjectAvailability.class, id);
    }

    @Override
    public ProjectAvailability findVolunteerAvailabilityByWorkSession(Integer personId,
            ProjectDepartmentSession workSession) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ProjectAvailability.class);
        criteria.add(Restrictions.eq("projectDepartmentSession.projectDepartmentSessionId",
                workSession.getProjectDepartmentSessionId()));
        criteria.add(Restrictions.eq("person.personId", personId));
        return (ProjectAvailability) criteria.uniqueResult();
    }

    @Override
    public ProjectAvailability find(Integer personId, Integer projectDepartmentSessionId) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ProjectAvailability.class);
        criteria.add(Restrictions.eq("projectDepartmentSession.projectDepartmentSessionId", projectDepartmentSessionId));
        criteria.add(Restrictions.eq("person.personId", personId));
        return (ProjectAvailability) criteria.uniqueResult();
    }

    @Override
    public void delete(ProjectAvailability projectAvailability) {
        this.sessionFactory.getCurrentSession().delete(projectAvailability);
    }

    @Override
    public void save(ProjectAvailability projectAvailability) {
        this.sessionFactory.getCurrentSession().save(projectAvailability);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ProjectAvailability> findForDepartmentSession(Integer projectDepartmentSessionId) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ProjectAvailability.class);
        criteria.add(Restrictions.eq("projectDepartmentSession.projectDepartmentSessionId", projectDepartmentSessionId));
        return criteria.list();
    }
}
