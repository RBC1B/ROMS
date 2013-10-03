<%--
Document   : kingdomhallEdit
Created on : 08-Sep-2012, 17:46:53
Author     : oliver.elder.esq
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:choose>
        <c:when test="${!empty kingdomHallForm.kingdomHallId}">
            <c:set var="pageTitle" value="Edit Kingdom Hall - ${kingdomHallForm.kingdomHallId}" />
        </c:when>
        <c:otherwise>
            <c:set var="pageTitle" value="Create Kingdom Hall" />
        </c:otherwise>
    </c:choose>
    <c:set var="pageTitle" value="Create/Edit Kingdom Hall" />
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <div class="container">
            <h1>Kingdom Hall</h1>
            <hr>
            <c:url var="formAction" value="/kingdom-halls/edit" />
            <form:form commandName="kingdomHallForm" method="post" action="${formAction}">
                <form:input path="kingdomHallId" placeholder="Kingdom Hall Id" readonly="true"/>
                <form:label path="*"><form:input path="*" placeholder="Kingdom Hall Name" /></form:label>
                <form:label path="*"><form:input path="*" placeholder="Kingdom Hall Street" /></form:label>
                <form:label path="*"><form:input path="*" placeholder="Kingdom Hall Town" /></form:label>
                <form:label path="*"><form:input path="*" placeholder="Kingdom Hall County" /></form:label>
                <form:label path="*"><form:input path="*" placeholder="Kingdom Hall Postcode" /></form:label>
                <form:input path="*" type="text" name="*" placeholder="Title Holder" value="" id="searchinput" data-provide="typeahead" data-source="congregation.Name" maxlength="30" autocomplete="on"  /><br>
                <input type="submit" class="btn btn-primary"/>
            </form:form>
            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        </div>
        <script type="text/javascript" src="<c:url value='/javascript/kingdom-halls.js' />" ></script>
    </body>
</html>
