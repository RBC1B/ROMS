<%--
   Edit an existing project
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:choose>
        <c:when test="${empty projectForm.name}">
            <c:set var="pageTitle">Create new project</c:set>
        </c:when>
        <c:otherwise>
            <c:set var="pageTitle">Edit project</c:set>
        </c:otherwise>
    </c:choose>


    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <c:choose>
            <c:when test="${empty projectForm.name}">
                <div id="create-project" val="1"/>
                <h1>Create new project</h1>
            </c:when>
            <c:otherwise>
                <div id="create-project" val="0"/>
                <h1>Edit project (${projectForm.projectId})</h1>
            </c:otherwise>
        </c:choose>
        <hr />
        <c:url var="formAction" value="${submitUri}" />
        <form:form class="form-horizontal" commandName="projectForm" method="${submitMethod}" action="${formAction}"
                   data-project-id="${projectId}">
            <div class="form-group">
                <form:hidden class="form-control" path="projectId" />
                <label for="name" class="control-label col-sm-2">Project Name</label>
                <div class="col-sm-4">
                    <form:input path="name" class="form-control" maxlength="250" />
                </div>
            </div>
            <div class="form-group">
                <form:hidden path="kingdomHallId" />
                <label class="control-label col-sm-3 col-md-2" for="kingdomHallName">Kingdom Hall</label>
                <div class="col-sm-9 col-md-3">
                    <form:input path="kingdomHallName" class="typeahead form-control" type="text" name="kingdomHallName" placeholder="Name of the kingdom hall" maxlength="50" />
                </div>
            </div>
            <div class="form-group">
                <label for="requestDate" class="control-label col-sm-2">Request date</label>
                <div class="col-sm-2">
                    <form:input path="requestDate" placeholder="dd/mm/yyyy" class="datepicker form-control" data-date-format="dd/mm/yy" type="text" />
                </div>
            </div>
            <div class="form-group">
                <label for="completedDate" class="control-label col-sm-2">Completion date</label>
                <div class="col-sm-2">
                    <form:input path="completedDate" placeholder="dd/mm/yyyy" class="datepicker form-control" data-date-format="dd/mm/yy" type="text" />
                </div>
            </div>
            <div class="form-group">
                <div class="control-label col-sm-3 col-md-2">
                    <b>Coordinator</b>
                </div>
                <div class="col-sm-2">
                    <form:hidden class="form-control" path="coordinatorId" />
                    <div class="form-group">
                        <form:input class="form-control" path="forename" maxlength="50" placeholder="First Name"/>
                    </div>
                </div>
                <div class="col-sm-2">
                    <div class="form-group">
                        <form:input class="form-control" path="surname" maxlength="50" placeholder="Surname"/>
                    </div>
                </div>
            </div>
            <div id="coordinator-linked" class="alert alert-warning alert-dismissable" style="display:none;">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">Unlink</button>
                Linked to an existing person in the database
            </div>
            <div class="form-group">
                <label for="minorWork" class="control-label col-sm-2">Minor Work</label>
                <div class="col-sm-2">
                    <form:checkbox path="minorWork"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-3">
                    <button type="submit" class="btn btn-default btn-success">Submit</button>
                </div>
            </div>
        </form:form>
        <ol class="breadcrumb">
            <li><a href="<c:url value="/" />">Edifice</a></li>
            <li role="menuitem"><a href="<c:url value="/projects" />">Projects</a></li>
            <li class="active">Edit</li>
        </ol>
        <%@include file="/WEB-INF/views/common/person-link-modal.jsp" %>
        <%@include file="/WEB-INF/views/projects/mustache-coordinator-link-search-form.jsp" %>
        <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        <script type="text/javascript" src="<c:url value='/javascript/projects.js' />" ></script>
    </body>
</html>
