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
package uk.org.rbc1b.roms.controller.congregation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import uk.org.rbc1b.roms.db.Congregation;

/**
 * @author ramindursingh
 */
public class CongregationForm {

    @NotNull
    @Size(max = 50)
    private String name;
    @Size(max = 50)
    private String number;
    @NotNull
    private Integer kingdomHallId;
    @NotNull
    private Integer circuitId;
    private Integer rbcRegionId;
    private Integer rbcSubRegionId;
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
    @Size(max = 1000)
    private String strategy;

    /**
     * Constructors.
     */
    public CongregationForm() {
    }

    /**
     * Constructor with congregation.
     * @param congregation congregation
     */
    public CongregationForm(Congregation congregation) {
        name = congregation.getName();
        number = congregation.getNumber();
        kingdomHallId = congregation.getKingdomHall().getKingdomHallId();
        circuitId = congregation.getCircuit().getCircuitId();
        rbcRegionId = congregation.getRbcRegionId();
        publishers = congregation.getPublishers();
        attendance = congregation.getAttendance();
        funds = congregation.getFunds();
        loans = congregation.getLoans();
        monthlyIncome = congregation.getMonthlyIncome();
        strategy = congregation.getStrategy();
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

    public Integer getKingdomHallId() {
        return kingdomHallId;
    }

    public void setKingdomHallId(Integer kingdomHallId) {
        this.kingdomHallId = kingdomHallId;
    }

    public Integer getCircuitId() {
        return circuitId;
    }

    public void setCircuitId(Integer circuitId) {
        this.circuitId = circuitId;
    }

    public Integer getRbcRegionId() {
        return rbcRegionId;
    }

    public void setRbcRegionId(Integer rbcRegionId) {
        this.rbcRegionId = rbcRegionId;
    }

    public Integer getRbcSubRegionId() {
        return rbcSubRegionId;
    }

    public void setRbcSubRegionId(Integer rbcSubRegionId) {
        this.rbcSubRegionId = rbcSubRegionId;
    }

    public String getPublishers() {
        return publishers;
    }

    public void setPublishers(String publishers) {
        this.publishers = publishers;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    public String getFunds() {
        return funds;
    }

    public void setFunds(String funds) {
        this.funds = funds;
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

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }
}
