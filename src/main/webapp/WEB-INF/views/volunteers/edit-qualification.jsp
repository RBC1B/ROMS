<%--
Edit form for the volunteer data under the skill tab.
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="pageTitle">Edit ${forename} ${surname} qualification information</c:set>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <h1>${forename} ${surname} - qualification information</h1>
        <hr />
        <c:url var="formAction" value="${submitUri}" />
        <form:form class="form-horizontal" commandName="volunteerQualification" method="PUT" action="${formAction}">
            <fieldset>
                <div class="form-group">
                    <label class="control-label col-sm-4 col-md-3">Qualification</label>
                    <div class="col-sm-4 col-md-2">
                        <form:select class="form-control" path="qualificationId">
                            <form:option value="" label="None" />
                            <form:options items="${qualificationValues}" />
                        </form:select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-4 col-md-3">Comments</label>
                    <div class="col-sm-8 col-md-3">
                        <form:input path="comments" class="form-control" placeholder="Comments" autocomplete="off" />
                    </div>
                </div>
            </fieldset>
            <fieldset>
                <label class="control-label col-sm-4 col-md-3"></label>
                <div class="col-sm-4 col-md-2">
                    <button type="submit" class="btn btn-default btn-success">Submit</button>
                </div>
            </fieldset>
        </form:form>

        <ol class="breadcrumb">
            <li><a href="<c:url value="/" />">Edifice</a></li>
            <li><a href="<c:url value="/volunteers" />">Volunteers</a></li>
            <li class="active">Edit ${forename} ${surname} qualification information</li>
        </ol>

        <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        <script type="text/javascript" src="<c:url value='/javascript/thirdparty/jquery-numeric-1.3.1.js' />" ></script>
        <script type="text/javascript" src="<c:url value='/javascript/volunteers.js' />" ></script>
    </body>
</html>

