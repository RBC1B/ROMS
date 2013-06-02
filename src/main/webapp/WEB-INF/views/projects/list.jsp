<%--
    Author     : Oliver
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="pageTitle" value="Projects" />
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <div class="container-fluid">
            <h1>Projects</h1>
            <br>
            <table class="table table-bordered table-striped table-hover" id="project-list">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Type</th>
                        <th>Status</th>
                        <th>Stage</th>
                        <th>Requested</th>
                        <th>Completed</th>
                        <th>Contact</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${projects}" var="project">
                        <tr>
                            <td>${project.projectId}</td>
                            <td>${project.name}</td>
                            <td>${project.type}</td>
                            <td>${project.status}</td>
                            <td><span class="a-project-status" data-original-title="${project.stage.description} - ${project.stage.assignedTo}">${project.stage.name}</span></td>
                            <td><fmt:formatDate value="${project.requestDate}" pattern="yyyy-MM-dd" /></td>
                            <td><fmt:formatDate value="${project.completedDate}" pattern="yyyy-MM-dd" /></td>
                            <td><c:if test="${not empty project.contactPerson}"><a href="<c:url value='${project.contactPerson.uri}'/>">${project.contactPerson.name}</a></c:if></td>
                            <td>
                                <ul class="inline list-actions">
                                    <li><a class="btn btn-success" href="<c:url value="${project.uri}" />">View</a></li>
                                </ul>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <p>&nbsp;</p>
            <ul class="breadcrumb">
                <li><a href="<c:url value="/" />">ROMS</a> <span class="divider">/</span></li>
                <li class="active">Projects</li>
            </ul>
            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
            <script type="text/javascript" src="<c:url value='/javascript/projects.js' />" ></script>
        </div>
    </body>
</html>
