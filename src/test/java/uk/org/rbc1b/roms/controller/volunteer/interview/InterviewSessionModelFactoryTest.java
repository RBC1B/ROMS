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
package uk.org.rbc1b.roms.controller.volunteer.interview;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Test the {@code InterviewSessionModelFactory}.
 */
public class InterviewSessionModelFactoryTest {
    /**
     * Test formatTime.
     */
    @Test
    public void testFormatTime() {
        assertEquals(null, InterviewSessionModelFactory.formatDisplayTime(null));
        assertEquals("15:23", InterviewSessionModelFactory.formatDisplayTime("1523"));

    }

    /**
     * Test parseTime.
     */
    @Test
    public void testParseTime() {
        assertEquals(null, InterviewSessionModelFactory.parseDisplayTime(null));
        assertEquals("1523", InterviewSessionModelFactory.parseDisplayTime("15:23"));

    }

    /**
     * Test parseTime.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testParseInvalidTimeTooShort() {
        assertEquals("1523", InterviewSessionModelFactory.parseDisplayTime("1523"));

    }

}
