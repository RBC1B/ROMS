<%--
Edit form for the volunteer data under the rbc status tab.
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="pageTitle" value="Edit ${forename} ${surname} RBC status information" />
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
            <h1>Edit ${forename} ${surname} - RBC status information</h1>
            <hr />

            <c:url var="formAction" value="${submitUri}" />
            <form:form class="form-horizontal" commandName="volunteerRbcStatus" method="PUT" action="${formAction}">
                <fieldset>
                    <legend>Form date</legend>
                    <div class="form-group">
                        <label for="formDate" class="control-label col-sm-3 col-md-2">Form date</label>
                        <div class="col-sm-9 col-md-2">
                            <form:input path="formDate" class="datepicker form-control" data-date-format="dd/mm/yy" type="text" value=""/>
                        </div>
                    </div>
                </fieldset>
                <fieldset>
                    <legend>Interview</legend>
                    <div class="form-group">
                        <label for="interviewDate" class="control-label col-sm-3 col-md-2">Interview date</label>
                        <div class="col-sm-9 col-md-2">
                            <form:input path="interviewDate" class="datepicker form-control" data-date-format="dd/mm/yy" type="text" value=""/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="joinedDate" class="control-label col-sm-3 col-md-2">Joined date</label>
                        <div class="col-sm-9 col-md-2">
                            <form:input path="joinedDate" class="datepicker form-control" data-date-format="dd/mm/yy" type="text" value=""/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="badgeIssueDate" class="control-label col-sm-3 col-md-2">Badge issue date</label>
                        <div class="col-sm-9 col-md-2">
                            <form:input path="badgeIssueDate" class="datepicker form-control" data-date-format="dd/mm/yy" type="text" value=""/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="interviewerAUserName" class="control-label col-sm-3 col-md-2">Interviewer A</label>
                        <div class="col-sm-9 col-md-2">
                            <form:input path="interviewerAUserName" class="user form-control" placeholder="User Name" autocomplete="off" />
                            <form:hidden path="interviewerAPersonId" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="interviewerBPersonId" class="control-label col-sm-3 col-md-2">Interviewer B</label>
                        <div class="col-sm-9 col-md-2">
                            <form:input path="interviewerBUserName" class="user form-control" placeholder="User Name" autocomplete="off" />
                            <form:hidden path="interviewerBPersonId" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="interviewComments" class="control-label col-sm-3 col-md-2">Interview comments</label>
                        <div class="col-sm-9 col-md-4">
                            <form:textarea path="interviewComments" class="form-control" rows="5" maxlength="200" />
                        </div>
                    </div>
                </fieldset>
                <fieldset>
                    <legend>Availability</legend>
                    <div class="col-sm-3 col-md-2">
                        &nbsp;
                    </div>
                    <div class="col-sm-9 col-md-10">
                        <div class="form-group">
                            <strong><a href="#" id="clear-availability">Clear all days</a> | <a href="#" id="set-availability">Tick all days</a></strong>
                        </div>
                        <div class="form-group">
                        <label class="checkbox-inline">
                            <form:checkbox path="availabilityMonday" class="availability" /> Monday
                        </label>
                        <label class="checkbox-inline">
                            <form:checkbox path="availabilityTuesday" class="availability" /> Tuesday
                        </label>
                        <label class="checkbox-inline">
                            <form:checkbox path="availabilityWednesday" class="availability" /> Wednesday
                        </label>
                        <label class="checkbox-inline">
                            <form:checkbox path="availabilityThursday" class="availability" /> Thursday
                        </label>
                        <label class="checkbox-inline">
                            <form:checkbox path="availabilityFriday" class="availability" /> Friday
                        </label>
                        <label class="checkbox-inline">
                            <form:checkbox path="availabilitySaturday" class="availability" /> Saturday
                        </label>
                        <label class="checkbox-inline">
                            <form:checkbox path="availabilitySunday" class="availability" /> Sunday
                        </label>
                        </div>
                    </div>
                    </fieldset>
                <fieldset>
                    <legend>Oversight</legend>
                    <div class="col-sm-3 col-md-2">
                        &nbsp;
                    </div>
                    <div class="form-group col-sm-9 col-md-10">
                        <label class="checkbox">
                            <form:checkbox path="oversight" /> Recommended
                        </label>
                    </div>
                    <div class="form-group">
                        <label for="oversightComments" class="col-sm-3 col-md-2 control-label">Comments</label>
                        <div class="col-sm-9 col-md-4">
                            <form:textarea path="oversightComments" class="form-control" rows="3" maxlength="200" />
                        </div>
                    </div>
                </fieldset>
                <fieldset>
                    <legend>Relief - UK</legend>
                        <div class="col-sm-3 col-md-2">
                            &nbsp;
                        </div>
                    <div class="form-group col-sm-9 col-md-10">
                        <label class="checkbox">
                            <form:checkbox path="reliefUK" /> Recommended
                        </label>
                    </div>
                    <div class="form-group">
                        <label for="reliefUKComments" class="control-label col-sm-3 col-md-2">Comments</label>
                        <div class="col-sm-9 col-md-4">
                            <form:textarea path="reliefUKComments" class="form-control" rows="2" maxlength="50" />
                        </div>
                    </div>
                </fieldset>
                <fieldset>
                    <legend>Relief - abroad</legend>
                        <div class="col-sm-3 col-md-2">
                            &nbsp;
                        </div>
                    <div class="form-group col-sm-9 col-md-10">
                    <label class="checkbox">
                        <form:checkbox path="reliefAbroad" /> Recommended
                    </label>
                    </div>
                    <div class="form-group">
                        <label for="reliefAbroadComments" class="control-label col-sm-3 col-md-2">Comments</label>
                        <div class="col-sm-9 col-md-4">
                            <form:textarea path="reliefAbroadComments" class="form-control" rows="2" maxlength="50" />
                        </div>
                    </div>
                </fieldset>
                <fieldset>
                    <legend>HHC</legend>
                    <div class="form-group">
                        <label for="hhcFormCode" class="control-label col-sm-3 col-md-2">Form code</label>
                        <div class="col-sm-9 col-md-2">
                            <form:input path="hhcFormCode" class="form-control" maxlength="15" />
                        </div>
                    </div>
                </fieldset>
                <fieldset>
                    <button type="submit" class="btn btn-default btn-success">Submit</button>
                </fieldset>
            </form:form>

            <ol class="breadcrumb">
                <li><a href="<c:url value="/" />">Edifice</a></li>
                <li><a href="<c:url value="/volunteers" />">Volunteers</a></li>
                <li class="active">Edit ${forename} ${surname} - RBC status information</li>
            </ol>

            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        <script type="text/javascript" src="<c:url value='/javascript/thirdparty/jquery-numeric-1.3.1.js' />" ></script>
        <script type="text/javascript" src="<c:url value='/javascript/volunteers.js' />" ></script>
    </body>
</html>

