<%--
    Error page when a 404 (Page not found) error occurs.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <c:set var="pageTitle">404 - page not found</c:set>
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
            <p>Feel free to <a href="mailto:edificesupport@rbc-lhc.org.uk?Subject=Edifice%20Error">report a problem</a></p>
            <p>...or go <a href="<c:url value="/" />">&laquo; back to dashboard</a></p>
            <hr/>
        </div>
        <%@ include file="/WEB-INF/views/common/footer.jsp" %>
    </body>
</html>
