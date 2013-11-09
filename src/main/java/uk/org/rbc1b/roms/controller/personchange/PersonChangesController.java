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
package uk.org.rbc1b.roms.controller.personchange;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;
import uk.org.rbc1b.roms.db.PersonChange;
import uk.org.rbc1b.roms.db.PersonChangeDao;

/**
 * Control access to PersonChange table.
 *
 */
@Controller
@RequestMapping("/person-changes")
public class PersonChangesController {

    @Autowired
    private PersonChangeDao personChangeDao;
    @Autowired
    private PersonChangeModelFactory personChangeModelFactory;

    /**
     * Display the list of changes made to person table.
     *
     * @param model mvc model
     * @return view
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showPersonChangeList(ModelMap model) {
        List<PersonChange> personChanges = personChangeDao.findPersonChangeNotUpdated();

        if (!personChanges.isEmpty()) {
            List<PersonChangeModel> modelList = new ArrayList<PersonChangeModel>();
            for (PersonChange personChange : personChanges) {
                modelList.add(personChangeModelFactory.generatePersonChangeModel(personChange));
            }
            model.addAttribute("personchanges", modelList);
        }
        return "person-changes/list";
    }

    /**
     * Updates a personChange by setting the updated form to true.
     *
     * @param personChangeId the row to update
     * @throws NoSuchRequestHandlingMethodException on failure to find the row
     */
    @RequestMapping(value = "{personChangeId}", method = RequestMethod.PUT)
    public void updatePersonChange(@PathVariable Integer personChangeId) throws NoSuchRequestHandlingMethodException {
        PersonChange personChange = personChangeDao.findPersonChange(personChangeId);
        if (personChange == null) {
            throw new NoSuchRequestHandlingMethodException("No personChange #" + personChangeId, this.getClass());
        }
        personChange.setFormUpdated(true);
        personChangeDao.updatePersonChange(personChange);
    }

}
