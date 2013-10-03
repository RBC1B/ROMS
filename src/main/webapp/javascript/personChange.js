/*
 * The MIT License
 *
 * Copyright 2013 RBC1B.
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
    $.validator.addMethod("phoneNumber", roms.common.validatorPhoneNumber, "Please enter a valid phone number");
    $.validator.addMethod("mobilePhoneNumber", roms.common.validatorMobilePhoneNumber, "Please enter a valid mobile phone number");
    $("#personchange").validate({
        rules: {
            forename: {
                minlength: 2,
                required: true
            },
            surname: {
                minlength: 2,
                required: true
            },
            email: {
                email: true
            },
            telephone: {
                phoneNumber: true
            },
            mobile: {
                phoneNumber: true,
                mobilePhoneNumber: true
            },
            workphone: {
                phoneNumber: true
            }
        },
        errorPlacement: roms.common.validatorErrorPlacement
    });

    // list
    var listActionTemplate = $("#list-action").html();

    roms.common.datatables(
            $('#personchange-list'),
            {
                "iDisplayLength": 10,
                "bProcessing": true,
                "bServerSide": true,
                "sAjaxSource": roms.common.relativePath + '/personchange',
                "aoColumns": [
                    {"sName": "forename", "mData": "forename"},
                    {"sName": "surname", "mData": "surname"},
                    {"sName": "action", "bSortable": false,
                        "mData":
                                function(data, type, val) {
                                    data.uriBase = roms.common.relativePath;
                                    return Mustache.to_html(listActionTemplate, data);
                                }
                    }
                ]
            }
    );

});

