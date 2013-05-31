<%--
    Document   : skills
    Created on : 25 Jan 2013
    Author     : ramindur.singh
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="pageTitle" value="Departmental Skills" />
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <div class="container-fluid">
            <h1>Departmental Skills</h1>
            <br>
            <div class="entity-list-results">
                <table class="table table-bordered table-striped table-hover" id="skill-list">
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Department</th>
                            <th>Description</th>
                            <th>Appear On Badge</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${skills}" var="skill">
                            <tr>
                                <td>${skill.name}</td>
                                <td><a href="${skill.department.uri}">${skill.department.name}</a></td>
                                <td>${skill.description}</td>
                                <td>${skill.appearOnBadge}</td>
                                <td>
                                    <ul class="inline list-actions">
                                        <li><a class="btn btn-success" href="<c:url value="${skill.uri}" />">View</a></li>
                                        <li><a class="list-action" href="<c:url value="/skills/${skill.id}/edit" />">Edit</a></li>
                                        <li><a class="list-action" href="delete">Delete</a></li>
                                    </ul>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="entity-list-add-new">
                <a class="btn btn-primary" href="<c:url value="/skills/new" />">Create new departmental skills</a>
            </div>
            <p>&nbsp;</p>
            <ul class="breadcrumb">
                <li><a href="<c:url value="/" />">ROMS</a> <span class="divider">/</span></li>
                <li class="active">Skills</li>
            </ul>
            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        </div>
        <script type="text/javascript" charset="utf-8" src="<c:url value='/javascript/skills.js' />" ></script>
    </body>
</html>
