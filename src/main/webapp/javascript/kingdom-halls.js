$(document).ready(function() {
    roms.common.datatables(
        $('#kingdom-hall-list'),
        {
            "iDisplayLength": 10,
            "aoColumnDefs": [
            {
                'bSortable': false,
                'aTargets': [ 3 ]
            }
            ]
        }
    );
});
