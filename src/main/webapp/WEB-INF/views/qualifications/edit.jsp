<%--
    Document   : qualificationEdit
    Created on : 23-Aug-2012, 20:23:17
    Author     : Tina
--%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="pageTitle" value="Create New Qualification" />
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <div class="container-fluid">
            <h1>Qualification</h1>
            <hr>
            <c:url var="formAction" value="/qualifications" />
            <form:form commandName="qualification" method="post" action="${formAction}">
                <div class="form">
                    <fieldset class="container-fluid">
                        <form:input path="qualificationId" readonly="true" placeholder="${qualificationId}" />
                        <p>Qualification Name</p>
                        <form:input path="name" placeholder="Qualification Name" />
                        <p>Description</p>
                        <form:input path="description" placeholder="Description" />
                    </fieldset>
                </div>
                <input type="submit" class="btn btn-primary" />
            </form:form>
            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        </div>
        <script type="text/javascript" src="<c:url value='/javascript/qualifications.js' />" ></script>
    </body>
</html>
