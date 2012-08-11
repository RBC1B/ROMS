<%--
    Document   : circuit
    Created on : 01-Jul-2012, 00:06:12
    Author     : oliver.elder.esq
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="pageTitle" value="Circuit" />
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <h1>Circuit: Hello World!</h1>
        ${circuit}

        <%@ include file="/WEB-INF/views/common/footer.jsp" %>
    </body>
</html>
