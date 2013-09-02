<%--
    Form use to edit or create congregations.
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<c:choose>
    <c:when test="${!empty congregationForm.name}">
        <c:set var="pageTitle" value="Edit Congregation" />
    </c:when>
    <c:otherwise>
        <c:set var="pageTitle" value="Create Congregation" />
    </c:otherwise>
</c:choose>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<body>
    <%@ include file="/WEB-INF/views/common/titlebar.jsp"%>
    <div class="container-fluid">
        <c:choose>
            <c:when test="${!empty congregationForm.name}">
                <h1>Edit Congregation</h1>
            </c:when>
            <c:otherwise>
                <h1>Create New Congregation</h1>
            </c:otherwise>
        </c:choose>
        <hr>
        <c:url var="formAction" value="${submitUri}" />
        <form:form commandName="congregationForm" method="${submitMethod}" action="${formAction}">
            <fieldset>
                <label>Congregation name</label>
                <form:input path="name" />
                <label>Number</label>
                <form:input path="number" />
                <label>Kingdom hall</label>
                <form:select path="kingdomHallId">
                    <form:option value="" />
                    <form:options items="${kingdomHalls}" itemValue="kingdomHallId" itemLabel="name" />
                </form:select>
                <label>Circuit</label>
                <form:select path="circuitId">
                    <form:option value="" />
                    <form:options items="${circuits}" itemValue="circuitId" itemLabel="name" />
                </form:select>
                <label>RBC region</label>
                <form:select path="rbcRegionId">
                    <form:option value="" />
                    <form:options items="${rbcRegions}" />
                </form:select>
                <label>RBC sub-region</label>
                <form:select path="rbcSubRegionId">
                    <form:option value="" />
                    <form:options items="${rbcSubRegions}" />
                </form:select>
                <label>Publishers</label>
                <form:input path="publishers" />
                <label>Attendance</label>
                <form:input path="attendance" />
            </fieldset>
            <fieldset>
                <legend>Contacts</legend>
                <label>CoBE</label>
                <label>Secretary</label>
            </fieldset>
            <fieldset>
                <legend>Strategy</legend>
                <label>Funds</label>
                <form:input path="funds" />
                <label>Loans</label>
                <form:input path="loans" />
                <label>Monthly income</label>
                <form:input path="monthlyIncome" />
                <label>Strategy</label>
                <form:textarea path="strategy" />
            </fieldset>
            <input type="submit" class="btn btn-primary" />
        </form:form>
        <%@ include file="/WEB-INF/views/common/footer.jsp"%>
    </div>
    <script type="text/javascript" src="<c:url value='/javascript/congregations.js' />"></script>
</body>
</html>
