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

import org.springframework.beans.factory.annotation.Autowired;
import uk.org.rbc1b.roms.db.CongregationDao;
import uk.org.rbc1b.roms.db.circuit.CircuitDao;
import uk.org.rbc1b.roms.db.kingdomhall.KingdomHall;

/**
 * @author ramonsingh
 */
public class KingdomHallModelFactory {

    private static final String BASE_URI = "/kingdomhall/";
    private CircuitDao circuitDao;
    private CongregationDao congregationDao;

    /**
     * generates a URI for kingdom hall.
     * @param kingdomHallId the Kingdom Hall Id to set
     * @return String
     */
    public static String generateUri(Integer kingdomHallId) {
        return kingdomHallId != null ? BASE_URI + kingdomHallId : BASE_URI;
    }

    /**
     * Generates a model for a list of Kingdom Halls.
     * @param kingdomHall the Kingdom Hall to set
     * @return KingdomHallListModel
     */
    public KingdomHallListModel generateKingdomHallListModel(KingdomHall kingdomHall) {
        KingdomHallListModel model = new KingdomHallListModel();

        model.setKingdomHallId(kingdomHall.getKingdomHallId());
        model.setName(kingdomHall.getName());
        model.setTown(kingdomHall.getAddress().getTown());
        model.setPostCode(kingdomHall.getAddress().getPostcode());

        return model;
    }

    /**
     * @param circuitDao the circuitDao to set
     */
    @Autowired
    public void setCircuitDao(CircuitDao circuitDao) {
        this.circuitDao = circuitDao;
    }

    /**
     * @param congregationDao the congregationDao to set
     */
    @Autowired
    public void setCongregationDao(CongregationDao congregationDao) {
        this.congregationDao = congregationDao;
    }
}
