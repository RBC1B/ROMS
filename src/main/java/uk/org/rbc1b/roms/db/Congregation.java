package uk.org.rbc1b.roms.db;

import java.util.Set;
import uk.org.rbc1b.roms.db.circuit.Circuit;
import uk.org.rbc1b.roms.db.kingdomhall.KingdomHall;

/**
 * Group of people.
 * @author oliver.elder.esq
 */
public class Congregation extends DefaultAuditable {

    private Integer congregationId;
    private String name;
    private String number;
    private KingdomHall kingdomHall;
    private Circuit circuit;
    private RbcRegion rbcRegion;
    private RbcSubRegion rbcSubRegion;
    private String publishers;
    private String attendance;
    private String funds;
    private String loans;
    private String monthlyIncome;
    private String strategy;
    private Set<CongregationContact> contacts;

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    public Set<CongregationContact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<CongregationContact> contacts) {
        this.contacts = contacts;
    }

    public Circuit getCircuit() {
        return circuit;
    }

    public void setCircuit(Circuit circuit) {
        this.circuit = circuit;
    }

    public Integer getCongregationId() {
        return congregationId;
    }

    public void setCongregationId(Integer congregationId) {
        this.congregationId = congregationId;
    }

    public String getFunds() {
        return funds;
    }

    public void setFunds(String funds) {
        this.funds = funds;
    }

    public KingdomHall getKingdomHall() {
        return kingdomHall;
    }

    public void setKingdomHall(KingdomHall kingdomHall) {
        this.kingdomHall = kingdomHall;
    }

    public String getLoans() {
        return loans;
    }

    public void setLoans(String loans) {
        this.loans = loans;
    }

    public String getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(String monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPublishers() {
        return publishers;
    }

    public void setPublishers(String publishers) {
        this.publishers = publishers;
    }

    public RbcRegion getRbcRegion() {
        return rbcRegion;
    }

    public void setRbcRegion(RbcRegion rbcRegion) {
        this.rbcRegion = rbcRegion;
    }

    public RbcSubRegion getRbcSubRegion() {
        return rbcSubRegion;
    }

    public void setRbcSubRegion(RbcSubRegion rbcSubRegion) {
        this.rbcSubRegion = rbcSubRegion;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    @Override
    public String toString() {
        return name;
    }
}
