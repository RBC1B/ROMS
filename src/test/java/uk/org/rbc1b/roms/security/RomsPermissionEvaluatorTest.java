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

import static org.junit.Assert.assertEquals;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author oliver.elder.esq
 */
public class RomsPermissionEvaluatorTest {
    // CHECKSTYLE:OFF

    private RomsPermissionEvaluator evaluator;
    private MockAuthentication authentication;

    @Before
    public void setUp() {
        evaluator = new RomsPermissionEvaluator();
        authentication = new MockAuthentication(1, "testname", "passwd");
        authentication.addAuthority(Application.ATTENDANCE, AccessLevel.DELETE, AccessLevel.DELETE);
        authentication.addAuthority(Application.CIRCUIT, AccessLevel.EDIT, AccessLevel.DELETE);
        authentication.addAuthority(Application.CONG, AccessLevel.ADD, AccessLevel.DELETE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidPermission() {
        evaluator.hasPermission(authentication, "AuthC", "Invalid");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValidPermissionTargetNotFound() {
        evaluator.hasPermission(authentication, "AuthUnknown", "READ");
    }

    @Test
    public void testValidPermissionDenied() {
        assertEquals(false, evaluator.hasPermission(authentication, Application.CIRCUIT.name(), "ADD"));
    }

    @Test
    public void testValidPermissionAllowed() {
        assertEquals(true, evaluator.hasPermission(authentication, Application.CONG.name(), "ADD"));
        assertEquals(true, evaluator.hasPermission(authentication, Application.ATTENDANCE.name(), "ADD"));
    }

    private class MockAuthentication implements Authentication {
        private static final long serialVersionUID = 1L;
        private final Map<Application, ROMSGrantedAuthority> authorities = new HashMap<Application, ROMSGrantedAuthority>();
        private final Integer userId;
        private final String password;
        private final String userName;

        private MockAuthentication(Integer userId, String userName, String password) {
            this.userId = userId;
            this.userName = userName;
            this.password = password;
        }

        private void addAuthority(Application application, AccessLevel departmentLevel, AccessLevel nonDepartmentLevel) {
            ROMSGrantedAuthority authority = new ROMSGrantedAuthority();
            authority.setApplication(application);
            authority.setDepartmentLevelAccess(departmentLevel);
            authority.setNonDepartmentLevelAccess(nonDepartmentLevel);

            authorities.put(application, authority);
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return this.authorities.values();
        }

        @Override
        public Object getCredentials() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public Object getDetails() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public Object getPrincipal() {
            return new ROMSUserDetails() {
                private static final long serialVersionUID = 1L;

                @Override
                public ROMSGrantedAuthority findAuthority(Application application) {
                    return authorities.get(application);
                }

                @Override
                public Integer getUserId() {
                    return userId;
                }

                @Override
                public Collection<? extends GrantedAuthority> getAuthorities() {
                    throw new UnsupportedOperationException("Not supported yet.");
                }

                @Override
                public String getPassword() {
                    return password;
                }

                @Override
                public String getUsername() {
                    return userName;
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

        @Override
        public boolean isAuthenticated() {
            return true;
        }

        @Override
        public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
            // do nothing
        }

        @Override
        public String getName() {
            return userName;
        }
    }
}
