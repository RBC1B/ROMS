<%--
    Display the report results in an html table.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<c:set var="pageTitle">Report - ${report.name}</c:set>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<body>
    <%@ include file="/WEB-INF/views/common/titlebar.jsp"%>
    <h1>Report: <c:out value="${report.name}" /></h1>
    <c:if test="${!empty report.description}">
        <p><c:out value="${report.description}" /></p>
    </c:if>

    <div class="entity-list-results">
        <table class="table table-bordered table-condensed table-striped table-hover" id="fixed-report-result-list">
            <thead>
                <tr>
                    <c:forEach items="${columnNames}" var="columnName">
                        <th><c:out value="${columnName}" /></th>
                    </c:forEach>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${resultRows}" var="resultRow">
                    <tr>
                        <c:forEach items="${resultRow}" var="resultColumn">
                            <td><c:out value="${resultColumn}" /></td>
                        </c:forEach>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="clearfix"></div>
    <br />
    <ol class="breadcrumb">
        <li><a href="<c:url value="/" />">Edifice</a></li>
        <li><a href="<c:url value="/reports/fixed" />">Reports</a></li>
        <li><c:out value="${report.name}" /></li>
    </ol>

    <%@ include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>
