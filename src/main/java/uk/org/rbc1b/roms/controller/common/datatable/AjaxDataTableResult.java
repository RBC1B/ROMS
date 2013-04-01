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
    private int iTotalRecords;
    private int iTotalDisplayRecords;

    /**
     * @return List of data making up each result row
     */
    public List<T> getAaData() {
        return aaData;
    }

    /**
     * The method name doesn't match the variable to allow us to drop the hungarian notation.
     * @param records List of data making up each result row
     */
    public void setRecords(List<T> records) {
        this.aaData = records;
    }

    /**
     * @return total number of records to display
     */
    public int getiTotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

    /**
     * The method name doesn't match the variable to allow us to drop the hungarian notation.
     * @param totalDisplayRecords total number of records to display
     */
    public void setTotalDisplayRecords(int totalDisplayRecords) {
        this.iTotalDisplayRecords = totalDisplayRecords;
    }

    /**
     * @return total number of records across all pages
     */
    public int getiTotalRecords() {
        return iTotalRecords;
    }

    /**
     * The method name doesn't match the variable to allow us to drop the hungarian notation.
     * @param totalRecords total number of records across all pages
     */
    public void setTotalRecords(int totalRecords) {
        this.iTotalRecords = totalRecords;
    }

    /**
     * @return unique identifier for request, echoed back to caller
     */
    public String getsEcho() {
        return sEcho;
    }

    /**
     * The method name doesn't match the variable to allow us to drop the hungarian notation.
     * @param echo unique identifier for request, echoed back to caller
     */
    public void setEcho(String echo) {
        this.sEcho = echo;
    }
}
