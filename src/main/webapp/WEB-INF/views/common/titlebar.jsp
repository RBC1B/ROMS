<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!-- data attribute is used to determine the relative paths for ajax requests -->
<div id="relative-path" data-relative-path="<c:url value="/" />"></div>
<div id="wrap">
    <div class="navbar navbar-inverse navbar-fixed-top" id="navbar">
        <div class="navbar-inner">
            <div class="container-fluid">
                <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">MENU</a> <a class="brand"
                    href="<c:url value="/" />">RBC Edifice</a>
                <!-- Everything you want minimised at 940px or less, place within here -->
                <div class="nav-collapse">
                    <ul class="nav">
                        <li class="dropdown"><a href="#" class="dropdown-toggle" id="dLabel" role="button"
                            data-toggle="dropdown">Organisation <b class="caret"></b></a>
                            <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                                <sec:authorize access="hasPermission('CIRCUIT', 'READ')">
                                    <li role="menuitem"><a href="<c:url value="/circuits" />">Circuits</a></li>
                                </sec:authorize>
                                <sec:authorize access="hasPermission('KINGDOMHALL', 'READ')">
                                    <li role="menuitem"><a href="<c:url value="/kingdom-halls" />">Kingdom
                                            Halls</a></li>
                                </sec:authorize>
                                <sec:authorize access="hasPermission('CONG', 'READ')">
                                    <li role="menuitem"><a href="<c:url value="/congregations" />">Congregations</a></li>
                                </sec:authorize>
                            </ul></li>
                        <li class="dropdown"><a href="#" class="dropdown-toggle" id="dLabel" role="button"
                            data-toggle="dropdown">People <b class="caret"></b></a>
                            <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                                <sec:authorize access="hasPermission('VOLUNTEER', 'READ')">
                                    <li role="menuitem"><a href="<c:url value="/volunteers" />">Volunteers</a></li>
                                </sec:authorize>
                                <sec:authorize access="hasPermission('VOLUNTEER', 'READ')">
                                    <li role="menuitem"><a href="<c:url value="/persons" />">Persons</a></li>
                                </sec:authorize>
                                <sec:authorize access="hasPermission('SKILL', 'READ')">
                                    <li role="menuitem"><a href="<c:url value="/qualifications" />">Qualifications</a></li>
                                </sec:authorize>
                                <sec:authorize access="hasPermission('EXPERIENCE', 'READ')">
                                    <li role="menuitem"><a href="<c:url value="/experience" />">Experience</a></li>
                                </sec:authorize>
                                <sec:authorize access="hasPermission('SKILL', 'READ')">
                                    <li role="menuitem"><a href="<c:url value="/skills" />">Skills</a></li>
                                </sec:authorize>
                            </ul></li>
                        <li class="dropdown"><a href="#" class="dropdown-toggle" id="dLabel" role="button"
                            data-toggle="dropdown">Department <b class="caret"></b></a>
                            <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                                <sec:authorize access="hasPermission('SKILLS', 'READ')">
                                    <li><a href="<c:url value="/skills" />">Skills</a></li>
                                </sec:authorize>
                            </ul></li>
                        <sec:authorize access="hasPermission('PROJECT', 'READ')">
                            <li><a href="<c:url value="/projects" />">Projects</a></li>
                        </sec:authorize>
                        <sec:authorize access="hasPermission('ADMIN', 'READ')">
                            <li><a href="<c:url value="/admin" />">Sys Admin</a></li>
                        </sec:authorize>

                    </ul>
                    <p class="pull-right">
                        <a class="btn btn-info" href="#"><i class="icon-question-sign icon-white"></i>&nbsp;Help</a>
                        &nbsp; <a class="btn btn-danger" href="<c:url value="/j_spring_security_logout" />">Logout</a>
                        &nbsp;
                    </p>
                </div>
                <!-- Everything between parenthesis will be minimised at 940px or less -->
            </div>
        </div>
    </div>
