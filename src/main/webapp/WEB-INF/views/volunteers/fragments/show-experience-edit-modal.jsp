<%--
    Contents of the modal dialog used to update volunteer's experience.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="volunteer-experience-modal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="volunteer-comments-modal-label" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form class="modal-form" id="volunteer-experience-modal-form" method="POST" action="">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h3 id="volunteer-comments-modal-label">Edit volunteer experience</h3>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <input type="hidden" name="volunteerTradeId" />
                        <label class="col-sm-4 control-label">Name</label>
                        <input class="form-control" type="text" name="name" maxlength="250" />
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">Description</label>
                        <input class="form-control" type="text" name="experienceDescription" maxlength="250" />
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">Years</label>
                        <input class="form-control" type="text" name="experienceYears" maxlength="2" />
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
