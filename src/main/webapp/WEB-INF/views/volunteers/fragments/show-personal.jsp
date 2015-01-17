<%--
The contents of the personal tab.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<dl class="dl-horizontal">
    <dt>Email:</dt>
    <dd>
    <c:choose>
        <c:when test="${!empty volunteer.email}">
            <a href="mailto:${volunteer.email}">${volunteer.email}</a>
        </c:when>
        <c:otherwise>-</c:otherwise>
    </c:choose>
</dd>
<dt>Home phone:</dt>
<dd>
<c:choose>
    <c:when test="${!empty volunteer.telephone}">${volunteer.telephone}</c:when>
    <c:otherwise>-</c:otherwise>
</c:choose>
</dd>
<dt>Mobile phone:</dt>
<dd>
<c:choose>
    <c:when test="${!empty volunteer.mobile}">${volunteer.mobile}</c:when>
    <c:otherwise>-</c:otherwise>
</c:choose>
</dd>
<dt>Work phone:</dt>
<dd>
<c:choose>
    <c:when test="${!empty volunteer.workPhone}">${volunteer.workPhone}</c:when>
    <c:otherwise>-</c:otherwise>
</c:choose>
</dd>
<dt>Address:</dt>
<dd>
<c:choose>
    <c:when test="${!empty volunteer.address}">
        <address>
            <c:if test="${!empty volunteer.address.street}">${volunteer.address.street}<br/></c:if>
            <c:if test="${!empty volunteer.address.town}">${volunteer.address.town}<br/></c:if>
            <c:if test="${!empty volunteer.address.county}">${volunteer.address.county}<br/></c:if>
            <c:if test="${!empty volunteer.address.postcode}">${volunteer.address.postcode}<br/></c:if>
        </address>
    </c:when>
    <c:otherwise>-</c:otherwise>
</c:choose>
</dd>
<dt>Gender:</dt>
<dd>
<c:if test="${!empty volunteer.gender}">
    <c:choose>
        <c:when test="${volunteer.gender == 'F'}">Female</c:when>
        <c:otherwise>Male</c:otherwise>
    </c:choose>
</c:if>
</dd>
<dt>Birth date:</dt>
<dd id="birth-date">
<c:choose>
    <c:when test="${!empty volunteer.birthDate}">
        <fmt:formatDate value="${volunteer.birthDate}" pattern="dd MMM yyyy" />
    </c:when>
    <c:otherwise>-</c:otherwise>
</c:choose>
</dd>
<dt>Marital status:</dt>
<dd>${volunteer.maritalStatus}
<c:if test="${!empty volunteer.spouse}">
    (<a href="<c:url value='${volunteer.spouse.uri}' />" ><c:out value="${volunteer.spouse.displayName}" /></a>)
</c:if>
</dd>
</dl>
<sec:authorize access="hasPermission('VOLUNTEER', 'EDIT')">
    <hr />
    <a class="btn btn-edifice" href="<c:url value='${volunteer.editPersonalUri}' />">Edit</a>
</sec:authorize>

<%@ include file="show-emergency-contacts.jsp" %>
