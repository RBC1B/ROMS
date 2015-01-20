<%--
   Create or edit a qualification
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:choose>
        <c:when test="${!empty qualificationForm.name}">
            <c:set var="pageTitle">Edit qualification</c:set>
        </c:when>
        <c:otherwise>
            <c:set var="pageTitle">Create new qualification</c:set>
        </c:otherwise>
    </c:choose>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <c:choose>
            <c:when test="${!empty qualificationForm.name}">
                <h1>Edit qualification</h1>
            </c:when>
            <c:otherwise>
                <h1>Create new qualification</h1>
            </c:otherwise>
        </c:choose>
        <hr />
        <c:url var="formAction" value="${submitUri}" />
        <form:form class="form-horizontal" commandName="qualificationForm" method="${submitMethod}" action="${formAction}">
            <fieldset>
                <div class="form-group">
                    <label for="name" class="control-label col-sm-3 col-md-2">Name</label>
                    <div class="col-sm-9 col-md-3">
                        <c:choose>
                            <c:when test="${!empty qualificationForm.name}">
                                <form:input path="name" class="form-control" maxlength="50" readonly="true" />
                            </c:when>
                            <c:otherwise>
                                <%--<form:input path="name" class="form-control" maxlength="50" />--%>
                                <form:select class="form-control" path="name">
                                    <form:option value="" label="None" />
                                    <form:options items="${ownershipValues}" />
                                </form:select>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <div class="form-group">
                    <label for="description" class="control-label col-sm-3 col-md-2">Description</label>
                    <div class="col-sm-9 col-md-3">
                        <form:textarea path="description" class="form-control" rows="4" cols="50" maxlength="100" />
                    </div>
                    <div class="col-sm-3 col-md-3">
                         <span class="glyphicon glyphicon-question-sign" aria-hidden="true" data-toggle="tooltip" data-placement="right" title="Include house number or house name"></span>
                    </div>
                </div>
            </fieldset>
            <fieldset>
                <div class="form-group">
                    <label class="control-label col-sm-3 col-md-2"></label>
                    <div class="col-sm-9 col-md-3">
                        <button type="submit" class="btn btn-default btn-success">Submit</button>
                    </div>
                </div>
            </fieldset>
        </form:form>

        <ol class="breadcrumb">
            <li><a href="<c:url value="/" />">Edifice</a></li>
            <li role="menuitem"><a href="<c:url value="/qualifications" />">Qualifications</a></li>
            <li class="active">Edit</li>
        </ol>

        <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        <script type="text/javascript" src="<c:url value='/javascript/qualifications.js' />" ></script>
    </body>
</html>
