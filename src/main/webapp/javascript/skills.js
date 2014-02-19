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
    
    // edit
    $('#skillForm').validate({
        rules:{
            name: {
                minlength: 2,
                required: true,
                remote: uniqueSkillNameValidation($("#name"), $("#skillId"))
            },
            department: {
                required: true
            },
            category: {
                required:true
            },
            description: {
                maxlength: 250
            }
        },
        messages: {
            name: {
                remote: "This skill name is already used"
            }
        },
        errorPlacement: roms.common.validatorErrorPlacement
    });
    
    function uniqueSkillNameValidation($nameInput, $idInput) {
        return {
            // check for an exact match. Populate the user id
            url: roms.common.relativePath + "/skills/search",
            contentType: "application/json",
            dataType: "json",
            data: {
                name: function() {
                    return $nameInput.val();
                }
            },
            dataFilter: function(rawData) {
                var data = JSON.parse(rawData)
                // if we have a match, it must have the same id
                if (data && data.length > 0 && data[0].name.toLowerCase() == $nameInput.val().toLowerCase() &&
                        $idInput.val() != data[0].skillId) {
                    return false;
                } 
                // no match
                return true;
            }
        };
    }
    
    
    // details
    var listVolunteersActionTemplate = $("#read-only-list-action").html();
    var skillId = $("#skills-volunteer-list").data("skillId");
    roms.common.datatables(
        $('#skills-volunteer-list'),
        {
            "iDisplayLength": 10,
            "bProcessing": true,
            "bServerSide": true,
            "sAjaxSource": roms.common.relativePath + '/volunteers',
            "fnServerParams": function(aoData) {
                aoData.push({ "name": "skillId", "value": skillId });
            },
            "aoColumns": [
                {   "sName": "ID", "mData": "id" },
                {   "sName": "forename", "mData": "forename" },
                {   "sName": "surname", "mData": "surname" },
                {   "sName": "congregation", "mData": "congregation.name", "sDefaultContent": "" },
                {   "sName": "action", "bSortable": false,
                    "mData":
                        function ( data, type, val ) {
                            data.uriBase = roms.common.relativePath;
                            return Mustache.to_html(listVolunteersActionTemplate, data);
                        }
                }
            ]
        }
    );

    
    // list
    roms.common.datatables(
        $('#skill-list'),
        {
            "iDisplayLength": 10,
            "aoColumnDefs": [
            {
                'bSortable': false,
                'aTargets': [ 4 ]
            }
            ]
        }
    );
});
