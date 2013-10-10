<%--
    Document   : kingdomhallList
    Created on : 30-Jun-2012, 13:19:18
    Author     : oliver.elder.esq
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="pageTitle" value="Kingdom Halls" />
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
            <h1>Kingdom Halls</h1>
            <hr>
            <div class="entity-list-results">
                <table class="table table-bordered table-condensed table-striped table-hover" id="kingdom-hall-list">
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Street</th>
                            <th>Town</th>
                            <th>Post Code</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${kingdomHalls}" var="kingdomHall">
                            <tr>
                                <td>${kingdomHall.name}</td>
                                <td>${kingdomHall.street}</td>
                                <td>${kingdomHall.town}</td>
                                <td>${kingdomHall.postcode}</td>
                                <td>
                                    <ul class="list-inline">
                                        <li><a class="btn btn-success" href="<c:url value="${kingdomHall.uri}" />">View</a></li>
                                        <li><a class="list-action" href="<c:url value="${kingdomHall.editUri}" />">Edit</a></li>
                                        <li><a class="list-action" href="delete">Delete</a></li>
                                    </ul>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="entity-list-add-new">
                <a class="btn btn-edifice" href="<c:url value="${newUri}" />">Create new kingdom hall</a>
            </div>
            <p>&nbsp;</p>
            <ol class="breadcrumb">
                <li><a href="<c:url value="/" />">Edifice</a></li>
                <li class="active">Kingdom Halls</li>
            </ol>
            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        <script type="text/javascript" src="<c:url value='/javascript/kingdom-halls.js' />" ></script>
    </body>
</html>
