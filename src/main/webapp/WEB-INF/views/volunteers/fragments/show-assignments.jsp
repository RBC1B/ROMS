<%--
The volunteer department links.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
                <tr>
                    <td class="a-assignment-trade-number">${assignment.tradeNumber}</td>
                    <td class="a-assignment-department"><a href="<c:url value="${assignment.department.uri}" />"><c:out
                                value="${assignment.department.name}" /></a></td>
                    <td class="a-assignment-team"><a href="<c:url value="${assignment.team.uri}" />"><c:out
                                value="${assignment.team.name}" /></a></td>
                    <td class="a-assignment-role">${assignment.role}</td>
                    <td class="a-assignment-date"><fmt:formatDate value="${assignment.assignedDate}" pattern="dd/MM/yyyy" /></td>
                    <td><sec:authorize access="hasPermission('VOLUNTEER', 'EDIT')">
                            <ul class="list-inline">
                                <li><a class="a-edit-assignment" href="<c:url value="${assignment.uri}" />">Edit</a></li>
                                <li><a class="a-delete-assignment"
                                    data-ajax-url="<c:url value="${assignment.uri}" />" href="#"> Delete</a></li>
                            </ul>
                        </sec:authorize></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<div id="volunteer-without-assignments" <c:if test="${!empty assignments}">style="display: none"</c:if>>
    <br />
    <div class="alert alert-warning">Volunteer is not assigned to any teams</div>
</div>
