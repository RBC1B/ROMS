<%--
    Document   : list
    Created on : 23-Aug-2012, 20:22:25
    Author     : Tina
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="pageTitle" value="Qualifications" />
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <div class="container-fluid">
            <h1>Qualifications</h1>
            <table class="table table-bordered table-striped table-hover" id="qualification-list">
                <thead>
                    <tr>
                        <th>Qualification</th>
                        <th>Description</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${qualifications}" var="qualification">
                        <tr>
                            <td>${qualification.qualification}</td>
                            <td>${qualification.description}</td>
                            <td>
                                <ul class="inline list-actions">
                                    <li><a class="btn btn-success" href="<c:url value="/qualifications/${qualification.qualification}" />">View</a></li>
                                    <li><a class="list-action" href="<c:url value="/qualifications/${qualification.qualification}/edit" />">Edit</a></li>
                                    <li><a class="list-action" href="delete">Delete</a></li>
                                </ul>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <p>&nbsp;</p>
            <ul class="breadcrumb">
                <li><a href="<c:url value="/" />">ROMS</a> <span class="divider">/</span></li>
                <li class="active">Qualifications</li>
            </ul>
            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
            <script type="text/javascript" charset="utf-8" src="<c:url value='/javascript/qualifications.js' />" ></script>
        </div>
    </body>
</html>
