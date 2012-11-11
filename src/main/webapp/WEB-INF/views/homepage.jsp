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
                <h3>Forthcoming Projects</h3>
                <ul class="nav nav-tabs nav-stacked">
                    <li>Potters Bar</li>
                    <li>Potters Bar</li>
                    <li>Potters Bar</li>
                </ul>
              </div>
              <div class="well span4">
                <h3>Announcements</h3>
                <ul class="nav nav-tabs nav-stacked">
                    <p>RBC ROMS Launch Date Announced!
                        <a href="">click here for more info ></a></p>
                </ul>
              </div>
              <div class="well span4">
                <h3>My ToDo List</h3>
                <ul class="nav nav-tabs nav-stacked">
                    <li>Get an iPad</li>
                    <li>Get an iPhone</li>
                    <li>Get an iCar</li>
                    <li>Order Superyacht</li>
                </ul>
              </div>
            </div>
            <div class="row-fluid">
              <div class="well span4">
                <h3>My Projects</h3>
                <ul class="nav nav-tabs nav-stacked">
                    <li>Herpendon</li>
                    <li>East Molseley</li>
                    <li>Potters Bar</li>
                </ul>
              </div>
              <div class="well span4">
                <h3>Recent Volunteers</h3>
                <ul class="nav nav-tabs nav-stacked">
                    <li>Moses</li>
                    <li>Abraham</li>
                    <li>Joshua</li>
                </ul>
              </div>
              <div class="well span4">
                <h3>Unused Volunteers</h3>
                <ul class="nav nav-tabs nav-stacked">
                    <li>Nimrod</li>
                    <li>Cain</li>
                    <li>Balaam</li>
                </ul>
              </div>
            </div>
            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        </div>
    </body>
</html>
