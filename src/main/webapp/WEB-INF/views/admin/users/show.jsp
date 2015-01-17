<%--
    Document   : show
    Created on : Apr 19, 2014, 1:57:11 PM
    Author     : ramindursingh
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="roms" uri="/WEB-INF/tld/roms.tld"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="pageTitle">Edifice Username - ${user.userName}</c:set>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <h1>User: <c:out value="${user.userName}" /></h1>
        <hr />
        <dl class="dl-horizontal">
            <dt>Name:</dt>
            <dd>
                <c:choose>
                    <c:when test="${!empty user.name}">
                        <a href="<c:url value="${user.volunteerUri}" />"><c:out value="${user.name}" /></a>
                    </c:when>
                    <c:otherwise>-</c:otherwise>
                </c:choose>
            </dd>
            <dt>User Status:</dt>
            <dd>
                <roms:userStatus status="${user.active}" />
            </dd>
        </dl>
        <div class="clearfix"></div>
        <br />
        <ul class="nav nav-tabs">
            <li class="active"><a href="#appacl" data-toggle="tab">Permissions</a></li>
        </ul>
        <div class="tab-content">
            <div class="tab-pane active" id="volunteers">
                <div class="row-fluid">
                    <div id="user-volunteer-user" data-user-id="${user.personId}"></div>
                    <table class="table table-bordered table-condensed table-striped table-hover" id="user-acl-list">
                        <thead>
                            <tr>
                                <th>Application</th>
                                <th>Department Access</th>
                                <th>Non-Department Access</th>
                            </tr>
                        <thead />
                        <tbody>
                            <c:forEach items="${permissions}" var="permission">
                                <tr>
                                    <td><c:out value="${permission.application.name}" /></td>
                                    <td><roms:permission forValue="${permission.departmentAccess}" /></td>
                                    <td><roms:permission forValue="${permission.nonDepartmentAccess}" /></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                        <tfoot>
                        </tfoot>
                    </table>
                </div>
            </div>
        </div>
        <sec:authorize access="hasPermission('DATABASE', 'EDIT')">
            <hr />
            <a href="<c:url value='${user.editUri}' />" class="btn btn-edifice">Edit User</a>
        </sec:authorize>
        <div class="clearfix"></div>

        <br />
        <ol class="breadcrumb">
            <li><a href="<c:url value="/" />">Edifice</a></li>
            <li role="menuitem"><a href="<c:url value="/admin" />">Admin</a></li>
            <li role="menuitem"><a href="<c:url value="/admin#!users" />">Users</a></li>
            <li class="active"><c:out value="${user.userName}" /></li>
        </ol>

        <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        <%@ include file="/WEB-INF/views/common/mustache-list-actions.jsp" %>
        <script type="text/javascript" src="<c:url value='/javascript/admin.js' />" ></script>
    </body>
</html>
