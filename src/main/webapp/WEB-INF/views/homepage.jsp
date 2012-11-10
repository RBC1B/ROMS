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
        <div class="container">
            <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
            <h1>Dashboard</h1>
            <hr>
            <div class="row-fluid">
              <div class="well span4">
                <h3>Your Projects</h3>
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
