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
import java.util.HashSet;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
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
import uk.org.rbc1b.roms.db.volunteer.Volunteer;
import uk.org.rbc1b.roms.db.volunteer.VolunteerDao;
import uk.org.rbc1b.roms.security.AccessLevel;
import uk.org.rbc1b.roms.security.RomsPermissionEvaluator;

/**
 * Read/write ROMS users.
 *
 * @author oliver.elder.esq
 */
@Controller
@RequestMapping("/users")
public class UsersController {

    private static final String ACTIVE_VOLUNTEER = "AT";
    private static final String NONDEPARTMENT = "All";
    private static final String DEPARTMENT = "Dept";
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
    public String showUser(@PathVariable Integer personId, ModelMap model) throws NoSuchRequestHandlingMethodException {
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

        model.addAttribute("applications", ApplicationAccessFormFactory.generateApplicationAccessForm(applications, null));
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
    @RequestMapping(value = "search-volunteer", method = RequestMethod.GET, produces = "application/json")
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
     * Checks if the username is already being used.
     *
     * @param username the username to check
     * @return isUsed true or false
     */
    @RequestMapping(value = "check-user", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public boolean checkUsername(@RequestParam(value = "username", required = true) String username) {
        return userDao.checkUserExist(username);
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
            Volunteer volunteer = volunteerDao.findVolunteer(person.getPersonId(), null);
            if (volunteer == null) {
                result.setVolunteer(false);
            } else {
                result.setVolunteer(volunteer.getRbcStatusCode().equalsIgnoreCase(ACTIVE_VOLUNTEER));
            }
            User user = userDao.findUser(person.getPersonId());
            if (user == null) {
                result.setUser(false);
            } else {
                result.setUser(true);
                result.setUserName(user.getUserName());
            }
        }
        return result;
    }

    /**
     * Handles create user form.
     *
     * @param userForm the form input
     * @param request the request
     * @return redirect to view
     */
    @RequestMapping(method = RequestMethod.POST)
    public String createUser(@Valid UserForm userForm, HttpServletRequest request) {
        User user = new User();
        Person theUser = new Person();
        if (userForm.getPersonId() != null) {
            user.setPersonId(userForm.getPersonId());
            theUser = personDao.findPerson(userForm.getPersonId());
        }
        user.setActive(true);
        user.setUserName(userForm.getUserName());
        user.setPassword(getPasswordHash("", userForm.getPassword1()));

        User me = getMyUser();
        user.setUpdatedBy(me.getPersonId());
        Date date = new Date();
        user.setUpdateTime(date);
        user.setApplicationAccess(null);
        userDao.createUser(user);

        saveAcl(user.getPersonId(), request, me);
        LOGGER.error("UsersController: Created Edifice User:" + user.getUserName());

        return "redirect:" + UserModelFactory.generateUri(user.getPersonId());
    }

    /**
     * Handles requests to update a user.
     *
     * @param personId the user to update
     * @param model the MVC model
     * @return the jsp page to display
     * @throws NoSuchRequestHandlingMethodException if the user does not exist
     * @throws ForbiddenRequestException if access is not granted to the user
     */
    @RequestMapping(value = "{personId}/edit", method = RequestMethod.GET)
    public String showUserForm(@PathVariable Integer personId, ModelMap model)
            throws NoSuchRequestHandlingMethodException, ForbiddenRequestException {
        User user = userDao.findUser(personId);
        if (user == null) {
            throw new NoSuchRequestHandlingMethodException("No User with ID:" + personId, this.getClass());
        }
        if (!RomsPermissionEvaluator.hasPermission(uk.org.rbc1b.roms.security.Application.DATABASE, AccessLevel.EDIT)) {
            throw new ForbiddenRequestException(
                    "Database application edit permission is required to show the edit user form");
        }
        List<Application> applications = applicationDao.getApplications();
        UserForm userForm = createUserFormForCurrentUser(user);

        model.addAttribute("userForm", userForm);
        model.addAttribute("applications", ApplicationAccessFormFactory.generateApplicationAccessForm(applications, user.getApplicationAccess()));
        model.addAttribute("submitUri", UserModelFactory.generateUri(personId));
        model.addAttribute("submitMethod", "PUT");
        model.addAttribute("user", userModelFactory.generateUserModel(user));

        return "users/edit";
    }

    /**
     * Saves an update to a user.
     *
     * @param personId the person
     * @param userForm a valid user form
     * @param request the http request
     * @return redirect
     */
    @RequestMapping(value = "{personId}", method = RequestMethod.PUT)
    public String saveUserUpdate(@PathVariable Integer personId, @Valid UserForm userForm, HttpServletRequest request) {
        User me = getMyUser();
        // Three things we could do: 1. disable user, 2. change password, 3. update acl
        if (userForm.isActive()) {
            if (userForm.getPassword1() != null && !userForm.getPassword1().isEmpty()) {
                LOGGER.error("UsersController: Updating password for: " + userForm.getUserName());
                savePassword(userForm, me);
            }
            LOGGER.error("UsersController: Updating ACL for: " + userForm.getUserName());
            enableUser(personId, me, true);
            deleteAcl(personId, me);
            saveAcl(personId, request, me);
        } else {
            LOGGER.error("UsersController: Disabling user: " + userForm.getUserName());
            deleteAcl(personId, me);
            enableUser(personId, me, false);
        }
        return "redirect:" + UserModelFactory.generateUri(personId);
    }

    /**
     * Creates a user form for editing a user.
     *
     * @param user the user to update
     * @param applications list of ALL applications
     * @param permissions the Map of permissions
     * @return form the UserForm
     */
    private UserForm createUserFormForCurrentUser(User user) {
        Person person = personDao.findPerson(user.getPersonId());
        UserForm form = new UserForm();
        form.setPersonId(user.getPersonId());
        form.setUserName(user.getUserName());
        form.setForename(person.getForename());
        form.setSurname(person.getSurname());
        form.setActive(user.isActive());
        return form;
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

    /**
     * Gets the current user logged in.
     *
     * @return user the user object
     */
    private User getMyUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String myUserName = userDetails.getUsername();
        User me = userDao.findUserAndPermissions(myUserName);
        return me;
    }

    /**
     * Saves the user's password only.
     *
     * @param form the userForm
     * @param me the updater
     */
    private void savePassword(UserForm form, User me) {
        User user = userDao.findUser(form.getPersonId());
        user.setUpdatedBy(me.getPersonId());
        user.setUpdateTime(new Date());
        user.setPassword(getPasswordHash("", form.getPassword1()));
        userDao.updateUser(user);
    }

    /**
     * Saves a person's acl list. We are only going to use non departmental
     * access to set both dept and non-dept to the same code. If the access is
     * 'N' then we do not add to the set.
     *
     * @param request http request
     */
    private void saveAcl(Integer personId, HttpServletRequest request, User me) {
        List<Application> applications = applicationDao.getApplications();
        Person person = personDao.findPerson(personId);
        HashSet<ApplicationAccess> applicationAccess = new HashSet<ApplicationAccess>();
        for (Application application : applications) {
            ApplicationAccess appAccess = new ApplicationAccess();
            appAccess.setPerson(person);
            appAccess.setApplication(application);
            appAccess.setUpdateTime(new Date());
            appAccess.setUpdatedBy(me.getPersonId());
            String allAccess = application.getCode().toLowerCase() + NONDEPARTMENT;
            Character nonDeptAccessCode = request.getParameter(allAccess).charAt(0);
            if (nonDeptAccessCode != 'N') {
                appAccess.setDepartmentAccess(nonDeptAccessCode);
                appAccess.setNonDepartmentAccess(nonDeptAccessCode);
                applicationAccess.add(appAccess);
            }
        }
        applicationAccessDao.saveApplicationAccess(applicationAccess);
        User user = userDao.findUser(personId);
        user.setApplicationAccess(applicationAccess);
        userDao.updateUser(user);
    }

    /**
     * Deletes existing acls for a user.
     *
     * @param personId for whom the acl will be deleted
     */
    private void deleteAcl(Integer personId, User me) {
        User user = userDao.findUser(personId);
        user.setApplicationAccess(null);
        user.setUpdateTime(new Date());
        user.setUpdatedBy(me.getPersonId());
        applicationAccessDao.deleteAclForPerson(personId);
        userDao.updateUser(user);
    }

    /**
     * Enables/Disables a user.
     *
     * @param personId the user to disable
     * @param me the one making the change
     * @param enable true if enabled, else false
     */
    private void enableUser(Integer personId, User me, boolean enable) {
        User user = userDao.findUser(personId);
        if (user.isActive() != enable) {
            user.setActive(enable);
            user.setApplicationAccess(null);
            user.setUpdateTime(new Date());
            user.setUpdatedBy(me.getPersonId());
            if (!enable) {
                user.setPassword("INVALID");
            }
            userDao.updateUser(user);
        }
    }
}
