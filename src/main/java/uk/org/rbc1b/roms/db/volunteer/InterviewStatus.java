/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db.volunteer;

/**
 *
 * @author oliver.elder.esq
 */
public class InterviewStatus {

    public static final InterviewStatus INVITE_DUE = new InterviewStatus(1);
    private Integer interviewStatusId;
    private String description;

    /**
     * Default constructor.
     */
    public InterviewStatus() {
        // do nothing
    }

    /**
     * Constructor.
     *
     * @param interviewStatusId id
     */
    public InterviewStatus(Integer interviewStatusId) {
        this.interviewStatusId = interviewStatusId;
    }

    public Integer getInterviewStatusId() {
        return interviewStatusId;
    }

    public void setInterviewStatusId(Integer interviewStatusId) {
        this.interviewStatusId = interviewStatusId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "InterviewStatus{" + "interviewStatusId=" + interviewStatusId + '}';
    }
}
