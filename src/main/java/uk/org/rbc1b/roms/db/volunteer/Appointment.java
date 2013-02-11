/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db.volunteer;

/**
 *
 * @author oliver.elder.esq
 */
public class Appointment {

    public static final Integer ELDER = 1;
    public static final Integer MINISTERIAL_SERVANT = 2;
    public static final Integer PUBLISHER = 3;
    private Integer appointmentId;
    private String description;

    /**
     * Default constructor.
     */
    public Appointment() {
        // do nothing
    }

    /**
     * Constructor.
     *
     * @param appointmentId appointment id
     */
    public Appointment(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Integer getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Appointment{" + "appointmentId=" + appointmentId + '}';
    }
}
