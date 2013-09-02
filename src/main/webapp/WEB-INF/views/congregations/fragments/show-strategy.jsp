<%--
The contents of the strategy tab.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<dl class="dl-horizontal">
    <dt>Funds:</dt>
    <dd>
        <c:choose>
            <c:when test="${!empty congregation.funds}">${congregation.funds}</c:when>
            <c:otherwise>-</c:otherwise>
        </c:choose>
    </dd>
    <dt>Loans:</dt>
    <dd>
        <c:choose>
            <c:when test="${!empty congregation.loans}">${congregation.loans}</c:when>
            <c:otherwise>-</c:otherwise>
        </c:choose>
    </dd>
    <dt>Monthly income:</dt>
    <dd>
        <c:choose>
            <c:when test="${!empty congregation.monthlyIncome}">${congregation.monthlyIncome}</c:when>
            <c:otherwise>-</c:otherwise>
        </c:choose>
    </dd>
    <dt>Strategy:</dt>
    <dd>
        <c:choose>
            <c:when test="${!empty congregation.strategy}">${congregation.strategy}</c:when>
            <c:otherwise>-</c:otherwise>
        </c:choose>
    </dd>
</dl>
