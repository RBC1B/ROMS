<%--
    Display the skill details.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<c:set var="pageTitle" value="Skill: ${skill.name}" />
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<body>
    <%@ include file="/WEB-INF/views/common/titlebar.jsp"%>
    <div class="container-fluid">
        <h1>Skill: ${skill.name}</h1>
        <hr>
        <dl class="dl-horizontal">
            <dt>Description:</dt>
            <dd>
                <c:choose>
                    <c:when test="${!empty skill.description}">${skill.description}</c:when>
                    <c:otherwise>-</c:otherwise>
                </c:choose>
            </dd>
            <dt>Department</dt>
            <dd>
                <a href="<c:url value='${skill.department.uri}' />">${skill.department.name}</a>
            </dd>
            <dt>Category:</dt>
            <dd>
                <c:choose>
                    <c:when test="${!empty skill.category}">
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
                    </c:when>
                    <c:otherwise>-</c:otherwise>
                </c:choose>
            </dd>
            <dt>Appear on badge?:</dt>
            <dd>
                <c:choose>
                    <c:when test="${!empty skill.category && skill.category.appearOnBadge}">
                        <span class="icon-ok"></span>
                    </c:when>
                    <c:otherwise>
                        <span class="icon-remove"></span>
                    </c:otherwise>
                </c:choose>
            </dd>
        </dl>
        <sec:authorize access="hasPermission('SKILL', 'EDIT')">
            <a href="<c:url value='${skill.editUri}' />" class="btn btn-primary">Edit Skill</a>
        </sec:authorize>
        <div class="clearfix"></div>
        <br />
        <ul class="nav nav-tabs">
            <li class="active"><a href="#volunteers" data-toggle="tab">Volunteers</a></li>
        </ul>
        <div class="tab-content">
            <div class="tab-pane active" id="volunteers">
                <div class="row-fluid">
                    <div class="entity-list-results">
                        <table class="table table-bordered table-condensed table-striped table-hover" id="skills-volunteer-list" data-skill-id="${skill.skillId}">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>First Name</th>
                                    <th>Last Name</th>
                                    <th>Congregation</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                            </tbody>
                            <tfoot>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>

            <ol class="breadcrumb">
                <li><a href="<c:url value="/" />">Edifice</a></li>
                <sec:authorize access="hasPermission('SKILL', 'READ')">
                  <li><a href="<c:url value="/skills" />">Skills</a></li>
                </sec:authorize>
                <li>${skill.name}</li>
            </ol>
                            
        <%@ include file="/WEB-INF/views/common/footer.jsp"%>
    </div>
    <%@ include file="/WEB-INF/views/common/mustache-list-actions.jsp" %>
    <script type="text/javascript" src="<c:url value='/javascript/skills.js' />"></script>
</body>
</html>
