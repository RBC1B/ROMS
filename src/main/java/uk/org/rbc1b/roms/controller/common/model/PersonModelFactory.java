/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.common.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.org.rbc1b.roms.controller.congregation.CongregationsController;
import uk.org.rbc1b.roms.db.Congregation;
import uk.org.rbc1b.roms.db.CongregationDao;
import uk.org.rbc1b.roms.db.Person;
import uk.org.rbc1b.roms.db.application.User;

/**
 * Create a person model.
 *
 * @author oliver
 */
@Component
public class PersonModelFactory {

    private static final String BASE_URI = "/persons/";
    private CongregationDao congregationDao;

    /**
     * Generate the uri used to access the person pages.
     *
     * @param personId optional volunteer id
     * @return uri
     */
    public String generateUri(Integer personId) {
        return personId != null ? BASE_URI + personId : BASE_URI;
    }

    /**
     * Create a person model.
     *
     * @param person person
     * @return model
     */
    public PersonModel generatePersonModel(Person person) {

        if (person == null) {
            return null;
        }

        PersonModel model = new PersonModel();
        model.setId(person.getPersonId());
        model.setUri(generateUri(person.getPersonId()));

        model.setAddress(person.getAddress());
        model.setBirthDate(person.getBirthDate());
        model.setComments(person.getComments());
        model.setCongregation(generateCongregationModel(person.getCongregationId()));
        model.setEmail(person.getEmail());
        model.setForename(person.getForename());
        model.setMiddleName(person.getMiddleName());
        model.setSurname(person.getSurname());
        model.setMobile(person.getMobile());
        model.setTelephone(person.getTelephone());
        model.setWorkPhone(person.getWorkPhone());

        model.setUri(generateUri(person.getPersonId()));
        model.setEditUri(generateUri(person.getPersonId()) + "/edit");

        return model;
    }

    /**
     * Generate the user model. We point the user uri to the person page.
     * @param user user
     * @return model
     */
    public EntityModel generateUserModel(User user) {
        EntityModel model = new EntityModel();
        model.setName(user.getUserName());
        model.setId(user.getPersonId());
        model.setUri(generateUri(user.getPersonId()));
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
        congregationModel.setUri(CongregationsController.generateUri(congregation.getCongregationId()));

        return congregationModel;
    }

    @Autowired
    public void setCongregationDao(CongregationDao congregationDao) {
        this.congregationDao = congregationDao;
    }
}
