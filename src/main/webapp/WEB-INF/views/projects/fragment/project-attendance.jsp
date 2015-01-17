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
        <div class="container-fluid">
            <br/>
            <div id="confirmation">
                <div class="form-group">
                    <div class="row col-md-12">
                        <label class="control-label col-md-3">Project Work Session</label>
                        <div class="col-md-4">
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
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="container-fluid">
            <label class="control-label">Department Volunteers</label>
            <div id="confirmation-table-location"></div>
        </div>
    </c:otherwise>
</c:choose>