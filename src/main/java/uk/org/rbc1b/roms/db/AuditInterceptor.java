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
 * @author oliver.elder.esq
 */
public class AuditInterceptor extends EmptyInterceptor {
    private static final long serialVersionUID = 5012613778658433063L;

    @Override
    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState,
            String[] propertyNames, Type[] types) {
        boolean modified = false;

        if (entity instanceof UpdateAuditable) {
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

        if (entity instanceof CreateAuditable) {
            for (int i = 0; i < propertyNames.length; i++) {
                if ("createTime".equals(propertyNames[i])) {
                    currentState[i] = new Date();
                    modified = true;
                } else if ("createdBy".equals(propertyNames[i])) {
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

        if (entity instanceof UpdateAuditable) {
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

        if (entity instanceof CreateAuditable) {
            for (int i = 0; i < propertyNames.length; i++) {
                if ("createTime".equals(propertyNames[i])) {
                    state[i] = new Date();
                    modified = true;
                } else if ("createdBy".equals(propertyNames[i])) {
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
