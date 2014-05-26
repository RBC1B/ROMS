<%--
The contents of the skills tab.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="volunteer-with-skills" <c:if test="${empty volunteerSkills}">style="display: none"</c:if>>
    <h3>Skills</h3>
    <table id="volunteer-skills-skills" class="table table-bordered table-condensed table-striped table-hover">
        <thead>
            <tr>
                <th>Name</th>
                <th>Department</th>
                <th>Level</th>
                <th>Training date</th>
                <th>Training results</th>
                <th>Comments</th>
                <th>Badge</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${volunteerSkills}" var="skill">
                <tr>
                    <td><span class="a-skill-description" data-original-title="${skill.description}"><a href="<c:url value="${skill.skill.uri}" />"><c:out value="${skill.skill.name}" /></a></span></td>
                    <td><a href="<c:url value="${skill.department.uri}" />"><c:out value="${skill.department.name}" /></a></td>
                    <td>${skill.level}</td>
                    <td>
                        <c:choose>
                            <c:when test="${!empty skill.trainingDate}">
                                <fmt:formatDate value="${skill.trainingDate}" pattern="dd/MM/yyyy" />
                            </c:when>
                            <c:otherwise>-</c:otherwise>
                        </c:choose>
                    </td>
                    <td><c:out value="${skill.trainingResults}" /></td>
                    <td><c:out value="${skill.comments}" /></td>
                    <td>
                        <c:choose>
                            <c:when test="${skill.appearOnBadge}"><span class="glyphicon glyphicon-ok"></span></c:when>
                            <c:otherwise><span class="glyphicon glyphicon-remove"></span></c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <sec:authorize access="hasPermission('VOLUNTEER', 'EDIT')">
                            <ul class="list-inline">
                                <li><a class="a-edit-skill" href="<c:url value="${skill.uri}" />">Edit</a></li>
                                <li><a class="a-delete-skill"
                                    data-ajax-url="<c:url value="${skill.uri}" />" href="#"> Delete</a></li>
                            </ul>
                        </sec:authorize>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<div id="volunteer-without-skills" <c:if test="${!empty volunteerSkills}">style="display: none"</c:if>>
    <br />
    <div class="alert alert-warning">Volunteer has no defined skills</div>
</div>

<h3>Qualifications</h3>
<c:choose>
    <c:when test="${!empty qualifications}">
        <table id="volunteer-skills-qualifications" class="table table-bordered table-condensed table-striped table-hover">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Comments</th>
                    <th>Badge</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${qualifications}" var="qualification">
                    <tr>
                        <td>
                            <span class="a-qualification-description" data-original-title="${qualification.description}">
                                <a href="<c:url value="${qualification.qualification.uri}" />"><c:out value="${qualification.qualification.name}" /></a>
                            </span>
                        </td>
                        <td><c:out value="${qualification.comments}" /></td>
                        <td>
                            <c:choose>
                                <c:when test="${qualification.appearOnBadge}"><span class="glyphicon glyphicon-ok"></span></c:when>
                                <c:otherwise><span class="glyphicon glyphicon-remove"></span></c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <ul class="list-inline">
                                <sec:authorize access="hasPermission('SKILL', 'EDIT')">
                                    <li><a href="<c:url value="${qualification.editUri}" />">Edit</a></li>
                                    <li><a href="<c:url value="#" />">Delete</a></li>
                                </sec:authorize>
                            </ul>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <div class="alert alert-block">Volunteer has no defined qualifications</div>
    </c:otherwise>
</c:choose>

<h3>Experience</h3>
<c:choose>
    <c:when test="${!empty volunteer.trades}">
        <table id="volunteer-skills-experience" class="table table-bordered table-condensed table-striped table-hover">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Years</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${volunteer.trades}" var="trade">
                    <tr>
                        <td><c:out value="${trade.name}" /></td>
                        <td><c:out value="${trade.experienceDescription}" /></td>
                        <td>${trade.experienceYears}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <div class="alert alert-block">Volunteer has no defined experience</div>
    </c:otherwise>
</c:choose>
