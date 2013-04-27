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
            <form:form commandName="circuit" method="post" action="${formAction}">
                <div class="form">
                    <div class="form">
                        <p>Circuit</p>
                        <fieldset class="control-group">
                            <form:input path="circuitId" readonly="true" placeholder="${circuitId}"/>
                            <form:input path="name" placeholder="Circuit Name"/>
                        </fieldset>
                        <p>Circuit Overseer</p>
                        <form:input path="personId" readonly="true" placeholder="${personId}" />
                        <form:input path="forename" placeholder = "First Name"/>
                        <form:input path="middleName" placeholder = "Middle Name"/>
                        <form:input path="surname" placeholder = "Surname"/>
                        <form:input path="email" placeholder = "E-mail"/>
                        <form:input path="street" placeholder = "Street"/>
                        <form:input path="town" placeholder = "Town"/>
                        <form:input path="county" placeholder = "County"/>
                        <form:input path="postcode" placeholder = "Post Code"/>
                        <form:input path="telephone" placeholder = "Telephone Number"/>
                        <form:input path="mobile" placeholder = "Mobile Number"/>
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
        <script type="text/javascript" charset="utf-8" src="<c:url value='/javascript/circuits.js' />" ></script>
    </body>
</html>
