<%--
    Document   : list
    Created on : Apr 18, 2014, 3:17:00 PM
    Author     : ramindursingh
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="roms" uri="/WEB-INF/tld/roms.tld"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:url var="formAction" value="/users" />
<html>
    <c:set var="pageTitle">Departmental users</c:set>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <h1>Edifice Users</h1>
        <hr />
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

        <br />
        <ol class="breadcrumb">
            <li><a href="<c:url value="/" />">Edifice</a></li>
            <li class="active">Users</li>
        </ol>
        <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        <script type="text/javascript" src="<c:url value='/javascript/users.js' />" ></script>
    </body>
</html>
