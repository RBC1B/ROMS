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

    // created/edit common
    $(".datepicker").datepicker({
        dateFormat: "dd/mm/yy",
        changeYear: true,
        maxDate: "0"
    });
    
    $("#congregationName").typeahead({
        source: roms.common.congregationTypeAheadSource,
        minLength: 2
    });
    
    // we always clear the congregation id on change.
    // it will be re-calculated in validation
    $("#congregationName").change(function() {
        $("#congregationId").val(null);
    });

    // create
    $("#surname, #forename").blur(function() {
        matchVolunteerPerson($("#forename").val(), $("#surname").val(), $("#personId"));
    });

    $("#spouseSurname").blur(function() {
        matchLinkedPerson(
            $("#spouseForename").val(),
            $("#spouseSurname").val(),
            $("#spousePersonId"),
            populateSpouseFromPerson
        );
    });
    
    $("#emergencyContactSurname").blur(function() {
        matchLinkedPerson(
            $("#emergencyContactForename").val(),
            $("#emergencyContactSurname").val(),
            $("#emergencyContactPersonId"),
            populateEmergencyContactFromPerson
        );
    });

    // elder and ministerial values are exclusive
    $("input[name='elder']").change(function() {
        if($(this).is(':checked')) {
            $("input[name='ministerialServant']").prop("checked", false);
        }
    });
    $("input[name='ministerialServant']").change(function() {
        if($(this).is(':checked')) {
            $("input[name='elder']").prop("checked", false);
        }
    });

    $("#spouse-linked a").click(function() {
        populateSpouseFromPerson(null, null, null, $("#spousePersonId"));
    });

    $("#emergency-contact-linked button.close").click(function() {
        populateEmergencyContactFromPerson(null, $("#emergencyContactPersonId"));
    });

    // when adding a trades row, clone the last one, clear the values
    // and at it after that row
    $("#trades-row-add").click(function() {
       var $lastTradesRow = $(".trades-row").last();
       var $clonedTradesRow = $lastTradesRow.clone();
       var lastIndex = $lastTradesRow.data("index");
       var nextIndex = lastIndex + 1;
       $clonedTradesRow.data("index", nextIndex);
       $clonedTradesRow.find("input").each(function() {
           $(this).val('');
           var name = $(this).prop("name");
           name = name.replace('[' + lastIndex + ']', '[' + nextIndex + ']');
           $(this).prop("name", name);
       })
       $clonedTradesRow.hide();
       $clonedTradesRow.insertAfter($lastTradesRow);
       initialiseTradeRow($clonedTradesRow);
       $clonedTradesRow.slideDown(500);
    });

    $("#volunteer").validate({
        rules: {
            forename: {
                minlength: 2,
                required: true
            },
            surname: {
                minlength: 2,
                required: true
            },
            gender: {
                required: true
            },
            birthDate: {
                required: true
            },
            baptismDate: {
                required: true
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
            },
            email: {
                required: true,
                email: true
            },
            maritalStatusId: {
                required: true
            },
            emergencyContactForename: {
                minlength: 2,
                required: true
            },
            emergencyContactSurname: {
                minlength: 2,
                required: true
            },
            emergencyContactStreet: {
                minlength: 2,
                required: true
            },
            emergencyContactTown: {
                minlength: 2,
                required: true
            },
            emergencyRelationshipId: {
                required: true
            },
            congregationName: {
                required: true,
                remote: {
                    // check for an exact match. Populate the congregation id
                    url: roms.common.relativePath + "/congregations/search",
                    contentType: "application/json",
                    dataType: "json",
                    data: {
                        name: function() {
                            return $("#congregationName").val();
                        }
                    },
                    dataFilter: function(rawData) {
                        var data = JSON.parse(rawData)
                        if (data.results && data.results[0].name == $("#congregationName").val()) {
                            $("#congregationId").val(data.results[0].id);
                            return true;

                        }
                        return false;
                    }
                }
            },
            congregationId: {
                required: true
            },
            formDate: {
                required: true
            }
        },
        submitHandler :function(form) {
            form.submit();
        },
        errorPlacement: roms.common.validatorErrorPlacement
    });

    $(".trades-row").each(function() {
        initialiseTradeRow($(this));
    });

    /**
     * Initialise the actions on the trade rows.
     * Note: this function call needs to be made after the initial form.validate
     * initialisation, otherwise we cannot add validation.
     */
    function initialiseTradeRow($row) {
        // limit inputs
        $(".trade-experience-years", $row).numeric({ negative : false, decimal: false });

        // add validation. completely empty rows are allowed (ignored server side)
        // so the field is only required if other fields are populated
        $(".trade-experience-name", $row).rules("add", {
                required: function() {
                var result = false;
                $("input", $row).each(function() {
                    if ($(this).val()) {
                        // the input has a value set, the name field is required
                        result = true;
                        // break the loop
                        return false;
                    }
                    return true;
                });
                return result;
            }
        });

        // add delete button functionality
        $(".trades-row-delete", $row).click(function() {
            // if this is the only row, clear the values instead
            if ($(".trades-row").length < 2) {
                $("input", $row).val('');
            } else {
                $row.slideUp(1000, new function() {
                    $row.remove();
                });
            }
        });
    }

    /**
     * Match the entered name of the volunteer with an existing person/volunteer
     * @param forename entered forename
     * @param surname entered surname
     * @param $personId jquery reference to the hidden field containing the person id
     */
    function matchVolunteerPerson(forename, surname, $personId) {

       if(!forename || !surname) {
           return;
       }

       var existingPersonName = $personId.data("full-name");
       if (existingPersonName == forename + " " + surname) {
           // no change in value
           return;
       }
       findVolunteerPerson(forename, surname, $personId, existingPersonName);

       $personId.data("full-name", forename + " " + surname);
    }

    /**
     * Look up the person matching the name and show a popover
     * to allow the user the select the match, if any
     */
    function findVolunteerPerson(forename, surname, $personId, existingPersonName)  {
        var existingPersonId = $personId.val();
        $.ajax({
            url: roms.common.relativePath + '/persons/search',
            contentType: "application/json",
            dataType: 'json',
            data:  {
                forename: forename,
                surname: surname,
                checkVolunteer: true
            },
            success: function(data) {
                // no match, and no person linked. We don't show anything
                if (!data.results && !existingPersonId) {
                    return;
                }

                // enrich the results, splitting out the volunters from the persons.
                // if we are matching an existing volunteer we don't want to create a new one
                var volunteers = new Array();
                var persons = new Array();
                if (data.results) {
                    for (var i = 0; i < data.results.length; i++) {
                        var result = data.results[i];

                        if (result.personId != existingPersonId) {
                            if (result.volunteer) {
                                volunteers.push(result);
                            } else {
                                persons.push(result);
                            }
                        }
                    }
                }

                data.existingPersonId = existingPersonId;
                data.existingPersonName = existingPersonName;
                if (volunteers.length > 0) {
                    data.matchedVolunteers = true;
                    data.volunteers = volunteers;
                }

                if (persons.length > 0) {
                    data.matchedPersons = true;
                    data.persons = persons;
                }

                var template = $("#volunteer-person-search-form").html();
                var html = Mustache.to_html(template, data);

                $("#volunteer-person-modal .modal-body").html(html)
                var modalElement = $("#volunteer-person-modal")

                modalElement.modal('show')

                // if they select the person id, set it to the hidden volunteer person id field
                $("a.matched-person").on("click", function(event){
                    populateVolunteerFromPerson($(this).data("person-id"), $personId);
                    modalElement.modal('hide')
                });
            }
        });
    }

    /**
     * Populate the volunteer form from the existing person
     */
    function populateVolunteerFromPerson(selectedPersonId, $personId) {
        // chosen nobody. Clear the existing value
        if (!selectedPersonId) {
            $personId.val(selectedPersonId);
            return;
        }
        var existingPersonId = $personId.val();

        if (existingPersonId == selectedPersonId) {
            return;
        }

        // new person - pull the person data and populate the form.
        $.ajax({
            url: roms.common.relativePath + '/persons/' + selectedPersonId  + "/reference",
            contentType: "application/json",
            dataType: 'json',
            success: function(data) {
                $("#birthDate").val(data.birthDate);
                $("input[name='middleName']").val(data.middleName);
                $("input[name='telephone']").val(data.telephone);
                $("input[name='mobile']").val(data.mobile);
                $("input[name='workPhone']").val(data.workPhone);
                $("input[name='email']").val(data.email);
                if(data.congregation) {
                    $("input[name='congregationId']").val(data.congregation.congregationId);
                }
                if (data.address) {
                    $("input[name='street']").val(data.address.street);
                    $("input[name='town']").val(data.address.town);
                    $("input[name='county']").val(data.address.county);
                    $("input[name='postcode']").val(data.address.postcode);
                }
            }
        });
        $personId.val(selectedPersonId);
    }

    function matchLinkedPerson(forename, surname, $personId, populateFunction) {
        if(!forename || !surname) {
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
            url: roms.common.relativePath + '/persons/search',
            contentType: "application/json",
            dataType: 'json',
            data:  {
                forename: forename,
                surname: surname,
                checkVolunteer: false
            },
            success: function(data) {
                // no match, and no person linked. We don't show anything
                if (!data.results && !existingPersonId) {
                    return;
                }

                data.existingPersonId = existingPersonId;
                data.existingPersonName = existingPersonName;

                if (data.results) {
                    data.matchedPersons = true;
                }

                var template = $("#volunteer-person-link-search-form").html();
                var html = Mustache.to_html(template, data);

                $("#volunteer-person-modal .modal-body").html(html)
                var modalElement = $("#volunteer-person-modal")

                modalElement.modal('show')

                // if they select the person id, set it to the hidden volunteer person id field
                $("a.matched-person").on("click", function(event){
                    populateFunction($(this).data("person-id"), forename, surname, $personId);
                    modalElement.modal('hide')
                });
            }
        });

        $personId.data("full-name", forename + " " + surname);
    }

    function populateSpouseFromPerson(selectedPersonId, forename, surname, $personId) {
        if (selectedPersonId) {
            var template = $("#linked-person-text").html();
            var data = {};
            data.forename = forename;
            data.surname = surname;
            var html = Mustache.to_html(template, data);
            $("#spouse-linked-text").html(html);
            
            $("#spouse-linked").show("fast");
            $("#spouse-unlinked").hide("fast");
        } else {
            $("#spouse-linked").hide("fast");
            $("#spouse-unlinked input").val("");
            $("#spouse-unlinked").show("fast");
        }
        $personId.val(selectedPersonId);
    }

    function populateEmergencyContactFromPerson(selectedPersonId, forename, surname, $personId) {

        if (selectedPersonId) {
            // disable all the additional fields. include the text that indicates we are
            $("#emergency-contact-additional-fields input").prop("disabled", true);
            $("#emergency-contact-additional-fields").hide("fast");
            $("#emergency-contact-linked").show("fast");
        } else {
            $("#emergency-contact-additional-fields input").prop("disabled", false)
            $("#emergency-contact-additional-fields").show("fast");
            $("#emergency-contact-linked").hide("fast");
        }
        $personId.val(selectedPersonId);
    }

    // run the spouse display on page load
    populateSpouseFromPerson($("#spousePersonId").val(), $("#spouseSurname").val(), $("#spouseForename").val(), $("#spousePersonId"))
    
    
    // list
    var listActionTemplate = $("#list-action").html();

    roms.common.datatables(
        $('#volunteer-list'),
        {
            "iDisplayLength": 20,
            "bProcessing": true,
            "bServerSide": true,
            "sAjaxSource": roms.common.relativePath + '/volunteers',
            "aoColumns": [
                {   "sName": "ID", "mData": "id" },
                {   "sName": "forename", "mData": "forename" },
                {   "sName": "surname", "mData": "surname" },
                {   "sName": "congregation", "mData": "congregation.name", "sDefaultContent": "" },
                {   "sName": "action", "bSortable": false,
                    "mData":
                        function ( data, type, val ) {
                            data.uriBase = roms.common.relativePath;
                            return Mustache.to_html(listActionTemplate, data);
                        }
                }
            ]
        }
    );

    // edit
    $(".user").typeahead({
        source: roms.common.userTypeAheadSource,
        minLength: 2
    });

    $("#volunteerSpiritual").validate({
        rules: {
            baptismDate: {
                required: true
            },
            congregationName: {
                required: true,
                remote: {
                    // check for an exact match. Populate the congregation id
                    url: roms.common.relativePath + "/congregations/search",
                    contentType: "application/json",
                    dataType: "json",
                    data: {
                        name: function() {
                            return $("#congregationName").val();
                        }
                    },
                    dataFilter: function(rawData) {
                        var data = JSON.parse(rawData)
                        if (data.results && data.results[0].name == $("#congregationName").val()) {
                            $("#congregationId").val(data.results[0].id);
                            return true;

                        }
                        return false;
                    }
                }
            },
            congregationId: {
                required: true
            }
        },
        submitHandler :function(form) {
            form.submit();
        },
        errorPlacement: roms.common.validatorErrorPlacement
    });

    $("#clear-availability").on("click", function(event){
       $(".availability").prop('checked', false);
       return false;
    });

    $("#set-availability").on("click", function(event){
       $(".availability").prop('checked', true);
       return false;
    });

    // we always clear the person id on change.
    // it will be re-calculated in validation
    $("#interviewerAUserName").change(function() {
        $("#interviewerAPersonId").val(null);
    });

    $("#interviewerBUserName").change(function() {
        $("#interviewerBPersonId").val(null);
    });

    $("#volunteerRbcStatus").validate({
        rules: {
            interviewerAUserName: {
                required: false,
                remote: {
                    // check for an exact match. Populate the congregation id
                    url: roms.common.relativePath + "/users/search",
                    contentType: "application/json",
                    dataType: "json",
                    data: {
                        name: function() {
                            return $("#interviewerAUserName").val();
                        }
                    },
                    dataFilter: function(rawData) {
                        var data = JSON.parse(rawData)
                        if (data.results && data.results[0].userName == $("#interviewerAUserName").val()) {
                            $("#interviewerAPersonId").val(data.results[0].personId);
                            return true;
                        } else {
                            $("#interviewerAPersonId").val(null);
                        }
                        return false;
                    }
                }
            },
            interviewerBUserName: {
                required: false,
                remote: {
                    // check for an exact match. Populate the congregation id
                    url: roms.common.relativePath + "/users/search",
                    contentType: "application/json",
                    dataType: "json",
                    data: {
                        name: function() {
                            return $("#interviewerBUserName").val();
                        }
                    },
                    dataFilter: function(rawData) {
                        var data = JSON.parse(rawData)
                        if (data.results && data.results[0].userName == $("#interviewerBUserName").val()) {
                            $("#interviewerBPersonId").val(data.results[0].personId);
                            return true;
                        } else {
                            $("#interviewerBPersonId").val(null);
                        }
                        return false;
                    }
                }
            }
        },
        submitHandler :function(form) {
            form.submit();
        },
        errorPlacement: roms.common.validatorErrorPlacement
    });

    // display
    roms.common.datatables(
        $("#volunteer-skills-experience"),
        {
            "iDisplayLength": 20
        }
    );

    roms.common.datatables(
        $("#volunteer-skills-skills"),
        {
            "iDisplayLength": 20
        }
    );

    roms.common.datatables(
        $("#volunteer-skills-qualifications"),
        {
            "iDisplayLength": 20
        }
    );

    $(".a-skill-description").tooltip();
    $(".a-qualification-description").tooltip();

    roms.common.persistentTabs();

    $(".a-edit-hover").hover(
        function over() {
            $(this).find("a").show();
        },
        function out() {
            $(this).find("a").hide();
        }
    );

    $("#volunteer-name a").on("click", function(e) {
        e.preventDefault();

        // make sure the values are set to the current name
        // if the user edits it, then cancels the fields would be wrong
        var $volunteerName = $("#volunteer-name");
        $("#volunteer-name-modal-form input[name='forename']").val($volunteerName.data("forename"));
        $("#volunteer-name-modal-form input[name='middleName']").val($volunteerName.data("middle-name"));
        $("#volunteer-name-modal-form input[name='surname']").val($volunteerName.data("surname"));

        $('#volunteer-name-modal').modal('show');
    });

    $("#volunteer-name-modal-form").validate({
        rules: {
            forename: {
                minlength: 2,
                required: true
            },
            surname: {
                minlength: 2,
                required: true
            }
        },
        submitHandler :function(form) {
            $.ajax({
                url: $(form).attr("action"),
                data: $(form).serialize(),
                type: "POST",
                statusCode: {
                    404: function() {
                        alert("Volunteer not found");
                    },
                    500: function() {
                        alert("Failed to save volunteer name");
                    }
                },
                success: function() {
                    updateVolunteerName();
                    $('#volunteer-name-modal').modal('hide');
                }
            });
        },
        errorPlacement: roms.common.validatorErrorPlacement
    });

    function updateVolunteerName() {

        var forename = $("#volunteer-name-modal-form input[name='forename']").val();
        var middleName = $("#volunteer-name-modal-form input[name='middleName']").val();
        var surname = $("#volunteer-name-modal-form input[name='surname']").val();

        $("#volunteer-full-name").html(forename + " " + middleName + " " + surname);

        $("#volunteer-name").data("forename", forename);
        $("#volunteer-name").data("middle-name", middleName);
        $("#volunteer-name").data("surname", surname);
    }

    $("#volunteer-comments a").on("click", function(e) {
        e.preventDefault();
        // make sure the values are set to the current comments
        // if the user edits it, then cancels the fields would be wrong
        $("#volunteer-comments-modal-form input[name='comments']").val($("#volunteer-comments-content").html());

        $('#volunteer-comments-modal').modal('show');
    });

    $("#volunteer-comments-modal-form").submit(function() {
        var $form = $(this);
        $.ajax({
            url: $form.attr("action"),
            data: $form.serialize(),
            type: "POST",
            statusCode: {
                404: function() {
                    alert("Volunteer not found");
                },
                500: function() {
                    alert("Failed to save volunteer comments");
                }
            },
            success: function() {
                $("#volunteer-comments-content").html($("#volunteer-comments-modal-form input[name='comments']").val());
                $('#volunteer-comments-modal').modal('hide');
            }
        });
        return false;
    });
});
