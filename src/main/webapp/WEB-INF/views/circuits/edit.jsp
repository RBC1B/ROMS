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
            <c:url var="formAction" value="/circuits" />
            <form:form commandName="circuit" method="post" action="${formAction}" class="form-horizontal">
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
                                    <form:input path="forename" placeholder = "First Name"/> 
                                </div>
                                <div class="span2">
                                    <form:input path="middleName" placeholder = "Middle Name"/> 
                                </div>
                                <div class="span2">
                                    <form:input path="surname" placeholder = "Surname"/>
                                </div>
                            </div>
                        </fieldset>
                        <fieldset class="control-group">      
                            <label class="control-label">Email:</label>
                            <div class="controls controls-row">
                                <div class="span2">
                                    <form:input path="email" placeholder = "E-mail"/>
                                </div>
                            </div>
                        </fieldset>
                        <fieldset class="control-group">
                            <label class="control-label">Address:</label>
                            <div class="controls controls-row">
                                <div class="span3">
                                    <form:input path="street" placeholder = "Street"/>
                                </div>
                                <div class="span2">
                                    <form:input path="town" placeholder = "Town"/>
                                </div>
                                <div class="span1">
                                    <form:input path="county" placeholder = "County"/>
                                </div>
                                <div class="span1">
                                    <form:input path="postcode" maxlength="10" placeholder = "Postcode"/>
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
                    <input type="submit" class="btn btn-primary" />
                </div>
            </form:form>
            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        </div>
        <div id="circuit-person-modal" class="modal hide fade">
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
