<%-- 
    Document   : availability-update
    Created on : Nov 18, 2014, 3:33:06 PM
    Author     : ramindursingh
    Form to update one's availability for a project
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" />
        <link href="//cdnjs.cloudflare.com/ajax/libs/fullcalendar/2.2.0/fullcalendar.min.css" rel="stylesheet" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Edifice Volunteer Availability Update Form</title>
    </head>
    <body>
        <div class="container">
            <nav class="navbar navbar-inverse navbar-static-top" role="navbar">
                <a class="navbar-brand" href="<c:url value="/" />"><img src="<c:url value='/images/logo-brand.png' />" alt="edifice" /></a>
            </nav>
        </div>
        <div class="container"
             <h1>RBC London and Home Counties</h1>
            <br />
            <br/><b>Project: </b><c:out value="${availabilityModel.projectName}" />
            <br /><b>Address: </b><c:out value="${availabilityModel.address}" />
            <br/><b>Department: </b><c:out value="${availabilityModel.departmentName}" />
            <br /><b>Volunteer: </b><c:out value="${availabilityModel.volunteer}" />
        </div>
        <div class="container">
            <p>Please indicate the days that you are available by clicking on the light green shaded days:
                <br/><b>From: </b><c:out value="${availabilityModel.fromDate}" />
                <br/><b>To: </b><c:out value="${availabilityModel.toDate}" />
            </p>
        </div>
            <div id="working-sunday" value="${availabilityModel.workingSunday}" />
        <form id="availability-update-form" class="form-horizontal"  action="${submitUrl}" method="${submitMethod}">
            <div id='calendar'>
            </div>
            <div class="container">
                To de-select a date, simply click on it again.
            </div>
            <div class="container">
                <div class="col-lg-6">
                    <div class="input-group">
                        <input id="accommodationRequired" type="checkbox"> Accommodation Required
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="col-lg-6">
                    <div class="input-group">
                        <input id="transportRequired" type="checkbox"> Transport Required
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="col-lg-6">
                    <div class="input-group">
                        <input id="offerTransport" type="checkbox"> Can give others transportation
                    </div>
                </div>
            </div>
        </form>
        <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script type="text/javascript" src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.6.0/moment.min.js"></script>
        <script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/fullcalendar/2.2.0/fullcalendar.min.js"></script>
        <script type="text/javascript" src="/javascript/project-availability.js"></script>
        <script>
            var updateUrl = $('#availability-update-form').attr('action');
            var updateMethod = $('#availability-update-form').attr('method');
            var workingSunday = $("#working-sunday").attr("value");
            $(document).ready(function() {

                $('#calendar').fullCalendar({
                    header: {
                        left: "title"
                    },
                    events: [
                        {
                            start: '<c:out value="${availabilityModel.fromDate}" />',
                            end: '<c:out value="${availabilityModel.endDate}" />',
                            rendering: 'background'
                        }
                    ],
                    dayClick: function(date, jsEvent, view) {
                        if (date >= Date.parse('<c:out value="${availabilityModel.fromDate}" />') && date <= Date.parse('<c:out value="${availabilityModel.toDate}" />')) {
                                updateDateMap(date, this);
                        }
                    }
                });

                $('#calendar').fullCalendar('gotoDate', '<c:out value="${availabilityModel.fromDate}" />');
                
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
            });
        </script>
        <style>
            #calendar {
                max-width: 700px;
                margin: 0 auto;
            }
        </style>
    </body>
</html>