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
        <div class="container">
            <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
            <h1>Circuits</h1>
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
                                <td><a href="<c:url value="/circuits/${circuit.circuitId}" />">View</a>&nbsp;
                                    <a href="<c:url value="/circuits/${circuit.circuitId}/edit" />">Edit</a>&nbsp;
                                    <a href="delete">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                        <tr>
                            <th></th>
                            <th><input type="text" name="search_name" value="Search names" class="search_init" /></th>
                            <th></th>
                        </tr>
                    </tfoot>
                </table>
            </div>
            <div class="entity-list-add-new">
                <a href="<c:url value="/circuits/new" />">Create new circuit</a>
            </div>
            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        </div>
        <script type="text/javascript" charset="utf8" src="<c:url value='/javascript/circuits.js' />" ></script>
    </body>
</html>
