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

import java.util.HashMap;
import java.util.Map;

/**
 * Permissible permissions for Applications.
 *
 * @author ramindursingh
 */
public class PermissionMap {

    private HashMap<String, String> acl;

    /**
     * Returns a map.
     * Constructor.
     */
    public PermissionMap() {
        acl = new HashMap<String, String>();
        acl.put("N", "None - Select One");
        acl.put("R", "Read Only");
        acl.put("E", "Edit Records");
        acl.put("A", "Add New Records");
        acl.put("D", "Delete Records");
    }

    /**
     * Returns the acl.
     *
     * @return the acl
     */
    public Map getAcl() {
        return acl;
    }
}
