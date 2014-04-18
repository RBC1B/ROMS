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
package uk.org.rbc1b.roms.controller.user;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import uk.org.rbc1b.roms.db.application.User;
import uk.org.rbc1b.roms.db.application.UserDao;

/**
 * Read/write ROMS users.
 *
 * @author oliver.elder.esq
 */
@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserModelFactory userModelFactory;

    /**
     * Display the list of users.
     *
     * @param model mvc model
     * @return view
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showUserList(ModelMap model) {
        List<User> users = userDao.findAllUsers();
        List<UserModel> modelList = new ArrayList<UserModel>();
        for (User user : users) {
            modelList.add(userModelFactory.generateUserModel(user));
        }
        model.addAttribute("users", modelList);
        model.addAttribute("newUri", UserModelFactory.generateUri(null) + "/new");
        return "users/list";
    }

    /**
     * User search, making partial (prefix) matches on the user name.
     *
     * @param name full name prefix to match against user name
     * @return model containing the list of users
     */
    @RequestMapping(value = "search", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<UserSearchResult> findUsers(@RequestParam(value = "name", required = true) String name) {
        List<User> users = userDao.findUsers(name);
        List<UserSearchResult> results = new ArrayList<UserSearchResult>(users.size());
        if (!users.isEmpty()) {
            for (User user : users) {
                UserSearchResult result = new UserSearchResult();
                result.setPersonId(user.getPersonId());
                result.setUserName(user.getUserName());
                results.add(result);
            }
        }
        return results;
    }
}
