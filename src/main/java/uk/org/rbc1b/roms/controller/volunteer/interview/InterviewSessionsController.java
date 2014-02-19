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
package uk.org.rbc1b.roms.controller.volunteer.interview;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uk.org.rbc1b.roms.db.volunteer.interview.InterviewSession;
import uk.org.rbc1b.roms.db.volunteer.interview.InterviewSessionDao;

/**
 * Handler the volunteer interview session and invitations.
 */
@Controller
@RequestMapping(value = "/interview-sessions")
public class InterviewSessionsController {

    @Autowired
    private InterviewSessionDao interviewSessionDao;

    @Autowired
    private InterviewSessionModelFactory interviewSessionModelFactory;

    /**
     * Display a list of volunteer interview sessions.
     *
     * @param model mvc model
     * @return view
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showInterviewSessionList(ModelMap model) {

        List<InterviewSession> sessions = interviewSessionDao.findInterviewSessions();
        Map<Integer, Map<String, Integer>> sessionVolunteerCounts = interviewSessionDao
                .findInterviewSessionVolunteerCounts();

        List<InterviewSessionModel> modelList = new ArrayList<InterviewSessionModel>(sessions.size());
        for (InterviewSession session : sessions) {
            modelList.add(interviewSessionModelFactory.generateInterviewSessionModel(session,
                    sessionVolunteerCounts.get(session.getInterviewSessionId())));
        }

        model.addAttribute("interviewSessions", modelList);
        model.addAttribute("newUri", InterviewSessionModelFactory.generateUri(null) + "new");
        return "volunteers/interview-sessions-list";
    }
}
