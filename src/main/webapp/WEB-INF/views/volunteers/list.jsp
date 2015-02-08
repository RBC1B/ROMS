<%--
    List the volunteers.
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="pageTitle">Volunteers</c:set>
    <%@ include file="/WEB-INF/views/common/header.jsp"%>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp"%>
        <h1>Volunteers</h1>
        <hr />
        <div class="panel-group" id="volunteer-advanced-search" role="tablist">
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="volunteer-advanced-search-heading">
                    <a data-toggle="collapse" data-parent="#volunteer-advanced-search" href="#volunteer-advanced-search-collapse" aria-expanded="false" aria-controls="collapseOne">
                    Advanced search
                    </a>
                </div>
                <div id="volunteer-advanced-search-collapse" class="panel-collapse collapse" role="tabpanel" aria-labelledby="volunteer-advanced-search-heading">
                    <div class="panel-body">
                        <form id="volunteer-advanced-search-form" class="form-inline">
                            <div class="form-group">
                                <label for="volunteerId">ID</label>
                                <input type="text" class="form-control" name="volunteerId" maxlength="5" size="5"/>
                            </div>
                            <div class="form-group">
                                <label for="forename">Forename</label>
                                <input type="text" class="form-control" name="forename" size="10" />
                            </div>
                            <div class="form-group">
                                <label for="surname">Surname</label>
                                <input type="text" class="form-control" name="surname" size="10" />
                            </div>
                            <div class="form-group">
                                <label for="congregationName">Congregation</label>
                                <input type="text" class="form-control" name="congregationName" id="congregationName" size="25" autocomplete="off" />
                                <input type="hidden" name="congregationId" id="congregationId" />
                            </div>
                            <div class="form-group">
                                <label for="departmentName">Department</label>
                                <input type="text" class="form-control" name="departmentName" id="departmentName" size="25" />
                                <input type="hidden" name="departmentId" id="departmentId" />
                            </div>
                            <div class="form-group">
                                <label for="skillName">Skill</label>
                                <input type="text" class="form-control" name="skillName" id="skillName" size="25" />
                                <input type="hidden" name="skillId" id="skillId" />
                            </div>
                            <div class="form-group">
                                <label for="kingdomHallName">Kingdom Hall</label>
                                <input type="text" class="form-control" name="kingdomHallName" id="kingdomHallName" size="25" />
                                <input type="hidden" name="kingdomHallId" id="kingdomHallId" />
                            </div>
                            <div class="form-group">
                                <label for="location">Location</label>
                                <input type="text" class="form-control" name="location" size="10" />
                            </div>
                            <button type="submit" class="btn btn-success">Search</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="entity-list-results">
            <table class="table table-bordered table-condensed table-striped table-hover" id="volunteer-list">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Forename</th>
                        <th>Surname</th>
                        <th>Congregation</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
                <tfoot>
                </tfoot>
            </table>
        </div>
        <sec:authorize access="hasPermission('VOLUNTEER', 'ADD')">
            <hr />
            <div class="entity-list-add-new">
                <a class="btn btn-edifice" href="<c:url value="${newUri}" />">Create new volunteer</a>
            </div>
        </sec:authorize>
        <br />
        <ol class="breadcrumb">
            <li><a href="<c:url value="/" />">Edifice</a></li>
            <li class="active">Volunteers</li>
        </ol>
        <%@ include file="/WEB-INF/views/common/footer.jsp"%>
        <%@ include file="/WEB-INF/views/common/mustache-list-actions.jsp"%>
        <script type="text/javascript" src="<c:url value='/javascript/thirdparty/jquery-numeric-1.3.1.js' />" ></script>
        <script type="text/javascript" src="<c:url value='/javascript/volunteers.js' />"></script>
    </body>
</html>
