<%--
Edit form for the volunteer data under the rbc status tab.
Author: oliver.elder.esq
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

            <c:url var="formAction" value="${submitUri}" />
            <form:form commandName="volunteerRbcStatus" method="PUT" action="${formAction}">
                <fieldset>
                    <label>Form date</label>
                    <form:input path="formDate" class="datepicker" data-date-format="dd/mm/yy" type="text" value=""/>
                </fieldset>
                <fieldset>
                    <h3>Interview</h3>
                    <label>Interview date</label>
                    <form:input path="interviewDate" class="datepicker" data-date-format="dd/mm/yy" type="text" value=""/>
                    <label>Interviewer A</label>
                    <form:input path="interviewerAUserName" class="user" placeholder="User Name" />
                    <form:hidden path="interviewerAPersonId" />
                    <label>Interviewer B</label>
                    <form:input path="interviewerBUserName" class="user" placeholder="User Name" />
                    <form:hidden path="interviewerBPersonId" />
                    <label>Interview comments</label>
                    <form:input path="interviewComments" maxlength="150" />
                    <label>Joined date</label>
                    <form:input path="joinedDate" class="datepicker" data-date-format="dd/mm/yy" type="text" value=""/>
                    <label>Badge issue date</label>
                    <form:input path="badgeIssueDate" class="datepicker" data-date-format="dd/mm/yy" type="text" value=""/>
                </fieldset>
                <fieldset>
                    <h3>Availability</h3>
                    <label class="checkbox">
                        <form:checkbox path="availabilityMonday" /> Monday
                    </label>
                    <label class="checkbox">
                        <form:checkbox path="availabilityTuesday" /> Tuesday
                    </label>
                    <label class="checkbox">
                        <form:checkbox path="availabilityWednesday" /> Wednesday
                    </label>
                    <label class="checkbox">
                        <form:checkbox path="availabilityThursday" /> Thursday
                    </label>
                    <label class="checkbox">
                        <form:checkbox path="availabilityFriday" /> Friday
                    </label>
                    <label class="checkbox">
                        <form:checkbox path="availabilitySaturday" /> Saturday
                    </label>
                    <label class="checkbox">
                        <form:checkbox path="availabilitySunday" /> Sunday
                    </label>
                </fieldset>
                <fieldset>
                    <h3>Oversight</h3>
                    <label class="checkbox">
                        <form:checkbox path="oversight" /> Recommended
                    </label>
                    <label>Comments</label>
                    <form:input path="oversightComments" maxlength="50" />
                </fieldset>
                <fieldset>
                    <h3>Relief - UK</h3>
                    <label class="checkbox">
                        <form:checkbox path="reliefUK" /> Recommended
                    </label>
                    <label>Comments</label>
                    <form:input path="reliefUKComments" maxlength="50" />
                </fieldset>
                <fieldset>
                    <h3>Relief - Abroad</h3>
                    <label class="checkbox">
                        <form:checkbox path="reliefAbroad" /> Recommended
                    </label>
                    <label>Comments</label>
                    <form:input path="reliefAbroadComments" maxlength="50" />
                </fieldset>
                <fieldset>
                    <h3>HHC</h3>
                    <label>Form code</label>
                    <form:input path="hhcFormCode" maxlength="15" />
                </fieldset>
                <fieldset>
                    <button type="submit" class="btn">Submit</button>
                </fieldset>
            </form:form>
            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        </div>
        <script type="text/javascript" src="<c:url value='/javascript/jquery-numeric-1.3.1.js' />" ></script>
        <script type="text/javascript" src="<c:url value='/javascript/volunteers.js' />" ></script>
    </body>
</html>

