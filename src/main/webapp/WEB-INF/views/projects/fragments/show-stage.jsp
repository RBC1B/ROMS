<%--
    Show an individual project stage
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div id="stage-${stage.id}" class="panel panel-stage">
    <div class="panel-heading">
        <c:choose>
            <c:when test="${stage.isInProgress()}">
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
                data-target="#collapse-stage-${stage.id}">
            <span class="glyphicon ${accordionIconClass}"></span>
        </button>
        <div class="project-stage-type-name col-sm-4"><h4>${stage.type.name}: ${stage.type.description}</h4></div>
        <div class="project-stage-status col-sm-4"><h4>${stage.status}</h4></div>

        <div class="col-sm-2">
            <div class="project-counts">
                <div class="project-count a-project-count" title="" data-toggle="tooltip"
                     data-original-title="Not started: ${stage.createdActivityCount} of ${stage.totalActivityCount}">
                    <span class="badge badge-important">${stage.createdActivityCount}</span>
                </div>
                <div class="project-count a-project-count" title="" data-toggle="tooltip"
                     data-original-title="In progress: ${stage.startedActivityCount} of ${stage.totalActivityCount}">
                    <span class="badge badge-warning">${stage.startedActivityCount}</span>
                </div>
                <div class="project-count a-project-count" title="" data-toggle="tooltip"
                     data-original-title="Completed: ${stage.completedActivityCount} of ${stage.totalActivityCount}">
                    <span class="badge badge-success">${stage.completedActivityCount}</span>
                </div>
            </div>
        </div>
        <div class="clearfix"></div>
    </div>
    <div class="a-accordian-wrapper">
        <div class="accordion" id="accordion-stage-${stage.id}">
            <div class="accordion-group">
                <div id="collapse-stage-${stage.id}" class="accordion-body collapse ${accordionOpenClass}">
                    <div class="panel-body">
                        <div class="col-sm-3">
                            <strong>Created:</strong>
                            <c:choose>
                                <c:when test="${!empty stage.createdTime}">
                                    <fmt:formatDate value="${stage.createdTime}" pattern="yyyy-MM-dd" />
                                </c:when>
                                <c:otherwise>&nbsp;</c:otherwise>
                            </c:choose>
                        </div>
                        <div class="col-sm-3">
                            <strong>Started:</strong>
                            <c:choose>
                                <c:when test="${!empty stage.startedTime}">
                                    <fmt:formatDate value="${stage.startedTime}" pattern="yyyy-MM-dd" />
                                </c:when>
                                <c:otherwise>&nbsp;</c:otherwise>
                            </c:choose>
                        </div>
                        <div class="col-sm-3">
                            <strong>Completed:</strong>
                            <c:choose>
                                <c:when test="${!empty stage.completedTime}">
                                    <fmt:formatDate value="${stage.completedTime}" pattern="yyyy-MM-dd" />
                                </c:when>
                                <c:otherwise>&nbsp;</c:otherwise>
                            </c:choose>
                        </div>
                        <br>
                        <c:choose>
                            <c:when test="${!empty stage.activities}">
                                <h3>Activities</h3>
                                <div id="project-stage-${stage.id}-activities">
                                    <c:forEach var="activity" items="${stage.activities}">
                                        <%@ include file="show-stage-activity.jsp" %>
                                    </c:forEach>
                                </div>
                            </c:when>
                            <c:otherwise>No activities defined</c:otherwise>
                        </c:choose>
                        <c:if test="${!empty stage.events}">
                            <h3>Events</h3>
                            <div class="list-group">
                                <c:forEach var="event" items="${stage.events}">
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
