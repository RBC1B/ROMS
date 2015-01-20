<%-- 
    Document   : project-gatelist - displays the list of volunteers attending
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container-fluid">
    <br/>
    <div class="row">
        <div class="form-group">
            <label class="control-label col-md-3">Project Date</label>
            <div class="col-md-3">
                <input id="projectDate" name="projectDate" type="text" class="form-control" placeholder="Date (dd-mm-yyyy)" />
            </div>
        </div>
    </div>
    <br/>
    <div class="row">
        <div class="col-md-3">
            <a id="generate-gate-list" class="btn btn-success" href="#">Show gate list</a>
        </div>
        <div class="col-md-3">
            <a id="download-gate-list" class="btn btn-success" href="#">Download gate list</a>
        </div>
        <div class="col-md-3">
            <a id="download-co-list" class="btn btn-success" href="#">Project Coordinator list</a>
        </div>
    </div>
</div>
<div class="container-fluid">
    <div id="gate-list-summary">
        <dl>
            <dt>Date</dt>
            <dd id="project-summary-date"></dd>
            <dt>Number Invited</dt>
            <dd id="project-summary-invited"></dd>
            <dt>Total Available</dt>
            <dd id="project-summary-available"></dd>
        </dl>
    </div>
    <div id="gate-list-location">
    </div>
</div>



