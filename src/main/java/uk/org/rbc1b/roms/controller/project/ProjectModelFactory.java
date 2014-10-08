/*
 * The MIT License
 *
 * Copyright 2014 RBC1B.
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
package uk.org.rbc1b.roms.controller.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.org.rbc1b.roms.controller.common.model.EntityModel;
import uk.org.rbc1b.roms.db.Person;
import uk.org.rbc1b.roms.db.PersonDao;
import uk.org.rbc1b.roms.db.kingdomhall.KingdomHall;
import uk.org.rbc1b.roms.db.kingdomhall.KingdomHallDao;
import uk.org.rbc1b.roms.db.project.Project;

/**
 *
 * @author ramindursingh
 */
@Component
public class ProjectModelFactory {

    private static final String BASE_URI = "/projects";
    private static final String PERSON_BASE_URI = "/volunteers/";
    private static final String KH_BASE_URI = "/kingdom-halls/";
    @Autowired
    private PersonDao personDao;
    @Autowired
    private KingdomHallDao kingdomHallDao;

    /**
     * Generate the uri used to access the project page.
     *
     * @param projectId the optional project id
     * @return uri
     */
    public static String generateUri(Integer projectId) {
        return projectId != null ? BASE_URI + "/" + projectId : BASE_URI;
    }

    /**
     * Generates a new project model.
     *
     * @param project project
     * @return model
     */
    public ProjectModel generateProjectModel(Project project) {
        ProjectModel model = new ProjectModel();

        model.setProjectId(project.getProjectId());
        model.setName(project.getName());
        model.setMinorWork(project.isMinorWork());

        model.setRequestDate(project.getRequestDate());
        model.setCompletedDate(project.getCompletedDate());

        if (project.getCoordinator() != null) {
            Person person = personDao.findPerson(project.getCoordinator().getPersonId());
            EntityModel personModel = new EntityModel();
            personModel.setId(person.getPersonId());
            personModel.setName(person.formatDisplayName());
            personModel.setUri(PERSON_BASE_URI + person.getPersonId());
            model.setCoordinator(personModel);
        }

        if (project.getKingdomHall() != null) {
            KingdomHall kingdomHall = kingdomHallDao.findKingdomHall(project.getKingdomHall().getKingdomHallId());
            EntityModel kingdomHallModel = new EntityModel();
            kingdomHallModel.setId(kingdomHall.getKingdomHallId());
            kingdomHallModel.setName(kingdomHall.getName());
            kingdomHallModel.setUri(KH_BASE_URI + kingdomHall.getKingdomHallId());
            model.setKingdomHall(kingdomHallModel);
        }

        model.setEditUri(generateUri(project.getProjectId()) + "/edit");
        model.setUri(generateUri(project.getProjectId()));

        return model;
    }
}
