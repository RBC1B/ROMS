$(document).ready(function() {
    $("#qualification-list").validate({
        rules: {
            name: {
                minlength: 2,
                required: true
            },
            description: {
                minlength: 2,
                required: true
            }
        },
        errorPlacement: roms.common.validatorErrorPlacement
    });
    
    roms.common.datatables(
        $('#qualification-list'),
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
