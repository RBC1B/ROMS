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

    // Password strength meter
    $("#password1").change(function() {
        "use strict";
        var options = {};
        options.ui = {
            container: "#pwd-container",
            viewports: {
                progress: ".pwstrength_viewport_progress",
                verdict: ".pwstrength_viewport_verdict"
            }
        };
        options.common = {
            zxcvbn: true,
            minChar: 7,
            usernameField: "#userName",
            bootstrap2: false,
            showPopover: true
        };
        $(':password').pwstrength(options);
    });
    /*
     // Disable fields until we have a valid user
     $("#userName").attr("disabled", "disabled");
     $("#password1").attr("disabled", "disabled");
     $("#password2").attr("disabled", "disabled");
     
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
     $("#volunteer-linked").show("fast");
     }
     $personId.val(selectedPersonId);
     }
     */
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
            url: '/users/search',
            contentType: "application/json",
            dataType: 'json',
            data: {
                forename: forename,
                surname: surname,
                checkVolunteer: false
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
                    populateFunction($(this).data("person-id"), forename, surname, $personId);
                    modalElement.modal('hide')
                });
            }
        });

        $personId.data("full-name", forename + " " + surname);
    }
});
