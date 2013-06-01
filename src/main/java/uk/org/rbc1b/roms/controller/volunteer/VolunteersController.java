/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.volunteer;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.validation.Valid;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;
import uk.org.rbc1b.roms.controller.common.datatable.AjaxDataTableRequestData;
import uk.org.rbc1b.roms.controller.common.datatable.AjaxDataTableResult;
import uk.org.rbc1b.roms.controller.common.model.PersonModelFactory;
import uk.org.rbc1b.roms.db.Address;
import uk.org.rbc1b.roms.db.CongregationDao;
import uk.org.rbc1b.roms.db.Person;
import uk.org.rbc1b.roms.db.PersonDao;
import uk.org.rbc1b.roms.db.volunteer.Assignment;
import uk.org.rbc1b.roms.db.volunteer.Volunteer;
import uk.org.rbc1b.roms.db.volunteer.VolunteerDao;
import uk.org.rbc1b.roms.db.volunteer.VolunteerDao.VolunteerData;
import uk.org.rbc1b.roms.db.volunteer.VolunteerQualification;
import uk.org.rbc1b.roms.db.volunteer.VolunteerSearchCriteria;
import uk.org.rbc1b.roms.db.volunteer.VolunteerSkill;
import uk.org.rbc1b.roms.db.volunteer.VolunteerTrade;
import uk.org.rbc1b.roms.reference.ReferenceDao;

/**
 *
 *
 * @author rahulsingh
 */
@Controller
@RequestMapping("/volunteers")
public class VolunteersController {

    private static final int MARRIED_MARITAL_STATUS = 2;
    private static final int RBC_STATUS_ACTIVE = 1;
    private static final int INTERVIEW_STATUS_INVITE_DUE = 1;
    private static final int FULLTIME_REGULAR_PIONEER = 2;
    private static final int APPOINTMENT_ELDER = 1;
    private static final int APPOINTMENT_MINISTERIAL_SERVANT = 2;
    private static final Set<VolunteerData> VOLUNTEER_DATA = EnumSet.of(VolunteerData.SPOUSE,
            VolunteerData.EMERGENCY_CONTACT, VolunteerData.TRADES, VolunteerData.INTERVIEWER);
    private VolunteerDao volunteerDao;
    private PersonDao personDao;
    private CongregationDao congregationDao;
    private ReferenceDao referenceDao;
    private PersonModelFactory personModelFactory;
    private VolunteerModelFactory volunteerModelFactory;

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
        model.addAttribute("newUri", volunteerModelFactory.generateUri(null) + "new");
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
    public AjaxDataTableResult showDatatableAjaxVolunteerList(AjaxDataTableRequestData requestData) {
        VolunteerSearchCriteria searchCriteria = new VolunteerSearchCriteria();
        searchCriteria.setSearch(requestData.getSearch());
        searchCriteria.setSortValue(requestData.getSortValue());
        searchCriteria.setSortDirection(requestData.getSortDirection());
        searchCriteria.setStartIndex(requestData.getDisplayStart());
        searchCriteria.setMaxResults(requestData.getDisplayLength());

        AjaxDataTableResult<VolunteerListModel> result = new AjaxDataTableResult<VolunteerListModel>();
        result.setEcho(requestData.getEcho());

        int totalResults = volunteerDao.findVolunteersCount(searchCriteria);
        result.setTotalRecords(totalResults);
        if (totalResults > 0) {
            List<Volunteer> volunteers = volunteerDao.findVolunteers(searchCriteria);
            List<VolunteerListModel> modelList = new ArrayList<VolunteerListModel>(volunteers.size());
            for (Volunteer volunteer : volunteers) {
                modelList.add(volunteerModelFactory.generateVolunteerListModel(volunteer));
            }
            result.setRecords(modelList);
            result.setTotalDisplayRecords(modelList.size());
        }

        return result;

    }

    /**
     * @param volunteerId volunteer primary key
     * @param model model
     * @return view name
     * @throws NoSuchRequestHandlingMethodException when no person matching the id is found
     */
    @RequestMapping(value = "{volunteerId}", method = RequestMethod.GET)
    public String showVolunteer(@PathVariable Integer volunteerId, ModelMap model) throws NoSuchRequestHandlingMethodException {

        Volunteer volunteer = volunteerDao.findVolunteer(volunteerId, VOLUNTEER_DATA);
        if (volunteer == null) {
            if (personDao.findPerson(volunteerId) != null) {
                return "redirect:" + personModelFactory.generateUri(volunteerId);
            }
            throw new NoSuchRequestHandlingMethodException("No volunteer or person with id [" + volunteerId + "]", this.getClass());
        }

        List<Assignment> assignments = volunteerDao.findAssignments(volunteerId);
        List<VolunteerSkill> skills = volunteerDao.findSkills(volunteerId);
        List<VolunteerQualification> qualifications = volunteerDao.findQualifications(volunteerId);

        VolunteerModel volunteerModel = volunteerModelFactory.generateVolunteerModel(volunteer);
        volunteerModel.setAssignments(volunteerModelFactory.generateAssignments(assignments));
        volunteerModel.setSkills(volunteerModelFactory.generateVolunteerSkillsModel(skills));
        volunteerModel.setQualifications(volunteerModelFactory.generateVolunteerQualificationsModel(qualifications));
        model.addAttribute("volunteer", volunteerModel);

        return "volunteers/show";
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
     * Handle the volunteer core details form submission. <p>This handles new volunteer creation only.
     *
     * @param form volunteer form
     * @return redirect url
     */
    @RequestMapping(method = RequestMethod.POST)
    public String createVolunteer(@ModelAttribute("volunteer") @Valid VolunteerForm form) {

        Volunteer volunteer;

        // look up the existing volunteer/person if possible
        if (form.getPersonId() != null) {
            volunteer = volunteerDao.findVolunteer(form.getPersonId(), VOLUNTEER_DATA);
            if (volunteer == null) {
                Person person = personDao.findPerson(form.getPersonId());
                if (person == null) {
                    volunteer = new Volunteer();
                } else {
                    volunteer = new Volunteer(person);
                }
            }
        } else {
            volunteer = new Volunteer();
        }

        Address address = new Address();
        address.setStreet(form.getCounty());
        address.setTown(form.getTown());
        address.setCounty(form.getCounty());
        address.setPostcode(form.getPostcode());
        volunteer.setAddress(address);

        volunteer.setBirthDate(new java.sql.Date(form.getBirthDate().toDateMidnight().getMillis()));

        if (volunteer.getCongregation() == null || !volunteer.getCongregation().getCongregationId().equals(form.getCongregationId())) {
            volunteer.setCongregation(congregationDao.findCongregation(form.getCongregationId()));
        }

        volunteer.setEmail(form.getEmail());
        volunteer.setForename(form.getForename());
        volunteer.setFormDate(new java.sql.Date(form.getFormDate().toDateMidnight().getMillis()));
        volunteer.setMiddleName(form.getMiddleName());
        volunteer.setSurname(form.getSurname());
        volunteer.setMobile(form.getMobile());
        volunteer.setTelephone(form.getTelephone());
        volunteer.setWorkPhone(form.getWorkPhone());
        volunteer.setBaptismDate(new java.sql.Date(form.getBaptismDate().toDateMidnight().getMillis()));

        if (form.isElder()) {
            volunteer.setAppointmentId(APPOINTMENT_ELDER);
        } else if (form.isMinisterialServant()) {
            volunteer.setAppointmentId(APPOINTMENT_MINISTERIAL_SERVANT);
        }
        volunteer.setGender(form.getGender());

        if (form.isRegularPioneer()) {
            volunteer.setFulltimeId(FULLTIME_REGULAR_PIONEER);
        }

        Person emergencyContact = createEmergencyContact(form);

        volunteer.setEmergencyContact(emergencyContact);
        volunteer.setEmergencyContactRelationshipId(form.getEmergencyRelationshipId());

        Person spouse = createSpouse(form, emergencyContact);
        if (spouse != null) {
            volunteer.setMaritalStatusId(MARRIED_MARITAL_STATUS);
            volunteer.setSpouse(spouse);
        }

        volunteer.setRbcStatusId(RBC_STATUS_ACTIVE);
        volunteer.setInterviewStatusId(INTERVIEW_STATUS_INVITE_DUE);

        if (form.getTrades() != null) {
            Set<VolunteerTrade> volunteerTrades = new HashSet<VolunteerTrade>();
            for (VolunteerTrade trade : form.getTrades()) {
                if (StringUtils.isNotBlank(trade.getName())) {
                    trade.setPerson(volunteer);
                    volunteerTrades.add(trade);
                }
            }
            if (!volunteerTrades.isEmpty()) {
                volunteer.setTrades(volunteerTrades);
            }
        }

        volunteerDao.saveVolunteer(volunteer);

        return "redirect:" + volunteerModelFactory.generateUri(volunteer.getPersonId());
    }

    /**
     * Display the form to create a new volunteer.
     *
     * @param volunteerId volunteer id to edit
     * @param model mvc model
     * @return view name
     * @throws NoSuchRequestHandlingMethodException if volunteer is not found
     */
    @RequestMapping(value = "{volunteerId}/spiritual/edit", method = RequestMethod.GET)
    public String showEditVolunteerSpiritualForm(@PathVariable Integer volunteerId, ModelMap model) throws NoSuchRequestHandlingMethodException {

        Volunteer volunteer = volunteerDao.findVolunteer(volunteerId, VOLUNTEER_DATA);
        if (volunteer == null) {
            throw new NoSuchRequestHandlingMethodException("No volunteer #" + volunteerId + " found", this.getClass());
        }

        VolunteerSpiritualForm form = new VolunteerSpiritualForm();
        form.setAppointmentId(volunteer.getAppointmentId());
        form.setBaptismDate(LocalDate.fromDateFields(volunteer.getBaptismDate()).toDateTimeAtStartOfDay());
        form.setCongregationName(volunteer.getCongregation().getName());
        form.setCongregationId(volunteer.getCongregation().getCongregationId());
        form.setFulltimeId(volunteer.getFulltimeId());

        model.addAttribute("volunteerSpiritual", form);
        model.addAttribute("forename", volunteer.getForename());
        model.addAttribute("surname", volunteer.getSurname());
        model.addAttribute("fulltimeValues", referenceDao.findFulltimeValues());
        model.addAttribute("appointmentValues", referenceDao.findAppointmentValues());
        model.addAttribute("submitUri", volunteerModelFactory.generateUri(volunteerId) + "/spiritual");
        return "volunteers/edit-spiritual";
    }

    private Person createEmergencyContact(VolunteerForm form) {
        if (form.getEmergencyContactPersonId() != null) {
            return personDao.findPerson(form.getEmergencyContactPersonId());
        }

        Person emergencyContact = new Person();

        Address address = new Address();
        address.setStreet(form.getEmergencyContactStreet());
        address.setTown(form.getEmergencyContactTown());
        address.setCounty(form.getEmergencyContactCounty());
        address.setPostcode(form.getEmergencyContactPostcode());
        emergencyContact.setAddress(address);

        emergencyContact.setForename(form.getEmergencyContactForename());
        emergencyContact.setSurname(form.getEmergencyContactSurname());
        emergencyContact.setTelephone(form.getEmergencyContactTelephone());
        emergencyContact.setMobile(form.getEmergencyContactMobile());

        return emergencyContact;

    }

    private Person createSpouse(VolunteerForm form, Person emergencyContact) {
        if (form.getSpousePersonId() != null) {
            return personDao.findPerson(form.getSpousePersonId());
        }

        // if the emergency contact is new too, see if it is the same person
        if (emergencyContact.getPersonId() == null) {
            if (ObjectUtils.equals(emergencyContact.getForename(), form.getSpouseForename())
                    && ObjectUtils.equals(emergencyContact.getSurname(), form.getSurname())) {

                return emergencyContact;
            }
        }

        // create a new person
        Person spouse = new Person();
        spouse.setForename(form.getSpouseForename());
        spouse.setSurname(form.getSpouseSurname());
        return spouse;
    }

    @Autowired
    public void setCongregationDao(CongregationDao congregationDao) {
        this.congregationDao = congregationDao;
    }

    @Autowired
    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Autowired
    public void setPersonModelFactory(PersonModelFactory personModelFactory) {
        this.personModelFactory = personModelFactory;
    }

    @Autowired
    public void setReferenceDao(ReferenceDao referenceDao) {
        this.referenceDao = referenceDao;
    }

    @Autowired
    public void setVolunteerDao(VolunteerDao volunteerDao) {
        this.volunteerDao = volunteerDao;
    }

    @Autowired
    public void setVolunteerModelFactory(VolunteerModelFactory volunteerModelFactory) {
        this.volunteerModelFactory = volunteerModelFactory;
    }
}
