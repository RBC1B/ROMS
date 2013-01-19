<%--
    Document   : circuitEdit
    Created on : 14-Jul-2012, 00:54:53
    Author     : oliver.elder.esq
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="pageTitle" value="Create/Edit Circuit" />
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <div class="container-fluid">
            <h1>Circuit</h1>
            <c:url var="formAction" value="/circuits" />
            <form:form commandName="circuit" method="post" action="${formAction}">
                <div class="form">
                    <input class="input-append" id="disabledInput" type="text" placeholder="Cicuit ID" disabled><br />
                    <form:label path="name"> <form:input path="name" placeholder="Circuit Name"/> </form:label>
                    <form:label path="coForename"><form:input path="coForename" placeholder="Circuit Overseer Forename" /></form:label>
                    <form:label path="coSurname"><form:input path="coSurname" placeholder="Circuit Overseer Surname" /></form:label>
                    <input type="submit" class="btn btn-primary" />
                </div>
            </form:form>
            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        </div>
        <script type="text/javascript" charset="utf8" src="<c:url value='/javascript/circuits.js' />" ></script>
    </body>
</html>
