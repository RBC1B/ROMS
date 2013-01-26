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
    <c:set var="pageTitle" value="Create/Edit Circuit" />
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <div class="container-fluid">
            <h1>Circuit</h1>
            <c:url var="formAction" value="/circuits" />
            <form:form commandName="circuit" method="post" action="${formAction}">
                <div class="form">
                    <input class="input-append" id="disabledInput" type="text" placeholder="Cicuit ID" disabled><br />
                    <div class="form">
                        <p>Circuit</p>
                        <form:input path="name" placeholder="Circuit Name"/>
                        <p>Circuit Overseer</p>
                        <form:input path="person.forename" placeholder = "First Name" />
                        <form:input path="person.middleName" placeholder = "Middle Name"/>
                        <form:input path="person.surname" placeholder = "Surname"/>
                        <form:input path="person.email" placeholder = "E-mail"/>
                        <form:input path="person.address.street" placeholder = "Street"/>
                        <form:input path="person.address.town" placeholder = "Town"/>
                        <form:input path="person.address.county" placeholder = "County"/>
                        <form:input path="person.address.postcode" placeholder = "Post Code"/>
                        <form:input path="person.telephone" placeholder = "Telephone Number"/>
                        <form:input path="person.mobile" placeholder = "Mobile Number"/>
                    </div>
                    <input type="submit" class="btn btn-primary" />
                </div>
            </form:form>
            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        </div>
        <script type="text/javascript" charset="utf-8" src="<c:url value='/javascript/circuits.js' />" ></script>
    </body>
</html>
