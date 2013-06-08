<%--
The contents of the personal tab.
Author: oliver.elder.esq
--%>

<dl class="dl-horizontal">
    <dt>Email:</dt>
    <dd>
    <c:choose>
        <c:when test="${volunteer.email != null}">
            <a href="mailto:${volunteer.email}">${volunteer.email}</a>
        </c:when>
        <c:otherwise>-</c:otherwise>
    </c:choose>
</dd>
<dt>Home phone:</dt>
<dd>
<c:choose>
    <c:when test="${volunteer.telephone != null}">${volunteer.telephone}</c:when>
    <c:otherwise>-</c:otherwise>
</c:choose>
</dd>
<dt>Mobile phone:</dt>
<dd>
<c:choose>
    <c:when test="${volunteer.mobile != null}">${volunteer.mobile}</c:when>
    <c:otherwise>-</c:otherwise>
</c:choose>
</dd>
<dt>Work phone:</dt>
<dd>
<c:choose>
    <c:when test="${volunteer.workPhone != null}">${volunteer.workPhone}</c:when>
    <c:otherwise>-</c:otherwise>
</c:choose>
</dd>
<dt>Address:</dt>
<dd>
<c:choose>
    <c:when test="${volunteer.address != null}">
        <address>
            <c:if test="${volunteer.address.street != null}">${volunteer.address.street}<br/></c:if>
            <c:if test="${volunteer.address.town != null}">${volunteer.address.town}<br/></c:if>
            <c:if test="${volunteer.address.county != null}">${volunteer.address.county}<br/></c:if>
            <c:if test="${volunteer.address.postcode != null}">${volunteer.address.postcode}<br/></c:if>
        </address>
    </c:when>
    <c:otherwise>-</c:otherwise>
</c:choose>
</dd>
<dt>Gender:</dt>
<dd>
<c:if test="${volunteer.gender != null}">
    <c:choose>
        <c:when test="${volunteer.gender == 'F'}">Female</c:when>
        <c:otherwise>Male</c:otherwise>
    </c:choose>
</c:if>
</dd>
<dt>Birth date:</dt>
<dd>
<c:choose>
    <c:when test="${volunteer.birthDate != null}">
        <fmt:formatDate value="${volunteer.birthDate}" pattern="dd MMM yyyy" />
    </c:when>
    <c:otherwise>-</c:otherwise>
</c:choose>
</dd>
<dt>Marital Status:</dt>
<dd>${volunteer.maritalStatus}
<c:if test="${volunteer.spouse != null}">
    (<a href="<c:url value='${volunteer.spouse.uri}' />" >${volunteer.spouse.displayName}</a>)
</c:if>
</dd>
</dl>
<h3>Emergency Contact</h3>
<c:choose>
    <c:when test="${volunteer.emergencyContact != null}">
        <dl class="dl-horizontal">
            <dt>Name:</dt>
            <dd>
                <a href="<c:url value='${volunteer.emergencyContact.uri}' />">${volunteer.emergencyContact.displayName}</a>
            </dd>
            <dt>Relationship:</dt><dd>${volunteer.emergencyContactRelationship}</dd>
            <c:if test="${volunteer.emergencyContact.email != null}">
                <dt>Email:</dt>
                <dd>
                    <a href="mailto:${volunteer.emergencyContact.email}">${volunteer.emergencyContact.email}</a>
                </dd>
            </c:if>
            <c:if test="${volunteer.emergencyContact.telephone != null}">
                <dt>Home phone:</dt><dd>${volunteer.emergencyContact.telephone}</dd>
            </c:if>
            <c:if test="${volunteer.emergencyContact.mobile != null}">
                <dt>Mobile phone:</dt><dd>${volunteer.emergencyContact.mobile}</dd>
            </c:if>
            <c:if test="${volunteer.emergencyContact.workPhone != null}">
                <dt>Work phone:</dt><dd>${volunteer.emergencyContact.workPhone}</dd>
            </c:if>
        </dl>
    </c:when>
    <c:otherwise>
        Not set
    </c:otherwise>
</c:choose>
