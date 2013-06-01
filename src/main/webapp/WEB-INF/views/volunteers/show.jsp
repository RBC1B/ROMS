<%--
    Author     : rahulsingh
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="pageTitle">Volunteer #${volunteer.id}: ${volunteer.forename} ${volunteer.surname}</c:set>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <div class="container-fluid">
            <div class="media">
                <img src="<c:url value='/images/oli-lion.jpg' />" class="media-object img-polaroid pull-left" />
                <div class="media-body">
                    <h1 class="media-heading">#${volunteer.id}: ${volunteer.forename} ${volunteer.middleName} ${volunteer.surname}</h1>
                    <dl class="dl-horizontal">
                        <dt>Status:</dt><dd>${volunteer.status}</dd>
                        <dt>Comments:</dt><dd>${volunteer.comments}</dd>
                    </dl>
                </div>
            </div>
            <div class="clearfix">
                <c:choose>
                    <c:when test="${volunteer.assignments != null}">
                        <h3>Assignments</h3>
                        <table class="table table-bordered table-striped table-hover">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Department</th>
                                    <th>Team</th>
                                    <th>Role</th>
                                    <th>Assigned</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${volunteer.assignments}" var="assignment">
                                    <tr>
                                        <td>${assignment.tradeNumber}</td>
                                        <td><a href="${assignment.department.uri}">${assignment.department.name}</a></td>
                                        <td><a href="${assignment.team.uri}">${assignment.team.name}</a></td>
                                        <td>${assignment.role}</td>
                                        <td><fmt:formatDate value="${assignment.assignedDate}" pattern="dd MMM yyyy" /></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <br />
                        <div class="alert alert-block">Volunteer is not assigned to any teams</div>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="clearfix"></div>
            <br />
            <ul class="nav nav-tabs">
                <li class="active"><a href="#personal" data-toggle="tab">Personal</a></li>
                <li><a href="#spiritual" data-toggle="tab">Spiritual</a></li>
                <li><a href="#skills" data-toggle="tab">Skills</a></li>
                <li><a href="#rbc-status" data-toggle="tab">RBC Status</a></li>
            </ul>
            <div class="tab-content">
                <div class="tab-pane active" id="personal">
                    <%@ include file="fragments/show-personal.jsp" %>
                </div>
                <div class="tab-pane" id="spiritual">
                    <%@ include file="fragments/show-spiritual.jsp" %>
                </div>
                <div class="tab-pane" id="skills">
                    <%@ include file="fragments/show-skills.jsp" %>
                </div>
                <div class="tab-pane" id="rbc-status">
                    <%@ include file="fragments/show-rbc-status.jsp" %>
                </div>
            </div>
            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        </div>
        <script type="text/javascript" charset="utf8" src="<c:url value='/javascript/volunteers.js' />" ></script>
    </body>
</html>