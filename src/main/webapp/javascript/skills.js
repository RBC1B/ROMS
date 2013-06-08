$(document).ready(function() {
    $('#skill-list').validate({
        rules:{
            name: {
                minlength: 2,
                required: true
            },
            department: {
                minlength: 2,
                required: true
            },
            category: {
                minlength: 2,
                required:true
            }
        },
        errorPlacement: roms.common.validatorErrorPlacement
    });
    roms.common.datatables(
        $('#skill-list'),
        {
            "iDisplayLength": 10,
            "aoColumnDefs": [
            {
                'bSortable': false,
                'aTargets': [ 4 ]
            }
            ]
        }
        );
});
