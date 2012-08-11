<%--
    Document   : homepage
    Created on : 30-Jun-2012, 12:46:06
    Author     : oliver.elder.esq
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="pageTitle" value="RBC Online Management System for Construction" />
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/roms.css" />" />
    </head>
    <body>
        <sec:authorize access="hasPermission('Circuit', 'READ')"><a href="<c:url value="/circuits" />">Circuits</a></sec:authorize>
        <h1>Homepage: Hello World!</h1>
        <img src="<c:url value="/images/oli-lion.jpg" />" height="140" width="104" alt="test image" />
        <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
        <%@ include file="/WEB-INF/views/common/footer.jsp" %>
    </body>
</html>
