<%--
The contents of the rbc status tab.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<dl class="dl-horizontal">
    <dt>Form date:</dt>
    <dd>
        <c:choose>
            <c:when test="${!empty volunteer.formDate}">
            <fmt:formatDate value="${volunteer.formDate}" pattern="dd MMM yyyy" />
            </c:when>
            <c:otherwise>-</c:otherwise>
        </c:choose>
    </dd>
</dl>

<c:choose>
    <c:when test="${empty interviews}">
        <div class="alert alert-danger">No interview invitations</div>
    </c:when>
    <c:otherwise>
        <div class="entity-list-results">
            <table class="table table-bordered table-condensed table-striped table-hover">
                <thead>
                    <tr>
                        <th>Session</th>
                        <th>Comments</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${interviews}" var="interview">
                        <tr>
                            <td>
                                <a href="<c:url value='${interview.sessionUri}' />">
                                    <!-- missing line break to preserve spacing around comma -->
                                    <fmt:formatDate value="${interview.date}" pattern="yyyy-MM-dd" /><c:if test="${!empty interview.kingdomHall}">,
                                        <c:out value="${interview.kingdomHall.name}" />
                                    </c:if>
                                </a>
                            </td>
                            <td>${interview.comments}</td>
                            <td>${interview.status}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </c:otherwise>
</c:choose>
<dl class="dl-horizontal">
    <dt>Interviewers:</dt>
    <dd>
        <c:choose>
            <c:when test="${!empty volunteer.interviewerA  || !empty volunteer.interviewerB}">
                <c:if test="${!empty volunteer.interviewerA}">
                    <a href="<c:url value='${volunteer.interviewerA.uri}' />" ><c:out value="${volunteer.interviewerA.name}" /></a>
                </c:if>
                <c:if test="${!empty volunteer.interviewerA && !empty volunteer.interviewerB}">,</c:if>
                <c:if test="${!empty volunteer.interviewerB}">
                    <a href="<c:url value='${volunteer.interviewerB.uri}' />" ><c:out value="${volunteer.interviewerB.name}" /></a>
                </c:if>
            </c:when>
            <c:otherwise>-</c:otherwise>
        </c:choose>
    </dd>
    <dt>Interview comments:</dt>
    <dd>
        <c:choose>
            <c:when test="${!empty volunteer.interviewComments}">
                <c:out value="${volunteer.interviewComments}" />
            </c:when>
            <c:otherwise>-</c:otherwise>
        </c:choose>
    </dd>
    <dt>Joined date:</dt>
    <dd>
        <c:choose>
            <c:when test="${!empty volunteer.joinedDate}">
                <fmt:formatDate value="${volunteer.joinedDate}" pattern="dd MMM yyyy" />
            </c:when>
            <c:otherwise>-</c:otherwise>
        </c:choose>
    </dd>
    <dt>Badge issue date:</dt>
    <dd>
        <c:choose>
            <c:when test="${!empty volunteer.badgeIssueDate}">
                <fmt:formatDate value="${volunteer.badgeIssueDate}" pattern="dd MMM yyyy" />
            </c:when>
            <c:otherwise>-</c:otherwise>
        </c:choose>
    </dd>
    <dt>Availability:</dt>
    <dd>
        <span>
            <c:choose>
                <c:when test="${volunteer.availability.get(0)}"><span class="label label-success">Mon</span></c:when>
                <c:otherwise><span class="label label-danger">Mon</span></c:otherwise>
            </c:choose>
        </span>
        <span>
            <c:choose>
                <c:when test="${volunteer.availability.get(1)}"><span class="label label-success">Tue</span></c:when>
                <c:otherwise><span class="label label-danger">Tue</span></c:otherwise>
            </c:choose>
        </span>
        <span>
            <c:choose>
                <c:when test="${volunteer.availability.get(2)}"><span class="label label-success">Wed</span></c:when>
                <c:otherwise><span class="label label-danger">Wed</span></c:otherwise>
            </c:choose>
        </span>
        <span>
            <c:choose>
                <c:when test="${volunteer.availability.get(3)}"><span class="label label-success">Thu</span></c:when>
                <c:otherwise><span class="label label-danger">Thu</span></c:otherwise>
            </c:choose>
        </span>
        <span>
            <c:choose>
                <c:when test="${volunteer.availability.get(4)}"><span class="label label-success">Fri</span></c:when>
                <c:otherwise><span class="label label-danger">Fri</span></c:otherwise>
            </c:choose>
        </span>
        <span>
            <c:choose>
                <c:when test="${volunteer.availability.get(5)}"><span class="label label-success">Sat</span></c:when>
                <c:otherwise><span class="label label-danger">Sat</span></c:otherwise>
            </c:choose>
        </span>
        <span>
            <c:choose>
                <c:when test="${volunteer.availability.get(6)}"><span class="label label-success">Sun</span></c:when>
                <c:otherwise><span class="label label-danger">Sun</span></c:otherwise>
            </c:choose>
        </span>
    </dd>
    <dt>Oversight:</dt>
    <dd>
        <c:choose>
            <c:when test="${volunteer.oversight}"><span class="icon-ok"></span></c:when>
            <c:otherwise><span class="icon-remove"></span></c:otherwise>
        </c:choose>
        <c:if test="${!empty volunteer.oversightComments}">
            <c:out value="${volunteer.oversightComments}" />
        </c:if>
    </dd>
    <dt>Relief UK:</dt>
    <dd>
        <c:choose>
            <c:when test="${volunteer.reliefUK}"><span class="icon-ok"></span></c:when>
            <c:otherwise><span class="icon-remove"></span></c:otherwise>
        </c:choose>
        <c:if test="${!empty volunteer.reliefUKComments}">
            <c:out value="${volunteer.reliefUKComments}" />
        </c:if>
    </dd>
    <dt>Relief abroad:</dt>
    <dd>
        <c:choose>
            <c:when test="${volunteer.reliefAbroad}"><span class="icon-ok"></span></c:when>
            <c:otherwise><span class="icon-remove"></span></c:otherwise>
        </c:choose>
        <c:if test="${!empty volunteer.reliefAbroadComments}">
            <c:out value="${volunteer.reliefAbroadComments}" />
        </c:if>
    </dd>
    <dt>HHC form code:</dt>
    <dd>
        <c:choose>
            <c:when test="${!empty volunteer.hhcFormCode}">
                ${volunteer.hhcFormCode}
            </c:when>
            <c:otherwise>-</c:otherwise>
        </c:choose>
    </dd>
</dl>
<sec:authorize access="hasPermission('VOLUNTEER', 'EDIT')">
    <hr />
    <a class="btn btn-edifice" href="<c:url value='${volunteer.editRbcStatusUri}' />">Edit</a>
</sec:authorize>
