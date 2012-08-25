var qualificationColumnValues = new Array();
$(document).ready(function() {
    var qualificationTable = $('#qualification-list').dataTable({
        "sPaginationType": "full_numbers",
        "iDisplayLength": 50,
        "aoColumnDefs": [
        {
            'bSortable': false, 
            'aTargets': [ 2 ]
        }
        ]
    });

    $("tfoot input").keyup(function () {
        qualificationTable.fnFilter( this.value, $("tfoot input").index(this) );
    });

    $("tfoot input").each( function (i) {
        qualificationColumnValues[i] = this.value;
    });

    $("tfoot input").focus( function () {
        if (this.className == "search_init" ) {
            this.className = "";
            this.value = "";
        }
    });

    $("tfoot input").blur( function (i) {
        if ( this.value == "" ) {
            this.className = "search_init";
            this.value = qualificationColumnValues[$("tfoot input").index(this)];
        }
    });

} );


