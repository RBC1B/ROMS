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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

/**
 * @author Tina
 */
@Repository
public class HibernateQualificationDao implements QualificationDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Qualification findQualification(final String name) {
        return (Qualification) this.sessionFactory.getCurrentSession().get(Qualification.class, name);
    }

    @Override
    @Cacheable("qualification.qualification")
    public Qualification findQualification(final Integer qualificationId) {
        return (Qualification) this.sessionFactory.getCurrentSession().get(Qualification.class, qualificationId);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Qualification> findQualifications() {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Qualification.class);

        // TODO: support search criteria

        return criteria.list();
    }

    @Override
    public void saveQualification(Qualification qualification) {
        if (qualification.getQualificationId() == null) {
            this.sessionFactory.getCurrentSession().save(qualification);
        } else {
            this.sessionFactory.getCurrentSession().merge(qualification);
        }
    }

    @Override
    public void deleteQualification(Qualification qualification) {
        this.sessionFactory.getCurrentSession().delete(qualification);
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
