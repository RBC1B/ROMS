<%--
    Document    :   project-invitation - displays the list of volunteers
                    who can be invited to a project
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:choose>
    <c:when test="${not assignment}">
        You have no current department assignment.
    </c:when>
    <c:otherwise>
        <dl class="dl-horizontal">
            <dt>Project Work Session</dt>
            <dd>
                <select id="work-sessions" class="form-control" name="work-sessions">
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
            <sec:authorize access="hasPermission('PROJECT', 'EDIT')">
                <a href='<c:url value="${project.editUri}" />' class="btn btn-edifice">Add New Work Session</a>
            </sec:authorize>
        </dl>
        <dl>
            <dt>Volunteers</dt>
            <div id="table-location">
            </div>
        </dl>
    </c:otherwise>
</c:choose>