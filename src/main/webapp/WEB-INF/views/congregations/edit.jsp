<%--
    Document   : CongregationEdit
    Created on : 06-01-2013 18:12
    Author     : ben.read
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="pageTitle" value="Create/Edit Congregation" />
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <div class="container-fluid">
            <h1>Circuit</h1>
            <hr>
            <c:url var="formAction" value="/congregation" />
            <form:form commandName="circuit" method="post" action="${formAction}">
                <div class="form">
                    <input class="input-append" id="disabledInput" type="text" placeholder="Congregation ID" disabled><br />
                    <form:label path="name"> <form:input path="name" placeholder="Congregation Name"/> </form:label>
                    <form:label path="coForename"><form:input path="coForename" placeholder="Circuit Overseer Forename" /></form:label>
                    <form:label path="coSurname"><form:input path="coSurname" placeholder="Circuit Overseer Surname" /></form:label>
                    <input type="submit" class="btn btn-primary" />
                </div>
            </form:form>
            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        </div>
       <script type="text/javascript" src="<c:url value='/javascript/congregations.js' />" ></script>
    </body>
</html>
