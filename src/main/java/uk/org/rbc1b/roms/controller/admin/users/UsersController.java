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
package uk.org.rbc1b.roms.controller.admin.users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import uk.org.rbc1b.roms.controller.BadRequestException;
import uk.org.rbc1b.roms.controller.ResourceNotFoundException;
import uk.org.rbc1b.roms.controller.common.model.PersonModelFactory;
import uk.org.rbc1b.roms.db.Person;
import uk.org.rbc1b.roms.db.PersonDao;
import uk.org.rbc1b.roms.db.application.Application;
import uk.org.rbc1b.roms.db.application.ApplicationAccess;
import uk.org.rbc1b.roms.db.application.ApplicationAccessDao;
import uk.org.rbc1b.roms.db.application.ApplicationDao;
import uk.org.rbc1b.roms.db.application.User;
import uk.org.rbc1b.roms.db.application.UserDao;
import uk.org.rbc1b.roms.db.common.MergeUtil;
import uk.org.rbc1b.roms.security.AccessLevel;

/**
 * Read/write ROMS users.
 */
@Controller
@RequestMapping("/users")
public class UsersController {
    private static final ApplicationAccessComparator APPLICATION_ACCESS_COMPARATOR = new ApplicationAccessComparator();
    private static final String NONDEPARTMENT = "All";
    private static final String DEPARTMENT = "Dept";
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserModelFactory userModelFactory;
    @Autowired
    private ApplicationAccessDao applicationAccessDao;
    @Autowired
    private PersonDao personDao;
    @Autowired
    private PersonModelFactory personModelFactory;
    @Autowired
    private ApplicationDao applicationDao;

    /**
     * Display the list of users.
     *
     * @param model mvc model
     * @return view
     */
    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasPermission('DATABASE','READ')")
    public String showUserList(ModelMap model) {
        List<User> users = userDao.findAllUsers();
        List<UserModel> modelList = new ArrayList<UserModel>();
        for (User user : users) {
            modelList.add(userModelFactory.generateUserModel(user));
        }
        model.addAttribute("users", modelList);
        return "admin/users/list";
    }

    /**
     * User search, making partial (prefix) matches on the user name.
     *
     * @param name full name prefix to match against user name
     * @return model containing the list of users
     */
    @RequestMapping(value = "search", method = RequestMethod.GET, produces = "application/json")
    @PreAuthorize("hasPermission('DATABASE','READ')")
    @ResponseBody
    public List<UserSearchResult> findUsers(@RequestParam(value = "name", required = true) String name) {
        List<User> users = userDao.findUsersByUserNamePrefix(name);
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
     */
    @RequestMapping(value = "{personId}", method = RequestMethod.GET)
    @PreAuthorize("hasPermission('DATABASE','READ')")
    public String showUser(@PathVariable Integer personId, ModelMap model) {
        User user = userDao.findUser(personId);
        if (user == null) {
            throw new ResourceNotFoundException("No User with ID:" + personId);
        }
        model.addAttribute("user", userModelFactory.generateUserModel(user));
        List<ApplicationAccess> permissions = applicationAccessDao.findUserPermissions(personId);
        model.addAttribute("permissions", permissions);
        return "admin/users/show";
    }

    /**
     * Display form to create new user.
     *
     * @param model mvc model
     * @param userId user id (person id) to create
     * @return view
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    @PreAuthorize("hasPermission('DATABASE','ADD')")
    public String showCreateUserForm(ModelMap model, @RequestParam Integer userId) {

        // if the user already exists, redirect to their view page
        User user = userDao.findUser(userId);
        if (user != null) {
            return "redirect:" + UserModelFactory.generateUri(userId);
        }

        Person person = personDao.findPerson(userId);
        if (person == null) {
            throw new ResourceNotFoundException("No Person with ID:" + userId);
        }

        List<Application> applications = applicationDao.getApplications();

        model.addAttribute("applications",
                ApplicationAccessFormFactory.generateApplicationAccessForm(applications, null));
        model.addAttribute("person", personModelFactory.generatePersonModel(person));

        UserForm userForm = new UserForm();
        userForm.setUserId(userId);
        userForm.setActive(true);

        model.addAttribute("userForm", userForm);
        model.addAttribute("submitUri", UserModelFactory.generateUri(null));
        model.addAttribute("submitMethod", "POST");

        return "admin/users/edit";
    }

    /**
     * Handles create user form.
     *
     * @param userForm the form input
     * @param request the request
     * @return redirect to view
     */
    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasPermission('DATABASE','ADD')")
    public String createUser(@Valid UserForm userForm, HttpServletRequest request) {

        User user = userDao.findUser(userForm.getUserId());
        if (user != null) {
            throw new BadRequestException("User #" + userForm.getUserId() + " already exists");
        }

        Person person = personDao.findPerson(userForm.getUserId());
        if (person == null) {
            throw new BadRequestException("Person #" + userForm.getUserId() + " does not exist");
        }

        user = new User();
        user.setPersonId(userForm.getUserId());
        user.setActive(userForm.isActive());
        user.setUserName(userForm.getUserName());
        user.setPassword(getPasswordHash("", userForm.getPassword()));
        userDao.createUser(user);
        mergeUserAcl(user, request);

        return "redirect:" + UserModelFactory.generateUri(user.getPersonId());
    }

    /**
     * Display the form to edit an existing user.
     *
     * @param userId the user to update
     * @param model the MVC model
     * @return the jsp page to display
     */
    @RequestMapping(value = "{userId}/edit", method = RequestMethod.GET)
    @PreAuthorize("hasPermission('DATABASE','EDIT')")
    public String showUserForm(@PathVariable Integer userId, ModelMap model) {
        User user = userDao.findUser(userId);
        if (user == null) {
            throw new ResourceNotFoundException("No User with ID:" + userId);
        }
        List<Application> applications = applicationDao.getApplications();
        Person person = personDao.findPerson(userId);

        model.addAttribute("person", personModelFactory.generatePersonModel(person));

        UserForm userForm = new UserForm();
        userForm.setUserId(user.getPersonId());
        userForm.setUserName(user.getUserName());
        userForm.setActive(user.isActive());

        model.addAttribute("userForm", userForm);
        model.addAttribute(
                "applications",
                ApplicationAccessFormFactory.generateApplicationAccessForm(applications,
                        applicationAccessDao.findUserPermissions(userId)));
        model.addAttribute("submitUri", UserModelFactory.generateUri(userId));
        model.addAttribute("submitMethod", "PUT");
        model.addAttribute("user", userModelFactory.generateUserModel(user));

        return "admin/users/edit";
    }

    /**
     * Saves an update to an existing user.
     *
     * @param userId the user
     * @param userForm a valid user form
     * @param request the http request
     * @return redirect
     */
    @RequestMapping(value = "{userId}", method = RequestMethod.PUT)
    @PreAuthorize("hasPermission('DATABASE','EDIT')")
    public String saveUserUpdate(@PathVariable Integer userId, @Valid UserForm userForm, HttpServletRequest request) {
        User user = userDao.findUser(userId);
        if (user == null) {
            throw new ResourceNotFoundException("No User with ID:" + userId);
        }

        // Three things we could do: 1. disable user, 2. change password, 3. update acl
        if (StringUtils.isNotBlank(userForm.getPassword())) {
            user.setPassword(getPasswordHash("", userForm.getPassword()));
        }
        user.setActive(userForm.isActive());
        userDao.updateUser(user);
        mergeUserAcl(user, request);

        return "redirect:" + UserModelFactory.generateUri(userId);
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
        return encoder.encodePassword(password, salt.isEmpty() ? null : salt);
    }

    /**
     * Merge the user ACL. If any records are missing, set the to having no access,
     * rather than deleting the row. This means we can track who removed access.
     */
    private void mergeUserAcl(User user, HttpServletRequest request) {
        List<Application> applications = applicationDao.getApplications();

        List<ApplicationAccess> dbApplicationAccess = new ArrayList<>(applicationAccessDao.findUserPermissions(user
                .getPersonId()));
        List<ApplicationAccess> requestApplicationAccess = new ArrayList<>();
        for (Application application : applications) {
            ApplicationAccess appAccess = new ApplicationAccess();
            appAccess.setPersonId(user.getPersonId());
            appAccess.setApplication(application);

            String departmentAccess = application.getCode().toLowerCase() + DEPARTMENT;
            Character departmentAccessCode = request.getParameter(departmentAccess).charAt(0);
            appAccess.setDepartmentAccess(departmentAccessCode);

            String allAccess = application.getCode().toLowerCase() + NONDEPARTMENT;
            Character nonDeptAccessCode = request.getParameter(allAccess).charAt(0);
            appAccess.setNonDepartmentAccess(nonDeptAccessCode);
            requestApplicationAccess.add(appAccess);
        }

        MergeUtil.sortAndMerge(dbApplicationAccess, requestApplicationAccess, APPLICATION_ACCESS_COMPARATOR,
                new MergeUtil.Callback<ApplicationAccess, ApplicationAccess>() {
                    @Override
                    public void output(ApplicationAccess db, ApplicationAccess input) {
                        if (db != null && input != null) {
                            input.setApplicationAccessId(db.getApplicationAccessId());
                            applicationAccessDao.saveApplicationAccess(input);
                        } else if (db != null) {
                            // the access has been removed
                            db.setDepartmentAccess(AccessLevel.NOACCESS.getCode());
                            db.setNonDepartmentAccess(AccessLevel.NOACCESS.getCode());
                            applicationAccessDao.saveApplicationAccess(db);
                        } else {
                            // access has been added - new record
                            applicationAccessDao.saveApplicationAccess(input);
                        }
                    }
                });
    }

    private static class ApplicationAccessComparator implements Comparator<ApplicationAccess>, Serializable {
        private static final long serialVersionUID = 1L;

        @Override
        public int compare(ApplicationAccess a0, ApplicationAccess a1) {
            return ObjectUtils.compare(a0.getApplication().getCode(), a1.getApplication().getCode());
        }

    }

}
