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
