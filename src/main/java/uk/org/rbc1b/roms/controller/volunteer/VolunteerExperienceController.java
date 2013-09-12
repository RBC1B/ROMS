package uk.org.rbc1b.roms.controller.volunteer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Control the display of the volunteer trades (experience) list.
 */
@Controller
@RequestMapping("/volunteer-experience")
public class VolunteerExperienceController {

    /**
     * Display a list of volunteer experience. Since the actual list is retrieved by ajax, there is no need to load
     * anthing into the model.
     * @param model mvc model
     * @return view
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showVolunteerExperienceList(ModelMap model) {
        return "volunteers/experience-list";
    }

}
