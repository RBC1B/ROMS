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

                     <script type="text/javascript">$('.datepicker').datepicker()</script>
                     <div class="input-append date" id="dpMonths" data-date="102/2012" data-date-format="mm/yyyy" data-date-viewmode="years" data-date-minviewmode="months">
                     <input class="span2" size="16" type="text" value="02/2012" readonly="">
		     <span class="add-on"><i class="icon-calendar"></i></span>
		     </div>
                    
                    
                    
                <input type="text" class="span2" value="02/16/12" data-date-format="mm/dd/yy" id="dp2">
                
                
                </div>
                </div>
                <br>
                <div class="form">
                    <p>6. Date of baptism:</p>
                </div>
                <div class="tab-pane" id="tab2">
                <br>
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
                 <br>
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
                </div>                               <br>
                </div>
                <br>
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
                <br>
                <div class="form">
                    <p>Form date</p>  
                </div>
                </div>
                <input type="submit" class="btn"/>
            </form:form>
            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        </div>
       <script type="text/javascript" charset="utf8" src="<c:url value='/javascript/circuits.js' />" ></script>
    </body>
</html>
