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
            <c:set var="pageTitle">Edit kingdom hall - ${kingdomHallForm.name}</c:set>
        </c:when>
        <c:otherwise>
            <c:set var="pageTitle">Create new kingdom hall</c:set>
        </c:otherwise>
    </c:choose>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <c:choose>
            <c:when test="${!empty kingdomHallForm.name}">
                <h1>Edit kingdom hall - <c:out value="${kingdomHallForm.name}" /></h1>
            </c:when>
            <c:otherwise>
                <h1>Create new kingdom hall</h1>
            </c:otherwise>
        </c:choose>
        <hr />
        <c:url var="formAction" value="${submitUri}" />
        <form:form commandName="kingdomHallForm" method="${submitMethod}" action="${formAction}" role="form">
            <div class="row">
                <div class="col-md-3">
                    <div class="form-group">
                        <label for="name">Name:</label>
                        <form:input path="name" class="form-control" placeholder="Kingdom Hall Name" maxlength="50" /><br />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="row">
                    <div class="col-md-12">
                        <h4>Address:</h4>
                    </div>
                    <div class="clearfix"></div>
                    <div class="col-md-3">
                        <label for="street">Street:</label>
                        <form:input path="street" class="form-control" placeholder="Kingdom Hall Street" maxlength="50" />
                    </div>
                    <div class="col-md-3">
                        <label for="town">Town:</label>
                        <form:input path="town" class="form-control" placeholder="Kingdom Hall Town" maxlength="50" />
                    </div>
                    <div class="col-md-3">
                        <label for="county">County:</label>
                        <form:input path="county" class="form-control" placeholder="Kingdom Hall County" maxlength="50" />
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3">
                        <label for="postcode">Postcode:</label>
                        <form:input path="postcode" class="form-control" placeholder="Kingdom Hall Postcode" maxlength="10" />
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <h4>Ownership details:</h4>
                    <div class="form-group">
                        <label for="ownershipTypeCode">Ownership type:</label>
                        <form:select style="width:175px" class="form-control" path="ownershipTypeCode">
                            <form:option value="" label="None" />
                            <form:options items="${ownershipValues}" />
                        </form:select><br />
                    </div>
                    <form:hidden path="titleHoldingCongregationId" />
                    <label for="titleHoldingCongregationName">Titleholder congregation:</label>
                    <form:input path="titleHoldingCongregationName" class="typeahead form-control" type="text" name="titleHoldingCongregationName" placeholder="Title Holder" maxlength="50" /><br />
                    <br />
                    <input type="submit" class="btn btn-edifice"/>
                </div>
            </div>
        </form:form>

        <ol class="breadcrumb">
            <li><a href="<c:url value="/" />">Edifice</a></li>
            <li role="menuitem"><a href="<c:url value="/kingdom-halls" />">Kingdom halls</a></li>
            <li class="active">Edit</li>
        </ol>

        <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        <script type="text/javascript" src="<c:url value='/javascript/kingdom-halls.js' />" ></script>
    </body>
</html>
