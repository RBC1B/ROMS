/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.qualification;

import org.springframework.stereotype.Component;

/**
 * Generate the qualifications models.
 *
 * @author oliver.elder.esq
 */
@Component
public class QualificationModelFactory {

    private static final String BASE_URI = "/qualifications/";

    /**
     * Generate the uri used to access the qualification pages.
     *
     * @param qualificationId optional qualification id
     * @return uri
     */
    public String generateUri(Integer qualificationId) {
        return qualificationId != null ? BASE_URI + qualificationId : BASE_URI;
    }
}
