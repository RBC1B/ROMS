<%--
    Author     : oliver
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="pageTitle">Person #${person.id}: ${person.displayName}</c:set>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <div class="container-fluid">
            <h1>${person.forename} ${person.middleName} ${person.surname}</h1>
            <hr>
            <dl class="dl-horizontal">
                <dt>Congregation:</dt>
                <dd>
                    <c:choose>
                        <c:when test="${!empty person.congregation}">
                            <a href="<c:url value='${person.congregation.uri}' />">${person.congregation.name}</a>
                        </c:when>
                        <c:otherwise>-</c:otherwise>
                    </c:choose>
                </dd>
                <dt>Birth date:</dt>
                <dd>
                    <c:choose>
                        <c:when test="${!empty person.birthDate}">
                            <fmt:formatDate value="${person.birthDate}" pattern="dd MMM yyyy" />
                        </c:when>
                        <c:otherwise>-</c:otherwise>
                    </c:choose>
                </dd>
            </dl>
            <h2>Contact</h2>

            <dl class="dl-horizontal">
                <dt>Email:</dt>
                <dd>
                    <c:choose>
                        <c:when test="${!empty person.email}">
                            <a href="mailto:${person.email}">${person.email}</a>
                        </c:when>
                        <c:otherwise>-</c:otherwise>
                    </c:choose>
                </dd>
                <dt>Home phone:</dt>
                <dd>
                    <c:choose>
                        <c:when test="${!empty person.telephone}">${person.telephone}</c:when>
                        <c:otherwise>-</c:otherwise>
                    </c:choose>
                </dd>
                <dt>Mobile phone:</dt>
                <dd>
                    <c:choose>
                        <c:when test="${!empty person.mobile}">${person.mobile}</c:when>
                        <c:otherwise>-</c:otherwise>
                    </c:choose>
                </dd>
                <dt>Work phone:</dt>
                <dd>
                    <c:choose>
                        <c:when test="${!empty person.workPhone}">${person.workPhone}</c:when>
                        <c:otherwise>-</c:otherwise>
                    </c:choose>
                </dd>

                <dt>Address:</dt>
                <dd>
                    <c:choose>
                        <c:when test="${!empty person.address}">
                            <address>
                                <c:if test="${!empty person.address.street}">${person.address.street}<br/></c:if>
                                <c:if test="${!empty person.address.town}">${person.address.town}<br/></c:if>
                                <c:if test="${!empty person.address.county}">${person.address.county}<br/></c:if>
                                <c:if test="${!empty person.address.postcode}">${person.address.postcode}<br/></c:if>
                            </address>
                        </c:when>
                        <c:otherwise>-</c:otherwise>
                    </c:choose>
                </dd>
            </dl>
            <c:if test="${!empty person.comments}">
                <h2>Comments</h2>
                <p>${person.comments}</p>
            </c:if>

            <sec:authorize access="hasPermission('VOLUNTEER', 'EDIT')">
                <a href="<c:url value='${person.editUri}' />" class="btn btn-edifice">Edit Person</a>
            </sec:authorize>

            <ol class="breadcrumb">
                <li><a href="<c:url value="/" />">Edifice</a></li>
                <sec:authorize access="hasPermission('VOLUNTEER', 'READ')">
                  <li><a href="<c:url value="/persons" />">Persons</a></li>
                </sec:authorize>
                <li class="active">#${person.id}: ${person.displayName}</li>
            </ol>                
                
            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        </div>
        <script type="text/javascript" src="<c:url value='/javascript/persons.js' />" ></script>
    </body>
</html>
