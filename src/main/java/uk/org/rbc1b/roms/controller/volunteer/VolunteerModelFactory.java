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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.org.rbc1b.roms.controller.common.model.EntityModel;
import uk.org.rbc1b.roms.controller.common.model.PersonModelFactory;
import uk.org.rbc1b.roms.controller.congregation.CongregationModelFactory;
import uk.org.rbc1b.roms.controller.department.DepartmentModelFactory;
import uk.org.rbc1b.roms.controller.qualification.QualificationModelFactory;
import uk.org.rbc1b.roms.controller.skill.SkillModelFactory;
import uk.org.rbc1b.roms.db.Congregation;
import uk.org.rbc1b.roms.db.CongregationDao;
import uk.org.rbc1b.roms.db.reference.ReferenceDao;
import uk.org.rbc1b.roms.db.volunteer.Department;
import uk.org.rbc1b.roms.db.volunteer.DepartmentDao;
import uk.org.rbc1b.roms.db.volunteer.Qualification;
import uk.org.rbc1b.roms.db.volunteer.QualificationDao;
import uk.org.rbc1b.roms.db.volunteer.Skill;
import uk.org.rbc1b.roms.db.volunteer.SkillDao;
import uk.org.rbc1b.roms.db.volunteer.Volunteer;
import uk.org.rbc1b.roms.db.volunteer.VolunteerQualification;
import uk.org.rbc1b.roms.db.volunteer.VolunteerSkill;

/**
 * Generate the volunteer model.
 * @author oliver
 */
@Component
public class VolunteerModelFactory {

    private static final String BASE_URI = "/volunteers/";
    private static final Integer DAYS_PER_WEEK = 7;
    private static final Map<Integer, Boolean> NO_AVAILABILITY = new HashMap<Integer, Boolean>();
    private ReferenceDao referenceDao;
    private PersonModelFactory personModelFactory;
    private CongregationDao congregationDao;
    private SkillDao skillDao;
    private DepartmentDao departmentDao;
    private QualificationDao qualificationDao;

    static {
        for (int i = 0; i < DAYS_PER_WEEK; i++) {
            NO_AVAILABILITY.put(i, Boolean.FALSE);
        }
    }

    /**
     * Generate the uri used to access the volunteer pages.
     * @param volunteerId optional volunteer id
     * @return uri
     */
    public static String generateUri(Integer volunteerId) {
        return volunteerId != null ? BASE_URI + volunteerId : BASE_URI;
    }

    /**
     * Generate the volunteer model to be used in the summary list.
     * @param volunteer volunteer
     * @return model
     */
    public VolunteerListModel generateVolunteerListModel(Volunteer volunteer) {
        VolunteerListModel model = new VolunteerListModel();
        model.setId(volunteer.getPersonId());
        model.setUri(generateUri(volunteer.getPersonId()));
        if (volunteer.getCongregation() != null) {
            model.setCongregation(generateCongregationModel(volunteer.getCongregation().getCongregationId()));
        }
        model.setEmail(volunteer.getEmail());
        model.setForename(volunteer.getForename());
        model.setMiddleName(volunteer.getMiddleName());
        model.setSurname(volunteer.getSurname());

        model.setUri(generateUri(volunteer.getPersonId()));

        return model;
    }

    /**
     * Generate the model to be used in the volunteer details view.
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
            model.setCongregation(generateCongregationModel(volunteer.getCongregation().getCongregationId()));
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
            model.setEmergencyContactRelationship(referenceDao.findRelationshipValues().get(
                    volunteer.getEmergencyContactRelationshipId()));
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
        model.setInterviewerA(personModelFactory.generateUserModel(volunteer.getInterviewerA()));
        model.setInterviewerB(personModelFactory.generateUserModel(volunteer.getInterviewerB()));
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
        model.setEditNameUri(generateUri(volunteer.getPersonId()) + "/name");
        model.setEditCommentsUri(generateUri(volunteer.getPersonId()) + "/comments");
        model.setEditSpiritualUri(generateUri(volunteer.getPersonId()) + "/spiritual/edit");
        model.setEditPersonalUri(generateUri(volunteer.getPersonId()) + "/personal/edit");
        model.setEditRbcStatusUri(generateUri(volunteer.getPersonId()) + "/rbc-status/edit");

        return model;
    }

    private EntityModel generateCongregationModel(Integer congregationId) {
        if (congregationId == null) {
            return null;
        }

        Congregation congregation = congregationDao.findCongregation(congregationId);

        EntityModel congregationModel = new EntityModel();
        congregationModel.setId(congregation.getCongregationId());
        congregationModel.setName(congregation.getName());
        congregationModel.setUri(CongregationModelFactory.generateUri(congregation.getCongregationId()));

        return congregationModel;
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

    /**
     * Generate the models for the volunteer skills.
     * @param volunteerSkills list of volunteer skills
     * @return model list
     */
    public List<VolunteerSkillModel> generateVolunteerSkillsModel(List<VolunteerSkill> volunteerSkills) {
        if (CollectionUtils.isEmpty(volunteerSkills)) {
            return null;
        }

        List<VolunteerSkillModel> modelList = new ArrayList<VolunteerSkillModel>(volunteerSkills.size());
        for (VolunteerSkill volunteerSkill : volunteerSkills) {
            VolunteerSkillModel model = new VolunteerSkillModel();

            model.setId(volunteerSkill.getVolunteerSkillId());

            Skill skill = skillDao.findSkill(volunteerSkill.getSkillId());
            Department department = departmentDao.findDepartment(skill.getDepartment().getDepartmentId());

            model.setComments(volunteerSkill.getComments());

            EntityModel departmentModel = new EntityModel();
            departmentModel.setName(department.getName());
            departmentModel.setId(department.getDepartmentId());
            departmentModel.setUri(DepartmentModelFactory.generateUri(department.getDepartmentId()));

            model.setDepartment(departmentModel);
            model.setDescription(skill.getDescription());
            model.setLevel(volunteerSkill.getLevel());

            EntityModel skillModel = new EntityModel();
            skillModel.setName(skill.getName());
            skillModel.setId(skill.getSkillId());
            skillModel.setUri(SkillModelFactory.generateUri(volunteerSkill.getSkillId()));

            model.setSkill(skillModel);
            model.setTrainingDate(volunteerSkill.getTrainingDate());
            model.setTrainingResults(volunteerSkill.getTrainingResults());
            modelList.add(model);

        }

        return modelList;
    }

    /**
     * Generate the models for the volunteer qualifications.
     * @param volunteerQualifications list of volunteer qualifications
     * @return model list
     */
    public List<VolunteerQualificationModel> generateVolunteerQualificationsModel(
            List<VolunteerQualification> volunteerQualifications) {
        if (CollectionUtils.isEmpty(volunteerQualifications)) {
            return null;
        }

        List<VolunteerQualificationModel> modelList = new ArrayList<VolunteerQualificationModel>(
                volunteerQualifications.size());
        for (VolunteerQualification volunteerQualification : volunteerQualifications) {
            VolunteerQualificationModel model = new VolunteerQualificationModel();

            model.setId(volunteerQualification.getVolunteerQualificationId());

            model.setAppearOnBadge(true);
            model.setComments(volunteerQualification.getComments());

            Qualification qualification = qualificationDao.findQualification(volunteerQualification
                    .getQualificationId());
            model.setDescription(qualification.getDescription());

            EntityModel qualificationModel = new EntityModel();
            qualificationModel.setId(qualification.getQualificationId());
            qualificationModel.setName(qualification.getName());
            qualificationModel.setUri(QualificationModelFactory.generateUri(qualification.getQualificationId()));

            model.setQualification(qualificationModel);

            modelList.add(model);
        }

        return modelList;
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
    public void setSkillDao(SkillDao skillDao) {
        this.skillDao = skillDao;
    }

    @Autowired
    public void setCongregationDao(CongregationDao congregationDao) {
        this.congregationDao = congregationDao;
    }

    @Autowired
    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Autowired
    public void setQualificationDao(QualificationDao qualificationDao) {
        this.qualificationDao = qualificationDao;
    }

}
