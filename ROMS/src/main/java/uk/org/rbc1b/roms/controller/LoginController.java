/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author oliver.elder.esq
 */
@Controller
@RequestMapping("/login/")
public class LoginController {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleGetRequest() {
        ModelAndView model = new ModelAndView("login");

        return model;
    }



}
