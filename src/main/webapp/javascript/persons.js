/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {

    // edit page
    $(".datepicker").datepicker({
        dateFormat: "dd/mm/yy",
        changeYear: true
    });

    $("#congregationName").typeahead({
        source: roms.common.congregationTypeAheadSource,
        minLength: 2
    });

    $("#person").validate({
        rules: {
            forename: {
                minlength: 2,
                required: true
            },
            surname: {
                minlength: 2,
                required: true
            },
            email: {
                email: true
            },
            congregationName: {
                remote: {
                    // check for an exact match. Populate the congregation id
                    url: roms.common.relativePath + "congregations/search",
                    contentType: "application/json",
                    dataType: "json",
                    data: {
                        name: function() {
                            return $("#congregationName").val();
                        }
                    },
                    dataFilter: function(rawData) {
                        var data = JSON.parse(rawData)
                        if (data.results && data.results[0].name == $("#congregationName").val()) {
                            $("#congregationId").val(data.results[0].id);
                            return true;

                        }
                        return false;
                    }
                }
            }
        },
        errorPlacement: roms.common.validatorErrorPlacement
    });

    // list
    var listActionTemplate = $("#list-action").html();

    roms.common.datatables(
        $('#person-list'),
        {
            "iDisplayLength": 20,
            "bProcessing": true,
            "bServerSide": true,
            "sAjaxSource": roms.common.relativePath + 'persons',
            "aoColumns": [
                {   "sName": "forename", "mData": "forename" },
                {   "sName": "surname", "mData": "surname" },
                {   "sName": "congregation", "mData": "congregation.name", "sDefaultContent": "" },
                {   "sName": "action", "bSortable": false,
                    "mData":
                        function ( data, type, val ) {
                            return Mustache.to_html(listActionTemplate, data);
                        }
                }
            ]
        }
        );

});

