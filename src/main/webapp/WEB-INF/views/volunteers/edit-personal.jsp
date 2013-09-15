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
    <div class="container-fluid">
        <h1>${forename}${surname} - Personal Information</h1>
        <hr>

        <c:url var="formAction" value="${submitUri}" />
        <form:form commandName="volunteerPersonal" method="PUT" action="${formAction}">
            <fieldset>
                <legend>Contact</legend>
                <label for="email">Email:</label>
                <form:input path="email" maxlength="50" />
                <label for="telephone">Home phone</label>
                <form:input path="telephone" maxlength="15" />
                <label for="mobile">Mobile phone:</label>
                <form:input path="mobile" maxlength="15" />
                <label for="workPhone">Work phone:</label>
                <form:input path="workPhone" maxlength="15" />
            </fieldset>
            <fieldset>
                <legend>Address</legend>
                <label for="street">Street</label>
                <form:input path="street" maxlength="50" />
                <label for="town">Town</label>
                <form:input path="town" maxlength="50" />
                <label for="county">County</label>
                <form:input path="county" maxlength="50" />
                <label for="postcode">Postcode</label>
                <form:input path="postcode" maxlength="10" />
            </fieldset>
            <fieldset>
                <legend>Gender</legend>
                <form:select path="gender">
                    <option value="M">Male</option>
                    <option value="F">Female</option>
                </form:select>
            </fieldset>
            <fieldset>
                <legend>Date of birth</legend>
                <label for="birthDate">Date of birth</label>
                <form:input class="datepicker" path="birthDate" placeholder="15/03/1980" />
            </fieldset>
            <fieldset>
                <legend>Marital status</legend>
                <label for="maritalStatusId">Status</label>
                <form:select path="maritalStatusId">
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
                </div>
            </fieldset>
            <fieldset>
                <button type="submit" class="btn">Submit</button>
            </fieldset>
        </form:form>
        <%@ include file="/WEB-INF/views/common/footer.jsp"%>
    </div>
    <%@ include file="/WEB-INF/views/common/mustache-person-link-search-form.jsp"%>
    <%@ include file="/WEB-INF/views/common/person-link-modal.jsp"%>
    <script type="text/javascript" src="<c:url value='/javascript/thirdparty/phoneformat-574.js' />" ></script>
    <script type="text/javascript" src="<c:url value='/javascript/volunteers.js' />"></script>
</body>
</html>
