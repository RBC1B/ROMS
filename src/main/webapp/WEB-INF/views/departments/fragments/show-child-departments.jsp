<%--
The contents of the child departments tab.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="entity-list-results">
    <table class="table table-bordered table-condensed table-striped table-hover" id="department-list">
        <thead>
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Overseer</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${childDepartments}" var="department">
                <tr>
                    <td><c:out value="${department.name}" /></td>
                    <td><c:if test="${not empty department.description}"><c:out value="${department.description}" /></c:if></td>
                    <td><c:if test="${not empty department.overseer}">
                            <a href="<c:url value='${department.overseer.uri}'/>"><c:out value="${department.overseer.displayName}" /></a>
                        </c:if></td>
                    <td>
                        <ul class="inline list-actions">
                            <li><a class="btn btn-success" href="<c:url value="${department.uri}" />">View</a></li>
                        </ul>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
