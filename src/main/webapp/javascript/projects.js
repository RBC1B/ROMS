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
    var attendance = $("#project-permissions").attr("attendance");

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
                            if (invited === "true") {
                                cell.innerHTML = false;
                            } else {
                                cell.innerHTML = true;
                            }
                        };
                    };
            thisRow.onclick = createOnClickHandler(thisRow);
        }
    }

    function updateVolunteerAvailability(personId, departmentSessionId, invited) {
        if (attendance !== null && attendance !== "READ") {
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

    // Handle requests to add new project department work sessions
    var updateUrl = $("#project-department-session-form").attr("action");
    var updateMethod = $("#project-department-session-form").attr("method");

    $("input[name=fromDate]").datetimepicker({
        pickTime: false,
        minDate: '1/1/2000',
        format: "DD/MM/YYYY"
    });
    $("input[name=toDate]").datetimepicker({
        pickTime: false,
        minDate: '1/1/2000',
        format: "DD/MM/YYYY"
    });

    $("#add-new-project-session").on("click", function(event) {
        event.preventDefault();
        $("#project-department-session").modal("show");
    });

    $("#cancel-update").on("click", function(event) {
        $("#project-department-session").modal("hide")
    });

    $("#project-department-session-form").validate({
        debug: true,
        rules: {
            fromDate: {
                required: true
            }
        },
        messages: {
            fromDate: "You must enter the start date"
        },
        submitHandler: function(form) {
            $("#alert-update").hide();
            $.ajax({
                url: updateUrl,
                data: $(form).serialize(),
                type: updateMethod,
                cache: false,
                success: function(data, status, xhr) {
                    $("#alert-update").hide();
                    $("#project-department-session").modal("hide");
                    window.location.reload();
                }
            });
        }
    });

    $("#available-work-sessions").change(function() {
        var workSession = $("#available-work-sessions option:selected");
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

// Overseer inviting/confirming dates when volunteers should come
    $("#confirmation-work-sessions").change(function() {
        var workSession = $("#confirmation-work-sessions option:selected");
        if (workSession.val() !== "None") {
            var sessionTokens = workSession.val().split(" - ");
            var sessionId = sessionTokens[1];
            if (sessionId !== null) {
                removeConfirmationTable();
                addConfirmationTable();
                getAvailableVolunteers(sessionId);
            }
        } else {
            removeConfirmationTable();
        }
    });

    // Gets a list of dates used to build table headers for confirmation
    var sessionWorkDates;
    function getSessionWorkDates(sessionId) {
        $.ajax({
            url: roms.common.relativePath + "/service/projects/session/" + sessionId + '/dates',
            contenType: "application/json",
            dataType: "json",
            data: {
                sessionId: sessionId
            },
            success: function(data) {
                if (!data || data.length === 0) {
                    sessionWorkDates = "";
                } else {
                    sessionWorkDates = data;
                }
            }
        });
    }

    // Gets list of volunteers and dates that they are available as JSON data
    function getAvailableVolunteers(sessionId)
    {
        getSessionWorkDates(sessionId);
        $.ajax({
            url: roms.common.relativePath + '/service/projects/session/' + sessionId + '/volunteers',
            contentType: "application/json",
            dataType: "json",
            data: {
                projectDepartmentSessionId: sessionId
            },
            success: function(data) {
                if (!data || data.length === 0) {
                    return;
                } else {
                    buildConfirmationTable(data);
                }
            }
        });
    }

    function buildConfirmationTable(volunteers)
    {
        if (volunteers.length > 0)
        {
            generateConfirmationTableHeaders();
            var tbody$ = $('<tbody>');
            for (var volunteer = 0; volunteer < volunteers.length; volunteer++)
            {
                var row$ = $('<tr/>');
                var rbcid = volunteers[volunteer]["personId"];
                if (rbcid === null)
                    rbcid = "";
                row$.append($('<td/>').html(rbcid));
                var name = volunteers[volunteer]["name"];
                if (name === null)
                    name = "";
                row$.append($('<td/>').html(name));
                var workDates = volunteers[volunteer]["projectAttendanceDates"];
                for (var availableDate = 0; availableDate < workDates.length; availableDate++)
                {
                    var projectAttendanceId = workDates[availableDate]["projectAttendanceId"];
                    var required = workDates[availableDate]["required"];
                    var status = "";
                    if (projectAttendanceId === null)
                    {
                        status = "";
                    } else if (required === false) {
                        status = "Available";
                    } else {
                        status = "Invited";
                    }
                    var htmldata = "<div id='" + projectAttendanceId + "'projectAttendanceId='" + projectAttendanceId + "' />" + status;
                    row$.append($('<td/>').html(htmldata));
                }
                $("#volunteer-confirmation").append(row$);
            }
            var tableData = document.getElementById("volunteer-confirmation");
            var wrapper = document.createElement("div");
            wrapper.appendChild(tableData.cloneNode(true));
            roms.common.datatables($("#volunteer-confirmation"), {"iDisplayLength": 10});
            addConfirmationTableCellEventHandler();
        }
    }

    // Sets up on click event handler for each cell
    function addConfirmationTableCellEventHandler()
    {
        var table = document.getElementById("volunteer-confirmation");
        var rows = table.getElementsByTagName("tr");
        for (var rowIndex = 0; rowIndex < rows.length; rowIndex++)
        {
            var row = table.rows[rowIndex];
            var cells = row.getElementsByTagName("td");
            for (var cellIndex = 2; cellIndex < cells.length; cellIndex++)
            {
                var cell = table.rows[rowIndex].cells[cellIndex];
                cell.onclick = function()
                {
                    processConfirmationRequest(this);
                }
            }
        }
    }

    // handles AJAX requests to update attendance
    function processConfirmationRequest(html)
    {
        if (attendance !== null && attendance !== "READ")
        {
            var clickedCell = html.getElementsByTagName("div")[0];
            if (clickedCell !== null) {
                var attendanceId = clickedCell.getAttribute("projectAttendanceId");
                if (attendanceId !== null && attendanceId > 0)
                {
                    sendConfirmationRequest(attendanceId, html)
                            .done(function(r) {
                        updateCell(html);
                    })
                            .fail(function(x) {
                        alert("Failed to send update");
                    });
                }
            }
        }
    }
    function updateCell(html) {
        var attendanceId = html.getElementsByTagName("div")[0].getAttribute("projectAttendanceId");
        var newHtml = "<div id='" + attendanceId + "'projectAttendanceId='" + attendanceId + "' />";
        var cell = document.getElementById(attendanceId).parentNode;
        if (html.innerHTML.indexOf("Available") > -1)
        {
            cell.innerHTML = newHtml + "Invited";
        }
        else if (html.innerHTML.indexOf("Invited") > -1)
        {
            cell.innerHTML = newHtml + "Available";
        } else {
            alert("Could not understand...");
        }
    }

    function sendConfirmationRequest(attendanceId, html) {
        var aStatus = html.innerHTML.indexOf("Available");
        var cStatus = html.innerHTML.indexOf("Invited");
        var required;
        if (aStatus > -1)
        {
            required = true;
        } else if (cStatus > -1)
        {
            required = false;
        } else {
            //Assume that the volunteer is available...
            required = true;
        }
        var jsonData = {projectAttendanceId: attendanceId, "required": required, attended: false};
        return $.ajax({
            url: roms.common.relativePath + "/service/projects/attendance/" + attendanceId,
            contentType: "application/json",
            type: "PUT",
            datatype: "json",
            data: JSON.stringify(jsonData),
        });
    }

    // Create the confirmation table header
    function generateConfirmationTableHeaders()
    {
        var thead$ = $('<thead/>');
        var headerTr$ = $('<tr/>');
        headerTr$.append($('<th/>').html("RBC ID"));
        headerTr$.append($('<th/>').html("Name"));
        for (var dateIndex = 0; dateIndex < sessionWorkDates.length; dateIndex++)
        {
            headerTr$.append($('<th/>').html(sessionWorkDates[dateIndex]));
        }
        thead$.append(headerTr$);
        $('#volunteer-confirmation').append(thead$);
    }

    // Removes the volunteer invitation confirmation table
    function removeConfirmationTable()
    {
        var table = document.getElementById("volunteer-confirmation");
        if (table !== null)
        {
            var parent = document.getElementById("volunteer-confirmation").parentNode;
            parent.removeChild(table);
        }
    }

    // Adds the volunteer invitation confirmation table
    function addConfirmationTable()
    {
        $('#confirmation-table-location').html('<table class="table table-bordered table-condensed table-striped table-hover" cellspacing="0" id="volunteer-confirmation" width="90%"></table>');
    }
});
