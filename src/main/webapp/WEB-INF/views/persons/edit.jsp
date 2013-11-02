<%--
    Document   : PersonEdit
    Created on : Feb 23, 2013, 2:08:09 PM
    Author     : oliver
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="pageTitle" value="Edit Person" />
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
            <h1>Edit Person</h1>
           <hr>
            <c:url var="formAction" value="${submitUri}" />
            <form:form commandName="person" method="PUT" action="${formAction}">
                <fieldset>
                    <legend>Name</legend>
                    <form:hidden path="personId" />
                    <label for="forename">Forename</label>
                    <form:input path="forename" maxlength="50" />
                    <label for="middleName">Middle Name</label>
                    <form:input path="middleName" maxlength="50" />
                    <label for="surname">Surname</label>
                    <form:input path="surname" maxlength="50" />
                </fieldset>
                <fieldset>
                    <legend>Spiritual</legend>
                    <label for="congregationName">Congregation</label>
                    <form:input path="congregationName" autocomplete="off" />
                    <form:hidden path="congregationId" />
                </fieldset>
                <fieldset>
                    <legend>Personal</legend>
                    <label for="birthDate">Date of birth</label>
                    <form:input class="datepicker" path="birthDate" placeholder="15/03/1980" data-date-format="dd/mm/yy" />
                </fieldset>
                <fieldset>
                    <legend>Contact</legend>
                    <label for="email">Email</label>
                    <form:input path="email" maxlength="50" />
                    <label for="telephone">Home phone</label>
                    <form:input path="telephone" maxlength="15" />
                    <label for="mobile">Mobile phone:</label>
                    <form:input path="mobile" maxlength="15" />
                    <label for="workPhone">Work phone:</label>
                    <form:input path="workPhone" maxlength="15" />
                </fieldset>
                <fieldset>
                    <legend>Address</legend>
                    <label for="street">Street</label>
                    <form:input path="street" maxlength="50"/>
                    <label for="town">Town</label>
                    <form:input path="town" maxlength="50" />
                    <label for="county">County</label>
                    <form:input path="county" maxlength="50" />
                    <label for="postcode">Postcode</label>
                    <form:input path="postcode" maxlength="10" />
                </fieldset>
                <fieldset>
                    <legend>Comments</legend>
                    <form:textarea path="comments" rows="5" />
                </fieldset>
                <div class="form-actions">
                    <input type="submit" class="btn btn-primary"/>
                </div>
            </form:form>

            <ol class="breadcrumb">
                <li><a href="<c:url value="/" />">Edifice</a></li>
                <sec:authorize access="hasPermission('VOLUNTEER', 'READ')">
                  <li><a href="<c:url value="/persons" />">Persons</a></li>
                </sec:authorize>
                <li class="active">Edit</li>
            </ol>

            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        <script type="text/javascript" src="<c:url value='/javascript/thirdparty/phoneformat-574.js' />" ></script>
        <script type="text/javascript" src="<c:url value='/javascript/persons.js' />" ></script>
    </body>
</html>
