/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.authentication;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author oliver.elder.esq
 */
@Controller
public class AuthenticationController {

    /**
     * Display the login form.
     *
     * @param status status flag, used after unsuccessful login attempts
     * @param model mvc model
     * @return view name
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginForm(@RequestParam(value = "status", required = false) String status, ModelMap model) {
        if (status != null) {
            model.addAttribute("status", status);
        }
        return "login";
    }

    /**
     * Display the logout request.
     *
     * @return view name
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String showLogout() {
        return "logout";
    }
}
