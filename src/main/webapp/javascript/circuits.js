$(document).ready(function() {
    $("#circuit").validate({
        rules: {
            name: {
                minlength: 2,
                required: true
            },
            forename: {
                minlength: 2,
                required: true
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
                'aTargets': [ 2 ]
            }
            ]
        }
    );
        
});


