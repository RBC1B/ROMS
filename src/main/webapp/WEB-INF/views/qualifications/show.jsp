<%--
    Document   : show
    Created on : 23-Aug-2012, 20:22:52
    Author     : Tina
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="pageTitle" value="Qualification" />
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <div class="container-fluid">
            <h1>Qualification: Hello World!</h1>
            ${qualification}
        <%@ include file="/WEB-INF/views/common/footer.jsp" %>
       </div>
     <script type="text/javascript" src="<c:url value='/javascript/qualifications.js' />" ></script>
    </body>
</html>
