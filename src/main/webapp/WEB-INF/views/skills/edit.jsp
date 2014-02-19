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
            <c:set var="pageTitle">Edit skill - ${skillForm.name}</c:set>
        </c:when>
        <c:otherwise>
            <c:set var="pageTitle">Create new skill</c:set>
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
        <form:form class="form-horizontal" commandName="skillForm" method="${submitMethod}" action="${formAction}">
            <fieldset>
                <div class="form-group">
                    <label for="name" class="control-label col-sm-3 col-md-2">Name</label>
                    <div class="col-sm-9 col-md-3">
                        <form:input path="name" class="form-control" maxlength="50" />
                        <form:hidden path="skillId" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="departmentId" class="control-label col-sm-3 col-md-2">Department</label>
                    <div class="col-sm-9 col-md-2">
                        <form:select path="departmentId" class="form-control" >
                            <form:options items="${departments}" itemValues="department" itemLabel="name" itemValue="departmentId" />
                        </form:select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="skillCategoryId" class="control-label col-sm-3 col-md-2">Category</label>
                    <div class="col-sm-9 col-md-2">
                        <form:select path="skillCategoryId" class="form-control" >
                            <form:options items="${categories}" itemValues="category" itemLabel="name" itemValue="skillCategoryId" />
                        </form:select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="description" class="control-label col-sm-3 col-md-2">Description</label>
                    <div class="col-sm-9 col-md-3">
                         <form:textarea path="description" class="form-control" rows="4" cols="50" />
                    </div>
                </div>
            </fieldset>
            <fieldset>
                <div class="form-group">
                    <label class="control-label col-sm-3 col-md-2"></label>
                    <div class="col-sm-9 col-md-3">
                        <button type="submit" class="btn btn-default btn-success">Submit</button>
                    </div>
                </div>
            </fieldset>
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
