if (!roms) {
    var roms = {};
};

if(!roms.volunteer){
    roms.volunteer = {};
}

/**
 * Match the entered name of the volunteer with an existing person/volunteer
 * @param forename entered forename
 * @param surname entered surname
 * @param $personId jquery reference to the hidden field containing the person id
 */
roms.volunteer.matchVolunteerPerson = function(forename, surname, $personId) {

    if(!forename || !surname) {
        return;
    }

    var existingPersonName = $personId.data("full-name");
    if (existingPersonName == forename + " " + surname) {
        // no change in value
        return;
    }
    this.findVolunteerPerson(forename, surname, $personId, existingPersonName);

    $personId.data("full-name", forename + " " + surname);
}

/**
 * Look up the person matching the name and show a popover
 * to allow the user the select the match, if any
 */
roms.volunteer.findVolunteerPerson = function(forename, surname, $personId, existingPersonName)  {
    var _parent = this;
    var existingPersonId = $personId.val();
    $.ajax({
        url: roms.common.relativePath + 'persons/search',
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
                _parent.populateVolunteerFromPerson($(this).data("person-id"), $personId);
                modalElement.modal('hide')
            });
        }
    });
}

/**
 * Populate the volunteer form from the existing person
 */
roms.volunteer.populateVolunteerFromPerson = function(selectedPersonId, $personId) {
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
        url: roms.common.relativePath + 'persons/' + selectedPersonId  + "/reference",
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

roms.volunteer.matchEmergencyContactPerson = function(forename, surname, $personId) {
    var _parent = this;
    if(!forename || !surname) {
        return;
    }

    var existingPersonName = $personId.data("full-name");
    if (existingPersonName == forename + " " + surname) {
        // no change in value
        return;
    }
    var existingPersonId = $personId.val();

    $.ajax({
        url: roms.common.relativePath + 'persons/search',
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

            var template = $("#volunteer-emergency-contact-search-form").html();
            var html = Mustache.to_html(template, data);

            $("#volunteer-person-modal .modal-body").html(html)
            var modalElement = $("#volunteer-person-modal")

            modalElement.modal('show')

            // if they select the person id, set it to the hidden volunteer person id field
            $("a.matched-person").on("click", function(event){
                _parent.populateEmergencyContactFromPerson($(this).data("person-id"), $personId);
                modalElement.modal('hide')
            });
        }
    });

    $personId.data("full-name", forename + " " + surname);
}

roms.volunteer.populateEmergencyContactFromPerson = function(selectedPersonId, $personId) {

    if (selectedPersonId) {
        // disable all the additional fields. include the text that indicates we are
        $("#emergency-contact-additional-fields input").prop("disabled", true);
        $("#emergency-contact-additional-fields").hide("fast");
        $("#emergency-contact-linked").show("fast");
    } else {
        alert("unselected");
        $("#emergency-contact-additional-fields input").prop("disabled", true)
        $("#emergency-contact-additional-fields").show("fast");
        $("#emergency-contact-linked").hide("fast");
    }
}

$(document).ready(function() {
    $("#surname").blur(function() {
        roms.volunteer.matchVolunteerPerson($("#forename").val(), $("#surname").val(), $("#personId"));
    });

    $("#emergencyContactSurname").blur(function() {
        roms.volunteer.matchEmergencyContactPerson(
            $("#emergencyContactForename").val(),
            $("#emergencyContactSurname").val(),
            $("#emergencyContactPersonId")
        );
    });

    $(".datepicker").datepicker({
        dateFormat: "dd/mm/yy",
        changeYear: true
    });

    $("#congregationName").typeahead({
        source: roms.common.congregationTypeAheadSource,
        minLength: 2
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
                    url: roms.common.relativePath + "congregations/search",
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
        highlight: roms.common.validatorHighlight,
        success: roms.common.validatorSuccess,
        errorPlacement: roms.common.validatorErrorPlacement
    });

});
