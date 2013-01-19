<%--
    Document   : homepage
    Created on : 30-Jun-2012, 12:46:06
    Author     : oliver.elder.esq
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="pageTitle" value="RBC Online Management System for Construction" />
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
            <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <div class="container-fluid">
            <h1>Dashboard</h1>
            <hr>
            <div class="row-fluid">
              <div class="well span4">
                <h3>Organisations</h3>
                <ul class="nav nav-tabs nav-stacked">
                    <li>Congregation</li>
                    <li>Circuit</li>
                </ul>
              </div>
              <div class="well span4">
                <h3>Kingdom Hall</h3>
                <ul class="nav nav-tabs nav-stacked">
                    <li>List Of Kingdom Halls</li>
                        <a href="">Details of Kingdom Halls ></a></p>
                </ul>
              </div>
              <div class="well span4">
                <h3>Projects</h3>
                <ul class="nav nav-tabs nav-stacked">
                    <li>Project Coordination</li>
                    <li>Project Design</li>
                    <li>Upcoming Projects</li>
                </ul>
              </div>
            </div>
            <div class="row-fluid">
              <div class="well span4">
                <h3>Volunteers</h3>
                <ul class="nav nav-tabs nav-stacked">
                    <li>Contact Details</li>
                    <li>Skills & Training</li>
                    <li>Gate List</li>
                </ul>
              </div>
              <div class="well span4">
                <h3>My Profile</h3>
                <ul class="nav nav-tabs nav-stacked">
                    <li>Ramindur</li>
                </ul>
              </div>
            </div>
            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        </div>
       <script type="text/javascript" charset="utf-8" src="<c:url value='/javascript/circuits.js' />" ></script>

    </body>
</html>
