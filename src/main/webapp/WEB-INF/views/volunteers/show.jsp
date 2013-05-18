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
            <div class="clearfix"></div>
            <br />
            <ul class="nav nav-tabs">
                <li class="active"><a href="#personal" data-toggle="tab">Personal</a></li>
                <li><a href="#spiritual" data-toggle="tab">Spiritual</a></li>
                <li><a href="#skills" data-toggle="tab">Skills</a></li>
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
                    skills tbd
                </div>
            </div>
            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        </div>
        <script type="text/javascript" charset="utf8" src="<c:url value='/javascript/volunteer.js' />" ></script>
    </body>
</html>