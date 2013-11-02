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
                'aTargets': [ 7 ]
            }
            ]
        }
    );

    $(".a-project-status, .a-project-count, .a-project-assignment, .a-event-created-by").tooltip();

    // details view
    $("#project-stages").sortable({
        items: "> div",
        update: function( event, ui ) {
            var stageIds = $(this).sortable('toArray').toString();
            $.ajax({
                url: roms.common.relativePath + '/projects/' + $(this).data("project-id") + "/stage-order",
                type: 'PUT',
                data:  {
                    stageIdValues: stageIds
                },
                error: function(xhr, ajaxOptions, thrownError) {
                    alert(xhr.status + ": " + thrownError);
                }
            });
        }
    });

    // toggle the accordion and the accordion image
    $('.a-accordian-control').click(function() {
       var target = $(this).data("target");
       var $accordion = $(target);
       if ($accordion.hasClass("in")) {
           $accordion.collapse('hide');
           $("span", $(this)).addClass("glyphicon-plus");
           $("span", $(this)).removeClass("glyphicon-minus");
       } else {
           $accordion.collapse('show');
           $("span", $(this)).removeClass("glyphicon-plus");
           $("span", $(this)).addClass("glyphicon-minus");
       }
    });
    
    /*
    $('.collapse').on('hidden.bs.collapse', function () {
        if (!$(this).hasClass("in")) {
            var control = $(this).closest(".a-accordian-wrapper").children(".a-accordian-control").find("span.glyphicon-minus");
            control.addClass("glyphicon-plus");
            control.removeClass("glyphicon-minus");
        }
    })
    
    $('.collapse').on('show.bs.collapse', function () {
        if ($(this).hasClass("in")) {
            var control = $(this).closest(".a-accordian-wrapper").children(".a-accordian-control").find("span.glyphicon-plus");
            control.removeClass("glyphicon-minus");
            control.addClass("glyphicon-minus");
        }
    })
    */
});
