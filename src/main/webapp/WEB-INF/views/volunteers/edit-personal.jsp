<%--
Edit form for the volunteer data under the personal tab.
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<c:set var="pageTitle" value="Edit ${forename} ${surname} Personal Information" />
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<body>
    <%@ include file="/WEB-INF/views/common/titlebar.jsp"%>
        <h1>${forename} ${surname} - Personal Information</h1>
        <hr>

        <c:url var="formAction" value="${submitUri}" />
        <form:form class="form-horizontal" commandName="volunteerPersonal" method="PUT" action="${formAction}">
            <fieldset>
                <legend>Contact</legend>
                    <div class="form-group">
                        <div class="container">
                             <label for="email" class="col-md-2 col-sm-3 control-label">Email:</label>
                             <div class="col-md-10 col-sm-6">
                                 <form:input class="form-control" path="email" maxlength="50" />
                             </div>
                        </div><br>
                        <div class="container">
                            <label class="control-label col-sm-3 col-md-2" for="telephone">Home phone:</label>
                            <div class="col-md-2 col-sm-9">
                                <form:input class="form-control" path="telephone" maxlength="15" />
                            </div>
                            <label class="control-label col-sm-3 col-md-2" for="mobile">Mobile phone:</label>
                            <div class="col-md-2 col-sm-9">
                                <form:input class="form-control" path="mobile" maxlength="15" />
                            </div>
                            <label class="control-label col-sm-3 col-md-2" for="workPhone">Work phone:</label>
                            <div class="col-md-2 col-sm-9">
                                <form:input class="form-control" path="workPhone" maxlength="15" />
                            </div>
                        </div>
                    </div>
            </fieldset><br>
            <fieldset>
                <legend>Address</legend>
                <div class="form-group">
                    <div class="col-md-6">
                    <label class="col-md-4 col-sm-3 control-label" for="street">Street</label>
                    <div class="col-md-8 col-sm-9">
                        <form:input class="form-control" path="street" maxlength="50" />
                    </div>
                    </div>
                    <div class="col-md-6">
                        <label class="col-md-4 col-sm-3 control-label" for="town">Town</label>
                        <div class="col-md-8 col-sm-9">
                            <form:input class="form-control" path="town" maxlength="50" />
                        </div>
                    </div>
                    <div class="col-md-6">
                        <label class="col-md-4 col-sm-3 control-label" for="county">County</label>
                        <div class="col-md-8 col-sm-9">
                            <form:input class="form-control" path="county" maxlength="50" />
                        </div>
                    </div>
                    <div class="col-md-6">
                        <label class="col-md-4 col-sm-3 control-label" for="postcode">Postcode</label>
                        <div class="col-md-8 col-sm-9">
                            <form:input class="form-control" path="postcode" maxlength="10" />
                        </div>
                    </div>
                </div>
            </fieldset><br>
            <fieldset>
                <legend>Gender</legend>
                <form:select path="gender">
                    <option value="M">Male</option>
                    <option value="F">Female</option>
                </form:select>
            </fieldset><br>
            <fieldset>
                <legend>Date of birth</legend>
                <label for="birthDate">Date of birth</label>
                <form:input class="datepicker" path="birthDate" placeholder="15/03/1980" />
            </fieldset><br>
            <fieldset>
                <legend>Marital status</legend>
                <label for="maritalStatusCode">Status</label>
                <form:select path="maritalStatusCode">
                    <form:option value="" />
                    <form:options items="${maritalStatusValues}" />
                </form:select>
                <div id="spouse">
                    <form:hidden path="spousePersonId" />
                    <div id="spouse-unlinked" class="hide">
                        <label for="spouseForename">Spouse forename</label>
                        <form:input path="spouseForename" maxlength="50" />
                        <label for="spouseForename">Spouse surname</label>
                        <form:input path="spouseSurname" maxlength="50" />
                    </div>
                    <div id="spouse-linked" class="hide">
                        Spouse: <span id="spouse-linked-text"></span>
                        <a class="btn btn-primary btn-mini" href="#">Unlink</a>
                    </div>
                <!--/div-->
            </fieldset><br>
            <fieldset>
                <button type="submit" class="btn btn-default btn-success">Submit</button>
            </fieldset>
        </form:form>

        <ol class="breadcrumb">
            <li><a href="<c:url value="/" />">Edifice</a></li>
            <li><a href="<c:url value="/volunteers" />">Volunteers</a></li>
            <li class="active">#${volunteer.id}: ${volunteer.displayName} Edit Personal Info</li>
        </ol>

        <%@ include file="/WEB-INF/views/common/footer.jsp"%>
    <%@ include file="/WEB-INF/views/common/mustache-person-link-search-form.jsp"%>
    <%@ include file="/WEB-INF/views/common/person-link-modal.jsp"%>
    <script type="text/javascript" src="<c:url value='/javascript/thirdparty/phoneformat-574.js' />" ></script>
    <script type="text/javascript" src="<c:url value='/javascript/volunteers.js' />"></script>
</body>
</html>
