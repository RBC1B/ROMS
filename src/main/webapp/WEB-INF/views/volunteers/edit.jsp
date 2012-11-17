<%--
    Document   : circuitEdit
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
                <div>
                    Indicate form(s) of service with which you are willing to assist:
                    <!--form:checkbox path="" /> -->
                </div>
                <div>1(a) Legal name: 
                    Last <form:input path="person.surname" />
                    First <form:input path="person.forename" />
                    Middle <form:input path="person.middleName" />
                </div>
                <div>1(b) Gender 
                    <form:radiobutton path="gender" value="M" /> Male
                    <form:radiobutton path="gender" value="F" /> Female
                </div>    
                <div>
                    2(a) Date of birth:
                </div>
                <div>
                    2(b) Date of baptism:
                </div>
                <div>
                    3. Addresses: 
                    Street <form:input path="person.address.street" />
                    Town <form:input path="person.address.town" />
                    County <form:input path="person.address.county" />
                    Postcode <form:input path="person.address.postcode" />
                </div>
                <div>
                    Email: <form:input path="person.email" />
                </div>
                <div>
                    Phones: 
                    Home: <form:input path="person.telephone" />
                    Work: <form:input path="person.workPhone" />
                    Mobile: <form:input path="person.mobile" />
                </div>
                <div>
                    5. Current privileges: 
                    <form:checkbox path="elder" />Elder
                    <form:checkbox path="ministerialServant" />Ministerial servant
                    <form:checkbox path="regularPioneer" />Regular pioneer
                </div>
                <div>
                    6. Name of mate if married: ???
                </div>
                <div>
                    7. Work background:
                    TBD
                </div>
                <div>
                    8. In case of accident or illness notify:
                    TBD
                    <form:select path="emergencyRelationshipId">
                        <form:option value="" label="-- Choose one--" />
                    </form:select>
                </div>
                <div>
                    Congregation
                    <form:select path="congregationId">
                        <form:option value="" label="-- Choose one--" />
                    </form:select>
                </div>
                <div>
                    Form date
                    
                </div>
                <input type="submit" />
            </form:form>
            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        </div>
    </body>
</html>
