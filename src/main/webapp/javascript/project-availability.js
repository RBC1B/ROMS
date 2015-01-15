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
$(document).ready(function() {
    var workingSunday = $("#working-sunday").attr("value");
    var startDate = $("#start-date").attr("value");
    var endDate = $("#end-date").attr("value");
    var calEndDate = incrementDate(endDate);

    $("#startDate").append(startDate);
    $("#endDate").append(endDate);

    $('#calendar').fullCalendar({
        header: {
            left: "title"
        },
        events: [
            {
                start: startDate,
                end: endDate,
                rendering: 'inverse-background'
            }
        ],
        eventColor: '#F0F0F0',
        dayClick: function(date, jsEvent, view) {
            if (date >= Date.parse(startDate) && date <= Date.parse(endDate)) {
                updateDateMap(date, this);
            }
        }
    });

    $('#calendar').fullCalendar('gotoDate', startDate);
    getExistingAvailableRecord();
    getExistingAttendanceRecords();

    $('#accommodationRequired').on("click", function(event) {
        updateRequirements("accommodationRequired");
    });
    $('#transportRequired').on("click", function(event) {
        updateRequirements("transportRequired");
    });

    $('#offerTransport').on("click", function(event) {
        updateRequirements("offerTransport");
    });

    function updateDateMap(dateClicked, thisObject) {
        if (workingSunday === "false") {
            if (checkIfSunday(dateClicked)) {
                return;
            }
        }
        var dateKey = "[data-date='" + dateClicked.format() + "']";
        var colour = $(dateKey).css('background-color');
        if (colour === "rgba(0, 0, 0, 0)" || colour == "rgb(255, 255, 255)") {
            setAvailabilityDate(dateClicked)
                    .done(function() {
                $(thisObject).css('background-color', 'rgb(255, 255, 0)');
            });
        } else if (colour === "rgb(255, 255, 0)") {
            deleteAvailabilityDate(dateClicked)
                    .done(function() {
                $(thisObject).css('background-color', 'rgba(0,0,0,0');
            });
        } else if (colour === "rgb(0, 128, 0)") {
            alert("Your overseer has confirmed this date - please contact your overseer.");
        } else {
            alert("Unknown status - please contact Edifice.Help@gmail.com");
        }
    }

    function setAvailabilityDate(date) {
        return $.ajax({
            url: window.location.href + "/" + date.format(),
            type: 'POST',
            cache: false,
            error: function(jqXHR, textStatus) {
                alert("Failed to set the date. Status: " + textStatus);
            }
        });
    }

    function deleteAvailabilityDate(date) {
        return $.ajax({
            url: window.location.href + "/" + date.format(),
            type: 'DELETE',
            cache: false,
            error: function(jqXHR, textStatus) {
                alert("Failed to clear the date. Status: " + textStatus);
            }
        });
    }

    function updateRequirements(requirement) {
        $.ajax({
            url: window.location.href + "/" + requirement,
            type: "PUT",
            cache: false,
            error: function(jqXHR, textStatus) {
                alert("Failed to set the value. Status: " + textStatus);
            }
        });
    }

    function getExistingAvailableRecord() {
        return $.ajax({
            url: window.location.href + "/availability",
            type: "GET",
            cache: false,
            success: function(data) {
                if (!data || data.length === 0) {
                    return;
                }
                if (data["transportRequired"] === true) {
                    document.getElementById("transportRequired").checked = true;
                }
                if (data["offerTransport"] === true) {
                    document.getElementById("offerTransport").checked = true;
                }
                if (data["accommodationRequired"] === true) {
                    document.getElementById("accommodationRequired").checked = true;
                }
            }
        });
    }

    function getExistingAttendanceRecords() {
        $.ajax({
            url: window.location.href + "/attendance",
            type: "GET",
            cache: false,
            success: function(data) {
                if (!data || data.length === 0) {
                    return;
                }
                for (var key in data) {
                    var attendanceDate = key;
                    var required = data[attendanceDate];
                    var dateKey = "[data-date='" + attendanceDate + "']";
                    if (required) {
                        $(dateKey).css("background-color", "rgb(0, 128, 0)");
                    } else {
                        $(dateKey).css("background-color", "rgb(255, 255, 0)");
                    }
                }
            }
        });
    }

    function incrementDate(myDate) {
        var newDate = new Date(myDate);
        newDate.setDate(newDate.getDate() + 1);
        var month = newDate.getMonth();
        if (month < 10)
            month = "0" + month;
        var date = newDate.getDate();
        if (date < 10)
            date = "0" + date;
        var dateStr = newDate.getFullYear() + "-" + month + "-" + date;
        return dateStr;
    }

    function checkIfSunday(dateStr) {
        var myDate = new Date(dateStr);
        return (myDate.getDay() === 0);
    }
});