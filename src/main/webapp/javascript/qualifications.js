/*
 * The following should be set to the URL for the
 * application
 */
var APP ='ROMS';
var editingRow;
var editingMode = false;
var qualification=new Array('id', 'name', 'description');

APPURL = "/" + APP + "/qualifications";

var EDIT = APPURL + "/edit/";
var SAVE = APPURL + "/save/";
var DELETE = APPURL + "/delete/";
var NEW = APPURL + "/new/";

var SAVELINK = 
"<ul><a id='save' class='save' href=''>" +
"<img src='/" + APP + "/images/checkmark.ico' width='20' height='20' alt='Save'>Save " +
"</a>" + 
"<a id='cancel' class='cancel' href=''>" +
"<img src='/" + APP + "/images/undelete.ico' width='20' height='20' alt='Cancel'>Cancel " +
"</a></ul>";


var EDITLINK =
"<ul><a id='edit' class='edit' href=''>" +
"<img src='/" + APP + "/images/pencil.ico' width='20' height='20' alt='Edit'>Edit " +
"</a>" +
"<a id='delete' class='delete' href=''>" +
"<img src='/" + APP + "/images/delete.ico' width='20' height='20' alt='Delete'>Delete" +
"</a></ul>";



$(document).ready(function() {
    $("#qualification-list").validate({
        rules: {
            name: {
                minlength: 2,
                required: true
            },
            description: {
                minlength: 2,
                required: true
            }
        },
        errorPlacement: roms.common.validatorErrorPlacement
    });
    
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

$('#qualification-list').on('click','a.edit', function (e) {
    e.preventDefault();
    var oTable = $('#qualification-list').dataTable();
    var nRow = $(this).parents('tr')[0];
    if ( editingMode == true ){
        restoreRow(oTable, editingRow);
    }else{
        editingMode=true;
    }
    editingRow=nRow;
    // nData will hold the values from the table row
    var nData = oTable.fnGetData(nRow);
    // jqTds will hold the HTML between <TD></TD>
    var jqTds = $('>td', nRow);
    for (var index=0; index < (jqTds.length - 1); index++){
        if (index == 0){
            jqTds[index].innerHTML = '<input type="text"' +
            ' name="' + qualification[index - 1] + '"' +
            ' value="'+ nData[index] + '" readonly>';
        }else{
            jqTds[index].innerHTML = '<input type="text"' +
            ' name="' + qualification[index - 1] + '"' +
            ' value="'+ nData[index]+'">';
        }
    }
    jqTds[jqTds.length - 1].innerHTML = SAVELINK;
});
/*
 * Saves the edited row
 */

$('#qualification-list').on('click', "a.save", function (e){
    e.preventDefault();
    var oTable = $('#qualification-list').dataTable();
    var nRow = $(this).parents('tr')[0];
    saveRow(oTable, nRow);
});

/*
 * Cancels the edit mode
 */
$('#qualification-list').on('click','a.cancel', function (e){
    e.preventDefault();
    var oTable = $('#qualification-list').dataTable();
    restoreRow(oTable, editingRow);
});

/*
 * Make AJAX calls to delete
 */
$('#qualification-list').on('click','a.delete', function (e) {
    e.preventDefault();
    var confirmDelete = confirm("Are you sure you want to delete this record?");
    if(confirmDelete == true){
        var oTable = $('#qualification-list').dataTable();
        var nRow = $(this).parents('tr')[0];
        var aData = oTable.fnGetData(nRow);
        var url = DELETE + aData[0];
        var settings = {
            url: url,
            type: "GET",
            complete: function(xhr, textStatus){
                //alert('Return status:' + xhr.status + ";" + textStatus);
                assessAjaxDelete(xhr, textStatus, oTable, nRow);
            }
        };
        $.ajax(settings);
    }
} );
/*
 * This simply saves the new data using AJAX 
 */
function saveRow ( oTable, nRow )
{
    //aData holds the input changed data
    var aData = $('input', nRow);
    var toSaveQualification = {
        qualificationId: aData[0].value,
        name: aData[1].value,
        description: aData[2].value
    };
    var url = SAVE;
    var settings = {
        url: url,
        type: "POST",
        data: toSaveQualification,
        complete: function(xhr, textStatus){
            //alert('Return status:' + xhr.status + ";" + textStatus);
            assessAjaxSave(xhr, textStatus, oTable, nRow);
        }
    };
    $.ajax(settings);
}

function assessAjaxSave(xhr, textStatus, oTable, nRow){
    if(xhr.status == 200){
        saveQualificationResult(oTable, nRow);
    }else if(xhr.status == 500){
        failedToUpdate(oTable, nRow);
    }else{
        alert('Could not determine the result of update. Please reload the page.' +
            'Status:' + xhr.status);
    }
}
function assessAjaxDelete(xhr, textStatus, oTable, nRow){
    if(xhr.status == 200){
        deleteFromTableResult(oTable, nRow);
    }else if(xhr.status == 500){
        failedToUpdate(oTable, nRow);
    }else{
        alert('Could not determine the result of update. Please reload the page.' +
            'Status:' + xhr.status);
    }
}
/*
 * This simply restores the row to original
 */
function restoreRow ( oTable, nRow )
{
    var aData = oTable.fnGetData(nRow);
    var jqTds = $('>td', nRow);
    for ( var i=0, iLen=jqTds.length ; i<iLen ; i++ ) {
        oTable.fnUpdate( aData[i], nRow, i, false );
    }
    //oTable.fnDraw(); We don't want to redraw the table
    editingRow=null;
    editingMode=false;
}


function deleteFromTableResult(oTable, nRow){
    alert('The qualification has been deleted from the database.');
    oTable.fnDeleteRow(nRow);
}

function failedToUpdate(oTable, nRow){
    alert('The database could not be updated.');
    restoreRow(oTable, nRow);
}

function saveQualificationResult(oTable, nRow){
    alert('Qualification has been saved.');
    var jqInputs = $('input', nRow);
    var rowLength=jqInputs.length;
    for (var index=0; index < rowLength; index++){
        oTable.fnUpdate( jqInputs[index].value, nRow, index, false );
    }
    oTable.fnUpdate( EDITLINK, nRow, (rowLength), false );
    //oTable.fnDraw(); We don't want to redraw the table
    editRow=null;
    editMode=false;
}
