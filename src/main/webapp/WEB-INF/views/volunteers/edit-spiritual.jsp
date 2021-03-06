<%--
Edit form for the volunteer data under the spiritual tab.
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="pageTitle">Edit ${forename} ${surname} spiritual information</c:set>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <h1>${forename} ${surname} - spiritual information</h1>
        <hr />
        <c:url var="formAction" value="${submitUri}" />
        <form:form class="form-horizontal" commandName="volunteerSpiritual" method="PUT" action="${formAction}">
            <fieldset>
                <div class="form-group">
                    <label class="control-label col-sm-4 col-md-3">Congregation</label>
                    <div class="col-sm-8 col-md-3">
                        <form:input path="congregationName" class="form-control" placeholder="Congregation name" autocomplete="off" />
                        <form:hidden path="congregationId" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-4 col-md-3">Date of baptism</label>
                    <div class="col-sm-4 col-md-2">
                        <form:input path="baptismDate" placeholder="dd/mm/yyyy" class="datepicker form-control" data-date-format="dd/mm/yy" type="text" value=""/>
                    </div>
                </div>
            </fieldset>
            <fieldset>
                <div class="form-group">
                    <label class="control-label col-sm-4 col-md-3">Full time service</label>
                    <div class="col-sm-4 col-md-2">
                        <form:select class="form-control" path="fulltimeCode">
                            <form:option value="" label="None" />
                            <form:options items="${fulltimeValues}" />
                        </form:select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-4 col-md-3">Appointment</label>
                    <div class="col-sm-4 col-md-2">
                        <form:select class="form-control" path="appointmentCode">
                            <form:option value="" label="None (Publisher)" />
                            <form:options items="${appointmentValues}" />
                        </form:select>
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
            <li class="active">Edit ${forename} ${surname} spiritual information</li>
        </ol>

        <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        <script type="text/javascript" src="<c:url value='/javascript/thirdparty/jquery-numeric-1.3.1.js' />" ></script>
        <script type="text/javascript" src="<c:url value='/javascript/volunteers.js' />" ></script>
    </body>
</html>

