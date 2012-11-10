<%--
    Document   : circuit
    Created on : 01-Jul-2012, 00:06:12
    Author     : oliver.elder.esq
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="pageTitle" value="Kingdom Hall" />
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
       <div class="container">
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
            <h1>Kingdom Hall: ${kingdomHall.kingdomHall}</h1>
            ${kingdomHall}
        <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        </div>
    </body>
</html>
