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
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <div class="container">
            <c:choose>
                <c:when test="${!empty kingdomHallForm.kingdomHallId}">
                    <h1>Edit Kingdom Hall - ${kingdomHallForm.name}</h1>
                </c:when>
                <c:otherwise>
                    <h1>Create New Kingdom Hall</h1>
                </c:otherwise>
            </c:choose>
            <hr>
            <c:url var="formAction" value="${submitUri}" />
            <form:form commandName="kingdomHallForm" method="${submitMethod}" action="${formAction}">
                    <form:hidden path="kingdomHallId" />
                <div class="control-group">
                    <label for="name">Name:</label>
                    <form:input path="name" placeholder="Kingdom Hall Name" /><br />
                </div>
                <div class="control-group">
                    <label for="street">Street:</label>
                    <form:input path="street" placeholder="Kingdom Hall Street" /><br/>
                    <label for="town">Town:</label>
                    <form:input path="town" placeholder="Kingdom Hall Town" /><br />
                    <label for="county">County:</label>
                    <form:input path="county" placeholder="Kingdom Hall County" /><br />
                    <label for="postcode">Postcode:</label>
                    <form:input path="postcode" placeholder="Kingdom Hall Postcode" /><br/>
                </div>
                <div class="control-group">
                    <label for="ownershipTypeId">Ownership Type:</label>
                    <form:select path="ownershipTypeId">
                        <form:option value="" label="None" />
                        <form:options items="${ownershipValues}" />
                    </form:select><br />
                </div>
                <div class="control-group">
                    <form:hidden path="titleHolderCongregationId" />
                    <label for="titleHolderCongregationName">Title Holding Congregation:</label>
                    <form:input path="titleHolderCongregationName" type="text" name="titleHolderCongregationName" placeholder="Title Holder" value="" id="searchinput" data-provide="typeahead" data-source="congregation.Name" maxlength="30" autocomplete="on" /><br>
                </div>
                <input type="submit" class="btn btn-primary"/>
            </form:form>
                
            <ol class="breadcrumb">
                <li><a href="<c:url value="/" />">Edifice</a></li>
                <sec:authorize access="hasPermission('KINGDOMHALL', 'READ')">
                  <li role="menuitem"><a href="<c:url value="/kingdom-halls" />">Kingdom Halls</a></li>
                </sec:authorize>
                  <li class="active">Edit</li>
            </ol>                
                
            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        </div>
        <script type="text/javascript" src="<c:url value='/javascript/kingdom-halls.js' />" ></script>
    </body>
</html>
