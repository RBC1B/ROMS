<%-- 
    Contents of the modal dialog used to update volunteer's qualifications.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="volunteer-qualification-modal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="volunteer-comments-modal-label" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form class="modal-form" id="volunteer-qualification-modal-form" method="POST" action="">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h3 id="volunteer-comments-modal-label">Edit volunteer qualification</h3>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-4 control-label">Qualification</label>
                        <select class="form-control" name="qualificationId">
                            <option></option>
                            <c:forEach items="${qualifications}" var="qualification">
                                <option value="${qualification.key}"
                                        data-qualification-name="${qualification.value.name}"
                                        >${qualification.value.name}</option>
                            </c:forEach>
                        </select>
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
