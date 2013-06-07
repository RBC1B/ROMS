/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.security;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Store the authenticated user details, including the user id.
 *
 * @author oliver.elder.esq
 */
public interface ROMSUserDetails extends UserDetails {

    /**
     * Look up the authority by the application name.
     *
     * @param application application name
     * @return authority
     */
    ROMSGrantedAuthority findAuthority(String application);

    /**
     * @return user id
     */
    Integer getUserId();
}
