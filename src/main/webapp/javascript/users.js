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
    // Disable username fields on load
    disableAll();

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

// Unlinking a person should diable username/password
    // Get User
    $("#surname").blur(function() {
        findUserByName(
                $("#forename").val(),
                $("#surname").val(),
                $("#personId"),
                populateUserFromPerson
                );
    });

    function populateUserFromPerson(selectedPersonId, forename, surname, $personId) {
        if (selectedPersonId) {
            $("#volunteer-linked").show("fast");
        } else {
            $("#volunteer-linked").hide("fast");
        }
        $personId.val(selectedPersonId);
        enableUserName();
    }
// Unlinking a person should disable username fields
    $("#volunteer-linked .close").on("click", function() {
        disableAll();
    });


    /**
     * Match a person to a given forename and surname (exact match).
     * This assumes we have the person-link-search-form and person-link-modal elements on the page
     * @param forename person's first name
     * @param surname person's last name
     * @param $personId jquery element linking to the input use to store the matched person id
     * @param populateFunction callback to populate the page with the selected person
     */
    findUserByName = function(forename, surname, $personId, populateFunction) {
        if (!forename || !surname) {
            // clear any linked name
            $personId.data("full-name", "");
            populateFunction(null, forename, surname, $personId);
            return;
        }

        var existingPersonName = $personId.data("full-name");
        if (existingPersonName == forename + " " + surname) {
            // no change in value
            return;
        }
        var existingPersonId = $personId.val();

        $.ajax({
            url: '/users/search-volunteer',
            contentType: "application/json",
            dataType: 'json',
            data: {
                forename: forename,
                surname: surname,
                checkVolunteer: true
            },
            success: function(data) {
                // no match, and no person linked. We don't show anything
                if ((!data || data.length === 0) && !existingPersonId) {
                    return;
                }

                templateData = {};
                templateData.existingPersonId = existingPersonId;
                templateData.existingPersonName = existingPersonName;

                if (data) {
                    templateData.matchedPersons = true;
                    templateData.results = data;
                }

                var template = $("#person-link-search-form").html();
                var html = Mustache.to_html(template, templateData);

                $("#person-link-modal .modal-body").html(html)
                var modalElement = $("#person-link-modal")

                modalElement.modal('show')

                // if they select the person id, set it to the hidden volunteer person id field
                $("a.matched-person").on("click", function(event) {
                    populateFunction($(this).data("personId"), forename, surname, $personId);
                    modalElement.modal('hide')
                });
            }
        });

        $personId.data("full-name", forename + " " + surname);
    }
    // Handle username - TO DO AJAX calls to check if username exists
    $("#userName").blur(function() {
        var username = $("#userName").val();
        if (username.length < 7) {
            $("#username-already-exists").hide();
            $("#username-too-small").show();
        } else {
            $("#username-too-small").hide();
            $("#username-already-exists").show();
            $.ajax({
                url: '/users/check-user',
                contentType: "application/json",
                dataType: 'json',
                data: {
                    username: username,
                },
                success: function(data) {
                    if (data == true) {
                        $("#username-already-exists").show();
                        disablePassword();
                    } else {
                        $("#username-already-exists").hide();
                        enablePassword();
                    }
                }
            });
            return true;
        }
    });

    $("#password2").blur(function() {
        var password1 = $("#password1").val();
        var password2 = $("#password2").val();
        if (password1.length < 8) {
            $("#password-not-matched").hide();
            $("#password-too-small").show();
            $("#password2").val('');
            $("#password1").focus();
        } else if (password1 != password2) {
            $("#password-too-small").hide();
            $("#password-not-matched").show();
            $("#password2").focus();
        } else {
            enableAclSubmit();
        }
    });


    // Disable/enable fields until we have a valid user
    function disableAll() {
        disableUserField();
        disableAcl();
        disableSubmit();
    }

    function disableUserField() {
        $("#userName").prop("disabled", true);
        $("#password1").prop("disabled", true);
        $("#password2").prop("disabled", true);
    }

    function disableAcl() {
        $("select").children().prop('disabled', true);
    }

    function disableSubmit() {
        $("#submit-button").children().prop('disabled', true);
    }

    function enableUserName() {
        $("#userName").removeAttr("disabled");
    }
    function disablePassword() {
        $("#password1").prop("disabled", true);
        $("#password2").prop("disabled", true);
    }
    function enablePassword() {
        $("#password1").removeAttr("disabled");
        $("#password2").removeAttr("disabled");
    }
    function enableAclSubmit() {
        $("select").children().prop('disabled', false);
        $("#submit-button").children().prop('disabled', false);
    }
});
