<%--
The contents of the personal tab.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<dd>
<c:choose>
    <c:when test="${!empty volunteer.birthDate}">
        <fmt:formatDate value="${volunteer.birthDate}" pattern="dd MMM yyyy" />
    </c:when>
    <c:otherwise>-</c:otherwise>
</c:choose>
</dd>
<dt>Marital Status:</dt>
<dd>${volunteer.maritalStatus}
<c:if test="${!empty volunteer.spouse}">
    (<a href="<c:url value='${volunteer.spouse.uri}' />" >${volunteer.spouse.displayName}</a>)
</c:if>
</dd>
</dl>
<sec:authorize access="hasPermission('VOLUNTEER', 'EDIT')">
    <a class="btn btn-edifice" href="<c:url value='${volunteer.editPersonalUri}' />">Edit</a>
</sec:authorize>

<h3>Emergency Contact</h3>
<c:choose>
    <c:when test="${!empty volunteer.emergencyContact}">
        <dl class="dl-horizontal">
            <dt>Name:</dt>
            <dd>
                <a href="<c:url value='${volunteer.emergencyContact.uri}' />">${volunteer.emergencyContact.displayName}</a>
            </dd>
            <dt>Relationship:</dt><dd>${volunteer.emergencyContactRelationship}</dd>
            <c:if test="${!empty volunteer.emergencyContact.email}">
                <dt>Email:</dt>
                <dd>
                    <a href="mailto:${volunteer.emergencyContact.email}">${volunteer.emergencyContact.email}</a>
                </dd>
            </c:if>
            <c:if test="${!empty volunteer.emergencyContact.telephone}">
                <dt>Home phone:</dt><dd>${volunteer.emergencyContact.telephone}</dd>
            </c:if>
            <c:if test="${!empty volunteer.emergencyContact.mobile}">
                <dt>Mobile phone:</dt><dd>${volunteer.emergencyContact.mobile}</dd>
            </c:if>
            <c:if test="${!empty volunteer.emergencyContact.workPhone}">
                <dt>Work phone:</dt><dd>${volunteer.emergencyContact.workPhone}</dd>
            </c:if>
        </dl>
    </c:when>
    <c:otherwise>
        Not set
    </c:otherwise>
</c:choose>
