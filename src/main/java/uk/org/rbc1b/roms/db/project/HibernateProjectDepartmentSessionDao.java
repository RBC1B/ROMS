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
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ramindursingh
 */
@Repository
public class HibernateProjectDepartmentSessionDao implements ProjectDepartmentSessionDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Cacheable("projectDepartmentSession.projectDepartmentSession")
    public ProjectDepartmentSession findByProjectDepartmentSessionId(Integer projectDepartmentSessionId) {
        return (ProjectDepartmentSession) this.sessionFactory.getCurrentSession().get(ProjectDepartmentSession.class, projectDepartmentSessionId);
    }

    @Override
    public List<ProjectDepartmentSession> findProjectSessionsForDepartment(Integer projectId, Integer departmentId) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ProjectDepartmentSession.class);
        criteria.add(Restrictions.eq("project.projectId", projectId));
        criteria.add(Restrictions.eq("department.departmentId", departmentId));
        return criteria.list();
    }

    @Override
    public void save(ProjectDepartmentSession workSession) {
        this.sessionFactory.getCurrentSession().save(workSession);
    }
}
