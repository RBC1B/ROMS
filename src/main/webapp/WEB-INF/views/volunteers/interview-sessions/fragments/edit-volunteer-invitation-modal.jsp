<%--
    Popup containing a form to edit a volunteer invitation information.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="volunteer-invitation-modal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="volunteer-invitation-modal-label" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form class="modal-form" id="volunteer-invitation-modal-form" method="POST" action="">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h3 id="volunteer-invitation-modal-label">Edit volunteer invitation status</h3>
                </div>
                <div class="modal-body">
                    <input type="hidden" name="_method" value="PUT" />
                    <div class="form-group">
                        <label>Status</label>
                        <select class="form-control" name="interviewStatusCode">
                            <c:forEach items="${interviewStatusValues}" var="interviewStatusValue">
                                <option value="${interviewStatusValue.key}">${interviewStatusValue.value}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Comments</label>
                        <textarea class="form-control" rows="4" name="comments"></textarea>
                    </div>
                </div>
                <div class="clearfix"></div>
                <div class="modal-footer">
                    <button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
                    <button type="submit" class="btn btn-edifice">Update</button>
                </div>
            </form>
        </div>
    </div>
</div>
