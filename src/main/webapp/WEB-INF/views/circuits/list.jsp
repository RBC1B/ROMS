<%--
    Document   : circuit
    Created on : 30-Jun-2012, 13:19:18
    Author     : oliver.elder.esq
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="pageTitle" value="Circuits" />
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <div class="container-fluid">
            <h1>Circuits</h1>
            <br>
            <div class="entity-list-results">
                <table class="table table-bordered table-striped table-hover" id="circuit-list">
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
                                    <ul class="inline list-actions">
                                        <li><a class="btn btn-success" href="<c:url value="/circuits/${circuit.circuitId}" />">View</a></li>
                                        <li><a class="list-action" href="<c:url value="/circuits/${circuit.circuitId}/edit" />">Edit</a></li>
                                        <li><a class="list-action" href="delete">Delete</a></li>
                                    </ul>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="entity-list-add-new">
                <a class="btn btn-primary" href="<c:url value="/circuits/new" />">Create new circuit</a>
            </div>
            <p>&nbsp;</p>
            <ul class="breadcrumb">
                <li><a href="<c:url value="/" />">ROMS</a> <span class="divider">/</span></li>
                <li class="active">Circuits</li>
            </ul>
            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        </div>
        <script type="text/javascript" charset="utf-8" src="<c:url value='/javascript/circuits.js' />" ></script>
    </body>
</html>
