<%--
    Author     : oliver
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="pageTitle" value="Kingdom Hall" />
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <div class="container-fluid">
            <h1>Kingdom Hall: ${kingdomHall.name}</h1>    
            <hr>
            <h4>Address: </h4>
            ${kingdomHall.address.street}<br />
            ${kingdomHall.address.town}<br />
            ${kingdomHall.address.county}<br />
            ${kingdomHall.address.postcode}
            <h4>Title Holder</h4>
            ${kingdomHall.titleHolder.congregation} <br/>
            <h4>Ownership Type</h4>
            ${kingdomHall.ownershipTypeId}
            
        <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        </div>
     <script type="text/javascript" src="<c:url value='/javascript/kingdom-halls.js' />" ></script>
    </body>
</html>
