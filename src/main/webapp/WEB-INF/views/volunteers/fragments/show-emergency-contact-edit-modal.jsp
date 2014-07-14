<%--
Content of the model dialog used to update the volunteer's emergency contacts.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="volunteer-emergency-contact-modal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="volunteer-comments-modal-label" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form class="modal-form" id="volunteer-emergency-contact-modal-form" method="POST" action="">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h3 id="volunteer-comments-modal-label">Edit volunteer emergency contact</h3>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <input type="hidden" name="emergencyContactId" />
                        <label class="col-sm-4 control-label">First Name</label>
                        <input class="form-control" type="text" name="firstName" maxlength="250" />
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">Last Name</label>
                        <input class="form-control" type="text" name="surName" maxlength="250" />
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">Relationship</label>
                        <select class="form-control" name="relationshipCode">
                            <c:forEach items="${relationshipValues}" var="relationshipValue">
                                <option value="${relationshipValue.key}">${relationshipValue.value}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group" id="emergency-contact-home-phone">
                        <label class="col-sm-4 control-label">Home Phone</label>
                        <input class="form-control" type="text" name="homePhone" maxlength="15" />
                    </div>
                    <div class="form-group" id="emergency-contact-mobile-phone">
                        <label class="col-sm-4 control-label">Mobile Phone</label>
                        <input class="form-control" type="text" name="mobilePhone" maxlength="15" />
                    </div>
                    <div class="form-group" id="emergency-contact-work-phone">
                        <label class="col-sm-4 control-label">Work Phone</label>
                        <input class="form-control" type="text" name="workPhone" maxlength="15" />
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
