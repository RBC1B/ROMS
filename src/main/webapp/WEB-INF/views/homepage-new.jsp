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
                <div class="alert alert-error">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <strong>WARNING!</strong> By using Edifice, you agree to the <a href="#">Terms Of Use</a>. Please see the <a href="#">Help section</a> if you're having problems.
                </div>
                <div class="alert">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <strong>ALERT!</strong> Some of your Volunteers have updated their details. <a href="#">View report &gt;</a>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span5">
                  <img src="<c:url value='/images/spend-mockup.png' />">
                </div>
                <div class=""span5">
                  <img src="<c:url value='/images/velocity-mockup.png' />">
                </div>
            </div>
            <div class="row-fluid">
                <div class="span6">
                  <img src="<c:url value='/images/calendar-mockup.png' />">
                </div>
                <div class=""span3">
                  <img src="<c:url value='/images/volunteer-mockup.png' />">
                </div>
            </div>
            <div class="row-fluid">
              <div class="well span4">
               <button class="btn btn-info btn-block">Add Widgets</button>
              </div>
            </div>
           </div>
            <%@ include file="/WEB-INF/views/common/footer.jsp" %>

    </body>
</html>
