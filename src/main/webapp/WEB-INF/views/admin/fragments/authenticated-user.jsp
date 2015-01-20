<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="roms" uri="/WEB-INF/tld/roms.tld"%>
<h3><c:out value="${user.userName}" />
    <a href="<c:url value='${user.volunteerUri}' />" class="btn btn-edifice btn-xs">View volunteer information</a>
</h3>
<br />
<h4>Actions</h4>
<a id="user-password-change" href="#" class="btn btn-edifice">Change password</a>

<div id="user-password-change-modal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="user-password-change-label" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form class="modal-form" id="user-password-change-modal-form" method="POST" action="<c:url value="${changePasswordUri}" />">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h3 id="user-password-change-modal-label">Change password</h3>
                </div>
                <div class="modal-body">
                    <fieldset>
                        <input type="hidden" name="_method" value="PUT" />
                        <label>Password</label>
                        <input type="password" id="password" name="password" maxlength="50"  />
                        <label>Password Confirm</label>
                        <input type="password" name="passwordConfirm" maxlength="50"  />
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
