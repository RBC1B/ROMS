<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!-- data attribute is used to determine the relative paths for ajax requests -->
<div id="relative-path" data-relative-path="<c:url value="/" />"></div>
<nav class="navbar navbar-inverse navbar-static-top" role="navbar">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">MENU
        <span class="sr-only">Toggle navigation</span>
    </button>
    <a class="navbar-brand" href="<c:url value="/" />"><img src="<c:url value='/images/logo-brand.png' />" alt="edifice"></a>
    <!-- Everything you want minimised at 940px or less, place within here -->
    <div class="collapse navbar-collapse navbar-ex1-collapse">
        <ul class="nav navbar-nav">
            <sec:authorize access="hasPermission('CIRCUIT', 'READ') or hasPermission('KINGDOMHALL', 'READ') or hasPermission('CONG', 'READ')">
                <li class="dropdown"><a href="#" class="dropdown-toggle" id="dLabel" role="button"
                    data-toggle="dropdown">Organisation <b class="caret"></b></a>
                    <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                        <sec:authorize access="hasPermission('CIRCUIT', 'READ')">
                            <li role="menuitem"><a href="<c:url value="/circuits" />">Circuits</a></li>
                        </sec:authorize>
                        <sec:authorize access="hasPermission('KINGDOMHALL', 'READ')">
                            <li role="menuitem"><a href="<c:url value="/kingdom-halls" />">Kingdom Halls</a></li>
                        </sec:authorize>
                        <sec:authorize access="hasPermission('CONG', 'READ')">
                            <li role="menuitem"><a href="<c:url value="/congregations" />">Congregations</a></li>
                        </sec:authorize>
                    </ul>
                </li>
            </sec:authorize>
            <sec:authorize access="hasPermission('VOLUNTEER', 'READ') or hasPermission('SKILL', 'READ')">
                <li class="dropdown"><a href="#" class="dropdown-toggle" id="dLabel" role="button"
                    data-toggle="dropdown">People <b class="caret"></b></a>
                    <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                        <sec:authorize access="hasPermission('VOLUNTEER', 'READ')">
                            <li role="menuitem"><a href="<c:url value="/volunteers" />">Volunteers</a></li>
                        </sec:authorize>
                        <sec:authorize access="hasPermission('VOLUNTEER', 'READ')">
                            <li role="menuitem"><a href="<c:url value="/persons" />">Persons</a></li>
                        </sec:authorize>
                        <sec:authorize access="hasPermission('VOLUNTEER', 'READ')">
                            <li role="menuitem"><a href="<c:url value="/person-changes" />">Changes/Updates to Persons</a></li>
                        </sec:authorize>
                        <sec:authorize access="hasPermission('SKILL', 'READ')">
                            <li role="menuitem"><a href="<c:url value="/qualifications" />">Qualifications</a></li>
                        </sec:authorize>
                        <sec:authorize access="hasPermission('VOLUNTEER', 'READ')">
                            <li role="menuitem"><a href="<c:url value="/volunteer-experience" />">Experience</a></li>
                        </sec:authorize>
                        <sec:authorize access="hasPermission('SKILL', 'READ')">
                            <li role="menuitem"><a href="<c:url value="/skills" />">Skills</a></li>
                        </sec:authorize>
                    </ul>
                </li>
            </sec:authorize>
            <sec:authorize access="hasPermission('VOLUNTEER', 'READ') or hasPermission('SKILL', 'READ')">
                <li class="dropdown"><a href="#" class="dropdown-toggle" id="dLabel" role="button"
                    data-toggle="dropdown">Department <b class="caret"></b></a>
                    <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                        <sec:authorize access="hasPermission('VOLUNTEER', 'READ')">
                            <li><a href="<c:url value="/departments" />">Departments</a></li>
                        </sec:authorize>

                        <sec:authorize access="hasPermission('SKILL', 'READ')">
                            <li><a href="<c:url value="/skills" />">Skills</a></li>
                        </sec:authorize>
                    </ul>
                </li>
            </sec:authorize>
            <sec:authorize access="hasPermission('PROJECT', 'READ')">
                <li><a href="<c:url value="/projects" />">Projects</a></li>
            </sec:authorize>
            <sec:authorize access="hasPermission('DATABASE', 'READ')">
                <li><a href="<c:url value="/admin" />">Sys Admin</a></li>
            </sec:authorize>
        </ul>
        <form class="navbar-form navbar-right">
            <a class="btn btn-edifice" href="#" ><span class="glyphicon glyphicon-question-sign"></span>&nbsp;Help</a>&nbsp;
            <a class="btn btn-danger" href="<c:url value="/j_spring_security_logout" />" ><span class="glyphicon glyphicon-off"></span>&nbsp;Logout</a>&nbsp;
        </form>
    <!-- Everything between parenthesis will be minimised at 940px or less -->
    </div>
</nav>
