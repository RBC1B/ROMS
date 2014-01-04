<%--
    List the person changes that require paper form updates.
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="pageTitle" value="Pending volunteer form updates" />
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <h1>Pending volunteer form updates</h1>
        <hr />
        <div class="entity-list-results">
            <table class="table table-bordered table-condensed table-striped table-hover" id="personchange-list">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Address</th>
                        <th>Email</th>
                        <th>Mobile phone</th>
                        <th>Home phone</th>
                        <th>Work phone</th>
                        <th>Comment</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${personchanges}" var="personchange">
                        <tr>
                            <td>
                                ${personchange.personId}
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${personchange.oldSurname eq personchange.newSurname}">
                                        ${personchange.oldSurname}
                                    </c:when>
                                    <c:otherwise>
                                        <font class="details-changed">${personchange.newSurname}</font>
                                    </c:otherwise>
                                </c:choose>
                                <br />
                                <c:choose>
                                    <c:when test="${personchange.oldForename eq personchange.newForename}">
                                        ${personchange.oldForename}
                                    </c:when>
                                    <c:otherwise>
                                        <font class="details-changed">${personchange.newForename}</font>
                                    </c:otherwise>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${personchange.oldMiddleName eq personchange.newMiddleName}">
                                        ${personchange.oldMiddleName}
                                    </c:when>
                                    <c:otherwise>
                                        <font class="details-changed">${personchange.newMiddleName}
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${personchange.oldStreet eq personchange.newStreet}">
                                        ${personchange.oldStreet}
                                    </c:when>
                                    <c:otherwise>
                                        <font class="details-changed">${personchange.newStreet}</font>
                                    </c:otherwise>
                                </c:choose>
                                <br />
                                <c:choose>
                                    <c:when test="${personchange.oldTown eq personchange.newTown}">
                                        ${personchange.oldTown}
                                    </c:when>
                                    <c:otherwise>
                                        <font class="details-changed">${personchange.newTown}</font>
                                    </c:otherwise>
                                </c:choose>
                                <br />
                                <c:choose>
                                    <c:when test="${personchange.oldCounty eq personchange.newCounty}">
                                        ${personchange.oldCounty}
                                    </c:when>
                                    <c:otherwise>
                                        <font class="details-changed">${personchange.newCounty}</font>
                                    </c:otherwise>
                                </c:choose>
                                <br />
                                <c:choose>
                                    <c:when test="${personchange.oldPostcode eq personchange.newPostcode}">
                                        ${personchange.oldPostcode}
                                    </c:when>
                                    <c:otherwise>
                                        <font class="details-changed">${personchange.newPostcode}</font>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${personchange.oldEmail eq personchange.newEmail}">
                                        ${personchange.oldEmail}
                                    </c:when>
                                    <c:otherwise>
                                        <font class="details-changed">${personchange.newEmail}</font>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${personchange.oldMobile eq personchange.newMobile}">
                                        ${personchange.oldMobile}
                                    </c:when>
                                    <c:otherwise>
                                        <font class="details-changed">${personchange.newMobile}</font>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${personchange.oldTelephone eq personchange.newTelephone}">
                                        ${personchange.oldTelephone}
                                    </c:when>
                                    <c:otherwise>
                                        <font class="details-changed">${personchange.newTelephone}</font>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${personchange.oldWorkPhone eq personchange.newWorkPhone}">
                                        ${personchange.oldWorkPhone}
                                    </c:when>
                                    <c:otherwise>
                                        <font class="details-changed">${personchange.newWorkPhone}</font>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                ${personchange.comment}
                            </td>
                            <td>
                                <sec:authorize access="hasPermission('VOLUNTEER', 'EDIT')">
                                    <a class="btn btn-edifice a-update-paperwork" data-update-url="<c:url value="${personchange.updateUri}" />">Form updated</a>
                                </sec:authorize>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <br />
        <ol class="breadcrumb">
            <li><a href="<c:url value="/" />">Edifice</a> <span class="divider"></span></li>
            <li class="active">Form updates</li>
        </ol>
        <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        <script type="text/javascript" src="<c:url value='/javascript/person-changes.js' />" ></script>
    </body>
</html>
