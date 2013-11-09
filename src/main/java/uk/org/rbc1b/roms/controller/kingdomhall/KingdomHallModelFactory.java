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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.org.rbc1b.roms.controller.common.model.EntityModel;
import uk.org.rbc1b.roms.controller.congregation.CongregationModelFactory;
import uk.org.rbc1b.roms.db.Congregation;
import uk.org.rbc1b.roms.db.CongregationDao;
import uk.org.rbc1b.roms.db.CongregationSearchCriteria;
import uk.org.rbc1b.roms.db.kingdomhall.KingdomHall;

/**
 * Provides the model factory for creating the models.
 *
 * @author ramonsingh
 */
@Component
public class KingdomHallModelFactory {

    private static final String BASE_URI = "/kingdom-halls";
    @Autowired
    private CongregationDao congregationDao;

    /**
     * Generates a URI for kingdom hall.
     *
     * @param kingdomHallId the Kingdom Hall Id to set
     * @return String
     */
    public static String generateUri(Integer kingdomHallId) {
        return kingdomHallId != null ? BASE_URI + "/" + kingdomHallId : BASE_URI;
    }

    /**
     * Generate the model to be used for the kingdom hall details view.
     *
     * @param kingdomHall kingdom hall
     * @return KingdomHallModel the model
     */
    public KingdomHallModel generateKingdomHallModel(KingdomHall kingdomHall) {
        KingdomHallModel model = new KingdomHallModel();
        // kingdom hall base model information
        populateBaseModel(kingdomHall, model);

        model.setOwnershipTypeCode(kingdomHall.getOwnershipTypeCode());
        model.setCongregations(generateCongregationModels(kingdomHall.getKingdomHallId()));

        if (kingdomHall.getTitleHolder() != null) {
            Congregation titleHoldingCongregation = congregationDao.findCongregation(kingdomHall.getTitleHolder()
                    .getCongregationId());

            EntityModel titleHolderModel = new EntityModel();
            titleHolderModel.setId(titleHoldingCongregation.getCongregationId());
            titleHolderModel.setName(titleHoldingCongregation.getName());
            titleHolderModel.setUri(CongregationModelFactory.generateUri(titleHoldingCongregation.getCongregationId()));

            model.setTitleHoldingCongregation(titleHolderModel);
        }

        return model;
    }

    /**
     * Generates a single model for a list of Kingdom Halls.
     *
     * @param kingdomHall the Kingdom Hall to set
     * @return KingdomHallListModel
     */
    public KingdomHallListModel generateKingdomHallListModel(KingdomHall kingdomHall) {
        KingdomHallListModel model = new KingdomHallListModel();
        populateBaseModel(kingdomHall, model);

        return model;
    }

    /**
     * Generate the list of kingdom hall models to appear in the list view.
     *
     * @param kingdomHalls kingdom halls list
     * @return model list
     */
    public List<KingdomHallListModel> generateKingdomHallListModels(List<KingdomHall> kingdomHalls) {
        List<KingdomHallListModel> models = new ArrayList<KingdomHallListModel>(kingdomHalls.size());
        for (KingdomHall kingdomHall : kingdomHalls) {
            models.add(generateKingdomHallListModel(kingdomHall));
        }
        return models;
    }

    private void populateBaseModel(KingdomHall kingdomHall, KingdomHallListModel model) {
        model.setKingdomHallId(kingdomHall.getKingdomHallId());
        model.setName(kingdomHall.getName());

        if (kingdomHall.getAddress() != null) {
            model.setStreet(kingdomHall.getAddress().getStreet());
            model.setTown(kingdomHall.getAddress().getTown());
            model.setCounty(kingdomHall.getAddress().getCounty());
            model.setPostcode(kingdomHall.getAddress().getPostcode());
        }

        model.setUri(KingdomHallModelFactory.generateUri(kingdomHall.getKingdomHallId()));
        model.setEditUri(KingdomHallModelFactory.generateUri(kingdomHall.getKingdomHallId()) + "/edit");
    }

    /**
     * Helper method to generate congregation models for each congregation that
     * meets at a hall.
     *
     * @param kingdomHallId kingdom hall id
     * @return set of entity models representing congregations
     */
    private Set<EntityModel> generateCongregationModels(Integer kingdomHallId) {
        Set<EntityModel> models = new HashSet<EntityModel>();

        CongregationSearchCriteria congregationSearchCriteria = new CongregationSearchCriteria();
        congregationSearchCriteria.setKingdomHallId(kingdomHallId);

        List<Congregation> congregations = congregationDao.findCongregations(congregationSearchCriteria);
        for (Congregation congregation : congregations) {
            EntityModel model = new EntityModel();
            model.setId(congregation.getCongregationId());
            model.setName(congregation.getName());
            model.setUri(CongregationModelFactory.generateUri(congregation.getCongregationId()));

            models.add(model);
        }
        return models;
    }

}
