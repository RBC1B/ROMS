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

    $("#alert-update").hide();

    $("#cancel").on("click", function(event) {
        window.location.assign("/login");
    });

    $.validator.addMethod("phoneNumber", roms.common.validatorPhoneNumber, "Please enter a valid phone number");
    $.validator.addMethod("mobilePhoneNumber", roms.common.validatorMobilePhoneNumber, "Please enter a valid mobile phone number");
    
    $("#contact-update-form").validate({
        debug: true,
        rules: {
            email: {
                required: true,
                email: true
            },
            telephone: {
                required: true,
                phoneNumber: true
            },
            mobile: {
                required: true,
                phoneNumber: true,
                mobilePhoneNumber: true
            },
            workPhone: {
                phoneNumber: true
            },
            street: {
                minlength: 2,
                required: true
            },
            town: {
                minlength: 2,
                required: true
            },
            postcode: {
                minlength: 2,
                required: true
            }
        },
        submitHandler: function(form) {
            $("#a-submit-alerts").hide();
            $("#a-submit-alerts .alert").hide();
            $.ajax({
                url: window.location.href,
                data: $(form).serialize(),
                type: 'POST',
                cache: false,
                success: function(data, status, xhr) {
                    $("#a-submit-alerts .alert-success").show();
                    $("#a-submit-alerts").show();
                    $('html, body').animate({scrollTop:$('#a-submit-alerts').position().top});
                },
                error: function (jqXHR, textStatus) {
                    $("#a-submit-alerts .alert-error").show();
                    $("#a-submit-alerts").show();
                    $('html, body').animate({scrollTop:$('#a-submit-alerts').position().top});
                }
            });
        },
        errorPlacement: roms.common.validatorErrorPlacement
    });
});