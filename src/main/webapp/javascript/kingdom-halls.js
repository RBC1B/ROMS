var kingdomHallColumnValues = new Array();
$(document).ready(function() {
    var kingdomHallTable = $('#kingdom-hall-list').dataTable({
        "sPaginationType": "full_numbers",
        "iDisplayLength": 50,
        "aoColumnDefs": [
            { 'bSortable': false, 'aTargets': [ 3 ] }
        ]
    });

    $("tfoot input").keyup(function () {
		kingdomHallTable.fnFilter( this.value, $("tfoot input").index(this) );
	});

    $("tfoot input").each( function (i) {
		kingdomHallColumnValues[i] = this.value;
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
			this.value = kingdomHallColumnValues[$("tfoot input").index(this)];
		}
	});

} );


