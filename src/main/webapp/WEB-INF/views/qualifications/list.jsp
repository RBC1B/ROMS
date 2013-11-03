<%--
    Document   : QualificationsList
    Created on : 23-Aug-2012, 20:22:25
    Author     : Tina
--%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:url var="formAction" value="/qualifications" />
<html>
    <c:set var="pageTitle" value="Qualifications" />
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
            <h1>Qualifications</h1>
            <hr>
            <table class="table table-bordered table-condensed table-striped table-hover" id="qualification-list">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${qualifications}" var="qualification">
                        <tr>
                            <td>${qualification.name}</td>
                            <td>${qualification.description}</td>
                            <td>
                                <ul class="list-inline">
                                    <li><a class="btn btn-success" href="<c:url value="${qualification.uri}" />">View</a></li>
                                    <sec:authorize access="hasPermission('SKILL', 'EDIT')">
                                        <li><a class="list-action" href="<c:url value="${qualification.editUri}" />">Edit</a></li>
                                    </sec:authorize>
                                </ul>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <sec:authorize access="hasPermission('SKILL', 'EDIT')">
                <br />
                <a class="btn btn-edifice" href="<c:url value="${newUri}" />" />Create new qualification</a>
            </sec:authorize>

            <br />
            <ol class="breadcrumb">
                <li><a href="<c:url value="/" />">Edifice</a></li>
                <li class="active">Qualifications</li>
            </ol>
            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
            <script type="text/javascript" src="<c:url value='/javascript/qualifications.js' />" ></script>
    </body>
</html>
