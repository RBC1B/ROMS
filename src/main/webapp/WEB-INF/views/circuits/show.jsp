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
            <h1 class="media-heading">#${circuit.circuitId}: ${circuit.name}</h1>
            <hr />
            <h3>Circuit Overseer's details: </h3>

            <c:choose>
                <c:when test="${circuit.circuitOverseer != null}">
                    <dl class="dl-horizontal">
                        <!-- Show the name using forename and surname -->
                        <dt>Name:</dt>
                        <dd>
                            <c:choose>
                                <c:when test="${circuit.circuitOverseer.forename != null && circuit.circuitOverseer.surname != null}">
                                    <a href="<c:url value="/persons/${circuit.circuitOverseer.personId}"/>">${circuit.circuitOverseer.forename} ${circuit.circuitOverseer.surname}</a>
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
                        <dt>Home Phone:</dt>
                        <dd>
                            <c:choose>
                                <c:when test="${circuit.circuitOverseer.telephone != null}">
                                    ${circuit.circuitOverseer.telephone}
                                </c:when>
                                <c:otherwise>-</c:otherwise>
                            </c:choose>
                        </dd>
                        <dt>Mobile:</dt>
                        <dd>
                            <c:choose>
                                <c:when test="${circuit.circuitOverseer.mobile != null}">
                                    ${circuit.circuitOverseer.mobile}
                                </c:when>
                                <c:otherwise>-</c:otherwise>
                            </c:choose>
                        </dd>
                    </dl>
                </c:when>
                <c:otherwise>Not set</c:otherwise>
            </c:choose>
            <div class="entity-list-add-new">
                <a class="btn btn-primary" href="<c:url value="/circuits/${circuit.circuitId}/edit" />">Edit Circuit</a>
            </div>
            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        </div>
        <script type="text/javascript" src="<c:url value='/javascript/circuits.js' />" ></script>
    </body>
</html>
