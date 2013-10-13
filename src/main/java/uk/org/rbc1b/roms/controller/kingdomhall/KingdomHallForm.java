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
package uk.org.rbc1b.roms.controller.kingdomhall;

import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Kingdom Hall form bean for creating/editing a kingdom hall.
 *
 * @author rahulsingh
 */
public class KingdomHallForm {

    @NotNull
    @Size(min = 2, max = 50)
    private String name;
    @Size(min = 2, max = 50)
    private String steet;
    @Size(min = 2, max = 50)
    private String town;
    @Size(min = 2, max = 50)
    private String county;
    @Size(min = 4, max = 7)
    private String postcode;
    private Integer owningCongregationId;
    private String owningCongregationName;
    private Integer ownershipTypeId;
    private List<String> congregations;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSteet() {
        return steet;
    }

    public void setSteet(String steet) {
        this.steet = steet;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public Integer getOwningCongregationId() {
        return owningCongregationId;
    }

    public void setOwningCongregationId(Integer owningCongregationId) {
        this.owningCongregationId = owningCongregationId;
    }

    public String getOwningCongregationName() {
        return owningCongregationName;
    }

    public void setOwningCongregationName(String owningCongregationName) {
        this.owningCongregationName = owningCongregationName;
    }

    public Integer getOwnershipTypeId() {
        return ownershipTypeId;
    }

    public void setOwnershipTypeId(Integer ownershipTypeId) {
        this.ownershipTypeId = ownershipTypeId;
    }

    public List<String> getCongregations() {
        return congregations;
    }

    public void setCongregations(List<String> congregations) {
        this.congregations = congregations;
    }
}
