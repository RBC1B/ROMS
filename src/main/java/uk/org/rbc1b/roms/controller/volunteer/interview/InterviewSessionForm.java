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
package uk.org.rbc1b.roms.controller.volunteer.interview;

import javax.validation.constraints.NotNull;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Data used to populate and submit the interview session edit.
 */
public class InterviewSessionForm {
    @NotNull
    private Integer kingdomHallId;
    private String kingdomHallName;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private DateTime date;
    private String time;
    private String comments;

    public Integer getKingdomHallId() {
        return kingdomHallId;
    }

    public void setKingdomHallId(Integer kingdomHallId) {
        this.kingdomHallId = kingdomHallId;
    }

    public String getKingdomHallName() {
        return kingdomHallName;
    }

    public void setKingdomHallName(String kingdomHallName) {
        this.kingdomHallName = kingdomHallName;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

}
