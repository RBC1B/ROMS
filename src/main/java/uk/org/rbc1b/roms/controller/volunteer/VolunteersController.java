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
package uk.org.rbc1b.roms.controller.volunteer;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;
import uk.org.rbc1b.roms.controller.common.DataConverterUtil;
import uk.org.rbc1b.roms.controller.common.PhoneNumberFormatter;
import uk.org.rbc1b.roms.controller.common.datatable.AjaxDataTableResult;
import uk.org.rbc1b.roms.controller.common.model.PersonModelFactory;
import uk.org.rbc1b.roms.db.Address;
import uk.org.rbc1b.roms.db.Congregation;
import uk.org.rbc1b.roms.db.CongregationDao;
import uk.org.rbc1b.roms.db.Person;
import uk.org.rbc1b.roms.db.PersonDao;
import uk.org.rbc1b.roms.db.application.User;
import uk.org.rbc1b.roms.db.reference.ReferenceDao;
import uk.org.rbc1b.roms.db.volunteer.Volunteer;
import uk.org.rbc1b.roms.db.volunteer.VolunteerDao;
import uk.org.rbc1b.roms.db.volunteer.VolunteerDao.VolunteerData;
import uk.org.rbc1b.roms.db.volunteer.VolunteerSearchCriteria;
import uk.org.rbc1b.roms.db.volunteer.department.Assignment;
import uk.org.rbc1b.roms.db.volunteer.department.DepartmentDao;
import uk.org.rbc1b.roms.db.volunteer.interview.InterviewSession;
import uk.org.rbc1b.roms.db.volunteer.interview.InterviewSessionDao;
import uk.org.rbc1b.roms.db.volunteer.interview.VolunteerInterviewSession;
import uk.org.rbc1b.roms.db.volunteer.qualification.VolunteerQualification;
import uk.org.rbc1b.roms.db.volunteer.skill.VolunteerSkill;
import uk.org.rbc1b.roms.db.volunteer.trade.VolunteerTrade;

/**
 * @author rahulsingh
 */
@Controller
@RequestMapping("/volunteers")
public class VolunteersController {

    private static final String MARRIED_MARITAL_STATUS = "MR";
    private static final String RBC_STATUS_PENDING = "PD";
    private static final String FULLTIME_REGULAR_PIONEER = "RP";
    private static final String APPOINTMENT_ELDER = "EL";
    private static final String APPOINTMENT_MINISTERIAL_SERVANT = "MS";
    private static final String VOLUNTEER_IMAGE_DIRECTORY_KEY = "volunteer.images.directory";
    private static final Set<VolunteerData> VOLUNTEER_DATA = EnumSet.of(VolunteerData.SPOUSE,
            VolunteerData.EMERGENCY_CONTACT, VolunteerData.TRADES, VolunteerData.INTERVIEWER);
    @Autowired
    private VolunteerDao volunteerDao;
    @Autowired
    private PersonDao personDao;
    @Autowired
    private CongregationDao congregationDao;
    @Autowired
    private ReferenceDao referenceDao;
    @Autowired
    private VolunteerModelFactory volunteerModelFactory;
    @Autowired
    private AssignmentModelFactory assignmentModelFactory;
    @Autowired
    private VolunteerBadgePdfModelFactory volunteerBadgePdfModelFactory;
    @Autowired
    private InterviewSessionDao interviewSessionDao;
    @Autowired
    private DepartmentDao departmentDao;
    @Resource(name = "imageDirectories")
    private Properties imageDirectories;

    /**
     * Display a list of volunteers.
     *
     * @param model mvc model
     * @param searchCriteria search criteria
     * @return view
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showVolunteerList(ModelMap model, VolunteerSearchCriteria searchCriteria) {

        model.addAttribute("volunteers", volunteerDao.findVolunteers(searchCriteria));
        model.addAttribute("newUri", VolunteerModelFactory.generateUri(null) + "new");

        return "volunteers/list";
    }

    /**
     * Display the list of volunteers.
     *
     * @param requestData data tables request data
     * @return view
     */
    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public AjaxDataTableResult<VolunteerListModel> showDatatableAjaxVolunteerList(
            VolunteerAjaxDataTableRequestData requestData) {
        VolunteerSearchCriteria searchCriteria = new VolunteerSearchCriteria();
        requestData.populateSearchCriteria(searchCriteria);
        searchCriteria.setCongregationId(requestData.getCongregationId());
        searchCriteria.setSkillId(requestData.getSkillId());
        searchCriteria.setQualificationId(requestData.getQualificationId());

        AjaxDataTableResult<VolunteerListModel> result = new AjaxDataTableResult<VolunteerListModel>();
        result.setEcho(requestData.getEcho());

        int totalFilteredResults = volunteerDao.findVolunteersCount(searchCriteria);
        if (searchCriteria.isFiltered()) {
            VolunteerSearchCriteria noFilterCriteria = searchCriteria.clone();
            noFilterCriteria.clearFilter();
            result.setTotalRecords(volunteerDao.findVolunteersCount(searchCriteria));
        } else {
            result.setTotalRecords(totalFilteredResults);
        }

        if (totalFilteredResults > 0) {
            List<Volunteer> volunteers = volunteerDao.findVolunteers(searchCriteria);
            List<VolunteerListModel> modelList = new ArrayList<VolunteerListModel>(volunteers.size());
            for (Volunteer volunteer : volunteers) {
                modelList.add(volunteerModelFactory.generateVolunteerListModel(volunteer));
            }
            result.setRecords(modelList);
            result.setTotalDisplayRecords(totalFilteredResults);
        } else {
            result.setRecords(Collections.<VolunteerListModel>emptyList());
        }

        return result;

    }

    /**
     * @param volunteerId volunteer primary key
     * @param model model
     * @return view name
     * @throws NoSuchRequestHandlingMethodException when no person matching the
     * id is found
     */
    @RequestMapping(value = "{volunteerId}", method = RequestMethod.GET)
    public String showVolunteer(@PathVariable Integer volunteerId, ModelMap model)
            throws NoSuchRequestHandlingMethodException {

        Volunteer volunteer = volunteerDao.findVolunteer(volunteerId, VOLUNTEER_DATA);
        if (volunteer == null) {
            if (personDao.findPerson(volunteerId) != null) {
                return "redirect:" + PersonModelFactory.generateUri(volunteerId);
            }
            throw new NoSuchRequestHandlingMethodException("No volunteer or person with id [" + volunteerId + "]",
                    this.getClass());
        }

        List<Assignment> assignments = volunteerDao.findAssignments(volunteerId);
        List<VolunteerSkill> skills = volunteerDao.findSkills(volunteerId);
        List<VolunteerQualification> qualifications = volunteerDao.findQualifications(volunteerId);

        model.addAttribute("volunteer", volunteerModelFactory.generateVolunteerModel(volunteer));
        model.addAttribute("assignments", generateAssignments(assignments));
        model.addAttribute("skills", volunteerModelFactory.generateVolunteerSkillsModel(skills));
        model.addAttribute("qualifications", volunteerModelFactory.generateVolunteerQualificationsModel(qualifications));
        model.addAttribute("interviews", generateInterviewsModel(volunteerId));

        if (volunteer.isPhotoProvided() && volunteer.getPerson().getBirthDate() != null && assignments != null) {
            model.addAttribute("badgeUri", VolunteerBadgePdfModelFactory.generateUri(volunteerId));
        }

        model.addAttribute("rbcStatusCodes", referenceDao.findRBCStatusValues());
        model.addAttribute("tradeNumbers", referenceDao.findTradeNumbers());
        model.addAttribute("teams", referenceDao.findTeams());
        model.addAttribute("assignmentRoles", referenceDao.findAssignmentRoleValues());

        return "volunteers/show";
    }

    /**
     * Generate the models for the volunteer assignments.
     *
     * @param assignments assignments
     * @return model list
     */
    private List<AssignmentModel> generateAssignments(List<Assignment> assignments) {
        if (CollectionUtils.isEmpty(assignments)) {
            return null;
        }

        List<AssignmentModel> modelList = new ArrayList<AssignmentModel>(assignments.size());
        for (Assignment assignment : assignments) {
            modelList.add(assignmentModelFactory.generateAssignmentModel(assignment));
        }

        return modelList;
    }

    private List<VolunteerInterviewModel> generateInterviewsModel(Integer volunteerId) {
        List<VolunteerInterviewSession> interviews = interviewSessionDao
                .findVolunteerInterviewSessionsByVolunteer(volunteerId);
        if (CollectionUtils.isEmpty(interviews)) {
            return null;
        }

        List<VolunteerInterviewModel> modelList = new ArrayList<VolunteerInterviewModel>();
        for (VolunteerInterviewSession interview : interviews) {
            InterviewSession session = interviewSessionDao.findInterviewSession(interview.getInterviewSession()
                    .getInterviewSessionId());
            modelList.add(volunteerModelFactory.generateVolunteerInterviewModel(interview, session));
        }

        Collections.sort(modelList, new Comparator<VolunteerInterviewModel>() {
            @Override
            public int compare(VolunteerInterviewModel model1, VolunteerInterviewModel model2) {
                return model2.getDate().compareTo(model1.getDate());
            }

        });
        return modelList;
    }

    /**
     * Display the form to create a new volunteer.
     *
     * @param model mvc model
     * @return view name
     */
    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String showCreateVolunteerForm(ModelMap model) {

        // initialise the form bean
        model.addAttribute("volunteer", new VolunteerForm());
        model.addAttribute("relationshipValues", referenceDao.findRelationshipValues());
        return "volunteers/create";
    }

    /**
     * Handle the volunteer core details form submission.
     * <p>
     * This handles new volunteer creation only.
     *
     * @param form volunteer form
     * @return redirect url
     */
    @RequestMapping(method = RequestMethod.POST)
    public String createVolunteer(@Valid VolunteerForm form) {

        Volunteer volunteer;

        // look up the existing volunteer/person if possible
        if (form.getPersonId() != null) {
            volunteer = volunteerDao.findVolunteer(form.getPersonId(), VOLUNTEER_DATA);
            if (volunteer == null) {
                volunteer = new Volunteer();
                Person person = personDao.findPerson(form.getPersonId());
                if (person != null) {
                    volunteer.setPerson(person);
                }
            }
        } else {
            volunteer = new Volunteer();
        }

        // save/update the underlying person data
        Person person = volunteer.getPerson();

        Address address = new Address();
        address.setStreet(form.getStreet());
        address.setTown(form.getTown());
        // county is not set in the create volunteer form - it is not on the S-82 paper form
        address.setPostcode(form.getPostcode());
        person.setAddress(address);

        person.setBirthDate(DataConverterUtil.toSqlDate(form.getBirthDate()));

        Integer congregationId = person.getCongregation() != null ? person.getCongregation().getCongregationId() : null;

        if (ObjectUtils.notEqual(congregationId, form.getCongregationId())) {
            person.setCongregation(congregationDao.findCongregation(form.getCongregationId()));
        }
        person.setEmail(form.getEmail());
        person.setForename(form.getForename());
        person.setMiddleName(form.getMiddleName());
        person.setSurname(form.getSurname());
        person.setMobile(PhoneNumberFormatter.format(form.getMobile()));
        person.setTelephone(PhoneNumberFormatter.format(form.getTelephone()));
        person.setWorkPhone(PhoneNumberFormatter.format(form.getWorkPhone()));

        // save the volunteer specific data
        volunteer.setBaptismDate(DataConverterUtil.toSqlDate(form.getBaptismDate()));
        volunteer.setFormDate(DataConverterUtil.toSqlDate(form.getFormDate()));

        if (form.isElder()) {
            volunteer.setAppointmentCode(APPOINTMENT_ELDER);
        } else if (form.isMinisterialServant()) {
            volunteer.setAppointmentCode(APPOINTMENT_MINISTERIAL_SERVANT);
        }
        volunteer.setGender(form.getGender());

        if (form.isRegularPioneer()) {
            volunteer.setFulltimeCode(FULLTIME_REGULAR_PIONEER);
        }

        Person emergencyContact = createEmergencyContact(form);

        volunteer.setEmergencyContact(emergencyContact);
        volunteer.setEmergencyContactRelationshipCode(form.getEmergencyRelationshipCode());

        Person spouse = createSpouse(form, emergencyContact);
        if (spouse != null) {
            volunteer.setMaritalStatusCode(MARRIED_MARITAL_STATUS);
            volunteer.setSpouse(spouse);
        }

        volunteer.setRbcStatusCode(RBC_STATUS_PENDING);

        if (form.getTrades() != null) {
            Set<VolunteerTrade> volunteerTrades = new HashSet<VolunteerTrade>();
            for (VolunteerTrade trade : form.getTrades()) {
                if (StringUtils.isNotBlank(trade.getName())) {
                    trade.setVolunteer(volunteer);
                    volunteerTrades.add(trade);
                }
            }
            if (!volunteerTrades.isEmpty()) {
                volunteer.setTrades(volunteerTrades);
            }
        }

        volunteerDao.createVolunteer(volunteer);

        return "redirect:" + VolunteerModelFactory.generateUri(volunteer.getPersonId());
    }

    /**
     * Display the form to edit the info under the spiritual tab on the
     * volunteer.
     *
     * @param volunteerId volunteer id to edit
     * @param model mvc model
     * @return view name
     * @throws NoSuchRequestHandlingMethodException if volunteer is not found
     */
    @RequestMapping(value = "{volunteerId}/spiritual/edit", method = RequestMethod.GET)
    public String showEditVolunteerSpiritualForm(@PathVariable Integer volunteerId, ModelMap model)
            throws NoSuchRequestHandlingMethodException {

        Volunteer volunteer = volunteerDao.findVolunteer(volunteerId, EnumSet.noneOf(VolunteerData.class));
        if (volunteer == null) {
            throw new NoSuchRequestHandlingMethodException("No volunteer #" + volunteerId + " found", this.getClass());
        }

        VolunteerSpiritualForm form = new VolunteerSpiritualForm();
        form.setAppointmentCode(volunteer.getAppointmentCode());
        form.setBaptismDate(DataConverterUtil.toDateTime(volunteer.getBaptismDate()));

        if (volunteer.getPerson().getCongregation() != null) {
            Congregation congregation = congregationDao.findCongregation(volunteer.getPerson().getCongregation()
                    .getCongregationId());
            form.setCongregationName(congregation.getName());
            form.setCongregationId(congregation.getCongregationId());
        }

        form.setFulltimeCode(volunteer.getFulltimeCode());

        model.addAttribute("volunteerSpiritual", form);
        model.addAttribute("forename", volunteer.getPerson().getForename());
        model.addAttribute("surname", volunteer.getPerson().getSurname());
        model.addAttribute("fulltimeValues", referenceDao.findFulltimeValues());
        model.addAttribute("appointmentValues", referenceDao.findAppointmentValues());
        model.addAttribute("submitUri", VolunteerModelFactory.generateUri(volunteerId) + "/spiritual");
        return "volunteers/edit-spiritual";
    }

    /**
     * Display the form to edit the info under the rbc status tab on the
     * volunteer.
     *
     * @param volunteerId volunteer id to edit
     * @param model mvc model
     * @return view name
     * @throws NoSuchRequestHandlingMethodException if volunteer is not found
     */
    @RequestMapping(value = "{volunteerId}/rbc-status/edit", method = RequestMethod.GET)
    public String showEditVolunteerRbcStatusForm(@PathVariable Integer volunteerId, ModelMap model)
            throws NoSuchRequestHandlingMethodException {

        Volunteer volunteer = volunteerDao.findVolunteer(volunteerId, EnumSet.of(VolunteerData.INTERVIEWER));
        if (volunteer == null) {
            throw new NoSuchRequestHandlingMethodException("No volunteer #" + volunteerId + " found", this.getClass());
        }

        VolunteerRbcStatusForm form = new VolunteerRbcStatusForm();

        form.setFormDate(DataConverterUtil.toDateTime(volunteer.getFormDate()));

        if (volunteer.getInterviewerA() != null) {
            form.setInterviewerAPersonId(volunteer.getInterviewerA().getPersonId());
            form.setInterviewerAUserName(volunteer.getInterviewerA().getUserName());
        }
        if (volunteer.getInterviewerB() != null) {
            form.setInterviewerBPersonId(volunteer.getInterviewerB().getPersonId());
            form.setInterviewerBUserName(volunteer.getInterviewerB().getUserName());
        }

        form.setInterviewComments(volunteer.getInterviewComments());
        form.setJoinedDate(DataConverterUtil.toDateTime(volunteer.getJoinedDate()));
        form.setBadgeIssueDate(DataConverterUtil.toDateTime(volunteer.getBadgeIssueDate()));

        if (volunteer.getAvailability() != null && volunteer.getAvailability().length() == 7) {
            char[] availability = volunteer.getAvailability().toCharArray();

            form.setAvailabilityMonday(availability[0] == 'T');
            form.setAvailabilityTuesday(availability[1] == 'T');
            form.setAvailabilityWednesday(availability[2] == 'T');
            form.setAvailabilityThursday(availability[3] == 'T');
            form.setAvailabilityFriday(availability[4] == 'T');
            form.setAvailabilitySaturday(availability[5] == 'T');
            form.setAvailabilitySunday(availability[6] == 'T');
        }
        form.setOversight(volunteer.isOversight());
        form.setOversightComments(volunteer.getOversightComments());
        form.setReliefAbroad(volunteer.isReliefAbroad());
        form.setReliefAbroadComments(volunteer.getReliefAbroadComments());
        form.setReliefUK(volunteer.isReliefUK());
        form.setReliefUKComments(volunteer.getReliefUKComments());
        form.setHhcFormCode(volunteer.getHhcFormCode());

        model.addAttribute("volunteerRbcStatus", form);
        model.addAttribute("forename", volunteer.getPerson().getForename());
        model.addAttribute("surname", volunteer.getPerson().getSurname());
        model.addAttribute("submitUri", VolunteerModelFactory.generateUri(volunteerId) + "/rbc-status");

        return "volunteers/edit-rbc-status";
    }

    /**
     * Display the form to edit the info under the personal tab on the
     * volunteer.
     *
     * @param volunteerId volunteer id to edit
     * @param model mvc model
     * @return view name
     * @throws NoSuchRequestHandlingMethodException if volunteer is not found
     */
    @RequestMapping(value = "{volunteerId}/personal/edit", method = RequestMethod.GET)
    public String showEditVolunteerPersonalForm(@PathVariable Integer volunteerId, ModelMap model)
            throws NoSuchRequestHandlingMethodException {

        Volunteer volunteer = volunteerDao.findVolunteer(volunteerId, EnumSet.of(VolunteerData.SPOUSE));
        if (volunteer == null) {
            throw new NoSuchRequestHandlingMethodException("No volunteer #" + volunteerId + " found", this.getClass());
        }

        VolunteerPersonalForm form = new VolunteerPersonalForm();

        Person person = volunteer.getPerson();

        form.setEmail(person.getEmail());
        form.setTelephone(person.getTelephone());
        form.setMobile(person.getMobile());
        form.setWorkPhone(person.getWorkPhone());
        if (person.getAddress() != null) {
            form.setStreet(person.getAddress().getStreet());
            form.setTown(person.getAddress().getTown());
            form.setCounty(person.getAddress().getCounty());
            form.setPostcode(person.getAddress().getPostcode());
        }
        form.setGender(volunteer.getGender());

        if (person.getBirthDate() != null) {
            form.setBirthDate(new DateTime(person.getBirthDate().getTime()));
        }

        form.setMaritalStatusCode(volunteer.getMaritalStatusCode());
        if (volunteer.getSpouse() != null) {
            form.setSpousePersonId(volunteer.getSpouse().getPersonId());
            form.setSpouseForename(volunteer.getSpouse().getForename());
            form.setSpouseSurname(volunteer.getSpouse().getSurname());
        }

        model.addAttribute("maritalStatusValues", referenceDao.findMaritalStatusValues());
        model.addAttribute("volunteerPersonal", form);
        model.addAttribute("forename", person.getForename());
        model.addAttribute("surname", person.getSurname());
        model.addAttribute("submitUri", VolunteerModelFactory.generateUri(volunteerId) + "/personal");

        return "volunteers/edit-personal";
    }

    /**
     * Update the volunteer name.
     * <p>
     * This is expected to be called with an ajax request, so we return a 204
     * response on success
     *
     * @param volunteerId volunteer id to edit
     * @param form form data
     * @throws NoSuchRequestHandlingMethodException if volunteer is not found
     */
    @RequestMapping(value = "{volunteerId}/name", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateVolunteerName(@PathVariable Integer volunteerId, @Valid VolunteerNameForm form)
            throws NoSuchRequestHandlingMethodException {

        Volunteer volunteer = volunteerDao.findVolunteer(volunteerId, VOLUNTEER_DATA);
        if (volunteer == null) {
            throw new NoSuchRequestHandlingMethodException("No volunteer #" + volunteerId + " found", this.getClass());
        }

        Person person = volunteer.getPerson();

        person.setForename(form.getForename());
        person.setMiddleName(form.getMiddleName());
        person.setSurname(form.getSurname());

        volunteerDao.updateVolunteer(volunteer);
    }

    /**
     * Update the volunteer comments.
     * <p>
     * This is expected to be called with an ajax request, so we return a 204
     * response on success
     *
     * @param volunteerId volunteer id to edit
     * @param comments comments to set
     * @throws NoSuchRequestHandlingMethodException if volunteer is not found
     */
    @RequestMapping(value = "{volunteerId}/comments", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateVolunteerComments(@PathVariable Integer volunteerId, @RequestParam("comments") String comments)
            throws NoSuchRequestHandlingMethodException {

        Volunteer volunteer = volunteerDao.findVolunteer(volunteerId, VOLUNTEER_DATA);
        if (volunteer == null) {
            throw new NoSuchRequestHandlingMethodException("No volunteer #" + volunteerId + " found", this.getClass());
        }

        volunteer.getPerson().setComments(comments);

        volunteerDao.updateVolunteer(volunteer);
    }

    /**
     * Update the volunteer spiritual information.
     *
     * @param volunteerId volunteer id to edit
     * @param form form data
     * @return view name (redirect)
     * @throws NoSuchRequestHandlingMethodException if volunteer is not found
     */
    @RequestMapping(value = "{volunteerId}/spiritual", method = RequestMethod.PUT)
    public String updateVolunteerSpiritual(@PathVariable Integer volunteerId, @Valid VolunteerSpiritualForm form)
            throws NoSuchRequestHandlingMethodException {

        Volunteer volunteer = volunteerDao.findVolunteer(volunteerId, VOLUNTEER_DATA);
        if (volunteer == null) {
            throw new NoSuchRequestHandlingMethodException("No volunteer #" + volunteerId + " found", this.getClass());
        }

        volunteer.setBaptismDate(DataConverterUtil.toSqlDate(form.getBaptismDate()));
        volunteer.setFulltimeCode(form.getFulltimeCode());
        volunteer.setAppointmentCode(form.getAppointmentCode());

        if (form.getCongregationId() == null) {
            volunteer.getPerson().setCongregation(null);
        } else {
            volunteer.getPerson().setCongregation(congregationDao.findCongregation(form.getCongregationId()));
        }

        volunteerDao.updateVolunteer(volunteer);

        return "redirect:" + VolunteerModelFactory.generateUri(volunteer.getPersonId()) + "#!spiritual";

    }

    /**
     * Update the volunteer RBC status.
     *
     * @param volunteerId volunteer id to edit
     * @param form form data
     * @return view name (redirect)
     * @throws NoSuchRequestHandlingMethodException if volunteer is not found
     */
    @RequestMapping(value = "{volunteerId}/rbc-status", method = RequestMethod.PUT)
    public String updateVolunteerRbcStatus(@PathVariable Integer volunteerId, @Valid VolunteerRbcStatusForm form)
            throws NoSuchRequestHandlingMethodException {

        Volunteer volunteer = volunteerDao.findVolunteer(volunteerId, VOLUNTEER_DATA);
        if (volunteer == null) {
            throw new NoSuchRequestHandlingMethodException("No volunteer #" + volunteerId + " found", this.getClass());
        }

        volunteer.setFormDate(DataConverterUtil.toSqlDate(form.getFormDate()));

        if (form.getInterviewerAPersonId() != null) {
            User user = new User();
            user.setPersonId(form.getInterviewerAPersonId());
            volunteer.setInterviewerA(user);
        } else {
            volunteer.setInterviewerA(null);
        }
        if (form.getInterviewerBPersonId() != null) {
            User user = new User();
            user.setPersonId(form.getInterviewerBPersonId());
            volunteer.setInterviewerB(user);
        } else {
            volunteer.setInterviewerB(null);
        }

        volunteer.setInterviewComments(form.getInterviewComments());
        volunteer.setJoinedDate(DataConverterUtil.toSqlDate(form.getJoinedDate()));
        volunteer.setBadgeIssueDate(DataConverterUtil.toSqlDate(form.getBadgeIssueDate()));
        volunteer.setAvailability(generateAvailability(form.isAvailabilityMonday(), form.isAvailabilityTuesday(),
                form.isAvailabilityWednesday(), form.isAvailabilityThursday(), form.isAvailabilityFriday(),
                form.isAvailabilitySaturday(), form.isAvailabilitySunday()));
        volunteer.setOversight(form.isOversight());
        volunteer.setOversightComments(form.getOversightComments());
        volunteer.setReliefAbroad(form.isReliefAbroad());
        volunteer.setReliefAbroadComments(form.getReliefAbroadComments());
        volunteer.setReliefUK(form.isReliefUK());
        volunteer.setReliefUKComments(form.getReliefUKComments());
        volunteer.setHhcFormCode(form.getHhcFormCode());

        volunteerDao.updateVolunteer(volunteer);

        return "redirect:" + VolunteerModelFactory.generateUri(volunteer.getPersonId()) + "#!rbc-status";
    }

    /**
     * Update the RBC status code of a volunteer. Not to be confused with the
     * volunteer's RBC status tab that appears on the volunteer show JSP. This
     * will be an AJAX request.
     *
     * @param volunteerId volunteer id
     * @param rbcStatusCode RBC status code to be passed in the request
     * @throws NoSuchRequestHandlingMethodException if volunteer not found
     *
     */
    @RequestMapping(value = "{volunteerId}/rbc-status-code", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateVolunteerRbcStatusCode(@PathVariable Integer volunteerId,
            @RequestParam("rbcStatusCode") String rbcStatusCode) throws NoSuchRequestHandlingMethodException {

        Volunteer volunteer = volunteerDao.findVolunteer(volunteerId, VOLUNTEER_DATA);

        if (volunteer == null) {
            throw new NoSuchRequestHandlingMethodException("No volunteer #" + volunteerId + " found", this.getClass());
        }

        volunteer.setRbcStatusCode(rbcStatusCode);
        volunteerDao.updateVolunteer(volunteer);
    }

    /**
     * Update the volunteer personal information.
     *
     * @param volunteerId volunteer id to edit
     * @param form form data
     * @return view name (redirect)
     * @throws NoSuchRequestHandlingMethodException if volunteer is not found
     */
    @RequestMapping(value = "{volunteerId}/personal", method = RequestMethod.PUT)
    public String updateVolunteerPersonal(@PathVariable Integer volunteerId, @Valid VolunteerPersonalForm form)
            throws NoSuchRequestHandlingMethodException {

        Volunteer volunteer = volunteerDao.findVolunteer(volunteerId, VOLUNTEER_DATA);
        if (volunteer == null) {
            throw new NoSuchRequestHandlingMethodException("No volunteer #" + volunteerId + " found", this.getClass());
        }

        Person person = volunteer.getPerson();

        Address address = new Address();
        address.setStreet(form.getStreet());
        address.setTown(form.getTown());
        address.setCounty(form.getCounty());
        address.setPostcode(form.getPostcode());

        person.setAddress(address);
        person.setBirthDate(DataConverterUtil.toSqlDate(form.getBirthDate()));
        person.setEmail(form.getEmail());
        volunteer.setGender(form.getGender());
        volunteer.setMaritalStatusCode(form.getMaritalStatusCode());
        person.setMobile(PhoneNumberFormatter.format(form.getMobile()));
        person.setTelephone(PhoneNumberFormatter.format(form.getTelephone()));
        person.setWorkPhone(PhoneNumberFormatter.format(form.getWorkPhone()));

        if (form.getSpousePersonId() != null) {
            volunteer.setSpouse(personDao.findPerson(form.getSpousePersonId()));
        } else if (form.getSpouseForename() != null && form.getSpouseSurname() != null) {

            Person spouse = new Person();
            spouse.setForename(form.getSpouseForename());
            spouse.setSurname(form.getSpouseSurname());

            personDao.createPerson(spouse);

            volunteer.setSpouse(spouse);
        } else {
            volunteer.setSpouse(null);
        }

        volunteerDao.updateVolunteer(volunteer);

        return "redirect:" + VolunteerModelFactory.generateUri(volunteer.getPersonId()) + "#!personal";
    }

    /**
     * Produce the Volunteer Badge PDF.
     *
     * @param volunteerId volunteer id of for his/her badge
     * @param volunteerBadgeId volunteer badge id
     *
     * @throws IOException if image file not found
     * @return modelAndView of the VolunteerBadgePdfView
     */
    @RequestMapping(value = "{volunteerId}/rbc-{volunteerBadgeId}-badge.pdf", method = RequestMethod.GET)
    public ModelAndView produceVolunteerBadgePdf(@PathVariable Integer volunteerId,
            @PathVariable Integer volunteerBadgeId) throws IOException {
        Volunteer volunteer = volunteerDao.findVolunteer(volunteerId, VOLUNTEER_DATA);
        if (volunteerId.equals(volunteerBadgeId)) {
            ModelAndView modelAndView = new ModelAndView("volunteerBadgePdfView");

            String assignment = volunteerBadgePdfModelFactory.generatePrimaryAssignment(volunteer);
            Set<String> skillsSet = volunteerBadgePdfModelFactory.generateSkillsSet(volunteer);
            VolunteerBadgeColour colourBand = volunteerBadgePdfModelFactory.generateColourBand(volunteer);

            modelAndView.getModelMap().addAttribute("volunteer", volunteer);
            modelAndView.getModelMap().addAttribute("colourBand", colourBand);
            modelAndView.getModelMap().addAttribute("skillsSet", skillsSet);
            modelAndView.getModelMap().addAttribute("assignment", assignment);

            String imageName = volunteerId + ".jpg";
            File imageFile = new File(imageDirectories.getProperty(VOLUNTEER_IMAGE_DIRECTORY_KEY) + imageName);
            byte[] bytes = FileUtils.readFileToByteArray(imageFile);
            InputStream inputStream = new ByteArrayInputStream(bytes);
            BufferedImage bufferedImage = ImageIO.read(inputStream);
            modelAndView.getModelMap().addAttribute("bufferedImage", bufferedImage);

            return modelAndView;
        } else {
            return new ModelAndView("redirect:" + VolunteerBadgePdfModelFactory.generateUri(volunteerId));
        }
    }

    /**
     * Get volunteer image.
     * <p>This assumes we have already checked the volunteer object to ensure it exists
     * and that it expects to have an image defined.
     *
     * @param volunteerId id
     * @param response HttpServletResponse
     * @throws IOException if the file cannot be read
     * @throws NoSuchRequestHandlingMethodException when the image file is not found
     */
    @RequestMapping(value = "{volunteerId}/image", method = RequestMethod.GET)
    public void showImage(@PathVariable Integer volunteerId, HttpServletResponse response) throws IOException,
            NoSuchRequestHandlingMethodException {
        String imageName = volunteerId + ".jpg";
        File file = new File(imageDirectories.getProperty(VOLUNTEER_IMAGE_DIRECTORY_KEY) + imageName);

        if (!file.exists()) {
            throw new NoSuchRequestHandlingMethodException("Volunteer #" + volunteerId
                    + " does not have an image defined", this.getClass());
        }

        OutputStream out = response.getOutputStream();
        response.setHeader("Content-Disposition", "attachment; filename=\"" + imageName + "\"");
        response.setContentType("image/jpeg");
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
        FileCopyUtils.copy(in, out);
        out.flush();
    }

    /**
    * Handles the volunteer image upload.
    *
    * @param volunteerId id
    * @param imageFile file to be uploaded
    * @throws IOException if file cannot be written
    * @return view
     * @throws NoSuchRequestHandlingMethodException on failure to find the volunteer
    */
    @RequestMapping(value = "{volunteerId}/image", method = RequestMethod.POST)
    public String handleImageUpload(@PathVariable Integer volunteerId,
            @RequestParam(value = "image", required = true) MultipartFile imageFile) throws IOException,
            NoSuchRequestHandlingMethodException {

        Volunteer volunteer = volunteerDao.findVolunteer(volunteerId, EnumSet.of(VolunteerData.INTERVIEWER));
        if (volunteer == null) {
            throw new NoSuchRequestHandlingMethodException("No volunteer #" + volunteerId + " found", this.getClass());
        }

        String filename = volunteerId + ".jpg";

        File file = new File(imageDirectories.getProperty(VOLUNTEER_IMAGE_DIRECTORY_KEY) + filename);
        FileUtils.writeByteArrayToFile(file, imageFile.getBytes());

        volunteer.setPhotoProvided(true);
        volunteerDao.updateVolunteer(volunteer);

        return "redirect:" + VolunteerModelFactory.generateUri(volunteerId);
    }

    private String generateAvailability(boolean... availabilityDays) {
        StringBuilder builder = new StringBuilder(7);
        for (boolean availabilityDay : availabilityDays) {
            builder.append(availabilityDay ? 'T' : 'F');
        }
        return builder.toString();
    }

    private Person createEmergencyContact(VolunteerForm form) {
        if (form.getEmergencyContactPersonId() != null) {
            // NOTE: an existing person which is linked as emergency contact may
            // have fields which are set to null.
            return personDao.findPerson(form.getEmergencyContactPersonId());
        }

        Person emergencyContact = new Person();

        Address address = new Address();
        address.setStreet(form.getEmergencyContactStreet());
        address.setTown(form.getEmergencyContactTown());
        // county is not set when creating a new contact in the create form
        address.setPostcode(form.getEmergencyContactPostcode());
        emergencyContact.setAddress(address);

        emergencyContact.setForename(form.getEmergencyContactForename());
        emergencyContact.setSurname(form.getEmergencyContactSurname());
        emergencyContact.setTelephone(PhoneNumberFormatter.format(form.getEmergencyContactTelephone()));
        emergencyContact.setMobile(PhoneNumberFormatter.format(form.getEmergencyContactMobile()));

        return emergencyContact;

    }

    private Person createSpouse(VolunteerForm form, Person emergencyContact) {
        if (form.getSpousePersonId() != null) {
            return personDao.findPerson(form.getSpousePersonId());
        }

        // if the emergency contact is also new, see if it is the same person
        if (form.getEmergencyContactPersonId() == null) {
            if (ObjectUtils.equals(emergencyContact.getForename(), form.getSpouseForename())
                    && ObjectUtils.equals(emergencyContact.getSurname(), form.getSurname())
                    && (ObjectUtils.equals(form.getEmergencyRelationshipCode(), "HB") || ObjectUtils.equals(
                            form.getEmergencyRelationshipCode(), "WF"))) {

                return emergencyContact;
            }
        }

        // if unpopulated, return null
        if (form.getSpouseForename() == null || form.getSpouseSurname() == null) {
            return null;
        }

        // create a new person
        Person spouse = new Person();
        spouse.setForename(form.getSpouseForename());
        spouse.setSurname(form.getSpouseSurname());
        return spouse;
    }

    /**
     * Delete a department assignment linked to a volunteer.
     * @param volunteerId volunteer id
     * @param assignmentId linked volunteer department assignment id
     * @throws NoSuchRequestHandlingMethodException if either the volunteer assignment is not found
     */
    @RequestMapping(value = "{volunteerId}/assignment/{assignmentId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVolunteerAssignment(@PathVariable Integer volunteerId, @PathVariable Integer assignmentId)
            throws NoSuchRequestHandlingMethodException {

        List<Assignment> assignments = volunteerDao.findAssignments(volunteerId);

        Assignment volunteerAssignment = null;
        if (assignments != null) {
            for (Assignment assignment : assignments) {
                if (assignment.getAssignmentId().equals(assignmentId)) {
                    volunteerAssignment = assignment;
                }
            }
        }

        if (volunteerAssignment == null) {
            throw new NoSuchRequestHandlingMethodException("Volunteer #" + volunteerId
                    + " is not linked to assignment #" + assignmentId, this.getClass());
        }

        departmentDao.deleteAssignment(volunteerAssignment);
    }

}
