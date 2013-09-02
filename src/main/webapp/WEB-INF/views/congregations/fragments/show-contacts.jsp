<%--
The contents of the contacts tab.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:forEach items="${congregation.contacts}" var="contact">
    <dl class="dl-horizontal">
        <h3><a href="${contact.person.uri}">${contact.person.displayName}</a> (${contact.role})</h3>
        <dt>Email:</dt>
        <dd>
            <c:choose>
                <c:when test="${!empty contact.person.email}">
                    <a href="mailto:${contact.person.email}">${contact.person.email}</a>
                </c:when>
                <c:otherwise>-</c:otherwise>
            </c:choose>
        </dd>
        <dt>Home phone:</dt>
        <dd>
            <c:choose>
                <c:when test="${!empty contact.person.telephone}">${contact.person.telephone}</c:when>
                <c:otherwise>-</c:otherwise>
            </c:choose>
        </dd>
        <dt>Mobile phone:</dt>
        <dd>
            <c:choose>
                <c:when test="${!empty contact.person.mobile}">${contact.person.mobile}</c:when>
                <c:otherwise>-</c:otherwise>
            </c:choose>
        </dd>
        <dt>Work phone:</dt>
        <dd>
            <c:choose>
                <c:when test="${!empty contact.person.workPhone}">${contact.person.workPhone}</c:when>
                <c:otherwise>-</c:otherwise>
            </c:choose>
        </dd>
    </dl>
</c:forEach>
