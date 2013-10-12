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
            <h1>Dashboard</h1>
            <hr>
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
                    <li>List Of Kingdom Halls</li>
                        <a href="">Details of Kingdom Halls ></a></p>
                  </ul>
                </div>
              </div>
              <div class="well-edifice col-md-4 col-xs-6">
                  <div class="well">
                <h3>Projects</h3>
                <ul class="nav nav-tabs nav-stacked">
                    <li>Project Coordination</li>
                    <li>Project Design</li>
                    <li>Upcoming Projects</li>
                </ul>
                  </div>
              </div>
            <!--/div-->
            <!--div class="row"-->
            <!--Made 2 rows into 1, so that, for smaller screen sizes, the "Projects" well doesn't stand on its own. Just to make it look better! Yoshi 09/10/13-->
              <div class="well-edifice col-md-4 col-xs-6">
                <div class="well">
                  <h3>Volunteers</h3>
                    <ul class="nav nav-tabs nav-stacked">
                      <li>Contact Details</li>
                      <li>Skills & Training</li>
                      <li>Gate List</li>
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
