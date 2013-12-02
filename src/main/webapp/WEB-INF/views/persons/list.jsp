<%--
    Document   : PeopleList
    Created on : 20-Sep-2012, 11:33:13
    Author     : rahulsingh
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="pageTitle" value="People" />
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <h1>People</h1>
        <hr>
        <div class="entity-list-results">
            <c:set var="editEnabled" value="false" />
            <sec:authorize access="hasPermission('VOLUNTEER', 'EDIT')">
                <c:set var="editEnabled" value="true" />
            </sec:authorize>
            <table
                class="table table-bordered table-condensed table-striped table-hover"
                id="person-list"
                data-edit-enabled="${editEnabled}"
                <thead>
                    <tr>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Congregation</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
        <p>&nbsp;</p>
        <ol class="breadcrumb">
            <li><a href="<c:url value="/" />">Edifice</a></li>
            <li class="active">Persons</li>
        </ol>
        <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        <%@ include file="/WEB-INF/views/common/mustache-list-actions.jsp" %>
        <script type="text/javascript" src="<c:url value='/javascript/persons.js' />" ></script>
    </body>
</html>
