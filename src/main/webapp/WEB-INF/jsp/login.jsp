<%--
    Document   : login
    Created on : 30-Jun-2012, 12:23:06
    Author     : oliver.elder.esq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ROMS Login</title>
    </head>
    <body>
        <h1>ROMS Login</h1>
        <form action='j_spring_security_check' method='POST'>
            <fieldset>
                <input type='text' name='username' value=''>
            </fieldset>
            <fieldset>
                <input type='password' name='password'/>
            </fieldset>
            <input name="submit" type="submit" value="Login"/>
        </form>
    </body>
</html>
