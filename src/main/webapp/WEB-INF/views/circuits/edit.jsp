<%--
    Document   : circuitEdit/Create
    Created on : 14-Jul-2012, 00:54:53
    Author     : oliver.elder.esq
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:choose>
        <c:when test="${!empty circuitForm.name}">
            <c:set var="pageTitle" value="Edit Circuit" />
        </c:when>
        <c:otherwise>
            <c:set var="pageTitle" value="Create Circuit" />
        </c:otherwise>
    </c:choose>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <c:choose>
            <c:when test="${!empty circuitForm.name}">
                <h1>Edit Circuit</h1>
            </c:when>
            <c:otherwise>
                <h1>Create New Circuit</h1>
            </c:otherwise>
        </c:choose>
        <hr>
        <c:url var="formAction" value="${submitUri}" />
        <form:form commandName="circuitForm" method="${submitMethod}" action="${formAction}">
            <fieldset>
                <legend>Circuit Details</legend>
                <label>Circuit Name</label>
                <form:input path="name" maxlength="50" placeholder="Circuit Name"/>
            </fieldset>
            <br />
            <fieldset>
                <legend>Circuit Overseer Details</legend>
                <div class="controls controls-row">
                    <div id="circuit-overseer-linked" class="controls alert" style="display:none;">
                        <button type="button" class="close">Unlink</button>
                        Linked to an existing person in the database
                    </div>
                </div>
                <form:hidden path="personId" />
                <div class="control-group">
                    <label>Name Details:</label>
                    <form:input path="forename" maxlength="50" placeholder="First Name"/>
                    <form:input path="middleName" maxlength="50" placeholder="Middle Name" />
                    <form:input path="surname" maxlength="50" placeholder="Surname"/>
                </div>
                <div class="control-group">
                    <label>Email:</label>
                    <form:input path="email" maxlength="50" placeholder="E-mail"/>
                </div>
                <div class="control-group">
                    <label>Address:</label>
                    <form:input path="street" maxlength="70" placeholder="Street"/>
                    <form:input path="town" maxlength="30" placeholder="Town"/>
                    <form:input path="county" maxlength="50" placeholder="County"/>
                    <form:input path="postcode" maxlength="10" placeholder="Postcode"/>
                </div>
                <div class="control-group">
                    <label>Phones:</label>
                    <form:input path="telephone" maxlength="20" placeholder="Telephone Number"/>
                    <form:input path="mobile" maxlength="20" placeholder="Mobile Number"/>
                </div>
                <c:choose>
                    <c:when test="${circuitForm.forename != null && circuitForm.surname != null}">
                        <fieldset>
                            <div class="controls controls-row">
                                <div class="alert alert-info span9" id="edit-circuit-overseer-person" style="display:none;">
                                    <p id="co-link">
                                        <script id="edit-circuit-overseer-person-link" type="text/html" charset="utf-8">
                                            Click this link if you would like to edit additional fields of the Circuit Overseer
                                            <a href="<c:url value='/persons/{{personId}}/edit'/>"><b>{{forename}} {{surname}}</b>
                                                </script>
                                        </p>
                                    </div>
                                </div>
                            </fieldset>
                        </c:when>
                    </c:choose>
                    <div class="control-group">
                        <div class="controls controls-row">
                            <input type="submit" class="btn btn-edifice" />
                        </div>
                    </div>
                    <br />
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
                            <h3>Select Matching Person</h3>
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
