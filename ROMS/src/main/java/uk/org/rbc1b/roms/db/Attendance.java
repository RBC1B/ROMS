/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db;

/**
 *
 * @author ramindursingh
 */
public class Attendance {
    private AttendanceID attendanceid;
    private String department;
    
    public Attendance(){}
    public Attendance(AttendanceID aid, String dept){
        this.attendanceid=aid;
        this.department=dept;
    }

    /**
     * @return the attendanceid
     */
    public AttendanceID getAttendanceid() {
        return attendanceid;
    }

    /**
     * @param attendanceid the attendanceid to set
     */
    public void setAttendanceid(AttendanceID attendanceid) {
        this.attendanceid = attendanceid;
    }

    /**
     * @return the department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * @param department the department to set
     */
    public void setDepartment(String department) {
        this.department = department;
    }
}
