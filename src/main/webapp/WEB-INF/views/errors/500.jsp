<%--
    Error page when a 500 error occurs.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <c:set var="pageTitle">500 - error</c:set>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <div class="container">
            <h1>Something has gone badly, badly wrong.</h1>
            <p>Please go to <a href="mailto:edificesupport@rbc-lhc.org.uk?Subject=Edifice%20Error">report a problem, including the details below</a></p>
            <hr/>
            <h2>Details</h2>
            <pre>${stackTrace}</pre>
        </div>
        <%@ include file="/WEB-INF/views/common/footer.jsp" %>
    </body>
</html>
