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
            <c:set var="pageTitle" value="Edit Qualification" />
        </c:when>
        <c:otherwise>
            <c:set var="pageTitle" value="Create Qualification" />
        </c:otherwise>
    </c:choose>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <div class="container-fluid">
            <c:choose>
                <c:when test="${!empty qualificationForm.name}">
                    <h1>Edit Qualification</h1>
                </c:when>
                <c:otherwise>
                    <h1>Create New Qualification</h1>
                </c:otherwise>
            </c:choose>
            <hr>
            <c:url var="formAction" value="${submitUri}" />
            <form:form commandName="qualificationForm" method="${submitMethod}" action="${formAction}">
                <fieldset>
                    <label>Qualification name</label>
                    <form:input path="name" maxlength="50" />
                    <label>Description</label>
                    <form:textarea path="description" />
                </fieldset>
                <input type="submit" class="btn btn-primary" />
            </form:form>
            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        </div>
        <script type="text/javascript" src="<c:url value='/javascript/qualifications.js' />" ></script>
    </body>
</html>
