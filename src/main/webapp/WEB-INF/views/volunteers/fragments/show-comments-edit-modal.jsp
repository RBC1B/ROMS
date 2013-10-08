<%--
Content of the model dialog used to update the volunteer comments
Author: oliver.elder.esq
--%>
<div id="volunteer-comments-modal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="volunteer-comments-modal-label" aria-hidden="true">
    <c:url var="formAction" value="${volunteer.editCommentsUri}" />
    <form class="modal-form" id="volunteer-comments-modal-form" method="POST" action="${formAction}">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <h3 id="volunteer-comments-modal-label">Edit volunteer comments</h3>
        </div>
        <div class="modal-body">
            <fieldset>
                <input type="hidden" name="_method" value="PUT" />
                <label>Comments</label>
                <input type="text" name="comments" maxlength="50" value="${volunteer.comments}" />
            </fieldset>
        </div>
        <div class="modal-footer">
            <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
            <button type="submit" class="btn btn-edifice">Save changes</button>
        </div>
    </form>
</div>
