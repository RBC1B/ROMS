/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.user;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    private UserDao userDao;

    /**
     * User search, making partial (prefix) matches on the user name.
     *
     * @param name full name prefix to match against user name
     * @return model containing the list of users
     */
    @RequestMapping(value = "search", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public UsersSearchResponse findUsers(@RequestParam(value = "name", required = true) String name) {
        List<User> users = userDao.findUsers(name);

        UsersSearchResponse response = new UsersSearchResponse();
        if (!users.isEmpty()) {
            List<UserSearchResult> results = new ArrayList<UserSearchResult>(users.size());
            for (User user : users) {
                UserSearchResult result = new UserSearchResult();
                result.setPersonId(user.getPersonId());
                result.setUserName(user.getUserName());
                results.add(result);
            }
            response.setResults(results);
        }

        return response;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
