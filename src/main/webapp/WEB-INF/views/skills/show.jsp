<%--
    Display the skill details.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<c:set var="pageTitle" value="Skill: ${skill.name}" />
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<body>
    <%@ include file="/WEB-INF/views/common/titlebar.jsp"%>
    <div class="container-fluid">
        <h1>Skill: ${skill.name}</h1>
        <hr>
        <dl class="dl-horizontal">
            <dt>Description:</dt>
            <dd>
                <c:choose>
                    <c:when test="${!empty skill.description}">${skill.description}</c:when>
                    <c:otherwise>-</c:otherwise>
                </c:choose>
            </dd>
            <dt>Department</dt>
            <dd>
                <a href="<c:url value='${skill.department.uri}' />">${skill.department.name}</a>
            </dd>
            <dt>Category:</dt>
            <dd>
                <c:choose>
                    <c:when test="${!empty skill.category}">
                        <c:choose>
                            <c:when test="${!empty skill.category.colour}">
                                <c:set var="categoryColour" value="${skill.category.colour}" />
                            </c:when>
                            <c:otherwise>
                                <c:set var="categoryColour" value="white" />
                            </c:otherwise>
                        </c:choose>
                        <div class="skill-category-colour" style="background-color:${categoryColour}"></div>
                        ${skill.category.name}
                    </c:when>
                    <c:otherwise>-</c:otherwise>
                </c:choose>
            </dd>
            <dt>Appear on badge?:</dt>
            <dd>
                <c:choose>
                    <c:when test="${!empty skill.category && skill.category.appearOnBadge}">
                        <span class="icon-ok"></span>
                    </c:when>
                    <c:otherwise>
                        <span class="icon-remove"></span>
                    </c:otherwise>
                </c:choose>
            </dd>
        </dl>
        <div class="clearfix"></div>
        <%@ include file="/WEB-INF/views/common/footer.jsp"%>
    </div>
    <script type="text/javascript" src="<c:url value='/javascript/skills.js' />"></script>
</body>
</html>
