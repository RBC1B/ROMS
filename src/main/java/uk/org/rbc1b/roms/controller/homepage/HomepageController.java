/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.homepage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author oliver.elder.esq
 */
@Controller
@RequestMapping("/")
public class HomepageController {

    @RequestMapping(method = RequestMethod.GET)
    public String handleGetRequest() {
        return "homepage";
    }
}
