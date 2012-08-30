/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.org.rbc1b.roms.controller.volunteer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uk.org.rbc1b.roms.controller.volunteer.VolunteerDao;

/*
 * @author rahulsingh
 */
@Controller
@RequestMapping("/volunteers")
public class VolunteerController {
     @Autowired
    private VolunteerDao volunteerDao;

    /**
     * Display the list of volunteers.
     * @param model mvc model
     * @return view
     */
    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasPermission('Volunteer', 'READ')")
    @Transactional(readOnly = true)
    public String handleList(ModelMap model) {

        model.addAttribute("volunteers", volunteerDao.findVolunteers());

        return "volunteers/list";
    }

    /**
     * @param volunteerDao the volunteerDao to set
     */
    public void setVolunteerDao(VolunteerDao volunteerDao) {
        this.volunteerDao = volunteerDao;
    }
    
    

}
