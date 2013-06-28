/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.qualification;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;
import uk.org.rbc1b.roms.db.volunteer.Qualification;
import uk.org.rbc1b.roms.db.volunteer.QualificationDao;

/**
 * Qualification types which may be applied to a volunteer.
 *
 * @author ramindur
 */
@Controller
@RequestMapping("/qualifications")
public class QualificationsController {

    private QualificationDao qualificationDao;

    /**
     * Display a list of qualifications.
     *
     * @param model spring mvc model
     * @return model containing the list of qualifications
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showQualificationList(ModelMap model) {

        model.addAttribute("qualifications", qualificationDao.findQualifications());

        return "qualifications/list";
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
        } else {
            QualificationForm form = new QualificationForm();
            form.setQualificationId(qualificationId);
            form.setName(qualification.getName());
            form.setDescription(qualification.getDescription());
            model.addAttribute("qualification", form);

            return "qualifications/edit";
        }
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
            throw new NoSuchRequestHandlingMethodException("No Qualification with id #" + qualificationId, this.getClass());
        } else {
            qualificationDao.deleteQualification(qualification);
        }
        return "redirect:/qualifications";
    }

    /**
     *
     * @param qualificationDao qualification DAO
     */
    @Autowired
    public void setQualificationDao(QualificationDao qualificationDao) {
        this.qualificationDao = qualificationDao;
    }
}
