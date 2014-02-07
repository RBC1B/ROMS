<%--
    Create or edit a circuit.
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:choose>
        <c:when test="${!empty circuitForm.name}">
            <c:set var="pageTitle">Edit circuit - ${circuitForm.name}</c:set>
        </c:when>
        <c:otherwise>
            <c:set var="pageTitle">Create new circuit"</c:set>
        </c:otherwise>
    </c:choose>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <c:choose>
            <c:when test="${!empty circuitForm.name}">
                <h1>Edit circuit</h1>
            </c:when>
            <c:otherwise>
                <h1>Create new circuit</h1>
            </c:otherwise>
        </c:choose>
        <hr />
        <c:url var="formAction" value="${submitUri}" />
        <form:form commandName="circuitForm" method="${submitMethod}" action="${formAction}" role="form">
            <fieldset>
                <form:hidden path="personId" />
                <div class="row">
                    <div class="col-md-3">
                        <div class="form-group">
                            <label for="name">Name</label>
                            <form:input path="name" class="form-control" placeholder="Circuit Name" maxlength="50" /><br />
                        </div>
                    </div>
                </div>
            </fieldset>
            <fieldset>
            <div class="form-group">
                    <div class="row">
                        <div class="col-md-12">
                            <h3>Circuit overseer details</h3>
                        </div>
                        <div class="col-md-12">
                            <div id="circuit-overseer-linked" class="alert alert-warning alert-dismissable" style="display:none;">
                                <button type="button" class="close" data-dismiss="alert">Unlink</button>
                                Linked to an existing person in the database
                            </div>
                        </div>
                        <div class="col-md-12">
                            <label>Name</label>
                        </div>
                        <div class="col-md-3">
                                <form:input path="forename" class="form-control" maxlength="50" placeholder="Forename"/>
                        </div>
                        <div class="col-md-3">
                                <form:input path="middleName" class="form-control" maxlength="50" placeholder="Middle name" />
                        </div>
                        <div class="col-md-3">
                                <form:input path="surname" class="form-control" maxlength="50" placeholder="Surname"/>
                        </div>
                    </div>
            </div>
            <div class="form-group">
                    <div class="row">
                        <div class="col-md-12">
                            <h4 class="text-left">Address</h4>
                        </div>
                        <div class="col-md-3">
                            <label for="street">Street</label>
                            <form:input path="street" class="form-control" maxlength="70" placeholder="Street"/>
                        </div>
                        <div class="col-md-3">
                            <label for="town">Town</label>
                            <form:input path="town" class="form-control" maxlength="30" placeholder="Town"/>
                        </div>
                        <div class="col-md-3">
                            <label for="county">County</label>
                            <form:input path="county" class="form-control" maxlength="50" placeholder="County"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-3">
                            <label for="postcode">Postcode</label>
                            <form:input path="postcode" class="form-control" maxlength="10" placeholder="Postcode"/>
                        </div>
                    </div>
            </div>
            <div class="form-group">
                    <div class="row">
                        <div class="col-md-12">
                            <h4>Email</h4>
                        </div>
                        <div class="col-md-3">
                                <form:input path="email" class="form-control" maxlength="50" placeholder="Email"/>
                        </div>
                    </div>
            </div>
            <div class="form-group">
                    <div class="row">
                        <div class="col-md-12">
                            <h4>Phone</h4>
                        </div>
                        <div class="col-md-3">
                            <label for="telephone">Home phone</label>
                            <form:input path="telephone" class="form-control" maxlength="20" placeholder="Home phone"/>
                        </div>
                        <div class="col-md-3">
                            <label for="mobile">Mobile phone</label>
                            <form:input path="mobile" class="form-control" maxlength="20" placeholder="Mobile phone"/>
                        </div>
                        <div class="clearfix"></div>
                        <sec:authorize access="hasPermission('VOLUNTEER', 'EDIT')">
                            <c:choose>
                                <c:when test="${circuitForm.forename != null && circuitForm.surname != null}">
                                    <fieldset>
                                        <div class="form-group">
                                            <div class="row">
                                                <div class="alert alert-info col-md-9" id="edit-circuit-overseer-person" style="display:none;">
                                                    <p id="co-link">
                                                        <script id="edit-circuit-overseer-person-link" type="text/html" charset="utf-8">
                                                            Click this link if you would like to edit additional fields of the Circuit Overseer
                                                            <a href="<c:url value='/persons/{{personId}}/edit'/>"><b>{{forename}} {{surname}}</b>
                                                        </script>
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                    </fieldset>
                                </c:when>
                            </c:choose>
                        </sec:authorize>
                        <br />
                        <div class="col-md-12">
                            <input type="submit" class="btn btn-edifice"/>
                        </div>
                    </div>
                </div>
            </fieldset>
        </form:form>

            <ol class="breadcrumb">
                <li><a href="<c:url value="/" />">Edifice</a></li>
                <li><a href="<c:url value="/circuits" />">Circuits</a></li>
                <li class="active">Edit</li>
            </ol>

            <%@ include file="/WEB-INF/views/common/footer.jsp" %>

            <!-- Mustache template for displaying the person link, use of mustaches {{}} -->
            <script id="circuit-overseer-link-form" type="text/html">
                {{#existingPersonId}}
                <p>The name is already linked to <b>{{existingPersonName}}</b></p>
                <p><a href="#" class="matched-person" data-person-id="{{existingPersonId}}">Leave linked to {{existingPersonName}} (same as ignore)</a></p>
                <p><a href="#" class="matched-person" data-person-id="">Unlink {{existingPersonName}} (create a new person)</a></p>
                {{/existingPersonId}}
                {{#matchedPersons}}
                <p>Link to an existing person:</p>
                {{#results}}
                <a href="#" class="matched-person" data-person-id="{{personId}}">{{forename}} {{surname}}{{#congregationName}}, {{congregationName}}{{/congregationName}}</a>
                {{/results}}
                {{/matchedPersons}}
            </script>

            <div id="circuit-overseer-modal" class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h3>Select matching person</h3>
                        </div>
                        <div class="modal-body">

                        </div>
                        <div class="modal-footer">
                            <a href="#" class="btn" data-dismiss="modal">Ignore</a>
                        </div>
                    </div>
                </div>
            </div>

            <script type="text/javascript" src="<c:url value='/javascript/thirdparty/phoneformat-574.js' />" ></script>
            <script type="text/javascript" src="<c:url value='/javascript/circuits.js' />" ></script>
        </body>
    </html>
