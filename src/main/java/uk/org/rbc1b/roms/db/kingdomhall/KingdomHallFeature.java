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
package uk.org.rbc1b.roms.db.kingdomhall;

import java.io.Serializable;
import java.util.Date;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import uk.org.rbc1b.roms.db.UpdateAuditable;

/**
 * An aspect of a kingdom hall, e.g. car park, that may need fixing one day.
 * @author oliver.elder.esq
 */
@Audited
public class KingdomHallFeature implements UpdateAuditable, Serializable {
    private static final long serialVersionUID = -6662820261133712777L;
    private Integer kingdomHallFeatureId;
    private KingdomHall kingdomHall;
    private HallFeature hallFeature;
    private String comments;
    private Date updateTime;
    private Integer updatedBy;

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    public HallFeature getHallFeature() {
        return hallFeature;
    }

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    public void setHallFeature(HallFeature hallFeature) {
        this.hallFeature = hallFeature;
    }

    public KingdomHall getKingdomHall() {
        return kingdomHall;
    }

    public void setKingdomHall(KingdomHall kingdomHall) {
        this.kingdomHall = kingdomHall;
    }

    public Integer getKingdomHallFeatureId() {
        return kingdomHallFeatureId;
    }

    public void setKingdomHallFeatureId(Integer kingdomHallFeatureId) {
        this.kingdomHallFeatureId = kingdomHallFeatureId;
    }

    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public String toString() {
        return "KingdomHallFeature{" + "kingdomHallFeatureId=" + kingdomHallFeatureId + '}';
    }
}
