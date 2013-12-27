<%--
    Document   : login
    Created on : 30-Jun-2012, 12:23:06
    Author     : oliver.elder.esq
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <c:set var="pageTitle" value="Login" />
        <%@ include file="/WEB-INF/views/common/header.jsp" %>
        <nav class="navbar navbar-inverse navbar-static-top" role="navbar">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">MENU
                <span class="sr-only">Toggle navigation</span>
            </button>
            <a class="navbar-brand" href="<c:url value="/" />"><img src="<c:url value='/images/logo-brand.png' />" alt="edifice"></a>
            <!-- Everything you want minimised at 940px or less, place within here -->
            <a class="btn btn-edifice" href="#" ><span class="glyphicon glyphicon-question-sign"></span>&nbsp;Help</a>&nbsp;
            <button type="button" class="btn btn-success" id="login-popover" data-container="body" data-toggle="popover" data-placement="bottom">
                Login
            </button>
            <div id="popover-head" class="hide">Login:</div>
            <div id="popover-content" class="hide">
                <form class="form-signin" action="<c:url value='j_spring_security_check' />" method="POST">
                    <c:if test="${not empty status}"><p class="error">Invalid login details supplied. Please try again</p></c:if>
                        <fieldset>
                            <input type='text' class="input-block-level" name='j_username' value=''>
                        </fieldset>
                        <fieldset>
                            <input type='password' class="input-block-level" name='j_password'/>
                        </fieldset>
                        <input name="submit" class="btn btn-large btn-edifice" type="submit" value="Login"/>
                    <c:if test="${not empty status}"><p class="error">Invalid login details supplied. Please try again</p></c:if>
                </form>
            </div>
        </nav>
        <h1>Dashboard</h1>
        <hr>
        <div class="jumbotron">
            <div class="well-edifice col-md-4 col-xs-6">
                <div class="well">
                    <!--Nested the well into another div with padding inside to give space between wells. Yoshi 09/10/13-->
                    <h3>Organisations</h3>
                    <ul class="nav nav-tabs nav-stacked">
                        <li>Congregation</li>
                        <li>Circuit</li>
                    </ul>
                </div>
            </div>
            <div class="well-edifice col-md-4 col-xs-6">
                <div class="well">
                    <h3>Kingdom Hall</h3>
                    <ul class="nav nav-tabs nav-stacked">
                        <li>List Of Kingdom Halls</li>
                        <a href="">Details of Kingdom Halls ></a></p>
                    </ul>
                </div>
            </div>
            <div class="well-edifice col-md-4 col-xs-6">
                <div class="well">
                    <h3>Projects</h3>
                    <ul class="nav nav-tabs nav-stacked">
                        <li>Project Coordination</li>
                        <li>Project Design</li>
                        <li>Upcoming Projects</li>
                    </ul>
                </div>
            </div>
            <!--/div-->
            <!--div class="row"-->
            <!--Made 2 rows into 1, so that, for smaller screen sizes, the "Projects" well doesn't stand on its own. Just to make it look better! Yoshi 09/10/13-->
            <div class="well-edifice col-md-4 col-xs-6">
                <div class="well">
                    <h3>Volunteers</h3>
                    <ul class="nav nav-tabs nav-stacked">
                        <li>Contact Details</li>
                        <li>Skills & Training</li>
                        <li>Gate List</li>
                    </ul>
                </div>
            </div>
            <div class="well-edifice col-md-4 col-xs-6">
                <div class="well">
                    <h3>My Profile</h3>
                    <ul class="nav nav-tabs nav-stacked">
                        <li>Ramindur</li>
                    </ul>
                </div>
            </div>
        </div>
        <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
        <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.0/jquery-ui.min.js"></script>
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
        <script>
            $(document).ready(function(){
                $('#login-popover').popover({ 
                    html : true,
                    title: function() {
                        return $("#popover-head").html();
                    },
                    content: function() {
                        return $("#popover-content").html();
                    }
                });
            });
        </script>
    </body>
</html>