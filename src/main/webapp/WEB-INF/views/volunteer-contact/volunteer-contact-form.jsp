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
        <div class="container"
             <h1>RBC London and Home Counties</h1>
        </div>
        <div class="container">
            <div class="container">
                Contact Information for <c:out value="${contactUpdateModel.surname}"/>, <c:out value="${contactUpdateModel.forename}"/>
            </div>
            <form id="contact-update-form" class="form-horizontal" commandName="contactUpdateModel" action="${submitUrl}" method="${submitMethod}">
                <fieldset>
                    <legend>Contacts</legend>
                    <div class="form-group">
                        <label for="email" class="control-label col-sm-3 col-md-2">Email</label>
                        <div class="col-sm-9 col-md-3">
                            <form:input path="contactUpdateModel.email" class="form-control" maxlength="50" placeholder="Email Address" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="telephone" class="control-label col-sm-3 col-md-2">Home phone</label>
                        <div class="col-sm-9 col-md-2">
                            <form:input path="contactUpdateModel.telephone" class="form-control" maxlength="15" placeholder="Home Phone" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="mobile" class="control-label col-sm-3 col-md-2">Mobile phone</label>
                        <div class="col-sm-9 col-md-2">
                            <form:input path="contactUpdateModel.mobile" class="form-control" maxlength="15" placeholder="Mobile" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="workPhone" class="control-label col-sm-3 col-md-2">Work phone</label>
                        <div class="col-sm-9 col-md-2">
                            <form:input path="contactUpdateModel.workPhone" class="form-control" maxlength="15" placeholder="Work Phone" />
                        </div>
                    </div>
                </fieldset>
                <fieldset>
                    <legend>Address</legend>
                    <div class="form-group">
                        <label for="street" class="control-label col-sm-3 col-md-2">Street</label>
                        <div class="col-sm-9 col-md-3">
                            <form:input path="contactUpdateModel.street" class="form-control" maxlength="50" placeholder="Street" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="town" class="control-label col-sm-3 col-md-2">Town</label>
                        <div class="col-sm-9 col-md-3">
                            <form:input path="contactUpdateModel.town" class="form-control" maxlength="50" placeholder="Town" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="county" class="control-label col-sm-3 col-md-2">County</label>
                        <div class="col-sm-9 col-md-3">
                            <form:input path="contactUpdateModel.county" class="form-control" maxlength="50" placeholder="County" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="postcode" class="control-label col-sm-3 col-md-2">Postcode</label>
                        <div class="col-sm-9 col-md-2">
                            <form:input path="contactUpdateModel.postcode" class="form-control" maxlength="10" placeholder="Postcode" />
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
            </form>
        </div>
        <div class="modal fade" id="volunteer-contact-update-success" tabindex="-1" role="dialog" aria-labelledby="volunteer-comments-modal-success-label" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">x</button>
                        <h4 class="modal-title" id="volunteer-contact-modal-form-label">Update Request</h4>
                    </div>
                    <div class="modal-body">
                        <div class="alert alert-success"><p><strong>Success: </strong>Your request has been submitted. You will receive a confirmation email shortly.</p></div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-edifice" aria-hidden="true" data-dismiss="modal">OK</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <p>Please note that from this page, only your contact details can be updated.</p>
            <p>To update any other other information, please contact Volunteer Service Desk. Your
                department overseer should be able to provide you with the necessary information.</p>
            <br/>
            <a href="/">Edifice Login Page</a>
        </div>
        <%@ include file="/WEB-INF/views/common/footer-min.jsp" %>
        <script type="text/javascript" src="<c:url value='/javascript/common.js' />"></script>
        <script type="text/javascript" src="<c:url value='/javascript/thirdparty/phoneformat-574.js' />" ></script>
        <script type="text/javascript" src="<c:url value='/javascript/volunteer-contact.js' />" ></script>
    </body>
</html>
