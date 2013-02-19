/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.volunteer;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uk.org.rbc1b.roms.controller.common.congregation.CongregationDao;
import uk.org.rbc1b.roms.controller.common.person.PersonDao;
import uk.org.rbc1b.roms.db.Address;
import uk.org.rbc1b.roms.db.Person;
import uk.org.rbc1b.roms.db.volunteer.Volunteer;
import uk.org.rbc1b.roms.reference.ReferenceDao;

/**
 *
 *
 * @author rahulsingh
 */
@Controller
@RequestMapping("/volunteers")
public class VolunteersController {

    public static final int SINGLE_MARITAL_STATUS = 5;
    public static final int RBC_STATUS_ACTIVE = 1;
    public static final int INTERVIEW_STATUS_INVITE_DUE = 1;
    public static final int FULLTIME_BETHEL = 1;
    public static final int FULLTIME_PUBLISHER = 2;
    public static final int FULLTIME_REGULAR_PIONEER = 3;
    public static final int APPOINTMENT_ELDER = 1;
    public static final int APPOINTMENT_MINISTERIAL_SERVANT = 2;
    public static final int APPOINTMENT_PUBLISHER = 3;
    @Autowired
    private VolunteerDao volunteerDao;
    @Autowired
    private PersonDao personDao;
    @Autowired
    private CongregationDao congregationDao;
    @Autowired
    private ReferenceDao referenceDao;

    /**
     * Display a list of volunteers.
     *
     * @param model mvc model
     * @return view
     */
    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasPermission('VOLUNTEER', 'READ')")
    @Transactional(readOnly = true)
    public String handleList(ModelMap model) {

        model.addAttribute("volunteers", volunteerDao.findVolunteers());

        return "volunteers/list";
    }

    /**
     * Display the form to create a new volunteer.
     *
     * @param model mvc model
     * @return view name
     */
    @RequestMapping(value = "new", method = RequestMethod.GET)
    @PreAuthorize("hasPermission('VOLUNTEER', 'ADD')")
    @Transactional(readOnly = true)
    public String handleNewForm(ModelMap model) {

        // initialise the form bean
        model.addAttribute("volunteer", new VolunteerForm());
        model.addAttribute("maritalStatusValues", referenceDao.findMaritalStatusValues());
        model.addAttribute("relationshipValues", referenceDao.findRelationshipValues());
        return "volunteers/create";
    }

    /**
     * Handle the volunteer core details form submission.
     * <p>This handles new volunteer creation only.
     *
     * @param form volunteer form
     * @return redirect url
     */
    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasPermission('VOLUNTEER', 'ADD')")
    @Transactional
    public String handleSubmit(@ModelAttribute("volunteer") @Valid VolunteerForm form) {

        Volunteer volunteer;

        // look up the existing volunteer/person if possible
        if (form.getPersonId() != null) {
            volunteer = volunteerDao.findVolunteer(form.getPersonId());
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
        } else {
            volunteer.setAppointmentId(APPOINTMENT_PUBLISHER);
        }
        volunteer.setGender(form.getGender());

        if (form.isRegularPioneer()) {
            volunteer.setFulltimeId(FULLTIME_REGULAR_PIONEER);
        } else {
            volunteer.setFulltimeId(FULLTIME_PUBLISHER);
        }

        volunteer.setRbcStatusId(RBC_STATUS_ACTIVE);
        volunteer.setMaritalStatusId(SINGLE_MARITAL_STATUS);
        volunteer.setInterviewStatusId(INTERVIEW_STATUS_INVITE_DUE);
        volunteerDao.saveVolunteer(volunteer);

//    private Person emergencyContact;
//    private Relationship emergencyContactRelationshipId;

        return "redirect:/volunteers/" + volunteer.getPersonId();
    }

    /**
     * @param personDao person dao
     */
    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }

    /**
     * @param volunteerDao volunteer dao
     */
    public void setVolunteerDao(VolunteerDao volunteerDao) {
        this.volunteerDao = volunteerDao;
    }
}
