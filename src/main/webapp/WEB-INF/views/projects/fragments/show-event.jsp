<%--
The contents of the project event.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="list-group-item">
    <h4 class="list-group-item-heading">
        <fmt:formatDate value="${event.createTime}" pattern="yyyy-MM-dd HH:mm" />
        ${event.type}
        <a class="a-event-created-by" href="${event.createdBy.uri}"
           data-toggle="tooltip" data-original-title="${event.createdBy.displayName}">
            ${event.createdBy.initials}
        </a>
    </h4>
    <c:if test="${!empty event.comments}">
        <p class="list-group-item-text"><c:out value="${event.comments}" /></p>
    </c:if>
</div>
