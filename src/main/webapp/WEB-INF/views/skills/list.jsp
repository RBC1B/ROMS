<%--
    Document   : skillsList
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
            <h1>Skills</h1>
            <hr>
            <div class="entity-list-results">
                <table class="table table-bordered table-condensed table-striped table-hover" id="skill-list">
                    <thead>
                        <tr>
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
                                <td>${skill.name}</td>
                                <td><a href="<c:url value='${skill.department.uri}' />">${skill.department.name}</a></td>
                                <td>${skill.description}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${!empty skill.category.colour}">
                                            <c:set var="categoryColour" value="${skill.category.colour}" />
                                        </c:when>
                                        <c:otherwise>
                                            <c:set var="categoryColour" value="white" />
                                        </c:otherwise>
                                    </c:choose>
                                    <div class="skill-category-colour" style="background-color:${categoryColour}"></div>
                                    ${skill.category.name}
                                </td>
                                <td>
                                    <ul class="inline list-actions">
                                        <li><a class="btn btn-success" href="<c:url value="${skill.uri}" />">View</a></li>
                                        <li><a class="list-action" href="<c:url value="${skill.editUri}" />">Edit</a></li>
                                    </ul>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <sec:authorize access="hasPermission('SKILL', 'ADD')">
                <div class="entity-list-add-new">
                    <a class="btn btn-edifice" href="<c:url value="${newUri}" />">Create new departmental skills</a>
                </div>
            </sec:authorize>
            <p>&nbsp;</p>
            <ol class="breadcrumb">
                <li><a href="<c:url value="/" />">Edifice</a></li>
                <li class="active">Skills</li>
            </ol>
            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        <script type="text/javascript" src="<c:url value='/javascript/skills.js' />" ></script>
    </body>
</html>
