/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db.volunteer;

import java.util.List;
import java.util.Set;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import uk.org.rbc1b.roms.controller.common.SortDirection;

/**
 *
 * @author rahulsingh
 */
@Repository
public class HibernateVolunteerDao implements VolunteerDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Volunteer findVolunteer(Integer volunteerId, Set<VolunteerData> data) {
        Volunteer volunteer = (Volunteer) this.sessionFactory.getCurrentSession().get(Volunteer.class, volunteerId);
        if (volunteer != null && CollectionUtils.isNotEmpty(data)) {
            if (data.contains(VolunteerData.SPOUSE)) {
                Hibernate.initialize(volunteer.getSpouse());
            }
            if (data.contains(VolunteerData.EMERGENCY_CONTACT)) {
                Hibernate.initialize(volunteer.getEmergencyContact());
            }
            if (data.contains(VolunteerData.TRADES)) {
                Hibernate.initialize(volunteer.getTrades());
            }
            if (data.contains(VolunteerData.INTERVIEWER)) {
                Hibernate.initialize(volunteer.getInterviewerA());
                Hibernate.initialize(volunteer.getInterviewerB());
            }
        }
        return volunteer;
    }

    @Override
    public List<Volunteer> findVolunteers(VolunteerSearchCriteria searchCriteria) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = createVolunteerSearchCriteria(searchCriteria, session);

        criteria.setFirstResult(searchCriteria.getStartIndex());
        criteria.setMaxResults(searchCriteria.getMaxResults());

        if (searchCriteria.getSortValue() != null) {
            criteria.addOrder(searchCriteria.getSortDirection() == SortDirection.ASCENDING
                    ? Order.asc(searchCriteria.getSortValue())
                    : Order.desc(searchCriteria.getSortValue()));
        }

        return criteria.list();

    }

    @Override
    public int findVolunteersCount(VolunteerSearchCriteria searchCriteria) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = createVolunteerSearchCriteria(searchCriteria, session);

        criteria.setProjection(Projections.rowCount());

        return ((Long) criteria.uniqueResult()).intValue();
    }

    private Criteria createVolunteerSearchCriteria(VolunteerSearchCriteria searchCriteria, Session session) {

        Criteria criteria = session.createCriteria(Volunteer.class);

        if (searchCriteria.getSearch() != null || "congregation.name".equals(searchCriteria.getSortValue())) {
            criteria.createAlias("congregation", "congregation", JoinType.LEFT_OUTER_JOIN);
        }

        if (searchCriteria.getSearch() != null) {
            String searchValue = "%" + searchCriteria.getSearch() + "%";

            criteria.add(Restrictions.or(Restrictions.like("forename", searchValue),
                    Restrictions.like("middleName", searchValue),
                    Restrictions.like("surname", searchValue),
                    Restrictions.like("email", searchValue),
                    Restrictions.like("congregation.name", searchValue)));
        }

        return criteria;
    }

    @Override
    public void saveVolunteer(Volunteer volunteer) {
        if (volunteer.getPersonId() == null) {
            this.sessionFactory.getCurrentSession().save(volunteer);
        } else {
            this.sessionFactory.getCurrentSession().merge(volunteer);
        }
    }

    @Override
    public List<Assignment> findAssignments(Integer volunteerId) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Assignment.class);
        criteria.add(Restrictions.eq("personId", volunteerId));
        criteria.addOrder(Order.asc("tradeNumberId"));

        return criteria.list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
