<%--
The contents of the department assignments tab.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="entity-list-results">
    <table  class="table table-bordered table-condensed table-striped table-hover"
            id="department-assignment-list"
            data-department-id="${department.departmentId}">
        <thead>
            <tr>
                <th>Forename</th>
                <th>Surname</th>
                <th>Congregation</th>
                <th>Team</th>
                <th>Role</th>
                <th>Trade no.</th>
                <th>Assigned</th>
                <th>Action</th>
            </tr>
        <thead />
        <tbody>
        </tbody>
        <tfoot>
        </tfoot>
    </table>
</div>
