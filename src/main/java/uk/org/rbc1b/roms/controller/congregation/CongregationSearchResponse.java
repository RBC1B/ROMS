/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.congregation;

import java.util.List;

/**
 * Congregation references after a search.
 *
 * @author oliver.elder.esq
 */
public class CongregationSearchResponse {

    private List<CongregationSearchResult> results;

    public List<CongregationSearchResult> getResults() {
        return results;
    }

    public void setResults(List<CongregationSearchResult> results) {
        this.results = results;
    }
}
