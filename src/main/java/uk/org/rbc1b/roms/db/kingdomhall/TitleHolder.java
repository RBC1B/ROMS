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

import org.hibernate.envers.Audited;
import uk.org.rbc1b.roms.db.Congregation;
import uk.org.rbc1b.roms.db.DefaultUpdateAuditable;

/**
 * Congregation that holds the deeds to the hall.
 * @author oliver.elder.esq
 */
@Audited
public class TitleHolder extends DefaultUpdateAuditable {
    private static final long serialVersionUID = 4632818885924564868L;
    private Integer titleHolderId;
    private KingdomHall kingdomHall;
    private Congregation congregation;

    public Congregation getCongregation() {
        return congregation;
    }

    public void setCongregation(Congregation congregation) {
        this.congregation = congregation;
    }

    public KingdomHall getKingdomHall() {
        return kingdomHall;
    }

    public void setKingdomHall(KingdomHall kingdomHall) {
        this.kingdomHall = kingdomHall;
    }

    public Integer getTitleHolderId() {
        return titleHolderId;
    }

    public void setTitleHolderId(Integer titleHolderId) {
        this.titleHolderId = titleHolderId;
    }

    @Override
    public String toString() {
        return "TitleHolder{" + "titleHolderId=" + titleHolderId + '}';
    }
}
