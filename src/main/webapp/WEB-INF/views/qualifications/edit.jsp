<%--
   Create or edit a qualification
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:choose>
        <c:when test="${!empty qualificationForm.name}">
            <c:set var="pageTitle" value="Edit qualification" />
        </c:when>
        <c:otherwise>
            <c:set var="pageTitle" value="Create new qualification" />
        </c:otherwise>
    </c:choose>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
            <c:choose>
                <c:when test="${!empty qualificationForm.name}">
                    <h1>Edit qualification</h1>
                </c:when>
                <c:otherwise>
                    <h1>Create new qualification</h1>
                </c:otherwise>
            </c:choose>
            <hr />
            <c:url var="formAction" value="${submitUri}" />
            <form:form commandName="qualificationForm" method="${submitMethod}" action="${formAction}">
                <fieldset>
                    <label>Name</label>
                    <form:input path="name" maxlength="50" />
                    <label>Description</label>
                    <form:textarea path="description" rows="4" cols="50" />
                </fieldset>
                <input type="submit" class="btn btn-edifice" />
            </form:form>

            <ol class="breadcrumb">
                <li><a href="<c:url value="/" />">Edifice</a></li>
                <li role="menuitem"><a href="<c:url value="/qualifications" />">Qualifications</a></li>
                <li class="active">Edit</li>
            </ol>

            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        <script type="text/javascript" src="<c:url value='/javascript/qualifications.js' />" ></script>
    </body>
</html>
