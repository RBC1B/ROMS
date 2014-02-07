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
    <form:form commandName="congregationForm" method="${submitMethod}" action="${formAction}">
        <fieldset>
            <div class="form-group">
                <div class="row">
                    <div class="col-md-3">
                        <label for="name">Congregation name</label>
                        <form:input path="name" class="form-control" placeholder="Congregation Name" maxlength="50" />                        
                    </div>
                    <div class="col-md-3">
                        <label for="number">Number</label>
                        <form:input path="number" class="form-control" placeholder="Number" maxlength="10" />
                    </div>
                    <div class="col-md-3">
                        <label for="kingdomHallName">Kingdom Hall</label>
                        <form:input path="kingdomHallName" class="form-control" placeholder="Kingdom Hall name"/>
                        <form:hidden path="kingdomHallId" />
                    </div>
                </div>
                <br />
                <div class="row">
                    <div class="col-md-3">
                        <label for="circuitId">Circuit</label>
                        <form:select class="form-control" path="circuitId">
                            <form:option value="" label="None" />
                            <form:options items="${circuits}" itemValue="circuitId" itemLabel="name" />
                        </form:select>
                    </div>
                    <div class="col-md-3">
                        <label for="rbcRegionId">RBC region</label>
                        <form:select class="form-control" path="rbcRegionId">
                            <form:option value="" label="None" />
                            <form:options items="${rbcRegions}" />
                        </form:select>
                    </div>
                    <div class="col-md-3">
                        <label for="rbcSubRegionId">RBC subregion</label>
                        <form:select class="form-control" path="rbcSubRegionId">
                            <form:option value="" />
                            <form:options items="${rbcSubRegions}" />
                        </form:select>
                    </div>
                </div>
                <br />
                <div class="row">
                    <div class="col-md-3">
                        <label for="publishers">Publishers</label>
                        <form:input path="publishers" class="form-control" placeholder="Publisher number" maxlength="10" />
                    </div>
                    <div class="col-md-3">
                        <label>Attendance</label>
                        <form:input path="attendance" class="form-control" placeholder="Attendance number" maxlength="10" />
                    </div>
                </div>
            </div>
            <br />
        </fieldset>
        <fieldset>
            <div class="form-group">
                <div class="row">
                    <div class="col-md-12">
                        <h4>Contacts</h4>
                    </div>
                    <div id="coordinator">
                        <form:hidden path="coordinatorPersonId" />
                        <!-- use style display:none since the div is dynamically shown, based on the defined coordinator -->
                        <div id="coordinator-unlinked" style="display:none">
                            <div class="col-md-3">
                                <label for="coordinatorForename">Coordinator forename</label>
                                <form:input path="coordinatorForename" class="form-control" maxlength="50" />
                            </div>
                            <div class="col-md-3">
                                <label for="coordinatorForename">Coordinator surname</label>
                                <form:input path="coordinatorSurname" class="form-control" maxlength="50" />
                            </div>
                        </div>
                        <!-- use style display:none since the div is dynamically shown, based on the defined coordinator -->
                        <div id="coordinator-linked" class="col-md-6" style="display:none">
                            Coordinator: <span id="coordinator-linked-text"></span>
                            <a class="btn btn-edifice btn-xs" href="#">Unlink</a>
                        </div>                    
                    </div>
                    <div class="clearfix"></div><br />
                    <div id="secretary">
                        <form:hidden path="secretaryPersonId" />
                        <!-- use style display:none since the div is dynamically shown, based on the defined secretary -->
                        <div id="secretary-unlinked" style="display:none">
                            <div class="col-md-3">
                                <label for="secretaryForename">Secretary forename</label>
                                <form:input path="secretaryForename" class="form-control" maxlength="50" />
                            </div>
                            <div class="col-md-3">
                                <label for="secretaryForename">Secretary surname</label>
                                <form:input path="secretarySurname" class="form-control" maxlength="50" />
                            </div>
                        </div>
                        <!-- use style display:none since the div is dynamically shown, based on the defined secretary -->
                        <div id="secretary-linked" class="col-md-6" style="display:none">
                            Secretary: <span id="secretary-linked-text"></span>
                            <a class="btn btn-edifice btn-xs" href="#">Unlink</a>
                        </div>
                    </div>
                </div>
            </div>
        </fieldset>
        <fieldset>
            <div class="form-group">
                <div class="row">
                    <div class="col-md-12">
                        <h4>Strategy</h4>
                    </div>
                    <div class="col-md-3">
                        <label for="funds">Funds</label>
                        <form:input path="funds" class="form-control" maxlength="50" />
                    </div>
                    <div class="col-md-3">
                        <label for="loans">Loans</label>
                        <form:input path="loans" class="form-control" maxlength="10" />
                    </div>
                    <div class="col-md-3">
                        <label for="monthlyIncome">Monthly income</label>
                        <form:input path="monthlyIncome" class="form-control" maxlength="10" />
                    </div>
                    <div class="clearfix"></div>
                    <div class="col-md-6">
                        <label for="strategy">Strategy</label>
                        <form:textarea path="strategy" class="form-control" rows="4" />
                    </div>
                </div>
            </div>
        </fieldset>
        <input type="submit" class="btn btn-edifice" />
    </form:form>

    <ol class="breadcrumb">
        <li><a href="<c:url value="/" />">Edifice</a></li>
        <li><a href="<c:url value="/congregations" />">Congregations</a></li>
        <li class="active">Edit Congregation</li>
    </ol>
        <!--cols="50" -->
    <%@ include file="/WEB-INF/views/common/footer.jsp"%>
    <%@ include file="/WEB-INF/views/common/mustache-person-link-search-form.jsp"%>
    <%@ include file="/WEB-INF/views/common/person-link-modal.jsp"%>
    <script type="text/javascript" src="<c:url value='/javascript/thirdparty/jquery-numeric-1.3.1.js' />" ></script>
    <script type="text/javascript" src="<c:url value='/javascript/congregations.js' />"></script>
</body>
</html>
