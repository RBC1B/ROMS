<%--
    Document   : edit
    Created on : Jun 4, 2013, 11:04:22 AM
    Author     : ramindursingh
--%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:choose>
        <c:when test="${!empty skillForm.name}">
            <c:set var="pageTitle" value="Edit skill" />
        </c:when>
        <c:otherwise>
            <c:set var="pageTitle" value="Create new skill" />
        </c:otherwise>
    </c:choose>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <c:choose>
            <c:when test="${!empty skillForm.name}">
                <h1>Edit skill</h1>
            </c:when>
            <c:otherwise>
                <h1>Create new skill</h1>
            </c:otherwise>
        </c:choose>
        <hr />
        <c:url var="formAction" value="${submitUri}" />
        <form:form commandName="skillForm" method="${submitMethod}" action="${formAction}">
                <fieldset>
                    <div class="row">
                        <div class="col-md-4">
                            <label>Name</label>
                            <form:input path="name" maxlength="50" />
                        </div>
                        <div class="col-md-4">
                            <label>Department</label>
                            <form:select path="departmentId">
                                <form:options items="${departments}" itemValues="department" itemLabel="name" itemValue="departmentId" />
                            </form:select>
                        </div>
                        <div class="col-md-4">
                            <label>Category</label>
                            <form:select path="skillCategoryId">
                              <form:options items="${categories}" itemValues="category" itemLabel="name" itemValue="skillCategoryId" />
                            </form:select>
                        </div>
                    </div>
                    <label>Description</label>
                    <form:textarea path="description" rows="4" cols="50"/>
                </fieldset>
            <input type="submit" class="btn btn-success" />
        </form:form>

        <ol class="breadcrumb">
            <li><a href="<c:url value="/" />">Edifice</a></li>
            <li><a href="<c:url value="/skills" />">Skills</a></li>
            <li>Edit</li>
        </ol>
        <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        <script type="text/javascript" src="<c:url value='/javascript/skills.js' />" ></script>
    </body>
</html>
