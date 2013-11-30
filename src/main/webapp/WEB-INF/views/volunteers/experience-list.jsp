<%--
    Show the list of volunteer trades (experience).
    This is more free-form than qualifications, and allows us to search for skills/experience that may not be mapped.
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="pageTitle" value="Volunteers" />
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <div class="container-fluid">
            <h1>Volunteer Experience</h1>
            <hr>
            <div class="entity-list-results">
                <table class="table table-bordered table-condensed table-striped table-hover" id="volunteer-experience-list">
                    <thead>
                        <tr>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Congregation</th>
                            <th>Experience</th>
                            <th>Description</th>
                            <th>Years</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                    </tbody>
                    <tfoot>
                  </tfoot>
                </table>
            </div>
            <p>&nbsp;</p>
            <ol class="breadcrumb">
                <li><a href="<c:url value="/" />">Edifice</a></li>
                <li><a href="<c:url value="/volunteers" />">Volunteers</a></li>
                <li class="active">#${volunteer.id}: ${volunteer.displayName} Edit Experience</li>
            </ol>

            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        </div>
        <%@ include file="/WEB-INF/views/common/mustache-list-actions.jsp" %>
        <script type="text/javascript" src="<c:url value='/javascript/volunteers.js' />" ></script>
    </body>
</html>
