<%--
    Form to allow volunteer to update their personal details.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <%@ include file="/WEB-INF/views/volunteer-contact/fragments/header.jsp" %>
    <body>
        <div id="relative-path" data-relative-path="<c:url value="/" />"></div>
        <div class="container">
            <nav class="navbar navbar-inverse navbar-static-top" role="navbar">
                <a class="navbar-brand" href="<c:url value="/" />"><img src="<c:url value='/images/logo-brand.png' />" alt="edifice" /></a>
            </nav>
        </div>
        <div class="container">
             <h1>RBC London and Home Counties</h1>
        </div>
        <div id="a-submit-alerts" style="display:none">
            <div class="alert alert-success">
                <p><strong>Success: </strong>Your request has been submitted. You will receive a confirmation email shortly.</p>
            </div>
            <div class="alert alert-error">
                <p><b>Error: </b>Failed to update your details. Please contact the Volunteer Department.</p>
            </div>
        </div>
        <div class="container">
            <p>Contact Information for <c:out value="${surname}"/>, <c:out value="${forename}"/></p>
            <form:form id="contact-update-form" class="form-horizontal" commandName="volunteer" action="" method="POST">
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
                    <div class="form-group">
                        <label class="control-label col-sm-3 col-md-2"></label>
                        <div class="col-sm-9 col-md-3">
                            <button id="submit" type="submit" class="btn btn-default btn-success">Submit</button>
                        </div>
                    </div>
                </fieldset>
            </form:form>
        </div>
        <div class="container">
            <p>Please note that from this page, only your contact details can be updated.</p>
            <p>To update any other other information, please contact Volunteer Service Desk. Your
                department overseer should be able to provide you with the necessary information.</p>
            <br/>
            <a href="<c:url value='/' />">Edifice Login Page</a>
        </div>
        <%@ include file="/WEB-INF/views/common/footer-min.jsp" %>
        <script type="text/javascript" src="<c:url value='/javascript/common.js' />"></script>
        <script type="text/javascript" src="<c:url value='/javascript/thirdparty/phoneformat-574.js' />" ></script>
        <script type="text/javascript" src="<c:url value='/javascript/volunteer-contact.js' />" ></script>
    </body>
</html>
