<%--
    Document   : volunteerEdit
    Created on : 14-Jul-2012, 00:54:53
    Author     : oliver.elder.esq
--%>F
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="pageTitle" value="Create/Edit Volunteer" />
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <div class="container-fluid">
            <h1>RBC Volunteer Application</h1>
            <c:url var="formAction" value="/volunteers" />
            <form:form commandName="volunteer" method="post" action="${formAction}" class="form-horizontal">
                <fieldset>
                    <div class="row">
                        <div class="span2">
                            <label class="control-label">1.(a) Legal name:</label>
                        </div>
                        <div class="span10">    
                            <div class="control-group">
                                <div class="controls">
                                    <form:input path="forename" placeholder="First"/>
                                </div>
                                <form:hidden path="personId" />
                            </div>
                            <div class="control-group">
                                <form:input path="middleName" placeholder="Middle" />
                            </div>
                            <div class="control-group">
                                <form:input path="surname" placeholder="Last"/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="span2">
                            <div class="control-group">
                                <label class="control-label">1.(b) Gender:</label>
                            </div>
                        </div>
                        <div class="span10">
                            <div class="controls">
                                <label class="radio inline">
                                    <form:radiobutton path="gender" value="M" /> Male
                                </label>
                                <label class="radio inline">
                                    <form:radiobutton path="gender" value="F" /> Female
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="span2">
                            <div class="control-group">
                                <label class="control-label">2.(a) Date of birth:</label>
                            </div>
                        </div>
                        <div class="span10">
                            <div class="controls">
                                <form:input class="datepicker" path="birthDate" placeholder="15/03/1980" data-date-format="dd/mm/yy" />
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="span2">
                            <div class="control-group">
                                <label class="control-label">2.(b) Date of baptism:</label>
                            </div>
                        </div>
                        <div class="span10">
                            <div class="controls form-inline">
                                <form:input class="datepicker" path="baptismDate" placeholder="15/03/1980" data-date-format="dd/mm/yy" />
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="span2">
                            <div class="control-group">
                                <label class="control-label">3. Addresses: </label>
                            </div>
                        </div>
                        <div class="span10">
                            <div class="controls">
                                <form:input path="street" placeholder="Street" />
                            </div>
                            <div class="control-group">
                                <form:input path="town" placeholder="Town" />
                            </div>
                            <div class="control-group">
                                <form:input path="county" placeholder="County" />
                            </div>
                            <div class="control-group">
                                <form:input path="postcode" maxlength="10" placeholder="Postcode" />
                            </div>
                            <div class="control-group">
                                <div class="controls">
                                    <form:input path="email" placeholder="Email" />
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="span2">
                            <div class="control-group">
                                <label class="control-label">4. Phones: </label>
                            </div>
                        </div>
                        <div class="span10">
                            <div class="controls">
                                <form:input path="telephone" placeholder="Home"/>
                            </div>
                            <div class="control-group">
                                <form:input path="workPhone" placeholder="Work"/>
                            </div>
                            <div class="control-group">
                                <form:input path="mobile" placeholder="Mobile"/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="span2">
                            <div class="control-group">
                                <label class="control-label">5. Current privileges:</label>
                            </div>    
                        </div>
                        <div class="span10">                         
                            <div class="controls">
                                <label class="checkbox inline">
                                    <form:checkbox path="elder" /> Elder
                                </label>
                                <label class="checkbox inline">
                                    <form:checkbox path="ministerialServant" /> MSP
                                </label>
                                <label class="checkbox inline">
                                    <form:checkbox path="regularPioneer" /> Regular&nbsp;pioneer
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="span2">
                            <div class="control-group">
                                <label class="control-label">6. Marital status:</label>
                            </div>    
                        </div>
                        <div class="span10"> 
                            <div class="controls">
                                <form:select path="maritalStatusId">
                                    <form:option value="" label="Select One" />
                                    <form:options items="${maritalStatusValues}" itemValue="key" itemLabel="value" />
                                </form:select>
                            </div>
                        </div>
                    </div>
                    <br />
                    <div class="row">
                        <div class="span2">                    
                            <p>7. Work background</p>
                        </div>
                        <div class="span10"><span class="alert">This area is handled in the next step.</span>
                        </div>
                    </div>                       
                    <div class="row">
                        <div class="span2">
                            <div class="control-group">
                                <label class="control-label">8.(a) In case of accident or illness notify:</label>
                            </div> 
                        </div>
                        <div class="span10"> 
                            <div class="controls">
                                <form:input path="emergencyContactForename" placeholder="First"/>
                            
                            <form:hidden path="emergencyContactPersonId" />
                        </div>
                        <div class="control-group">
                            <form:input path="emergencyContactSurname" placeholder="Last"/>
                        </div>
                        
                        <div class="control-group">
                            <div class="controls">
                                <form:select path="emergencyRelationshipId">
                                    <form:option value="" label="Relationship" />
                                    <form:options items="${relationshipValues}" itemValue="key" itemLabel="value" />
                                </form:select>
                            </div>
                        </div>
                        </div>
                        <p id="emergency-contact-linked" style="display:none;">Contact is linked to a person.</p>
                    </div><!--- / row --->
                        <div id="emergency-contact-additional-fields row">
                                <label class="control-label span2">8.(b) Phones:</label>
                                <div class="span10">
                                <div class="controls">
                                    <form:input path="emergencyContactTelephone" placeholder="Home"/>
                                </div>
                            <div class="control-group">
                                <form:input path="emergencyContactMobile" placeholder="Mobile"/>
                            </div>
                                </div><!-- /span10 --->
                                <div class="clearfix"></div>
                                <label class="control-label span2">8.(c) Address</label>
                                <div class="span10">
                                <div class="controls">
                                    <form:input path="emergencyContactStreet" placeholder="Street" />
                                </div>
                            <div class="control-group">
                                <form:input path="emergencyContactTown" placeholder="Town" />
                            </div>
                            <div class="control-group">
                                <form:input path="emergencyContactCounty" placeholder="County" />
                            </div>
                            <div class="control-group">
                                <form:input path="emergencyContactPostcode" maxlength="10" placeholder="Postcode" />
                            </div>
                            </div>
            </div><!--- / row --->
            <div class="clearfix"></div>
            <p>&nbsp;</p>
            <div class="row">
                <div class="span2">
                    <div class="control-group">
                        <label class="control-label">Congregation</label>
                    </div>
                </div>
                <div class="span10"> 
                    <div class="controls">
                        <form:input path="congregationName" placeholder="Congregation name" />
                        <form:hidden path="congregationId" />
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="span2">
                    <div class="control-group">
                        <label class="control-label">Form date</label>
                    </div>
                </div>
                <div class="span10"> 
                    <div class="controls">
                        <input class="datepicker" name="formDate" type="text" placeholder="15/03/1980" data-date-format="dd/mm/yy">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="span2 offset2">
                    <div class="control-group">
                        <div class="controls">
                            <input type="submit" class="btn btn-large btn-success"/>
                        </div>
                    </div>
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
<div id="volunteer-person-modal" class="modal hide fade">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h3>Select matching person</h3>
    </div>
    <div class="modal-body">
    </div>
    <div class="modal-footer">
        <a href="#" class="btn" data-dismiss="modal" aria-hidden="true">Ignore</a>
    </div>
</div>
<script type="text/javascript" charset="utf-8" src="<c:url value='/javascript/volunteers.js' />" ></script>
</body>
<!-- mustache template used to display the emergency contqct person selection form -->
<script id="volunteer-emergency-contact-search-form" type="text/html" charset="utf-8">
    {{#existingPersonId}}
    <p>The contact is already linked to {{existingPersonName}}</p>
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
<script type="text/javascript" charset="utf-8" src="<c:url value='/javascript/volunteers.js' />" ></script>
</body>
</html>
