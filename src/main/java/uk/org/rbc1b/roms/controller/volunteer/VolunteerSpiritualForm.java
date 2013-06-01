/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.volunteer;

import javax.validation.constraints.NotNull;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Form data associated with editing a the spiritual data associated with the volunteer.
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
    private Integer appointmentId;

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

    public Integer getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }
}
