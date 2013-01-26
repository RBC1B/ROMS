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
                <div class="tabbable"> <!-- Only required for left/right tabs -->
                    <ul class="nav nav-tabs">
                        <li class="active"><a href="#tab1" data-toggle="tab">Section 1</a></li>
                        <li><a href="#tab2" data-toggle="tab">Section 2</a></li>
                        <li><a href="#tab3" data-toggle="tab">Section 3</a></li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane active" id="tab1">
                            <div class="form">
                                <p>1(a) Legal name:</p>
                                <form:input path="person.forename" placeholder="First"/>
                                <form:input path="person.middleName" placeholder="Middle" />
                                <form:input path="person.surname" placeholder="Last"/>
                                <form:hidden path="person.personId" />
                            </div>
                            <br />
                            <div class="form">
                                <p>2. Full Postal Address: </p>
                                <form:input path="person.address.street" placeholder="Street"/>
                                <form:input path="person.address.town" placeholder="Town"/>
                                <form:input path="person.address.county" placeholder="County"/>
                                <form:input path="person.address.postcode" placeholder="Postcode"/>
                            </div>
                            <br />
                            <div class="form">
                                <p>3. Phones: </p>
                                <form:input path="person.telephone" placeholder="Home"/>
                                <form:input path="person.workPhone" placeholder="Work"/>
                                <form:input path="person.mobile" placeholder="Mobile"/>
                            </div>
                            <br />
                            <div class="form">
                                <p>4. Email: </p><form:input path="person.email" />
                            </div>
                        </div>
                        <div class="tab-pane" id="tab2">
                            <br />
                            <div class="form">
                                <p>5. Date of birth:</p>
                                <input class="datepicker" name="birthdate" type="text" placeholder="15/03/1980" data-date-format="dd/mm/yy">
                            </div>
                        </div>
                        <br />
                        <div class="form">
                            <p>6. Date of baptism:</p>
                            <input class="datepicker" name="baptismdate" type="text" placeholder="15/03/1980" data-date-format="dd/mm/yy">
                        </div>
                        <br />
                        <div class="form">
                            <p>7. Marital status:</p>
                        </div>
                        <div class="form">
                            <p>8. Gender</p>
                            <label class="radio inline">
                                <form:radiobutton path="gender" id="optionsRadios1" value="M" /> Male
                            </label>
                            <label class="radio inline">
                                <form:radiobutton path="gender" id="optionsRadios1" value="F" /> Female
                            </label>
                        </div>
                        <br />
                        <div class="form">
                            <p>9. Current privileges:</p>
                            <label class="checkbox inline">
                                <form:checkbox path="elder" />Elder
                            </label>
                            <label class="checkbox inline">
                                <form:checkbox path="ministerialServant" />MSP
                            </label>
                            <label class="checkbox inline">
                                <form:checkbox path="regularPioneer" />Regular pioneer
                            </label>
                        </div>
                        <br />
                    </div>
                    <br />
                    <div class="tab-pane" id="tab3">
                        <div class="form">
                            <p>10. Availability:</p>
                            TBD
                        </div>
                        <br>
                        <div class="form">
                            <p>11. Work background:</p>
                            TBD
                        </div>
                        <br>
                        <div class="form">
                            <p>12. In case of accident or illness notify:</p>
                            TBD
                            <form:select path="emergencyRelationshipId">
                                <form:option value="" label="-- Choose one--" />
                            </form:select>
                        </div>
                        <br>
                        <div class="form">
                            <p>Congregation</p>
                            <form:select path="congregationId">
                                <form:option value="" label="-- Choose one--" />
                            </form:select>
                        </div>
                        <br />
                        <div class="form">
                            <p>Form date</p>
                        </div>
                    </div>
                    <input type="submit" class="btn"/>
                </div>
            </div>
        </form:form>
        <%@ include file="/WEB-INF/views/common/footer.jsp" %>
    </div>
    <!-- mustache template used to display the person selection form -->
    <script id="person-search-form" type="text/html" charset="utf-8">
        {{#matchedVolunteers}}
        <p>Existing volunteers matched:</p>
        {{#volunteers}}
        <p><a href="<c:url value='/volunteers/{{personId}}'/>">{{forename}} {{surname}}{{#congregationName}}, {{congregationName}}{{/congregationName}}</a></p>
        {{/volunteers}}
        {{/matchedVolunteers}}
        {{#matchedPersons}}
        <p>Existing people (not volunteers) matched:</p>
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
