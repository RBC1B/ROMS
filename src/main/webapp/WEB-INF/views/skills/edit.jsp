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
            <c:set var="pageTitle" value="Edit Skill" />
        </c:when>
        <c:otherwise>
            <c:set var="pageTitle" value="Create Skill" />
        </c:otherwise>
    </c:choose>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
            <c:choose>
                <c:when test="${!empty skillForm.name}">
                    <h1>Edit Skill</h1>
                </c:when>
                <c:otherwise>
                    <h1>Create New Skill</h1>
                </c:otherwise>
            </c:choose>
            <hr>
            <c:url var="formAction" value="${submitUri}" />
            <form:form commandName="skillForm" method="${submitMethod}" action="${formAction}">
                <div class="form">
                    <fieldset class="container-fluid">
                        <label>Skill Name</label>
                        <form:input path="name" maxlength="50" />
                        <label>Description</label>
                        <form:textarea path="description" />
                        <label>Department</label>
                        <form:select path="departmentId">
                            <form:options items="${departments}" itemValues="department" itemLabel="name" itemValue="departmentId" />
                        </form:select>
                        <label>Category</label>
                        <form:select path="skillCategoryId">
                            <form:options items="${categories}" itemValues="category" itemLabel="name" itemValue="skillCategoryId" />
                        </form:select>
                    </fieldset>
                </div>
                <input type="submit" class="btn btn-primary" />
            </form:form>
                

            <ol class="breadcrumb">
                <li><a href="<c:url value="/" />">Edifice</a></li>
                <sec:authorize access="hasPermission('SKILL', 'READ')">
                  <li><a href="<c:url value="/skills" />">Skills</a></li>
                </sec:authorize>
                <li>Edit</li>
            </ol>                
     
            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        <script type="text/javascript" src="<c:url value='/javascript/skills.js' />" ></script>
    </body>
</html>
