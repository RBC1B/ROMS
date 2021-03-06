<%--
    List circuits.
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="pageTitle">Circuits</c:set>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
            <h1>Circuits</h1>
            <hr />
            <div class="entity-list-results">
                <table class="table table-bordered table-condensed table-striped table-hover" id="circuit-list">
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Overseer</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${circuits}" var="circuit">
                            <tr>
                                <td><c:out value="${circuit.name}" /></td>
                                <td>
                                    <c:if test="${not empty circuit.circuitOverseer}">
                                        <a href="<c:url value='${circuit.circuitOverseer.uri}'/>"><c:out value="${circuit.circuitOverseer.displayName}" /></a>
                                    </c:if>
                                </td>
                                <td>
                                    <ul class="list-inline">
                                        <li><a class="btn btn-success" href="<c:url value="/circuits/${circuit.circuitId}" />">View</a></li>
                                        <sec:authorize access="hasPermission('CIRCUIT', 'EDIT')">
                                            <li><a href="<c:url value="/circuits/${circuit.circuitId}/edit" />">Edit</a></li>
                                        </sec:authorize>
                                    </ul>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <sec:authorize access="hasPermission('CIRCUIT', 'ADD')">
                <hr />
                <div class="entity-list-add-new">
                    <a class="btn btn-edifice" href="<c:url value="/circuits/new" />">Create new circuit</a>
                </div>
            </sec:authorize>
            <br />
            <ol class="breadcrumb">
                <li><a href="<c:url value="/" />">Edifice</a></li>
                <li class="active">Circuits</li>
            </ol>
            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        <script type="text/javascript" src="<c:url value='/javascript/circuits.js' />" ></script>
    </body>
</html>
