/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.congregation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import uk.org.rbc1b.roms.db.Congregation;

/**
 *
 * @author ramindursingh
 */
public class CongregationForm {

    private Integer congregationId;
    @NotNull
    @Size(max = 50)
    private String name;
    @Size(max = 50)
    private String number;
    private Integer kingdomHallId;
    @Size(max = 50)
    private String kingdomHall;
    private Integer circuitId;
    @Size(max = 50)
    private String circuit;
    private Integer rbcRegionId;
    @Size(max = 50)
    private String rbcRegion;
    private Integer rbcSubRegionId;
    @Size(max = 50)
    private String rbcSubRegion;
    @Size(max = 50)
    private String publishers;
    @Size(max = 50)
    private String attendance;
    @Size(max = 50)
    private String funds;
    @Size(max = 50)
    private String loans;
    @Size(max = 50)
    private String monthlyIncome;
    @Size(max = 50)
    private String strategy;

    /**
     * Constructors.
     */
    public CongregationForm() {
    }

    /**
     * Constructor with congregation.
     *
     * @param congregation congregation
     */
    public CongregationForm(Congregation congregation) {
        congregationId = congregation.getCongregationId();
        name = congregation.getName();
        number = congregation.getNumber();
        kingdomHallId = congregation.getKingdomHall().getKingdomHallId();
        kingdomHall = congregation.getKingdomHall().getName();
        circuitId = congregation.getCircuit().getCircuitId();
        circuit = congregation.getCircuit().getName();
        rbcRegionId = congregation.getRbcRegion().getRbcRegionId();
        publishers = congregation.getPublishers();
        attendance = congregation.getAttendance();
        funds = congregation.getFunds();
        loans = congregation.getLoans();
        monthlyIncome = congregation.getMonthlyIncome();
        strategy = congregation.getStrategy();
    }

    /**
     * @return the congregationId
     */
    public Integer getCongregationId() {
        return congregationId;
    }

    /**
     * @param congregationId the congregationId to set
     */
    public void setCongregationId(Integer congregationId) {
        this.congregationId = congregationId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the number
     */
    public String getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * @return the kingdomHallId
     */
    public Integer getKingdomHallId() {
        return kingdomHallId;
    }

    /**
     * @param kingdomHallId the kingdomHallId to set
     */
    public void setKingdomHallId(Integer kingdomHallId) {
        this.kingdomHallId = kingdomHallId;
    }

    /**
     * @return the kingdomHall
     */
    public String getKingdomHall() {
        return kingdomHall;
    }

    /**
     * @param kingdomHall the kingdomHall to set
     */
    public void setKingdomHall(String kingdomHall) {
        this.kingdomHall = kingdomHall;
    }

    /**
     * @return the circuitId
     */
    public Integer getCircuitId() {
        return circuitId;
    }

    /**
     * @param circuitId the circuitId to set
     */
    public void setCircuitId(Integer circuitId) {
        this.circuitId = circuitId;
    }

    /**
     * @return the circuit
     */
    public String getCircuit() {
        return circuit;
    }

    /**
     * @param circuit the circuit to set
     */
    public void setCircuit(String circuit) {
        this.circuit = circuit;
    }

    /**
     * @return the rbcRegionId
     */
    public Integer getRbcRegionId() {
        return rbcRegionId;
    }

    /**
     * @param rbcRegionId the rbcRegionId to set
     */
    public void setRbcRegionId(Integer rbcRegionId) {
        this.rbcRegionId = rbcRegionId;
    }

    /**
     * @return the rbcRegion
     */
    public String getRbcRegion() {
        return rbcRegion;
    }

    /**
     * @param rbcRegion the rbcRegion to set
     */
    public void setRbcRegion(String rbcRegion) {
        this.rbcRegion = rbcRegion;
    }

    /**
     * @return the rbcSubRegionId
     */
    public Integer getRbcSubRegionId() {
        return rbcSubRegionId;
    }

    /**
     * @param rbcSubRegionId the rbcSubRegionId to set
     */
    public void setRbcSubRegionId(Integer rbcSubRegionId) {
        this.rbcSubRegionId = rbcSubRegionId;
    }

    /**
     * @return the rbcSubRegion
     */
    public String getRbcSubRegion() {
        return rbcSubRegion;
    }

    /**
     * @param rbcSubRegion the rbcSubRegion to set
     */
    public void setRbcSubRegion(String rbcSubRegion) {
        this.rbcSubRegion = rbcSubRegion;
    }

    /**
     * @return the publishers
     */
    public String getPublishers() {
        return publishers;
    }

    /**
     * @param publishers the publishers to set
     */
    public void setPublishers(String publishers) {
        this.publishers = publishers;
    }

    /**
     * @return the attendance
     */
    public String getAttendance() {
        return attendance;
    }

    /**
     * @param attendance the attendance to set
     */
    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    /**
     * @return the funds
     */
    public String getFunds() {
        return funds;
    }

    /**
     * @param funds the funds to set
     */
    public void setFunds(String funds) {
        this.funds = funds;
    }

    /**
     * @return the loans
     */
    public String getLoans() {
        return loans;
    }

    /**
     * @param loans the loans to set
     */
    public void setLoans(String loans) {
        this.loans = loans;
    }

    /**
     * @return the monthlyIncome
     */
    public String getMonthlyIncome() {
        return monthlyIncome;
    }

    /**
     * @param monthlyIncome the monthlyIncome to set
     */
    public void setMonthlyIncome(String monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    /**
     * @return the strategy
     */
    public String getStrategy() {
        return strategy;
    }

    /**
     * @param strategy the strategy to set
     */
    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }
}
