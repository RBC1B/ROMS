<%--
    Page displayed when a user logs out.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Logout | EDIFICE</title>
</head>
<body class="blueprints">
    <div class="container">
        <form class="form-signin">
            <h2>Logged out.</h2>
            <p>
                You can always <a href="<c:url value="/login" />">log back in</a> if you want to.
            </p>
        </form>
    </div>
</body>
</html>



