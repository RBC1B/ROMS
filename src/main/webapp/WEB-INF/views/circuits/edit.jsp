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
    <c:set var="pageTitle" value="Create/Edit Circuit" />
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <div class="container-fluid">
            <h1>Circuit</h1>
            <hr>
            <c:url var="formAction" value="/circuits" />
            <form:form commandName="circuitForm" method="post" action="${formAction}" class="form-horizontal">
                <div class="form">
                    <div class="form">
                        <h4>Circuit details</h4>
                        <fieldset class="control-group">
                            <label class="control-label">Circuit Id:</label>
                            <div class="controls controls-row">
                                <div class="span1">
                                    <form:input path="circuitId" readonly="true" placeholder="${circuitId}"/>
                                </div>
                            </div>
                        </fieldset>
                        <fieldset class="control-group">
                            <label class="control-label">Circuit Name:</label>
                            <div class="controls controls-row">
                                <div class="span2">
                                    <form:input path="name" placeholder="Circuit Name"/>
                                </div>
                            </div>
                        </fieldset>
                        <fieldset class="control-group">
                            <h4>Circuit Overseer details</h4>
                        </fieldset>
                        <fieldset class="control-group">        
                            <div id="circuit-overseer-linked" class="controls alert span10" style="display:none;">
                                <button type="button" class="close">Unlink</button>
                                Linked to an existing person in the database
                            </div>
                        </fieldset>
                        <fieldset class="control-group">
                            <label class="control-label">Circuit Overseer Id:</label>
                            <div class="controls controls-row">
                                <div class="span1">
                                    <form:input path="personId" readonly="true" placeholder="${personId}" />
                                </div>
                            </div>
                        </fieldset>
                        <fieldset class="control-group">
                            <label class="control-label">Name Details:</label>
                            <div class="controls controls-row">
                                <div class="span2">
                                    <form:input path="forename" placeholder="First Name"/> 
                                </div>
                                <div class="span2">
                                    <form:input path="middleName" placeholder="Middle Name"/> 
                                </div>
                                <div class="span2">
                                    <form:input path="surname" placeholder="Surname"/>
                                </div>
                            </div>
                        </fieldset>
                        <div id="circuit-overseer-additional-fields">
                            <fieldset class="control-group">      
                                <label class="control-label">Email:</label>
                                <div class="controls controls-row">
                                    <div class="span2">
                                        <form:input path="email" placeholder="E-mail"/>
                                    </div>
                                </div>
                            </fieldset>
                            <fieldset class="control-group">
                                <label class="control-label">Address:</label>
                                <div class="controls controls-row">
                                    <div class="span3">
                                        <form:input path="street" placeholder="Street"/>
                                    </div>
                                    <div class="span2">
                                        <form:input path="town" placeholder="Town"/>
                                    </div>
                                    <div class="span1">
                                        <form:input path="county" placeholder="County"/>
                                    </div>
                                    <div class="span1">
                                        <form:input path="postcode" maxlength="10" placeholder="Postcode"/>
                                    </div>
                                </div>
                            </fieldset>
                            <fieldset class="control-group">
                                <label class="control-label">Phones:</label>
                                <div class="controls controls-row">
                                    <div class="span2">
                                        <form:input path="telephone" placeholder = "Telephone Number"/>
                                    </div>
                                    <div class="span2">
                                        <form:input path="mobile" placeholder = "Mobile Number"/>
                                    </div>
                                </div>
                            </fieldset>
                        </div>
                        <c:choose>
                            <c:when test="${circuitForm.forename != null && circuitForm.surname != null}">
                                <fieldset class="control-group">
                                    <div class="controls controls-row">
                                        <div class="alert alert-info span9" id="edit-circuit-overseer-person" style="display:none;">
                                            Click this link if you would like to edit the circuit overseer 
                                            <a href="<c:url value="/persons/${circuitForm.personId}/edit"/>"><b>${circuitForm.forename} ${circuitForm.surname}</b></a>
                                        </div>
                                    </div>
                                </fieldset>
                            </c:when>
                        </c:choose>
                        <fieldset class="control-group">
                            <div class="controls controls-row">
                                <input type="submit" class="btn btn-primary" />
                            </div>
                        </fieldset>
                    </div>
                </div>
            </form:form>
            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        </div>

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

        <div id="circuit-overseer-modal" class="modal hide fade">
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
        <script type="text/javascript" src="<c:url value='/javascript/circuits.js' />" ></script>
    </body>
</html>
