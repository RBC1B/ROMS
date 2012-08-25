<%-- 
    Document   : list
    Created on : 23-Aug-2012, 20:22:25
    Author     : Tina
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="pageTitle" value="Qualifications" />
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <div class="content-container">
            <h1>Qualifications</h1>
            <table id="qualification-list">
                <thead>
                    <tr>
                        <th>Qualification</th>
                        <th>Description</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${qualifications}" var="qualification">
                        <tr>
                            <td>${qualification.qualification}</td>
                            <td>${qualification.description}</td>
                            <td><a href="<c:url value="/qualifications/${qualification.qualification}" />">View</a>&nbsp;
                                <a href="<c:url value="/qualifications/${qualification.qualification}/edit" />">Edit</a>&nbsp;
                                <a href="delete">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
                <tfoot>
                    <tr>
                        <th><input type="text" name="search_name" value="Search qualifications" class="search_init" /></th>
                        <th><input type="text" name="search_description" value="Search descriptions" class="search_init" /></th>
                        <th></th>
                    </tr>
                </tfoot>
            </table>
        </div>
        <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        <script type="text/javascript" charset="utf8" src="<c:url value='/javascript/qualifications.js' />" ></script>
    </body>
</html>
