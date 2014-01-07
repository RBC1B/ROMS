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
        <dt>Kingdom hall</dt><dd><a href="<c:url value='${project.kingdomHall.uri}' />"><c:out value="${project.kingdomHall.name}" /></a></dd>
    </c:if>
    <c:if test="${project.address != null}">
        <dt>Address:</dt>
        <dd>
            <address>
                <c:if test="${project.address.street != null}"><c:out value="${project.address.street}" /><br/></c:if>
                <c:if test="${project.address.town != null}"><c:out value="${project.address.town}" /><br/></c:if>
                <c:if test="${project.address.county != null}"><c:out value="${project.address.county}" /><br/></c:if>
                <c:if test="${project.address.postcode != null}"><c:out value="${project.address.postcode}" /><br/></c:if>
            </address>
        </dd>
    </c:if>
    <c:if test="${project.supportingCongregation != null}">
        <dt>Supporting cong.:</dt><dd><c:out value="${project.supportingCongregation}" /></dd>
    </c:if>
    <c:if test="${project.constraints != null}">
        <dt>Constraints:</dt><dd><c:out value="${project.constraints}" /></dd>
    </c:if>
</dl>
