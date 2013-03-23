<%--
    Document   : list
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
        <div class="container-fluid">
            <h1>People</h1>
            <div class="entity-list-results">
                <table class="table table-bordered table-striped table-hover" id="person-list">
                    <thead>

                        <tr>
                            <th><input type="text" name="search_firstname" value="Search first name" class="search_init" /></th>
                            <th><input type="text" name="search_lastname" value="Search last name" class="search_init" /></th>
                            <th><input type="text" name="search_cong" value="Search congregation" class="search_init" /></th>
                        </tr>
                        <tr>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Congregation</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${persons}" var="person">
                            <tr>
                                <td>${person.forename}</td>
                                <td>${person.surname}</td>
                                <td>${person.congregation.name}</td>
                                <td><a class="btn btn-success" href="<c:url value="${person.uri}" />">View</a>&nbsp;&nbsp;&nbsp;&nbsp;&#124;&nbsp;
                                    <a href="<c:url value="${person.editUri}" />">Edit</a>&nbsp;&nbsp;&nbsp;&nbsp;&#124;&nbsp;
                                    <a href="delete">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                  </tfoot>
                </table>
            </div>

            <p>&nbsp;</p>
            <ul class="breadcrumb">
                <li><a href="<c:url value="/" />">ROMS</a> <span class="divider">/</span></li>
                <li class="active">Persons</li>
            </ul>
            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        </div>
        <script type="text/javascript" charset="utf-8" src="<c:url value='/javascript/persons.js' />" ></script>
    </body>
</html>
