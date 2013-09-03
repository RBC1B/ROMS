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
    @Size(max = 50)
    private String coordinatorForename;
    @Size(max = 50)
    private String coordinatorSurname;
    private Integer coordinatorPersonId;
    @Size(max = 50)
    private String secretaryForename;
    @Size(max = 50)
    private String secretarySurname;
    private Integer secretaryPersonId;

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

    public String getCoordinatorForename() {
        return coordinatorForename;
    }

    public void setCoordinatorForename(String coordinatorForename) {
        this.coordinatorForename = coordinatorForename;
    }

    public String getCoordinatorSurname() {
        return coordinatorSurname;
    }

    public void setCoordinatorSurname(String coordinatorSurname) {
        this.coordinatorSurname = coordinatorSurname;
    }

    public Integer getCoordinatorPersonId() {
        return coordinatorPersonId;
    }

    public void setCoordinatorPersonId(Integer coordinatorPersonId) {
        this.coordinatorPersonId = coordinatorPersonId;
    }

    public String getSecretaryForename() {
        return secretaryForename;
    }

    public void setSecretaryForename(String secretaryForename) {
        this.secretaryForename = secretaryForename;
    }

    public String getSecretarySurname() {
        return secretarySurname;
    }

    public void setSecretarySurname(String secretarySurname) {
        this.secretarySurname = secretarySurname;
    }

    public Integer getSecretaryPersonId() {
        return secretaryPersonId;
    }

    public void setSecretaryPersonId(Integer secretaryPersonId) {
        this.secretaryPersonId = secretaryPersonId;
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
