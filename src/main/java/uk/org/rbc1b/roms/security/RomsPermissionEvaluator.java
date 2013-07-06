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
