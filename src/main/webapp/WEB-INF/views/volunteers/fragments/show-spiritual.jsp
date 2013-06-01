<%--
The contents of the spiritual tab.
Author: oliver.elder.esq
--%>
<dl class="dl-horizontal">
    <dt>Congregation:</dt>
    <dd>
        <c:choose>
            <c:when test="${volunteer.congregation != null}">
                <a href="<c:url value='${volunteer.congregation.uri}' />">${volunteer.congregation.name}</a>
            </c:when>
            <c:otherwise>-</c:otherwise>
        </c:choose>
    </dd>
    <dt>Date of Baptism:</dt>
    <dd>
        <c:choose>
            <c:when test="${volunteer.baptismDate != null}">
                <fmt:formatDate value="${volunteer.baptismDate}" pattern="dd MMM yyyy" />
            </c:when>
            <c:otherwise>-</c:otherwise>
        </c:choose>
    </dd>
    <dt>Full time service:</dt>
    <dd>
        <c:choose>
            <c:when test="${volunteer.fulltime != null}">${volunteer.fulltime}</c:when>
            <c:otherwise>-</c:otherwise>
        </c:choose>
    </dd>
    <dt>Appointment:</dt>
    <dd>
        <c:choose>
            <c:when test="${volunteer.appointment != null}">${volunteer.appointment}</c:when>
            <c:otherwise>-</c:otherwise>
        </c:choose>
    </dd>
</dl>
<sec:authorize access="hasPermission('VOLUNTEER', 'EDIT')">
    <a class="btn btn-primary" href="<c:url value='${volunteer.editSpiritualUri}' />">Edit</a>
</sec:authorize>
