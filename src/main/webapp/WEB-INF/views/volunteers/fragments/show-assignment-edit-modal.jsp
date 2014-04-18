<%--
Content of the model dialog used to update the volunteer's assignment status code.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="volunteer-assignment-modal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="volunteer-comments-modal-label" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form class="modal-form" id="volunteer-assignment-modal-form" method="POST" action="">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h3 id="volunteer-comments-modal-label">Edit volunteer assignment</h3>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-4 control-label">Department</label>
                        <input type="hidden" name="departmentId" />
                        <input class="form-control" type="text" name="departmentName" maxlength="250" readonly="true" />
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">Trade number</label>
                        <select class="form-control" name="tradeNumberId">
                            <c:forEach items="${tradeNumbers}" var="tradeNumber">
                                <option value="${tradeNumber.key}">${tradeNumber.value}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">Team</label>
                        <select class="form-control" name="teamId">
                            <c:forEach items="${teams}" var="team">
                                <option value="${team.key}">${team.value}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">Role</label>
                        <select class="form-control" name="assignmentRoleCode">
                            <c:forEach items="${assignmentRoles}" var="assignmentRole">
                                <option value="${assignmentRole.key}">${assignmentRole.value}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">Assigned Date</label>
                        <input name="assignedDate" placeholder="dd/mm/yyyy" class="datetimepicker-dateonly form-control" type="text" value=""/>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
                    <button type="submit" class="btn btn-edifice">Save changes</button>
                </div>
            </form>
        </div>
    </div>
</div>
