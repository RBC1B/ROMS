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
    // list
    roms.common.datatables(
            $('#user-list'),
            {
                "iDisplayLength": 10,
                "aoColumnDefs": [
                    {
                        'bSortable': false,
                        'aTargets': [2]
                    }
                ]
            }
    );

    // edit
    $('#userForm').validate({
        rules:{
            userName: {
                required: true,
                minlength: 7,
                remote: uniqueUserNameValidation($("#userName"), $("#userId"))
            },
            password: {
                minlength: 7
            },
            passwordConfirm: {
                equalTo: "#password"
            }
        },
        messages: {
            userName: {
                remote: "This user name is already used"
            },
            passwordConfirm: {
                equalTo: "Passwords should match"
            }
        },
        errorPlacement: roms.common.validatorErrorPlacement
    });
    
    function uniqueUserNameValidation($nameInput, $idInput) {
        return {
            // check for an exact match. Populate the user id
            url: roms.common.relativePath + "/users/search",
            contentType: "application/json",
            dataType: "json",
            data: {
                name: function() {
                    return $nameInput.val();
                }
            },
            dataFilter: function(rawData) {
                var data = JSON.parse(rawData)
                // if we have a match, it must have the same id
                if (data && data.length > 0 && data[0].userName.toLowerCase() == $nameInput.val().toLowerCase() &&
                        $idInput.val() != data[0].personId) {
                    return false;
                } 
                // no match
                return true;
            }
        };
    }

});
