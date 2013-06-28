<%--
    Document   : CongregationList
    Created on : 06-01-2013 18:12
    Author     : Ramindur Singh
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:url var="formAction" value="/congregations" />
<html>
    <c:set var="pageTitle" value="Congregation" />
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <div class="container-fluid">
            <h1>Congregations</h1>
            <hr>
            <div class="entity-list-results">
                <table class="table table-bordered table-striped table-hover" id="congregation-list">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Congregation</th>
                            <th>Kingdom Hall</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${congregations}" var="congregation">
                            <tr>
                                <td>${congregation.congregationId}</td>
                                <td>${congregation.name}</td>
                                <td>${congregation.kingdomHall.name}</td>
                                <td>
                                    <ul class="inline list-actions">
                                        <li><a class="btn btn-success" href="<c:url value="/congregations/${congregation.congregationId}" />">View</a></li>
                                        <li><a href="<c:url value="/congregations/${congregation.congregationId}/edit" />">Edit</a></li>
                                        <li>
                                            <form:form method="DELETE" action="${formAction}">
                                                <input type="hidden" name="congregatonId" value="${congregation.congregationId}" />
                                                <input type="submit" value="Delete" class="btn btn-mini" />
                                            </form:form>
                                        </li>
                                    </ul>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="entity-list-add-new">
                <a class="btn btn-primary" href="<c:url value="/congregation/new" />">Create new congregation</a>
            </div>
            <p>&nbsp;</p>
            <ul class="breadcrumb">
                <li><a href="<c:url value="/" />">ROMS</a> <span class="divider">/</span></li>
                <li class="active">Congregations</li>
            </ul>
            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        </div>
        <script type="text/javascript" src="<c:url value='/javascript/congregations.js' />" ></script>
    </body>
</html>
