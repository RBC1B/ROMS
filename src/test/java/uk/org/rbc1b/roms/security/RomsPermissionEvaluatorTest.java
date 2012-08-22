/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author rhioli
 */
public class RomsPermissionEvaluatorTest {
    //CHECKSTYLE:OFF

    private RomsPermissionEvaluator evaluator;
    private MockAuthentication authentication;

    @Before
    public void setUp() {
        evaluator = new RomsPermissionEvaluator();
        authentication = new MockAuthentication(Arrays.asList("AuthA:4", "AuthB:2", "AuthC:3"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidPermission() {
        evaluator.hasPermission(authentication, "AuthC", "Invalid");
    }

    @Test
    public void testValidPermissionTargetNotFound() {
        assertEquals(false, evaluator.hasPermission(authentication, "AuthUnknown", "READ"));
    }

    @Test
    public void testValidPermissionDenied() {
        assertEquals(false, evaluator.hasPermission(authentication, "AuthB", "ADD"));
    }

    @Test
    public void testValidPermissionAllowed() {
        assertEquals(true, evaluator.hasPermission(authentication, "AuthC", "ADD"));
        assertEquals(true, evaluator.hasPermission(authentication, "AuthA", "ADD"));
    }

    private class MockAuthentication implements Authentication {

        List<MockGrantedAuthority> authorities;

        private MockAuthentication(Collection<String> authorities) {
            this.authorities = new ArrayList<MockGrantedAuthority>(authorities.size());
            for (String authority : authorities) {
                this.authorities.add(new MockGrantedAuthority(authority));
            }
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return this.authorities;
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
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public boolean isAuthenticated() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public String getName() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    private class MockGrantedAuthority implements GrantedAuthority {

        private String authority;

        public MockGrantedAuthority(String authority) {
            this.authority = authority;
        }

        @Override
        public String getAuthority() {
            return this.authority;
        }
    }
}
