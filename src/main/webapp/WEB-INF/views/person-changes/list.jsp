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
                                        <c:out value="${personchange.oldSurname}" />
                                    </c:when>
                                    <c:otherwise>
                                        <font class="details-changed"><c:out value="${personchange.newSurname}" /></font>
                                    </c:otherwise>
                                </c:choose>
                                <br />
                                <c:choose>
                                    <c:when test="${personchange.oldForename eq personchange.newForename}">
                                        <c:out value="${personchange.oldForename}" />
                                    </c:when>
                                    <c:otherwise>
                                        <font class="details-changed"><c:out value="${personchange.newForename}" /></font>
                                    </c:otherwise>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${personchange.oldMiddleName eq personchange.newMiddleName}">
                                        <c:out value="${personchange.oldMiddleName}" />
                                    </c:when>
                                    <c:otherwise>
                                        <font class="details-changed"><c:out value="${personchange.newMiddleName}" />
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${personchange.oldStreet eq personchange.newStreet}">
                                        <c:out value="${personchange.oldStreet}" />
                                    </c:when>
                                    <c:otherwise>
                                        <font class="details-changed"><c:out value="${personchange.newStreet}" /></font>
                                    </c:otherwise>
                                </c:choose>
                                <br />
                                <c:choose>
                                    <c:when test="${personchange.oldTown eq personchange.newTown}">
                                        <c:out value="${personchange.oldTown}" />
                                    </c:when>
                                    <c:otherwise>
                                        <font class="details-changed"><c:out value="${personchange.newTown}" /></font>
                                    </c:otherwise>
                                </c:choose>
                                <br />
                                <c:choose>
                                    <c:when test="${personchange.oldCounty eq personchange.newCounty}">
                                        <c:out value="${personchange.oldCounty}" />
                                    </c:when>
                                    <c:otherwise>
                                        <font class="details-changed"><c:out value="${personchange.newCounty}" /></font>
                                    </c:otherwise>
                                </c:choose>
                                <br />
                                <c:choose>
                                    <c:when test="${personchange.oldPostcode eq personchange.newPostcode}">
                                        <c:out value="${personchange.oldPostcode}" />
                                    </c:when>
                                    <c:otherwise>
                                        <font class="details-changed"><c:out value="${personchange.newPostcode}" /></font>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${personchange.oldEmail eq personchange.newEmail}">
                                        <c:out value="${personchange.oldEmail}" />
                                    </c:when>
                                    <c:otherwise>
                                        <font class="details-changed"><c:out value="${personchange.newEmail}" /></font>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${personchange.oldMobile eq personchange.newMobile}">
                                        <c:out value="${personchange.oldMobile}" />
                                    </c:when>
                                    <c:otherwise>
                                        <font class="details-changed"><c:out value="${personchange.newMobile}" /></font>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${personchange.oldTelephone eq personchange.newTelephone}">
                                        <c:out value="${personchange.oldTelephone}" />
                                    </c:when>
                                    <c:otherwise>
                                        <font class="details-changed"><c:out value="${personchange.newTelephone}" /></font>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${personchange.oldWorkPhone eq personchange.newWorkPhone}">
                                        <c:out value="${personchange.oldWorkPhone}" />
                                    </c:when>
                                    <c:otherwise>
                                        <font class="details-changed"><c:out value="${personchange.newWorkPhone}" /></font>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:out value="${personchange.comment}" />
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
