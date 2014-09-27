/* 
 * The MIT License
 *
 * Copyright 2014 RBC1B.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
$(document).ready(function() {

    $("input[name=birthDate]").datetimepicker({
        pickTime: false,
        minDate: '1/1/1900',
        format: "DD/MM/YYYY"
    });

    $("#update-contact").on("click", function(event) {
        event.preventDefault();
        $("#volunteer-contact-update").modal("show");
    });

    $("#cancel-update").on("click", function(event) {
        $("#volunteer-contact-update").modal("hide");
    });

    $("#update-contact-form").validate({
        debug: true,
        rules: {
            personId: {
                required: true,
                number: true
            },
            birthDate: {
                required: true
            }
        },
        messages: {
            personid: "Please type in your RBC ID number",
            birthDate: "Please enter your Date of Birth"
        },
        submitHandler: function(form) {
            $("#alert-update").hide();
            $.ajax({
                url: roms.common.relativePath + '/update-contact',
                data: $(form).serialize(),
                type: "post",
                cache: false,
                statusCode: {
                    302: function(){
                        $("#alert-update").html("<p><b>Error: </b>Could not connect to the server.</p>");
                        $("#alert-update").show();
                    },
                    403: function() {
                        $("#alert-update").html("<p><b>Error: </b>Your records do not match.</p>");
                        $("#alert-update").show();
                    },
                    503: function() {
                        $("#alert-update").html("<p><b>Error: </b>Unable to send out an email - please contact Volunteer Department.</p>");
                        $("#alert-update").show();
                    }
                },
                success: function(data, status, xhr) {
                    $("#alert-update").hide();
                    $("#volunteer-contact-update").modal("hide");
                    $("#volunteer-contact-update-success").modal("show");
                }
            });
        }
    });
});