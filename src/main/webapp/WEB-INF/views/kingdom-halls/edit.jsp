<%--
    Create or edit a kingdom hall.
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:choose>
        <c:when test="${!empty kingdomHallForm.name}">
            <c:set var="pageTitle">Edit Kingdom Hall - ${kingdomHallForm.name}</c:set>
        </c:when>
        <c:otherwise>
            <c:set var="pageTitle">Create new Kingdom Hall</c:set>
        </c:otherwise>
    </c:choose>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <c:choose>
            <c:when test="${!empty kingdomHallForm.name}">
                <h1>Edit Kingdom Hall - <c:out value="${kingdomHallForm.name}" /></h1>
            </c:when>
            <c:otherwise>
                <h1>Create new Kingdom Hall</h1>
            </c:otherwise>
        </c:choose>
        <hr />
        <c:url var="formAction" value="${submitUri}" />
        <form:form class="form-horizontal" commandName="kingdomHallForm" method="${submitMethod}" action="${formAction}" role="form">
            <fieldset>
            <div class="form-group">
                <label class="control-label col-sm-3 col-md-2" for="name">Name</label>
                <div class="col-sm-9 col-md-3">
                    <form:input path="name" class="form-control" placeholder="Kingdom  Name" maxlength="50" />
                </div>
            </div>
            <div class="col-md-offset-2 col-md-10 col-sm-offset-3 col-sm-9">
                <h4 class="text-left">Address</h4>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3 col-md-2" for="street">Street</label>
                <div class="col-sm-9 col-md-3">
                    <form:input path="street" class="form-control" placeholder="Kingdom Hall Street" maxlength="50" />
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3 col-md-2" for="town">Town</label>
                <div class="col-sm-9 col-md-3">
                    <form:input path="town" class="form-control" placeholder="Kingdom Hall Town" maxlength="50" />
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3 col-md-2" for="county">County</label>
                <div class="col-sm-9 col-md-3">
                    <form:input path="county" class="form-control" placeholder="Kingdom Hall County" maxlength="50" />
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3 col-md-2" for="postcode">Postcode</label>
                <div class="col-sm-9 col-md-3">
                    <form:input path="postcode" class="form-control" placeholder="Kingdom Hall Postcode" maxlength="10" />
                </div>
            </div>
            <div class="col-md-offset-2 col-md-10 col-sm-offset-3 col-sm-9">
                <h4 class="text-left">Ownership details</h4>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3 col-md-2" for="ownershipTypeCode">Ownership type</label>
                <div class="col-sm-4 col-md-2">
                    <form:select class="form-control" path="ownershipTypeCode">
                        <form:option value="" label="None" />
                        <form:options items="${ownershipValues}" />
                    </form:select>
                </div>
            </div>
            <div class="form-group">
                <form:hidden path="titleHoldingCongregationId" />
                <label class="control-label col-sm-3 col-md-2" for="titleHoldingCongregationName">Titleholder congregation</label>
                <div class="col-sm-9 col-md-3">
                    <form:input path="titleHoldingCongregationName" class="typeahead form-control" type="text" name="titleHoldingCongregationName" placeholder="Title Holder" maxlength="50" />
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3 col-md-2"></label>
                <div class="col-sm-9 col-md-2">
                    <input type="submit" class="btn btn-edifice"/>
                </div>
            </div>    
            </fieldset>
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
