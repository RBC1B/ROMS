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
        <link rel="stylesheet" type="text/css" href="<c:url value='/css/roms.css' />" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>RBC London &amp; Counties - Availability for Projects</title>
    </head>
    <body>
        <div id="working-sunday" value="${availabilityModel.workingSunday}"></div>
        <div id="start-date" value='<c:out value="${availabilityModel.fromDate}" />'></div>
        <div id="end-date" value='<c:out value="${availabilityModel.endDate}" />'></div>
        <nav class="navbar navbar-inverse navbar-static-top" role="navbar">
            <a class="navbar-brand" href="<c:url value="/" />"><img src="<c:url value='/images/logo-brand.png' />" alt="edifice" /></a>
        </nav>
        <div class="container-fluid">
            <h3>RBC London &amp; Counties - Availability for Projects (<c:out value="${availabilityModel.volunteer}" />)</h3>
            <div class="row">
                <div class="col-md-6">
                    <div class="row form-group">
                        <label class="control-label col-md-2">Project:</label>
                        <div class="col-md-4"><c:out value="${availabilityModel.projectName}" /></div>
                    </div>
                    <div class="row form-group">
                        <label class="control-label col-md-2">Address:</label>
                        <div class="col-md-4"><c:out value="${availabilityModel.address}" /></div>
                    </div>
                    <div class="row form-group">
                        <label class="control-label col-md-2">Department:</label>
                        <div class="col-md-4"><c:out value="${availabilityModel.departmentName}" /></div>
                    </div>
                    <div class="row form-group">
                        <label class="control-label col-md-2">Period:</label>
                        <div class="col-md-4"><span id="startDate"></span> <b>to</b> <span id="endDate"></span></div>
                    </div>
                    <h4>Additional Information</h4>
                    <form id="availability-update-form" class="form-horizontal">
                        <div class="checkbox">
                            <label>
                                <input id="offerTransport" type="checkbox"> I can give others transport to this project.
                            </label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input id="accommodationRequired" type="checkbox"> I need accommodation for this project.
                            </label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input id="transportRequired" type="checkbox"> I need transport to get to this project.
                            </label>
                        </div>
                    </form>
                    <h5><span style='background-color: rgb(255,255,0)'>Yellow cells</span> indicate that you are available on that day.</h5>
                    <h5><span style='background-color: rgb(0,255,0)'>Green cells</span> indicate that these dates have been confirmed by your team.</h5>
                    <h5>White cells indicate that you are unavailable.</h5>
                    <h5>To de-select a date, simply click on it again.</h5>
                </div>
                <div class="col-md-6">
                    <div id='calendar-div'>
                        <div id='calendar'>
                        </div>   
                    </div>
                </div>
            </div>
        </div>
        <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script type="text/javascript" src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.6.0/moment.min.js"></script>
        <script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/fullcalendar/2.2.0/fullcalendar.min.js"></script>
        <script type="text/javascript" src="<c:url value='/javascript/project-availability.js' />"></script>
        <style>
            #calendar {
                margin: 0 auto;
            }
        </style>
    </body>
</html>
