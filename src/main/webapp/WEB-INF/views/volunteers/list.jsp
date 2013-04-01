<%--
    Document   : list
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
        <div class="container-fluid">
            <h1>Volunteers</h1>
            <div class="entity-list-results">
                <table class="table table-bordered table-striped table-hover" id="volunteer-list">
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
                <a class="btn btn-primary" href="<c:url value="/volunteers/new" />">Create new Volunteer</a>
            </div>
            <p>&nbsp;</p>
            <ul class="breadcrumb">
                <li><a href="<c:url value="/" />">ROMS</a> <span class="divider">/</span></li>
                <li class="active">Volunteers</li>
            </ul>
            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        </div>
        <script id="list-action" type="text/html" charset="utf-8">
            <ul class="inline list-actions">
                {{#uri}}<li><a class="btn btn-success" href="<c:url value="{{uri}}" />">View</a></li>{{/uri}}
                {{#editUri}}<li><a class="list-action" href="<c:url value="{{editUri}}" />">Edit</a></li>{{/editUri}}
                {{#deleteUri}}<li><a class="list-action" href="<c:url value="{{deleteUri}}" />">Delete</a></li>{{/deleteUri}}
            </ul>
        </script>
        <script type="text/javascript" charset="utf-8" src="<c:url value='/javascript/volunteers.js' />" ></script>
    </body>
</html>
