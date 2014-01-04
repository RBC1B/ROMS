<%--
Edit form for the volunteer data under the personal tab.
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="pageTitle" value="Edit ${forename} ${surname} personal information" />
    <%@ include file="/WEB-INF/views/common/header.jsp"%>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp"%>
        <h1>${forename} ${surname} - personal information</h1>
        <hr />

        <c:url var="formAction" value="${submitUri}" />
        <form:form class="form-horizontal" commandName="volunteerPersonal" method="PUT" action="${formAction}">
            <fieldset>
                <legend>Contacts</legend>
                <div class="form-group">
                    <label for="email" class="control-label col-sm-3 col-md-2">Email</label>
                    <div class="col-sm-9 col-md-3">
                        <form:input path="email" class="form-control" maxlength="50" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="telephone" class="control-label col-sm-3 col-md-2">Home phone</label>
                    <div class="col-sm-9 col-md-2">
                        <form:input path="telephone" class="form-control" maxlength="15" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="mobile" class="control-label col-sm-3 col-md-2">Mobile phone</label>
                    <div class="col-sm-9 col-md-2">
                        <form:input path="mobile" class="form-control" maxlength="15" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="workPhone" class="control-label col-sm-3 col-md-2">Work phone</label>
                    <div class="col-sm-9 col-md-2">
                        <form:input path="workPhone" class="form-control" maxlength="15" />
                    </div>
                </div>
            </fieldset>
            <fieldset>
                <legend>Address</legend>
                <div class="form-group">
                    <label for="street" class="control-label col-sm-3 col-md-2">Street</label>
                    <div class="col-sm-9 col-md-3">
                        <form:input path="street" class="form-control" maxlength="50" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="town" class="control-label col-sm-3 col-md-2">Town</label>
                    <div class="col-sm-9 col-md-3">
                        <form:input path="town" class="form-control" maxlength="50" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="county" class="control-label col-sm-3 col-md-2">County</label>
                    <div class="col-sm-9 col-md-3">
                        <form:input path="county" class="form-control" maxlength="50" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="postcode" class="control-label col-sm-3 col-md-2">Postcode</label>
                    <div class="col-sm-9 col-md-2">
                        <form:input path="postcode" class="form-control" maxlength="10" />
                    </div>
                </div>
            </fieldset>
            <fieldset>
                <legend>Personal</legend>
                <div class="form-group">
                    <label class="control-label col-sm-3 col-md-2">Gender</label>
                    <div class="col-sm-4 col-md-2">
                        <form:select path="gender">
                            <form:option value="M">Male</form:option>
                            <form:option value="F">Female</form:option>
                        </form:select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-3 col-md-2">Date of birth</label>
                    <div class="col-sm-9 col-md-3">
                        <form:input path="birthDate" placeholder="dd/mm/yyyy" class="datepicker" data-date-format="dd/mm/yy" type="text" value=""/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-3 col-md-2">Marital status</label>
                    <div class="col-sm-4 col-md-2">
                        <form:select path="maritalStatusCode">
                            <form:option value="" />
                            <form:options items="${maritalStatusValues}" />
                        </form:select>
                    </div>
                </div>
                <div id="spouse">
                    <form:hidden path="spousePersonId" />
                    <!-- use style display:none since the div is dynamically showm, based on the defined spouse -->
                    <div id="spouse-unlinked" style="display:none">
                        <div class="form-group">
                            <label for="spouseForename" class="control-label col-sm-3 col-md-2">Spouse forename</label>
                            <div class="col-sm-9 col-md-3">
                                <form:input path="spouseForename" class="form-control" maxlength="50" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="spouseSurname" class="control-label col-sm-3 col-md-2">Spouse surname</label>
                            <div class="col-sm-9 col-md-3">
                                <form:input path="spouseSurname" class="form-control" maxlength="50" />
                            </div>
                        </div>
                    </div>
                    <!-- use style display:none since the div is dynamically showm, based on the defined spouse -->
                    <div id="spouse-linked" style="display:none">
                        <div class="form-group">
                            <label class="control-label col-sm-3 col-md-2">Spouse</label>
                            <div class="col-sm-9 col-md-3">
                                <span id="spouse-linked-text"></span>
                                <a class="btn btn-edifice btn-xs" href="#">Unlink</a>
                            </div>
                        </div>
                    </div>
                </div>
            <fieldset>
                <button type="submit" class="btn btn-default btn-success">Submit</button>
            </fieldset>
        </div>
    </form:form>

    <ol class="breadcrumb">
        <li><a href="<c:url value="/" />">Edifice</a></li>
        <li><a href="<c:url value="/volunteers" />">Volunteers</a></li>
        <li class="active">Edit ${forename} ${surname} personal information</li>
    </ol>

    <%@ include file="/WEB-INF/views/common/footer.jsp"%>
    <%@ include file="/WEB-INF/views/common/mustache-person-link-search-form.jsp"%>
    <%@ include file="/WEB-INF/views/common/person-link-modal.jsp"%>
    <script type="text/javascript" src="<c:url value='/javascript/thirdparty/phoneformat-574.js' />" ></script>
    <script type="text/javascript" src="<c:url value='/javascript/volunteers.js' />"></script>
</body>
</html>
