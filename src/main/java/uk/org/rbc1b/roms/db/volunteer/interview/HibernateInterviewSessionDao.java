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
package uk.org.rbc1b.roms.db.volunteer.interview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import uk.org.rbc1b.roms.db.Person;
import uk.org.rbc1b.roms.db.volunteer.Volunteer;

/**
 * Hibernate implementation of the interview session dao.
 */
@Repository
public class HibernateInterviewSessionDao implements InterviewSessionDao {

    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    public List<InterviewSession> findInterviewSessions() {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(InterviewSession.class);
        return criteria.list();
    }

    @Override
    public InterviewSession findInterviewSession(Integer interviewSessionId) {
        return (InterviewSession) this.sessionFactory.getCurrentSession().get(InterviewSession.class,
                interviewSessionId);
    }

    @Override
    public void updateInterviewSession(InterviewSession interviewSession) {
        this.sessionFactory.getCurrentSession().merge(interviewSession);
    }

    @Override
    public Map<Integer, Map<String, Integer>> findInterviewSessionVolunteerCounts() {
        Map<Integer, Map<String, Integer>> sessionStatusCounts = new HashMap<Integer, Map<String, Integer>>();
        @SuppressWarnings("unchecked")
        List<Object[]> results = this.sessionFactory.getCurrentSession().getNamedQuery("findAllSessionCountsByStatus")
                .list();
        for (Object[] result : results) {
            Integer sessionId = (Integer) result[0];

            Map<String, Integer> statusCounts = sessionStatusCounts.get(sessionId);
            if (statusCounts == null) {
                statusCounts = new HashMap<String, Integer>();
                sessionStatusCounts.put(sessionId, statusCounts);
            }

            String statusCode = (String) result[1];
            Integer count = (Integer) result[2];
            statusCounts.put(statusCode, count);
        }
        return sessionStatusCounts;
    }

    @Override
    public Map<String, Integer> findInterviewSessionVolunteerCounts(Integer interviewSessionId) {
        @SuppressWarnings("unchecked")
        List<Object[]> results = this.sessionFactory.getCurrentSession().getNamedQuery("findSessionCountsByStatus")
                .setParameter("id", interviewSessionId).list();
        Map<String, Integer> statusCounts = new HashMap<String, Integer>();
        for (Object[] result : results) {
            String statusCode = (String) result[0];
            Integer count = (Integer) result[1];
            statusCounts.put(statusCode, count);
        }
        return statusCounts;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<VolunteerInterviewSession> findVolunteerInterviewSessions(Integer interviewSessionId) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(VolunteerInterviewSession.class);
        criteria.add(Restrictions.eq("interviewSession.interviewSessionId", interviewSessionId));
        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<VolunteerInterviewSession> findVolunteerInterviewSessionsByVolunteer(Integer volunteerId) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(VolunteerInterviewSession.class);
        criteria.add(Restrictions.eq("volunteer.personId", volunteerId));
        return criteria.list();
    }

    @Override
    public VolunteerInterviewSession findVolunteerInterviewSession(Integer volunteerInterviewSessionId) {
        return (VolunteerInterviewSession) this.sessionFactory.getCurrentSession().get(VolunteerInterviewSession.class,
                volunteerInterviewSessionId);
    }

    @Override
    public void updateVolunteerInterviewSession(VolunteerInterviewSession volunteerInterviewSession) {
        this.sessionFactory.getCurrentSession().merge(volunteerInterviewSession);
    }

    @Override
    public void addVolunteerInterviewSessions(Set<Integer> volunteerIds, Integer interviewSessionId) {
        Session session = this.sessionFactory.getCurrentSession();

        InterviewSession interviewSession = (InterviewSession) session.get(InterviewSession.class, interviewSessionId);

        for (Integer volunteerId : volunteerIds) {
            VolunteerInterviewSession volunteerInterviewSession = new VolunteerInterviewSession();
            volunteerInterviewSession.setInterviewSession(interviewSession);
            volunteerInterviewSession.setVolunteer((Volunteer) session.get(Volunteer.class, volunteerId));
            volunteerInterviewSession
                    .setVolunteerInterviewStatusCode(VolunteerInterviewSession.INVITED_INTERVIEW_STATUS_CODE);
            session.save(volunteerInterviewSession);
        }

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Volunteer> findInterviewSessionEligibleVolunteers() {

        // sooo... a Criteria becomes unreadable (and we don't need the flexibility),
        // the Hql attempts hit hibernate's "computer says no" wall,
        // and the sql doesn't know how to inner join the volunteer to the person. This will do.
        List<Object[]> results = this.sessionFactory.getCurrentSession().getNamedQuery("findSessionEligibleVolunteers")
                .list();

        List<Volunteer> volunteers = new ArrayList<Volunteer>(results.size());
        for (Object[] result : results) {
            Volunteer volunteer = (Volunteer) result[0];
            volunteer.setPerson((Person) result[1]);
            volunteers.add(volunteer);
        }
        return volunteers;
    }

}
