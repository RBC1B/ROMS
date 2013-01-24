/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.person;

import java.util.List;

/**
 * Root element for the person search response.
 *
 * @author oliver.elder.esq
 */
public class PersonsSearchResponse {

    private List<PersonSearchResult> results;

    public List<PersonSearchResult> getResults() {
        return results;
    }

    public void setResults(List<PersonSearchResult> results) {
        this.results = results;
    }
}
