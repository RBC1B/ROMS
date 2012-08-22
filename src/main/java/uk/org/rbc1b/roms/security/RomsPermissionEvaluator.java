/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.security;

import java.io.Serializable;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.StringUtils;

/**
 * Implementation of the permission validator to match the JACL AppNames and levels.
 * with the part of the application being viewed
 * @author oliver.elder.esq
 */
public class RomsPermissionEvaluator implements PermissionEvaluator {

    /**
     * @param authentication authentication (username, authorities) derived form the data source
     * @param targetDomainObject object type to be viewed/modified, e.g. Circuit
     * @param permission permission level required, matching the AccessLevel enumeration
     * @return true if the user can perform the task
     */
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {

        AccessLevel level;
        try {
            level = AccessLevel.valueOf(permission.toString());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid access level [" + permission + "]");
        }

        for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
            String[] authority = StringUtils.split(grantedAuthority.getAuthority(), ":");
            if (!((String) targetDomainObject).equals(authority[0])) {
                continue;
            }

            // the user authority (0-4) matches the ordinal values of the access levels.
            // thje have permission the user authority must be greater or equal to the
            // access level required, i.e. accounts with edit access can read, but not add
            return Integer.parseInt(authority[1]) >= level.ordinal();
        }

        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        throw new UnsupportedOperationException("Not supported yet.");


    }

    /**
     * Access level values, corresponding to the levels 0-3.
     */
    public static enum AccessLevel {

        NOACCESS, READ, EDIT, ADD, DELETE
    }
}
