<%-- 
    Document   : show-emergency-contacts
    Created on : 14-Jun-2014, 17:13:19
    Author     : Graham Pointer
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="volunteer-with-emergency-contacts" <c:if test="${empty emergencyContacts}">style="display: none"</c:if>>
    <h3>Emergency contact</h3>
    <table class="table table-bordered table-condensed table-striped table-hover" id="volunteer-emergency-contacts">
        <thead>
            <tr>
                <th>Name</th>
                <th>Relationship</th>
                <th>Home Number</th>
                <th>Mobile Number</th>
                <th>Work Number</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${emergencyContacts}" var="emergencyContact">
                <tr data-emergency-contact-id="${emergencyContact.id}"
                    data-emergency-contact-surname="${emergencyContact.person.surname}"
                    data-emergency-contact-forename="${emergencyContact.person.forename}">
                    <td><a href="<c:url value="${emergencyContact.person.uri}" />"><c:out
                                value="${emergencyContact.person.displayName}" /></a></td>
                    <td>${emergencyContact.relationship}</td>
                    <td>${emergencyContact.person.telephone}</td>
                    <td>${emergencyContact.person.mobile}</td>
                    <td>${emergencyContact.person.workPhone}</td>
                    <td><sec:authorize access="hasPermission('VOLUNTEER', 'EDIT')">
                            <ul class="list-inline">
                                <li><a class="a-edit-emergency-contact" href="<c:url value="${emergencyContact.uri}" />">Edit</a></li>
                                <li><a class="a-delete-emergency-contact"
                                    data-ajax-url="<c:url value="${emergencyContact.uri}" />" href="#"> Delete</a></li>
                            </ul>
                        </sec:authorize>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<div id="volunteer-without-emergency-contacts" <c:if test="${!empty emergencyContacts}">style="display: none"</c:if>>
    <br />
    <div class="alert alert-warning">Volunteer has not got an emergency contact</div>
    <%-- for the time being, only showing button to add new emergency contacts if
         none are already shown. Once multiple emergency contacts are allowed,
         the '<a>' below can simply be moved to just above the <script>
         in the second <sec:authorize> block. --%>
    <sec:authorize access="hasPermission('VOLUNTEER', 'EDIT')">
        <a class="btn btn-edifice" href="<c:url value="${volunteer.editEmergencyContactUri}"/>" id="a-add-emergency-contact">Add emergency contact</a><br />
    </sec:authorize>
</div>
<sec:authorize access="hasPermission('VOLUNTEER', 'EDIT')">
    <script id="volunteer-emergency-contacts-action-template" type="text/html" charset="utf-8">
        <ul class="list-inline">
            <li><a class="a-edit-emergency-contact" href="{{emergencyContactUri}}">Edit</a></li>
            <li><a class="a-delete-emergency-contact" data-ajax-url="{{emergencyContactUri}}" href="#"> Delete</a></li>
        </ul>
    </script>
</sec:authorize>
