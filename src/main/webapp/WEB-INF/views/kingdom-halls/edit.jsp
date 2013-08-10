<%--
    Document   : kingdomhallEdit
    Created on : 08-Sep-2012, 17:46:53
    Author     : oliver.elder.esq
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="pageTitle" value="Create/Edit Kingdom Hall" />
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <div class="container-fluid">
        <h1>Kingdom Hall</h1>
        <hr>
            <c:url var="formAction" value="/kingdom-halls" />
            <form:form commandName="kingdomHall" method="post" action="${formAction}">
                <input class="input-append" id="disabledInput" type="text" placeholder="Kingdom Hall ID" disabled><br>
                <form:label path="*"><form:input path="*" placeholder="Kingdom Hall Name" /></form:label>
                <form:label path="*"><form:input path="*" placeholder="Kingdom Hall Street" /></form:label>
                <form:label path="*"><form:input path="*" placeholder="Kingdom Hall Town" /></form:label>
                <form:label path="*"><form:input path="*" placeholder="Kingdom Hall County" /></form:label>
                <form:label path="*"><form:input path="*" placeholder="Kingdom Hall Postcode" /></form:label>
                <form:input path="*" type="text" name="*" placeholder="Title Holder" value="" id="searchinput" data-provide="typeahead" data-source="congregation.Name" maxlength="30" autocomplete="on"  /><br>
                <input type="submit" class="btn btn-primary"/>
            </form:form>
        <%@ include file="/WEB-INF/views/common/footer.jsp" %>
       </div>
     <script type="text/javascript" src="<c:url value='/javascript/kingdom-halls.js' />" ></script>
    </body>
</html>
