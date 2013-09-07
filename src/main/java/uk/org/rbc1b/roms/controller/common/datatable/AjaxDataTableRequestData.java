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
package uk.org.rbc1b.roms.controller.common.datatable;

import java.lang.reflect.Field;
import uk.org.rbc1b.roms.controller.common.SortDirection;
import uk.org.rbc1b.roms.db.common.SearchCriteria;

/**
 * The request parameters made by the DataTables when generating the results using the server side processing.
 * <p>
 * Datatables used ugly hungarian notation for the parameter names, so the setters do not match the getters, which
 * return a sanitised version.
 * <p>
 * The column data props, which we capture to determine the column we are sorting on, pass in the index as part of the
 * name. Nasty. We support up to 10 columns (index 0-9)
 * @author oliver.elder.esq
 */
public class AjaxDataTableRequestData {
    // special case - to allow us to access these fields when looking up the sort value
    // in this and child classes, change the visibility. We could make this private and use
    // the this.getClass().getDeclaredField() method, but child classes have to loop through
    // super classes to try to find it
    // CSOFF: VisibilityModifier
    public String mDataProp0;
    public String mDataProp1;
    public String mDataProp2;
    public String mDataProp3;
    public String mDataProp4;
    public String mDataProp5;
    public String mDataProp6;
    public String mDataProp7;
    public String mDataProp8;
    public String mDataProp9;
    // CSON: VisibilityModifier
    private Integer echo;
    private String search;
    private SortDir sortDir;
    private Integer displayLength;
    private Integer displayStart;
    private Integer sortCol0;

    /**
     * Populate the search criteria from this search request data.
     * @param searchCriteria search criteria to populate
     */
    public void populateSearchCriteria(SearchCriteria searchCriteria) {
        searchCriteria.setSearch(search);
        searchCriteria.setSortValue(getSortValue());
        searchCriteria.setSortDirection(getSortDirection());
        searchCriteria.setStartIndex(displayStart);
        searchCriteria.setMaxResults(displayLength);
    }

    /**
     * Get the sort value. This is based on matching the underlying mDataProp value, indexed by the sortCol0
     * @return sort value
     */
    public String getSortValue() {
        try {
            Field field = this.getClass().getField("mDataProp" + sortCol0);
            field.setAccessible(true);
            return (String) field.get(this);
        } catch (IllegalArgumentException ex) {
            throw new AjaxDataTableRequestException("Failed to look up field for sort column " + sortCol0, ex);
        } catch (IllegalAccessException ex) {
            throw new AjaxDataTableRequestException("Failed to look up field for sort column " + sortCol0, ex);
        } catch (NoSuchFieldException ex) {
            throw new AjaxDataTableRequestException("Failed to look up field for sort column " + sortCol0, ex);
        } catch (SecurityException ex) {
            throw new AjaxDataTableRequestException("Failed to look up field for sort column " + sortCol0, ex);
        }
    }

    public Integer getEcho() {
        return echo;
    }

    public String getSearch() {
        return search;
    }

    /**
     * @return sort direction
     */
    public SortDirection getSortDirection() {
        if (sortDir == null) {
            return null;
        }

        return sortDir == SortDir.asc ? SortDirection.ASCENDING : SortDirection.DESCENDING;
    }

    public Integer getDisplayLength() {
        return displayLength;
    }

    public Integer getDisplayStart() {
        return displayStart;
    }

    /**
     * The method name doesn't match the variable to allow us to drop the hungarian notation.
     * @param sEcho echo
     */
    public void setsEcho(Integer sEcho) {
        this.echo = sEcho;
    }

    /**
     * The method name doesn't match the variable to allow us to drop the hungarian notation.
     * @param sSearch search
     */
    public void setsSearch(String sSearch) {
        this.search = sSearch;
    }

    /**
     * The method name doesn't match the variable to allow us to drop the hungarian notation.
     * @param iDisplayLength number of results
     */
    public void setiDisplayLength(Integer iDisplayLength) {
        this.displayLength = iDisplayLength;
    }

    /**
     * The method name doesn't match the variable to allow us to drop the hungarian notation.
     * @param iDisplayStart start index
     */
    public void setiDisplayStart(Integer iDisplayStart) {
        this.displayStart = iDisplayStart;
    }

    // We have to switch off the parameter name check to allow us to pick up the data tables variables
    // CSOFF: ParameterNameCheck
    // CSOFF: MethodNameCheck
    /**
     * The method name doesn't match the variable to allow us to drop the hungarian notation.
     * @param iSortCol_0 sort column
     */
    public void setiSortCol_0(Integer iSortCol_0) {
        this.sortCol0 = iSortCol_0;
    }

    /**
     * The method name doesn't match the variable to allow us to drop the hungarian notation.
     * @param sSortDir_0 sort direction
     */
    public void setsSortDir_0(SortDir sSortDir_0) {
        this.sortDir = sSortDir_0;
    }

    /**
     * @param mDataProp_0 data property
     */
    public void setmDataProp_0(String mDataProp_0) {
        this.mDataProp0 = mDataProp_0;
    }

    /**
     * @param mDataProp_1 data property
     */
    public void setmDataProp_1(String mDataProp_1) {
        this.mDataProp1 = mDataProp_1;
    }

    /**
     * @param mDataProp_2 data property
     */
    public void setmDataProp_2(String mDataProp_2) {
        this.mDataProp2 = mDataProp_2;
    }

    /**
     * @param mDataProp_3 data property
     */
    public void setmDataProp_3(String mDataProp_3) {
        this.mDataProp3 = mDataProp_3;
    }

    /**
     * @param mDataProp_4 data property
     */
    public void setmDataProp_4(String mDataProp_4) {
        this.mDataProp4 = mDataProp_4;
    }

    /**
     * @param mDataProp_5 data property
     */
    public void setmDataProp_5(String mDataProp_5) {
        this.mDataProp5 = mDataProp_5;
    }

    /**
     * @param mDataProp_6 data property
     */
    public void setmDataProp_6(String mDataProp_6) {
        this.mDataProp6 = mDataProp_6;
    }

    /**
     * @param mDataProp_7 data property
     */
    public void setmDataProp_7(String mDataProp_7) {
        this.mDataProp7 = mDataProp_7;
    }

    /**
     * @param mDataProp_8 data property
     */
    public void setmDataProp_8(String mDataProp_8) {
        this.mDataProp8 = mDataProp_8;
    }

    /**
     * @param mDataProp_9 data property
     */
    public void setmDataProp_9(String mDataProp_9) {
        this.mDataProp9 = mDataProp_9;
    }

    // CSON: ParameterNameCheck
    // CSON: MethodNameCheck
    /**
     * Sort direction.
     */
    public static enum SortDir {

        asc, desc;
    }
}
