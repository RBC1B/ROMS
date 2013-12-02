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
        <c:when test="${!empty kingdomHallForm.name}">
            <c:set var="pageTitle" value="Edit Kingdom Hall - ${kingdomHallForm.name}" />
        </c:when>
        <c:otherwise>
            <c:set var="pageTitle" value="Create Kingdom Hall" />
        </c:otherwise>
    </c:choose>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <c:choose>
            <c:when test="${!empty kingdomHallForm.name}">
                <h1>Edit Kingdom Hall - ${kingdomHallForm.name}</h1>
            </c:when>
            <c:otherwise>
                <h1>Create New Kingdom Hall</h1>
            </c:otherwise>
        </c:choose>
        <hr>
        <c:url var="formAction" value="${submitUri}" />
        <form:form commandName="kingdomHallForm" method="${submitMethod}" action="${formAction}" role="form">
            <div class="row">
                <div class="col-xs-6">
                    <div class="form-group">
                        <label for="name">Name:</label>
                        <div class="span3">
                            <form:input path="name" class="form-control" placeholder="Kingdom Hall Name" maxlength="50" /><br />
                        </div>
                    </div>
                    <div class="form-group">
                        <h4>Address:</h4>
                        <label for="street">Street:</label>
                        <div class="span3">
                            <form:input path="street" class="form-control" placeholder="Kingdom Hall Street" maxlength="50" />
                        </div>
                        <label for="town">Town:</label>
                        <div class="span2">
                            <form:input path="town" class="form-control" placeholder="Kingdom Hall Town" maxlength="50" />
                        </div>
                        <label for="county">County:</label>
                        <div class="span2">
                            <form:input path="county" class="form-control" placeholder="Kingdom Hall County" maxlength="50" />
                        </div>
                        <label for="postcode">Postcode:</label>
                        <div class="span2">
                            <form:input path="postcode" class="form-control" placeholder="Kingdom Hall Postcode" maxlength="10" />
                        </div>
                    </div>
                </div>
                <div class="col-xs-6">
                    <h4>Ownership Details:</h4>
                    <div class="form-group">
                        <label for="ownershipTypeCode">Ownership Type:</label>
                        <div class="span2">
                            <form:select style="width:175px" class="form-control" path="ownershipTypeCode">
                                <form:option value="" label="None" />
                                <form:options items="${ownershipValues}" />
                            </form:select><br />
                        </div>
                        <form:hidden path="titleHoldingCongregationId" />
                        <label for="titleHoldingCongregationName">Title Holding Congregation:</label>
                        <div class="span2">
                            <form:input path="titleHoldingCongregationName" class="typeahead form-control" type="text" name="titleHoldingCongregationName" placeholder="Title Holder" maxlength="50" /><br />
                        </div>
                        <br />
                        <input type="submit" class="btn btn-primary"/>
                    </div>
                </div>
            </div>
        </form:form>

        <ol class="breadcrumb">
            <li><a href="<c:url value="/" />">Edifice</a></li>
            <li role="menuitem"><a href="<c:url value="/kingdom-halls" />">Kingdom Halls</a></li>
            <li class="active">Edit</li>
        </ol>

        <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        <script type="text/javascript" src="<c:url value='/javascript/kingdom-halls.js' />" ></script>
    </body>
</html>
