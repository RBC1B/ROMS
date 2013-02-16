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
    <c:set var="pageTitle" value="Create/Edit Volunteer" />
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <div class="container-fluid">
            <h1>RBC Volunteer Application</h1>
            <c:url var="formAction" value="/volunteers" />
            <form:form commandName="volunteer" method="post" action="${formAction}">
                <fieldset>
                    <label>1.(a) Legal name:</label>
                    <form:input path="forename" placeholder="First"/>
                    <form:input path="middleName" placeholder="Middle" />
                    <form:input path="surname" placeholder="Last"/>
                    <form:hidden path="personId" />
                </fieldset>
                <fieldset>
                    <label>1.(b) Gender:</label>
                    <label class="radio inline">
                        <form:radiobutton path="gender" value="M" /> Male
                    </label>
                    <label class="radio inline">
                        <form:radiobutton path="gender" value="F" /> Female
                    </label>
                </fieldset>
                <fieldset>
                    <div class="pull-left" id="volunteer-birthdate">
                        <label>2.(a) Date of birth:</label>
                        <form:input class="datepicker" path="birthDate" placeholder="15/03/1980" data-date-format="dd/mm/yy" />
                    </div>
                    <div id="volunteer-baptismdate">
                        <label>2.(b) Date of baptism:</label>
                        <form:input class="datepicker" path="baptismDate" placeholder="15/03/1980" data-date-format="dd/mm/yy" />
                    </div>
                </fieldset>
                <fieldset>
                    <label>3. Addresses: </label>
                    <form:input path="street" placeholder="Street" />
                    <form:input path="town" placeholder="Town" />
                    <form:input path="county" placeholder="County" />
                    <form:input path="postcode" size="10" maxlength="10" placeholder="Postcode" />
                </fieldset>
                <fieldset>
                    <form:input path="email" placeholder="Email" />
                </fieldset>
                <fieldset>
                    <label>4. Phones: </label>
                    <form:input path="telephone" placeholder="Home"/>
                    <form:input path="workPhone" placeholder="Work"/>
                    <form:input path="mobile" placeholder="Mobile"/>
                </fieldset>
                <fieldset>
                    <label>5. Current privileges:</label>
                    <label class="checkbox inline">
                        <form:checkbox path="elder" /> Elder
                    </label>
                    <label class="checkbox inline">
                        <form:checkbox path="ministerialServant" /> MSP
                    </label>
                    <label class="checkbox inline">
                        <form:checkbox path="regularPioneer" /> Regular&nbsp;pioneer
                    </label>
                </fieldset>

                <fieldset>
                    <label>6. Marital status:</label>
                    <form:select path="maritalStatusId">
                        <form:option value="" label="Select One" />
                        <form:options items="${maritalStatusValues}" itemValue="key" itemLabel="value" />
                    </form:select>
                </fieldset>
                <fieldset>
                    <label>7. Work background is handled in the next step</label>
                </fieldset>

                <fieldset>
                    <label>10. Availability:</label>
                    TBD
                </fieldset>

                <fieldset>
                    <label>12. In case of accident or illness notify:</label>
                    TBD
                    <form:select path="emergencyRelationshipId">
                        <form:option value="" label="-- Choose one--" />
                    </form:select>
                </fieldset>
                <fieldset>
                    <label>Congregation</label>
                    <form:select path="congregationId">
                        <form:option value="" label="-- Choose one--" />
                    </form:select>
                </fieldset>
                <fieldset>
                    <label>Form date</label>
                    <input class="datepicker" name="formdate" type="text" placeholder="15/03/1980" data-date-format="dd/mm/yy">
                </fieldset>
                <input type="submit" class="btn btn-success"/>

            </div>
        </div>
    </form:form>
    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- mustache template used to display the person selection form -->
<script id="person-search-form" type="text/html" charset="utf-8">
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
<script type="text/javascript" charset="utf-8" src="<c:url value='/javascript/mustache.js' />" ></script>
<script type="text/javascript" charset="utf-8" src="<c:url value='/javascript/volunteers.js' />" ></script>
<script type="text/javascript" charset="utf-8" src="<c:url value='/javascript/jquery-ui-1.10.0.custom.min.js' />" ></script>
</body>
</html>
