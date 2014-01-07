<%--
The contents of the project contacts tab.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<dl class="dl-horizontal">
    <c:if test="${project.telephone != null}">
        <dt>Telephone:</dt><dd>${project.telephone}</dd>
    </c:if>
    <c:if test="${project.contactPerson != null}">
        <h3>Contact person</h3>
        <dt>Name:</dt>
        <dd><a href="<c:url value='${project.contactPerson.uri}' />"><c:out value="${project.contactPerson.displayName}" /></a></dd>
        <dt>Email:</dt>
        <dd>
            <c:if test="${project.contactPerson.email != null}">
                <a href="mailto:${project.contactPerson.email}">${project.contactPerson.email}</a>
            </c:if>
        </dd>
        <c:if test="${project.contactPerson.telephone != null}">
            <dt>Home phone:</dt><dd>${project.contactPerson.telephone}</dd>
        </c:if>
        <c:if test="${project.contactPerson.mobile != null}">
            <dt>Mobile phone:</dt><dd>${project.contactPerson.mobile}</dd>
        </c:if>
        <c:if test="${project.contactPerson.workPhone != null}">
            <dt>Work phone:</dt><dd>${project.contactPerson.workPhone}</dd>
        </c:if>
    </c:if>
    <c:if test="${project.coordinator != null}">
        <h3>Coordinator</h3>
        <dt>Name:</dt>
        <dd><a href="<c:url value='${project.coordinator.uri}' />"><c:out value="${project.coordinator.displayName}" /></a></dd>
        <dt>Email:</dt>
        <dd>
            <c:if test="${project.coordinator.email != null}">
                <a href="mailto:${project.coordinator.email}">${project.coordinator.email}</a>
            </c:if>
        </dd>
        <c:if test="${project.coordinator.telephone != null}">
            <dt>Home phone:</dt><dd>${project.coordinator.telephone}</dd>
        </c:if>
        <c:if test="${project.coordinator.mobile != null}">
            <dt>Mobile phone:</dt><dd>${project.coordinator.mobile}</dd>
        </c:if>
        <c:if test="${project.coordinator.workPhone != null}">
            <dt>Work phone:</dt><dd>${project.coordinator.workPhone}</dd>
        </c:if>
    </c:if>
</dl>
