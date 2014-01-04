<%--
The contents of the project details tab.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<dl class="dl-horizontal">
    <dt>Priority:</dt><dd>${project.priority}</dd>
    <c:if test="${project.requestDate != null}">
        <dt>Request date:</dt><dd><fmt:formatDate value="${project.requestDate}" pattern="yyyy-MM-dd" /></dd>
    </c:if>
    <c:if test="${project.visitDate != null}">
        <dt>Visit date:</dt><dd><fmt:formatDate value="${project.visitDate}" pattern="yyyy-MM-dd" /></dd>
    </c:if>
    <c:if test="${project.completedDate != null}">
        <dt>Completed date:</dt><dd><fmt:formatDate value="${project.completedDate}" pattern="yyyy-MM-dd" /></dd>
    </c:if>
    <dt>Estimate cost:</dt><dd>${project.estimateCost}</dd>
    <c:if test="${project.kingdomHall != null}">
        <dt>Kingdom hall</dt><dd><a href="<c:url value='${project.kingdomHall.uri}' />">${project.kingdomHall.name}</a></dd>
    </c:if>
    <c:if test="${project.address != null}">
        <dt>Address:</dt>
        <dd>
            <address>
                <c:if test="${project.address.street != null}">${project.address.street}<br/></c:if>
                <c:if test="${project.address.town != null}">${project.address.town}<br/></c:if>
                <c:if test="${project.address.county != null}">${project.address.county}<br/></c:if>
                <c:if test="${project.address.postcode != null}">${project.address.postcode}<br/></c:if>
            </address>
        </dd>
    </c:if>
    <c:if test="${project.supportingCongregation != null}">
        <dt>Supporting cong.:</dt><dd>${project.supportingCongregation}</dd>
    </c:if>
    <c:if test="${project.constraints != null}">
        <dt>Constraints:</dt><dd>${project.constraints}</dd>
    </c:if>
</dl>
