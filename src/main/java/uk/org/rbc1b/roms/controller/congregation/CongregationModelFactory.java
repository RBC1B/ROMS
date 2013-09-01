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

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.org.rbc1b.roms.controller.circuit.CircuitModelFactory;
import uk.org.rbc1b.roms.controller.common.model.EntityModel;
import uk.org.rbc1b.roms.controller.common.model.PersonModelFactory;
import uk.org.rbc1b.roms.controller.kingdomhall.KingdomHallModelFactory;
import uk.org.rbc1b.roms.db.Congregation;
import uk.org.rbc1b.roms.db.CongregationContact;
import uk.org.rbc1b.roms.db.reference.ReferenceDao;

/**
 * @author ramindursingh
 */
@Component
public class CongregationModelFactory {
    private static final String BASE_URI = "/congregations";
    private ReferenceDao referenceDao;
    private PersonModelFactory personModelFactory;

    /**
     * Generate the uri used to access congregation pages.
     * @param congregationId optional congregation id
     * @return uri
     */
    public static String generateUri(Integer congregationId) {
        return congregationId != null ? BASE_URI + "/" + congregationId : BASE_URI;
    }

    /**
     * Generate the model used in the congregation list view.
     * @param congregation congregation
     * @return model
     */
    public CongregationListModel generateCongregationListModel(Congregation congregation) {
        CongregationListModel model = new CongregationListModel();
        populateBaseModel(congregation, model);

        return model;
    }

    /**
     * Generate the model used in the congregation detailsd view.
     * @param congregation congregation
     * @return model
     */
    public CongregationModel generateCongregationModel(Congregation congregation) {
        CongregationModel model = new CongregationModel();
        populateBaseModel(congregation, model);

        model.setAttendance(congregation.getAttendance());

        model.setFunds(congregation.getFunds());
        model.setLoans(congregation.getLoans());
        model.setMonthlyIncome(congregation.getMonthlyIncome());
        model.setNumber(congregation.getNumber());
        model.setPublishers(congregation.getPublishers());
        model.setStrategy(congregation.getStrategy());

        if (congregation.getContacts() != null) {
            List<CongregationContactModel> contactModels = new ArrayList<CongregationContactModel>(congregation
                    .getContacts().size());
            for (CongregationContact contact : congregation.getContacts()) {
                contactModels.add(generateContactModel(contact));
            }
            model.setContacts(contactModels);
        }

        return model;
    }

    private CongregationContactModel generateContactModel(CongregationContact contact) {

        CongregationContactModel model = new CongregationContactModel();
        model.setRole(referenceDao.findCongregationRoleValues().get(contact.getCongregationRoleId()));
        model.setPerson(personModelFactory.generatePersonModel(contact.getPerson()));

        return model;
    }

    private void populateBaseModel(Congregation congregation, CongregationListModel model) {
        model.setCongregationId(congregation.getCongregationId());
        model.setName(congregation.getName());

        EntityModel kingdomHallModel = new EntityModel();
        if (congregation.getKingdomHall() != null) {
            kingdomHallModel.setId(congregation.getKingdomHall().getKingdomHallId());
            kingdomHallModel.setName(congregation.getKingdomHall().getName());
            kingdomHallModel.setUri(KingdomHallModelFactory.generateUri(congregation.getKingdomHall()
                    .getKingdomHallId()));
        }
        model.setKingdomHall(kingdomHallModel);

        EntityModel circuitModel = new EntityModel();
        if (congregation.getCircuit() != null) {
            circuitModel.setId(congregation.getCircuit().getCircuitId());
            circuitModel.setName(congregation.getCircuit().getName());
            circuitModel.setUri(CircuitModelFactory.generateCircuitUri(congregation.getCircuit().getCircuitId()));
        }
        model.setCircuit(circuitModel);

        if (congregation.getRbcRegionId() != null) {
            model.setRbcRegion(referenceDao.findRbcRegionValues().get(congregation.getRbcRegionId()));
        }

        if (congregation.getRbcSubRegionId() != null) {
            model.setRbcSubRegion(referenceDao.findRbcSubRegionValues().get(congregation.getRbcSubRegionId()));
        }

        model.setUri(generateUri(congregation.getCongregationId()));
        model.setEditUri(generateUri(congregation.getCongregationId()) + "/edit");
    }

    @Autowired
    public void setReferenceDao(ReferenceDao referenceDao) {
        this.referenceDao = referenceDao;
    }

    @Autowired
    public void setPersonModelFactory(PersonModelFactory personModelFactory) {
        this.personModelFactory = personModelFactory;
    }

}
