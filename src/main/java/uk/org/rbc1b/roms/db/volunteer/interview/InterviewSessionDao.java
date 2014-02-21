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

import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;

/**
 * Access the volunteer interview session information.
 */
public interface InterviewSessionDao {

    /**
     * Get all interview sessions.
     * @return List of sessions
     */
    @Transactional(readOnly = true)
    List<InterviewSession> findInterviewSessions();

    /**
     * Get a single interview sessions.
     * @param interviewSessionId primary key
     * @return interview session
     */
    @Transactional(readOnly = true)
    InterviewSession findInterviewSession(Integer interviewSessionId);

    /**
     * Find the number of volunteers for each given status for each session.
     * @return map of interview status volunteer counts, keyed against the session id
     */
    @Transactional(readOnly = true)
    Map<Integer, Map<String, Integer>> findInterviewSessionVolunteerCounts();

    /**
     * Find the number of volunteers for each given status for a session.
     * @param interviewSessionId session id
     * @return map of interview status volunteer counts
     */
    @Transactional(readOnly = true)
    Map<String, Integer> findInterviewSessionVolunteerCounts(Integer interviewSessionId);

    /**
     * Find all the volunteers linked to the interview session.
     * @param interviewSessionId session id
     * @return list of {@code VolunteerInterviewSession} instances
     */
    @Transactional(readOnly = true)
    List<VolunteerInterviewSession> findVolunteerInterviewSessions(Integer interviewSessionId);

}
