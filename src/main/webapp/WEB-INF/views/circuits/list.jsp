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
        <div class="content-container">
            <h1>Circuits</h1>
            <table id="circuit-list">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Overseer</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th>Town</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${circuits}" var="circuit">
                        <tr>
                            <td>${circuit.circuitName}</td>
                            <td>${circuit.circuitOverseer}</td>
                            <td>${circuit.email}</td>
                            <td>${circuit.telephone}</td>
                            <td>${circuit.cotown}</td>
                            <td><a href="<c:url value="/circuits/${circuit.circuitName}" />">View</a>&nbsp;
                                <a href="<c:url value="/circuits/${circuit.circuitName}/edit" />">Edit</a>&nbsp;
                                <a href="delete">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
                <tfoot>
                    <tr>
                        <th><input type="text" name="search_name" value="Search names" class="search_init" /></th>
                        <th><input type="text" name="search_overseer" value="Search overseers" class="search_init" /></th>
                        <th><input type="text" name="search_email" value="Search email" class="search_init" /></th>
                        <th><input type="text" name="search_telephone" value="Search telephone" class="search_init" /></th>
                        <th><input type="text" name="search_town" value="Search town" class="search_init" /></th>
                        <th></th>
                    </tr>
                </tfoot>
            </table>
        </div>
        <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        <script type="text/javascript" charset="utf8" src="<c:url value='/javascript/circuits.js' />" ></script>
    </body>
</html>
