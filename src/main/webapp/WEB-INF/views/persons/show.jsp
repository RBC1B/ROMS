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
                        <c:when test="${person.congregation != null}">
                            <a href="<c:url value='${person.congregation.uri}' />">${person.congregation.name}</a>
                        </c:when>
                        <c:otherwise>-</c:otherwise>
                    </c:choose>
                </dd>
                <dt>Birth date:</dt>
                <dd>
                    <c:choose>
                        <c:when test="${person.birthDate != null}">
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
                        <c:when test="${person.email != null}">
                            <a href="mailto:${person.email}">${person.email}</a>
                        </c:when>
                        <c:otherwise>-</c:otherwise>
                    </c:choose>
                </dd>
                <dt>Home phone:</dt>
                <dd>
                    <c:choose>
                        <c:when test="${person.telephone != null}">${person.telephone}</c:when>
                        <c:otherwise>-</c:otherwise>
                    </c:choose>
                </dd>
                <dt>Mobile phone:</dt>
                <dd>
                    <c:choose>
                        <c:when test="${person.mobile != null}">${person.mobile}</c:when>
                        <c:otherwise>-</c:otherwise>
                    </c:choose>
                </dd>
                <dt>Work phone:</dt>
                <dd>
                    <c:choose>
                        <c:when test="${person.workPhone != null}">${person.workPhone}</c:when>
                        <c:otherwise>-</c:otherwise>
                    </c:choose>
                </dd>

                <dt>Address:</dt>
                <dd>
                    <c:choose>
                        <c:when test="${person.address != null}">
                            <address>
                                <c:if test="${person.address.street != null}">${person.address.street}<br/></c:if>
                                <c:if test="${person.address.town != null}">${person.address.town}<br/></c:if>
                                <c:if test="${person.address.county != null}">${person.address.county}<br/></c:if>
                                <c:if test="${person.address.postcode != null}">${person.address.postcode}<br/></c:if>
                            </address>
                        </c:when>
                        <c:otherwise>-</c:otherwise>
                    </c:choose>
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
        <script type="text/javascript" src="<c:url value='/javascript/volunteer.js' />" ></script>
    </body>
</html>