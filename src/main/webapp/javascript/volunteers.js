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
        minDate: "-70y",
        maxDate: "0d",
        yearRange: "-70:+0"
    });
    
    $("#congregationName").typeahead({
        remote: roms.common.relativePath + '/congregations/search?name=%QUERY',
        valueKey: 'name'
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
	roms.common.matchLinkedPerson(
            $("#spouseForename").val(),
            $("#spouseSurname").val(),
            $("#spousePersonId"),
            populateSpouseFromPerson
        );
    });
    
    $("#emergencyContactSurname").blur(function() {
	roms.common.matchLinkedPerson(
            $("#emergencyContactForename").val(),
            $("#emergencyContactSurname").val(),
            $("#emergencyContactPersonId"),
            populateEmergencyContactFromPerson
        );
    });

    $("input[name='gender']").change(function() {
        if($(this).val() === 'F') {
            $("input[name='elder']").attr("disabled", true);
            $("input[name='ministerialServant']").attr("disabled", true);
            $("#elderLabel").addClass('label-disabled');
            $("#ministerialServantLabel").addClass('label-disabled');
            // remove Wife option from Emergency Contact Relationship 
            // NOTE: .hide() for options may not work in some browsers
            $("#emergencyRelationshipCode").find("option[value='WF']").remove();
            if ($("#emergencyRelationshipCode option[value='HB']").length == 0) {
                var husbandOption = $("<option value='HB'>Husband</option>");
                husbandOption.insertAfter("#emergencyRelationshipCode option[value='FM']");
            }
        } else if ($(this).val() === 'M') {
            $("input[name='elder']").removeAttr("disabled");
            $("input[name='ministerialServant']").removeAttr("disabled");
             $("#elderLabel").removeClass('label-disabled');
            $("#ministerialServantLabel").removeClass('label-disabled');
            // remove Husband option from the Emergency Contact Relationship
            $("#emergencyRelationshipCode").find("option[value='HB']").remove();
            if ($("#emergencyRelationshipCode option[value='WF']").length == 0) {
                $("#emergencyRelationshipCode").append("<option value='WF'>Wife</option>");
            }
        }
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

    $("#spouse-linked button.close").click(function() {
        populateSpouseFromPerson(null, null, null, $("#spousePersonId"));
    });

    $("#emergency-contact-linked button.close").click(function() {
        populateEmergencyContactFromPerson(null, null, null, $("#emergencyContactPersonId"));
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

    $.validator.addMethod("phoneNumber", roms.common.validatorPhoneNumber, "Please enter a valid phone number");
    $.validator.addMethod("mobilePhoneNumber", roms.common.validatorMobilePhoneNumber, "Please enter a valid mobile phone number");
    
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
                email: true
            },
            telephone: {
                phoneNumber: true
            },
            mobile: {
                phoneNumber: true,
                mobilePhoneNumber: true
            },
            workPhone: {
                phoneNumber: true
            },
            maritalStatusCode: {
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
            emergencyRelationshipCode: {
                required: true
            },
            congregationName: {
                required: true,
                remote: roms.common.validation.congregation($("#congregationName"), $("#congregationId"))
            },
            congregationId: {
                required: true
            },
            formDate: {
                required: true
            }
        },
        messages: {
            congregationName: {
                remote: "Please provide the name of an existing congregation"
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
                if (!data && !existingPersonId) {
                    return;
                }

                // enrich the results, splitting out the volunters from the persons.
                // if we are matching an existing volunteer we don't want to create a new one
                var volunteers = new Array();
                var persons = new Array();
                if (data) {
                    for (var i = 0; i < data.length; i++) {
                        var result = data[i];

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

                $("#person-link-modal .modal-body").html(html)
                var modalElement = $("#person-link-modal")

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
                if (data.birthDate) {
                    $("#birthDate").val($.datepicker.formatDate('dd/mm/yy', new Date(data.birthDate)));
                }
                $("input[name='middleName']").val(data.middleName);
                $("input[name='telephone']").val(data.telephone);
                $("input[name='mobile']").val(data.mobile);
                $("input[name='workPhone']").val(data.workPhone);
                $("input[name='email']").val(data.email);
                if(data.congregation) {
                    $("input[name='congregationId']").val(data.congregation.id);
                    $("input[name='congregationName']").val(data.congregation.name);
                }
                if (data.address) {
                    $("input[name='street']").val(data.address.street);
                    $("input[name='town']").val(data.address.town);
                    $("input[name='postcode']").val(data.address.postcode);
                }
            }
        });
        $personId.val(selectedPersonId);
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
            $("#emergency-contact-additional-fields input").prop("disabled", false);
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
            "iDisplayLength": 10,
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
        remote: roms.common.relativePath + '/users/search?name=%QUERY',
        valueKey: 'userName'
    });

    $("#volunteerPersonal").validate({
        rules: {
            birthDate: {
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
                email: true
            },
            telephone: {
                phoneNumber: true
            },
            mobile: {
                phoneNumber: true,
                mobilePhoneNumber: true
            },
            workPhone: {
                phoneNumber: true
            },
            maritalStatusCode: {
                required: true
            }
        },
        submitHandler: function(form) {
            form.submit();
        },
        errorPlacement: roms.common.validatorErrorPlacement
    });
    
    $("#volunteerSpiritual").validate({
        rules: {
            baptismDate: {
                required: true
            },
            congregationName: {
                required: true,
                remote: roms.common.validation.congregation($("#congregationName"), $("#congregationId"))
            },
            congregationId: {
                required: true
            }
        },
        messages: {
            congregationName: {
                remote: "Please provide the name of an existing congregation"
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
                remote: roms.common.validation.user($("#interviewerAUserName"), $("#interviewerAPersonId"))
            },
            interviewerBUserName: {
                required: false,
                remote: roms.common.validation.user($("#interviewerBUserName"), $("#interviewerBPersonId"))
            }
        },
        messages: {
            interviewerAUserName: {
                remote: "Please provide the name of an existing user"
            },
            interviewerBUserName: {
                remote: "Please provide the name of an existing user"
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
            "iDisplayLength": 10
        }
    );

    roms.common.datatables(
        $("#volunteer-skills-skills"),
        {
            "iDisplayLength": 10
        }
    );

    roms.common.datatables(
        $("#volunteer-skills-qualifications"),
        {
            "iDisplayLength": 10
        }
    );

    $(".a-skill-description").tooltip();
    $(".a-qualification-description").tooltip();

    roms.common.persistentTabs();

    $(".a-edit-hover").hover(
        function over() {
            $(this).find("a").removeClass("hide") ;
        },
        function out() {
            $(this).find("a").addClass("hide");
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
                $("#volunteer-comments-content").html($("#volunteer-comments-modal-form textarea[name='comments']").val());
                $('#volunteer-comments-modal').modal('hide');
            }
        });
        return false;
    });

    $("#volunteer-rbc-status-code a").on("click", function(e) {
        e.preventDefault();

        $('#volunteer-rbc-status-code-modal').modal('show');

    });

    $("#volunteer-rbc-status-code-modal-form").submit(function() {
        var $form = $(this);
        // .serialize() to send the form input name-value pairs as params.
        $.ajax({
            // url can be obtained via the form action attribute passed to the JSP.
            url: $form.attr("action"),
            data: $form.serialize(),
            type: "POST",
            statusCode: {
                404: function() {
                    alert("Volunteer not found");
                },
                500: function() {
                    alert("Failed to update volunteer's RBC Status");
                }
            },
            success: function() {
                $("#volunteer-rbc-status-code-content").html($("#rbcStatusSelect").find(":selected").text());
                $('#volunteer-rbc-status-code-modal').modal('hide');
            }
        });
        return false;
    });

    $("#disabled-badge-button").popover({
        placement: 'right',
        trigger: 'hover',
        title: $(this).data("title"),
        content: $(this).data("content")
    });
    
    if ($.trim($("#birth-date").text()) === '-') {
        $("#badge-button").click(function(e) {
            e.preventDefault();
            $("#birth-date-badge-alert").before("<br />");
            $("#birth-date-badge-alert").show();
        });
    }
    
    // volunteers experience list
    var volunteerExperienceListActionTemplate = $("#read-only-list-action").html();
    roms.common.datatables(
        $("#volunteer-experience-list"),
        {
            "iDisplayLength": 10,
            "bProcessing": true,
            "bServerSide": true,
            "sAjaxSource": roms.common.relativePath + '/volunteer-experience',
            "aoColumns": [
                {   "sName": "person.forename", "mData": "person.forename" },
                {   "sName": "person.surname", "mData": "person.surname" },
                {   "sName": "person.congregation", "mData": "person.congregation.name", "sDefaultContent": "" },
                {   "sName": "name", "mData": "name", "sDefaultContent": "" },
                {   "sName": "experienceDescription", "mData": "experienceDescription", "sDefaultContent": "" },
                {   "sName": "experienceYears", "mData": "experienceYears", "sDefaultContent": "" },
                {   "sName": "action", "bSortable": false,
                    "mData":
                        function ( data, type, val ) {
                            data.person.uriBase = roms.common.relativePath;
                            return Mustache.to_html(volunteerExperienceListActionTemplate, data.person);
                        }
                }
            ]            
        }
    );
    
    $('img').mouseout(function() {
        $('#popup').fadeOut('slow');
    })
    .mouseover(function() {
        $('#popup').fadeIn(2000);
    });
    
    $('#image-link').click(function(){
        $('#volunteer-image-modal').modal('show');
    });
    
    $('#submit-image').on("click", function(){
        $('#volunteer-image-modal').modal('hide');
    });
    
    // volunteer interview sessions
    roms.common.datatables(
        $("#volunteer-interview-session-list"),
        {
            "iDisplayLength": 10,
            "aaSorting": [[ 0, "desc" ]],
            "aoColumnDefs": [
                {
                    'bSortable': false,
                    'aTargets': [4]
                }
            ]
        });
    
    // volunteer interview sessions
    roms.common.datatables(
        $("#session-volunteer-list"),
        {
            "iDisplayLength": 50,
            "aoColumnDefs": [
                {
                    'bSortable': false,
                    'aTargets': [6]
                }
            ]
        });
    
    // show modal form when editing the volunteer interview status
    $(".a-volunteer-edit").on("click", function(){
        var $modalElement = $("#volunteer-invitation-modal")
        initialiseVolunteerInvitationModalForm($modalElement, $(this));
        
        $modalElement.modal('show');
    });
    
    // interview session edit form
    $("#kingdomHallName").typeahead({
        remote: roms.common.relativePath + '/kingdom-halls/search?name=%QUERY',
        valueKey: 'name'
    });

    // we always clear the kingdom hall id on change.
    // it will be re-calculated in validation
    $("#kingdomHallName").change(function() {
        $("#kingdomHallId").val(null);
    });
    
    $('#interviewSessionForm').validate({
        rules:{
            date: {
                required: true
            },
            time: {
                required: true
            },
            kingdomHallName: {
                required: true,
                remote: roms.common.validation.kingdomHall($("#kingdomHallName"), $("#kingdomHallId"))
            },
            conments: {
                maxlength: 250
            }
        },
        messages: {
            kingdomHallName: {
                remote: "Please provide the name of an existing kingdom hall"
            }
        },
        errorPlacement: roms.common.validatorErrorPlacement
    });
    
    
    $("#volunteer-invitation-modal-form").submit(function() {
        var $form = $(this);
        $.ajax({
            url: $form.attr("action"),
            data: $form.serialize(),
            type: "POST",
            statusCode: {
                404: function() {
                    alert("Volunteer invitation not found");
                },
                500: function() {
                    alert("Failed to save volunteer interview status");
                }
            },
            success: function() {
                // update the data in the table
                var $table = $("#session-volunteer-list");
                var volunteerId = $form.data("volunteer-id");
                
                var $row = null;
                $("tr",$table).each(function() {
                    if ($("td.a-volunteer-id", $(this)).html() == volunteerId) {
                        $row = $(this)[0];
                        return false;
                    }
                });
                
                var $selectedStatus = $("select[name='interviewStatusCode'] option:selected", $form);

                // show/hide the completed button if the status is set to invited
                // the button is not included in the dom is the session date is in the future
                // so no check required here
                $selectedStatus.val() == 'CF'?$(".a-volunteer-completed", $row).show():$(".a-volunteer-completed", $row).hide()
                
                var dataTable = $table.dataTable();
                dataTable.fnUpdate($selectedStatus.text(), $row, 5, 0);
                
                var comments = $("textarea[name='comments']", $form).val();
                // we set the table to refresh since this is the last cell update
                dataTable.fnUpdate(comments, $row, 4, 0);
                
                $('#volunteer-invitation-modal').modal('hide');
            }
        });
        return false;
    });
    
    $("a.a-volunteer-completed").on("click", function() {
        // not the prettiest implementation, but mark the interview completed by 
        // submitting it through the modal form
        var $modalElement = $("#volunteer-invitation-modal")
        initialiseVolunteerInvitationModalForm($modalElement, $(this));
        
        // explicitly set the status to completed
        $("select[name='interviewStatusCode']", $modalElement).val('CP')
        
        $("#volunteer-invitation-modal-form").submit();
        
        // open the volunteer page in a new form
        var $volunteerCell = $("td.a-volunteer-id", $(this).closest("tr"));
        var editRbsStatusUri =  $volunteerCell.data("edit-rbc-status-uri");
        var volunteerId = $volunteerCell.html();
        window.open(roms.common.relativePath + editRbsStatusUri,'_blank_' + volunteerId);
    });
    
    function initialiseVolunteerInvitationModalForm($modalElement, $button) {
        // initialise the values
        var $row = $button.closest("tr")
        var comments = $("td.a-volunteer-comments", $row).html();
        $("textarea[name='comments']", $modalElement).val(comments);
        
        var interviewStatus = $("td.a-volunteer-interview-status", $row).html();
        $("select[name='interviewStatusCode'] option", $modalElement).filter(function() {
            //may want to use $.trim in here
            return $(this).text() == interviewStatus; 
        }).prop('selected', true);
        
        $("form", $modalElement).prop('action', roms.common.relativePath + $("td.a-volunteer-id", $row).data("uri"));
        $("form", $modalElement).data("volunteer-id", $("td.a-volunteer-id", $row).html());
    }
    
    
    // volunteer interview session invitation
    roms.common.datatables(
        $("#session-invitation-list"),
        {
            "iDisplayLength": 100,
            "aLengthMenu": [[25, 50, 100, -1], [25, 50, 100, "All"]],
            "aoColumnDefs": [
                {
                    'bSortable': false,
                    'aTargets': [0]
                }
            ]
        });
    
    $(".a-invite-all").on("click", function() {
        var checked = $(this).prop("checked");
        $(".a-invite-all").prop({
            checked: checked,
            indeterminate: false
        });
        $invite = $(".a-invite");
        $invite.prop("checked", checked)
        if (checked) {
            $invite.closest("tr").addClass('success');
        } else {
            $invite.closest("tr").removeClass('success');
        }
    });
    
    $(".a-invite").on("click", function() {
        var checked = $(this).prop("checked");
        $(this).toggleClass('success');
        // if we have just checked the checkbox
        if (checked) {
            $(this).closest("tr").addClass('success');
            
            // if all the volunteers are checked, the invite alls should be checked
            if ($(".a-invite:not(:checked)").length == 0) {
                $(".a-invite-all").prop({
                    checked: true,
                    indeterminate: false
                });
            } else {
                // at least some are checked, so indeterminate
                $(".a-invite-all").prop({
                    checked: true,
                    indeterminate: true
                });
            }            
        } else {
            $(this).closest("tr").removeClass('success');
            
            // if none of the volunteers are checked, the invite alls should be unchecked
            if ($(".a-invite:checked").length == 0) {
                $(".a-invite-all").prop({
                    checked: false,
                    indeterminate: false
                });
            } else {
                // some of them are checked, the invite alls are indeterminate
                $(".a-invite-all").prop({
                    checked: true,
                    indeterminate: true
                });
            }
        }
    });
    
    $("#invite-volunteers").on("click", function() {
        // if none of the volunteers are selected, show an error message
        var $selected = $(".a-invite:checked");
        if ($selected.length == 0) {
            $("#invite-volunteers-error").removeClass("hide");
            return false;
        }
        
        var volunteerIds = [];
        $selected.each(function(i, el) {
            volunteerIds.push($(this).data("volunteer-id"));
        });
        
        $("input[name='volunteerIds']").val(volunteerIds);
        $("#invite-volunteer-form").submit();
        
    });
    
});
