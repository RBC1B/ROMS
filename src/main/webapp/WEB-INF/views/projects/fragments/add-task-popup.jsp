<%--
    Popup containing a form to add a new project activity task.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="project-task-modal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="project-task-modal-label" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form class="modal-form" id="project-task-modal-form" method="POST" action="">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h3 id="project-task-modal-label">Add task</h3>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-4 control-label">Name</label>
                        <input type="text" name="name" maxlength="250" />
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">Assigned volunteer</label>
                        <input type="hidden" name="assignedVolunteerId" />
                        <input type="text" name="assignedVolunteerName" autocomplete="off" />
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">Comments</label>
                        <textarea class="col-sm-6" rows="4" name="comments"></textarea>
                    </div>
                </div>
                <div class="clearfix"></div>
                <div class="modal-footer">
                    <button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
                    <button type="submit" class="btn btn-edifice">Save</button>
                </div>
            </form>
        </div>
    </div>
</div>
