</div>
<footer class="footer">
    <hr />
    <div class="navbar navbar-inverse">
        <div class="navbar-inner">
            <div class="row">
                <div class="col-xs-4">
                    <ul class="footer-nav">
                        <li class="first">Organisation</li>
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
                </div>
                <div class="col-xs-4">
                    <ul class="footer-nav">
                        <li class="first">People</li>
                        <sec:authorize access="hasPermission('VOLUNTEER', 'READ')">
                            <li role="menuitem"><a href="<c:url value="/volunteers" />">Volunteers</a></li>
                        </sec:authorize>
                        <sec:authorize access="hasPermission('VOLUNTEER', 'READ')">
                          <li role="menuitem"><a href="<c:url value="/persons" />">Persons</a></li>
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
                        <sec:authorize access="hasPermission('VOLUNTEER', 'READ')">
                            <li><a href="<c:url value="/departments" />">Department</a></li>
                        </sec:authorize>
                    </ul>
                </div>
                <div class="col-xs-4">
                    <ul class="footer-nav">
                        <li class="first">More...</li>
                        <sec:authorize access="hasPermission('PROJECT', 'READ')">
                            <li><a href="<c:url value="/projects" />">Projects</a></li>
                        </sec:authorize>
                        <sec:authorize access="hasPermission('DATABASE', 'READ')">
                            <li><a href="<c:url value="/admin" />">Sys Admin</a></li>
                        </sec:authorize>
                    </ul>
                </div>
            </div>
                <div class="col-xs-12" id="footer-nav">
                    <!-- So users on smaller screens don't have to scroll all the way back to the top -->
                    <btn class="btn btn-edifice pull-right" id="back-to-top"> <a href="#navbar"><i class="glyphicon glyphicon-circle-arrow-up"></i>&nbsp;Back to Top</a></btn>
                </div>
            </div>
        </div>
    </div>
</footer>
<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.0/jquery-ui.min.js"></script>
<script type="text/javascript" src="//ajax.aspnetcdn.com/ajax/jquery.validate/1.11.0/jquery.validate.min.js"></script>
<script type="text/javascript" src="//ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="<c:url value='/javascript/thirdparty/datatables.bootstrap.js' />"></script>
<script type="text/javascript" src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<c:url value='/javascript/thirdparty/mustache-0.7.2.js' />"></script>
<script type="text/javascript" src="<c:url value='/javascript/thirdparty/typeahead-0.9.3.min.js' />" ></script>
<script type="text/javascript" src="<c:url value='/javascript/common.js' />"></script>
