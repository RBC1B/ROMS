<%--
    Show the admin dashbard
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="pageTitle">System and User Administration</c:set>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <h1>System and User Administration</h1>
        <hr />
        <div role="tabpanel">
            <!-- Tabs -->
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class="active">
                    <a href="#account" aria-controls="account" role="tab"
                       data-toggle="tab">My Account</a>
                </li>
                <sec:authorize access="hasPermission('DATABASE', 'READ')">
                    <li role="presentation">
                        <a href="#users" aria-controls="users" role="tab"
                           data-toggle="tab">Users</a>
                    </li>
                </sec:authorize>
            </ul>

            <!-- Tab contents -->
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="account">
                    <%@ include file="fragments/authenticated-user.jsp" %>
                </div>
                <sec:authorize access="hasPermission('DATABASE', 'READ')">
                    <div role="tabpanel" class="tab-pane" id="users">
                        <%@ include file="fragments/users-list.jsp" %>
                    </div>
                </sec:authorize>
            </div>
        </div>
        <div class="clearfix"></div>
        <br />
        <ol class="breadcrumb">
            <li><a href="<c:url value="/" />">Edifice</a></li>
            <li>Admin</li>
        </ol>
        <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        <script type="text/javascript" src="<c:url value='/javascript/admin.js' />" ></script>
    </body>
</html>
