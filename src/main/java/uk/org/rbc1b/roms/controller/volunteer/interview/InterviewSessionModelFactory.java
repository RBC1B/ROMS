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

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.org.rbc1b.roms.controller.common.model.EntityModel;
import uk.org.rbc1b.roms.controller.common.model.PersonModelFactory;
import uk.org.rbc1b.roms.controller.kingdomhall.KingdomHallModelFactory;
import uk.org.rbc1b.roms.controller.volunteer.VolunteerModelFactory;
import uk.org.rbc1b.roms.db.Congregation;
import uk.org.rbc1b.roms.db.CongregationDao;
import uk.org.rbc1b.roms.db.Person;
import uk.org.rbc1b.roms.db.kingdomhall.KingdomHall;
import uk.org.rbc1b.roms.db.kingdomhall.KingdomHallDao;
import uk.org.rbc1b.roms.db.volunteer.Volunteer;
import uk.org.rbc1b.roms.db.volunteer.interview.InterviewSession;
import uk.org.rbc1b.roms.db.volunteer.interview.VolunteerInterviewSession;

/**
 * Create interview session models.
 */
@Component
public class InterviewSessionModelFactory {
    private static final String BASE_URI = "/interview-sessions/";

    @Autowired
    private KingdomHallDao kingdomHallDao;

    @Autowired
    private CongregationDao congregationDao;

    @Autowired
    private PersonModelFactory personModelFactory;

    /**
     * Generate the uri used to access the interview session pages.
     * @param interviewSessionId optional interview session id
     * @return uri
     */
    public static String generateUri(Integer interviewSessionId) {
        return interviewSessionId != null ? BASE_URI + interviewSessionId : BASE_URI;
    }

    /**
     * Simplistic time formatter, returning the time in HH:mm format.
     * @param time unformatted time, in HHmm format
     * @return formatted time
     */
    public static String formatDisplayTime(String time) {
        if (time == null) {
            return null;
        }
        return time.substring(0, 2) + ":" + time.substring(2);
    }

    /**
     * Simplistic time parser, returning the time in HHmm format.
     * @param time formatted time, in HH:mm format
     * @return formatted time
     */
    public static String parseDisplayTime(String time) {
        if (time == null) {
            return null;
        }
        if (time.length() < 5) {
            throw new IllegalArgumentException("Invalid time string [" + time + "]");
        }

        return time.substring(0, 2) + time.substring(3);
    }

    /**
     * Create an interview session model.
     * @param interviewSession session
     * @param volunteerStatusCounts map of volunteer counts, mapped by interview status code
     * @return model
     */
    public InterviewSessionModel generateInterviewSessionModel(InterviewSession interviewSession,
            Map<String, Integer> volunteerStatusCounts) {

        if (interviewSession == null) {
            return null;
        }

        InterviewSessionModel model = new InterviewSessionModel();
        model.setComments(interviewSession.getComments());
        model.setDate(interviewSession.getDate());
        model.setTime(formatDisplayTime(interviewSession.getTime()));
        model.setTodayOrInPast(interviewSession.isInPast() || interviewSession.isToday());

        if (interviewSession.getKingdomHall() != null && interviewSession.getKingdomHall().getKingdomHallId() != null) {
            KingdomHall kingdomHall = kingdomHallDao.findKingdomHall(interviewSession.getKingdomHall()
                    .getKingdomHallId());
            EntityModel kingdomHallModel = new EntityModel();
            kingdomHallModel.setId(kingdomHall.getKingdomHallId());
            kingdomHallModel.setName(kingdomHall.getName());
            kingdomHallModel.setUri(KingdomHallModelFactory.generateUri(kingdomHall.getKingdomHallId()));
            model.setKingdomHall(kingdomHallModel);
        }

        model.setUri(generateUri(interviewSession.getInterviewSessionId()));
        model.setEditUri(generateUri(interviewSession.getInterviewSessionId()) + "/edit");

        if (!interviewSession.isInPast()) {
            model.setInvitationsUri(InterviewSessionModelFactory.generateUri(interviewSession.getInterviewSessionId())
                    + "/invitations");
        }

        if (volunteerStatusCounts != null) {
            int invitedCount = 0;
            int confirmedCount = 0;
            int declinedCount = 0;

            for (Map.Entry<String, Integer> entry : volunteerStatusCounts.entrySet()) {
                invitedCount += entry.getValue();
                if (entry.getKey().equals(VolunteerInterviewSession.CONFIRMED_INTERVIEW_STATUS_CODE)
                        || entry.getKey().equals(VolunteerInterviewSession.COMPLETED_INTERVIEW_STATUS_CODE)) {
                    confirmedCount += entry.getValue();
                } else if (!entry.getKey().equals(VolunteerInterviewSession.INVITED_INTERVIEW_STATUS_CODE)) {
                    declinedCount += entry.getValue();
                }
            }
            model.setInvitedVolunteerCount(invitedCount);
            model.setConfirmedVolunteerCount(confirmedCount);
            model.setDeclinedVolunteerCount(declinedCount);
        }

        return model;
    }

    /**
     * Generate the model for a volunteer associated with an interview session.
     * @param volunteer volunteer
     * @param volunteerInterviewSession mapped session, optional
     * @param interviewStatusValues reference data
     * @param rbcSubRegionValues reference data
     * @return model
     */
    public VolunteerInterviewSessionModel generateVolunteerInterviewSessionModel(Volunteer volunteer,
            VolunteerInterviewSession volunteerInterviewSession, Map<String, String> interviewStatusValues,
            Map<Integer, String> rbcSubRegionValues) {
        VolunteerInterviewSessionModel model = new VolunteerInterviewSessionModel();

        Person person = volunteer.getPerson();
        if (person.getCongregation() != null) {
            Congregation congregation = congregationDao.findCongregation(person.getCongregation().getCongregationId());

            model.setCongregation(personModelFactory.generateCongregationModel(congregation));
            if (congregation.getRbcRegionId() != null) {
                model.setRbcSubRegion(rbcSubRegionValues.get(congregation.getRbcSubRegionId()));
            }
        }
        model.setId(person.getPersonId());
        model.setForename(person.getForename());
        model.setSurname(person.getSurname());
        model.setUri(generateUri(volunteerInterviewSession.getInterviewSession().getInterviewSessionId())
                + "/invitations/" + volunteerInterviewSession.getVolunteerInterviewSessionId());
        model.setVolunteerUri(VolunteerModelFactory.generateUri(person.getPersonId()));
        model.setVolunteerEditRbcStatusUri(VolunteerModelFactory.generateUri(person.getPersonId()) + "/rbc-status/edit");

        if (volunteerInterviewSession != null) {
            model.setComments(volunteerInterviewSession.getComments());
            model.setInterviewStatus(interviewStatusValues.get(volunteerInterviewSession
                    .getVolunteerInterviewStatusCode()));
            model.setInterviewStatusCode(volunteerInterviewSession.getVolunteerInterviewStatusCode());
        }

        return model;
    }

}
