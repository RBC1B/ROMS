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

import java.util.Comparator;
import java.util.List;
import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import uk.org.rbc1b.roms.db.common.MergeUtil;

/**
 * Hibernate implementation of the congregation dao.
 */
@Repository
public class HibernateCongregationDao implements CongregationDao {

    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    public List<Congregation> findAllCongregations() {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Congregation.class);
        return criteria.addOrder(Order.asc("name")).list();
    }

    @Override
    @Cacheable(value = "congregation.congregation", key = "#congregationId")
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

    @SuppressWarnings("unchecked")
    @Override
    public List<Congregation> findCongregations(CongregationSearchCriteria searchCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Congregation.class);

        if (searchCriteria.getKingdomHallId() != null) {

            criteria.add(Restrictions.eq("kingdomHall.kingdomHallId", searchCriteria.getKingdomHallId()));
        }
        return criteria.list();
    }

    @CacheEvict(value = "congregation.congregation", key = "#congregation.congregationId")
    @Override
    public void updateCongregation(final Congregation congregation) {

        final Session session = this.sessionFactory.getCurrentSession();

        Congregation existing = (Congregation) session.get(Congregation.class, congregation.getCongregationId());

        // merge the contacts
        MergeUtil.sortAndMerge(existing.getContacts(), congregation.getContacts(),
                new Comparator<CongregationContact>() {
                    @Override
                    public int compare(CongregationContact o1, CongregationContact o2) {
                        return o1.getCongregationRoleCode().compareTo(o2.getCongregationRoleCode());
                    }
                }, new MergeUtil.Callback<CongregationContact, CongregationContact>() {
                    @Override
                    public void output(CongregationContact existingContact, CongregationContact incomingContact) {
                        if (existingContact == null) {
                            session.save(incomingContact);
                        } else if (incomingContact == null) {
                            session.delete(existingContact);
                        } else if (!ObjectUtils.equals(existingContact.getPerson().getPersonId(), incomingContact
                                .getPerson().getPersonId())) {
                            existingContact.setPerson(incomingContact.getPerson());
                            session.update(existingContact);
                        }
                    }
                });

        sessionFactory.getCurrentSession().merge(congregation);
    }

    @Override
    public void createCongregation(Congregation congregation) {
        Session session = this.sessionFactory.getCurrentSession();

        session.save(congregation);

        for (CongregationContact contact : congregation.getContacts()) {
            contact.setCongregation(congregation);
            session.save(contact);
        }
    }

}
