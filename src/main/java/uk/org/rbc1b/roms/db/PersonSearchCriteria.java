/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db;

import uk.org.rbc1b.roms.controller.common.SortDirection;

/**
 * Criteria used when searching for a person.
 *
 * @author oliver.elder.esq
 */
public class PersonSearchCriteria {
    private static final int DEFAULT_MAX_RESULTS = 10;
    private static final int DEFAULT_START_INDEX = 0;
    private Integer maxResults = DEFAULT_MAX_RESULTS;
    private String search;
    private Integer startIndex = DEFAULT_START_INDEX;
    private SortDirection sortDirection;
    private String sortValue;

    public Integer getMaxResults() {
        return maxResults;
    }

    public void setMaxResults(Integer maxResults) {
        this.maxResults = maxResults;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public SortDirection getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(SortDirection sortDirection) {
        this.sortDirection = sortDirection;
    }

    public String getSortValue() {
        return sortValue;
    }

    public void setSortValue(String sortValue) {
        this.sortValue = sortValue;
    }
}
