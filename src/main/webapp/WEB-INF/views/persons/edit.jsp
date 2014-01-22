<%--
    Document   : PersonEdit
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="pageTitle">Edit person</c:set>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <h1>Edit person</h1>
        <hr />
        <c:url var="formAction" value="${submitUri}" />
        <form:form commandName="person" method="PUT" action="${formAction}">
            <fieldset>
                <h3>Personal</h3><form:hidden path="personId" />
                <div class="row">
                    <div class="col-md-4">
                        <label for="forename">Forename</label>
                        <form:input path="forename" maxlength="50" />
                    </div>
                    <div class="col-md-4">
                        <label for="middleName">Middle name</label>
                        <form:input path="middleName" maxlength="50" />
                    </div>
                    <div class="col-md-4">
                        <label for="surname">Surname</label>
                        <form:input path="surname" maxlength="50" />
                    </div>
                </div>
            </fieldset>
            <div class="row">
                <fieldset class="col-md-4">
                        <label for="congregationName">Congregation</label>
                        <form:input path="congregationName" autocomplete="off" />
                        <form:hidden path="congregationId" />
                </fieldset>
                <fieldset class="col-md-4">
                  <label for="birthDate">Date of birth</label>
                  <form:input class="datepicker" path="birthDate" />
                </fieldset>
            </div>
            <fieldset>
                <h3>Contact</h3>
                <div class="row">
                    <div class="col-md-4">
                        <label for="email">Email</label>
                        <form:input path="email" maxlength="50" />
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-4">
                        <label for="telephone">Home phone</label>
                        <form:input path="telephone" maxlength="15" />
                    </div>
                    <div class="col-md-4">
                        <label for="mobile">Mobile phone:</label>
                        <form:input path="mobile" maxlength="15" />
                    </div>
                    <div class="col-md-4">
                        <label for="workPhone">Work phone:</label>
                        <form:input path="workPhone" maxlength="15" />
                    </div>
                </div>
            </fieldset>
            <fieldset>
                <h3>Address</h3>
                <div class="row">
                    <div class="col-md-4">
                        <label for="street">Street</label>
                        <form:input path="street" maxlength="50"/>
                    </div>
                    <div class="col-md-4">
                        <label for="town">Town</label>
                        <form:input path="town" maxlength="50" />
                    </div>
                    <div class="col-md-4">
                        <label for="county">County</label>
                        <form:input path="county" maxlength="50" />
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-4">
                        <label for="postcode">Postcode</label>
                        <form:input path="postcode" maxlength="10" />
                    </div>
                </div>
            </fieldset>
            <fieldset>
                <h3>Comments</h3>
                <form:textarea path="comments" rows="5" cols="50"/>
            </fieldset>
            <br>
            <div class="form-actions">
                <input type="submit" class="btn btn-lg btn-success"/>
            </div>
        </form:form>
        <ol class="breadcrumb">
            <li><a href="<c:url value="/" />">Edifice</a></li>
            <li><a href="<c:url value="/persons" />">Persons</a></li>
            <li class="active">Edit</li>
        </ol>

        <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        <script type="text/javascript" src="<c:url value='/javascript/thirdparty/phoneformat-574.js' />" ></script>
        <script type="text/javascript" src="<c:url value='/javascript/persons.js' />" ></script>
    </body>
</html>
