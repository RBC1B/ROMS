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
        <br />
        <ul class="nav nav-tabs">
            <li class="active"><a href="#volunteers" data-toggle="tab">Volunteers</a></li>
        </ul>
        <div class="tab-content">
            <div class="tab-pane active" id="volunteers">
                <div class="row-fluid">
                </div>
            </div>
        </div>
        <sec:authorize access="hasPermission('VOLUNTEER', 'EDIT')">
            <hr />
            <a href="<c:url value='${interviewSession.editUri}' />" class="btn btn-edifice">Edit Interview Session</a>
        </sec:authorize>

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

