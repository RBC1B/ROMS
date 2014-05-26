<%--
Content of the model dialog used to update the volunteer's skills.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="volunteer-skill-modal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="volunteer-comments-modal-label" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form class="modal-form" id="volunteer-skill-modal-form" method="POST" action="">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h3 id="volunteer-comments-modal-label">Edit volunteer skill</h3>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-4 control-label">Skill</label>
                        <select class="form-control" name="skillId">
                            <option></option>
                            <c:forEach items="${skills}" var="skill">
                                <option value="${skill.key}"
                                        data-department-name="${skill.value.departmentName}"
                                        data-skill-name="${skill.value.skillName}"
                                        >${skill.value}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">Level</label>
                        <select class="form-control" name="level">
                            <c:forEach begin="1" end="5" varStatus="level">
                                <option value="${level.index}">${level.index}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">Training date</label>
                        <input name="trainingDate" placeholder="dd/mm/yyyy" class="datetimepicker-dateonly form-control" type="text" value=""/>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">Training results</label>
                        <input class="form-control" type="text" name="trainingResults" maxlength="15" />
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">Comments</label>
                        <input class="form-control" type="text" name="comments" maxlength="250" />
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
