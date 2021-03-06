/*
 * The MIT License
 *
 * Copyright 2014 RBC1B.
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
package uk.org.rbc1b.roms.tags;

import java.util.Map;
import uk.org.rbc1b.roms.security.AccessLevel;
import com.google.common.collect.ImmutableMap;

/**
 * Permissible permissions for Applications.
 *
 * @author ramindursingh
 */
public class PermissionMap {

    private static final Map<Character, String> ACL = ImmutableMap.of(AccessLevel.NOACCESS.getCode(), "None",
            AccessLevel.READ.getCode(), "Read Only", AccessLevel.EDIT.getCode(), "Edit Records",
            AccessLevel.ADD.getCode(), "Add New Records", AccessLevel.DELETE.getCode(), "Delete Records");

    /**
     * Returns the acl.
     *
     * @return the acl
     */
    public Map<Character, String> getAcl() {
        return ACL;
    }
}
