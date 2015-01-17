<%--
The contents of the contacts tab.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:forEach items="${congregation.contacts}" var="contact">
	<h3>${contact.role}</h3>
    <dl class="dl-horizontal">
        <dt>Name:</dt>
        <dd><a href="<c:url value='${contact.person.uri}' />"><c:out value="${contact.person.displayName}" /></a></dd>
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
