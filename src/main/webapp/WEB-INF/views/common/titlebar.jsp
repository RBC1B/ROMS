<div id="menuFrame" >
    <ul class="menu" >
        <sec:authorize access="hasPermission('Circuit', 'READ')"><li><a href="<c:url value="/circuits" />">Circuits</a></li></sec:authorize>
        <sec:authorize access="hasPermission('KingdomHall', 'READ')"><li><a href="<c:url value="/kingdom-halls" />">Kingdom Halls</a></li></sec:authorize>
        <sec:authorize access="hasPermission('Congregation', 'READ')"><li><a href="<c:url value="/congregations" />">Congregations</a></li></sec:authorize>
        <sec:authorize access="hasPermission('Volunteer', 'READ')"><li><a href="<c:url value="/volunteers" />">Volunteers</a></li></sec:authorize>
        <sec:authorize access="hasPermission('Qualification', 'READ')"><li><a href="<c:url value="/qualifications" />">Qualifications</a></li></sec:authorize>
    </ul>
</div>
