<%--
    Document   : circuitEdit
    Created on : 14-Jul-2012, 00:54:53
    Author     : oliver.elder.esq
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create/Edit Circuit - ROMS</title>
    </head>
    <body>
        <h1>Circuit</h1>
        <c:url value="/circuits" var="formAction"/>
        <form:form commandName="circuit" method="post" action="${formAction}">
            <form:label path="name">Name <form:input path="name" /></form:label>
            <input type="submit" />
        </form:form>
    </body>
</html>
