<%--
    Show the project details.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
            <div class="span6">
                <h2>Details</h2>
                <dl class="dl-horizontal">
                    <dt>Type:</dt><dd>${project.type}</dd>
                    <dt>Status:</dt><dd>${project.status}</dd>
                    <dt>Priority:</dt><dd>${project.priority}</dd>
                    <dt>Type:</dt><dd>${project.type}</dd>
                    <c:if test="${project.requestDate != null}">
                        <dt>Request Date:</dt><dd><fmt:formatDate value="${project.requestDate}" pattern="yyyy-MM-dd" /></dd>
                    </c:if>
                    <c:if test="${project.visitDate != null}">
                        <dt>Visit Date:</dt><dd><fmt:formatDate value="${project.visitDate}" pattern="yyyy-MM-dd" /></dd>
                    </c:if>
                    <c:if test="${project.completedDate != null}">
                        <dt>Completed Date:</dt><dd><fmt:formatDate value="${project.completedDate}" pattern="yyyy-MM-dd" /></dd>
                    </c:if>
                    <dt>Estimate Cost:</dt><dd>${project.estimateCost}</dd>
                    <c:if test="${project.kingdomHall != null}">
                        <dt>Kingdom Hall</dt><dd><a href="<c:url value='${project.kingdomHall.uri}' />">${project.kingdomHall.name}</a></dd>
                    </c:if>
                    <c:if test="${project.address != null}">
                        <dt>Address:</dt>
                        <dd>
                            <address>
                                <c:if test="${project.address.street != null}">${project.address.street}<br/></c:if>
                                <c:if test="${project.address.town != null}">${project.address.town}<br/></c:if>
                                <c:if test="${project.address.county != null}">${project.address.county}<br/></c:if>
                                <c:if test="${project.address.postcode != null}">${project.address.postcode}<br/></c:if>
                            </address>
                        </dd>
                    </c:if>
                    <c:if test="${project.supportingCongregation != null}">
                        <dt>Supporting Cong:</dt><dd>${project.supportingCongregation}</dd>
                    </c:if>
                    <c:if test="${project.constraints != null}">
                        <dt>Constraints:</dt><dd>${project.constraints}</dd>
                    </c:if>
                </dl>
            </div>
            <div class="span6">
                <h2>Contact</h2>
                <dl class="dl-horizontal">
                    <c:if test="${project.telephone != null}">
                        <dt>Telephone:</dt><dd>${project.telephone}</dd>
                    </c:if>
                    <c:if test="${project.contactPerson != null}">
                        <h3>Contact Person</h3>
                        <dt>Name:</dt>
                        <dd><a href="<c:url value='${project.contactPerson.uri}' />">${project.contactPerson.displayName}</a></dd>
                        <dt>Email:</dt>
                        <dd>
                            <c:if test="${project.contactPerson.email != null}">
                                <a href="mailto:${project.contactPerson.email}">${project.contactPerson.email}</a>
                            </c:if>
                        </dd>
                        <c:if test="${project.contactPerson.telephone != null}">
                            <dt>Home phone:</dt><dd>${project.contactPerson.telephone}</dd>
                        </c:if>
                        <c:if test="${project.contactPerson.telephone != null}">
                            <dt>Mobile phone:</dt><dd>${project.contactPerson.mobile}</dd>
                        </c:if>
                        <c:if test="${project.contactPerson.telephone != null}">
                            <dt>Work phone:</dt><dd>${project.contactPerson.workPhone}</dd>
                        </c:if>
                    </c:if>
                    <c:if test="${project.coordinator != null}">
                        <h3>Coordinator</h3>
                        <dt>Name:</dt>
                        <dd><a href="<c:url value='${project.coordinator.uri}' />">${project.coordinator.displayName}</a></dd>
                        <dt>Email:</dt>
                        <dd>
                            <c:if test="${project.coordinator.email != null}">
                                <a href="mailto:${project.coordinator.email}">${project.coordinator.email}</a>
                            </c:if>
                        </dd>
                        <c:if test="${project.coordinator.telephone != null}">
                            <dt>Home phone:</dt><dd>${project.coordinator.telephone}</dd>
                        </c:if>
                        <c:if test="${project.coordinator.telephone != null}">
                            <dt>Mobile phone:</dt><dd>${project.coordinator.mobile}</dd>
                        </c:if>
                        <c:if test="${project.coordinator.telephone != null}">
                            <dt>Work phone:</dt><dd>${project.coordinator.workPhone}</dd>
                        </c:if>
                    </c:if>
                </dl>
            </div>
            <div class="clearfix"></div>

            <c:choose>
                <c:when test="${!empty project.stages}">
                    <div id="project-stages">
                        <c:forEach var="stage" items="${project.stages}">
                            <%@ include file="fragments/show-stage.jsp" %>
                        </c:forEach>
                    </div>
                </c:when>
                <c:otherwise>No stages defined</c:otherwise>
            </c:choose>

            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        </div>
        <script type="text/javascript" src="<c:url value='/javascript/projects.js' />" ></script>
    </body>
</html>