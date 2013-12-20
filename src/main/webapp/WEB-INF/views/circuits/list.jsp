<%--
    Document   : circuit
    Created on : 30-Jun-2012, 13:19:18
    Author     : oliver.elder.esq
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="pageTitle" value="Circuits" />
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
            <h1>Circuits</h1>
            <hr>
            <div class="entity-list-results">
                <table class="table table-bordered table-condensed table-striped table-hover" id="circuit-list">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${circuits}" var="circuit">
                            <tr>
                                <td>${circuit.circuitId}</td>
                                <td>${circuit.name}</td>
                                <td>
                                    <ul class="list-inline">
                                        <li><a class="btn btn-success" href="<c:url value="/circuits/${circuit.circuitId}" />">View</a></li>
                                        <sec:authorize access="hasPermission('CIRCUIT', 'EDIT')">
                                            <li><a class="list-action" href="<c:url value="/circuits/${circuit.circuitId}/edit" />">Edit</a></li>
                                        </sec:authorize>
                                    </ul>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <hr>
            <sec:authorize access="hasPermission('CIRCUIT', 'ADD')">
                <div class="entity-list-add-new">
                    <a class="btn btn-edifice" href="<c:url value="/circuits/new" />">Create new circuit</a>
                </div>
                <p>&nbsp;</p>
            </sec:authorize>
            <ol class="breadcrumb">
                <li><a href="<c:url value="/" />">Edifice</a></li>
                <li class="active">Circuits</li>
            </ol>
            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        <script type="text/javascript" src="<c:url value='/javascript/circuits.js' />" ></script>
    </body>
</html>
