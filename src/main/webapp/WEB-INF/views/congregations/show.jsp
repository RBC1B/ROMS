<%--
    Document   : CongregationShow
    Created on : 06-01-2013 18:12
    Author     : ben.read
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="pageTitle" value="Congregation" />
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
            <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <div class="container-fluid">
            <h1>Circuit: ${congregation.congregationId} - ${congregation.name}</h1>
            <h3>Circuit Overseer's details: </h3>
            Forename: ${congregation.circuitOverseer.forename}<br />
            Surname:  ${congregation.circuitOverseer.surname}<br />
            Email:    ${congregation.circuitOverseer.email} <br />
            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        </div>    
      <script type="text/javascript" charset="utf8" src="<c:url value='/javascript/congregation.js' />" ></script>
    </body>
</html>
