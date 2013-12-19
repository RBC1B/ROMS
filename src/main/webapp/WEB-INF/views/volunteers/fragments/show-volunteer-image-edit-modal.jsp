<%--
Content of the model dialog used to add/edit a volunteer image
Author: rahulsingh
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div id="volunteer-image-modal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="volunteer-image-modal-label" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <c:url var="formAction" value="${volunteer.editImageUri}" />
            <form:form class="form" id="volunteer-image-modal-form" method="POST" action="${formAction}"
                       enctype="multipart/form-data">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 id="volunteer-image-modal-label" class="modal-title">Edit/Add Volunteer Image</h4>
                </div>
                <div class="modal-body">
                    <fieldset>
                        <label for="image">Select file for the volunteer image:</label>
                        <input class="btn btn-default" type="file" name="image" />
                    </fieldset>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <input id="submit-image" type="submit" class="btn btn-edifice" />
                </div>
            </form:form>
        </div>
    </div>
</div>