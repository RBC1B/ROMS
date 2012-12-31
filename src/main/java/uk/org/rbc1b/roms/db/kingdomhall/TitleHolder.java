/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db.kingdomhall;

import uk.org.rbc1b.roms.db.Congregation;

/**
 *
 * @author oliver.elder.esq
 */
public class TitleHolder {

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
