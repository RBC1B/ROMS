<%--
    Author     : rahulsingh
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="pageTitle">Volunteer #${volunteer.id}: ${volunteer.forename} ${volunteer.surname}</c:set>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <div class="container-fluid">
            <div class="media">
                <img src="<c:url value='/images/oli-lion.jpg' />" class="media-object img-polaroid pull-left" />
                <div class="media-body">
                    <h1 class="media-heading">#${volunteer.id}: ${volunteer.forename} ${volunteer.middleName} ${volunteer.surname}</h1>
                    <dl class="dl-horizontal">
                        <dt>Status:</dt><dd>${volunteer.status}</dd>
                        <dt>Comments:</dt><dd>${volunteer.comments}</dd>
                    </dl>
                </div>
            </div>
            <div class="clearfix"></div>
            <br />
            <ul class="nav nav-tabs">
                <li class="active"><a href="#personal" data-toggle="tab">Personal</a></li>
                <li><a href="#spiritual" data-toggle="tab">Spiritual</a></li>
                <li><a href="#skills" data-toggle="tab">Skills</a></li>
            </ul>
            <div class="tab-content">
                <div class="tab-pane active" id="personal">
                    personal info tbd
                </div>
                <div class="tab-pane" id="spiritual">
                    spiritual info tbd
                </div>
                <div class="tab-pane" id="skills">
                    skills tbd
                </div>
            </div>
            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        </div>
        <script type="text/javascript" charset="utf8" src="<c:url value='/javascript/volunteer.js' />" ></script>
    </body>
</html>