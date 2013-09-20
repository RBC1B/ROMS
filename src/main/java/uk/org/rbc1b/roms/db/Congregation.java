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
package uk.org.rbc1b.roms.db;

import java.util.Iterator;
import java.util.Set;
import org.hibernate.envers.Audited;
import uk.org.rbc1b.roms.db.circuit.Circuit;
import uk.org.rbc1b.roms.db.kingdomhall.KingdomHall;

/**
 * Group of people.
 * @author oliver.elder.esq
 */
@Audited
public class Congregation extends DefaultUpdateAuditable {
    private static final long serialVersionUID = -346266378560523630L;
    private Integer congregationId;
    private String name;
    private String number;
    private KingdomHall kingdomHall;
    private Circuit circuit;
    private Integer rbcRegionId;
    private Integer rbcSubRegionId;
    private String publishers;
    private String attendance;
    private String funds;
    private String loans;
    private String monthlyIncome;
    private String strategy;
    private Set<CongregationContact> contacts;

    /**
     * Find the contact matching the type.
     * @param congregationRoleId role id
     * @return contact
     */
    public CongregationContact findContact(Integer congregationRoleId) {
        for (CongregationContact contact : contacts) {
            if (contact.getCongregationRoleId().equals(congregationRoleId)) {
                return contact;
            }
        }
        return null;
    }

    /**
     * Remove a contact from the collection.
     * @param congregationRoleId role id
     * @return true if we remove a contact
     */
    public boolean removeContact(Integer congregationRoleId) {
        for (Iterator<CongregationContact> i = contacts.iterator(); i.hasNext();) {
            CongregationContact contact = i.next();
            if (contact.getCongregationRoleId().equals(congregationRoleId)) {
                i.remove();
                return true;
            }

        }
        return false;
    }

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
