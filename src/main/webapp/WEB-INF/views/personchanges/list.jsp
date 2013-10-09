<%--
    Document   : list
    Created on : Oct 3, 2013, 12:05:09 PM
    Author     : ramindursingh
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:url var="formAction" value="/personchanges" />
<html>
    <c:set var="pageTitle" value="Changes/Updates to Person Information" />
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <div class="container">
            <h1>Person Information Changes/Updates</h1>
            <hr>
            <div class="entity-list-results">
                <table class="table table-bordered table-condensed table-striped table-hover" id="personchange-list">
                    <thead>
                        <tr>
                            <th>Person ID</th>
                            <th>Surname</th>
                            <th>Forename</th>
                            <th>Address</th>
                            <th>Email</th>
                            <th>Mobile</th>
                            <th>Telephone</th>
                            <th>Work Phone</th>
                            <th>Forms Updated</th>
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
                                        <c:when test = "${personchange.oldSurname eq personchange.newSurname}">
                                            ${personchange.oldSurname}
                                        </c:when>
                                        <c:otherwise>
                                            <font color="red">${personchange.newSurname}</font>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test = "${personchange.oldForename eq personchange.newForename}">
                                            ${personchange.oldForename}
                                        </c:when>
                                        <c:otherwise>
                                            <font color="red">${personchange.newForename}</font>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test = "${personchange.oldStreet eq personchange.newStreet}">
                                            ${personchange.oldStreet}
                                        </c:when>
                                        <c:otherwise>
                                            <font color="red">${personchange.newStreet}</font>
                                        </c:otherwise>
                                    </c:choose>
                                    <br />
                                    <c:choose>
                                        <c:when test = "${personchange.oldTown eq personchange.newTown}">
                                            ${personchange.oldTown}
                                        </c:when>
                                        <c:otherwise>
                                            <font color="red">${personchange.newTown}</font>
                                        </c:otherwise>
                                    </c:choose>
                                    <br />
                                    <c:choose>
                                        <c:when test = "${personchange.oldCounty eq personchange.newCounty}">
                                            ${personchange.oldCounty}
                                        </c:when>
                                        <c:otherwise>
                                            <font color="red">${personchange.newCounty}</font>
                                        </c:otherwise>
                                    </c:choose>
                                    <br />
                                    <c:choose>
                                        <c:when test = "${personchange.oldPostcode eq personchange.newPostcode}">
                                            ${personchange.oldPostcode}
                                        </c:when>
                                        <c:otherwise>
                                            <font color="red">${personchange.newPostcode}</font>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test = "${personchange.oldEmail eq personchange.newEmail}">
                                            ${personchange.oldEmail}
                                        </c:when>
                                        <c:otherwise>
                                            <font color="red">${personchange.newEmail}</font>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test = "${personchange.oldMobile eq personchange.newMobile}">
                                            ${personchange.oldMobile}
                                        </c:when>
                                        <c:otherwise>
                                            <font color="red">${personchange.newMobile}</font>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test = "${personchange.oldTelephone eq personchange.newTelephone}">
                                            ${personchange.oldTelephone}
                                        </c:when>
                                        <c:otherwise>
                                            <font color="red">${personchange.newTelephone}</font>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test = "${personchange.oldWorkPhone eq personchange.newWorkPhone}">
                                            ${personchange.oldWorkPhone}
                                        </c:when>
                                        <c:otherwise>
                                            <font color="red">${personchange.newWorkPhone}</font>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <ul class="list-inline">
                                        <li><a class="list-action" href="<c:url value="${personchange.updateUri}" />">Paper Work Updated</a></li>
                                    </ul>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <p>&nbsp;</p>
            <ul class="breadcrumb">
                <li><a href="<c:url value="/" />">Edifice</a> <span class="divider">/</span></li>
                <li class="active">Person Information Change</li>
            </ul>
            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        </div>
        <script type="text/javascript" src="<c:url value='/javascript/personChange.js' />" ></script>
    </body>
</html>
