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

/**
 * Access level values for user permissions.
 */
public enum AccessLevel {

    NOACCESS('X'), READ('R'), EDIT('E'), ADD('A'), DELETE('D');

    private final char code;

    private AccessLevel(char code) {
        this.code = code;
    }

    public char getCode() {
        return code;
    }

    /**
     * Find the access level associated with the code.
     * @param code code
     * @return level
     */
    public static AccessLevel findAccessLevel(char code) {
        for (AccessLevel level : AccessLevel.values()) {
            if (level.getCode() == code) {
                return level;
            }
        }

        throw new IllegalArgumentException("Failed to find access level with code [" + code + "]");
    }

}
