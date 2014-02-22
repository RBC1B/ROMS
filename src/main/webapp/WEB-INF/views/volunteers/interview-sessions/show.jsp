<%--
    Display the volunteer interview session details.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="pageTitle">Interviews
        <fmt:formatDate value="${interviewSession.date}" pattern="yyyy-MM-dd" />
        <c:if test="${!empty interviewSession.kingdomHall}"> - <c:out value="${interviewSession.kingdomHall.name}" /></c:if>
    </c:set>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <h1><c:out value="${pageTitle}" /></h1>
        <hr />
        <dl class="dl-horizontal">
            <dt>Time:</dt>
            <dd>${interviewSession.formatTime()}</dd>
            <dt>Kingdom hall:</dt>
            <dd>
                <c:choose>
                    <c:when test="${!empty interviewSession.kingdomHall}">
                        <a href="<c:url value='${interviewSession.kingdomHall.uri}' />"><c:out value="${interviewSession.kingdomHall.name}" /></a>
                    </c:when>
                    <c:otherwise>-</c:otherwise>
                </c:choose>
            </dd>
            <dt>Notes:</dt>
            <dd>
                <c:choose>
                    <c:when test="${!empty interviewSession.comments}">
                        ${interviewSession.comments}
                    </c:when>
                    <c:otherwise>-</c:otherwise>
                </c:choose>
            </dd>
        </dl>
        <div class="clearfix"></div>
        <sec:authorize access="hasPermission('VOLUNTEER', 'EDIT')">
            <a href="<c:url value='${interviewSession.editUri}' />" class="btn btn-edifice">Edit Interview Session</a>
            <div class="clearfix"></div>
        </sec:authorize>
        <br />
        <ul class="nav nav-tabs">
            <li class="active"><a href="#volunteers" data-toggle="tab">Volunteers</a></li>
        </ul>
        <div class="tab-content">
            <div class="tab-pane active" id="volunteers">
                <div class="row-fluid">
                    <div class="entity-list-results">
                        <table class="table table-bordered table-condensed table-striped table-hover" id="session-volunteer-list">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Forename</th>
                                    <th>Surname</th>
                                    <th>Congregation</th>
                                    <th>Region</th>
                                    <th>Comments</th>
                                    <th>Status</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${volunteers}" var="volunteer">
                                    <tr>
                                        <td>${volunteer.id}</td>
                                        <td><c:out value="${volunteer.forename}" /></td>
                                        <td><c:out value="${volunteer.surname}" /></td>
                                        <td>
                                            <c:if test="${!empty volunteer.congregation}">
                                                <a href="<c:url value='${volunteer.congregation.uri}' />"><c:out value="${volunteer.congregation.name}" /></a>
                                            </c:if>
                                        </td>
                                        <td><c:out value="${volunteer.rbcSubRegion}" /></td>
                                        <td><c:out value="${volunteer.comments}" /></td>
                                        <td><c:out value="${volunteer.interviewStatus}" /></td>
                                        <td>
                                            <ul class="list-inline">
                                                <li><a class="btn btn-success" href="<c:url value="${volunteer.uri}" />">View</a></li>
                                            </ul>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                <sec:authorize access="hasPermission('VOLUNTEER', 'EDIT')">
                    <hr />
                    <c:if test="${!empty interviewSession.invitationsUri}">
                        <a href="<c:url value='${interviewSession.invitationsUri}' />" class="btn btn-edifice">Invite Volunteers</a>
                    </c:if>
                </sec:authorize>
            </div>
        </div>

        <br />
        <ol class="breadcrumb">
            <li><a href="<c:url value="/" />">Edifice</a></li>
            <li><a href="<c:url value="${listUri}" />">Interview Sessions</a></li>
            <li class="active"><c:out value="${pageTitle}" /></li>
        </ol>

        <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        <script type="text/javascript" src="<c:url value='/javascript/volunteers.js' />" ></script>
    </body>
</html>

