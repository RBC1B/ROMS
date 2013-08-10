<%--
    Show an individual project stage
--%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="row">
    <div class="icon-plus" ></div>
    <div class="project-stage-type-name">${stage.type.name} </div>
    
    <div class="icon-resize-vertical"></div>
    <div class="project-stage-task-counts">
        <div class="project-stage-task-count a-project-task-count" title=""
             data-toggle="tooltip"
             data-original-title="Not started: ${stage.createdTaskCount} of ${stage.totalTaskCount}">
            <span class="badge badge-important">${stage.createdTaskCount}</span>
        </div>
        <div class="project-stage-task-count a-project-task-count" title=""
             data-toggle="tooltip"
             data-original-title="In progress: ${stage.startedTaskCount} of ${stage.totalTaskCount}">
            <span class="badge badge-warning">${stage.startedTaskCount}</span>
        </div>
        <div class="project-stage-task-count a-project-task-count" title=""
             data-toggle="tooltip"
             data-original-title="Completed: ${stage.completedTaskCount} of ${stage.totalTaskCount}">
            <span class="badge badge-success">${stage.completedTaskCount}</span>
        </div>
    </div>
    <div class="project-stage-completed-date"><fmt:formatDate value="${stage.completedTime}" pattern="yyyy-MM-dd" /></div>
    <div class="project-stage-started-date"><fmt:formatDate value="${stage.startedTime}" pattern="yyyy-MM-dd" /></div>
    <div class="project-stage-created-date"><fmt:formatDate value="${stage.createdTime}" pattern="yyyy-MM-dd" /></div>
    <div class="project-stage-status">${stage.status} </div>
</div>