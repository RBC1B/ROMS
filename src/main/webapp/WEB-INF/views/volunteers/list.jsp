<%--
    Document   : volunteers
    Created on : 30-Aug-2012, 16:02:18
    Author     : rahulsingh
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="pageTitle" value="Volunteers" />
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <div class="content-container">
            <h1>Volunteers</h1>
            <table id="volunteer-list">
                <thead>
                    <tr>
                        <th>RBCID</th>
                        <th>Forename</th>
                        <th>Middle Name</th>
                        <th>Surname</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${volunteers}" var="vol">
                        <tr>
                            <td>${vol.rbcid}</td>
                            <td>${vol.forename}</td>
                            <td>${vol.middleName}</td>
                            <td>${vol.surname}</td>
                            <td><a href="<c:url value="/volunteers/${vol.rbcid}"/>">View</a>&nbsp;
                                <a href="<c:url value="/volunteers/${vol.rbcid}/edit" />">Edit</a>&nbsp;
                                <a href="delete">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
                <tfoot>
                    <tr>
                        <th><input type="text" name="search_rbcid" value="Search RBCIDs" class="search_init" /></th>
                        <th><input type="text" name="search_forenames" value="Search forenames" class="search_init" /></th>
                        <th><input type="text" name="search_middleNames" value="Search middleNames" class="search_init" /></th>
                        <th><input type="text" name="search_surnames" value="Search surnames" class="search_init" /></th>
                        <th></th>
                    </tr>
                </tfoot>
            </table>
        </div>
        <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        <script type="text/javascript" charset="utf8" src="<c:url value='/javascript/circuits.js' />" ></script>
    </body>
</html>
