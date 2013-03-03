<%--
    Document   : show
    Created on : Feb 23, 2013, 10:46:37 AM
    Author     : oliver
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="pageTitle" value="Person" />
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <div class="container-fluid">
            <h1>${person.forename} ${person.middleName} ${person.surname}</h1>
            <dl class="dl-horizontal">
                <dt>Congregation:</dt>
                <dd>
                    <c:choose>
                        <c:when test="${person.congregation != null}">
                            <a href="<c:url value='${person.congregation.uri}' />">${person.congregation.name}</a>
                        </c:when>
                        <c:otherwise>
                            Not set
                        </c:otherwise>
                    </c:choose>
                </dd>
                <dt>Birth date:</dt>
                <dd>
                    <c:choose>
                        <c:when test="${person.birthDate != null}">
                            <fmt:formatDate value="${person.birthDate}" pattern="dd MMM yyyy" />
                        </c:when>
                        <c:otherwise>
                            Not set
                        </c:otherwise>
                    </c:choose>
                </dd>
            </dl>
            <h2>Contact</h2>

            <dl class="dl-horizontal">
                <dt>Email:</dt>
                <dd>
                    <c:if test="${person.email != null}">
                        <a href="mailto:${person.email}">${person.email}</a>
                    </c:if>
                </dd>
                <dt>Home phone:</dt>
                <dd>${person.telephone}</dd>
                <dt>Mobile phone:</dt>
                <dd>${person.mobile}</dd>
                <dt>Work phone:</dt>
                <dd>${person.workPhone}</dd>
                <dt>Address:</dt>
                <dd>
                    <c:if test="${person.address != null}">
                        <address>
                            <c:if test="${person.address.street != null}">${person.address.street}<br/></c:if>
                            <c:if test="${person.address.town != null}">${person.address.town}<br/></c:if>
                            <c:if test="${person.address.county != null}">${person.address.county}<br/></c:if>
                            <c:if test="${person.address.postcode != null}">${person.address.postcode}<br/></c:if>
                            </address>
                    </c:if>
                </dd>
            </dl>
            <c:if test="${person.comments != null}">
                <h2>Comments</h2>
                <p>${person.comments}</p>
            </c:if>

            <sec:authorize access="hasPermission('VOLUNTEER', 'EDIT')">
                <a href="<c:url value='${editUri}' />" class="btn btn-primary">Edit Person</a>
            </sec:authorize>

            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        </div>
        <script type="text/javascript" charset="utf-8" src="<c:url value='/javascript/volunteer.js' />" ></script>
    </body>
</html>