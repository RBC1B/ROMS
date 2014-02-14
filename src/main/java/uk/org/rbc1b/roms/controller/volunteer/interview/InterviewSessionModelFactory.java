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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.org.rbc1b.roms.controller.common.model.EntityModel;
import uk.org.rbc1b.roms.controller.kingdomhall.KingdomHallModelFactory;
import uk.org.rbc1b.roms.db.kingdomhall.KingdomHall;
import uk.org.rbc1b.roms.db.kingdomhall.KingdomHallDao;
import uk.org.rbc1b.roms.db.volunteer.interview.InterviewSession;

/**
 * Create interview session models.
 */
@Component
public class InterviewSessionModelFactory {
    private static final String BASE_URI = "/interview-sessions/";

    @Autowired
    private KingdomHallDao kingdomHallDao;

    /**
     * Generate the uri used to access the interview session pages.
     * @param interviewSessionId optional interview session id
     * @return uri
     */
    public static String generateUri(Integer interviewSessionId) {
        return interviewSessionId != null ? BASE_URI + interviewSessionId : BASE_URI;
    }

    /**
     * Create an interview session model.
     * @param interviewSession session
     * @return model
     */
    public InterviewSessionModel generateInterviewSessionModel(InterviewSession interviewSession) {

        if (interviewSession == null) {
            return null;
        }

        InterviewSessionModel model = new InterviewSessionModel();
        model.setComments(interviewSession.getComments());
        model.setDate(interviewSession.getDate());
        model.setTime(interviewSession.getTime());

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

        return model;
    }
}
