/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.volunteer;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.org.rbc1b.roms.controller.common.model.EntityModel;
import uk.org.rbc1b.roms.controller.common.model.PersonModelFactory;
import uk.org.rbc1b.roms.controller.congregation.CongregationsController;
import uk.org.rbc1b.roms.db.volunteer.Volunteer;
import uk.org.rbc1b.roms.reference.ReferenceDao;

/**
 * Generate the volunteer model.
 *
 * @author oliver
 */
@Component
public class VolunteerModelFactory {

    private static final String BASE_URI = "/volunteers/";
    private static final Integer DAYS_PER_WEEK = 7;
    private static final Map<Integer, Boolean> NO_AVAILABILITY = new HashMap<Integer, Boolean>();
    private ReferenceDao referenceDao;
    private PersonModelFactory personModelFactory;

    static {
        for (int i = 0; i < DAYS_PER_WEEK; i++) {
            NO_AVAILABILITY.put(i, Boolean.FALSE);
        }
    }

    /**
     * Generate the uri used to access the volunteer pages.
     *
     * @param volunteerId optional volunteer id
     * @return uri
     */
    public String generateUri(Integer volunteerId) {
        return volunteerId != null ? BASE_URI + volunteerId : BASE_URI;
    }

    /**
     * Generate the volunteer model to be used in the summary list.
     *
     * @param volunteer volunteer
     * @return model
     */
    public VolunteerListModel generateVolunteerListModel(Volunteer volunteer) {
        VolunteerListModel model = new VolunteerListModel();
        model.setId(volunteer.getPersonId());
        model.setUri(generateUri(volunteer.getPersonId()));

        if (volunteer.getCongregation() != null) {
            EntityModel congregation = new EntityModel();
            congregation.setId(volunteer.getCongregation().getCongregationId());
            congregation.setName(volunteer.getCongregation().getName());
            congregation.setUri(CongregationsController.generateUri(volunteer.getCongregation().getCongregationId()));

            model.setCongregation(congregation);
        }

        model.setEmail(volunteer.getEmail());
        model.setForename(volunteer.getForename());
        model.setMiddleName(volunteer.getMiddleName());
        model.setSurname(volunteer.getSurname());

        model.setUri(generateUri(volunteer.getPersonId()));
        model.setEditUri(generateUri(volunteer.getPersonId()) + "/edit");

        return model;
    }

    /**
     * Generate the model to be used in the volunteer details view.
     *
     * @param volunteer volunteer
     * @return model
     */
    public VolunteerModel generateVolunteerModel(Volunteer volunteer) {
        VolunteerModel model = new VolunteerModel();

        // person model info
        model.setId(volunteer.getPersonId());
        model.setUri(generateUri(volunteer.getPersonId()));
        model.setAddress(volunteer.getAddress());
        model.setBirthDate(volunteer.getBirthDate());
        model.setComments(volunteer.getComments());

        if (volunteer.getCongregation() != null) {
            EntityModel congregation = new EntityModel();
            congregation.setId(volunteer.getCongregation().getCongregationId());
            congregation.setName(volunteer.getCongregation().getName());
            congregation.setUri(CongregationsController.generateUri(volunteer.getCongregation().getCongregationId()));

            model.setCongregation(congregation);
        }

        model.setEmail(volunteer.getEmail());
        model.setForename(volunteer.getForename());
        model.setMiddleName(volunteer.getMiddleName());
        model.setSurname(volunteer.getSurname());
        model.setMobile(volunteer.getMobile());
        model.setTelephone(volunteer.getTelephone());
        model.setWorkPhone(volunteer.getWorkPhone());

        // volunteer specific personal data
        model.setGender(volunteer.getGender());
        model.setStatus(referenceDao.findRBCStatusValues().get(volunteer.getRbcStatusId()));

        if (volunteer.getMaritalStatusId() != null) {
            model.setMaritalStatus(referenceDao.findMaritalStatusValues().get(volunteer.getMaritalStatusId()));
        }

        // spiritual data
        if (volunteer.getAppointmentId() != null) {
            model.setAppointment(referenceDao.findAppointmentValues().get(volunteer.getAppointmentId()));
        }
        model.setBaptismDate(volunteer.getBaptismDate());
        model.setEmergencyContact(personModelFactory.generatePersonModel(volunteer.getEmergencyContact()));
        if (volunteer.getEmergencyContactRelationshipId() != null) {
            model.setEmergencyContactRelationship(referenceDao.findRelationshipValues().get(volunteer.getEmergencyContactRelationshipId()));
        }
        if (volunteer.getFulltimeId() != null) {
            model.setFulltime(referenceDao.findFulltimeValues().get(volunteer.getFulltimeId()));
        }
        model.setSpouse(personModelFactory.generatePersonModel(volunteer.getSpouse()));

        // skills
        model.setTrades(volunteer.getTrades().isEmpty() ? null : volunteer.getTrades());


        // rbs status
        model.setFormDate(volunteer.getFormDate());
        model.setJoinedDate(volunteer.getJoinedDate());
        model.setBadgeIssueDate(volunteer.getBadgeIssueDate());
        model.setInterviewDate(volunteer.getBadgeIssueDate());
        model.setInterviewerA(personModelFactory.generatePersonModel(volunteer.getInterviewerA()));
        model.setInterviewerB(personModelFactory.generatePersonModel(volunteer.getInterviewerB()));
        model.setInterviewComments(volunteer.getInterviewComments());
        model.setAvailability(generateAvailability(volunteer.getAvailability()));
        model.setOversight(volunteer.isOversight());
        model.setOversightComments(volunteer.getOversightComments());
        model.setReliefAbroad(volunteer.isReliefAbroad());
        model.setReliefAbroadComments(volunteer.getReliefAbroadComments());
        model.setReliefUK(volunteer.isReliefUK());
        model.setReliefUKComments(volunteer.getReliefUKComments());
        model.setHhcFormCode(volunteer.getHhcFormCode());

        model.setUri(generateUri(volunteer.getPersonId()));
        model.setEditUri(generateUri(volunteer.getPersonId()) + "/edit");

        return model;
    }

    private Map<Integer, Boolean> generateAvailability(String availability) {
        if (availability == null || availability.length() != 7) {
            return NO_AVAILABILITY;
        }

        Map<Integer, Boolean> availabilityMap = new HashMap<Integer, Boolean>(DAYS_PER_WEEK);
        for (int i = 0; i < DAYS_PER_WEEK; i++) {
            availabilityMap.put(i, availability.charAt(i) == 'T');
        }
        return availabilityMap;
    }

    @Autowired
    public void setPersonModelFactory(PersonModelFactory personModelFactory) {
        this.personModelFactory = personModelFactory;
    }

    @Autowired
    public void setReferenceDao(ReferenceDao referenceDao) {
        this.referenceDao = referenceDao;
    }
}
