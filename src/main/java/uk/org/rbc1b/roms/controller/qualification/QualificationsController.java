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
package uk.org.rbc1b.roms.controller.qualification;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;
import uk.org.rbc1b.roms.db.volunteer.qualification.Qualification;
import uk.org.rbc1b.roms.db.volunteer.qualification.QualificationDao;

/**
 * Qualification types which may be applied to a volunteer.
 *
 * @author ramindur
 */
@Controller
@RequestMapping("/qualifications")
public class QualificationsController {

    private QualificationDao qualificationDao;
    private QualificationModelFactory qualificationModelFactory;

    /**
     * Display a list of qualifications.
     *
     * @param model spring mvc model
     * @return model containing the list of qualifications
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showQualificationList(ModelMap model) {

        List<Qualification> qualifications = qualificationDao.findQualifications();

        List<QualificationModel> modelList = new ArrayList<QualificationModel>();
        for (Qualification qualification : qualifications) {
            modelList.add(qualificationModelFactory.generateQualificationModel(qualification));
        }

        model.addAttribute("qualifications", modelList);

        return "qualifications/list";
    }

    /**
     * Displays a qualification.
     * @param qualificationId qualification Id (primary key)
     * @param model mvc
     * @return view name
     * @throws NoSuchRequestHandlingMethodException on failure to look up the qualification
     */
    @RequestMapping(value = "{qualificationId}", method = RequestMethod.GET)
    public String showQualification(@PathVariable Integer qualificationId, ModelMap model)
            throws NoSuchRequestHandlingMethodException {
        Qualification qualification = qualificationDao.findQualification(qualificationId);
        if (qualification == null) {
            throw new NoSuchRequestHandlingMethodException("No qualification #" + qualificationId, this.getClass());
        }
        model.addAttribute("qualification", qualificationModelFactory.generateQualificationModel(qualification));
        return "qualifications/show";
    }

    /**
     * Displays a qualification for editing.
     *
     * @param qualificationId qualification ID
     * @param model mvc model
     * @return view name
     * @throws NoSuchRequestHandlingMethodException on failure to find the
     * qualification
     */
    @RequestMapping(value = "{qualificationId}/edit", method = RequestMethod.GET)
    public String showEditQualificationForm(@PathVariable Integer qualificationId, ModelMap model)
            throws NoSuchRequestHandlingMethodException {
        Qualification qualification = this.qualificationDao.findQualification(qualificationId);
        if (qualification == null) {
            throw new NoSuchRequestHandlingMethodException("No qualification #" + qualificationId, this.getClass());
        }

        QualificationForm form = new QualificationForm();
        form.setQualificationId(qualificationId);
        form.setName(qualification.getName());
        form.setDescription(qualification.getDescription());
        model.addAttribute("qualification", form);

        return "qualifications/edit";

    }

    /**
     * Displays a form to create a new qualification.
     *
     * @param model mvc model
     * @return view name
     */
    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String showCreateQualificationForm(ModelMap model) {

        model.addAttribute("qualification", new QualificationForm());

        return "qualifications/edit";
    }

    /**
     * Saves a changed qualification or creates a new qualification.
     *
     * @param qualificationForm form bean
     * @return mvc redirect
     */
    @RequestMapping(method = RequestMethod.POST)
    public String createQualification(@Valid QualificationForm qualificationForm) {

        Qualification qualification = new Qualification();
        if (qualificationForm.getQualificationId() != null) {
            qualification.setQualificationId(qualificationForm.getQualificationId());
        }
        qualification.setName(qualificationForm.getName());
        qualification.setDescription(qualificationForm.getDescription());
        qualificationDao.saveQualification(qualification);

        return "redirect:/qualifications";
    }

    /**
     * Delete a qualification.
     *
     * @param request http servlet request
     * @param model spring mvc model
     * @return mvc redirect
     * @throws NoSuchRequestHandlingMethodException on failure to find the
     * qualification
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public String deleteQualification(HttpServletRequest request, ModelMap model)
            throws NoSuchRequestHandlingMethodException {
        Integer qualificationId;
        qualificationId = Integer.parseInt(request.getParameter("qualificationId"));
        Qualification qualification = qualificationDao.findQualification(qualificationId);
        if (qualification == null) {
            throw new NoSuchRequestHandlingMethodException("No Qualification with id #" + qualificationId,
                    this.getClass());
        } else {
            qualificationDao.deleteQualification(qualification);
        }
        return "redirect:/qualifications";
    }

    @Autowired
    public void setQualificationDao(QualificationDao qualificationDao) {
        this.qualificationDao = qualificationDao;
    }

    @Autowired
    public void setQualificationModelFactory(QualificationModelFactory qualificationModelFactory) {
        this.qualificationModelFactory = qualificationModelFactory;
    }

}
