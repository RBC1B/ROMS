$(document).ready(function() {

    // Event when the surname has lost focus
    $('#surname').blur(function() {
        matchPerson($('#forename').val(), $('#surname').val(), $('#personId'));
    });
    
    // if the circuit-overseer link is unlinked then reset the form
    $('#circuit-overseer-linked button.close').click(function() {
        populateCircuitOverseer(null, $('#personId')); 
    });
    
    /**
     * Function to match the Circuit Overseer with an existing person.
     * @param forename the forename from the bean
     * @param surname the surname from the bean
     * @param $personId jquery reference to the personId. Although shown on the form
     * it is read-only and can't be changed.
     */
    function matchPerson(forename, surname, $personId){
        // test if either forename or surname aren't set
        if (!forename || !surname){
            return; // can't do the person match
        }
        
        var existingPersonName = $personId.data("full-name");
        // could return the value given key "full-name" as done before
        if (existingPersonName == forename + " " + surname){
            return;
        }
        existingPersonName = forename + " " + surname;
        findPerson(forename, surname, $personId, existingPersonName);
        
        $personId.data("full-name", forename + " " + surname);
    }
    /**
     * Look up the person using the AJAX jQuery function. Show modal 
     * if there is a match with the forename and surname.
     */
    function findPerson(forename, surname, $personId, existingPersonName){
        var existingPersonId = $personId.val();
        $.ajax({
            url: roms.common.relativePath + '/persons/search/',
            contentType: "application/json",
            dataType: 'json',
            // parameters name-value pairs to be passed
            data: {
                forename: forename,
                surname: surname,
                checkVolunteer: false
            },
            success: function(data) {
                // no response and no person id
                if (!data.results && !existingPersonId){
                    return;
                }
                
                data.existingPersonId = existingPersonId;
                data.existingPersonName = existingPersonName;

                if (data.results) {
                    data.matchedPersons = true;
                }
                
                // Now to use mustache templating technique
                var tpl = $("#circuit-overseer-link-form").html();
                var html = Mustache.to_html(tpl, data);
                // set modal body using class modal-body
                $("#circuit-overseer-modal .modal-body").html(html)
                var modalElement = $("#circuit-overseer-modal")
                
                modalElement.modal('show');
                
                // if select person id, set it to circuit overseer person id field.
                $("a.matched-person").on("click", function(event){
                    populateCircuitOverseer($(this).data("person-id"), $personId);
                    modalElement.modal('hide')
                });
            }
        });
    }
    function populateCircuitOverseer(selectedPersonId, $personId){
        // In unlink <a> link, data-person-id="", therefore test for this
        if (selectedPersonId){
            $('#circuit-overseer-linked').show('fast');
            // now disable all the input tags within the additional-fields div
            $('#circuit-overseer-additional-fields input').prop("disabled", true);
            // hide the circuit-overseer person additional details
            $('#circuit-overseer-additional-fields').hide('fast');
            // set personId to selectedPersonId
            $('#personId').val(selectedPersonId);
            $('#edit-circuit-overseer-person').show('fast');
        } else {
            $('#circuit-overseer-linked').hide('fast');
            $('#personId').val('')
            $('#circuit-overseer-additional-fields').show('fast');
            $('#edit-circuit-overseer-person').hide('fast');
            $('#circuit-overseer-additional-fields input').prop("disabled", false);
        }
    }
    
    // need a custom validation rule for validating phone numbers
    $.validator.addMethod("phoneNos",
            function(value, element) {
                return /^\d+$/.test(value.replace(/[()\s+-]/g,''));
            },
            "Please enter numbers or spaces only"
    );
        
    $("#circuit").validate({
        rules: {
            name: {
                required: true,
                minlength: 2
            },
            forename: {
                required: true,
                minlength: 2
            },
            surname: {
                required: true,
                minlength: 2
            },
            email: {
                required: true,
                email: true
            },
            street: {
                required: true,
                minlength: 3
            },
            town: {
                required: true,
                minlength: 2
            },
            postcode: {
                required: true,
                minlength: 4
            },
            telephone: {
                required: true,
                minlength: 11,
                phoneNos: true
            },
            mobile: {
                required: true,
                minlength: 11,
                phoneNos: true
            }
        },
        errorPlacement: roms.common.validatorErrorPlacement
    });

    roms.common.datatables(
            $('#circuit-list'),
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

});


