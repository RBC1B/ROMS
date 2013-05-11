$(document).ready(function() {
    roms.common.datatables(
        $('#project-list'),
        {
            "iDisplayLength": 10,
            "aoColumnDefs": [
            {
                'bSortable': false,
                'aTargets': [ 8 ]
            }
            ]
        }
    );

    $(".a-project-status").tooltip();

});