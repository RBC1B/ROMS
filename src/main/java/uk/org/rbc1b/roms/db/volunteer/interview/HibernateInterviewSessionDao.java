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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    public Map<Integer, Map<String, Integer>> findInterviewSessionVolunteerCounts() {
        Map<Integer, Map<String, Integer>> sessionStatusCounts = new HashMap<Integer, Map<String, Integer>>();
        @SuppressWarnings("unchecked")
        List<Object[]> results = this.sessionFactory.getCurrentSession().getNamedQuery("findSessionCountsByStatus")
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

}
