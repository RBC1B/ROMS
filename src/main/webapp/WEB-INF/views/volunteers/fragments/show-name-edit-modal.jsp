<%--
Content of the model dialog used to update the volunteer name
Author: oliver.elder.esq
--%>
<div id="volunteer-name-modal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="volunteer-name-modal-label" aria-hidden="true">
    <c:url var="formAction" value="${volunteer.editNameUri}" />
    <form class="modal-form" id="volunteer-name-modal-form" method="POST" action="${formAction}">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <h3 id="volunteer-name-modal-label">Edit volunteer name</h3>
        </div>
        <div class="modal-body">
            <fieldset>
                <input type="hidden" name="_method" value="PUT" />
                <label>Forename</label>
                <input type="text" name="forename" maxlength="50" value="${volunteer.forename}" />
                <label>Middle name</label>
                <input type="text" name="middleName" maxlength="50" value="${volunteer.middleName}" />
                <label>Surname</label>
                <input type="text" name="surname" maxlength="50" value="${volunteer.surname}" />
            </fieldset>
        </div>
        <div class="modal-footer">
            <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
            <button type="submit" class="btn btn-primary">Save changes</button>
        </div>
    </form>
</div>
