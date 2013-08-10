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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Merge inputs together, producing a single output row for matching inputs.
 */
public final class MergeUtil {
    private MergeUtil() {
    }

    /**
     * Sort two homogeneous collections and merge them, all according to the given comparator.
     * @param <T> Type of objects to merge
     * @param input1 First collection of objects to merge
     * @param input2 Second collection of objects to merge
     * @param comparator Comparator for sorting and merging objects
     * @param callback Callback to output merged objects
     */
    public static <T> void sortAndMerge(Collection<T> input1, Collection<T> input2, Comparator<T> comparator,
            Callback<T, T> callback) {
        // support null values for one or both of the collections
        List<T> list1 = input1 != null ? new ArrayList<T>(input1) : new ArrayList<T>();
        List<T> list2 = input2 != null ? new ArrayList<T>(input2) : new ArrayList<T>();

        Collections.sort(list1, comparator);
        Collections.sort(list2, comparator);

        merge(list1, list2, comparator, callback);
    }

    /**
     * Merge two homogeneous pre-sorted iterable inputs.
     * @param <T> Input type
     * @param left Left input
     * @param right Right input
     * @param comparator Comparator to compare inputs
     * @param callback Callback for each merge output
     */
    public static <T> void merge(Iterable<T> left, Iterable<T> right, final Comparator<T> comparator,
            Callback<T, T> callback) {
        merge(left, right, new Compare<T, T>() {
            @Override
            public int compare(T leftValue, T rightValue) {
                return comparator.compare(leftValue, rightValue);
            }
        }, callback);
    }

    /**
     * Merge two heterogenous pre-sorted iterable inputs.
     * @param <U> Left input type
     * @param <V> Right input type
     * @param left Left input
     * @param right Right input
     * @param comparator Comparator to compare inputs
     * @param callback Callback for each merge output
     */
    public static <U, V> void merge(Iterable<U> left, Iterable<V> right, Compare<U, V> comparator,
            Callback<U, V> callback) {
        Cursor<U> cursor1 = new IteratorCursor<U>(left.iterator());
        Cursor<V> cursor2 = new IteratorCursor<V>(right.iterator());

        while (cursor1.hasValue() && cursor2.hasValue()) {
            U value1 = cursor1.value();
            V value2 = cursor2.value();
            int n = comparator.compare(value1, value2);

            if (n == 0) {
                callback.output(value1, value2);
                cursor1.advance();
                cursor2.advance();
            } else if (n < 0) {
                callback.output(value1, null);
                cursor1.advance();
            } else {
                callback.output(null, value2);
                cursor2.advance();
            }
        }

        while (cursor1.hasValue()) {
            callback.output(cursor1.value(), null);
            cursor1.advance();
        }

        while (cursor2.hasValue()) {
            callback.output(null, cursor2.value());
            cursor2.advance();
        }
    }

    /**
     * Called when two elements from inputs are merged.
     * @param <U> Left input type
     * @param <V> Right input type
     */
    public interface Callback<U, V> {
        /**
         * Output two merged elements.
         * <p>
         * Either <code>left</code> or <code>right</code> may be null.
         * @param left Left input element
         * @param right Right input element
         */
        void output(U left, V right);
    }

    /**
     * Compare two heterogeneous merge inputs.
     * @param <U> Left input type
     * @param <V> Right input type
     * @see Comparable
     */
    public interface Compare<U, V> {
        /**
         * Returns an integer that is less than, equal to, or greater than zero depending on whether <code>left</code>
         * is less than, equal to, or greater than <code>right</code>.
         * @param left Left input element
         * @param right Right input element
         * @return Comparison result
         */
        int compare(U left, V right);
    }
}
