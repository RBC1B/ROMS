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
        <dl class="dl-horizontal">
            <dt>Kingdom Hall</dt>
            <dd>
                <a href="<c:url value="${project.kingdomHall.uri}" />">
                    <c:out value="${project.kingdomHall.name}" />
                </a>
            </dd>
            <dt>Request Date</dt><dd>${project.requestDate}</dd>
            <dt>Completed Date:</dt><dd>${project.completedDate}</dd>
            <dt>Minor Work</dt><dd>${project.minorWork}</dd>
            <dt>Coordinator</dt>
            <dd>
                <a href="<c:url value="${project.coordinator.uri}" />">
                    <c:out value="${project.coordinator.name}" />
                </a>
            </dd>
        </dl>
        <sec:authorize access="hasPermission('PROJECT', 'EDIT')">
            <hr />
            <a href="<c:url value='${project.editUri}' />" class="btn btn-edifice">Edit Project</a>
        </sec:authorize>
        <div class="clearfix"></div>
        <br />
        <ol class="breadcrumb">
            <li><a href="<c:url value="/" />">Edifice</a></li>
            <li><a href="<c:url value="/projects" />">Projects</a></li>
            <li>#${project.projectId}: <c:out value="${project.name}" /></li>
        </ol>
        <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        <script type="text/javascript" src="<c:url value='/javascript/projects.js' />" ></script>
    </body>
</html>
