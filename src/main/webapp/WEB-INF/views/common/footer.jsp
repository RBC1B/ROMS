<footer class="footer">
    <hr />
    <div class="row-fluid">
        <div class="span4">
            <ul class="nav nav-list">
                <li class="nav-header">Sections:</li>
                <sec:authorize access="hasPermission('CIRCUIT', 'READ')"><li><a href="<c:url value="/circuits" />">Circuits</a></li></sec:authorize>
                <sec:authorize access="hasPermission('KINGDOMHALL', 'READ')"><li><a href="<c:url value="/kingdom-halls" />">Kingdom Halls</a></li></sec:authorize>
                <sec:authorize access="hasPermission('CONG', 'READ')"><li><a href="<c:url value="/congregations" />">Congregations</a></li></sec:authorize>
                <sec:authorize access="hasPermission('VOLUNTEER', 'READ')"><li><a href="<c:url value="/volunteers" />">Volunteers</a></li></sec:authorize>
                <sec:authorize access="hasPermission('SKILL', 'READ')"><li><a href="<c:url value="/qualifications" />">Qualifications</a></li></sec:authorize>
            </ul>
        </div><!-- /span4 -->
        <div class="span4">
        </div><!-- /span4 -->
        <div class="span4">
        </div><!-- /span4 -->
    </div><!-- /row-fluid -->
    <div class="pull-right">
        <!-- So users on smaller screens don't have to scroll all the way back to the top -->
        <p><a href="#navbar"><i class="icon-circle-arrow-up"></i>&nbsp;Back to Top</a></p>
    </div>
</footer>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.0/jquery-ui.min.js"></script>
<script src="//ajax.aspnetcdn.com/ajax/jquery.validate/1.11.0/jquery.validate.min.js"></script>
<script src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/js/bootstrap.min.js"></script>

