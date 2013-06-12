<%-- 
    Document   : show
    Created on : Jun 4, 2013, 8:49:12 AM
    Author     : ramindursingh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <c:set var="pageTitle" value="Skills" />
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <div class="container-fluid">
            <h1>Skills</h1>
            <hr>
            <fieldset class="container-fluid">
                <p>Skill ID</p>
                <p>${skill.skillId}</p>
                <p>Skill</p>
                <p>${skill.name}</p>
                <p>Description</p>
                <p>${skill.description}</p>
                <p>Department</p>
                <p>${skill.department.name}</p>
                <p>Category</p>
                <p>${skill.category.name}</p>
                <p>Does it appear on ID badge?</p>
                <p>${skill.category.appearOnBadge}</p>
                <p>If appears on badge, in what colour</p>
                <p>${skill.category.colour}</p>
            </fieldset>
            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        </div>
        <script type="text/javascript" src="<c:url value='/javascript/skills.js' />" >
        </script>
    </body>
</html>
