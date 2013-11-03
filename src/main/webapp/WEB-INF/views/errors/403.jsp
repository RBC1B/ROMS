<%--
    Error page when a 503 (Forbidden) error occurs.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <c:set var="pageTitle" value="503 - Forbidden page" />
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <div class="container">
            <h1>Page access blocked.</h1>
            <p>Sorry, but you don't have permissions to view this page.</p>
            <p>Feel free to <a href="mailto:edificesupport@rbc-lhc.org.uk?Subject=Edifice%20Error">report a problem</a></p>
            <p>...or go <a href="<c:url value="/" />">&laquo; back to dashboard</a></p>
            <hr/>
        </div>
        <%@ include file="/WEB-INF/views/common/footer.jsp" %>
    </body>
</html>
