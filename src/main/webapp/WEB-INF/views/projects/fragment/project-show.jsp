<%--
    Document   : project-show - shows project information
    Created on : Nov 24, 2014, 9:02:58 AM
    Author     : ramindursingh
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<dl class="dl-horizontal">
    <dt>Kingdom Hall</dt>
    <dd>
        <a href='<c:url value="${project.kingdomHall.uri}" />' >
            <c:out value="${project.kingdomHall.name}" />
        </a>
    </dd>
    <dt>Request Date</dt><dd>${project.requestDate}</dd>
    <dt>Completed Date:</dt><dd>${project.completedDate}</dd>
    <dt>Minor Work</dt><dd>${project.minorWork}</dd>
    <dt>Coordinator</dt>
    <dd>
        <a href='<c:url value="${project.coordinator.uri}" />' >
            <c:out value="${project.coordinator.name}" />
        </a>
    </dd>
</dl>
<sec:authorize access="hasPermission('PROJECT', 'EDIT')">
    <a href='<c:url value="${project.editUri}" />' class="btn btn-edifice">Edit Project</a>
</sec:authorize>
