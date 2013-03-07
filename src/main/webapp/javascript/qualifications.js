$(document).ready(function() {
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
