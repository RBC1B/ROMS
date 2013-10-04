<%-- 
    Document   : list
    Created on : Oct 3, 2013, 12:05:09 PM
    Author     : ramindursingh
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:url var="formAction" value="/personchanges" />
<html>
    <c:set var="pageTitle" value="Changes/Updates to Person Information" />
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <div class="container">
            <h1>Person Information Changes/Updates</h1>
            <hr>
            <div class="entity-list-results">
                <table class="table table-bordered table-condensed table-striped table-hover" id="personchange-list">
                    <thead>
                        <tr>
                            <th>Person ID</th>
                            <th>Surname</th>
                            <th>Forename</th>
                            <th>Email</th>
                            <th>Forms Updated</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${personChanges}" var="personChange">
                            <tr>
                                <td>${personChange.personChangeId}</td>
                                <td>${personChange.surname}</a></td>
                                <td>${personChange.forename}</td>
                                <td>${personChange.email}</td>
                                <td>
                                    <ul class="list-inline">
                                        <li><a class="list-action" href="<c:url value="${personChange.editUri}" />">Paper Work Updated</a></li>
                                    </ul>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <p>&nbsp;</p>
            <ul class="breadcrumb">
                <li><a href="<c:url value="/" />">Edifice</a> <span class="divider">/</span></li>
                <li class="active">Person Information Change</li>
            </ul>
            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        </div>
        <script type="text/javascript" src="<c:url value='/javascript/personChange.js' />" ></script>
    </body>
</html>
