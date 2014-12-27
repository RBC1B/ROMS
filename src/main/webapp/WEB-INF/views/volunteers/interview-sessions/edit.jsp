<%--
    Form use to edit or create interview sessions.
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<c:choose>
    <c:when test="${!empty interviewSessionForm.date}">
        <c:set var="pageTitle">Edit interview session</c:set>
    </c:when>
    <c:otherwise>
        <c:set var="pageTitle">Create new interview session</c:set>
    </c:otherwise>
</c:choose>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<body>
    <%@ include file="/WEB-INF/views/common/titlebar.jsp"%>
    <h1>${pageTitle}</h1>
    <hr />
    <c:url var="formAction" value="${submitUri}" />
    <form:form class="form-horizontal" commandName="interviewSessionForm" method="${submitMethod}" action="${formAction}">
        <fieldset>
            <div class="form-group">
                <label class="control-label col-sm-3 col-md-2" for="name">Date</label>
                <div class="col-sm-9 col-md-3">
                    <form:input path="date" placeholder="dd/mm/yyyy" class="datetimepicker-dateonly form-control" type="text" value=""/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3 col-md-2" for="time">Time</label>
                <div class="col-sm-9 col-md-3">
                    <form:input path="time" class="datetimepicker-timeonly form-control" placeholder="hh:mm" maxlength="5" />
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3 col-md-2" for="time">Kingdom Hall</label>
                <div class="col-sm-9 col-md-3">
                    <form:input path="kingdomHallName" class="form-control" placeholder="Kingdom Hall name" autocomplete="off" />
                    <form:hidden path="kingdomHallId" />
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3 col-md-2" for="comments">Comments</label>
                <div class="col-sm-9 col-md-3">
                    <form:textarea path="comments" class="form-control" rows="4" />
                </div>
                <div class=”col-sm-3 col-md-3”>
                    <span class="glyphicon glyphicon-question-sign" aria-hidden="true" data-toggle="tooltip" data-placement="right" title=""></span>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3 col-md-2"></label>
                <div class="col-sm-9 col-md-3">
                    <input type="submit" class="btn btn-edifice" />
                </div>
            </div>
        </fieldset>
    </form:form>

    <ol class="breadcrumb">
        <li><a href="<c:url value="/" />">Edifice</a></li>
        <li><a href="<c:url value="${listUri}" />">Interview sessions</a></li>
        <li class="active">${pageTitle}</li>
    </ol>
    <%@ include file="/WEB-INF/views/common/footer.jsp"%>
    <script type="text/javascript" src="<c:url value='/javascript/thirdparty/jquery-numeric-1.3.1.js' />" ></script>
    <script type="text/javascript" src="<c:url value='/javascript/volunteers.js' />"></script>
</body>
</html>
