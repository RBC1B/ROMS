<%--
    Show an individual project stage
--%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div id="stage-${stage.projectStageId}" class="container-fluid well">
    <div class="row-fluid">
        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne"><div class="span1 icon-span">
            <span class="icon-plus"></span>                </a>
        </div>
        <div class="span4">
            <div class="project-stage-type-name">${stage.type.name}: ${stage.type.description}</div>
        </div>
        <div class="span1">
            <div class="project-stage-created-date">
                <c:choose>
                    <c:when test="${!empty stage.createdTime}">
                        <fmt:formatDate value="${stage.createdTime}" pattern="yyyy-MM-dd" />
                    </c:when>
                    <c:otherwise>&nbsp;</c:otherwise>
                </c:choose>
            </div>
        </div>
        <div class="span1">
            <div class="project-stage-started-date">
                <c:choose>
                    <c:when test="${!empty stage.startedTime}">
                        <fmt:formatDate value="${stage.startedTime}" pattern="yyyy-MM-dd" />
                    </c:when>
                    <c:otherwise>&nbsp;</c:otherwise>
                </c:choose>
            </div>
        </div>
        <div class="span1">
            <div class="project-stage-completed-date">
                <c:choose>
                    <c:when test="${!empty stage.completedTime}">
                        <fmt:formatDate value="${stage.completedTime}" pattern="yyyy-MM-dd" />
                    </c:when>
                    <c:otherwise>&nbsp;</c:otherwise>
                </c:choose>
            </div>
        </div>
        <div class="span1">
            <div class="project-stage-status">${stage.status}</div>
        </div>
        <div class="span2">
            <div class="project-stage-task-counts">
                <div class="project-stage-task-count a-project-task-count" title="" data-toggle="tooltip"
                     data-original-title="Not started: ${stage.createdTaskCount} of ${stage.totalTaskCount}">
                    <span class="badge badge-important">${stage.createdTaskCount}</span>
                </div>
                <div class="project-stage-task-count a-project-task-count" title="" data-toggle="tooltip"
                     data-original-title="In progress: ${stage.startedTaskCount} of ${stage.totalTaskCount}">
                    <span class="badge badge-warning">${stage.startedTaskCount}</span>
                </div>
                <div class="project-stage-task-count a-project-task-count" title="" data-toggle="tooltip"
                     data-original-title="Completed: ${stage.completedTaskCount} of ${stage.totalTaskCount}">
                    <span class="badge badge-success">${stage.completedTaskCount}</span>
                </div>
            </div>
        </div>
        <div class="span1 icon-span">
            <span class="icon-resize-vertical"></span>
        </div>
    </div>


    <div class="accordion" id="accordion2">
        <div class="accordion-group">
            <div id="collapseOne" class="accordion-body collapse in">
                <div class="accordion-inner">
                    Accordion can be persuaded to work if we replace the #collapseOne with a unique identifier for each fragment (that doesn't also kick out bootstrap.js)
                </div>
            </div>
        </div>
    </div>


</div>
