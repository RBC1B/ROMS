<%-- 
    Document   : show
    Created on : Feb 23, 2013, 10:46:37 AM
    Author     : oliver
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="pageTitle" value="Volunteer" />
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <body>
        <%@ include file="/WEB-INF/views/common/titlebar.jsp" %>
        <div class="container-fluid">

           Name: ${person.forename}
            
            private java.sql.Date birthDate;
    private EntityModel congregation;
    private String forename;
    private String middleName;
    private String surname;
    private Address address;
    private String telephone;
    private String mobile;
    private String workPhone;
    private String email;
    private String comments;
            
            
            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
        </div>
        <script type="text/javascript" charset="utf8" src="<c:url value='/javascript/volunteer.js' />" ></script>
    </body>
</html>