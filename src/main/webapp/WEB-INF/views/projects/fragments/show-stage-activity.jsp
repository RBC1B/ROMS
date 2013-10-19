<%--
    Show an individual project stage activity
--%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div id="stage-${stage.projectStageId}-activity-${activity.projectStageActivityId}" class="panel panel-default">
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
        <button type="button" class="btn btn-edifice pull-right">
            <a class="accordion-toggle pull-right" data-toggle="collapse" data-parent="#accordion-stage-${stage.projectStageId}-activity-${activity.projectStageActivityId}" href="#collapse-stage-${stage.projectStageId}-activity-${activity.projectStageActivityId}">
                <span class="glyphicon glyphicon-plus"></span>
            </a>
        </button>
        <div class="clearfix"></div>
        <div class="accordion" id="accordion-stage-${stage.projectStageId}-activity-${activity.projectStageActivityId}">
            <div class="accordion-group">
                <div id="collapse-stage-${stage.projectStageId}-activity-${activity.projectStageActivityId}" class="accordion-body collapse in">
                    <div class="accordion-inner">
                        <br>
                        <c:choose>
                            <c:when test="${!empty activity.tasks}">
                                <h3>Tasks</h3>
                                <div id="project-stage-${stage.projectStageId}-activity-${activity.projectStageActivityId}-tasks">
                                    <c:forEach var="task" items="${activity.tasks}">
                                        <%@ include file="show-stage-activity-task.jsp" %>
                                    </c:forEach>
                                </div>
                            </c:when>
                            <c:otherwise>No tasks defined</c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
