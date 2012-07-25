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
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>JSP Page</title>
    </head>
    <body>
        <sec:authorize access="hasPermission('Circuit', 'READ')"><a href="<c:url value="/circuits" />">Circuits</a></sec:authorize>
        <h1>Homepage: Hello World!</h1>
        <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
    </body>
</html>