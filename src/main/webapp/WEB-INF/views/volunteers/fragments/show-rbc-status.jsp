<%--
The contents of the rbc status tab.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<dl class="dl-horizontal">
    <dt>Form date:</dt>
    <dd>
        <c:choose>
            <c:when test="${!empty volunteer.formDate}">
                <fmt:formatDate value="${volunteer.formDate}" pattern="dd MMM yyyy" />
            </c:when>
            <c:otherwise>-</c:otherwise>
        </c:choose>
    </dd>
    <dt>Interview date:</dt>
    <dd>
        <c:choose>
            <c:when test="${!empty volunteer.interviewDate}">
                <fmt:formatDate value="${volunteer.interviewDate}" pattern="dd MMM yyyy" />
            </c:when>
            <c:otherwise>-</c:otherwise>
        </c:choose>
    </dd>
    <dt>Interviewers:</dt>
    <dd>
        <c:choose>
            <c:when test="${!empty volunteer.interviewerA  || !empty volunteer.interviewerB}">
                <c:if test="${!empty volunteer.interviewerA}">
                    <a href="<c:url value='${volunteer.interviewerA.uri}' />" ><c:out value="${volunteer.interviewerA.name}" /></a>
                </c:if>
                <c:if test="${!empty volunteer.interviewerA && !empty volunteer.interviewerB}">,</c:if>
                <c:if test="${!empty volunteer.interviewerB}">
                    <a href="<c:url value='${volunteer.interviewerB.uri}' />" ><c:out value="${volunteer.interviewerB.name}" /></a>
                </c:if>
            </c:when>
            <c:otherwise>-</c:otherwise>
        </c:choose>
    </dd>
    <dt>Interview comments:</dt>
    <dd>
        <c:choose>
            <c:when test="${!empty volunteer.interviewComments}">
                <c:out value="${volunteer.interviewComments}" />
            </c:when>
            <c:otherwise>-</c:otherwise>
        </c:choose>
    </dd>
    <dt>Joined date:</dt>
    <dd>
        <c:choose>
            <c:when test="${!empty volunteer.joinedDate}">
                <fmt:formatDate value="${volunteer.joinedDate}" pattern="dd MMM yyyy" />
            </c:when>
            <c:otherwise>-</c:otherwise>
        </c:choose>
    </dd>
    <dt>Badge issue date:</dt>
    <dd>
        <c:choose>
            <c:when test="${!empty volunteer.badgeIssueDate}">
                <fmt:formatDate value="${volunteer.badgeIssueDate}" pattern="dd MMM yyyy" />
            </c:when>
            <c:otherwise>-</c:otherwise>
        </c:choose>
    </dd>
    <dt>Availability:</dt>
    <dd>
     <c:forEach var="i" begin="0" end="0">
                <span>
                <c:choose>
                    <c:when test="${volunteer.availability.get(i)}"><span class="label label-success">Mon</span></c:when>
                    <c:otherwise><span class="label label-danger">Mon</span></c:otherwise>
                </c:choose>
                </span>
            </c:forEach>  
     <c:forEach var="i" begin="1" end="1">
                <span>
                <c:choose>
                    <c:when test="${volunteer.availability.get(i)}"><span class="label label-success">Tue</span></c:when>
                    <c:otherwise><span class="label label-danger">Tue</span></c:otherwise>
                </c:choose>
                </span>
            </c:forEach> 
     <c:forEach var="i" begin="2" end="2">
                <span>
                <c:choose>
                    <c:when test="${volunteer.availability.get(i)}"><span class="label label-success">Wed</span></c:when>
                    <c:otherwise><span class="label label-danger">Wed</span></c:otherwise>
                </c:choose>
                </span>
            </c:forEach> 
        
        <c:forEach var="i" begin="3" end="3">
                <span>
                <c:choose>
                    <c:when test="${volunteer.availability.get(i)}"><span class="label label-success">Thu</span></c:when>
                    <c:otherwise><span class="label label-danger">Thu</span></c:otherwise>
                </c:choose>
                </span>
            </c:forEach> 
        <c:forEach var="i" begin="4" end="4">
                <span>
                <c:choose>
                    <c:when test="${volunteer.availability.get(i)}"><span class="label label-success">Fri</span></c:when>
                    <c:otherwise><span class="label label-danger">Fri</span></c:otherwise>
                </c:choose>
                </span>
            </c:forEach> 
        <c:forEach var="i" begin="5" end="5">
                <span>
                <c:choose>
                    <c:when test="${volunteer.availability.get(i)}"><span class="label label-success">Sat</span></c:when>
                    <c:otherwise><span class="label label-danger">Sat</span></c:otherwise>
                </c:choose>
                </span>
            </c:forEach> 
        <c:forEach var="i" begin="6" end="6">
                <span>
                <c:choose>
                    <c:when test="${volunteer.availability.get(i)}"><span class="label label-success">Sun</span></c:when>
                    <c:otherwise><span class="label label-danger">Sun</span></c:otherwise>
                </c:choose>
                </span>
            </c:forEach> 
                        
    </dd>
    <dt>Oversight:</dt>
    <dd>
        <c:choose>
            <c:when test="${volunteer.oversight}"><span class="icon-ok"></span></c:when>
            <c:otherwise><span class="icon-remove"></span></c:otherwise>
        </c:choose>
        <c:if test="${!empty volunteer.oversightComments}">
            <c:out value="${volunteer.oversightComments}" />
        </c:if>
    </dd>
    <dt>Relief UK:</dt>
    <dd>
        <c:choose>
            <c:when test="${volunteer.reliefUK}"><span class="icon-ok"></span></c:when>
            <c:otherwise><span class="icon-remove"></span></c:otherwise>
        </c:choose>
        <c:if test="${!empty volunteer.reliefUKComments}">
            <c:out value="${volunteer.reliefUKComments}" />
        </c:if>
    </dd>
    <dt>Relief abroad:</dt>
    <dd>
        <c:choose>
            <c:when test="${volunteer.reliefAbroad}"><span class="icon-ok"></span></c:when>
            <c:otherwise><span class="icon-remove"></span></c:otherwise>
        </c:choose>
        <c:if test="${!empty volunteer.reliefAbroadComments}">
            <c:out value="${volunteer.reliefAbroadComments}" />
        </c:if>
    </dd>
    <dt>HHC form code:</dt>
    <dd>
        <c:choose>
            <c:when test="${!empty volunteer.hhcFormCode}">
                ${volunteer.hhcFormCode}
            </c:when>
            <c:otherwise>-</c:otherwise>
        </c:choose>
    </dd>
</dl>
<sec:authorize access="hasPermission('VOLUNTEER', 'EDIT')">
    <hr />
    <a class="btn btn-edifice" href="<c:url value='${volunteer.editRbcStatusUri}' />">Edit</a>
</sec:authorize>
