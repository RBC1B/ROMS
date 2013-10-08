<%--
    Document   : 404
    Created on : 06-Jul-2012, 00:32:12
    Author     : rhioli
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <c:set var="pageTitle" value="404" />
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
            <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
            <div class="container">
                <h1>We seem to have misplaced that one.</h1>
                <p>Sorry, but the page you were trying to view does not exist.</p>
                <p>It looks like this was the result of either:</p>
                <ul>
                    <li>a mistyped address</li>
                    <li>an out-of-date link</li>
                </ul>
                <p><a href="<c:url value="/" />">&laquo; Back to dashboard</a></p>
                <hr/>
              </div>
            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
    </body>
</html>
