<%--
The contents of the congregation members tab.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="congregation-member-congregation" data-congregation-id="${congregation.congregationId}"></div>
<table class="table table-bordered table-condensed table-striped table-hover" id="congregation-member-list">
    <thead>
        <tr>
            <th>ID</th>
            <th>Forename</th>
            <th>Surname</th>
            <th>Action</th>
        </tr>
    <thead />
    <tbody>
    </tbody>
    <tfoot>
    </tfoot>
</table>
<%@ include file="/WEB-INF/views/common/mustache-list-actions.jsp" %>
