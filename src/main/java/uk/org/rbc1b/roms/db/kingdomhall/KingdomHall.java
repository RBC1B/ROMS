package uk.org.rbc1b.roms.db.kingdomhall;

import java.util.Set;
import uk.org.rbc1b.roms.db.Address;

/**
 * @author oliver.elder.esq
 */
public class KingdomHall implements java.io.Serializable {

    private Integer kingdomHallId;
    private String name;
    private Address address;
    private OwnershipType ownershipType;
    private String drawings;
    private Set<KingdomHallFeature> features;
    private TitleHolder titleHolder;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getDrawings() {
        return drawings;
    }

    public void setDrawings(String drawings) {
        this.drawings = drawings;
    }

    public Integer getKingdomHallId() {
        return kingdomHallId;
    }

    public void setKingdomHallId(Integer kingdomHallId) {
        this.kingdomHallId = kingdomHallId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OwnershipType getOwnershipType() {
        return ownershipType;
    }

    public void setOwnershipType(OwnershipType ownershipType) {
        this.ownershipType = ownershipType;
    }

    public Set<KingdomHallFeature> getFeatures() {
        return features;
    }

    public void setFeatures(Set<KingdomHallFeature> features) {
        this.features = features;
    }

    public TitleHolder getTitleHolder() {
        return titleHolder;
    }

    public void setTitleHolder(TitleHolder titleHolder) {
        this.titleHolder = titleHolder;
    }

    @Override
    public String toString() {
        return "KingdomHall{" + "kingdomHallId=" + kingdomHallId + '}';
    }
}
