<%--
    Document   : circuit
    Created on : 01-Jul-2012, 00:06:12
    Author     : oliver.elder.esq
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="pageTitle" value="Circuit" />
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <div class="container-fluid">
            <div class="media">
                <div class="media-body">
                    <h1 class="media-heading">#${circuit.circuitId}: ${circuit.name}</h1>
                </div>
            </div>
            <br />
            <h3>Circuit Overseer's details: </h3>
            
            <dl class="dl-horizontal">
                <dt>ID:</dt>
                <!-- need to test if the circuit ID is null -->
                <dd>
                    <c:choose>
                        <c:when test="${circuit.circuitOverseer.personId != null}">
                            ${circuit.circuitOverseer.personId}
                        </c:when>
                        <c:otherwise>-</c:otherwise>
                    </c:choose>
                </dd>
                <dt>Forename:</dt>
                <dd>
                    <c:choose>
                        <c:when test="${circuit.circuitOverseer.forename != null}">
                            ${circuit.circuitOverseer.forename}
                        </c:when>
                        <c:otherwise>-</c:otherwise>
                    </c:choose>
                </dd>
                <dt>Middle Name:</dt>
                <dd>
                    <c:choose>
                        <c:when test="${circuit.circuitOverseer.middleName != null}">
                            ${circuit.circuitOverseer.middleName}
                        </c:when>
                        <c:otherwise>-</c:otherwise>
                    </c:choose>
                </dd>
                <dt>Surname:</dt>
                <dd>
                    <c:choose>
                        <c:when test="${circuit.circuitOverseer.surname != null}">
                            ${circuit.circuitOverseer.surname}
                        </c:when>
                        <c:otherwise>-</c:otherwise>
                    </c:choose>
                </dd>
                <dt>E-mail:</dt>
                <dd>
                    <c:choose>
                        <c:when test="${circuit.circuitOverseer.email != null}">
                            <a href="mailto:${circuit.circuitOverseer.email}">${circuit.circuitOverseer.email}</a>
                        </c:when>
                        <c:otherwise>-</c:otherwise>
                    </c:choose>
                </dd>
            </dl>
                 

           
            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        </div>
        <script type="text/javascript" charset="utf-8" src="<c:url value='/javascript/circuits.js' />" ></script>
    </body>
</html>
