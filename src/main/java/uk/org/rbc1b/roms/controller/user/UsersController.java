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
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;
import uk.org.rbc1b.roms.controller.ForbiddenRequestException;
import uk.org.rbc1b.roms.db.Congregation;
import uk.org.rbc1b.roms.db.CongregationDao;
import uk.org.rbc1b.roms.db.Person;
import uk.org.rbc1b.roms.db.PersonDao;
import uk.org.rbc1b.roms.db.application.Application;
import uk.org.rbc1b.roms.db.application.ApplicationAccess;
import uk.org.rbc1b.roms.db.application.ApplicationAccessDao;
import uk.org.rbc1b.roms.db.application.ApplicationDao;
import uk.org.rbc1b.roms.db.application.User;
import uk.org.rbc1b.roms.db.application.UserDao;
import uk.org.rbc1b.roms.db.volunteer.VolunteerDao;
import uk.org.rbc1b.roms.security.AccessLevel;
import uk.org.rbc1b.roms.security.RomsPermissionEvaluator;
import uk.org.rbc1b.roms.tags.PermissionMap;

/**
 * Read/write ROMS users.
 *
 * @author oliver.elder.esq
 */
@Controller
@RequestMapping("/users")
public class UsersController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsersController.class);
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserModelFactory userModelFactory;
    @Autowired
    private ApplicationAccessDao applicationAccessDao;
    @Autowired
    private PersonDao personDao;
    @Autowired
    private VolunteerDao volunteerDao;
    @Autowired
    private CongregationDao congregationDao;
    @Autowired
    private ApplicationDao applicationDao;

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

    /**
     * Display a user.
     *
     * @param personId the person id
     * @param model mvc model
     * @return view
     * @throws NoSuchRequestHandlingMethodException on failure to look up user
     */
    @RequestMapping(value = "{personId}", method = RequestMethod.GET)
    public String showUser(@PathVariable Integer personId, ModelMap model)
            throws NoSuchRequestHandlingMethodException {
        User user = userDao.findUser(personId);
        if (user == null) {
            throw new NoSuchRequestHandlingMethodException("No User with ID:" + personId, this.getClass());
        }
        model.addAttribute("user", userModelFactory.generateUserModel(user));
        List<ApplicationAccess> permissions = applicationAccessDao.findUserPermissions(personId);
        model.addAttribute("permissions", permissions);

        return "users/show";
    }

    /**
     * Display form to create new user.
     *
     * @param model mvc model
     * @return view
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String showCreateUserForm(ModelMap model) {
        if (!RomsPermissionEvaluator.hasPermission(uk.org.rbc1b.roms.security.Application.DATABASE, AccessLevel.ADD)) {
            throw new ForbiddenRequestException(
                    "Database application add permission is required to show the new user form");
        }
        List<Application> applications = applicationDao.getApplications();
        Map<String, String> permissions = new PermissionMap().getAcl();
        model.addAttribute("permissions", permissions);
        model.addAttribute("applications", applications);
        model.addAttribute("userForm", new UserForm());
        model.addAttribute("submitUri", UserModelFactory.generateUri(null));
        model.addAttribute("submitMethod", "POST");
        return "users/edit";
    }

    /**
     * User search. Pass in a candidate, match against first/last name and
     * return JSON format
     *
     * @param forename the first name to look up
     * @param surname the surname to look up
     * @param checkVolunteer if true, check if the user is a valid volunteer
     * @return model containing list of volunteers
     */
    @RequestMapping(value = "search", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<UserSearchResult> findUsers(@RequestParam(value = "forename", required = true) String forename,
            @RequestParam(value = "surname", required = true) String surname,
            @RequestParam(value = "checkVolunteer") boolean checkVolunteer) {
        List<Person> persons = personDao.findPersons(forename, surname);
        List<UserSearchResult> results = new ArrayList<UserSearchResult>();
        if (!persons.isEmpty()) {
            for (Person person : persons) {
                results.add(generateUserSearchResult(person, checkVolunteer));
            }
        }
        return results;
    }

    /**
     * Checks if the person is a valid volunteer.
     *
     * @param person the person to check
     * @param checkVolunteer to check if user and volunteer
     * @return PersonSearchResult the result
     */
    private UserSearchResult generateUserSearchResult(Person person, boolean checkVolunteer) {
        UserSearchResult result = new UserSearchResult();
        result.setForename(person.getForename());
        result.setSurname(person.getSurname());
        result.setPersonId(person.getPersonId());

        if (person.getCongregation() != null) {
            Congregation congregation = congregationDao.findCongregation(person.getCongregation().getCongregationId());

            result.setCongregationName(congregation.getName());
        }

        if (checkVolunteer) {
            result.setVolunteer(volunteerDao.findVolunteer(person.getPersonId(), null) != null);
        }
        return result;
    }

    /**
     * Handles create user form.
     *
     * @param userForm the form input
     * @return redirect to view
     */
    @RequestMapping(method = RequestMethod.POST)
    public String createUser(@Valid UserForm userForm) {
        User user = new User();
        if (userForm.getPersonId() != null) {
            user.setPersonId(userForm.getPersonId());
        }
        user.setActive(true);
        user.setUserName(userForm.getUserName());
        user.setPassword(getPasswordHash("", userForm.getPassword1()));

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String myUserName = userDetails.getUsername();
        User me = userDao.findUserAndPermissions(myUserName);
        user.setUpdatedBy(me.getPersonId());
        Date date = new Date();
        user.setUpdateTime(date);

        LOGGER.error("Creating user:" + user.getUserName() + " Password:" + user.getPassword());
        LOGGER.error("By:" + user.getUpdatedBy() + " at:" + user.getUpdateTime());
        userDao.createUser(user);

        return "redirect:" + UserModelFactory.generateUri(user.getPersonId());
    }

    /**
     * Gets the hash for the password.
     *
     * @param salt the salt
     * @param password the password
     * @return the hash
     */
    private String getPasswordHash(String salt, String password) {
        ShaPasswordEncoder encoder = new ShaPasswordEncoder();
        String hash;
        if (salt.isEmpty()) {
            hash = encoder.encodePassword(password, null);
        } else {
            hash = encoder.encodePassword(password, salt);
        }
        return hash;
    }
}
