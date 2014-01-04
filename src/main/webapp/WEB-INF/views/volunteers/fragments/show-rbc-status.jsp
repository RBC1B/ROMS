<%--
The contents of the rbc status tab.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <dt>Interview date:</dt>
    <dd>
        <c:choose>
            <c:when test="${!empty volunteer.interviewDate}">
                <fmt:formatDate value="${volunteer.interviewDate}" pattern="dd MMM yyyy" />
            </c:when>
            <c:otherwise>-</c:otherwise>
        </c:choose>
    </dd>
    <dt>Interviewers:</dt>
    <dd>
        <c:choose>
            <c:when test="${!empty volunteer.interviewerA  || !empty volunteer.interviewerB}">
                <c:if test="${!empty volunteer.interviewerA}">
                    <a href="<c:url value='${volunteer.interviewerA.uri}' />" >${volunteer.interviewerA.name}</a>
                </c:if>
                <c:if test="${!empty volunteer.interviewerA && !empty volunteer.interviewerB}">,</c:if>
                <c:if test="${!empty volunteer.interviewerB}">
                    <a href="<c:url value='${volunteer.interviewerB.uri}' />" >${volunteer.interviewerB.name}</a>
                </c:if>
            </c:when>
            <c:otherwise>-</c:otherwise>
        </c:choose>
    </dd>
    <dt>Interview comments:</dt>
    <dd>
        <c:choose>
            <c:when test="${!empty volunteer.interviewComments}">
                ${volunteer.interviewComments}
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
        <table id="volunteer-availability" class="table-bordered">
            <thead>
                <tr>
                    <th>Mon</th>
                    <th>Tue</th>
                    <th>Wed</th>
                    <th>Thu</th>
                    <th>Fri</th>
                    <th>Sat</th>
                    <th>Sun</th>
                </tr>
            </thead>
            <tbody>
                <tr>
            <c:forEach var="i" begin="0" end="6">
                <td>
                <c:choose>
                    <c:when test="${volunteer.availability.get(i)}"><span class="icon-ok"></span></c:when>
                    <c:otherwise><span class="icon-remove"></span></c:otherwise>
                </c:choose>
                </td>
            </c:forEach>
            </tr>
            </tbody>
        </table>
    </dd>
    <dt>Oversight:</dt>
    <dd>
        <c:choose>
            <c:when test="${volunteer.oversight}"><span class="icon-ok"></span></c:when>
            <c:otherwise><span class="icon-remove"></span></c:otherwise>
        </c:choose>
        <c:if test="${!empty volunteer.oversightComments}">
            ${volunteer.oversightComments}
        </c:if>
    </dd>
    <dt>Relief UK:</dt>
    <dd>
        <c:choose>
            <c:when test="${volunteer.reliefUK}"><span class="icon-ok"></span></c:when>
            <c:otherwise><span class="icon-remove"></span></c:otherwise>
        </c:choose>
        <c:if test="${!empty volunteer.reliefUKComments}">
            ${volunteer.reliefUKComments}
        </c:if>
    </dd>
    <dt>Relief abroad:</dt>
    <dd>
        <c:choose>
            <c:when test="${volunteer.reliefAbroad}"><span class="icon-ok"></span></c:when>
            <c:otherwise><span class="icon-remove"></span></c:otherwise>
        </c:choose>
        <c:if test="${!empty volunteer.reliefAbroadComments}">
            ${volunteer.reliefAbroadComments}
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
