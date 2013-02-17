$(document).ready(function() {
    $('#surname').blur(function() {
        matchVolunteerPerson();
    });

    $(".datepicker").datepicker({
        dateFormat: "dd/mm/yy"
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
            formDate: {
                required: true
            }
        },
        highlight: function(element) {
            $(element).closest('.control-group').removeClass('success').addClass('error');
        },
        success: function(element) {
            element.addClass('valid').closest('.control-group').removeClass('error').addClass('success');
        }

    });

});

function matchVolunteerPerson() {
    var forename = $('#forename').val();
    var surname = $('#surname').val();

    if(!forename || !surname) {
        return;
    }

    var $personId = $("input[name='personId']");

    var existingPersonName = $personId.data("full-name");
    if (existingPersonName == forename + " " + surname) {
        // no change in value
        return;
    }
    var existingPersonId = $personId.val();
    findVolunteerPerson(forename, surname, existingPersonId, existingPersonName);

    $personId.data("full-name", forename + " " + surname);
}

function findVolunteerPerson(forename, surname, existingPersonId, existingPersonName) {
    $.ajax({
        url: '../persons/search',
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

            var template = $("#person-search-form").html();
            var html = Mustache.to_html(template, data);

            $("#volunteer-person-modal .modal-body").html(html)
            var modalElement = $("#volunteer-person-modal")

            modalElement.modal('show')

            // if they select the person id, set it to the hidden volunteer person id field
            $("a.matched-person").on("click", function(event){
                populateVolunteerFromPerson($(this).data("person-id"));
                modalElement.modal('hide')
            });
        }
    });
}

function populateVolunteerFromPerson(personId) {
    $personId = $("input[name='personId']");
    if (!personId) {
        $personId.val(personId);
        return;
    }
    var existingPersonId = $personId.val();

    if (existingPersonId == personId) {
        return;
    }

    // new person - pull the person data and populate the form.
    $.ajax({
        url: '../persons/' + personId,
        contentType: "application/json",
        dataType: 'json',
        success: function(data) {
            $("input[name='birthDate']").val(data.birthDate);
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
    $personId.val(personId);
}
