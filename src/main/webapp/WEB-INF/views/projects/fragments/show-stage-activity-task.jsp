<%--
    Show an individual project stage activity tasks
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div id="stage-${stage.id}-activity-${activity.id}-task-${task.id}" class="panel panel-task">
    <div class="panel-heading">
        <div class="project-stage-type-name col-sm-4"><h4>${task.name}</h4></div>
        <div class="project-stage-status col-sm-2"><h4>${task.status}</h4></div>
        <div class="drag-move pull-right">
            <span class="glyphicon glyphicon-align-justify"></span>
        </div>
        <div class="clearfix"></div>
    </div>
    <div class="panel-body">
        <div class="col-sm-2">
            <div>
                <c:choose>
                    <c:when test="${!empty task.createdTime}">
                        <fmt:formatDate value="${task.createdTime}" pattern="yyyy-MM-dd" />
                    </c:when>
                    <c:otherwise>&nbsp;</c:otherwise>
                </c:choose>
            </div>
        </div>
        <div class="col-sm-2">
            <div>
                <c:choose>
                    <c:when test="${!empty task.startedTime}">
                        <fmt:formatDate value="${task.startedTime}" pattern="yyyy-MM-dd" />
                    </c:when>
                    <c:otherwise>&nbsp;</c:otherwise>
                </c:choose>
            </div>
        </div>
        <div class="col-sm-2">
            <div>
                <c:choose>
                    <c:when test="${!empty task.completedTime}">
                        <fmt:formatDate value="${task.completedTime}" pattern="yyyy-MM-dd" />
                    </c:when>
                    <c:otherwise>&nbsp;</c:otherwise>
                </c:choose>
            </div>
        </div>
        <div class="col-sm-2">
            <div>
                <c:choose>
                    <c:when test="${!empty task.assignedVolunteer}">
                        <a class="a-project-assignment" href="${task.assignedVolunteer.uri}"
                           data-toggle="tooltip" data-original-title="${task.assignedVolunteer.displayName}">
                            ${task.assignedVolunteer.initials}
                        </a>
                    </c:when>
                    <c:otherwise>Unassigned</c:otherwise>
                </c:choose>
            </div>
        </div>
        <div class="a-accordian-wrapper">
            <c:choose>
                <c:when test="${task.isInProgress()}">
                    <c:set var="accordionOpenClass">in</c:set>
                    <c:set var="accordionIconClass">glyphicon-minus</c:set>
                </c:when>
                <c:otherwise>
                    <c:set var="accordionOpenClass"></c:set>
                    <c:set var="accordionIconClass">glyphicon-plus</c:set>
                </c:otherwise>
            </c:choose>
            <button type="button"
                    class="btn btn-edifice pull-right a-accordian-control"
                    data-target="#collapse-stage-${stage.id}-activity-${activity.id}-task-${task.id}">
                <span class="glyphicon ${accordionIconClass}"></span>
            </button>
            <div class="clearfix"></div>
            <div class="accordion" id="accordion-stage-${stage.id}-activity-${activity.id}-task-${task.id}">
                <div class="accordion-group">
                    <div id="collapse-stage-${stage.id}-activity-${activity.id}-task-${task.id}" class="accordion-body collapse ${accordionOpenClass}">
                        <br>
                        <c:if test="${!empty task.comments}">
                            <p>${task.comments}</p>
                        </c:if>
                        <c:if test="${!empty task.events}">
                            <h3>Events</h3>
                            <div class="list-group">
                                <c:forEach var="event" items="${task.events}">
                                    <%@ include file="show-event.jsp" %>
                                </c:forEach>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
