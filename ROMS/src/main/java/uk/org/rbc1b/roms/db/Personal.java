/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db;

import java.sql.Date;

/**
 *
 * @author ramindursingh
 */
public class Personal {
    private Long id;
    private Date dob;
    private String gender;
    private String maritalstatus;
    private String baptism;
    private String oversight;
    private String reliefuk;
    private String reliefabroad;
    private Date interviewdate;
    private String interviewby;
    private String interviewresult;
    private Date receiveddate;
    private String acknowledge;
    private String assignletter;
    private String pcomments;
    private String interviewstatus;
    private String reliefukanswer;
    private String reliefabroadanswer;
    private String khcformcode;
    private Date badgeissue;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the dob
     */
    public Date getDob() {
        return dob;
    }

    /**
     * @param dob the dob to set
     */
    public void setDob(Date dob) {
        this.dob = dob;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the maritalstatus
     */
    public String getMaritalstatus() {
        return maritalstatus;
    }

    /**
     * @param maritalstatus the maritalstatus to set
     */
    public void setMaritalstatus(String maritalstatus) {
        this.maritalstatus = maritalstatus;
    }

    /**
     * @return the baptism
     */
    public String getBaptism() {
        return baptism;
    }

    /**
     * @param baptism the baptism to set
     */
    public void setBaptism(String baptism) {
        this.baptism = baptism;
    }

    /**
     * @return the oversight
     */
    public String getOversight() {
        return oversight;
    }

    /**
     * @param oversight the oversight to set
     */
    public void setOversight(String oversight) {
        this.oversight = oversight;
    }

    /**
     * @return the reliefuk
     */
    public String getReliefuk() {
        return reliefuk;
    }

    /**
     * @param reliefuk the reliefuk to set
     */
    public void setReliefuk(String reliefuk) {
        this.reliefuk = reliefuk;
    }

    /**
     * @return the reliefabroad
     */
    public String getReliefabroad() {
        return reliefabroad;
    }

    /**
     * @param reliefabroad the reliefabroad to set
     */
    public void setReliefabroad(String reliefabroad) {
        this.reliefabroad = reliefabroad;
    }

    /**
     * @return the interviewdate
     */
    public Date getInterviewdate() {
        return interviewdate;
    }

    /**
     * @param interviewdate the interviewdate to set
     */
    public void setInterviewdate(Date interviewdate) {
        this.interviewdate = interviewdate;
    }

    /**
     * @return the interviewby
     */
    public String getInterviewby() {
        return interviewby;
    }

    /**
     * @param interviewby the interviewby to set
     */
    public void setInterviewby(String interviewby) {
        this.interviewby = interviewby;
    }

    /**
     * @return the interviewresult
     */
    public String getInterviewresult() {
        return interviewresult;
    }

    /**
     * @param interviewresult the interviewresult to set
     */
    public void setInterviewresult(String interviewresult) {
        this.interviewresult = interviewresult;
    }

    /**
     * @return the receiveddate
     */
    public Date getReceiveddate() {
        return receiveddate;
    }

    /**
     * @param receiveddate the receiveddate to set
     */
    public void setReceiveddate(Date receiveddate) {
        this.receiveddate = receiveddate;
    }

    /**
     * @return the acknowledge
     */
    public String getAcknowledge() {
        return acknowledge;
    }

    /**
     * @param acknowledge the acknowledge to set
     */
    public void setAcknowledge(String acknowledge) {
        this.acknowledge = acknowledge;
    }

    /**
     * @return the assignletter
     */
    public String getAssignletter() {
        return assignletter;
    }

    /**
     * @param assignletter the assignletter to set
     */
    public void setAssignletter(String assignletter) {
        this.assignletter = assignletter;
    }

    /**
     * @return the pcomments
     */
    public String getPcomments() {
        return pcomments;
    }

    /**
     * @param pcomments the pcomments to set
     */
    public void setPcomments(String pcomments) {
        this.pcomments = pcomments;
    }

    /**
     * @return the interviewstatus
     */
    public String getInterviewstatus() {
        return interviewstatus;
    }

    /**
     * @param interviewstatus the interviewstatus to set
     */
    public void setInterviewstatus(String interviewstatus) {
        this.interviewstatus = interviewstatus;
    }

    /**
     * @return the reliefukanswer
     */
    public String getReliefukanswer() {
        return reliefukanswer;
    }

    /**
     * @param reliefukanswer the reliefukanswer to set
     */
    public void setReliefukanswer(String reliefukanswer) {
        this.reliefukanswer = reliefukanswer;
    }

    /**
     * @return the reliefabroadanswer
     */
    public String getReliefabroadanswer() {
        return reliefabroadanswer;
    }

    /**
     * @param reliefabroadanswer the reliefabroadanswer to set
     */
    public void setReliefabroadanswer(String reliefabroadanswer) {
        this.reliefabroadanswer = reliefabroadanswer;
    }

    /**
     * @return the khcformcode
     */
    public String getKhcformcode() {
        return khcformcode;
    }

    /**
     * @param khcformcode the khcformcode to set
     */
    public void setKhcformcode(String khcformcode) {
        this.khcformcode = khcformcode;
    }

    /**
     * @return the badgeissue
     */
    public Date getBadgeissue() {
        return badgeissue;
    }

    /**
     * @param badgeissue the badgeissue to set
     */
    public void setBadgeissue(Date badgeissue) {
        this.badgeissue = badgeissue;
    }
    public String getHTMLTable(){
        String html="<table>"
                + "<tr><td>test</td></tr>"
                + "</table>";
        return html;
    }
}
