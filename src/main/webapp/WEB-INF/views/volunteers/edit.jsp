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
                <div>
                    <p>Indicate form(s) of service with which you are willing to assist:</p>
                    <!--form:checkbox path="" /> -->
                </div>
                <div class="form">
                    <p>1(a) Legal name:</p> 
                    <form:input path="person.forename" placeholder="First"/>
                    <form:input path="person.middleName" placeholder="Middle" />
                    <form:input path="person.surname" placeholder="Last"/>
                </div>
                <br>
                <div class="form">
                    <p>2. Full Postal Address: </p>
                    <form:input path="person.address.street" placeholder="Street"/>
                    <form:input path="person.address.town" placeholder="Town"/>
                    <form:input path="person.address.county" placeholder="County"/>
                    <form:input path="person.address.postcode" placeholder="Postcode"/>
                </div>
                <br>
                <div class="form">
                    <p>3. Phones: </p>
                    <form:input path="person.telephone" placeholder="Home"/>
                    <form:input path="person.workPhone" placeholder="Work"/>
                    <form:input path="person.mobile" placeholder="Mobile"/>
                </div>
                <br>
                <div class="form">
                    <p>4. Email: </p><form:input path="person.email" />
                </div>
                <br>
                <div class="form">
                    <p>5. Date of birth:</p>
                    <input data-datepicker="datepicker" class="small" type="text" value="01/05/2011" />
                </div>
                </div>
                <br>
                <div class="form">
                    <p>6. Date of baptism:</p>
                    
                    <script>$('.datepicker').datepicker()</script>
                    <input type="text" class="span2" value="02/16/12" data-date-format="mm/dd/yy" id="dp2">                    
                    
                    
                    
                </div>
                <br>
                <div class="form">
                    <p>7. Marital status:</p>
                </div>
                 <div class="form">
                     <p>8. Gender</p> 
                    <form:radiobutton path="gender" value="M" /> Male
                    <form:radiobutton path="gender" value="F" /> Female
                </div>               
                 <br>
                <div class="form">
                    <p>9. Current privileges:</p> 
                    <form:checkbox path="elder" />Elder
                    <form:checkbox path="ministerialServant" />Ministerial servant
                    <form:checkbox path="regularPioneer" />Regular pioneer
                </div>                               <br>
                <br>
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
                <br>
                <div class="form">
                    <p>Form date</p>
                    
                </div>
                <input type="submit" class="btn"/>
            </form:form>
            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        </div>
       <script type="text/javascript" charset="utf8" src="<c:url value='/javascript/circuits.js' />" ></script>
    </body>
</html>
