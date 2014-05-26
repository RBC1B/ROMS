<%--
    Homepage dashboard.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<c:set var="pageTitle">RBC Online Management System for Construction</c:set>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<body>
    <%@ include file="/WEB-INF/views/common/titlebar.jsp"%>
    <c:choose>
        <c:when test="${!empty volunteer}">
            <div class="media">
                <c:choose>
                    <c:when test="${volunteer.photoProvided}">
                        <c:set var="volunteerImagePath">
                            <c:url value='${volunteer.id}/image' />
                        </c:set>
                    </c:when>
                    <c:otherwise>
                        <c:set var="volunteerImagePath">
                            <c:url value='/images/default-volunteer-image.jpg' />
                        </c:set>
                    </c:otherwise>
                </c:choose>
                <div id="volunteer-image">
                    <img src="${volunteerImagePath}" class="media-object pull-left" alt="volunteer image" />
                </div>
                <div class="media-body">
                    <h1 class="media-heading">
                        #${volunteer.id}: <span id="volunteer-full-name"><c:out
                                value="${volunteer.forename} ${volunteer.middleName} ${volunteer.surname}" /></span>
                    </h1>
                    <a href="<c:url value='${volunteer.uri}' />">Full details</a>
                    <div id="volunteer-with-assignments" <c:if test="${empty assignments}">style="display: none"</c:if>>
                        <table class="table table-bordered table-condensed table-striped table-hover" id="volunteer-assignments">
                            <thead>
                                <tr>
                                    <th>Trade no.</th>
                                    <th>Department</th>
                                    <th>Team</th>
                                    <th>Role</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${assignments}" var="assignment">
                                    <tr>
                                        <td>${assignment.tradeNumber}</td>
                                        <td><a href="<c:url value="${assignment.department.uri}" />"><c:out
                                                    value="${assignment.department.name}" /></a></td>
                                        <td><a href="<c:url value="${assignment.team.uri}" />"><c:out
                                                    value="${assignment.team.name}" /></a></td>
                                        <td>${assignment.role}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div id="volunteer-without-assignments" <c:if test="${!empty assignments}">style="display: none"</c:if>>
                        <br />
                        <div class="alert alert-warning">Volunteer is not assigned to any teams</div>
                    </div>
                </div>
            </div>
            <br />
            <div class="clearfix"></div>
        </c:when>
        <c:otherwise>
            <p>&nbsp;</p>
            <div class="alert alert-danger">
                <p>No volunteer is linked to your user account</p>
            </div>
        </c:otherwise>
    </c:choose>
    <%@ include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>
