<%--
    Document   : edit
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
        <div class="container-fluid">
            <h1>Edit Person</h1>
           <hr>
            <c:url var="formAction" value="${submitUri}" />
            <form:form class="form-horizontal" commandName="person" method="post" action="${formAction}">
                <form:hidden path="personId" />
                <div class="control-group">
                    <label class="control-label" for="forename">Forename:</label>
                    <div class="controls">
                        <form:input path="forename" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="middleName">Middle Name:</label>
                    <div class="controls">
                        <form:input path="middleName" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="surname">Surname:</label>
                    <div class="controls">
                        <form:input path="surname" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="congregationName">Congregation:</label>
                    <div class="controls">
                        <form:input path="congregationName" autocomplete="off" />
                        <form:hidden path="congregationId" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="birthDate">Date of birth:</label>
                    <div class="controls">
                        <form:input class="datepicker" path="birthDate" placeholder="15/03/1980" data-date-format="dd/mm/yy" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="email">Email:</label>
                    <div class="controls">
                        <form:input path="email" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="telephone">Home phone:</label>
                    <div class="controls">
                        <form:input path="telephone" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="mobile">Mobile phone:</label>
                    <div class="controls">
                        <form:input path="mobile" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="workPhone">Work phone:</label>
                    <div class="controls">
                        <form:input path="workPhone" />
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label" for="street">Address:</label>
                    <div class="controls">
                        <form:input path="street" placeholder="Street" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="town"></label>
                    <div class="controls">
                        <form:input path="town" placeholder="Town" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="county"></label>
                    <div class="controls">
                        <form:input path="county" placeholder="County" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="postcode"></label>
                    <div class="controls">
                        <form:input path="postcode" placeholder="Postcode" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="comments">Comments</label>
                    <div class="controls">
                        <form:textarea path="comments" rows="5" />
                    </div>
                </div>
                <div class="form-actions">
                    <input type="submit" class="btn btn-primary"/>
                </div>
            </form:form>
            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        </div>
        <script type="text/javascript" src="<c:url value='/javascript/persons.js' />" ></script>
    </body>
</html>