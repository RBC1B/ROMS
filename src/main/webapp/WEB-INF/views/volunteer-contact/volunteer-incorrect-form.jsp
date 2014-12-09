<%--
    Show an error page when the volunteer update link is expired or invalid.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Edifice Volunteer Contact Update Request</title>
    </head>
    <body>
        <div class="container">
            <nav class="navbar navbar-inverse navbar-static-top" role="navbar">
                <a class="navbar-brand" href="<c:url value="/" />"><img src="<c:url value='/images/logo-brand.png' />" alt="edifice" /></a>
            </nav>
        </div>
        <div class="container"
             <h1>RBC London and Home Counties</h1>
        </div>
        <div class="container">
            You have tried to update your details but the link is incorrect or expired.<br/>
            <a href="/">Please re-submit your update request.</a>
        </div>
    </body>
</html>
