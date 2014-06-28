<%--
    List the active fixed reports.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:url var="formAction" value="/skills" />
<html>
    <c:set var="pageTitle">Fixed Reports</c:set>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <h1>Fixed Reports</h1>
        <hr />
        <div class="entity-list-results">
            <table class="table table-bordered table-condensed table-striped table-hover" id="fixed-report-list">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${reports}" var="report">
                        <tr>
                            <td><c:out value="${report.name}" /></td>
                            <td><c:out value="${report.description}" /></td>
                            <td>
                                <ul class="list-inline">
                                    <li><a class="btn btn-success" href="<c:url value="${report.htmlUri}" />">Run</a></li>
                                    <li><a class="btn btn-success" href="<c:url value="${report.csvUri}" />">Download</a></li>
                                </ul>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <br />
        <ol class="breadcrumb">
            <li><a href="<c:url value="/" />">Edifice</a></li>
            <li class="active">Reports</li>
        </ol>
        <%@ include file="/WEB-INF/views/common/footer.jsp" %>
    </body>
</html>
