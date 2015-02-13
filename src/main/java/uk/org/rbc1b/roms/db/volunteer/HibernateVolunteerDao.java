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

import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Repository;
import uk.org.rbc1b.roms.controller.common.DataConverterUtil;
import uk.org.rbc1b.roms.controller.common.SortDirection;
import uk.org.rbc1b.roms.db.volunteer.department.Assignment;
import uk.org.rbc1b.roms.db.volunteer.interview.VolunteerInterviewSession;
import uk.org.rbc1b.roms.db.volunteer.qualification.VolunteerQualification;
import uk.org.rbc1b.roms.db.volunteer.skill.VolunteerSkill;
import uk.org.rbc1b.roms.db.volunteer.trade.VolunteerTrade;
import uk.org.rbc1b.roms.db.volunteer.trade.VolunteerTradeSearchCriteria;
import com.google.common.collect.Lists;

/**
 * Hibernate Volunteer Data Access Object for Volunteer data.
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
        if (volunteer == null) {
            return null;
        }
        Hibernate.initialize(volunteer.getPerson());
        if (CollectionUtils.isNotEmpty(data)) {
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

    @SuppressWarnings("unchecked")
    @Override
    public List<Volunteer> findVolunteers(VolunteerSearchCriteria searchCriteria) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = createVolunteerSearchCriteria(searchCriteria, session);

        if (searchCriteria.getStartIndex() != null) {
            criteria.setFirstResult(searchCriteria.getStartIndex());
        }
        if (searchCriteria.getMaxResults() != null) {
            criteria.setMaxResults(searchCriteria.getMaxResults());
        }

        if (searchCriteria.getSortValue() != null) {
            criteria.addOrder(searchCriteria.getSortDirection() == SortDirection.ASCENDING ? Order.asc(searchCriteria
                    .getSortValue()) : Order.desc(searchCriteria.getSortValue()));
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
        criteria.createAlias("Person", "person");

        if (searchCriteria.getCongregationId() != null || searchCriteria.getKingdomHallId() != null) {
            criteria.createAlias("person.congregation", "congregation");

            if (searchCriteria.getKingdomHallId() != null) {
                criteria.createAlias("congregation.kingdomHall", "kingdomHall");
            }
        } else if (searchCriteria.getSearch() != null || "congregation.name".equals(searchCriteria.getSortValue())) {
            criteria.createAlias("person.congregation", "congregation", JoinType.LEFT_OUTER_JOIN);
        }

        if (searchCriteria.getSearch() != null) {
            String searchValue = "%" + searchCriteria.getSearch() + "%";

            criteria.add(Restrictions.or(Restrictions.like("person.forename", searchValue),
                    Restrictions.like("person.middleName", searchValue),
                    Restrictions.like("person.surname", searchValue), Restrictions.like("person.email", searchValue),
                    Restrictions.like("congregation.name", searchValue)));
        }

        // private String location;

        if (searchCriteria.getId() != null) {
            criteria.add(Restrictions.eq("personId", searchCriteria.getId()));
        }

        if (searchCriteria.getForename() != null) {
            criteria.add(Restrictions.like("person.forename", "%" + searchCriteria.getForename() + "%"));
        }

        if (searchCriteria.getSurname() != null) {
            criteria.add(Restrictions.like("person.surname", "%" + searchCriteria.getSurname() + "%"));
        }

        if (searchCriteria.getLocation() != null) {
            String searchValue = "%" + searchCriteria.getLocation() + "%";

            criteria.add(Restrictions.or(Restrictions.like("person.address.street", searchValue),
                    Restrictions.like("person.address.town", searchValue),
                    Restrictions.like("person.address.county", searchValue),
                    Restrictions.like("person.address.postcode", searchValue)));
        }

        if (searchCriteria.getCongregationId() != null) {
            criteria.add(Restrictions.eq("person.congregation.congregationId", searchCriteria.getCongregationId()));
        }

        if (searchCriteria.getKingdomHallId() != null) {
            criteria.add(Restrictions.eq("kingdomHall.kingdomHallId", searchCriteria.getKingdomHallId()));
        }

        if (searchCriteria.getSkillId() != null) {
            DetachedCriteria skillCriteria = DetachedCriteria.forClass(VolunteerSkill.class);
            skillCriteria.add(Restrictions.eq("skillId", searchCriteria.getSkillId()));
            skillCriteria.setProjection(Projections.property("personId"));

            criteria.add(Property.forName("personId").in(skillCriteria));
        }

        if (searchCriteria.getQualificationId() != null) {
            DetachedCriteria qualificationCriteria = DetachedCriteria.forClass(VolunteerQualification.class);
            qualificationCriteria.add(Restrictions.eq("qualificationId", searchCriteria.getQualificationId()));
            qualificationCriteria.setProjection(Projections.property("personId"));

            criteria.add(Property.forName("personId").in(qualificationCriteria));
        }

        if (searchCriteria.getDepartmentId() != null) {
            DetachedCriteria departmentCriteria = DetachedCriteria.forClass(Assignment.class);
            departmentCriteria.add(Restrictions.eq("departmentId", searchCriteria.getDepartmentId()));
            departmentCriteria.setProjection(Projections.property("person.personId"));

            criteria.add(Property.forName("personId").in(departmentCriteria));
        }

        if (searchCriteria.getInterviewSessionId() != null) {
            DetachedCriteria interviewCriteria = DetachedCriteria.forClass(VolunteerInterviewSession.class);
            interviewCriteria.add(Restrictions.eq("interviewSession.interviewSessionId",
                    searchCriteria.getInterviewSessionId()));
            interviewCriteria.setProjection(Projections.property("volunteer.personId"));

            criteria.add(Property.forName("personId").in(interviewCriteria));
        }
        criteria.add(Restrictions.not(Restrictions.in("rbcStatusCode", Lists.newArrayList("DN", "IA"))));

        return criteria;
    }

    @Override
    public void createVolunteer(Volunteer volunteer) {
        Session session = this.sessionFactory.getCurrentSession();
        if (volunteer.getPerson().getPersonId() != null) {
            session.merge(volunteer.getPerson());
        }

        session.save(volunteer);
    }

    @Override
    @CacheEvict(value = "person.person", key = "#volunteer.personId")
    public void updateVolunteer(Volunteer volunteer) {
        Session session = this.sessionFactory.getCurrentSession();
        session.merge(volunteer.getPerson());
        session.merge(volunteer);
    }

    @CacheEvict(value = "reference.qualification", key = "#volunteerQualification.volunteerQualificationId")
    @Override
    public void updateVolunteerQualification(VolunteerQualification volunteerQualification) {
        this.sessionFactory.getCurrentSession().merge(volunteerQualification);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Assignment> findAssignments(Integer volunteerId) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Assignment.class);
        criteria.add(Restrictions.eq("person.personId", volunteerId));
        criteria.addOrder(Order.asc("tradeNumberId"));

        return criteria.list();
    }

    @Override
    public Assignment findPrimaryAssignment(Integer volunteerId) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Assignment.class);
        criteria.add(Restrictions.eq("person.personId", volunteerId)).add(Restrictions.eq("tradeNumberId", 1));

        return (Assignment) criteria.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<VolunteerSkill> findSkills(Integer volunteerId) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(VolunteerSkill.class);
        criteria.add(Restrictions.eq("personId", volunteerId));
        criteria.addOrder(Order.desc("level"));

        return criteria.list();
    }

    @Override
    public VolunteerSkill findSkill(Integer volunteerSkillId) {
        return (VolunteerSkill) this.sessionFactory.getCurrentSession().get(VolunteerSkill.class, volunteerSkillId);
    }

    @Override
    public void deleteSkill(VolunteerSkill volunteerSkill) {
        this.sessionFactory.getCurrentSession().delete(volunteerSkill);
    }

    @Override
    public void updateSkill(VolunteerSkill volunteerSkill) {
        this.sessionFactory.getCurrentSession().merge(volunteerSkill);
    }

    @Override
    public void createSkill(VolunteerSkill volunteerSkill) {
        this.sessionFactory.getCurrentSession().save(volunteerSkill);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<VolunteerQualification> findQualifications(Integer volunteerId) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(VolunteerQualification.class);
        criteria.add(Restrictions.eq("personId", volunteerId));

        return criteria.list();
    }

    @Override
    public VolunteerQualification findQualification(Integer volunteerQualificationId) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(VolunteerQualification.class);
        criteria.add(Restrictions.eq("volunteerQualificationId", volunteerQualificationId));

        return (VolunteerQualification) criteria.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<VolunteerTrade> findVolunteerTrades(VolunteerTradeSearchCriteria searchCriteria) {

        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = createVolunteerTradeSearchCriteria(searchCriteria, session);

        if (searchCriteria.getStartIndex() != null && searchCriteria.getMaxResults() != null) {
            criteria.setFirstResult(searchCriteria.getStartIndex());
            criteria.setMaxResults(searchCriteria.getMaxResults());
        }

        if (searchCriteria.getSortValue() != null) {
            // we may need to join into the values of the sort column
            if (searchCriteria.getSearch() == null) {
                if (searchCriteria.getSortValue().startsWith("person")) {
                    criteria.createAlias("person", "person");
                }

                if (searchCriteria.getSortValue().startsWith("person.congregation")) {
                    criteria.createAlias("person.congregation", "congregation", JoinType.LEFT_OUTER_JOIN);
                }
            }

            String sortValue = searchCriteria.getSortValue();
            if (sortValue.equals("person.congregation.name")) {
                sortValue = "congregation.name";
            }

            criteria.addOrder(searchCriteria.getSortDirection() == SortDirection.ASCENDING ? Order.asc(sortValue)
                    : Order.desc(sortValue));
        }

        return criteria.list();
    }

    @Override
    public int findVolunteerTradesCount(VolunteerTradeSearchCriteria searchCriteria) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = createVolunteerTradeSearchCriteria(searchCriteria, session);

        criteria.setProjection(Projections.rowCount());

        return ((Long) criteria.uniqueResult()).intValue();
    }

    private Criteria createVolunteerTradeSearchCriteria(VolunteerTradeSearchCriteria searchCriteria, Session session) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(VolunteerTrade.class);

        if (searchCriteria.getSearch() != null) {
            criteria.createAlias("person", "person");
            criteria.createAlias("person.congregation", "congregation", JoinType.LEFT_OUTER_JOIN);

            String searchValue = "%" + searchCriteria.getSearch() + "%";

            criteria.add(Restrictions.or(Restrictions.like("name", searchValue),
                    Restrictions.like("experienceDescription", searchValue),
                    Restrictions.like("person.forename", searchValue),
                    Restrictions.like("person.surname", searchValue),
                    Restrictions.like("congregation.name", searchValue)));
        }

        return criteria;

    }

    @Override
    public void deleteVolunteerTrade(VolunteerTrade volunteerTrade) {
        this.sessionFactory.getCurrentSession().delete(volunteerTrade);

    }

    @Override
    public void createTrade(VolunteerTrade volunteerTrade) {
        this.sessionFactory.getCurrentSession().save(volunteerTrade);
    }

    @Override
    public void updateTrade(VolunteerTrade volunteerTrade) {
        this.sessionFactory.getCurrentSession().merge(volunteerTrade);
    }

    @Override
    public VolunteerTrade findTrade(Integer volunteerTradeId) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(VolunteerTrade.class);
        criteria.add(Restrictions.eq("volunteerTradeId", volunteerTradeId));
        return (VolunteerTrade) criteria.uniqueResult();
    }

    @Override
    public void deleteVolunteerQualification(VolunteerQualification volunteerQualification) {
        this.sessionFactory.getCurrentSession().delete(volunteerQualification);
    }

    @Override
    public void createQualification(VolunteerQualification volunteerQualification) {
        this.sessionFactory.getCurrentSession().save(volunteerQualification);
    }

    @Override
    public List<Volunteer> findVolunteersWhoNeedBiannualEmail(VolunteerSearchCriteria searchCriteria) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = createVolunteerSearchCriteria(searchCriteria, session);

        DateTime todayLocalDate = new DateTime();
        DateTime sixMonthsBehindLocalDate = todayLocalDate.minusMonths(6);

        Date todayDate = DataConverterUtil.toSqlDate(todayLocalDate);
        Date sixMonthsBehind = DataConverterUtil.toSqlDate(sixMonthsBehindLocalDate);

        criteria.add(Restrictions.and(
                Restrictions.or(Restrictions.isNull("updateContactDetailsEmailLastSent"), Restrictions.not(Restrictions
                        .between("updateContactDetailsEmailLastSent", sixMonthsBehind, todayDate))), Restrictions.or(
                        Restrictions.isNull("contactDetailsLastConfirmed"), Restrictions.not(Restrictions.between(
                                "contactDetailsLastConfirmed", sixMonthsBehind, todayDate)))));

        if (searchCriteria.getMaxResults() != null) {
            criteria.setMaxResults(searchCriteria.getMaxResults());
        }

        criteria.addOrder(Order.asc("person.personId"));

        return criteria.list();
    }

    @Override
    public List<Volunteer> findVolunteersWhoNeedToSubmitLDCForm(VolunteerSearchCriteria searchCriteria) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = createVolunteerSearchCriteria(searchCriteria, session);

        criteria.add(Restrictions.eq("submitNewLDCFormEmailSent", false));

        DateTime twoAndHalfYearsInPast = new DateTime();
        twoAndHalfYearsInPast = twoAndHalfYearsInPast.minusYears(2);
        twoAndHalfYearsInPast = twoAndHalfYearsInPast.minusMonths(6);

        Date twoAndHalfYearsDate = DataConverterUtil.toSqlDate(twoAndHalfYearsInPast);

        criteria.add(Restrictions.or(Restrictions.isNull("formDate"), Restrictions.le("formDate", twoAndHalfYearsDate)));

        if (searchCriteria.getMaxResults() != null) {
            criteria.setMaxResults(searchCriteria.getMaxResults());
        }

        criteria.addOrder(Order.asc("person.personId"));

        return criteria.list();
    }
}
