var circuitColumnValues = new Array();
$(document).ready(function() {
    var circuitTable = $('#circuit-list').dataTable({
        "sPaginationType": "full_numbers",
        "iDisplayLength": 50,
        "aoColumnDefs": [
            { 'bSortable': false, 'aTargets': [ 5 ] }
        ]
    });

    $("tfoot input").keyup(function () {
		/* Filter on the column (the index) of this element */
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


