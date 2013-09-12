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
    <c:set var="pageTitle" value="Edit New or Existing Skill" />
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <div class="container-fluid">
            <h1>Qualification</h1>
            <hr>
            <c:url var="formAction" value="/skills" />
            <form:form commandName="skill" method="post" action="${formAction}">
                <div class="form">
                    <fieldset class="container-fluid">
                        <p>Skill Name</p>
                        <form:input path="name" placeholder="Use 'Department - Skill' format" />
                        <p>Description</p>
                        <form:input path="description" placeholder="Description" />
                        <p>Department</p>
                        <form:select path="departmentId">
                            <form:options items="${departments}" itemValues="department" itemLabel="name" itemValue="departmentId" />
                        </form:select>
                        <p>Category</p>
                        <form:select path="skillCategoryId">
                            <form:options items="${categories}" itemValues="category" itemLabel="name" itemValue="skillCategoryId" />
                        </form:select>
                    </fieldset>
                </div>
                <input type="submit" class="btn btn-primary" />
            </form:form>
            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        </div>
        <script type="text/javascript" src="<c:url value='/javascript/skill.js' />" ></script>
    </body>
</html>
