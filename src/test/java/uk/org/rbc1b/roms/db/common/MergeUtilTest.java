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

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

/**
 * Test the {@link MergeUtil} class.
 */
public class MergeUtilTest {
    // CHECKSTYLE:OFF
    @SuppressWarnings("unchecked")
    @Test
    public void testMergeSingleStrings() {
        final List<Pair<String, String>> outputs = new ArrayList<Pair<String, String>>();

        MergeUtil.merge(Arrays.asList("foo"), Arrays.asList("foo"), String.CASE_INSENSITIVE_ORDER,
                new MergeUtil.Callback<String, String>() {
                    @Override
                    public void output(String leftValue, String rightValue) {
                        outputs.add(new ImmutablePair<String, String>(leftValue, rightValue));
                    }
                });

        assertEquals(Arrays.asList(new ImmutablePair<String, String>("foo", "foo")), outputs);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testMergeLeftEmpty() {
        final List<Pair<String, String>> outputs = new ArrayList<Pair<String, String>>();

        MergeUtil.merge(Collections.<String> emptyList(), Arrays.asList("foo"), String.CASE_INSENSITIVE_ORDER,
                new MergeUtil.Callback<String, String>() {
                    @Override
                    public void output(String leftValue, String rightValue) {
                        outputs.add(new ImmutablePair<String, String>(leftValue, rightValue));
                    }
                });

        assertEquals(Arrays.asList(new ImmutablePair<String, String>(null, "foo")), outputs);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testMergeRightEmpty() {
        final List<Pair<String, String>> outputs = new ArrayList<Pair<String, String>>();

        MergeUtil.merge(Arrays.asList("foo"), Collections.<String> emptyList(), String.CASE_INSENSITIVE_ORDER,
                new MergeUtil.Callback<String, String>() {
                    @Override
                    public void output(String leftValue, String rightValue) {
                        outputs.add(new ImmutablePair<String, String>(leftValue, rightValue));
                    }
                });

        assertEquals(Arrays.asList(new ImmutablePair<String, String>("foo", null)), outputs);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testMergeDisjointSingleStrings() {
        final List<Pair<String, String>> outputs = new ArrayList<Pair<String, String>>();

        MergeUtil.merge(Arrays.asList("foo"), Arrays.asList("bar"), String.CASE_INSENSITIVE_ORDER,
                new MergeUtil.Callback<String, String>() {
                    @Override
                    public void output(String leftValue, String rightValue) {
                        outputs.add(new ImmutablePair<String, String>(leftValue, rightValue));
                    }
                });

        assertEquals(Arrays.asList(new ImmutablePair<String, String>(null, "bar"), new ImmutablePair<String, String>(
                "foo", null)), outputs);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testMergeOverlappingStrings() {
        final List<Pair<String, String>> outputs = new ArrayList<Pair<String, String>>();

        MergeUtil.merge(Arrays.asList("bar", "foo"), Arrays.asList("foo", "quux"), String.CASE_INSENSITIVE_ORDER,
                new MergeUtil.Callback<String, String>() {
                    @Override
                    public void output(String leftValue, String rightValue) {
                        outputs.add(new ImmutablePair<String, String>(leftValue, rightValue));
                    }
                });

        assertEquals(Arrays.asList(new ImmutablePair<String, String>("bar", null), new ImmutablePair<String, String>(
                "foo", "foo"), new ImmutablePair<String, String>(null, "quux")), outputs);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testMergeDuplicateStrings() {
        final List<Pair<String, String>> outputs = new ArrayList<Pair<String, String>>();

        MergeUtil.merge(Arrays.asList("bar", "foo", "foo"), Arrays.asList("foo", "quux"),
                String.CASE_INSENSITIVE_ORDER, new MergeUtil.Callback<String, String>() {
                    @Override
                    public void output(String leftValue, String rightValue) {
                        outputs.add(new ImmutablePair<String, String>(leftValue, rightValue));
                    }
                });

        // note outputs [bar,] [foo,foo] [foo,] [,quux] NOT [bar,] [foo,foo] [foo,foo] [,quux]
        assertEquals(Arrays.asList(new ImmutablePair<String, String>("bar", null), new ImmutablePair<String, String>(
                "foo", "foo"), new ImmutablePair<String, String>("foo", null), new ImmutablePair<String, String>(null,
                "quux")), outputs);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testMergeHeterogeneousOverlapping() {
        final List<Pair<String, Container>> outputs = new ArrayList<Pair<String, Container>>();

        MergeUtil.merge(Arrays.asList("bar", "foo"),
                Arrays.asList(new Container("foo", "FooValue"), new Container("quux", "QuuxValue")),
                new MergeUtil.Compare<String, Container>() {
                    @Override
                    public int compare(String left, Container right) {
                        return String.CASE_INSENSITIVE_ORDER.compare(left, right.key);
                    }
                }, new MergeUtil.Callback<String, Container>() {
                    @Override
                    public void output(String left, Container right) {
                        outputs.add(new ImmutablePair<String, Container>(left, right));
                    }
                });

        assertEquals(Arrays.asList(new ImmutablePair<String, Container>("bar", null),
                new ImmutablePair<String, Container>("foo", new Container("foo", "FooValue")),
                new ImmutablePair<String, Container>(null, new Container("quux", "QuuxValue"))), outputs);
    }

    private static final class Container {
        private final String key;
        private final String value;

        private Container(String key, String value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public int hashCode() {
            return key.hashCode() + value.hashCode();
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Container)) {
                return false;
            }

            Container c = (Container) o;

            return c.key.equals(key) && c.value.equals(value);
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }
}
