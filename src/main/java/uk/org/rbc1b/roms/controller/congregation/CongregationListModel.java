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

import uk.org.rbc1b.roms.controller.common.model.EntityModel;

/**
 *
 * @author ramindursingh
 */
public class CongregationListModel {

    private Integer congregationId;
    private String uri;
    private String name;
    private EntityModel congregation;
    private EntityModel kingdomHall;
    private EntityModel circuit;
    private EntityModel rbcRegion;
    private EntityModel rbcSubRegion;

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
     * @return the uri
     */
    public String getUri() {
        return uri;
    }

    /**
     * @param uri the uri to set
     */
    public void setUri(String uri) {
        this.uri = uri;
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
     * @return the congregation
     */
    public EntityModel getCongregation() {
        return congregation;
    }

    /**
     * @param congregation the congregation to set
     */
    public void setCongregation(EntityModel congregation) {
        this.congregation = congregation;
    }

    /**
     * @return the kingdomHall
     */
    public EntityModel getKingdomHall() {
        return kingdomHall;
    }

    /**
     * @param kingdomHall the kingdomHall to set
     */
    public void setKingdomHall(EntityModel kingdomHall) {
        this.kingdomHall = kingdomHall;
    }

    /**
     * @return the circuit
     */
    public EntityModel getCircuit() {
        return circuit;
    }

    /**
     * @param circuit the circuit to set
     */
    public void setCircuit(EntityModel circuit) {
        this.circuit = circuit;
    }

    /**
     * @return the rbcRegion
     */
    public EntityModel getRbcRegion() {
        return rbcRegion;
    }

    /**
     * @param rbcRegion the rbcRegion to set
     */
    public void setRbcRegion(EntityModel rbcRegion) {
        this.rbcRegion = rbcRegion;
    }

    /**
     * @return the rbcSubRegion
     */
    public EntityModel getRbcSubRegion() {
        return rbcSubRegion;
    }

    /**
     * @param rbcSubRegion the rbcSubRegion to set
     */
    public void setRbcSubRegion(EntityModel rbcSubRegion) {
        this.rbcSubRegion = rbcSubRegion;
    }
}
