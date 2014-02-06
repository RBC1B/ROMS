<%--
   Create a new project
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="pageTitle">Create new project</c:set>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <h1>Create new project</h1>
        <hr />
        <c:url var="formAction" value="${submitUri}" />
        <form:form class="form-horizontal" commandName="projectForm" method="${submitMethod}" action="${formAction}">
            <fieldset>
                <div class="form-group">
                    <label for="name" class="control-label col-sm-3 col-md-2">Name</label>
                    <div class="col-sm-9 col-md-3">
                        <form:input path="name" class="form-control" maxlength="50" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="projectTypeId" class="control-label col-sm-3 col-md-2">Type</label>
                    <div class="col-sm-9 col-md-2">
                        <form:select path="projectTypeId" class="form-control">
                            <form:option value="" />
                            <form:options items="${projectTypeValues}" />
                        </form:select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="coordinatorUserName" class="control-label col-sm-3 col-md-2">Coordinator</label>
                    <div class="col-sm-9 col-md-3">
                        <form:hidden path="coordinatorUserId" />
                        <form:input path="coordinatorUserName" class="form-control" maxlength="50" autocomplete="off" />
                    </div>
                </div>
            </fieldset>
            <fieldset>
                <div class="form-group">
                    <label for="kingdomHallName" class="control-label col-sm-3 col-md-2">Kingdom Hall</label>
                    <div class="col-sm-9 col-md-3">
                        <form:hidden path="kingdomHallId" />
                        <form:input path="kingdomHallName" class="form-control" maxlength="50" autocomplete="off" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="supportingCongregation" class="control-label col-sm-3 col-md-2">Supporting congregation</label>
                    <div class="col-sm-9 col-md-3">
                        <form:input path="supportingCongregation" class="form-control" maxlength="250" />
                    </div>
                </div>
            </fieldset>
            <fieldset>
                <div class="form-group">
                    <label for="requestDate" class="control-label col-sm-3 col-md-2">Request date</label>
                    <div class="col-sm-4 col-md-2">
                        <form:input path="requestDate" placeholder="dd/mm/yyyy" class="datepicker form-control" data-date-format="dd/mm/yy" type="text" value=""/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="visitDate" class="control-label col-sm-3 col-md-2">Visit Date</label>
                    <div class="col-sm-4 col-md-2">
                        <form:input path="visitDate" placeholder="dd/mm/yyyy" class="datepicker form-control" data-date-format="dd/mm/yy" type="text" value=""/>
                    </div>
                </div>
            </fieldset>
            <fieldset>
                <div class="form-group">
                    <label for="priority" class="control-label col-sm-3 col-md-2">Priority</label>
                    <div class="col-sm-9 col-md-3">
                        <form:input path="priority" class="form-control" maxlength="50" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="estimateCost" class="control-label col-sm-3 col-md-2">Estimated cost</label>
                    <div class="col-sm-9 col-md-3">
                        <form:input path="estimateCost" class="form-control" maxlength="50" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="estimateCost" class="control-label col-sm-3 col-md-2">Constraints</label>
                    <div class="col-sm-9 col-md-3">
                        <form:textarea path="constraints" class="form-control" rows="4" cols="50" />
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
            <li role="menuitem"><a href="<c:url value="/projects" />">Projects</a></li>
            <li class="active">Create</li>
        </ol>
        <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        <script type="text/javascript" src="<c:url value='/javascript/projects.js' />" ></script>
    </body>
</html>
