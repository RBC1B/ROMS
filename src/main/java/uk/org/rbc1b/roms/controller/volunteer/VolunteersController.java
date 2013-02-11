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
import uk.org.rbc1b.roms.db.volunteer.Appointment;
import uk.org.rbc1b.roms.db.volunteer.Fulltime;
import uk.org.rbc1b.roms.db.volunteer.InterviewStatus;
import uk.org.rbc1b.roms.db.volunteer.MaritalStatus;
import uk.org.rbc1b.roms.db.volunteer.RbcStatus;
import uk.org.rbc1b.roms.db.volunteer.Volunteer;

/**
 *
 *
 * @author rahulsingh
 */
@Controller
@RequestMapping("/volunteers")
public class VolunteersController {

    @Autowired
    private VolunteerDao volunteerDao;
    @Autowired
    private PersonDao personDao;
    @Autowired
    private CongregationDao congregationDao;

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
    public String handleNewForm(ModelMap model) {

        // initialise the form bean
        model.addAttribute("volunteer", new VolunteerForm());

        return "volunteers/edit";
    }

    /**
     * Handle the volunteer core details form submission. <p>This handles both new volunteers and editing existing ones
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

        if (form.getCongregationId() == null) {
            volunteer.setCongregation(null);
        } else if (volunteer.getCongregation() == null || !volunteer.getCongregation().getCongregationId().equals(form.getCongregationId())) {
            volunteer.setCongregation(congregationDao.findCongregation(form.getCongregationId()));
        }

        volunteer.setEmail(form.getEmail());
        volunteer.setForename(form.getForename());
        volunteer.setMiddleName(form.getMiddleName());
        volunteer.setSurname(form.getSurname());
        volunteer.setMobile(form.getMobile());
        volunteer.setTelephone(form.getTelephone());
        volunteer.setWorkPhone(form.getWorkPhone());
        volunteer.setBaptismDate(new java.sql.Date(form.getBaptismDate().toDateMidnight().getMillis()));

        if (form.isElder()) {
            volunteer.setAppointment(new Appointment(Appointment.ELDER));
        } else if (form.isMinisterialServant()) {
            volunteer.setAppointment(new Appointment(Appointment.MINISTERIAL_SERVANT));
        } else {
            volunteer.setAppointment(new Appointment(Appointment.PUBLISHER));
        }
        volunteer.setGender(form.getGender());

        if (form.isRegularPioneer()) {
            volunteer.setFulltime(new Fulltime(Fulltime.REGULAR_PIONEER));
        } else {
            volunteer.setFulltime(new Fulltime(Fulltime.PUBLISHER));
        }

        volunteer.setRbcStatus(RbcStatus.ACTIVE);
        volunteer.setMaritalStatus(MaritalStatus.SINGLE);
        volunteer.setInterviewStatus(InterviewStatus.INVITE_DUE);
        volunteerDao.saveVolunteer(volunteer);

//    private Integer emergencyRelationshipId;


        return "redirect:/";
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
