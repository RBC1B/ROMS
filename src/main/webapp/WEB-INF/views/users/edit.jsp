<%--
    Document   : edit
    Created on : Apr 20, 2014, 5:35:07 PM
    Author     : ramindursingh
--%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="roms" uri="/WEB-INF/tld/roms.tld"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:choose>
        <c:when test="${!empty userForm.userName}">
            <c:set var="pageTitle">Edit user</c:set>
            <c:set var="newUser" value="false" />
        </c:when>
        <c:otherwise>
            <c:set var="pageTitle">Create new user</c:set>
            <c:set var="newUser" value="true" />
        </c:otherwise>
    </c:choose>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <h1><c:out value='${pageTitle}' /></h1>
        <hr />
        <h2><c:out value="${person.forename} ${person.middleName} ${person.surname}"/></h2>
        <c:url var="formAction" value="${submitUri}" />
        <form:form class="form-horizontal" commandName="userForm" method="${submitMethod}" action="${formAction}">
            <fieldset>
                <form:hidden path="userId" />
                <div class="form-group">
                    <label for="name" class="control-label col-sm-3 col-md-2">User name</label>
                    <div class="col-sm-9 col-md-3">
                        <form:input class="form-control" path="userName" maxlength="50" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="active" class="control-label col-sm-2">Active</label>
                    <div class="col-sm-2">
                        <form:checkbox path="active"/>
                    </div>
                </div>
            </fieldset>
            <fieldset>
                <c:if test="${!newUser}"><p>Leave password blank unless changing it</p></c:if>
                <c:if test="${newUser}"><c:set var="passwordRequired">required="true"</c:set></c:if>
                <div class="form-group">
                    <label for="name" class="control-label col-sm-3 col-md-2">Password</label>
                    <div class="col-sm-9 col-md-3">
                        <input type="password" class="form-control" name="password" id="password" maxlength="50" ${passwordRequired} />
                    </div>
                </div>
                <div class="form-group">
                    <label for="name" class="control-label col-sm-3 col-md-2">Password Confirm</label>
                    <div class="col-sm-9 col-md-3">
                        <input type="password" class="form-control" name="passwordConfirm" id="passwordConfirm" maxlength="50" ${passwordRequired} />
                    </div>
                </div>
            </fieldset>
            <fieldset>
                <legend>Department Access Permissions</legend>
                <c:forEach items="${applications}" var="application">
                    <div class="form-group">
                        <label for="name" class="control-label col-sm-3 col-md-2">${application.name}</label>
                        <div class="col-sm-9 col-md-3">
                            <roms:select className="form-control" selected="${application.deptPermission}" itemValue="${fn:toLowerCase(application.code)}Dept" />
                        </div>
                    </div>
                </c:forEach>
            </fieldset>
            <fieldset>
                <legend>Non-department Access Permissions</legend>
                <c:forEach items="${applications}" var="application">
                    <div class="form-group">
                        <label for="name" class="control-label col-sm-3 col-md-2">${application.name}</label>
                        <div class="col-sm-9 col-md-3">
                            <roms:select className="form-control" selected="${application.nonDeptPermission}" itemValue="${fn:toLowerCase(application.code)}All" />
                        </div>
                    </div>
                </c:forEach>
            </fieldset>
            <fieldset>
                <div class="form-group">
                    <label class="control-label col-sm-3 col-md-2"></label>
                    <div class="col-sm-9 col-md-3">
                        <button type="submit" class="btn btn-default btn-success">Submit</button>
                    </div>
                </div>
            </fieldset>
        </form:form>

        <ol class="breadcrumb">
            <li><a href="<c:url value="/" />">Edifice</a></li>
            <li role="menuitem"><a href="<c:url value="/users" />">Users</a></li>
            <c:choose>
                <c:when test="${!empty userForm.userName}">
                <li class="active">Edit</li>
                </c:when>
                <c:otherwise>
                <li class="active">New User</li>
                </c:otherwise>
            </c:choose>
        </ol>
        <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        <script type="text/javascript" src="<c:url value='/javascript/users.js' />" ></script>
    </body>
</html>
