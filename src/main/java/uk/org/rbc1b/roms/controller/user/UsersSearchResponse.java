/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.user;

import java.util.List;

/**
 * Wrapper around the user search results.
 *
 * @author oliver.elder.esq
 */
public class UsersSearchResponse {

    private List<UserSearchResult> results;

    public List<UserSearchResult> getResults() {
        return results;
    }

    public void setResults(List<UserSearchResult> results) {
        this.results = results;
    }
}
