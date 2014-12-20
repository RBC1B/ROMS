<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="pageTitle">Edifice Username - ${user.userName}</c:set>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <dl class="dl-horizontal">
            <dt>To:</dt><dd><c:out value="${email.recipient}" /></dd>
            <dt>Reply To:</dt><dd><c:out value="${email.replyTo}" /></dd>
            <dt>Cc:</dt><dd><c:out value="${email.cc}" /></dd>
            <dt>Subject:</dt><dd><c:out value="${email.subject}" /></dd>
        </dl>
        <pre><c:out value="${email.text}" /></pre>
    </body>
</html>
