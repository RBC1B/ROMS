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
        <div class="container-fluid">
            <h1>Qualifications</h1>
            <table class="table table-bordered table-striped table-hover" id="qualification-list">
                <thead>
                    <tr>
                        <th>Qualfication ID</th>
                        <th>Qualification</th>
                        <th>Description</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${qualifications}" var="qualification">
                        <tr>
                            <td>${qualification.qualificationId}</td>
                            <td>${qualification.name}</td>
                            <td>${qualification.description}</td>
                            <td>
                                <ul>
                                    <a id='edit' class="edit" href="">
                                        <img src="<c:url value='/images/pencil.ico' />" width="20" height="20" alt="Edit">Edit 
                                    </a>
                                    <a id='delete' class="delete" href="">
                                        <img src="<c:url value='/images/delete.ico' />" width="20" height="20" alt="Delete">Delete
                                    </a>
                                </ul>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div id="new-qualification">
                <a class="btn btn-primary" href="qualifications/new" />New Qualification</a>
            </div>
            
            <p>&nbsp;</p>
            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
            <script type="text/javascript" charset="utf-8" src="<c:url value='/javascript/qualifications.js' />" ></script>
        </div>
    </body>
</html>
