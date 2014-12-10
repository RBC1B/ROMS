<!DOCTYPE html>
<%--
    Login form for users who are not authenticated.
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <c:set var="pageTitle">Login</c:set>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body class="blueprints">
        <div id="relative-path" data-relative-path="<c:url value="/" />"></div>
        <div class="container flex-container">
            <div class="col-md-6 well login-info">
                <h1>Welcome to the new version of ROMS</h1>
                <p><em>Edifice</em> Is a new version of the RBC Online Management System. You will need training to use this system. Please contact either the Training or IT Support department for training.</p>
                <br/>
                <h3>Old ROMS</h3>
                <p>ROMS is still in use, but Volunteers is now read-only. Please use Edifice to update Volunteer details.</p>
                <p><a class="btn btn-lg btn-edifice" href="https://roms.rbc-lhc.org.uk:1443">Login to old ROMS &raquo;</a></p>
                <br />
            </div>
            <div class="col-md-6">
                <div class="form-signin">
                    <form action="<c:url value='j_spring_security_check' />" method="POST">
                        <img src="<c:url value='/images/logo-login.png' />" alt="Edifice" />
                        <p class="title-quote">Edifice :- Origin from Latin <b>aedificum</b> from <b><i>aedis 'dwelling' + facere 'make' </i></b></p>
                        <c:if test="${not empty status}"><p class="error">Invalid login details supplied. Please try again</p></c:if>
                            <fieldset>
                                <input type='text' class="input-block-level" name='j_username' value=''>
                            </fieldset>
                            <fieldset>
                                <input type='password' class="input-block-level" name='j_password'/>
                            </fieldset>
                            <input name="submit" class="btn btn-large btn-edifice" type="submit" value="Login"/>
                        </form>
                    </div>
                    <div class="form-signin">
                        <h3>Update your Contact Details</h3>
                        <p>If you wish to update your contact details, please click on the link below.</p>
                        <a id="update-contact" class="btn btn-lg btn-edifice" href="#">Update contact details</a>
                    </div>
                </div>
            </div>
        <%@ include file="show-contact-validation-modal.jsp" %>
        <%@ include file="/WEB-INF/views/common/footer-min.jsp" %>
        <script type="text/javascript" src="<c:url value='/javascript/common.js' />"></script>
        <script type="text/javascript" src="<c:url value='/javascript/login.js' />" ></script>
    </body>
</html>
