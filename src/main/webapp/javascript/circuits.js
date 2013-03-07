$(document).ready(function() {
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


