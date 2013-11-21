<%--
    Show an individual project stage activity
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div id="stage-${stage.id}-activity-${activity.id}" class="panel panel-stage">
    <div class="panel-heading">
        <div class="project-stage-type-name col-sm-4"><h4>${activity.type.name}: ${activity.type.description}</h4></div>
        <div class="project-stage-status col-sm-2"><h4>${activity.status}</h4></div>
        <div class="drag-move pull-right">
            <span class="glyphicon glyphicon-align-justify"></span>
        </div>
        <div class="clearfix"></div>
    </div>
    <div class="panel-body">
        <div class="col-sm-2">
            <div>
                <c:choose>
                    <c:when test="${!empty activity.createdTime}">
                        <fmt:formatDate value="${activity.createdTime}" pattern="yyyy-MM-dd" />
                    </c:when>
                    <c:otherwise>&nbsp;</c:otherwise>
                </c:choose>
            </div>
        </div>
        <div class="col-sm-2">
            <div>
                <c:choose>
                    <c:when test="${!empty activity.startedTime}">
                        <fmt:formatDate value="${activity.startedTime}" pattern="yyyy-MM-dd" />
                    </c:when>
                    <c:otherwise>&nbsp;</c:otherwise>
                </c:choose>
            </div>
        </div>
        <div class="col-sm-2">
            <div>
                <c:choose>
                    <c:when test="${!empty activity.completedTime}">
                        <fmt:formatDate value="${activity.completedTime}" pattern="yyyy-MM-dd" />
                    </c:when>
                    <c:otherwise>&nbsp;</c:otherwise>
                </c:choose>
            </div>
        </div>
        <div class="col-sm-2">
            <div>
                <c:choose>
                    <c:when test="${!empty activity.assignedVolunteer}">
                        <a class="a-project-assignment" href="${activity.assignedVolunteer.uri}"
                           data-toggle="tooltip" data-original-title="${activity.assignedVolunteer.displayName}">
                            ${activity.assignedVolunteer.initials}
                        </a>
                    </c:when>
                    <c:otherwise>Unassigned</c:otherwise>
                </c:choose>
            </div>
        </div>
        <div class="col-sm-2">
            <div class="project-counts">
                <div class="project-count a-project-count" title="" data-toggle="tooltip"
                     data-original-title="Not started: ${activity.createdTaskCount} of ${activity.totalTaskCount}">
                    <span class="badge badge-important">${activity.createdTaskCount}</span>
                </div>
                <div class="project-count a-project-count" title="" data-toggle="tooltip"
                     data-original-title="In progress: ${activity.startedTaskCount} of ${activity.totalTaskCount}">
                    <span class="badge badge-warning">${activity.startedTaskCount}</span>
                </div>
                <div class="project-count a-project-count" title="" data-toggle="tooltip"
                     data-original-title="Completed: ${activity.completedTaskCount} of ${activity.totalTaskCount}">
                    <span class="badge badge-success">${activity.completedTaskCount}</span>
                </div>
            </div>
        </div>
        <div class="a-accordian-wrapper">
            <c:choose>
                <c:when test="${activity.isInProgress()}">
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
                    data-target="#collapse-stage-${stage.id}-activity-${activity.id}">
                <span class="glyphicon ${accordionIconClass}"></span>
            </button>
            <div class="clearfix"></div>
            <div class="accordion" id="accordion-stage-${stage.id}-activity-${activity.id}">
                <div class="accordion-group">
                    <div id="collapse-stage-${stage.id}-activity-${activity.id}" class="accordion-body collapse ${accordionOpenClass}">
                        <br>
                        <c:choose>
                            <c:when test="${!empty activity.comments}">
                                <p>${activity.comments}</p>
                            </c:when>
                            <c:otherwise>-</c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${!empty activity.tasks}">
                                <h3>Tasks</h3>
                                <div id="project-stage-${stage.id}-activity-${activity.id}-tasks">
                                    <c:forEach var="task" items="${activity.tasks}">
                                        <%@ include file="show-stage-activity-task.jsp" %>
                                    </c:forEach>
                                </div>
                            </c:when>
                            <c:otherwise>No tasks defined</c:otherwise>
                        </c:choose>
                        <c:if test="${!empty activity.events}">
                            <h3>Events</h3>
                            <div class="list-group">
                                <c:forEach var="event" items="${activity.events}">
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
