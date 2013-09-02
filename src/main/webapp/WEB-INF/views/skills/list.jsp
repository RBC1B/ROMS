<%--
    Document   : skills
    Created on : 25 Jan 2013
    Author     : ramindur.singh
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:url var="formAction" value="/skills" />
<html>
    <c:set var="pageTitle" value="Departmental Skills" />
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <div class="container-fluid">
            <h1>Departmental Skills</h1>
            <hr>
            <div class="entity-list-results">
                <table class="table table-bordered table-condensed table-striped table-hover" id="skill-list">
                    <thead>
                        <tr>
                            <th>Skill ID</th>
                            <th>Skill Name</th>
                            <th>Department</th>
                            <th>Description</th>
                            <th>Category</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${skills}" var="skill">
                            <tr>
                                <td>${skill.skillId}</td>
                                <td>${skill.name}</td>
                                <td><a href="${skill.department.uri}">${skill.department.name}</a></td>
                                <td>${skill.description}</td>
                                <td>${skill.category.name}</td>
                                <td>
                                    <ul class="inline list-actions">
                                        <li><a class="btn btn-success" href="<c:url value="${skill.uri}" />">View</a></li>
                                        <li><a class="list-action" href="<c:url value="/skills/${skill.skillId}/edit" />">Edit</a></li>
                                        <li>
                                            <form:form method="DELETE" action="${formAction}">
                                                <input type="hidden" name="skillId" value="${skill.skillId}" />
                                                <input type="submit" value="Delete" class="btn btn-mini" />
                                            </form:form>
                                        </li>
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
                <li><a href="<c:url value="/" />">Edifice</a> <span class="divider">/</span></li>
                <li class="active">Skills</li>
            </ul>
            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        </div>
        <script type="text/javascript" src="<c:url value='/javascript/skills.js' />" ></script>
    </body>
</html>
