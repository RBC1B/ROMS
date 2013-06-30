<%--
    Document   : CongregationShow
    Created on : 06-01-2013 18:12
    Author     : ramindursingh
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
            <h1>Congregation: ${congregation.congregationId} - ${congregation.name}</h1>
            <hr>
            <h3>Congregation's details: </h3>
            <fieldset class="container-fluid">
                <p>Congregation Number</p>
                <p>${congregation.number}</p>
                <p>Kingdom Hall</p>
                <p>${congregation.kingdomHall.name}</p>
                <p>Circuit</p>
                <p>${congregation.circuit.name}</p>
                <p>RBC Region</p>
                <p>${congregation.rbcRegion.name}</p>
                <p>RBC Sub-Region</p>
                <p>${congregation.rbcSubRegion.name}</p>
                <c:forEach items="${congregation.contacts}" var="contact">
                    <p>${roles[contact.congregationRoleId]}</p>
                    <p>${contact.person.surname}, ${contact.person.forename}</p>
                </c:forEach>
                <p>Number of Publishers</p>
                <p>${congregation.publishers}</p>
                <p>Meeting Attendance</p>
                <p>${congregation.attendance}</p>
                <p>Congregation Funds</p>
                <p>${congregation.funds}</p>
                <p>Outstanding Loans</p>
                <p>${congregation.loans}</p>
                <p>Monthly Contributions</p>
                <p>${congregation.monthlyIncome}</p>
                <p>RBC Strategy for Congregation</p>
                <p>${congregation.strategy}</p>
            </fieldset>
            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        </div>
        <script type="text/javascript" src="<c:url value='/javascript/congregation.js' />" ></script>
    </body>
</html>
