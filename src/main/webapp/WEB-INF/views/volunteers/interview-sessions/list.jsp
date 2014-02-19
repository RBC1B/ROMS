<%--
    List the volunteer interview sessions.
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="pageTitle">Volunteer Interview Sessions</c:set>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <h1>Volunteer Interview Sessions</h1>
        <hr />
        <div class="entity-list-results">
            <table class="table table-bordered table-condensed table-striped table-hover" id="volunteer-interview-session-list">
                <thead>
                    <tr>
                        <th>Date</th>
                        <th>Kingdom Hall</th>
                        <th>Invited</th>
                        <th>Confirmed</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${interviewSessions}" var="interviewSession">
                        <tr>
                            <td><fmt:formatDate value="${interviewSession.date}" pattern="yyyy-MM-dd" /></td>
                            <td>
                                <c:if test="!empty ${interviewSession.kingdomHall}">
                                    <a href="<c:url value='${interviewSession.kingdomHall.uri}' />"><c:out value="${interviewSession.kingdomHall.name}" /></a>
                                </c:if>
                            </td>
                            <td>${interviewSession.invitedVolunteerCount}</td>
                            <td>${interviewSession.confirmedVolunteerCount}</td>
                            <td>
                                <ul class="list-inline">
                                    <li><a class="btn btn-success" href="<c:url value="${interviewSession.uri}" />">View</a></li>
                                </ul>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <sec:authorize access="hasPermission('VOLUNTEER', 'EDIT')">
            <hr />
            <div class="entity-list-add-new">
                <a class="btn btn-edifice" href="<c:url value="${newUri}" />">Create new session</a>
            </div>
        </sec:authorize>
        <br />
        <ol class="breadcrumb">
            <li><a href="<c:url value="/" />">Edifice</a></li>
            <li class="active">Volunteer Interview Sessions</li>
        </ol>
        <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        <script type="text/javascript" src="<c:url value='/javascript/volunteers.js' />" ></script>
    </body>
</html>
