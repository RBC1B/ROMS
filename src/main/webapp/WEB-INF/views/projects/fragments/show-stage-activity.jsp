<%--
    Show an individual project stage activity
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div id="stage-${stage.id}-activity-${activity.id}" class="panel panel-activity">
    <div class="panel-heading">
        <c:choose>
            <c:when test="${activity.isInProgress()}">
                <c:set var="accordionOpenClass">in</c:set>
                <c:set var="accordionIconClass">glyphicon-chevron-down</c:set>
            </c:when>
            <c:otherwise>
                <c:set var="accordionOpenClass"></c:set>
                <c:set var="accordionIconClass">glyphicon-chevron-right</c:set>
            </c:otherwise>
        </c:choose>
        <button type="button"
                class="btn btn-default pull-left a-accordian-control"
                data-target="#collapse-stage-${stage.id}-activity-${activity.id}">
            <span class="glyphicon ${accordionIconClass}"></span>
        </button>
        <div class="col-xs-1 project-assignee">
            <c:choose>
                <c:when test="${!empty activity.assignedUser}">
                    <a class="a-project-assignment" href="${activity.assignedUser.uri}">
                        ${activity.assignedUser.name}
                    </a>
                </c:when>
                <c:otherwise>Unassigned</c:otherwise>
            </c:choose>
        </div>
        <div class="project-stage-type-name col-xs-4"><h4>${activity.type.name}: ${activity.type.description}</h4></div>
        <div class="project-stage-status col-xs-4"><h4>${activity.status}</h4></div>
        <div class="col-xs-2">
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
        <div class="drag-move pull-right">
            <span class="glyphicon glyphicon-move"></span>
        </div>
        <div class="clearfix"></div>
    </div>
    <div class="a-accordian-wrapper">
        <div class="accordion" id="accordion-stage-${stage.id}-activity-${activity.id}">
            <div class="accordion-group">
                <div id="collapse-stage-${stage.id}-activity-${activity.id}" class="accordion-body collapse ${accordionOpenClass}">
                    <div class="panel-body">
                        <div class="col-sm-3">
                            <div>
                                <strong>Created:</strong>
                                <c:choose>
                                    <c:when test="${!empty activity.createdTime}">
                                        <fmt:formatDate value="${activity.createdTime}" pattern="yyyy-MM-dd" />
                                    </c:when>
                                    <c:otherwise>&nbsp;</c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                        <div class="col-sm-3">
                            <div>
                                <strong>Started:</strong>
                                <c:choose>
                                    <c:when test="${!empty activity.startedTime}">
                                        <fmt:formatDate value="${activity.startedTime}" pattern="yyyy-MM-dd" />
                                    </c:when>
                                    <c:otherwise>&nbsp;</c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                        <div class="col-sm-3">
                            <div>
                                <strong>Completed:</strong>
                                <c:choose>
                                    <c:when test="${!empty activity.completedTime}">
                                        <fmt:formatDate value="${activity.completedTime}" pattern="yyyy-MM-dd" />
                                    </c:when>
                                    <c:otherwise>&nbsp;</c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                        <div class="col-sm-3">
                            <c:choose>
                                <c:when test="${!empty activity.assignedUser}">
                                    <a class="a-project-assignment" href="${activity.assignedUser.uri}">
                                        ${activity.assignedUser.name}
                                    </a>
                                </c:when>
                                <c:otherwise>Unassigned</c:otherwise>
                            </c:choose>
                        </div>
                        <br>
                        <div class="col-sm-12">
                            <c:choose>
                                <c:when test="${!empty activity.comments}">
                                    <strong>Comments:</strong>
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
                                <c:otherwise><p>No tasks defined</p></c:otherwise>
                            </c:choose>
                            <button type="button"
                                    class="btn btn-edifice a-add-task-button"
                                    data-uri="<c:url value="${activity.createNewTaskUri}" />"
                                    data-user-name="${userName}"
                                    data-user-id="${userId}"
                                    >
                                <span class="glyphicon glyphicon-plus"></span> Add task</button>
                        </div>
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
