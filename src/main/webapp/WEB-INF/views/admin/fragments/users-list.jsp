<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="roms" uri="/WEB-INF/tld/roms.tld"%>
<div class="entity-list-results">
    <table class="table table-bordered table-condensed table-striped table-hover" id="user-list">
        <thead>
            <tr>
                <th>User Name</th>
                <th>Name</th>
                <th>User Status</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td><c:out value="${user.userName}" /></td>
                    <td><a href="<c:url value="${user.volunteerUri}" />"><c:out value="${user.name}" /></a></td>
                    <td><roms:userStatus status="${user.active}" /></td>
                    <td>
                        <ul class="list-inline">
                            <li><a class="btn btn-success" href="<c:url value="${user.uri}" />">View</a></li>
                                <sec:authorize access="hasPermission('DATABASE', 'EDIT')">
                                <li><a href="<c:url value="${user.editUri}" />">Edit</a></li>
                                </sec:authorize>
                        </ul>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
