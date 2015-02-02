<%--
    Document   : view
    Created on : Jan 21, 2015, 4:02:18 PM
    Author     : rahulsingh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <%@ include file="/WEB-INF/views/volunteers/update/header.jsp" %>
    <body>
        <div id="relative-path" data-relative-path="<c:url value="/" />"></div>
        <div class="container">
            <nav class="navbar navbar-inverse navbar-static-top" role="navbar">
                <a class="navbar-brand" href="<c:url value="/" />"><img src="<c:url value='/images/logo-brand.png' />" alt="edifice" /></a>
            </nav>
        </div>
        <div class="container">
            <h1>RBC London and Home Counties</h1>
        </div>
        <div class="container">
            <div class="alert alert-success" role="alert">Your contact details have now been confirmed as correct. Thank you.</div>
        </div>
        <div class="container" >
            <a href="<c:url value='/' />">Edifice Login Page</a>
        </div>
        <%@ include file="/WEB-INF/views/common/footer-min.jsp" %>
        <script type="text/javascript" src="<c:url value='/javascript/common.js' />"></script>
        <script type="text/javascript" src="<c:url value='/javascript/thirdparty/phoneformat-574.js' />" ></script>
        <script type="text/javascript" src="<c:url value='/javascript/volunteer-update.js' />" ></script>
    </body>
</html>

