<%--
Content of the model dialog used to update the volunteer comments
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="volunteer-comments-modal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="volunteer-comments-modal-label" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
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
                        <textarea rows="5" cols="40" name="comments">${volunteer.comments}</textarea>
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
