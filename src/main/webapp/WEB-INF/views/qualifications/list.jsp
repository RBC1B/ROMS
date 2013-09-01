<%--
    Document   : list
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
        <div class="container-fluid">
            <h1>Qualifications</h1>
            <hr>
            <table class="table table-bordered table-condensed table-striped table-hover" id="qualification-list">
                <thead>
                    <tr>
                        <th>Qualification ID</th>
                        <th>Qualification</th>
                        <th>Description</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${qualifications}" var="qualification">
                        <tr>
                            <td>${qualification.qualificationId}</td>
                            <td>${qualification.name}</td>
                            <td>${qualification.description}</td>
                            <td>
                                <ul class="inline list-actions">
                                    <li><a class="list-action" href="<c:url value="/qualifications/${qualification.qualificationId}/edit" />">Edit</a></li>
                                    <li>
                                        <form:form method="DELETE" action="${formAction}">
                                            <input type="hidden" name="qualificationId" value="${qualification.qualificationId}" />
                                            <input type="submit" value="Delete" class="btn btn-primary" />
                                        </form:form>
                                    </li>
                                </ul>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div id="new-qualification">
                <a class="btn btn-primary" href="qualifications/new" />Create new qualification</a>
            </div>

            <p>&nbsp;</p>

            <ul class="breadcrumb">
                <li><a href="<c:url value="/" />">ROMS</a> <span class="divider">/</span></li>
                <li class="active">Skills</li>
            </ul>
            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
            <script type="text/javascript" src="<c:url value='/javascript/qualifications.js' />" ></script>
        </div>
    </body>
</html>
