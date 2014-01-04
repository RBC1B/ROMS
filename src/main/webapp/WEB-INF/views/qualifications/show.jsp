<%--
    Show the qualification view
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="pageTitle" value="Qualification: <c:out value='${qualification.name}' />" />
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <h1>Qualification: <c:out value="${qualification.name}" /></h1>
        <hr />
        <dl class="dl-horizontal">
            <dt>Description:</dt>
            <dd>
                <c:choose>
                    <c:when test="${!empty qualification.description}"><c:out value="${qualification.description}" /></c:when>
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
                                <th>Forename</th>
                                <th>Surname</th>
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
            <hr />
            <a href="<c:url value='${qualification.editUri}' />" class="btn btn-edifice">Edit Qualification</a>
        </sec:authorize>
        <div class="clearfix"></div>

        <br />
        <ol class="breadcrumb">
            <li><a href="<c:url value="/" />">Edifice</a></li>
            <li role="menuitem"><a href="<c:url value="/qualifications" />">Qualifications</a></li>
            <li class="active"><c:out value="${qualification.name}" /></li>
        </ol>

        <%@ include file="/WEB-INF/views/common/footer.jsp" %>
       <%@ include file="/WEB-INF/views/common/mustache-list-actions.jsp" %>
     <script type="text/javascript" src="<c:url value='/javascript/qualifications.js' />" ></script>
    </body>
</html>
