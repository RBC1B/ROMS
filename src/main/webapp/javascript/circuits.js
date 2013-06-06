$(document).ready(function() {

    // Event when the surname has lost focus
    $('#surname').blur(function() {
        matchVolunteerPerson($('#forename').val(), $('#surname').val(), $('#personId'));
    });
    
    /**
     * Function to match the Circuit Overseer with an existing person or volunteer.
     * @param forename the forename from the bean
     * @param surname the surname from the bean
     * @param $personId jquery reference to the personId. Although shown on the form
     * it is read-only and can't be changed.
     */
    function matchVolunteerPerson(forename, surname, $personId){
        // test if either forename or surname aren't set
        if (!forename || !surname){
            return; // can't do the person match
        }
        
        var existingPersonName = $personId.data("full-name");
        // could return the value given key "full-name" as done before
        if (existingPersonName == forename + " " + surname){
            return;
        }
        this.findVolunteerPerson(forename, surname, $personId, existingPersonName);
        
        $personId.data("full-name", forename + " " + surname);
    }
    /**
     * Look up the person/volunteer using the AJAX jQuery function. Show modal 
     * if there is a match with the forename and surname.
     */
    
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
                digits: true,
                minlength: 11
            },
            mobile: {
                required: true,
                digits: true,
                minlength: 11
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


