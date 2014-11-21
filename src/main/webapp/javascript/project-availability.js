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


var selectedDate = {};

function updateDateMap(dateClicked, thisObject) {
    if (selectedDate[dateClicked.format()] == 1) {
        selectedDate[dateClicked.format()] = 0;
        sendAvailabilityDate(dateClicked, "DELETE");
        $(thisObject).css('background-color', '#fff');
    } else {
        selectedDate[dateClicked.format()] = 1;
        var result = sendAvailabilityDate(dateClicked, "POST");
        $(thisObject).css('background-color', '#3a3');
    }
}

function sendAvailabilityDate(date, updateMethod) {
    $.ajax({
        url: updateUrl + "/" + date.format() + "/" + updateMethod,
        type: updateMethod,
        cache: false,
        statusCode: {
            302: function() {
                alert("302 error - could not connect to server.");
                return false;
            },
            403: function() {
                alert("403 error - problem with security token/dates");
                return false;
            },
            503: function() {
                alert("503 error - problem with server handling your request");
                return false;
            }
        },
        success: function(data, status, xhr) {
            return true;
        }
    });
}

function updateRequirements(requirement) {
    $.ajax({
        url: updateUrl + "/" + requirement + "/" + "PUT",
        type: "PUT",
        cache: false,
        statusCode: {
            302: function() {
                alert("302 error - could not connect to server.");
            },
            403: function() {
                alert("403 error - problem with security token/dates");
            },
            503: function() {
                alert("503 error - problem with server handling your request");
            }
        },
        success: function(data, status, xhr) {
            alert("Your requirement has been updated.");
        }
    });
}