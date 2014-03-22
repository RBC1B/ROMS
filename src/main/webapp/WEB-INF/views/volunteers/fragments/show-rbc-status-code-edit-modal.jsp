<%--
Content of the model dialog used to update the volunteer's rbc status code.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="volunteer-rbc-status-code-modal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="volunteer-rbc-status-code-modal-label" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <c:url var="formAction" value="${volunteer.editRbcStatusCodeUri}" />
            <form class="modal-form" id="volunteer-rbc-status-code-modal-form" method="POST" action="${formAction}">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h3 id="volunteer-rbc-status-code-modal-label">Edit Volunteer RBC Status</h3>
                </div>
                <div class="modal-body">
                    <fieldset>
                        <input type="hidden" name="_method" value="PUT" />
                        <label for="rbcStatusSelect">RBC Status</label>
                        <div class="col-md-6">
                            <select id="rbcStatusSelect" class="form-control" name="rbcStatusCode">
                                <c:forEach items="${rbcStatusCodes}" var="rbcStatusCode" varStatus="status">
                                    <c:choose>
                                        <c:when test="${volunteer.status eq rbcStatusCode.value}">
                                            <option value="${rbcStatusCode.key}" selected="selected">${rbcStatusCode.value}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${rbcStatusCode.key}">${rbcStatusCode.value}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </div>
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
