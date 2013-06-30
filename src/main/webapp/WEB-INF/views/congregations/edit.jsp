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
            <h1>Congregation</h1>
            <hr>
            <c:url var="formAction" value="/congregations" />
            <form:form commandName="congregation" method="post" action="${formAction}">
                <div class="form">
                    <fieldset class="container-fluid">
                        <p>Congregation ID</p>
                        <form:input path="congregationId" readonly="true" placeholder="${congregationId}" />
                        <p>Congregation Name</p>
                        <form:input path="name" placeholder="Town, Name format" />
                    </fieldset>
                </div>
                <input type="submit" class="btn btn-primary" />
            </form:form>
            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        </div>
        <script type="text/javascript" src="<c:url value='/javascript/congregations.js' />" ></script>
    </body>
</html>
