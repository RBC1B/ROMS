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
package uk.org.rbc1b.roms.db.common;

/**
 * A cursor pointing to an element in a sequence.
 * <p>
 * This is somewhat like an <code>Iterator</code>, except that is is "full": the "current value" can be requested
 * repeatedly. The value changes only after calling <code>advance()</code>.
 * <p>
 * The cursor may be "invalid", in which case calling <code>value</code> will result in an exception.
 * @param <T> Type of element
 */
public interface Cursor<T> {
    /**
     * Tests if this cursor is pointing to a valid value.
     * @return True if value is valid
     */
    boolean hasValue();

    /**
     * Gets the current value pointed to by the cursor, if any.
     * @return Current value
     * @throws IllegalStateException Cursor is not valid
     */
    T value() throws IllegalStateException;

    /**
     * Advance to the next value in the sequence.
     * <p>
     * After calling this method, {@link #hasValue} and {@link #value} may return different values.
     */
    void advance();

}
