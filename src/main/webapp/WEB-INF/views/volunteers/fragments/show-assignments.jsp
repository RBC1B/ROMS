<%--
The volunteer department links.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<div id="volunteer-with-assignments" <c:if test="${empty assignments}">style="display: none"</c:if>>
    <h3>Team roles</h3>
    <table class="table table-bordered table-condensed table-striped table-hover" id="volunteer-assignments">
        <thead>
            <tr>
                <th>Trade no.</th>
                <th>Department</th>
                <th>Team</th>
                <th>Role</th>
                <th>Assigned</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${assignments}" var="assignment">
                <tr data-assignment-id="${assignment.id}">
                    <td>${assignment.tradeNumber}</td>
                    <td><a href="<c:url value="${assignment.department.uri}" />"><c:out
                                value="${assignment.department.name}" /></a></td>
                    <td><a href="<c:url value="${assignment.team.uri}" />"><c:out
                                value="${assignment.team.name}" /></a></td>
                    <td>${assignment.role}</td>
                    <td><fmt:formatDate value="${assignment.assignedDate}" pattern="dd/MM/yyyy" /></td>
                    <td><sec:authorize access="hasPermission('VOLUNTEER', 'EDIT')">
                            <ul class="list-inline">
                                <li><a class="a-edit-assignment" href="<c:url value="${assignment.uri}" />">Edit</a></li>
                                <li><a class="a-delete-assignment"
                                    data-ajax-url="<c:url value="${assignment.uri}" />" href="#"> Delete</a></li>
                            </ul>
                        </sec:authorize>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<div id="volunteer-without-assignments" <c:if test="${!empty assignments}">style="display: none"</c:if>>
    <br />
    <div class="alert alert-warning">Volunteer is not assigned to any teams</div>
</div>
<sec:authorize access="hasPermission('VOLUNTEER', 'EDIT')">
    <a class="btn btn-edifice" href="<c:url value="${volunteer.editAssignmentUri}"/>" id="a-add-assignment">Add trade</a><br />
    <script id="volunteer-assignments-action-template" type="text/html" charset="utf-8">
        <ul class="list-inline">
            <li><a class="a-edit-assignment" href="{{assignmentUri}}">Edit</a></li>
            <li><a class="a-delete-assignment" data-ajax-url="{{assignmentUri}}" href="#"> Delete</a></li>
        </ul>
    </script>
</sec:authorize>
