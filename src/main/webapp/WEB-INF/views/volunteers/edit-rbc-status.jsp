<%--
Edit form for the volunteer data under the rbc status tab.
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="pageTitle" value="Edit ${forename} ${surname} RBC Status Information" />
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <div class="container-fluid">
            <h1>${forename} ${surname} - RBC Status Information</h1>
            <hr>

            <c:url var="formAction" value="${submitUri}" />
            <form:form commandName="volunteerRbcStatus" method="PUT" action="${formAction}">
                <fieldset>
                    <label>Form date</label>
                    <form:input path="formDate" class="datepicker" data-date-format="dd/mm/yy" type="text" value=""/>
                </fieldset>
                <fieldset>
                    <legend>Interview</legend>
                    <label>Interview date</label>
                    <form:input path="interviewDate" class="datepicker" data-date-format="dd/mm/yy" type="text" value=""/>
                    <label>Interviewer A</label>
                    <form:input path="interviewerAUserName" class="user" placeholder="User Name" autocomplete="off" />
                    <form:hidden path="interviewerAPersonId" />
                    <label>Interviewer B</label>
                    <form:input path="interviewerBUserName" class="user" placeholder="User Name" autocomplete="off" />
                    <form:hidden path="interviewerBPersonId" />
                    <label>Interview comments</label>
                    <form:input path="interviewComments" maxlength="150" />
                    <label>Joined date</label>
                    <form:input path="joinedDate" class="datepicker" data-date-format="dd/mm/yy" type="text" value=""/>
                    <label>Badge issue date</label>
                    <form:input path="badgeIssueDate" class="datepicker" data-date-format="dd/mm/yy" type="text" value=""/>
                </fieldset>
                <fieldset>
                    <legend>Availability</legend>
                    <a href="#" id="clear-availability">No days</a>|<a href="#" id="set-availability">All days</a>
                    <label class="checkbox">
                        <form:checkbox path="availabilityMonday" class="availability" /> Monday
                    </label>
                    <label class="checkbox">
                        <form:checkbox path="availabilityTuesday" class="availability" /> Tuesday
                    </label>
                    <label class="checkbox">
                        <form:checkbox path="availabilityWednesday" class="availability" /> Wednesday
                    </label>
                    <label class="checkbox">
                        <form:checkbox path="availabilityThursday" class="availability" /> Thursday
                    </label>
                    <label class="checkbox">
                        <form:checkbox path="availabilityFriday" class="availability" /> Friday
                    </label>
                    <label class="checkbox">
                        <form:checkbox path="availabilitySaturday" class="availability" /> Saturday
                    </label>
                    <label class="checkbox">
                        <form:checkbox path="availabilitySunday" class="availability" /> Sunday
                    </label>
                </fieldset>
                <fieldset>
                    <legend>Oversight</legend>
                    <label class="checkbox">
                        <form:checkbox path="oversight" /> Recommended
                    </label>
                    <label>Comments</label>
                    <form:input path="oversightComments" maxlength="50" />
                </fieldset>
                <fieldset>
                    <legend>Relief - UK</legend>
                    <label class="checkbox">
                        <form:checkbox path="reliefUK" /> Recommended
                    </label>
                    <label>Comments</label>
                    <form:input path="reliefUKComments" maxlength="50" />
                </fieldset>
                <fieldset>
                    <legend>Relief - Abroad</legend>
                    <label class="checkbox">
                        <form:checkbox path="reliefAbroad" /> Recommended
                    </label>
                    <label>Comments</label>
                    <form:input path="reliefAbroadComments" maxlength="50" />
                </fieldset>
                <fieldset>
                    <legend>HHC</legend>
                    <label>Form code</label>
                    <form:input path="hhcFormCode" maxlength="15" />
                </fieldset>
                <fieldset>
                    <button type="submit" class="btn">Submit</button>
                </fieldset>
            </form:form>
            
            <ol class="breadcrumb">
                <li><a href="<c:url value="/" />">Edifice</a></li>
                <sec:authorize access="hasPermission('VOLUNTEER', 'READ')">
                  <li><a href="<c:url value="/volunteers" />">Volunteers</a></li>
                </sec:authorize>
                <li class="active">#${volunteer.id}: ${volunteer.displayName} Edit RBC Status</li>
            </ol>           
            
            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        </div>
        <script type="text/javascript" src="<c:url value='/javascript/thirdparty/jquery-numeric-1.3.1.js' />" ></script>
        <script type="text/javascript" src="<c:url value='/javascript/volunteers.js' />" ></script>
    </body>
</html>

