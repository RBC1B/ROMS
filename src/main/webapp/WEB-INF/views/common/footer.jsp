</div>
<footer class="footer">
    <div class="navbar navbar-inverse">
        <div class="navbar-inner">
            <div class="row">
                <div class="col-xs-4">
                    <sec:authorize access="hasPermission('CIRCUIT', 'READ') or hasPermission('KINGDOMHALL', 'READ') or hasPermission('CONG', 'READ')">
                        <ul class="footer-nav">
                            <li class="first">Organisation</li>
                            <sec:authorize access="hasPermission('CIRCUIT', 'READ')">
                                <li role="menuitem"><a href="<c:url value="/circuits" />">Circuits</a></li>
                            </sec:authorize>
                            <sec:authorize access="hasPermission('KINGDOMHALL', 'READ')">
                                <li role="menuitem"><a href="<c:url value="/kingdom-halls" />">Kingdom halls</a></li>
                            </sec:authorize>
                            <sec:authorize access="hasPermission('CONG', 'READ')">
                                <li role="menuitem"><a href="<c:url value="/congregations" />">Congregations</a></li>
                            </sec:authorize>
                        </ul>
                    </sec:authorize>
                </div>
                <div class="col-xs-4">
                    <sec:authorize access="hasPermission('VOLUNTEER', 'READ') or hasPermission('SKILL', 'READ')">
                        <ul class="footer-nav">
                            <li class="first">People</li>
                            <sec:authorize access="hasPermission('VOLUNTEER', 'READ')">
                                <li role="menuitem"><a href="<c:url value="/volunteers" />">Volunteers</a></li>
                            </sec:authorize>
                            <sec:authorize access="hasPermission('VOLUNTEER', 'READ')">
                              <li role="menuitem"><a href="<c:url value="/persons" />">Persons</a></li>
                            </sec:authorize>
                            <sec:authorize access="hasPermission('VOLUNTEER', 'READ')">
                                <li role="menuitem"><a href="<c:url value="/person-changes" />">Form updates</a></li>
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
                    </sec:authorize>
                </div>
                <div class="col-xs-4">
                    <sec:authorize access="hasPermission('PROJECT', 'READ') or hasPermission('DATABASE', 'READ')">
                        <ul class="footer-nav">
                            <li class="first">More...</li>
                            <sec:authorize access="hasPermission('PROJECT', 'READ')">
                                <li><a href="<c:url value="/projects" />">Projects</a></li>
                            </sec:authorize>
                            <sec:authorize access="hasPermission('DATABASE', 'READ')">
                                <li><a href="<c:url value="/admin" />">Admin</a></li>
                            </sec:authorize>
                        </ul>
                    </sec:authorize>
                </div>
            </div>
                <div class="col-xs-12" id="footer-nav">
                    <!-- So users on smaller screens don't have to scroll all the way back to the top -->
                    <a href="#" class="btn btn-edifice pull-right" id="back-to-top"><span class="glyphicon glyphicon-circle-arrow-up"></span>&nbsp;Back to Top</a>
                </div>
            </div>
        </div>
    </div>
</footer>
<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
<script type="text/javascript" src="//ajax.aspnetcdn.com/ajax/jquery.validate/1.11.0/jquery.validate.min.js"></script>
<script type="text/javascript" src="//ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/x-editable/1.5.0/bootstrap3-editable/js/bootstrap-editable.min.js"></script>
<script type="text/javascript" src="<c:url value='/javascript/thirdparty/mustache-0.7.2.js' />"></script>
<script type="text/javascript" src="<c:url value='/javascript/thirdparty/typeahead-0.9.3.min.js' />" ></script>
<script type="text/javascript" src="<c:url value='/javascript/thirdparty/jquery.form.v20131121.min.js' />" ></script>
<script type="text/javascript" src="<c:url value='/javascript/common.js' />"></script>
