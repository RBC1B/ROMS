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
package uk.org.rbc1b.roms.controller.volunteer;

import javax.validation.constraints.NotNull;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Form data used when editing a the spiritual data associated with the volunteer.
 *
 * @author oliver.elder.esq
 */
public class VolunteerSpiritualForm {

    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private DateTime baptismDate;
    @NotNull
    private Integer congregationId;
    @NotNull
    private String congregationName;
    private Integer fulltimeId;
    private String appointmentCode;

    public DateTime getBaptismDate() {
        return baptismDate;
    }

    public void setBaptismDate(DateTime baptismDate) {
        this.baptismDate = baptismDate;
    }

    public Integer getCongregationId() {
        return congregationId;
    }

    public void setCongregationId(Integer congregationId) {
        this.congregationId = congregationId;
    }

    public String getCongregationName() {
        return congregationName;
    }

    public void setCongregationName(String congregationName) {
        this.congregationName = congregationName;
    }

    public Integer getFulltimeId() {
        return fulltimeId;
    }

    public void setFulltimeId(Integer fulltimeId) {
        this.fulltimeId = fulltimeId;
    }

    public String getAppointmentCode() {
        return appointmentCode;
    }

    public void setAppointmentCode(String appointmentCode) {
        this.appointmentCode = appointmentCode;
    }

}
