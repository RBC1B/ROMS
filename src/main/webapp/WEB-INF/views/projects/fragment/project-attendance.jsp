<%-- 
    Document   : project-attendance - displays the list of volunteers and dates
                    that they are available
    Created on : Dec 11, 2014, 2:41:23 PM
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
    <c:when test="${not assignment}">
        You have no departmental assignment to show volunteer availability for.
    </c:when>
    <c:otherwise>
        <dl class="dl-horizontal">
            <div id="confirmation">
                <dt>Project Work Session</dt>
                <dd>
                    <select id="confirmation-work-sessions" class="form-control" name="confirmation-work-sessions">
                        <option value="None" selected>
                            No Session Selected
                        </option>
                        <c:forEach var="workSession" items="${workSessions}">
                            <option value="${workSession.projectDepartmentSession}">
                                <c:out value="${workSession.projectDepartmentSession}" />
                            </option>
                        </c:forEach> 
                    </select>
                </dd>
            </div>
        </dl>
        <dl>
            <dt>Volunteers</dt>
            <div id="confirmation-table-location">
            </div>
        </dl>
    </c:otherwise>
</c:choose>