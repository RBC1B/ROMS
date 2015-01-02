<%--
    Show the project details.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="pageTitle">Project #${project.projectId} - ${project.name}</c:set>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <h1>Project #${project.projectId}: <c:out value="${project.name}" /></h1>
        <hr />
        <div role="tabpanel">
            <!-- Tabs -->
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class="active">
                    <a href="#project" aria-controls="project" role="tab"
                       data-toggle="tab">Project Information</a>
                </li>
                <li role="presentation">
                    <a href="#invitation" aria-controls="invitation" role="tab"
                       data-toggle="tab">Invitation & Summary</a>
                </li>
                <li role="presentation">
                    <a href="#attendance" aria-controls="attendance" role="tab"
                       data-toggle="tab">Attendance</a>
                </li>
                <li role="presentation">
                    <a href="#gatelist" aria-controls="gatelist" role="tab"
                       data-toggle="tab">Gate List</a>
                </li>
            </ul>

            <!-- Tab contents -->
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="project">
                    <%@ include file="fragment/project-show.jsp" %>
                </div>

                <div role="tabpanel" class="tab-pane" id="invitation">
                    <%@ include file="fragment/project-invitation.jsp" %>
                </div>
                <div role="tabpanel" class="tab-pane" id="attendance">
                    <%@ include file="fragment/project-attendance.jsp" %>
                </div>
                <div role="tabpanel" class="tab-pane" id="gatelist">
                    <%@ include file="fragment/project-gatelist.jsp" %>
                </div>
            </div>
        </div>
        <div id="project-permissions" attendance="${application.nonDepartmentLevelAccess}"/>
        <div class="clearfix"></div>
        <br />
        <ol class="breadcrumb">
            <li><a href="<c:url value="/" />">Edifice</a></li>
            <li><a href="<c:url value="/projects" />">Projects</a></li>
            <li>#${project.projectId}: <c:out value="${project.name}" /></li>
        </ol>
        <div id="project-id" project-id="${project.projectId}" />
        <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        <script type="text/javascript" src="<c:url value='/javascript/projects.js' />" ></script>
    </body>
</html>
