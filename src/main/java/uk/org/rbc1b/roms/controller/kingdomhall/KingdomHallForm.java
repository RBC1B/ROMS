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
    @NotNull
    @Size(min = 2, max = 50)
    private String street;
    @NotNull
    @Size(min = 2, max = 50)
    private String town;
    @Size(min = 2, max = 50)
    private String county;
    @NotNull
    @Size(min = 4, max = 10)
    private String postcode;
    private Integer titleHoldingCongregationId;
    private String titleHoldingCongregationName;
    @NotNull
    @Size(max = 20)
    private String ownershipTypeCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
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

    public Integer getTitleHoldingCongregationId() {
        return titleHoldingCongregationId;
    }

    public void setTitleHoldingCongregationId(Integer titleHoldingCongregationId) {
        this.titleHoldingCongregationId = titleHoldingCongregationId;
    }

    public String getTitleHoldingCongregationName() {
        return titleHoldingCongregationName;
    }

    public void setTitleHoldingCongregationName(String titleHoldingCongregationName) {
        this.titleHoldingCongregationName = titleHoldingCongregationName;
    }

    public String getOwnershipTypeCode() {
        return ownershipTypeCode;
    }

    public void setOwnershipTypeCode(String ownershipTypeCode) {
        this.ownershipTypeCode = ownershipTypeCode;
    }
}
