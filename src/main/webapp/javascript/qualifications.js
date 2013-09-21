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

    // list
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

    // show
    var listActionTemplate = $("#list-action").html();
    var qualificationId = $("#qualification-volunteer-qualification").data("qualificationId");
    roms.common.datatables(
        $('#qualification-volunteer-list'),
        {
                "iDisplayLength": 20,
                "bProcessing": true,
                "bServerSide": true,
                "sAjaxSource": roms.common.relativePath + '/volunteers',
                "fnServerParams": function(aoData) {
                aoData.push({ "name": "qualificationId", "value": qualificationId });
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

    
});
