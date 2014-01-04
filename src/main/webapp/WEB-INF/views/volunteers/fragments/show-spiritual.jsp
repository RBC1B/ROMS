<%--
The contents of the spiritual tab.
Author: oliver.elder.esq
--%>
<dl class="dl-horizontal">
    <dt>Congregation:</dt>
    <dd>
        <c:choose>
            <c:when test="${!empty volunteer.congregation}">
                <a href="<c:url value='${volunteer.congregation.uri}' />">${volunteer.congregation.name}</a>
            </c:when>
            <c:otherwise>-</c:otherwise>
        </c:choose>
    </dd>
    <dt>Date of baptism:</dt>
    <dd>
        <c:choose>
            <c:when test="${!empty volunteer.baptismDate}">
                <fmt:formatDate value="${volunteer.baptismDate}" pattern="dd MMM yyyy" />
            </c:when>
            <c:otherwise>-</c:otherwise>
        </c:choose>
    </dd>
    <dt>Full time service:</dt>
    <dd>
        <c:choose>
            <c:when test="${!empty volunteer.fulltime}">${volunteer.fulltime}</c:when>
            <c:otherwise>-</c:otherwise>
        </c:choose>
    </dd>
    <dt>Appointment:</dt>
    <dd>
        <c:choose>
            <c:when test="${!empty volunteer.appointment}">${volunteer.appointment}</c:when>
            <c:otherwise>-</c:otherwise>
        </c:choose>
    </dd>
</dl>
<sec:authorize access="hasPermission('VOLUNTEER', 'EDIT')">
    <hr />
    <a class="btn btn-edifice" href="<c:url value='${volunteer.editSpiritualUri}' />">Edit</a>
</sec:authorize>
