<%--
    Document   : volunteerEdit
    Created on : 14-Jul-2012, 00:54:53
    Author     : oliver.elder.esq
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="pageTitle" value="Create Volunteer" />
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <div class="container-fluid">
            <h1>RBC Volunteer Application</h1>
            <hr>
            <c:url var="formAction" value="/volunteers" />
            <form:form commandName="volunteer" method="post" action="${formAction}" class="form-horizontal">
                <fieldset class="control-group">
                    <form:hidden path="personId" />
                    <label class="control-label">1.(a) Legal name:</label>
                    <div class="controls controls-row">
                        <div class="span2">
                            <form:input path="surname" maxlength="50" placeholder="Last"/>
                        </div>,
                        <div class="span2">
                            <form:input path="forename" maxlength="50" placeholder="First"/>
                        </div>
                        <div class="span2">
                            <form:input path="middleName" maxlength="50" placeholder="Middle" />
                        </div>
                    </div>
                </fieldset>
                <fieldset class="control-group">
                    <label class="control-label">1.(b) Gender:</label>
                    <div class="controls controls-row">
                        <div class="span3 error-container">
                            <label class="radio inline">
                                <form:radiobutton path="gender" value="M" /> Male
                            </label>
                            <label class="radio inline">
                                <form:radiobutton path="gender" value="F" /> Female
                            </label>
                        </div>
                    </div>
                </fieldset>
                <fieldset class="control-group">
                    <label class="control-label">2.(a) Date of birth:</label>
                    <div class="controls controls-row">
                        <div class="span2">
                            <form:input path="birthDate" placeholder="15/03/1980" class="datepicker" data-date-format="dd/mm/yy" type="text" value=""/>
                        </div>
                    </div>
                </fieldset>
                <fieldset class="control-group">
                    <label class="control-label">2.(b) Date of baptism:</label>
                    <div class="controls controls-row">
                        <div class="span2">
                            <form:input path="baptismDate" placeholder="15/03/1980" class="datepicker" data-date-format="dd/mm/yy" type="text" value=""/>
                        </div>
                    </div>
                </fieldset>
                <fieldset class="control-group">
                    <label class="control-label">3. Addresses: </label>
                    <div class="controls controls-row">
                        <div class="span3">
                            <form:input path="street" placeholder="Street" />
                        </div>
                        <div class="span2">
                            <form:input path="town" placeholder="Town" />
                        </div>
                        <div class="span1">
                            <form:input path="county" placeholder="County" />
                        </div>
                        <div class="span1">
                            <form:input path="postcode" placeholder="Postcode" maxlength="10" />
                        </div>
                    </div>
                </fieldset>
                <fieldset class="control-group">
                    <label class="control-label"></label>
                    <div class="controls controls-row">
                        <div class="span3">
                            <form:input path="email" placeholder="Email" />
                        </div>
                    </div>
                </fieldset>
                <fieldset class="control-group">
                    <label class="control-label">4. Phones: </label>
                    <div class="controls controls-row">
                        <div class="span2">
                            <form:input path="telephone" placeholder="Home"/>
                        </div>
                        <div class="span2">
                            <form:input path="workPhone" placeholder="Work"/>
                        </div>
                        <div class="span2">
                            <form:input path="mobile" placeholder="Mobile"/>
                        </div>
                    </div>
                </fieldset>
                <fieldset class="control-group">
                    <label class="control-label">5. Current privileges:</label>
                    <div class="controls controls-row">
                        <div class="span3 error-container">
                            <label class="checkbox inline">
                                <form:checkbox path="elder" /> Elder
                            </label>
                            <label class="checkbox inline">
                                <form:checkbox path="ministerialServant" /> Ministerial&nbsp;Servant
                            </label>
                        </div>
                        <div class="span3 error-container">
                            <label class="checkbox inline">
                                <form:checkbox path="regularPioneer" /> Regular&nbsp;pioneer
                            </label>
                        </div>
                    </div>
                </fieldset>
                <fieldset class="control-group">
                    <form:hidden path="spousePersonId" />
                    <label class="control-label">6. Name of mate, if married:</label>
                    <div class="controls controls-row">
                        <div class="span2">
                            <form:input path="spouseForename" placeholder="First Name"/>
                        </div>
                        <div class="span2">
                            <form:input path="spouseSurname" placeholder="Surname"/>
                        </div>
                    </div>
                    <div id="spouse-linked" class="controls alert span10" style="display:none;">
                        <button type="button" class="close">Unlink</button>
                        Linked to an existing person in the database
                    </div>
                </fieldset>
                <fieldset class="control-group">
                    <label class="control-label">7. Work background</label>
                    <div class="controls controls-row trades-row" data-index="0">
                        <div class="span2">
                            <input type="text" name="trades[0].name" class="trade-experience-name" placeholder="Name" />
                        </div>
                        <div class="span6">
                            <input type="text" name="trades[0].experienceDescription" class="trade-experience-description" placeholder="Description"/>
                        </div>
                        <div class="span1">
                            <input type="text" name="trades[0].experienceYears" class="trade-experience-years" placeholder="Years"/>
                        </div>
                        <div class="span1">
                            <button type="button" class="btn btn-danger trades-row-delete"><i class="icon-remove icon-white"></i></button>
                        </div>
                    </div>
                    <div class="controls controls-row">
                        <div class="span2">
                            <button id="trades-row-add" type="button" class="btn"><i class="icon-align-left icon-plus"></i> Add trade</button>
                        </div>
                    </div>
                </fieldset>
                <fieldset class="control-group">
                    <form:hidden path="emergencyContactPersonId" />
                    <label class="control-label">8.(a) In case of accident or illness notify:</label>
                    <div class="controls controls-row">
                        <div class="span2">
                            <form:input path="emergencyContactForename" placeholder="First Name"/>
                        </div>
                        <div class="span2">
                            <form:input path="emergencyContactSurname" placeholder="Surname"/>
                        </div>
                        <div class="span2">
                            <form:select path="emergencyRelationshipId">
                                <form:option value="" label="Relationship" />
                                <form:options items="${relationshipValues}" />
                            </form:select>
                        </div>
                    </div>
                    <div id="emergency-contact-linked" class="controls alert span10" style="display:none;">
                        <button type="button" class="close">Unlink</button>
                        Linked to an existing person in the database
                    </div>
                </fieldset>
                <div id="emergency-contact-additional-fields">
                    <fieldset class="control-group">
                        <label class="control-label">8.(b) Phones:</label>
                        <div class="controls controls-row">
                            <div class="span2">
                                <form:input path="emergencyContactTelephone" placeholder="Home"/>
                            </div>
                            <div class="span2">
                                <form:input path="emergencyContactMobile" placeholder="Mobile"/>
                            </div>
                        </div>
                    </fieldset>
                    <fieldset class="control-group">
                        <label class="control-label">8.(c) Address</label>
                        <div class="controls controls-row">
                            <div class="span3">
                                <form:input path="emergencyContactStreet" placeholder="Street" />
                            </div>
                            <div class="span2">
                                <form:input path="emergencyContactTown" placeholder="Town" />
                            </div>
                            <div class="span1">
                                <form:input path="emergencyContactCounty" placeholder="County" />
                            </div>
                            <div class="span1">
                                <form:input path="emergencyContactPostcode" maxlength="10" placeholder="Postcode" />
                            </div>
                        </div>
                    </fieldset>
                </div>
                <fieldset class="control-group">
                    <label class="control-label">Congregation:</label>
                    <div class="controls controls-row">
                        <div class="span2">
                            <form:input path="congregationName" placeholder="Congregation name" autocomplete="off" />
                            <form:hidden path="congregationId" />
                        </div>
                    </div>
                </fieldset>
                <fieldset class="control-group">
                    <label class="control-label">Form date:</label>
                    <div class="controls controls-row">
                        <div class="span2">
                            <form:input path="formDate" placeholder="15/03/1980" class="datepicker" data-date-format="dd/mm/yy" />
                        </div>
                    </div>
                </fieldset>
                <fieldset class="control-group">
                    <label class="control-label"></label>
                    <div class="controls controls-row">
                        <div class="span2">
                            <input type="submit" class="btn btn-large btn-success"/>
                        </div>
                    </div>
                </fieldset>
            </form:form>
            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        </div>
        <!-- mustache template used to display the person selection form -->
        <script id="volunteer-person-search-form" type="text/html" charset="utf-8">
            {{#existingPersonId}}
            <p>You are already linked to {{existingPersonName}}</p>
            <p><a href="#" class="matched-person" data-person-id="{{existingPersonId}}">Leave linked to {{existingPersonName}} (same as ignore)</a></p>
            <p><a href="#" class="matched-person" data-person-id="">Unlink {{existingPersonName}} (create a new person)</a></p>
            {{/existingPersonId}}
            {{#matchedVolunteers}}
            <p>Edit an existing volunteer:</p>
            {{#volunteers}}
            <p><a href="<c:url value='/volunteers/{{personId}}'/>">{{forename}} {{surname}}{{#congregationName}}, {{congregationName}}{{/congregationName}}</a></p>
            {{/volunteers}}
            {{/matchedVolunteers}}
            {{#matchedPersons}}
            <p>Link to an existing person (not currently a volunteer):</p>
            {{#persons}}
            <a href="#" class="matched-person" data-person-id="{{personId}}">{{forename}} {{surname}}{{#congregationName}}, {{congregationName}}{{/congregationName}}</a>
            {{/persons}}
            {{/matchedPersons}}
        </script>
        <%@ include file="/WEB-INF/views/common/mustache-person-link-search-form.jsp" %>
        <%@ include file="/WEB-INF/views/common/person-link-modal.jsp" %>
        <script type="text/javascript" src="<c:url value='/javascript/thirdparty/jquery-numeric-1.3.1.js' />" ></script>
        <script type="text/javascript" src="<c:url value='/javascript/volunteers.js' />" ></script>
    </body>
</html>
