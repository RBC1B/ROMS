/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db.application;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 * Look up application user information.
 * @author oliver.elder.esq
 */
public interface UserDao {

    /**
     * Look up the user by name. Include the user permissions.
     * @param userName user name
     * @return user
     */
    @Transactional(readOnly = true)
    User findUserAndPermissions(String userName);

    /**
     * Look up the user by name. We do a prefix match on the name.
     * @param userName user name
     * @return users
     */
    @Transactional(readOnly = true)
    List<User> findUsers(String userName);

}
