<%--
    Document   : VolunteersList
    Created on : 20-Sep-2012, 11:33:13
    Author     : rahulsingh
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="pageTitle" value="Volunteers" />
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
            <h1>Volunteers</h1>
            <hr>
            <div class="entity-list-results">
                <table class="table table-bordered table-condensed table-striped table-hover" id="volunteer-list">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Congregation</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                    </tbody>
                    <tfoot>
                  </tfoot>
                </table>
            </div>
            <div class="entity-list-add-new">
                <a class="btn btn-edifice" href="<c:url value="${newUri}" />">Create new Volunteer</a>
            </div>
            <p>&nbsp;</p>
            <ol class="breadcrumb">
                <li><a href="<c:url value="/" />">Edifice</a></li>
                <li class="active">Volunteers</li>
            </ol>
            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        <%@ include file="/WEB-INF/views/common/mustache-list-actions.jsp" %>
        <script type="text/javascript" src="<c:url value='/javascript/volunteers.js' />" ></script>
    </body>
</html>
