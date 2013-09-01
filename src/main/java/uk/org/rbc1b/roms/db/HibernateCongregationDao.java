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
package uk.org.rbc1b.roms.db;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

/**
 * Hibernate implementation of the congregation dao.
 * @author oliver.elder.esq
 */
@Repository
public class HibernateCongregationDao implements CongregationDao {

    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    @Cacheable("congregation.congregationList")
    public List<Congregation> findAllCongregations() {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Congregation.class);
        return criteria.addOrder(Order.asc("name")).list();
    }

    @Override
    @Cacheable("congregation.congregation")
    public Congregation findCongregation(Integer congregationId) {
        if (congregationId == null) {
            return null;
        }

        return (Congregation) this.sessionFactory.getCurrentSession().get(Congregation.class, congregationId);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Congregation> findCongregations(String name) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Congregation.class);
        criteria.add(Restrictions.like("name", "%" + name + "%"));
        return criteria.list();
    }

    @Override
    public void saveCongregation(Congregation congregation) {
        if (congregation.getCongregationId() == null) {
            sessionFactory.getCurrentSession().save(congregation);
        } else {
            sessionFactory.getCurrentSession().merge(congregation);
        }
    }

    @Override
    public void createCongregation(Congregation congregation) {
        sessionFactory.getCurrentSession().save(congregation);
    }

    @Override
    public void deleteCongregation(Congregation congregation) {
        sessionFactory.getCurrentSession().delete(congregation);
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
