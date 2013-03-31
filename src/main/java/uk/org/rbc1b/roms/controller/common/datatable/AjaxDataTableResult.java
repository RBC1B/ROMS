/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.common.datatable;

import java.util.List;

/**
 * Wrapper class for DataTable results.
 * <p>The field names are specific to the data table naming
 *
 * @author oliver.elder.esq
 * @param <T> list data type
 */
public class AjaxDataTableResult<T> {

    private String sEcho;
    private List<T> aaData;
    private Integer iTotalRecords;
    private Integer iTotalDisplayRecords;

    /**
     * @return List of data making up each result row
     */
    public List<T> getAaData() {
        return aaData;
    }

    /**
     * @param aaData List of data making up each result row
     */
    public void setAaData(List<T> aaData) {
        this.aaData = aaData;
    }

    /**
     * @return total number of records to display
     */
    public Integer getiTotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

    /**
     * @param iTotalDisplayRecords total number of records to display
     */
    public void setiTotalDisplayRecords(Integer iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }

    /**
     * @return total number of records across all pages
     */
    public Integer getiTotalRecords() {
        return iTotalRecords;
    }

    /**
     * @param iTotalRecords total number of records across all pages
     */
    public void setiTotalRecords(Integer iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
    }

    /**
     * @return unique identifier for request, echoed back to caller
     */
    public String getsEcho() {
        return sEcho;
    }

    /**
     * @param sEcho unique identifier for request, echoed back to caller
     */
    public void setsEcho(String sEcho) {
        this.sEcho = sEcho;
    }
}
