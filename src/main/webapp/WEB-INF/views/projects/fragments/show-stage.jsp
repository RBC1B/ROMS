<%--
    Show an individual project stage
--%>
<div class="row">
    <div class="icon-plus" ></div>
    <div class="project-stage-type-name">${stage.type.name} </div>
    <div class="project-stage-status">${stage.status} </div>
    <div class="project-stage-created-date"><fmt:formatDate value="${stage.createdTime}" pattern="yyyy-MM-dd" /></div>
    <div class="project-stage-started-date"><fmt:formatDate value="${stage.startedTime}" pattern="yyyy-MM-dd" /></div>
    <div class="project-stage-completed-date"><fmt:formatDate value="${stage.completedTime}" pattern="yyyy-MM-dd" /></div>
    <div class="project-stage-task-counts">
        <span class="badge badge-important">${stage.createdTaskCount}</span>
        <span class="badge badge-warning">${stage.startedTaskCount}</span>
        <span class="badge badge-success">${stage.completedTaskCount}</span>
    </div>
    <div class="icon-resize-vertical"></div>
</div>