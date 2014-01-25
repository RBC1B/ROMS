<%--
    Author     : oliver
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="pageTitle">Kingdom hall - ${kingdomHall.name}</c:set>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <h1>Kingdom hall: <c:out value="${kingdomHall.name}" /></h1>
        <hr />
        <dl class="dl-horizontal">
            <dt>Address:</dt>
            <dd>
                <address>
                    <c:if test="${!empty kingdomHall.street}"><c:out value="${kingdomHall.street}" /><br /></c:if>
                    <c:if test="${!empty kingdomHall.town}"><c:out value="${kingdomHall.town}" /><br /></c:if>
                    <c:if test="${!empty kingdomHall.county}"><c:out value="${kingdomHall.county}" /><br /></c:if>
                    <c:if test="${!empty kingdomHall.postcode}"><c:out value="${kingdomHall.postcode}" /><br /></c:if>
                </address>
            </dd>
            <dt>Ownership type:</dt>
            <dd>
                <c:choose>
                    <c:when test="${!empty ownershipValue}">
                        ${ownershipValue}
                    </c:when>
                    <c:otherwise>-</c:otherwise>
                </c:choose>
            </dd>
            <dt>Used By:</dt>
            <dd>
                <c:choose>
                    <c:when test="${!empty congregations}">
                        <c:forEach items="${congregations}" var="congregation" varStatus="loop">
                            <a href="<c:url value="${congregation.uri}" />">
                            <c:out value="${congregation.name}" />
                            </a>
                            ${!loop.last ? ', ' : ''}
                        </c:forEach>
                    </c:when>
                    <c:otherwise><i>No congregations meet at this Hall</i></c:otherwise>
                </c:choose>
            </dd>
            <dt>Titleholder:</dt>
            <dd>
                <c:choose>
                    <c:when test="${!empty kingdomHall.titleHoldingCongregation}">
                        <a href="<c:url value="${kingdomHall.titleHoldingCongregation.uri}" />">
                        <c:out value="${kingdomHall.titleHoldingCongregation.name}" />
                        </a>
                    </c:when>
                    <c:otherwise>-</c:otherwise>
                </c:choose>
            </dd>
        </dl>
        <sec:authorize access="hasPermission('KINGDOMHALL', 'EDIT')">
            <hr />
            <div class="entity-list-add-new">
                <a class="btn btn-edifice" href="<c:url value="${kingdomHall.editUri}" />">Edit Kingdom hall</a>
            </div>
        </sec:authorize>

        <br />
        <ol class="breadcrumb">
            <li><a href="<c:url value="/" />">Edifice</a></li>
            <li><a href="<c:url value="/kingdom-halls" />">Kingdom halls</a></li>
            <li class="active">#${kingdomHall.kingdomHallId}: <c:out value="${kingdomHall.name}" /></li>
        </ol>

        <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        <script type="text/javascript" src="<c:url value='/javascript/kingdom-halls.js' />" ></script>
    </body>
</html>
