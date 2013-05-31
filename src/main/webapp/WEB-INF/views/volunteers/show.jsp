<%--
    Author     : rahulsingh
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="pageTitle">Volunteer #${volunteer.id}: ${volunteer.forename} ${volunteer.surname}</c:set>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <div class="container-fluid">
            <div class="media">
                <img src="<c:url value='/images/oli-lion.jpg' />" class="media-object img-polaroid pull-left" />
                <div class="media-body">
                    <h1 class="media-heading">#${volunteer.id}: ${volunteer.forename} ${volunteer.middleName} ${volunteer.surname}</h1>
                    <dl class="dl-horizontal">
                        <dt>Status:</dt><dd>${volunteer.status}</dd>
                        <dt>Comments:</dt><dd>${volunteer.comments}</dd>
                    </dl>
                </div>
            </div>
            <div class="clearfix">
                <c:choose>
                    <c:when test="${volunteer.assignments != null}">
                        <h3>Assignments</h3>
                        <table class="table table-bordered table-striped table-hover">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Department</th>
                                    <th>Team</th>
                                    <th>Role</th>
                                    <th>Assigned</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${volunteer.assignments}" var="assignment">
                                    <tr>
                                        <td>${assignment.tradeNumber}</td>
                                        <td><a href="${assignment.department.uri}">${assignment.department.name}</a></td>
                                        <td><a href="${assignment.team.uri}">${assignment.team.name}</a></td>
                                        <td>${assignment.role}</td>
                                        <td><fmt:formatDate value="${assignment.assignedDate}" pattern="dd MMM yyyy" /></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <br />
                        <div class="alert alert-block">Volunteer is not assigned to any teams</div>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="clearfix"></div>
            <br />
            <ul class="nav nav-tabs">
                <li class="active"><a href="#personal" data-toggle="tab">Personal</a></li>
                <li><a href="#spiritual" data-toggle="tab">Spiritual</a></li>
                <li><a href="#skills" data-toggle="tab">Skills</a></li>
                <li><a href="#rbc-status" data-toggle="tab">RBC Status</a></li>
            </ul>
            <div class="tab-content">
                <div class="tab-pane active" id="personal">
                    <dl class="dl-horizontal">
                        <dt>Email:</dt>
                        <dd>
                            <c:choose>
                                <c:when test="${volunteer.email != null}">
                                    <a href="mailto:${volunteer.email}">${volunteer.email}</a>
                                </c:when>
                                <c:otherwise>-</c:otherwise>
                            </c:choose>
                        </dd>
                        <dt>Home phone:</dt>
                        <dd>
                            <c:choose>
                                <c:when test="${volunteer.telephone != null}">${volunteer.telephone}</c:when>
                                <c:otherwise>-</c:otherwise>
                            </c:choose>
                        </dd>
                        <dt>Mobile phone:</dt>
                        <dd>
                            <c:choose>
                                <c:when test="${volunteer.mobile != null}">${volunteer.mobile}</c:when>
                                <c:otherwise>-</c:otherwise>
                            </c:choose>
                        </dd>
                        <dt>Work phone:</dt>
                        <dd>
                            <c:choose>
                                <c:when test="${volunteer.workPhone != null}">${volunteer.workPhone}</c:when>
                                <c:otherwise>-</c:otherwise>
                            </c:choose>
                        </dd>
                        <dt>Address:</dt>
                        <dd>
                            <c:choose>
                                <c:when test="${volunteer.address != null}">
                                    <address>
                                        <c:if test="${volunteer.address.street != null}">${volunteer.address.street}<br/></c:if>
                                        <c:if test="${volunteer.address.town != null}">${volunteer.address.town}<br/></c:if>
                                        <c:if test="${volunteer.address.county != null}">${volunteer.address.county}<br/></c:if>
                                        <c:if test="${volunteer.address.postcode != null}">${volunteer.address.postcode}<br/></c:if>
                                    </address>
                                </c:when>
                                <c:otherwise>-</c:otherwise>
                            </c:choose>
                        </dd>
                        <dt>Gender:</dt>
                        <dd>
                            <c:if test="${volunteer.gender != null}">
                                <c:choose>
                                    <c:when test="${volunteer.gender == 'F'}">Female</c:when>
                                    <c:otherwise>Male</c:otherwise>
                                </c:choose>
                            </c:if>
                        </dd>
                        <dt>Birth date:</dt>
                        <dd>
                            <c:choose>
                                <c:when test="${volunteer.birthDate != null}">
                                    <fmt:formatDate value="${volunteer.birthDate}" pattern="dd MMM yyyy" />
                                </c:when>
                                <c:otherwise>-</c:otherwise>
                            </c:choose>
                        </dd>
                        <dt>Marital Status:</dt>
                        <dd>${volunteer.maritalStatus}
                            <c:if test="${volunteer.spouse != null}">
                                (<a href="<c:url value='${volunteer.spouse.uri}' />" >${volunteer.spouse.forename} ${volunteer.spouse.surname}</a>)
                            </c:if>
                        </dd>
                    </dl>
                    <h3>Emergency Contact</h3>
                    <c:choose>
                        <c:when test="${volunteer.emergencyContact != null}">
                            <dl class="dl-horizontal">
                                <dt>Name:</dt>
                                <dd>
                                    <a href="<c:url value='${volunteer.emergencyContact.uri}' />">${volunteer.emergencyContact.forename} ${volunteer.emergencyContact.surname}</a>
                                </dd>
                                <dt>Relationship:</dt><dd>${volunteer.emergencyContactRelationship}</dd>
                                <c:if test="${volunteer.emergencyContact.email != null}">
                                    <dt>Email:</dt>
                                    <dd>
                                        <a href="mailto:${volunteer.emergencyContact.email}">${volunteer.emergencyContact.email}</a>
                                    </dd>
                                </c:if>
                                <c:if test="${volunteer.emergencyContact.telephone != null}">
                                    <dt>Home phone:</dt><dd>${volunteer.emergencyContact.telephone}</dd>
                                </c:if>
                                <c:if test="${volunteer.emergencyContact.mobile != null}">
                                    <dt>Mobile phone:</dt><dd>${volunteer.emergencyContact.mobile}</dd>
                                </c:if>
                                <c:if test="${volunteer.emergencyContact.workPhone != null}">
                                    <dt>Work phone:</dt><dd>${volunteer.emergencyContact.workPhone}</dd>
                                </c:if>
                            </dl>
                        </c:when>
                        <c:otherwise>
                            Not set
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="tab-pane" id="spiritual">
                    <dl class="dl-horizontal">
                        <dt>Congregation:</dt>
                        <dd>
                            <c:choose>
                                <c:when test="${volunteer.congregation != null}">
                                    <a href="<c:url value='${volunteer.congregation.uri}' />">${volunteer.congregation.name}</a>
                                </c:when>
                                <c:otherwise>-</c:otherwise>
                            </c:choose>
                        </dd>
                        <dt>Date of Baptism:</dt>
                        <dd>
                            <c:choose>
                                <c:when test="${volunteer.baptismDate != null}">
                                    <fmt:formatDate value="${volunteer.baptismDate}" pattern="dd MMM yyyy" />
                                </c:when>
                                <c:otherwise>-</c:otherwise>
                            </c:choose>
                        </dd>
                        <dt>Full time service:</dt>
                        <dd>
                            <c:choose>
                                <c:when test="${volunteer.fulltime != null}">${volunteer.fulltime}</c:when>
                                <c:otherwise>-</c:otherwise>
                            </c:choose>
                        </dd>
                        <dt>Appointment:</dt>
                        <dd>
                            <c:choose>
                                <c:when test="${volunteer.appointment != null}">${volunteer.appointment}</c:when>
                                <c:otherwise>-</c:otherwise>
                            </c:choose>
                        </dd>
                    </dl>
                </div>
                <div class="tab-pane" id="skills">
                    <h3>Skills</h3>
                    <c:choose>
                        <c:when test="${volunteer.skills != null}">
                            <table id="volunteer-skills-skills" class="table table-bordered table-striped table-hover">
                                <thead>
                                    <tr>
                                        <th>Name</th>
                                        <th>Department</th>
                                        <th>Level</th>
                                        <th>Training Date</th>
                                        <th>Training Results</th>
                                        <th>Comments</th>
                                        <th>Badge</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${volunteer.skills}" var="skill">
                                        <tr>
                                            <td><span class="a-skill-description" data-original-title="${skill.description}"><a href="${skill.skill.uri}">${skill.skill.name}</a></span></td>
                                            <td><a href="${skill.department.uri}">${skill.department.name}</a></td>
                                            <td>${skill.level}</td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${skill.trainingDate != null}">
                                                        <fmt:formatDate value="${skill.trainingDate}" pattern="dd MMM yyyy" />
                                                    </c:when>
                                                    <c:otherwise>-</c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td>${skill.trainingResults}</td>
                                            <td>${skill.comments}</td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${skill.appearOnBadge}"><span class="icon-ok"></span></c:when>
                                                    <c:otherwise><span class="icon-remove"></span></c:otherwise>
                                                </c:choose>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </c:when>
                        <c:otherwise>
                            <div class="alert alert-block">Volunteer has no defined skills</div>
                        </c:otherwise>
                    </c:choose>

                    <h3>Experience</h3>
                    <c:choose>
                        <c:when test="${volunteer.trades != null}">
                            <table id="volunteer-skills-experience" class="table table-bordered table-striped table-hover">
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
                                            <td>${trade.name}</td>
                                            <td>${trade.experienceDescription}</td>
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
                </div>
                <div class="tab-pane" id="rbc-status">
                    <dl class="dl-horizontal">
                        <dt>Form date:</dt>
                        <dd>
                            <c:choose>
                                <c:when test="${volunteer.formDate != null}">
                                    <fmt:formatDate value="${volunteer.formDate}" pattern="dd MMM yyyy" />
                                </c:when>
                                <c:otherwise>-</c:otherwise>
                            </c:choose>
                        </dd>
                        <dt>Interview date:</dt>
                        <dd>
                            <c:choose>
                                <c:when test="${volunteer.interviewDate != null}">
                                    <fmt:formatDate value="${volunteer.interviewDate}" pattern="dd MMM yyyy" />
                                </c:when>
                                <c:otherwise>-</c:otherwise>
                            </c:choose>
                        </dd>
                        <dt>Interviewers:</dt>
                        <dd>
                            <c:choose>
                                <c:when test="${volunteer.interviewerA != null || $volunteer.interviewerB != null}">
                                    <c:if test="${volunteer.interviewerA != null}">
                                        <a href="<c:url value='${volunteer.interviewerA.uri}' />" >${volunteer.interviewerA.forename} ${volunteer.interviewerA.surname}</a>
                                    </c:if>
                                    <c:if test="${volunteer.interviewerA != null && volunteer.interviewerB != null}">, </c:if>
                                    <c:if test="${volunteer.interviewerB != null}">
                                        <a href="<c:url value='${volunteer.interviewerB.uri}' />" >${volunteer.interviewerB.forename} ${volunteer.interviewerB.surname}</a>
                                    </c:if>
                                </c:when>
                                <c:otherwise>-</c:otherwise>
                            </c:choose>
                        </dd>
                        <dt>Interview comments:</dt>
                        <dd>
                            <c:choose>
                                <c:when test="${volunteer.interviewComments != null}">
                                    ${volunteer.interviewComments}
                                </c:when>
                                <c:otherwise>-</c:otherwise>
                            </c:choose>
                        </dd>
                        <dt>Joined date:</dt>
                        <dd>
                            <c:choose>
                                <c:when test="${volunteer.joinedDate != null}">
                                    <fmt:formatDate value="${volunteer.joinedDate}" pattern="dd MMM yyyy" />
                                </c:when>
                                <c:otherwise>-</c:otherwise>
                            </c:choose>
                        </dd>
                        <dt>Badge issue date:</dt>
                        <dd>
                            <c:choose>
                                <c:when test="${volunteer.badgeIssueDate != null}">
                                    <fmt:formatDate value="${volunteer.badgeIssueDate}" pattern="dd MMM yyyy" />
                                </c:when>
                                <c:otherwise>-</c:otherwise>
                            </c:choose>
                        </dd>
                        <dt>Availability:</dt>
                        <dd>
                            <table id="volunteer-availability" class="table-bordered">
                                <thead>
                                    <tr>
                                        <th>Mon</th>
                                        <th>Tue</th>
                                        <th>Wed</th>
                                        <th>Thu</th>
                                        <th>Fri</th>
                                        <th>Sat</th>
                                        <th>Sun</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <c:forEach var="i" begin="0" end="6">
                                            <td>
                                                <c:choose>
                                                    <c:when test="${volunteer.availability.get(i)}"><span class="icon-ok"></span></c:when>
                                                    <c:otherwise><span class="icon-remove"></span></c:otherwise>
                                                </c:choose>
                                            </td>
                                        </c:forEach>
                                    </tr>
                                </tbody>
                            </table>
                        </dd>
                        <dt>Oversight:</dt>
                        <dd>
                            <c:choose>
                                <c:when test="${volunteer.oversight}"><span class="icon-ok"></span></c:when>
                                <c:otherwise><span class="icon-remove"></span></c:otherwise>
                            </c:choose>
                            <c:if test="${volunteer.oversightComments != null}">
                                (${volunteer.oversightComments})
                            </c:if>
                        </dd>
                        <dt>Relief UK:</dt>
                        <dd>
                            <c:choose>
                                <c:when test="${volunteer.reliefUK}"><span class="icon-ok"></span></c:when>
                                <c:otherwise><span class="icon-remove"></span></c:otherwise>
                            </c:choose>
                            <c:if test="${volunteer.reliefUKComments != null}">
                                (${volunteer.reliefUKComments})
                            </c:if>
                        </dd>
                        <dt>Relief Abroad:</dt>
                        <dd>
                            <c:choose>
                                <c:when test="${volunteer.reliefAbroad}"><span class="icon-ok"></span></c:when>
                                <c:otherwise><span class="icon-remove"></span></c:otherwise>
                            </c:choose>
                            <c:if test="${volunteer.reliefAbroadComments != null}">
                                (${volunteer.reliefAbroadComments})
                            </c:if>
                        </dd>
                        <dt>HHC Form code:</dt>
                        <dd>
                            <c:choose>
                                <c:when test="${volunteer.hhcFormCode != null}">
                                    ${volunteer.hhcFormCode}
                                </c:when>
                                <c:otherwise>-</c:otherwise>
                            </c:choose>
                        </dd>
                    </dl>
                </div>
            </div>
            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        </div>
        <script type="text/javascript" charset="utf8" src="<c:url value='/javascript/volunteers.js' />" ></script>
    </body>
</html>