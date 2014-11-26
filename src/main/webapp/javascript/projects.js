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
    // list view
    roms.common.datatables(
            $('#project-list'),
            {
                "iDisplayLength": 10,
                "aoColumnDefs": [
                    {
                        'bSortable': false,
                        'aTargets': [7]
                    }
                ]
            }
    );

    // Gets the person after the names have been entered
    $("#surname").blur(function() {
        findCoordinatorByName(
                $("#forename").val(),
                $("#surname").val(),
                $("#coordinatorId")
                );
    });

    function findCoordinatorByName(forename, surname, $coordinatorId) {
        // The names are still blank
        if (!forename || !surname) {
            return;
        }
        // find the volunteer
        findCoordinator(forename, surname, $coordinatorId);
    }

    function findCoordinator(forename, surname, $coordinatorId) {
        var existingCoordinatorId = $coordinatorId.val();

        $.ajax({
            url: roms.common.relativePath + '/persons/search',
            contentType: "application/json",
            dataType: "json",
            data: {
                forename: forename,
                surname: surname,
                checkVolunteer: true
            },
            success: function(data) {
                // no match, and no person linked
                if ((!data || data.length === 0) && !existingCoordinatorId) {
                    return;
                }

                var matchedPersons = new Array();
                if (data) {
                    for (var i = 0; i < data.length; i++) {
                        var result = data[i];
                        if (result.personId !== existingCoordinatorId) {
                            matchedPersons.push(result);
                        } else {
                            data.existingCoordinatorName = result.forename + " " + result.surname;
                        }
                    }
                }


                data.existingCoordinatorId = existingCoordinatorId;

                if (matchedPersons.length > 0) {
                    data.matchedPersons = true;
                    data.results = matchedPersons;
                }
                var template = $("#coordinator-link-search-form").html();
                var html = Mustache.to_html(template, data);

                $("#person-link-modal .modal-body").html(html);
                var modalElement = $("#person-link-modal");

                modalElement.modal('show');

                $("a.matched-coordinator").on("click", function(event) {
                    populateCoordinatorFromPerson($(this).data("coordinator-id"), $coordinatorId);
                    modalElement.modal('hide');
                });
            }
        });
    }

    function populateCoordinatorFromPerson(selectedCoordinatorId, $coordinatorId) {
        if (selectedCoordinatorId) {
            $("#coordinator-linked").show("fast");
        } else {
            alert("hiding modal");
            $("#coordinator-linked").hide("fast");
        }
        $coordinatorId.val(selectedCoordinatorId);
    }

    // project create/edit
    $("#kingdomHallName").typeahead({
        remote: roms.common.relativePath + '/kingdom-halls/search?name=%QUERY',
        valueKey: 'name'
    });

    // we always clear the kingdom hall id on change.
    // it will be re-calculated in validation
    $("#kingdomHallName").change(function() {
        $("#kingdomHallId").val(null);
    });

    $(".datepicker").datepicker({
        dateFormat: "dd/mm/yy",
        minDate: "-1y",
        maxDate: "+0d"
    });

    $("#projectForm").validate({
        rules: {
            name: {
                required: true,
                remote: {
                    // make sure this is not a duplicate name
                    url: roms.common.relativePath + "/projects/search",
                    contentType: "application/json",
                    dataType: "json",
                    cache: false,
                    data: {
                        name: function() {
                            return $("#name").val();
                        }
                    },
                    dataFilter: function(rawData) {
                        //We only check if creating a new project..... 
                        var action = $("#create-project").attr("val");
                        if (action == 1) {
                            var data = JSON.parse(rawData)
                            if (data && data.length > 0) {
                                return false;
                            } else {
                                return true;
                            }
                        } else {
                            return true;
                        }
                    }
                }
            },
            kingdomHallName: {
                remote: roms.common.validation.kingdomHall($("#kingdomHallName"), $("#kingdomHallId"))
            },
            requestDate: {
                required: true
            },
        },
        messages: {
            name: {
                remote: "The project name must be unique"
            },
            kingdomHallName: {
                remote: "Please provide the name of an existing kingdom hall"
            },
        },
        submitHandler: function(form) {
            form.submit();
        },
        errorPlacement: roms.common.validatorErrorPlacement
    });

    // Project Work session/availabiliity request page
    function addColumnHeaders(volunteerdata) {
        var columnHeaders = {
            "projectDepartmentSessionId": "Work Session Id",
            "personId": "RBC ID",
            "personName": "Name",
            "address": "Address",
            "invited": "Invited",
            "emailSent": "Notified",
            "personResponded": "Acknowledged",
            "overseerConfirmed": "Dates Confirmed"
        };
        var columns = [];
        var thead$ = $('<thead/>');
        var headerTr$ = $('<tr/>');
        for (var i = 0; i < volunteerdata.length; i++) {
            var row = volunteerdata[i];
            for (var key in row) {
                if ($.inArray(key, columns) === -1) {
                    columns.push(key);
                    headerTr$.append($('<th/>').html(columnHeaders[key]));
                }
            }
        }
        thead$.append(headerTr$);
        $('#volunteer-availability').append(thead$);
        return columns;
    }

    function buildTable(volunteerdata) {
        if (volunteerdata.length > 0) {
            removeTable();
            addNewTable();
            var columns = addColumnHeaders(volunteerdata);
            var tbody$ = $('<tbody>');
            $('#volunteer-availability').append(tbody$);
            for (var i = 0; i < volunteerdata.length; i++) {
                var row$ = $('<tr/>');
                for (var c = 0; c < columns.length; c++) {
                    var cellvalue = volunteerdata[i][columns[c]];
                    if (cellvalue === null) {
                        cellvalue = "";
                    }
                    row$.append($('<td/>').html(cellvalue));
                }
                $('#volunteer-availability').append(row$);
            }
            roms.common.datatables($('#volunteer-availability'), {"iDisplayLength": 10});
            addRowEventHandler();
        }
    }

    function addRowEventHandler() {
        var table = document.getElementById("volunteer-availability");
        var rows = table.getElementsByTagName("tr");
        for (i = 0; i < rows.length; i++) {
            var thisRow = table.rows[i];
            var createOnClickHandler =
                    function(row)
                    {
                        return function() {
                            var cell = row.getElementsByTagName("td")[0];
                            var projectworksessionId = cell.innerHTML;
                            cell = row.getElementsByTagName("td")[1];
                            var personId = cell.innerHTML;
                            cell = row.getElementsByTagName("td")[4];
                            var invited = cell.innerHTML;
                            updateVolunteerAvailability(personId, projectworksessionId, invited);
                            if(invited === "true"){
                                cell.innerHTML=false;
                            }else{
                                cell.innerHTML=true;
                            }
                        };
                    };
            thisRow.onclick = createOnClickHandler(thisRow);
        }
    }

    function updateVolunteerAvailability(personId, departmentSessionId, invited) {
        if (invited === "true") {
            $.ajax({
                url: roms.common.relativePath + '/projects/' + departmentSessionId + "/" + personId + "/availability-delete",
                type: "DELETE",
                cache: false,
                success: function() {
                }
            });
        } else {
            $.ajax({
                url: roms.common.relativePath + '/projects/' + departmentSessionId + "/" + personId + "/availability-add",
                type: "POST",
                cache: false,
                success: function() {
                }
            });
        }
    }


    function getJsonData(sessionId) {
        $.ajax({
            url: roms.common.relativePath + '/projects/' + sessionId + '/department-session',
            contentType: "application/json",
            dataType: "json",
            data: {
                projectDepartmentSessionId: sessionId
            },
            success: function(data) {
                if (!data || data.length === 0) {
                    return;
                } else {
                    buildTable(data);
                }
            }
        });
    }

    function removeTable() {
        var table = document.getElementById("volunteer-availability");
        if (table !== null) {
            var parent = document.getElementById("volunteer-availability").parentNode;
            parent.removeChild(table);
        }
    }

    function addNewTable() {
        $('#table-location').html('<table class="table table-bordered table-condensed table-striped table-hover" cellspacing="0" id="volunteer-availability" width="90%"></table>');
    }

    $("#work-sessions").change(function() {
        var workSession = $("#work-sessions option:selected");
        if (workSession.val() !== "None") {
            var sessionTokens = workSession.val().split(" - ");
            var sessionId = sessionTokens[1];
            if (sessionId !== null) {
                getJsonData(sessionId);
            }
        } else {
            removeTable();
        }
    });
});
