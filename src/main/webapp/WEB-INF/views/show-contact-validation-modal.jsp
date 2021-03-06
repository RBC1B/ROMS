<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal fade" id="volunteer-contact-update" tabindex="-1" role="dialog" aria-labelledby="volunteer-comments-modal-label" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="#" method="post" id="update-contact-form">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">x</button>
                    <h4 class="modal-title" id="volunteer-contact-modal-form-label">Update Request</h4>
                </div>
                <div class="modal-body">
                    <!--If error occurs with update form, create a div with Bootstrap style alert-->
                    <div class="alert alert-danger" id="alert-update"></div>
                    <div class="form-group">
                        <label for="personId" class="control-label">RBC ID Number</label>
                        <input id="personId" name="personId" type="text" class="form-control" placeholder="Your RBC ID Number" />
                    </div>
                    <div class="form-group">
                        <label for="birthDate" class="control-label">Date of Birth</label>
                        <input id="birthDate" name="birthDate" type="text" class="datepicker form-control" placeholder="dd/mm/yyyy" data-date-format="dd/mm/yyyy"/>
                    </div>
                </div>
                <div class="modal-footer">
                        <button id="cancel-update" type="button" aria-hidden="true" data-dismiss="modal">Cancel</button>
                        <button id="submit-update" type="submit" class="btn btn-edifice">Submit Request</button>
                </div>
            </form>
        </div>
    </div>
</div>
<div class="modal fade" id="volunteer-contact-update-success" tabindex="-1" role="dialog" aria-labelledby="volunteer-comments-modal-success-label" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">x</button>
                    <h4 class="modal-title" id="volunteer-contact-modal-form-label">Update Request</h4>
                </div>
                <div class="modal-body">
                    <div class="alert alert-success"><p><strong>Success: </strong>Your request has been submitted. You will receive a confirmation email shortly.</p></div>
                </div>
                <div class="modal-footer">
                        <button type="button" class="btn btn-edifice" aria-hidden="true" data-dismiss="modal">OK</button>
                </div>
        </div>
    </div>
</div>
