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

function updateDateMap(dateClicked, thisObject) {
    var dateKey= "[data-date='" + dateClicked.format() + "']";
	var colour = $(dateKey).css('background-color');
	if(colour == "rgba(0, 0, 0, 0)" || colour == "rgb(255, 255, 255)" ){
                sendAvailabilityDate(dateClicked, "POST")
                .done(function(){
                    $(thisObject).css('background-color', '#3a3');
                });
	}else if (colour == "rgb(51, 170, 51)"){
                sendAvailabilityDate(dateClicked, "DELETE")
                .done(function(){
                    $(thisObject).css('background-color', '#fff');
                });
	}else if(colour == "rgb(255, 0, 0)"){
		alert("Your overseer has confirmed this date - please contact your overseer.");
	}else{
		alert("Unknown status - please contact Edifice.Help@gmail.com");
	}
}

function sendAvailabilityDate(date, updateMethod) {
    return $.ajax({
        url: updateUrl + "/" + date.format(),
        type: updateMethod,
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
        }
    });
}

function updateRequirements(requirement) {
    $.ajax({
        url: updateUrl + "/" + requirement,
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
        }
    });
}

function getExistingAvailableRecord(){
    return $.ajax({
        url: updateUrl + "/availability",
        type: "GET",
        cache: false,
        success: function(data){
            if(!data || data.length === 0){
                return;
            }
            if(data["transportRequired"] === true){
                document.getElementById("transportRequired").checked = true;
            }
            if(data["offerTransport"] === true){
                document.getElementById("offerTransport").checked = true;
            }
            if(data["accommodationRequired"] === true){
                document.getElementById("accommodationRequired").checked = true;
            }
        }
    });
}

function getExistingAttendanceRecords(){
    $.ajax({
        url: updateUrl + "/attendance",
        type: "GET",
        cache: false,
        success: function(data){
            if(!data || data.length === 0){
                return;
            }
                for(var key in data){
                    var attendanceDate = key;
                    var required = data[attendanceDate];
                    var dateKey= "[data-date='" + attendanceDate + "']";
                    if(required){
                        $(dateKey).css("background-color", "red");
                    }else{
                        $(dateKey).css("background-color", "#3a3");
                    }
                }
        }
    });
}