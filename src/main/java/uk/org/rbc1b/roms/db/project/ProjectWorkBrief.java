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
package uk.org.rbc1b.roms.db.project;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import uk.org.rbc1b.roms.db.DefaultUpdateAuditable;

/**
 * The work required for a given feature in a project.
 * @author oliver.elder.esq
 */
@Audited
public class ProjectWorkBrief extends DefaultUpdateAuditable {
    private static final long serialVersionUID = -4742828414469733445L;
    private Integer projectWorkBriefId;
    private Project project;
    private WorkFeature workFeature;
    private String brief;

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Integer getProjectWorkBriefId() {
        return projectWorkBriefId;
    }

    public void setProjectWorkBriefId(Integer projectWorkBriefId) {
        this.projectWorkBriefId = projectWorkBriefId;
    }

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    public WorkFeature getWorkFeature() {
        return workFeature;
    }

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    public void setWorkFeature(WorkFeature workFeature) {
        this.workFeature = workFeature;
    }

    @Override
    public String toString() {
        return "ProjectWorkBrief{" + "projectWorkBriefId=" + projectWorkBriefId + '}';
    }
}
