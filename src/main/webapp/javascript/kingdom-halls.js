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
    roms.common.datatables(
            $('#kingdom-hall-list'),
            {
                "iDisplayLength": 10,
                "aoColumnDefs": [
                    {
                        'bSortable': false,
                        'aTargets': [4]
                    }]
            }
    );
    
    // use the congregation name as the query parameter as in the controller
    $("#titleHoldingCongregationName").typeahead({
        remote: roms.common.relativePath + '/congregations/search?name=%QUERY',
        valueKey: 'name'
    });

    // we always clear the congregation id on change.
    // it will be re-calculated in validation
    $("#titleHoldingCongregationName").change(function() {
        $("#titleHoldingCongregationId").val(null);
    });
    
    $("#kingdomHallForm").validate({
        rules: {
            name: {
                minlength: 2,
                maxlength: 50,
                required: true
            },
            street: {
                minlength: 2,
                maxlength: 50,
                required: true
            },
            town: {
                minlength: 2,
                maxlength: 50,
                required: true
            },
            county: {
                minlength: 2,
                maxlength: 50
            },
            postcode: {
                minlength: 4,
                maxlength: 10,
                required: true
            },
            titleHoldingCongregationName: {
                remote: roms.common.validation.congregation($("#titleHoldingCongregationName"),
                        $("#titleHoldingCongregationId"))
            }
        },
        messages: {
            titleHoldingCongregationName: {
                remote: "Please provide the name of an existing congregation"
            }
        },
        submitHandler :function(form) {
            form.submit();
        },
        errorPlacement: roms.common.validatorErrorPlacement
    });
});

