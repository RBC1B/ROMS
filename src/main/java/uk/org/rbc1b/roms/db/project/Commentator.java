/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db.project;

import uk.org.rbc1b.roms.db.volunteer.Department;

/**
 *
 * @author oliver.elder.esq
 */
public class Commentator {

    private Integer commentatorId;
    private Department department;

    public Integer getCommentatorId() {
        return commentatorId;
    }

    public void setCommentatorId(Integer commentatorId) {
        this.commentatorId = commentatorId;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Commentator{" + "commentatorId=" + commentatorId + '}';
    }
}
