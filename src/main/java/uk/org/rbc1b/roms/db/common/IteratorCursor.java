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

import java.util.Iterator;

/**
 * Convert an Iterator into a Cursor.
 * @param <T> Type pointed to by iterator/cursor
 */
public class IteratorCursor<T> implements Cursor<T> {
    private final Iterator<T> iter;
    private boolean ready;
    private T value;

    /**
     * Create cursor based on iterator.
     * @param iter Iterator
     */
    public IteratorCursor(Iterator<T> iter) {
        this.iter = iter;
    }

    /**
     * Create cursor based on any iterable object.
     * @param iterable Iterable object
     */
    public IteratorCursor(Iterable<T> iterable) {
        this(iterable.iterator());
    }

    @Override
    public boolean hasValue() {
        fill();

        return ready;
    }

    @Override
    public T value() {
        fill();

        if (!ready) {
            throw new IllegalStateException();
        }

        return value;
    }

    @Override
    public void advance() {
        ready = false;
    }

    /**
     * Convenience method to return the current value while advancing to the next one, like an iterator does.
     * @return Value that was current before this method was called
     */
    public T next() {
        T next = value();

        advance();

        return next;
    }

    private void fill() {
        if (ready) {
            return;
        }

        if (iter.hasNext()) {
            value = iter.next();
            ready = true;
        }
    }

}
