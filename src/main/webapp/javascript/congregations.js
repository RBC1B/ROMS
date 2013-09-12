/*
 * The MIT License
 *
 * Copyright 2013 RBC1B.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
$(document).ready(function() {
    // list
    roms.common.datatables(
        $('#congregation-list'),
        {
            "iDisplayLength": 10,
            "aoColumnDefs": [
            {
                'bSortable': false,
                'aTargets': [ 5 ]
            }
            ]
        }
    );
    
    // show
    var listActionTemplate = $("#list-action").html();
    var congregationId = $("#congregation-member-congregation").data("congregationId");
    roms.common.datatables(
	    $('#congregation-member-list'),
	    {
	            "iDisplayLength": 20,
	            "bProcessing": true,
	            "bServerSide": true,
	            "sAjaxSource": roms.common.relativePath + '/volunteers',
	            "fnServerParams": function(aoData) {
	        	aoData.push({ "name": "congregationId", "value": congregationId });
	            },
	            "aoColumns": [
	                {   "sName": "ID", "mData": "id" },
	                {   "sName": "forename", "mData": "forename" },
	                {   "sName": "surname", "mData": "surname" },
	                {   "sName": "action", "bSortable": false,
	                    "mData":
	                        function ( data, type, val ) {
	                            data.uriBase = roms.common.relativePath;
	                            return Mustache.to_html(listActionTemplate, data);
	                        }
	                }
	            ]
	        }
	    );
    
    // edit
    if ($("#number").length) {
	$("#number").numeric({ negative : false, decimal: false });
    }
    
    $("#kingdomHallName").typeahead({
        source: roms.common.kingdomHallTypeAheadSource,
        minLength: 2
    });

    // we always clear the kingdom hall id on change.
    // it will be re-calculated in validation
    $("#kingdomHallName").change(function() {
        $("#kingdomHallId").val(null);
    });
    
    $('#congregationForm').validate({
        rules:{
            name: {
                minlength: 2,
                required: true
            },
            number: {
                required: true
            },
            kingdomHallName: {
                required: true,
                remote: {
                    // check for an exact match. Populate the kingdom hall id
                    url: roms.common.relativePath + "/kingdom-halls/search",
                    contentType: "application/json",
                    dataType: "json",
                    data: {
                        name: function() {
                            return $("#kingdomHallName").val();
                        }
                    },
                    dataFilter: function(rawData) {
                        var data = JSON.parse(rawData)
                        if (data.results && data.results[0].name == $("#kingdomHallName").val()) {
                            $("#kingdomHallId").val(data.results[0].id);
                            return true;

                        }
                        return false;
                    }
                }
            },
            circuitId: {
                required: true
            },
            strategy: {
                maxlength: 1000
            }
        },
        errorPlacement: roms.common.validatorErrorPlacement
    });
    
    $("#coordinatorSurname").blur(function() {
	roms.common.matchLinkedPerson(
            $("#coordinatorForename").val(),
            $("#coordinatorSurname").val(),
            $("#coordinatorPersonId"),
            populateCoordinatorFromPerson
        );
    });
    
    $("#secretarySurname").blur(function() {
	roms.common.matchLinkedPerson(
            $("#secretaryForename").val(),
            $("#secretarySurname").val(),
            $("#secretaryPersonId"),
            populateSecretaryFromPerson
        );
    });
    
    $("#coordinator-linked a").click(function() {
	populateCoordinatorFromPerson(null, null, null, $("#coordinatorPersonId"));
    });
    
    $("#secretary-linked a").click(function() {
	populateSecretaryFromPerson(null, null, null, $("#secretaryPersonId"));
    });
    
    // run the contacts display on page load
    populateCoordinatorFromPerson(
        $("#coordinatorPersonId").val(), 
	$("#coordinatorForename").val(), 
	$("#coordinatorSurname").val(), 
	$("#coordinatorPersonId"));
    populateSecretaryFromPerson(
        $("#secretaryPersonId").val(), 
        $("#secretaryForename").val(), 
        $("#secretarySurname").val(), 
        $("#secretaryPersonId"));
    
    function populateCoordinatorFromPerson(selectedPersonId, forename, surname, $personId) {
        if (selectedPersonId) {
            var template = $("#linked-person-text-no-congregation").html();
            var data = {};
            data.forename = forename;
            data.surname = surname;
            var html = Mustache.to_html(template, data);
            $("#coordinator-linked-text").html(html);
            
            $("#coordinator-linked").show("fast");
            $("#coordinator-unlinked").hide("fast");
        } else {
            $("#coordinator-linked").hide("fast");
            $("#coordinator-unlinked input").val("");
            $("#coordinator-unlinked").show("fast");
        }
        $personId.val(selectedPersonId);
    }
    
    function populateSecretaryFromPerson(selectedPersonId, forename, surname, $personId) {
        if (selectedPersonId) {
            var template = $("#linked-person-text-no-congregation").html();
            var data = {};
            data.forename = forename;
            data.surname = surname;
            var html = Mustache.to_html(template, data);
            $("#secretary-linked-text").html(html);
            
            $("#secretary-linked").show("fast");
            $("#secretary-unlinked").hide("fast");
        } else {
            $("#secretary-linked").hide("fast");
            $("#secretary-unlinked input").val("");
            $("#secretary-unlinked").show("fast");
        }
        $personId.val(selectedPersonId);
    }
    
});
