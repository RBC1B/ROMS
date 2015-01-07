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
        <title>RBC London & Home Counties - Availability for Projects</title>
    </head>
    <body>
        <nav class="navbar navbar-inverse navbar-static-top" role="navbar">
            <a class="navbar-brand" href="<c:url value="/" />"><img src="<c:url value='/images/logo-brand.png' />" alt="edifice" /></a>
        </nav>
        <div class="container">
            <h3>RBC London & Home Counties - Availability for Projects</h3>
            <div class="row">
                <div class="col-lg-5 col-lg-push-7">
                    <div class="row">
                        <label class="control-label col-sm-4 col-md-3" for="name">Project:</label>
                        <span><c:out value="${availabilityModel.projectName}" /></span>
                    </div>
                    <div class="row">                
                        <label class="control-label col-sm-4 col-md-3" for="name">Address:</label>
                        <span><c:out value="${availabilityModel.address}" /></span>
                    </div>
                    <div class="row">                
                        <label class="control-label col-sm-4 col-md-3" for="name">Department:</label>
                        <span><c:out value="${availabilityModel.departmentName}" /></span>
                    </div>
                    <div class="row form-group">                
                        <label class="control-label col-sm-4 col-md-3" for="name">Period for this session:</label>
                        <span id="startDate"></span> <b>to</b> <span id="endDate"></span> 
                    </div>
                    <h4>Additional Requirements/Information</h4>
                    <div id="working-sunday" value="${availabilityModel.workingSunday}"></div>
                    <form id="availability-update-form" class="form-horizontal">
                        <div class="checkbox">
                            <label>
                                <input id="accommodationRequired" type="checkbox"/> I will need local accommodation for this project
                            </label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input id="transportRequired" type="checkbox"/> I will require transport to get to this project
                            </label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input id="offerTransport" type="checkbox"/> I can give others transport to this project
                            </label>
                        </div>
                    </form>
                    <br/>
                    <div id='calendar-div'>
                        <div id='calendar'>
                        </div>
                        <div class="visible-lg">
                            <h5>To de-select a date, simply click on it again.</h5>
                        </div>
                    </div>
                </div>
            </div>
            <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
            <script type="text/javascript" src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
            <script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.6.0/moment.min.js"></script>
            <script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/fullcalendar/2.2.0/fullcalendar.min.js"></script>
            <script type="text/javascript" src="<c:url value='/javascript/project-availability.js' />"></script>
            <script>
                var workingSunday = $("#working-sunday").attr("value");
                var startDate = '<c:out value="${availabilityModel.fromDate}" />';
                var endDate = '<c:out value="${availabilityModel.endDate}" />';

                $(document).ready(function() {
                    $("#startDate").append(startDate);
                    $("#endDate").append(endDate);
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
                    margin: 0 auto;
                }
                #calendar-div {
                    height: 570px;
                }
            </style>
    </body>
</html>
