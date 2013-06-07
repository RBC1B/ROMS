/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db;

import java.io.Serializable;
import java.util.Date;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import uk.org.rbc1b.roms.security.ROMSUserDetails;

/**
 * Hibernate interceptor to add the user id and timestamp to any updates if the table supports it.
 *
 * @author oliver.elder.esq
 */
public class AuditInterceptor extends EmptyInterceptor {

    @Override
    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
        boolean modified = false;

        if (entity instanceof Auditable) {
            for (int i = 0; i < propertyNames.length; i++) {
                if ("updateTime".equals(propertyNames[i])) {
                    currentState[i] = new Date();
                    modified = true;
                } else if ("updatedBy".equals(propertyNames[i])) {
                    currentState[i] = findUserId();
                    modified = true;
                }
            }
        }
        return modified;
    }

    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        boolean modified = false;

        if (entity instanceof Auditable) {
            for (int i = 0; i < propertyNames.length; i++) {
                if ("updateTime".equals(propertyNames[i])) {
                    state[i] = new Date();
                    modified = true;
                } else if ("updatedBy".equals(propertyNames[i])) {
                    state[i] = findUserId();
                    modified = true;
                }
            }
        }
        return modified;
    }

    private Integer findUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ROMSUserDetails user = (ROMSUserDetails) authentication.getPrincipal();

        return user.getUserId();
    }
}
