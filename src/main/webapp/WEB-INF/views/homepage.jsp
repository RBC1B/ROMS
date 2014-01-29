<%--
    Homepage dashboard.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="pageTitle">RBC Online Management System for Construction</c:set>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <h1>Dashboard</h1>
        <hr />
        <div class="row">
            <div class="well-edifice col-md-4 col-xs-6">
                <div class="well">
                    <!--Nested the well into another div with padding inside to give space between wells. Yoshi 09/10/13-->
                    <h3>Organisations</h3>
                    <ul class="nav nav-tabs nav-stacked">
                        <li>Congregation</li>
                        <li>Circuit</li>
                    </ul>
                </div>
            </div>
            <div class="well-edifice col-md-4 col-xs-6">
                <div class="well">
                    <h3>Kingdom Hall</h3>
                    <ul class="nav nav-tabs nav-stacked">
                        <li>List of Kingdom Halls</li>
                    </ul>
                </div>
            </div>
            <div class="well-edifice col-md-4 col-xs-6">
                <div class="well">
                    <h3>Projects</h3>
                    <ul class="nav nav-tabs nav-stacked">
                        <li>Project coordination</li>
                        <li>Project design</li>
                        <li>Upcoming projects</li>
                    </ul>
                </div>
            </div>
            <!--Made 2 rows into 1, so that, for smaller screen sizes, the "Projects" well doesn't stand on its own. Just to make it look better! Yoshi 09/10/13-->
            <div class="well-edifice col-md-4 col-xs-6">
                <div class="well">
                    <h3>Volunteers</h3>
                    <ul class="nav nav-tabs nav-stacked">
                        <li>Contact details</li>
                        <li>Skills & training</li>
                        <li>Gate list</li>
                    </ul>
                </div>
            </div>
            <div class="well-edifice col-md-4 col-xs-6">
                <div class="well">
                    <h3>My Profile</h3>
                    <ul class="nav nav-tabs nav-stacked">
                        <li>Ramindur</li>
                    </ul>
                </div>
            </div>
        </div>
        <%@ include file="/WEB-INF/views/common/footer.jsp" %>
    </body>
</html>
