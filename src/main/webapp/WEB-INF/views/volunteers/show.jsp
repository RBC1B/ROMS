<%--
    Author     : rahulsingh
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="pageTitle">Volunteer #${volunteer.id} - ${volunteer.displayName}</c:set>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <div class="media">
            <c:choose>
                <c:when test="${volunteer.photoProvided}">
                    <c:set var="volunteerImagePath"><c:url value='${volunteer.id}/image' /></c:set>
                    <c:set var="volunteerImageUpdateText">Change image</c:set>
                </c:when>
                <c:otherwise>
                    <c:set var="volunteerImagePath"><c:url value='/images/default-volunteer-image.jpg' /></c:set>
                    <c:set var="volunteerImageUpdateText">Add image</c:set>
                </c:otherwise>
            </c:choose>
            <div id="volunteer-image">
                <img src="${volunteerImagePath}" class="media-object pull-left" alt="volunteer image" />
                <div id="volunteer-image-edit" class="caption" style="display: none;">
                    <p><a href="#">${volunteerImageUpdateText}</a></p>
                </div>
            </div>
            <div class="media-body">
                <div id="volunteer-name" class="a-edit-hover"
                     data-forename="${volunteer.forename}"
                     data-middle-name="${volunteer.middleName}"
                     data-surname="${volunteer.surname}">
                    <h1 class="media-heading">
                        #${volunteer.id}: <span id="volunteer-full-name"><c:out value="${volunteer.forename} ${volunteer.middleName} ${volunteer.surname}" /></span>
                        <sec:authorize access="hasPermission('DATABASE', 'READ')">
                            <c:if test="${!empty userUri}">
                                <a href="<c:url value='${userUri}' />" class="btn btn-edifice btn-xs">View User</a>
                            </c:if>
                        </sec:authorize>
                        <sec:authorize access="hasPermission('VOLUNTEER', 'EDIT')">
                            <a class="hide btn btn-edifice btn-xs a-edit-hover-action" href="#">Edit</a>
                        </sec:authorize>
                    </h1>

                </div>
                <dl class="dl-horizontal">
                    <div id="volunteer-rbc-status-code" class="a-edit-hover">
                        <dt>Status:</dt>
                        <dd>
                            <span id="volunteer-rbc-status-code-content"><c:out value="${volunteer.status}" /></span>
                            <sec:authorize access="hasPermission('VOLUNTEER', 'EDIT')">
                                <a class="hide btn btn-edifice btn-xs a-edit-hover-action" href="#">Edit</a>
                            </sec:authorize>
                        </dd>
                    </div>
                    <!-- we need to capture the dt,dd so that empty comments can be edited
                         but there is no wrapping object. Much complaints on the interwebs,
                         so this will have to do -->
                    <div id="volunteer-comments" class="a-edit-hover">
                        <dt>Comments:</dt>
                        <dd>
                            <span id="volunteer-comments-content"><c:out value="${volunteer.comments}" /></span>
                            <sec:authorize access="hasPermission('VOLUNTEER', 'EDIT')">
                                <a class="hide btn btn-edifice btn-xs a-edit-hover-action" href="#">Edit</a>
                            </sec:authorize>
                        </dd>
                    </div>
                </dl>
            </div>
        </div>
        <br />
        <c:choose>
            <c:when test="${!empty badgeUri}">
                <a class="btn btn-edifice" href="<c:url value="${badgeUri}"/>" id="badge-button">Generate badge</a><br />
            </c:when>
            <c:otherwise>
                <button class="btn" type="button" data-title="Pdf Badge Generation Not Possible"
                        data-content="A badge for <c:out value='${volunteer.displayName}' /> cannot be created before setting their:
                        <ul><li>birth date</li><li>photo</li><li>department assignments</li></ul>"
                        id="disabled-badge-button">Generate badge</button>
            </c:otherwise>
        </c:choose>
        <sec:authorize access="hasPermission('DATABASE', 'ADD')">
            <c:if test="${!empty createUserUri}">
                &nbsp;<a href="<c:url value='${createUserUri}' />" class="btn btn-edifice">Create User</a>
            </c:if>
        </sec:authorize>
        <hr />
        <%@ include file="fragments/show-assignments.jsp" %>
        <div class="clearfix"></div>
        <br />
        <ul class="nav nav-tabs">
            <li class="active"><a href="#personal" data-toggle="tab">Personal</a></li>
            <li><a href="#spiritual" data-toggle="tab">Spiritual</a></li>
            <li><a href="#skills" data-toggle="tab">Skills &amp; Experience</a></li>
            <li><a href="#rbc-status" data-toggle="tab">RBC status</a></li>
        </ul>
        <div class="tab-content">
            <div class="tab-pane active" id="personal">
                <div class="row-fluid">
                    <%@ include file="fragments/show-personal.jsp" %>
                </div>
            </div>
            <div class="tab-pane" id="spiritual">
                <div class="row-fluid">
                    <%@ include file="fragments/show-spiritual.jsp" %>
                </div>
            </div>
            <div class="tab-pane" id="skills">
                <%@ include file="fragments/show-skills.jsp" %>
            </div>
            <div class="tab-pane" id="rbc-status">
                <div class="row-fluid">
                    <%@ include file="fragments/show-rbc-status.jsp" %>
                </div>
            </div>
        </div>
        <div class="clearfix"></div>

        <br />
        <ol class="breadcrumb">
            <li><a href="<c:url value="/" />">Edifice</a></li>
            <li><a href="<c:url value="/volunteers" />">Volunteers</a></li>
            <li class="active">#${volunteer.id}: <c:out value="${volunteer.displayName}" /></li>
        </ol>
        <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        <%@ include file="fragments/show-name-edit-modal.jsp" %>
        <%@ include file="fragments/show-comments-edit-modal.jsp" %>
        <%@ include file="fragments/show-assignment-edit-modal.jsp" %>
        <%@ include file="fragments/show-skill-edit-modal.jsp" %>
        <%@ include file="fragments/show-rbc-status-code-edit-modal.jsp" %>
        <%@ include file="fragments/show-volunteer-image-edit-modal.jsp" %>
        <%@ include file="fragments/show-experience-edit-modal.jsp" %>
        <%@ include file="fragments/show-qualification-edit-modal.jsp" %>
        <%@ include file="fragments/show-emergency-contact-edit-modal.jsp" %>
        <%@ include file="/WEB-INF/views/common/person-link-modal.jsp"%>
        <%@ include file="/WEB-INF/views/common/mustache-person-link-search-form.jsp"%>
        <script type="text/javascript" src="<c:url value='/javascript/thirdparty/jquery-numeric-1.3.1.js' />" ></script>
        <script type="text/javascript" src="<c:url value='/javascript/volunteers.js' />" ></script>
    </body>
</html>
