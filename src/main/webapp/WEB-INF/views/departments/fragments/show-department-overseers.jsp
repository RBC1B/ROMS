<%--
The contents of the department overseers tab.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${department.overseer != null}">
    <h3>Overseer</h3>
    <dl class="dl-horizontal">
        <dt>Name:</dt>
        <dd>
            <a href="<c:url value='${department.overseer.uri}' />">${department.overseer.displayName}</a>
        </dd>
        <dt>Email:</dt>
        <dd>
            <c:if test="${department.overseer.email != null}">
                <a href="mailto:${department.overseer.email}">${department.overseer.email}</a>
            </c:if>
        </dd>
        <c:if test="${department.overseer.telephone != null}">
            <dt>Home phone:</dt>
            <dd>${department.overseer.telephone}</dd>
        </c:if>
        <c:if test="${department.overseer.mobile != null}">
            <dt>Mobile phone:</dt>
            <dd>${department.overseer.mobile}</dd>
        </c:if>
        <c:if test="${department.overseer.workPhone != null}">
            <dt>Work phone:</dt>
            <dd>${department.overseer.workPhone}</dd>
        </c:if>
    </dl>
</c:if>
<c:if test="${department.assistant != null}">
    <h3>Assistant</h3>
    <dl class="dl-horizontal">
        <dt>Name:</dt>
        <dd>
            <a href="<c:url value='${department.assistant.uri}' />">${department.assistant.displayName}</a>
        </dd>
        <dt>Email:</dt>
        <dd>
            <c:if test="${department.assistant.email != null}">
                <a href="mailto:${department.assistant.email}">${department.assistant.email}</a>
            </c:if>
        </dd>
        <c:if test="${department.assistant.telephone != null}">
            <dt>Home phone:</dt>
            <dd>${department.assistant.telephone}</dd>
        </c:if>
        <c:if test="${department.assistant.mobile != null}">
            <dt>Mobile phone:</dt>
            <dd>${department.assistant.mobile}</dd>
        </c:if>
        <c:if test="${department.assistant.workPhone != null}">
            <dt>Work phone:</dt>
            <dd>${department.assistant.workPhone}</dd>
        </c:if>
    </dl>
</c:if>
