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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import uk.org.rbc1b.roms.controller.common.DataConverterUtil;
import uk.org.rbc1b.roms.controller.common.model.PersonModelFactory;
import uk.org.rbc1b.roms.db.Congregation;
import uk.org.rbc1b.roms.db.CongregationContact;
import uk.org.rbc1b.roms.db.CongregationDao;
import uk.org.rbc1b.roms.db.Person;
import uk.org.rbc1b.roms.db.PersonDao;
import uk.org.rbc1b.roms.db.application.User;
import uk.org.rbc1b.roms.db.application.UserDao;
import uk.org.rbc1b.roms.db.email.Email;
import uk.org.rbc1b.roms.db.email.EmailDao;
import uk.org.rbc1b.roms.db.kingdomhall.KingdomHall;
import uk.org.rbc1b.roms.db.kingdomhall.KingdomHallDao;
import uk.org.rbc1b.roms.db.reference.ReferenceDao;
import uk.org.rbc1b.roms.db.volunteer.Volunteer;
import uk.org.rbc1b.roms.db.volunteer.VolunteerDao;
import uk.org.rbc1b.roms.db.volunteer.VolunteerSearchCriteria;
import uk.org.rbc1b.roms.db.volunteer.interview.InterviewSession;
import uk.org.rbc1b.roms.db.volunteer.interview.InterviewSessionDao;
import uk.org.rbc1b.roms.db.volunteer.interview.VolunteerInterviewSession;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;

/**
 * Handler the volunteer interview session and invitations.
 */
@Controller
@RequestMapping(value = "/interview-sessions")
public class InterviewSessionsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(InterviewSessionsController.class);
    private static final String INVITATION_EMAIL_TEMPLATE = "volunteer-interview-session-invitation.ftl";
    private static final String INVITATION_EMAIL_SUBJECT = "RBC induction interview";
    private static final Logger LOG = LoggerFactory.getLogger(InterviewSessionsController.class);
    @Autowired
    private InterviewSessionDao interviewSessionDao;
    @Autowired
    private InterviewSessionModelFactory interviewSessionModelFactory;
    @Autowired
    private VolunteerDao volunteerDao;
    @Autowired
    private CongregationDao congregationDao;
    @Autowired
    private PersonModelFactory personModelFactory;
    @Autowired
    private ReferenceDao referenceDao;
    @Autowired
    private KingdomHallDao kingdomHallDao;
    @Autowired
    private FreeMarkerConfigurer emailFreemarkerConfigurer;
    @Autowired
    private EmailDao emailDao;
    @Autowired
    private PersonDao personDao;
    @Autowired
    private UserDao userDao;

    /**
     * Display a list of volunteer interview sessions.
     *
     * @param model mvc model
     * @return view
     */
    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasPermission('VOLUNTEER', 'READ')")
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
        return "volunteers/interview-sessions/list";
    }

    /**
     * Show an individual interview session details.
     *
     * @param interviewSessionId id
     * @param model model
     * @return view name
     * @throws NoSuchRequestHandlingMethodException on failure to find the
     * interview session
     */
    @RequestMapping(value = "{interviewSessionId}", method = RequestMethod.GET)
    @PreAuthorize("hasPermission('VOLUNTEER', 'READ')")
    public String showInterviewSession(@PathVariable Integer interviewSessionId, ModelMap model)
            throws NoSuchRequestHandlingMethodException {
        InterviewSession session = interviewSessionDao.findInterviewSession(interviewSessionId);
        if (session == null) {
            throw new NoSuchRequestHandlingMethodException("No session with id [" + interviewSessionId + "]",
                    this.getClass());
        }

        Map<String, Integer> sessionVolunteerCounts = interviewSessionDao
                .findInterviewSessionVolunteerCounts(interviewSessionId);

        InterviewSessionModel sessionModel = interviewSessionModelFactory.generateInterviewSessionModel(session,
                sessionVolunteerCounts);

        model.addAttribute("interviewSession", sessionModel);
        model.addAttribute("volunteers", generateVolunterList(interviewSessionId));
        model.addAttribute("interviewStatusValues", referenceDao.findVolunteerInterviewStatusValues());
        model.addAttribute("listUri", InterviewSessionModelFactory.generateUri(null));
        return "volunteers/interview-sessions/show";
    }

    private List<VolunteerInterviewSessionModel> generateVolunterList(Integer interviewSessionId) {

        List<VolunteerInterviewSession> volunteerInterviewSessions = interviewSessionDao
                .findVolunteerInterviewSessions(interviewSessionId);
        if (volunteerInterviewSessions.isEmpty()) {
            return Collections.emptyList();
        }

        VolunteerSearchCriteria volunteerSearchCriteria = new VolunteerSearchCriteria();
        volunteerSearchCriteria.setInterviewSessionId(interviewSessionId);
        List<Volunteer> volunteers = volunteerDao.findVolunteers(volunteerSearchCriteria);

        Map<Integer, Volunteer> volunteerMap = new HashMap<Integer, Volunteer>();
        for (Volunteer volunteer : volunteers) {
            volunteerMap.put(volunteer.getPersonId(), volunteer);
        }

        Map<String, String> interviewStatusValues = referenceDao.findVolunteerInterviewStatusValues();
        Map<Integer, String> rbcSubRegionValues = referenceDao.findRbcSubRegionValues();

        List<VolunteerInterviewSessionModel> modelList = new ArrayList<VolunteerInterviewSessionModel>();
        for (VolunteerInterviewSession volunteerInterviewSession : volunteerInterviewSessions) {
            Volunteer volunteer = volunteerMap.get(volunteerInterviewSession.getVolunteer().getPersonId());
            if (volunteer == null) {
                LOG.error("Failed to look up Volunteer #{}, linked to InterviewSession #{}", volunteerInterviewSession
                        .getVolunteer().getPersonId(), interviewSessionId);
                continue;
            }

            modelList.add(interviewSessionModelFactory.generateVolunteerInterviewSessionModel(volunteer,
                    volunteerInterviewSession, interviewStatusValues, rbcSubRegionValues));
        }
        return modelList;
    }

    /**
     * Show the list of those who may be invited to the interview session.
     *
     * @param interviewSessionId session id
     * @param model model
     * @return view name
     * @throws NoSuchRequestHandlingMethodException on failure to find the
     * session
     */
    @RequestMapping(value = "{interviewSessionId}/invitations", method = RequestMethod.GET)
    @PreAuthorize("hasPermission('VOLUNTEER', 'READ')")
    public String showInvitationList(@PathVariable Integer interviewSessionId, ModelMap model)
            throws NoSuchRequestHandlingMethodException {

        InterviewSession session = interviewSessionDao.findInterviewSession(interviewSessionId);
        if (session == null) {
            throw new NoSuchRequestHandlingMethodException("No session with id [" + interviewSessionId + "]",
                    this.getClass());
        }

        // we can't invite more people if the session has already happened
        if (session.isInPast()) {
            return "redirect:" + InterviewSessionModelFactory.generateUri(interviewSessionId);
        }

        Map<String, Integer> sessionVolunteerCounts = interviewSessionDao
                .findInterviewSessionVolunteerCounts(interviewSessionId);

        InterviewSessionModel sessionModel = interviewSessionModelFactory.generateInterviewSessionModel(session,
                sessionVolunteerCounts);

        List<Volunteer> volunteers = interviewSessionDao.findInterviewSessionEligibleVolunteers();
        Map<String, String> interviewStatusValues = referenceDao.findVolunteerInterviewStatusValues();
        Map<Integer, String> rbcSubRegionValues = referenceDao.findRbcSubRegionValues();

        List<VolunteerInterviewSessionModel> volunteerModeList = new ArrayList<VolunteerInterviewSessionModel>();
        for (Volunteer volunteer : volunteers) {
            volunteerModeList.add(interviewSessionModelFactory.generateVolunteerInterviewSessionModel(volunteer, null,
                    interviewStatusValues, rbcSubRegionValues));
        }

        model.addAttribute("interviewSession", sessionModel);
        model.addAttribute("volunteers", volunteerModeList);
        model.addAttribute("listUri", InterviewSessionModelFactory.generateUri(null));
        model.addAttribute("viewUri", InterviewSessionModelFactory.generateUri(interviewSessionId));
        return "volunteers/interview-sessions/invitations";
    }

    /**
     * Submit a list of volunteers to be invited to the session.
     *
     * @param interviewSessionId session id
     * @param volunteerIdsParam volunteer ids to be invited
     * @return redirect
     * @throws NoSuchRequestHandlingMethodException on failure to find the
     * session
     */
    @RequestMapping(value = "{interviewSessionId}/invitations", method = RequestMethod.POST)
    @PreAuthorize("hasPermission('VOLUNTEER', 'READ')")
    public String submitInvitationList(@PathVariable Integer interviewSessionId,
            @RequestParam(value = "volunteerIds") String volunteerIdsParam) throws NoSuchRequestHandlingMethodException {

        Set<Integer> volunteerIds = new HashSet<Integer>();
        for (String volunteerId : volunteerIdsParam.split(",")) {
            volunteerIds.add(DataConverterUtil.toInteger(volunteerId));
        }

        InterviewSession session = interviewSessionDao.findInterviewSession(interviewSessionId);
        if (session == null) {
            throw new NoSuchRequestHandlingMethodException("No session with id [" + interviewSessionId + "]",
                    this.getClass());
        }

        // we can't invite more people if the session has already happened
        if (session.isInPast()) {
            throw new IllegalStateException("Interview session #" + interviewSessionId + " is in the past.");
        }

        if (session.getKingdomHall() == null || session.getKingdomHall().getKingdomHallId() == null) {
            throw new IllegalStateException("Interview session #" + interviewSessionId
                    + " does not have a kingdom hall defined.");
        }

        List<VolunteerInterviewSession> existingInterviewSessions = interviewSessionDao
                .findVolunteerInterviewSessions(interviewSessionId);

        Set<Integer> existingVolunteerIds = new HashSet<Integer>();
        for (VolunteerInterviewSession existingInterviewSession : existingInterviewSessions) {
            existingVolunteerIds.add(existingInterviewSession.getVolunteer().getPersonId());
        }

        volunteerIds.removeAll(existingVolunteerIds);

        interviewSessionDao.addVolunteerInterviewSessions(volunteerIds, interviewSessionId);

        KingdomHall kingdomHall = kingdomHallDao.findKingdomHall(session.getKingdomHall().getKingdomHallId());

        for (Integer volunteerId : volunteerIds) {
            scheduleInterviewInvitationEmail(session, kingdomHall, volunteerId);
        }

        return "redirect:" + InterviewSessionModelFactory.generateUri(interviewSessionId);

    }

    private void scheduleInterviewInvitationEmail(InterviewSession session, KingdomHall kingdomHall, Integer volunteerId) {

        Volunteer volunteer = volunteerDao.findVolunteer(volunteerId, null);
        if (volunteer.getPerson().getEmail() == null) {
            LOGGER.error("Volunteer #{} does not have an email address defined", volunteer.getPersonId());
            return;
        }

        Person me = getMyDetails();

        Configuration conf = emailFreemarkerConfigurer.getConfiguration();

        Map<String, Object> model = new HashMap<String, Object>();

        model.put("sessionDate", session.getDate());
        model.put("sessionTime", InterviewSessionModelFactory.formatDisplayTime(session.getTime()));
        model.put("kingdomHallName", kingdomHall.getName());
        model.put("kingdomHallAddress", kingdomHall.getAddress());
        model.put("volunteer", volunteer);
        model.put("person", me);

        Congregation congregation = null;
        if (volunteer.getPerson().getCongregation() != null
                && volunteer.getPerson().getCongregation().getCongregationId() != null) {
            congregation = congregationDao
                    .findCongregation(volunteer.getPerson().getCongregation().getCongregationId());
        }

        Person coordinator = null;
        if (congregation != null) {
            model.put("volunteerCongregationName", congregation.getName());

            CongregationContact coordinatorContact = congregation.findContact(CongregationContact.COORDINATOR_ROLE);
            if (coordinatorContact != null) {
                coordinator = personDao.findPerson(coordinatorContact.getPerson().getPersonId());
            }
        }

        try {
            String text = FreeMarkerTemplateUtils.processTemplateIntoString(
                    conf.getTemplate(INVITATION_EMAIL_TEMPLATE), model);
            // send one email to the volunteer
            Email email = new Email();
            email.setRecipient(volunteer.getPerson().getEmail());

            if (coordinator != null && coordinator.getEmail() != null) {
                email.setCc(coordinator.getEmail());
            }
            email.setReplyTo(me.getEmail());
            email.setSubject(INVITATION_EMAIL_SUBJECT);
            email.setText(text);
            emailDao.save(email);
        } catch (IOException e) {
            LOGGER.error("Failed to volunteer interview invitation email", e);
        } catch (TemplateException e) {
            LOGGER.error("Failed to volunteer interview invitation email", e);
        }

    }

    /**
     * Update information about an individual volunteer invitation.
     *
     * @param interviewSessionId session id
     * @param volunteerInterviewSessionId volunteer invitation id
     * @param interviewStatusCode updated status code
     * @param comments comments
     * @throws NoSuchRequestHandlingMethodException on failure to look up the
     * session or invitation
     */
    @RequestMapping(value = "{interviewSessionId}/invitations/{volunteerInterviewSessionId}", method = RequestMethod.PUT)
    @PreAuthorize("hasPermission('VOLUNTEER', 'EDIT')")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateVolunteerInvitation(@PathVariable Integer interviewSessionId,
            @PathVariable Integer volunteerInterviewSessionId, @RequestParam String interviewStatusCode,
            @RequestParam String comments) throws NoSuchRequestHandlingMethodException {

        InterviewSession session = interviewSessionDao.findInterviewSession(interviewSessionId);
        if (session == null) {
            throw new NoSuchRequestHandlingMethodException("No session with id [" + interviewSessionId + "]",
                    this.getClass());
        }

        VolunteerInterviewSession volunteerInterviewSession = interviewSessionDao
                .findVolunteerInterviewSession(volunteerInterviewSessionId);
        if (volunteerInterviewSession == null) {
            throw new NoSuchRequestHandlingMethodException("No volunteer interview session with id ["
                    + volunteerInterviewSessionId + "]", this.getClass());
        }

        if (!volunteerInterviewSession.getInterviewSession().getInterviewSessionId().equals(interviewSessionId)) {
            throw new NoSuchRequestHandlingMethodException("Volunteer interview session #"
                    + volunteerInterviewSessionId + " is not linked ot interview session #" + interviewSessionId,
                    this.getClass());
        }

        volunteerInterviewSession.setComments(comments);
        volunteerInterviewSession.setVolunteerInterviewStatusCode(interviewStatusCode);

        interviewSessionDao.updateVolunteerInterviewSession(volunteerInterviewSession);
    }

    /**
     * Show form to create a new interview session.
     *
     * @param model model
     * @return view
     */
    @RequestMapping(value = "new", method = RequestMethod.GET)
    @PreAuthorize("hasPermission('VOLUNTEER', 'EDIT')")
    public String showEditInterviewSessionForm(ModelMap model) {

        InterviewSessionForm form = new InterviewSessionForm();

        model.addAttribute("interviewSessionForm", form);
        model.addAttribute("listUri", InterviewSessionModelFactory.generateUri(null));
        model.addAttribute("submitUri", InterviewSessionModelFactory.generateUri(null));
        model.addAttribute("submitMethod", "POST");
        return "volunteers/interview-sessions/edit";

    }

    /**
     * Create a new session.
     *
     * @param interviewSessionForm updated session data
     * @return redirect
     */
    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasPermission('VOLUNTEER', 'EDIT')")
    public String createInterviewSession(@Valid InterviewSessionForm interviewSessionForm) {
        InterviewSession session = new InterviewSession();

        session.setComments(interviewSessionForm.getComments());
        session.setDate(DataConverterUtil.toSqlDate(interviewSessionForm.getDate()));
        session.setKingdomHall(kingdomHallDao.findKingdomHall(interviewSessionForm.getKingdomHallId()));
        session.setTime(InterviewSessionModelFactory.parseDisplayTime(interviewSessionForm.getTime()));

        interviewSessionDao.createInterviewSession(session);

        return "redirect:" + InterviewSessionModelFactory.generateUri(session.getInterviewSessionId());

    }

    /**
     * @param interviewSessionId primary key
     * @param model model
     * @return view
     * @throws NoSuchRequestHandlingMethodException on failure to find the
     * session
     */
    @RequestMapping(value = "{interviewSessionId}/edit", method = RequestMethod.GET)
    @PreAuthorize("hasPermission('VOLUNTEER', 'EDIT')")
    public String showEditInterviewSessionForm(@PathVariable Integer interviewSessionId, ModelMap model)
            throws NoSuchRequestHandlingMethodException {
        InterviewSession session = interviewSessionDao.findInterviewSession(interviewSessionId);
        if (session == null) {
            throw new NoSuchRequestHandlingMethodException("No session with id [" + interviewSessionId + "]",
                    this.getClass());
        }

        InterviewSessionForm form = new InterviewSessionForm();
        form.setComments(session.getComments());
        form.setDate(DataConverterUtil.toDateTime(session.getDate()));
        form.setTime(InterviewSessionModelFactory.formatDisplayTime(session.getTime()));

        if (session.getKingdomHall() != null && session.getKingdomHall().getKingdomHallId() != null) {
            KingdomHall kingdomHall = kingdomHallDao.findKingdomHall(session.getKingdomHall().getKingdomHallId());
            form.setKingdomHallId(kingdomHall.getKingdomHallId());
            form.setKingdomHallName(kingdomHall.getName());
        }

        model.addAttribute("interviewSessionForm", form);
        model.addAttribute("listUri", InterviewSessionModelFactory.generateUri(null));
        model.addAttribute("submitUri", InterviewSessionModelFactory.generateUri(interviewSessionId));
        model.addAttribute("submitMethod", "PUT");
        return "volunteers/interview-sessions/edit";

    }

    /**
     * Update an existing session.
     *
     * @param interviewSessionId session id
     * @param interviewSessionForm updated session data
     * @return redirect
     * @throws NoSuchRequestHandlingMethodException on failure to find the
     * session
     */
    @RequestMapping(value = "{interviewSessionId}", method = RequestMethod.PUT)
    @PreAuthorize("hasPermission('VOLUNTEER', 'EDIT')")
    public String updateInterviewSession(@PathVariable Integer interviewSessionId,
            @Valid InterviewSessionForm interviewSessionForm) throws NoSuchRequestHandlingMethodException {
        InterviewSession session = interviewSessionDao.findInterviewSession(interviewSessionId);
        if (session == null) {
            throw new NoSuchRequestHandlingMethodException("No session with id [" + interviewSessionId + "]",
                    this.getClass());
        }

        session.setComments(interviewSessionForm.getComments());
        session.setDate(DataConverterUtil.toSqlDate(interviewSessionForm.getDate()));
        session.setKingdomHall(kingdomHallDao.findKingdomHall(interviewSessionForm.getKingdomHallId()));
        session.setTime(InterviewSessionModelFactory.parseDisplayTime(interviewSessionForm.getTime()));

        interviewSessionDao.updateInterviewSession(session);

        return "redirect:" + InterviewSessionModelFactory.generateUri(interviewSessionId);

    }

    /**
     * Gets the current user's logged in person object.
     *
     * @return person the user's person object
     */
    private Person getMyDetails() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String myUserName = userDetails.getUsername();
        User me = userDao.findUserAndPermissions(myUserName);
        Person myDetails = personDao.findPerson(me.getPersonId());
        return myDetails;
    }
}
