<%--
    Display the department details.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="pageTitle" value="Department: ${department.name}" />
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
            <h1>Department: ${department.name}</h1>
            <hr />
            <dl class="dl-horizontal">
                <dt>Super Department:</dt>
                <dd>
                    <c:choose>
                        <c:when test="${!empty department.superDepartment}">
                            <a href="<c:url value='${department.superDepartment.uri}' />">${department.superDepartment.name}</a>
                        </c:when>
                        <c:otherwise>-</c:otherwise>
                    </c:choose>
                </dd>
                <dt>Description:</dt>
                <dd>
                    <c:choose>
                        <c:when test="${!empty department.description}">${department.description}</c:when>
                        <c:otherwise>-</c:otherwise>
                    </c:choose>
                </dd>
            </dl>
            <div class="clearfix"></div>
            <br />
            <ul class="nav nav-tabs">
                <li class="active"><a href="#overseers" data-toggle="tab">Overseers</a></li>
                <li><a href="#assignments" data-toggle="tab">Assignments</a></li>
                <c:if test="${!empty childDepartments}"><li><a href="#departments" data-toggle="tab">Departments</a></li></c:if>
                <li><a href="#skills" data-toggle="tab">Skills</a></li>
            </ul>
            <div class="tab-content">
                <div class="tab-pane active" id="overseers">
                    <div class="row-fluid">
                        <%@ include file="fragments/show-department-overseers.jsp" %>
                    </div>
                </div>
                <div class="tab-pane" id="assignments">
                    <div class="row-fluid">
                        <%@ include file="fragments/show-assignments.jsp" %>
                    </div>
                </div>
                <c:if test="${!empty childDepartments}">
                    <div class="tab-pane" id="departments">
                        <div class="row-fluid">
                            <%@ include file="fragments/show-child-departments.jsp" %>
                        </div>
                    </div>
                </c:if>
                <div class="tab-pane" id="skills">
                    <div class="row-fluid">
                        <%@ include file="fragments/show-skills.jsp" %>
                    </div>
                </div>
            </div>
            <sec:authorize access="hasPermission('PROJECT', 'EDIT')">
                <a href="<c:url value='${department.editUri}' />" class="btn btn-edifice">Edit Department</a>
            </sec:authorize>
            <div class="clearfix"></div>

            <ol class="breadcrumb">
                <li><a href="<c:url value="/" />">Edifice</a></li>
                <li><a href="<c:url value="/department" />">Department</a></li>
                <li class="active">${department.name}</li>
            </ol>

            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        <%@ include file="/WEB-INF/views/common/mustache-list-actions.jsp" %>
        <script type="text/javascript" src="<c:url value='/javascript/departments.js' />" ></script>
    </body>
</html>
