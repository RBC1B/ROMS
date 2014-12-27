<%--
    Form use to edit or create congregations.
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<c:choose>
    <c:when test="${!empty congregationForm.name}">
        <c:set var="pageTitle">Edit congregation - ${congregationForm.name}</c:set>
    </c:when>
    <c:otherwise>
        <c:set var="pageTitle">Create new congregation</c:set>
    </c:otherwise>
</c:choose>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<body>
    <%@ include file="/WEB-INF/views/common/titlebar.jsp"%>
    <c:choose>
        <c:when test="${!empty congregationForm.name}">
            <h1>Edit congregation</h1>
        </c:when>
        <c:otherwise>
            <h1>Create new congregation</h1>
        </c:otherwise>
    </c:choose>
    <hr />
    <c:url var="formAction" value="${submitUri}" />
    <form:form class="form-horizontal" commandName="congregationForm" method="${submitMethod}" action="${formAction}">
        <fieldset>
            <div class="form-group">
                <label class="control-label col-sm-3 col-md-2" for="name">Congregation Name</label>
                <div class="col-sm-9 col-md-3">
                    <form:input path="name" class="form-control" placeholder="Congregation Name" maxlength="50" />
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3 col-md-2" for="number">Number</label>
                <div class="col-sm-9 col-md-3">
                    <form:input path="number" class="form-control" placeholder="Number" maxlength="10" />
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3 col-md-2" for="KingdomHallName">Kingdom Hall</label>
                <div class="col-sm-9 col-md-3">
                    <form:input path="kingdomHallName" class="form-control" placeholder="Kingdom Hall name" size="50"/>
                    <form:hidden path="kingdomHallId" />
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3 col-md-2" for="circuitId">Circuit</label>
                <div class="col-sm-4 col-md-3">
                    <form:select class="form-control" path="circuitId">
                        <form:option value="" label="None" />
                        <form:options items="${circuits}" itemValue="circuitId" itemLabel="name" />
                    </form:select>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3 col-md-2" for="rbcRegionId">RBC region</label>
                <div class="col-sm-4 col-md-3">
                    <form:select class="form-control" path="rbcRegionId">
                        <form:option value="" label="None" />
                        <form:options items="${rbcRegions}" />
                    </form:select>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3 col-md-2" for="rbcSubRegionId">RBC subregion</label>
                <div class="col-sm-4 col-md-2">
                    <form:select class="form-control" path="rbcSubRegionId">
                        <form:option value="" />
                        <form:options items="${rbcSubRegions}" />
                    </form:select>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3 col-md-2" for="publishers">Publishers</label>
                <div class="col-sm-9 col-md-3">
                    <form:input path="publishers" class="form-control" placeholder="Publisher number" maxlength="10" />
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3 col-md-2" for="attendance">Attendance</label>
                <div class="col-sm-9 col-md-3">
                    <form:input path="attendance" class="form-control" placeholder="Attendance number" maxlength="10" />
                </div>
            </div>
        </fieldset>
        <fieldset>
            <div class="form-group">
                <label class="control-label col-sm-3 col-md-2"></label>
                <div class="col-sm-9 col-md-3">
                    <h4 class="text-left">Contacts</h4>
                </div>
            </div>
            <div id="coordinator">
                <form:hidden path="coordinatorPersonId" />
                <!-- use style display:none since the div is dynamically shown, based on the defined coordinator -->
                <div id="coordinator-unlinked" style="display:none">
                    <div class="form-group">
                        <label class="control-label col-sm-3 col-md-2" for="coordinatorForenamae">Coordinator forename</label>
                        <div class="col-sm-9 col-md-3">
                            <form:input path="coordinatorForename" class="form-control" maxlength="50" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3 col-md-2" for="coordinatorSurname">Coordinator surname</label>
                        <div class="col-sm-9 col-md-3">
                            <form:input path="coordinatorSurname" class="form-control" maxlength="50" />
                        </div>
                    </div>
                </div>
                <!-- use style display:none since the div is dynamically shown, based on the defined coordinator -->
                <div id="coordinator-linked" class="form-group" style="display:none">
                    <div class="col-sm-3 col-md-2 text-right">
                        <strong>Coordinator</strong>
                    </div>
                    <div class="col-sm-9 col-md-3">
                        <span id="coordinator-linked-text"></span>
                        <a class="btn btn-edifice btn-xs" href="#">Unlink</a>
                    </div>
                </div>
            </div><br />
            <div id="secretary">
                <form:hidden path="secretaryPersonId" />
                <!-- use style display:none since the div is dynamically shown, based on the defined secretary -->
                <div id="secretary-unlinked" style="display:none">
                    <div class="form-group">
                        <label class="control-label col-sm-3 col-md-2" for="secretaryForename">Secretary forename</label>
                        <div class="col-sm-9 col-md-3">
                            <form:input path="secretaryForename" class="form-control" maxlength="50" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3 col-md-2" for="secretarySurname">Secretary surname</label>
                        <div class="col-sm-9 col-md-3">
                            <form:input path="secretarySurname" class="form-control" maxlength="50" />
                        </div>
                    </div>
                </div>
                <!-- use style display:none since the div is dynamically shown, based on the defined secretary -->
                <div id="secretary-linked" class="form-group" style="display:none">
                    <div class="col-sm-3 col-md-2 text-right">
                        <strong>Secretary</strong>
                    </div>
                    <div class="col-sm-9 col-md-3">
                        <span id="secretary-linked-text"></span>
                        <a class="btn btn-edifice btn-xs" href="#">Unlink</a>
                    </div>
                </div>
            </div>
        </fieldset>
        <fieldset>
            <div class="form-group">
                <label class="control-label col-sm-3 col-md-2"></label>
                <div class="col-sm-9 col-md-3">
                    <h4 class="text-left">Strategy</h4>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3 col-md-2" for="funds">Funds</label>
                <div class="col-sm-9 col-md-3">
                    <form:input path="funds" class="form-control" maxlength="50" />
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3 col-md-2" for="loans">Loans</label>
                <div class="col-sm-9 col-md-3">
                    <form:input path="loans" class="form-control" maxlength="10" />
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3 col-md-2" for="monthlyIncome">Monthly income</label>
                <div class="col-sm-9 col-md-3">
                    <form:input path="monthlyIncome" class="form-control" maxlength="10" />
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3 col-md-2" for="strategy">Strategy</label>
                <div class="col-sm-9 col-md-3">
                    <form:textarea path="strategy" class="form-control" rows="4" />
                </div>
                <div class=”col-sm-3 col-md-3”>
                    <span class="glyphicon glyphicon-question-sign" aria-hidden="true" data-toggle="tooltip" data-placement="right" title=""></span>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3 col-md-2"></label>
                <div class="col-sm-9 col-md-3">
                    <input type="submit" class="btn btn-edifice" />
                </div>
            </div>
        </fieldset>
    </form:form>

    <ol class="breadcrumb">
        <li><a href="<c:url value="/" />">Edifice</a></li>
        <li><a href="<c:url value="/congregations" />">Congregations</a></li>
        <li class="active">Edit Congregation</li>
    </ol>
    <%@ include file="/WEB-INF/views/common/footer.jsp"%>
    <%@ include file="/WEB-INF/views/common/mustache-person-link-search-form.jsp"%>
    <%@ include file="/WEB-INF/views/common/person-link-modal.jsp"%>
    <script type="text/javascript" src="<c:url value='/javascript/thirdparty/jquery-numeric-1.3.1.js' />" ></script>
    <script type="text/javascript" src="<c:url value='/javascript/congregations.js' />"></script>
</body>
</html>
