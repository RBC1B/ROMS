<%--
    Document   : CongregationList
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
            <h1>Congregations</h1>
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
                                <td><a class="btn btn-success" href="<c:url value="/circuits/${circuit.circuitId}" />">View</a>&nbsp;&nbsp;&nbsp;&nbsp;&#124;&nbsp;
                                    <a href="<c:url value="/circuits/${circuit.circuitId}/edit" />">Edit</a>&nbsp;&nbsp;&nbsp;&nbsp;&#124;&nbsp;
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
                <a class="btn btn-primary" href="<c:url value="/circuits/new" />">Create new circuit</a>
            </div>
            <p>&nbsp;</p>
            <ul class="breadcrumb">
              <li><a href="<c:url value="/" />">ROMS</a> <span class="divider">/</span></li>
              <li class="active">Circuits</li>
            </ul>
            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        </div>
        <script type="text/javascript" src="<c:url value='/javascript/circuits.js' />" ></script>
    </body>
</html>
