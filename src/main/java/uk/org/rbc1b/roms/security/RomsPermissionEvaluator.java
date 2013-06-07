/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.security;

import java.io.Serializable;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

/**
 * Implementation of the permission validator to match the JACL AppNames and levels. with the part of the application being viewed
 *
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

        ROMSUserDetails user = (ROMSUserDetails) authentication.getPrincipal();
        ROMSGrantedAuthority authority = user.findAuthority((String) targetDomainObject);

        if (authority == null) {
            return false;
        }

        // the user authority (0-4) matches the ordinal values of the access levels.
        // the have permission the user authority must be greater or equal to the
        // access level required, i.e. accounts with edit access can read, but not add
        return authority.getDepartmentLevelAccess().intValue() >= level.ordinal();
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Access level values, corresponding to the levels 0-4.
     */
    public static enum AccessLevel {

        NOACCESS, READ, EDIT, ADD, DELETE
    }
}
