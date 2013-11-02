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

import java.util.Set;
import uk.org.rbc1b.roms.controller.common.model.EntityModel;

/**
 * Model representing congregation details create.
 *
 * @author oliver.elder.esq
 */
public class KingdomHallModel extends KingdomHallListModel {

    private String ownershipTypeCode;
    private String drawings;
    private EntityModel titleHoldingCongregation;
    private Set<EntityModel> congregations;

    public String getOwnershipTypeCode() {
        return ownershipTypeCode;
    }

    public void setOwnershipTypeCode(String ownershipTypeCode) {
        this.ownershipTypeCode = ownershipTypeCode;
    }

    public String getDrawings() {
        return drawings;
    }

    public void setDrawings(String drawings) {
        this.drawings = drawings;
    }

    public EntityModel getTitleHoldingCongregation() {
        return titleHoldingCongregation;
    }

    public void setTitleHoldingCongregation(EntityModel titleHoldingCongregation) {
        this.titleHoldingCongregation = titleHoldingCongregation;
    }

    public Set<EntityModel> getCongregations() {
        return congregations;
    }

    public void setCongregations(Set<EntityModel> congregations) {
        this.congregations = congregations;
    }
}
