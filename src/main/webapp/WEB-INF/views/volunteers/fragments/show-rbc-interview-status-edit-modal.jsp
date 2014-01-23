<%--
Content of the model dialog used to update the volunteer rbc interview status
--%>
<div id="volunteer-rbc-interview-status-modal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="volunteer-rbc-interview-status-modal-label" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <c:url var="formAction" value="${volunteer.editRbcInterviewStatusUri}" />
            <form class="modal-form" id="volunteer-rbc-interview-status-modal-form" method="POST" action="${formAction}">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h3 id="volunteer-rbc-interview-status-modal-label">Edit Volunteer RBC Status</h3>
                </div>
                <div class="modal-body">
                    <fieldset>
                        <input type="hidden" name="_method" value="PUT" />
                        <label for="rbcStatusCode">RBC Status</label>
                        <select id="rbcStatusSelect" style="width:175px" class="form-control" name="rbcStatusCode">
                        </select>
                    </fieldset>
                </div>
                <div class="modal-footer">
                    <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
                    <button type="submit" class="btn btn-edifice">Save changes</button>
                </div>
            </form>
        </div>
    </div>
</div>
