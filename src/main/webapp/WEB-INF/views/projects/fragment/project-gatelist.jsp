<%-- 
    Document   : project-gatelist - displays the list of volunteers attending
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="form-group">
    <label for="projectDate" class="control-label">Project Date</label>
    <input id="projectDate" name="projectDate" type="text" class="form-control" placeholder="Date (dd-mm-yyyy)" />
</div>

<a id="generate-gate-list" class="btn btn-success" href="#">Display List</a>

<div id="gate-list-summary"></div>
<div id="gate-list-location">
</div>

<a id="download-gate-list" class="btn btn-success" href="#">Download List</a>