<%--
    Show an error page when the volunteer update link is expired or invalid.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <%@ include file="/WEB-INF/views/volunteer-contact/fragments/header.jsp" %>
    <body>
        <div class="container">
            <nav class="navbar navbar-inverse navbar-static-top" role="navbar">
                <a class="navbar-brand" href="<c:url value="/" />"><img src="<c:url value='/images/logo-brand.png' />" alt="edifice" /></a>
            </nav>
        </div>
        <div class="container">
             <h1>RBC London and Home Counties</h1>
        </div>
        <div class="container">
            <p>You have tried to update your details but the link is incorrect or expired.</p>
            <a href="<c:url value='/' />">Please re-submit your update request.</a>
        </div>
    </body>
</html>
