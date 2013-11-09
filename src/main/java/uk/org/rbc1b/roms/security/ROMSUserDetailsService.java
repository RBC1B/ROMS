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
 * @author oliver.elder.esq
 */
@Service("userDetailsService")
public class ROMSUserDetailsService implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        final User user = userDao.findUserAndPermissions(userName);
        if (user == null) {
            throw new UsernameNotFoundException("Failed to find user [" + userName + "]");
        }

        final Map<Application, ROMSGrantedAuthority> authorityMap = new HashMap<Application, ROMSGrantedAuthority>();
        for (ApplicationAccess access : user.getApplicationAccess()) {
            ROMSGrantedAuthority authority = new ROMSGrantedAuthority();
            authority.setApplication(Application.valueOf(access.getApplication().getCode()));
            authority.setDepartmentLevelAccess(AccessLevel.findAccessLevel(access.getDepartmentAccess()));
            authority.setNonDepartmentLevelAccess(AccessLevel.findAccessLevel(access.getNonDepartmentAccess()));
            authorityMap.put(Application.valueOf(access.getApplication().getCode()), authority);
        }

        return new ROMSUserDetails() {
            private static final long serialVersionUID = -2342863582753427493L;

            @Override
            public ROMSGrantedAuthority findAuthority(Application application) {
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

}
