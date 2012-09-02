<div id="menuFrame" >
    <ul class="menu" >
        <sec:authorize access="hasPermission('CIRCUIT', 'READ')"><li><a href="<c:url value="/circuits" />">Circuits</a></li></sec:authorize>
        <sec:authorize access="hasPermission('KINGDOMHALL', 'READ')"><li><a href="<c:url value="/kingdom-halls" />">Kingdom Halls</a></li></sec:authorize>
        <sec:authorize access="hasPermission('CONG', 'READ')"><li><a href="<c:url value="/congregations" />">Congregations</a></li></sec:authorize>
        <sec:authorize access="hasPermission('VOLUNTEER', 'READ')"><li><a href="<c:url value="/volunteers" />">Volunteers</a></li></sec:authorize>
        <sec:authorize access="hasPermission('SKILL', 'READ')"><li><a href="<c:url value="/qualifications" />">Qualifications</a></li></sec:authorize>
    </ul>
</div>
