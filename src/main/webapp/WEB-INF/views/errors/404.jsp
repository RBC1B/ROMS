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
        <div class="container">
            <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
  <div class="container">
    <h1>We seem to have misplaced that one.</h1>
    <p>Sorry, but the page you were trying to view does not exist.</p>
    <p>It looks like this was the result of either:</p>
    <ul>
      <li>a mistyped address</li>
      <li>an out-of-date link</li>
    </ul>
        <p><a href="index.html">Back to home page ></a></p>
    <script>
      var GOOG_FIXURL_LANG = (navigator.language || '').slice(0,2),GOOG_FIXURL_SITE = location.host;
    </script>
    <script src="http://linkhelp.clients.google.com/tbproxy/lh/wm/fixurl.js"></script>
  </div>           
    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        </div>
      <script type="text/javascript" charset="utf8" src="<c:url value='/javascript/circuits.js' />" ></script>
    </body>
</html>
