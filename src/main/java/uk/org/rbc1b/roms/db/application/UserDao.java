/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db.application;

import org.springframework.transaction.annotation.Transactional;

/**
 * Look up application user information.
 * @author oliver.elder.esq
 */
public interface UserDao {

    /**
     * Look up the user by name.
     * @param userName user name
     * @return user
     */
    @Transactional(readOnly = true)
    User findUser(String userName);
}
