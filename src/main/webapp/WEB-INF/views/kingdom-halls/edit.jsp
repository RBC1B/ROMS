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
            <c:url var="formAction" value="/kingdom-halls" />
            <form:form commandName="kingdomHall" method="post" action="${formAction}">
                <form:label path="name">Name <form:input path="name" /></form:label>
                <input type="submit" />
            </form:form>
        <%@ include file="/WEB-INF/views/common/footer.jsp" %>
       </div>
     <script type="text/javascript" charset="utf8" src="<c:url value='/javascript/circuits.js' />" ></script>
    </body>
</html>
