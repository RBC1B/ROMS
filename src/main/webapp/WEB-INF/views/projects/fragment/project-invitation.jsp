<%--
    Document    :   project-invitation - displays the list of volunteers
                    who can be invited to a project
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:choose>
    <c:when test="${not assignment}">
        You have no departmental assignment to show invitations for.
    </c:when>
    <c:otherwise>
        <div class="container-fluid">
            <br />
            <div id="availability">
                <div class="form-group">
                    <div class="row col-md-12">
                        <label class="control-label col-md-3">Project Work Session</label>
                        <div class="col-md-4">
                            <select id="available-work-sessions" class="form-control" name="available-work-sessions">
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
            <br/>
            <sec:authorize access="hasPermission('PROJECT', 'EDIT')">
                <a id="add-new-project-session" href='#' class="btn btn-edifice">Add Project Work Session</a>
            </sec:authorize>
        </div>
        <div class="container-fluid">
            <label class="control-label">Department Volunteers</label>
            <div id="table-location"></div>
        </div>
    </c:otherwise>
</c:choose>

<!-- Modal for creating/updating project department work sessions -->
<div class="modal fade" id="project-department-session" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="${project.modifyDepartmentSessionUri}" method="post" id="project-department-session-form">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">x</button>
                    <h4 class="modal-title" id="project-department-session-form-label">Create Project Work Session</h4>
                </div>
                <div class="modal-body">
                    <div class="alert alert-danger" id="alert-update"></div>
                    <input id="projectId" type="hidden" name="projectId" value="${project.projectId}" />
                    <div class="form-group">
                        <label class="control-label">Department</label>
                        <select id="departmentId" class="form-control" name="departmentId">
                            <c:forEach var="department" items="${departments}">
                                <option value="${department.departmentId}">
                                    <c:out value="${department.name}" />
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="fromDate" class="control-label">From</label>
                    <input id="fromDate" name="fromDate" type="text" class="form-control" placeholder="Start Date" />
                </div>
                <div class="form-group">
                    <label for="toDate" class="control-label">To</label>
                    <input id="toDate" name="toDate" type="text" class="form-control" placeholder="Last Date" />
                </div>
                <div class="form-group">
                    <label for="sunday" class="control-label">Include Sunday</label>
                    <input id="sunday" name="sunday" type="checkbox">
                </div>
                <div class="modal-footer">
                    <button id="cancel-update" type="button" aria-hidden="true" data-dismiss="modal">Cancel</button>
                    <button id="submit-update" type="submit" class="btn btn-edifice">Submit Request</button>
                </div>
            </form>
        </div>
    </div>
</div>

