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
package uk.org.rbc1b.roms.controller.admin;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import uk.org.rbc1b.roms.controller.BadRequestException;
import uk.org.rbc1b.roms.controller.admin.users.UserModel;
import uk.org.rbc1b.roms.controller.admin.users.UserModelFactory;
import uk.org.rbc1b.roms.db.application.User;
import uk.org.rbc1b.roms.db.application.UserDao;
import uk.org.rbc1b.roms.security.AccessLevel;
import uk.org.rbc1b.roms.security.ROMSUserDetails;
import uk.org.rbc1b.roms.security.RomsPermissionEvaluator;

/**
 * Admin related functionality - users, passwords.
 */
@Controller
public class AdminController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserModelFactory userModelFactory;

    /**
     * Show the admin dashboard, which will contain the links to other admin related functionality.
     * @param model mvc model
     * @return page
     */
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String showAdminDashboard(ModelMap model) {
        // look up their own user info
        ROMSUserDetails principle = (ROMSUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        User authenticatedUser = userDao.findUser(principle.getUserId());
        model.addAttribute("user", userModelFactory.generateUserModel(authenticatedUser));
        model.addAttribute("changePasswordUri", "/admin/password");

        // look up the list of users if they have permission
        if (RomsPermissionEvaluator.hasPermission(uk.org.rbc1b.roms.security.Application.DATABASE, AccessLevel.READ)) {
            List<User> users = userDao.findAllUsers();
            List<UserModel> modelList = new ArrayList<UserModel>();
            for (User user : users) {
                modelList.add(userModelFactory.generateUserModel(user));
            }
            model.addAttribute("users", modelList);
        }

        return "admin/dashboard";
    }

    /**
     * Update the currently authenticated user's password.
     * @param password password
     */
    @RequestMapping(value = "/admin/password", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updatePassword(@RequestParam String password) {
        ROMSUserDetails principle = (ROMSUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        if (password.length() < User.MIN_PASSWORD_LENGTH || password.length() > User.MAX_PASSWORD_LENGTH) {
            throw new BadRequestException("Password must be between " + User.MIN_PASSWORD_LENGTH + " and "
                    + User.MAX_USERNAME_LENGTH + " chars");
        }

        User user = userDao.findUser(principle.getUserId());

        ShaPasswordEncoder encoder = new ShaPasswordEncoder();
        user.setPassword(encoder.encodePassword(password, null));

        userDao.updateUser(user);

    }
}
