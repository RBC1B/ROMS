var circuitColumnValues = new Array();
$(document).ready(function() {
    var circuitTable = $('#circuit-list').dataTable({
        "sPaginationType": "full_numbers",
        "iDisplayLength": 50,
        "aoColumnDefs": [
        {
            'bSortable': false, 
            'aTargets': [ 2 ]
        }
        ],
        "bStateSave": true,
        "fnStateSave": function (oSettings, oData) {
            localStorage.setItem( 'DataTables_'+window.location.pathname, JSON.stringify(oData) );
        },
        "fnStateLoad": function (oSettings) {
            return JSON.parse( localStorage.getItem('DataTables_'+window.location.pathname) );
        }
    });

    $("tfoot input").keyup(function () {
        circuitTable.fnFilter( this.value, $("tfoot input").index(this) );
    });

    $("tfoot input").each( function (i) {
        circuitColumnValues[i] = this.value;
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
            this.value = circuitColumnValues[$("tfoot input").index(this)];
        }
    });

} );


