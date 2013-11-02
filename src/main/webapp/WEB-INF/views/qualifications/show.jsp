<%--
    Show the qualification view
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="pageTitle" value="Qualification: ${qualification.name}" />
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <div class="container-fluid">
            <h1>Qualification: ${qualification.name}</h1>
            <hr>
            <dl class="dl-horizontal">
                <dt>Description:</dt>
                <dd>
                    <c:choose>
                        <c:when test="${!empty qualification.description}">${qualification.description}</c:when>
                        <c:otherwise>-</c:otherwise>
                    </c:choose>
                </dd>
            </dl>
            <div class="clearfix"></div>
            <br />
            <ul class="nav nav-tabs">
                <li class="active"><a href="#volunteers" data-toggle="tab">Volunteers</a></li>
            </ul>
            <div class="tab-content">
                <div class="tab-pane active" id="volunteers">
                    <div class="row-fluid">
                        <div id="qualification-volunteer-qualification" data-qualification-id="${qualification.qualificationId}"></div>
                        <table class="table table-bordered table-condensed table-striped table-hover" id="qualification-volunteer-list">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>First Name</th>
                                    <th>Last Name</th>
                                    <th>Action</th>
                                </tr>
                            <thead />
                            <tbody>
                            </tbody>
                            <tfoot>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
            <sec:authorize access="hasPermission('SKILL', 'EDIT')">
                <a href="<c:url value='${qualification.editUri}' />" class="btn btn-edifice">Edit Qualification</a>
            </sec:authorize>
            <div class="clearfix"></div>

            <ol class="breadcrumb">
                <li><a href="<c:url value="/" />">Edifice</a></li>
                <sec:authorize access="hasPermission('SKILL', 'READ')">
                  <li role="menuitem"><a href="<c:url value="/qualifications" />">Qualifications</a></li>
                </sec:authorize>
                <li class="active">${qualification.name}</li>
            </ol>

            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
       </div>
       <%@ include file="/WEB-INF/views/common/mustache-list-actions.jsp" %>
     <script type="text/javascript" src="<c:url value='/javascript/qualifications.js' />" ></script>
    </body>
</html>
