<%--
    Author     : Oliver
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="pageTitle">Projects</c:set>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <h1>Projects</h1>
        <hr />
        <table class="table table-bordered table-condensed table-striped table-hover" id="project-list">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Type</th>
                    <th>Status</th>
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
                        <td><c:out value="${project.name}" /></td>
                        <td>${project.type}</td>
                        <td>${project.status}</td>
                        <td><fmt:formatDate value="${project.requestDate}" pattern="yyyy-MM-dd" /></td>
                        <td><fmt:formatDate value="${project.completedDate}" pattern="yyyy-MM-dd" /></td>
                        <td><c:if test="${not empty project.contactPerson}"><a href="<c:url value='${project.contactPerson.uri}'/>"><c:out value="${project.contactPerson.name}" /></a></c:if></td>
                        <td>
                            <ul class="list-inline">
                                <li><a class="btn btn-success" href="<c:url value="${project.uri}" />">View</a></li>
                            </ul>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <sec:authorize access="hasPermission('PROJECT', 'ADD')">
            <hr />
            <a class="btn btn-edifice" href="<c:url value="${newUri}" />" />Create new project</a>
        </sec:authorize>
        <br />
        <ol class="breadcrumb">
            <li><a href="<c:url value="/" />">Edifice</a></li>
            <li class="active">Projects</li>
        </ol>
        <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        <script type="text/javascript" src="<c:url value='/javascript/projects.js' />" ></script>
    </body>
</html>
