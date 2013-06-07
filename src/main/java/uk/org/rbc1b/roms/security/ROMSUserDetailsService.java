/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.security;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uk.org.rbc1b.roms.db.application.ApplicationAccess;
import uk.org.rbc1b.roms.db.application.User;
import uk.org.rbc1b.roms.db.application.UserDao;

/**
 * Implement a user details service that allows us to store the user id.
 *
 * @author oliver.elder.esq
 */
@Service("userDetailsService")
public class ROMSUserDetailsService implements UserDetailsService {

    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        final User user = userDao.findUser(userName);
        if (user == null) {
            throw new UsernameNotFoundException("Failed to find user [" + userName + "]");
        }

        final Map<String, ROMSGrantedAuthority> authorityMap = new HashMap<String, ROMSGrantedAuthority>();
        for (ApplicationAccess access : user.getApplicationAccess()) {
            ROMSGrantedAuthority authority = new ROMSGrantedAuthority();
            authority.setApplication(access.getName());
            authority.setDepartmentLevelAccess(access.getDepartmentAccess());
            authority.setNonDepartmentLevelAccess(access.getNonDepartmentAccess());
            authorityMap.put(access.getApplication().getCode(), authority);
        }

        return new ROMSUserDetails() {
            @Override
            public ROMSGrantedAuthority findAuthority(String application) {
                return authorityMap.get(application);
            }

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return authorityMap.values();
            }

            @Override
            public String getPassword() {
                return user.getPassword();
            }

            @Override
            public String getUsername() {
                return user.getUserName();
            }

            @Override
            public Integer getUserId() {
                return user.getPersonId();
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        };
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
