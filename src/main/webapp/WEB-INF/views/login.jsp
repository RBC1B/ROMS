<%--
    Login form for users who are not authenticated.
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="pageTitle" value="Login" />
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body class="blueprints">
        <div class="container">
            <div class="col-md-6 well login-info">
                <h1>Welcome to the new version of ROMS</h1>
                <p><em>Edifice</em> Is a new version of the RBC Online Management System. You will need training to use this system. Please contact <a href="mailto:Janicem_green@hotmail.com">Janicem_green@hotmail.com</a> to alert us of your training needs.</p>
                <br/>
                <h3>Old ROMS</h3>
                <p>ROMS is still in use, but Volunteers is now read-only. Please use Edifice to update Volunteer details.</p>
                <a class="btn btn-lg btn-edifice" href="https://roms.rbc1b.org.uk:1443">Login to old ROMS &raquo;</a>
            </div>

            <div class="form-signin col-md-6">
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
        </div>
    </body>
</html>
