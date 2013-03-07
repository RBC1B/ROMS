$(document).ready(function() {
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
