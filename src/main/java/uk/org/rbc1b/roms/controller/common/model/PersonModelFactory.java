/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.common.model;

import org.springframework.stereotype.Component;
import uk.org.rbc1b.roms.controller.congregation.CongregationsController;
import uk.org.rbc1b.roms.db.Person;

/**
 * Create a person model.
 *
 * @author oliver
 */
@Component
public class PersonModelFactory {

    private static final String BASE_URI = "/persons/";

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

        if (person.getCongregation() != null) {
            EntityModel congregation = new EntityModel();
            congregation.setId(person.getCongregation().getCongregationId());
            congregation.setName(person.getCongregation().getName());
            congregation.setUri(CongregationsController.generateUri(person.getCongregation().getCongregationId()));

            model.setCongregation(congregation);
        }

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
}
