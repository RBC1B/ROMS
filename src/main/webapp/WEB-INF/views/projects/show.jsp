<%--
    Show the project details.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="pageTitle">Project #${project.projectId}: ${project.name}</c:set>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <div class="container-fluid">
            <h1>Project #${project.projectId}: ${project.name}</h1>
            <hr>
            <div class="clearfix"></div>
            <br />
            <dl class="dl-horizontal">
                <dt>Type:</dt><dd>${project.type}</dd>
                <dt>Status:</dt><dd>${project.status}</dd>
            </dl>
            <ul class="nav nav-tabs">
                <li class="active"><a href="#stages" data-toggle="tab">Stages</a></li>
                <li><a href="#details" data-toggle="tab">Details</a></li>
                <li><a href="#contacts" data-toggle="tab">Contacts</a></li>
            </ul>
            <div class="tab-content">
                <div class="tab-pane active" id="stages">
                    <div class="row-fluid">
                        <c:choose>
                            <c:when test="${!empty project.stages}">
                                <div id="project-stages" data-project-id="${project.projectId}">
                                    <c:forEach var="stage" items="${project.stages}">
                                        <%@ include file="fragments/show-stage.jsp" %>
                                    </c:forEach>
                                </div>
                            </c:when>
                            <c:otherwise>No stages defined</c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <div class="tab-pane" id="details">
                    <div class="row-fluid">
                        <%@ include file="fragments/show-details.jsp" %>
                    </div>
                </div>
                <div class="tab-pane" id="contacts">
                    <div class="row-fluid">
                        <%@ include file="fragments/show-contacts.jsp" %>
                    </div>
                </div>
            </div>
            <div class="clearfix"></div>
            <ol class="breadcrumb">
                <li><a href="<c:url value="/" />">Edifice</a></li>
                <sec:authorize access="hasPermission('PROJECT', 'READ')">
                  <li><a href="<c:url value="/projects" />">Projects</a></li>
                </sec:authorize>
                <li>#${project.projectId}: ${project.name}</li>
            </ol>
            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        </div>
        <script type="text/javascript" src="<c:url value='/javascript/projects.js' />" ></script>
    </body>
</html>
