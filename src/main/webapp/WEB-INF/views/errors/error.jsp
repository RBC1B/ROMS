<%--
    Document   : error
    Created on : Sep 1, 2013, 10:09:08 AM
    Author     : ramindursingh
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <c:set var="pageTitle" value="404" />
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <div class="container">
            <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
            <div class="container">
                <h1>Looks like a problem.</h1>
                <p>Sorry, but there was a problem loading the page. Please email the
                    support team giving them as much information as possible:</p>
                <ul>
                    <li>
                        <a href="mailto:edificesupport@rbc-lhc.org.uk?Subject=Edifice%20Error">
                              Report a Problem</a>
                    </li>
                </ul>
                <p><a href="<c:url value="/" />">&laquo; Back to dashboard</a></p>
                <hr/>
              </div>
            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        </div>
    </body>
</html>
