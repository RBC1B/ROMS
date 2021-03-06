<%--
    Display the congregation details.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="pageTitle">Congregation - ${congregation.name}</c:set>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
            <h1>Congregation: <c:out value="${congregation.name}" /></h1>
            <hr />
            <dl class="dl-horizontal">
                <dt>Number:</dt>
                <dd>
                    <c:choose>
                        <c:when test="${!empty congregation.number}">${congregation.number}</c:when>
                        <c:otherwise>-</c:otherwise>
                    </c:choose>
                </dd>
                <dt>Kingdom hall:</dt>
                <dd>
                    <c:choose>
                        <c:when test="${!empty congregation.kingdomHall}">
                            <a href="<c:url value='${congregation.kingdomHall.uri}' />"><c:out value="${congregation.kingdomHall.name}" /></a>
                        </c:when>
                        <c:otherwise>-</c:otherwise>
                    </c:choose>
                </dd>
                <dt>Circuit:</dt>
                <dd>
                    <c:choose>
                        <c:when test="${!empty congregation.circuit}">
                            <a href="<c:url value='${congregation.circuit.uri}' />"><c:out value="${congregation.circuit.name}" /></a>
                        </c:when>
                        <c:otherwise>-</c:otherwise>
                    </c:choose>
                </dd>
                <dt>RBC region:</dt>
                <dd>
                    <c:choose>
                        <c:when test="${!empty congregation.rbcRegion}">
                            ${congregation.rbcRegion} <c:if test="${!empty congregation.rbcSubRegion}"> - ${congregation.rbcSubRegion}</c:if>
                        </c:when>
                        <c:otherwise>-</c:otherwise>
                    </c:choose>
                </dd>
                <dt>Publishers:</dt>
                <dd>
                    <c:choose>
                        <c:when test="${!empty congregation.publishers}"><c:out value="${congregation.publishers}" /></c:when>
                        <c:otherwise>-</c:otherwise>
                    </c:choose>
                </dd>
                <dt>Attendance:</dt>
                <dd>
                    <c:choose>
                        <c:when test="${!empty congregation.attendance}"><c:out value="${congregation.attendance}" /></c:when>
                        <c:otherwise>-</c:otherwise>
                    </c:choose>
                </dd>
            </dl>
            <div class="clearfix"></div>
            <br />
            <ul class="nav nav-tabs">
                <li class="active"><a href="#contacts" data-toggle="tab">Contacts</a></li>
                <li><a href="#strategy" data-toggle="tab">Strategy</a></li>
                <li><a href="#members" data-toggle="tab">Members</a></li>
            </ul>
            <div class="tab-content">
                <div class="tab-pane active" id="contacts">
                    <div class="row-fluid">
                        <%@ include file="fragments/show-contacts.jsp" %>
                    </div>
                </div>
                <div class="tab-pane" id="strategy">
                    <div class="row-fluid">
                        <%@ include file="fragments/show-strategy.jsp" %>
                    </div>
                </div>
                <div class="tab-pane" id="members">
                    <div class="row-fluid">
                        <%@ include file="fragments/show-members.jsp" %>
                    </div>
                </div>
            </div>
            <sec:authorize access="hasPermission('CONG', 'EDIT')">
                <hr />
                <a href="<c:url value='${congregation.editUri}' />" class="btn btn-edifice">Edit congregation</a>
            </sec:authorize>

            <br />
            <ol class="breadcrumb">
                <li><a href="<c:url value="/" />">Edifice</a></li>
                <li><a href="<c:url value="/congregations" />">Congregations</a></li>
                <li class="active"><c:out value="${congregation.name}" /></li>
            </ol>

            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        <script type="text/javascript" src="<c:url value='/javascript/congregations.js' />" ></script>
    </body>
</html>
