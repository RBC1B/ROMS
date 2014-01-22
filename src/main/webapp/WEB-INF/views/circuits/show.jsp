<%--
    Show the circuit details.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="pageTitle">Circuit - ${circuit.name}</c:set>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
            <h1>#${circuit.circuitId}: <c:out value="${circuit.name}" /></h1>
            <hr />
            <h3>Circuit Overseer</h3>
            <c:choose>
                <c:when test="${not empty circuit.circuitOverseer}">
                    <dl class="dl-horizontal">
                        <!-- Show the name using forename and surname -->
                        <dt>Name:</dt>
                        <dd>
                            <a href="<c:url value="${circuit.circuitOverseer.uri}"/>"><c:out value="${circuit.circuitOverseer.displayName}" /></a>
                        </dd>
                        <dt>Email:</dt>
                        <dd>
                            <c:choose>
                                <c:when test="${circuit.circuitOverseer.email != null}">
                                    <a href="mailto:${circuit.circuitOverseer.email}">${circuit.circuitOverseer.email}</a>
                                </c:when>
                                <c:otherwise>-</c:otherwise>
                            </c:choose>
                        </dd>
                        <dt>Home phone:</dt>
                        <dd>
                            <c:choose>
                                <c:when test="${circuit.circuitOverseer.telephone != null}">
                                    ${circuit.circuitOverseer.telephone}
                                </c:when>
                                <c:otherwise>-</c:otherwise>
                            </c:choose>
                        </dd>
                        <dt>Mobile phone:</dt>
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
            <sec:authorize access="hasPermission('CIRCUIT', 'EDIT')">
                <hr />
                <div class="entity-list-add-new">
                    <a class="btn btn-edifice" href="<c:url value="${circuit.editUri}" />">Edit circuit</a>
                </div>
            </sec:authorize>
            <br />
            <ol class="breadcrumb">
                <li><a href="<c:url value="/" />">Edifice</a></li>
                <li><a href="<c:url value="/circuits" />">Circuits</a></li>
                <li class="active">#${circuit.circuitId}: <c:out value="${circuit.name}" /></li>
            </ol>

            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        <script type="text/javascript" src="<c:url value='/javascript/circuits.js' />" ></script>
    </body>
</html>
