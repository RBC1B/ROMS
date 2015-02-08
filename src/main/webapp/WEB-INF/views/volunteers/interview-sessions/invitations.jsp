<%--
    Display the volunteer interview session invitation form.
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
            <dt>Already invited:</dt>
            <dd>${interviewSession.getAttendingVolunteerCount()} (${interviewSession.declinedVolunteerCount} declined)</dd>
        </dl>
        <div class="row-fluid">
            <div class="pull-left">
                <label>
                    <input type="checkbox" name="inviteAll" class="a-invite-all"> Invite all
                </label>
            </div>
            <div class="entity-list-results">
                <table class="table table-bordered table-condensed table-striped table-hover" id="session-invitation-list">
                    <thead>
                        <tr>
                            <th>Invite</th>
                            <th>Name</th>
                            <th>Congregation</th>
                            <th>Region</th>
                            <th>Comments</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${volunteers}" var="volunteer">
                            <tr>
                                <td>
                                    <input class="a-invite" type="checkbox" data-volunteer-id="${volunteer.id}" />
                                </td>
                                <td>
                                    <a href="<c:url value="${volunteer.uri}" />">
                                        <c:out value="${volunteer.surname}" />, <c:out value="${volunteer.forename}" />
                                    </a>
                                </td>
                                <td>
                                    <c:if test="${!empty volunteer.congregation}">
                                        <a href="<c:url value='${volunteer.congregation.uri}' />"><c:out value="${volunteer.congregation.name}" /></a>
                                    </c:if>
                                </td>
                                <td><c:out value="${volunteer.rbcSubRegion}" /></td>
                                <td><c:out value="${volunteer.comments}" /></td>
                                <td><c:out value="${volunteer.interviewStatus}" /></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="pull-left">
                <label>
                    <input type="checkbox" name="inviteAll" class="a-invite-all"> Invite all
                </label>
            </div>
            <div class="pull-right">
                <form id="invite-volunteer-form" method="POST" action="">
                    <input type="hidden" name="volunteerIds" />
                    <button id="invite-volunteers" class="btn btn-lg btn-success" href="#">Invite</button>
                </form>
            </div>
            <div class="clearfix"></div>
            <div id="invite-volunteers-error" class="hide">
                <br />
                <div class="alert alert-danger">
                    <h4>No volunteers selected</h4>
                    <p>Use the checkboxes to select the volunteers to invite,
                        or return to the <a href="<c:url value="${viewUri}" />">interview session details</a></p>
                </div>
            </div>
        </div>
        <div class="clearfix"></div>
        <ol class="breadcrumb">
            <li><a href="<c:url value="/" />">Edifice</a></li>
            <li><a href="<c:url value="${listUri}" />">Interview Sessions</a></li>
            <li><a href="<c:url value="${viewUri}" />">${pageTitle}</a></li>
            <li class="active">Invitations</li>
        </ol>

        <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        <script type="text/javascript" src="<c:url value='/javascript/thirdparty/jquery-numeric-1.3.1.js' />" ></script>
        <script type="text/javascript" src="<c:url value='/javascript/volunteers.js' />" ></script>
    </body>
</html>
