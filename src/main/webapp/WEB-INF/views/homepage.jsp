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
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <h1>RBC Online Management System for Construction</h1>
        <img src="<c:url value="/images/oli-lion.jpg" />" height="140" width="104" alt="test image" />
        <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
        <%@ include file="/WEB-INF/views/common/footer.jsp" %>
    </body>
</html>
