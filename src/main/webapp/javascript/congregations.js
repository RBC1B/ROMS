$(document).ready(function() {
    $('#congregation-list').validate({
        rules:{
            name: {
                minlength: 2,
                required: true
            }
        },
        errorPlacement: roms.common.validatorErrorPlacement
    });
    roms.common.datatables(
        $('#congregation-list'),
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
