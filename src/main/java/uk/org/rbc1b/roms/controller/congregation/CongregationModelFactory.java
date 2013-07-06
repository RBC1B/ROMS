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

import org.springframework.stereotype.Component;
import uk.org.rbc1b.roms.controller.common.model.EntityModel;
import uk.org.rbc1b.roms.db.Congregation;

/**
 *
 * @author ramindursingh
 */
@Component
public class CongregationModelFactory {

    private static final String BASE_URI = "/congregations/";

    /**
     * Generate the uri used to access congregation pages.
     *
     * @param congregationId optional congregation id
     * @return uri
     */
    public static String generateUri(Integer congregationId) {
        return congregationId != null ? BASE_URI + congregationId : BASE_URI;
    }

    /**
     * Generate the model used in the congregation list view.
     *
     * @param congregation congregation
     * @return model
     */
    public CongregationListModel generateCongregationListModel(Congregation congregation) {
        CongregationListModel model = new CongregationListModel();
        model.setCongregationId(congregation.getCongregationId());
        model.setName(congregation.getName());

        EntityModel kingdomHallModel = new EntityModel();
        if (congregation.getKingdomHall() != null) {
            kingdomHallModel.setId(congregation.getKingdomHall().getKingdomHallId());
            kingdomHallModel.setName(congregation.getKingdomHall().getName());
        }
        model.setKingdomHall(kingdomHallModel);

        EntityModel circuitModel = new EntityModel();
        if (congregation.getCircuit() != null) {
            circuitModel.setId(congregation.getCircuit().getCircuitId());
            circuitModel.setName(congregation.getCircuit().getName());
        }
        model.setCircuit(circuitModel);

        EntityModel rbcRegionModel = new EntityModel();
        if (congregation.getRbcRegion() != null) {
            rbcRegionModel.setId(congregation.getRbcRegion().getRbcRegionId());
            rbcRegionModel.setName(congregation.getRbcRegion().getName());
        }
        model.setRbcRegion(rbcRegionModel);

        EntityModel rbcSubRegionModel = new EntityModel();
        if (congregation.getRbcSubRegion() != null) {
            rbcSubRegionModel.setId(congregation.getRbcSubRegion().getRbcSubRegionId());
            rbcSubRegionModel.setName(congregation.getRbcSubRegion().getName());
        }
        model.setRbcSubRegion(rbcSubRegionModel);

        return model;
    }
}
