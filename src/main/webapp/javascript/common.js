/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
if (!roms) {
    var roms = {};
};

if (!roms.common) {
    roms.common = {};
};

roms.common.validatorErrorPlacement = function(error, element) {
    var $controls = element.closest(".controls");
    if ($controls.length > 0) {
        error.appendTo($controls);
    } else {
        error.appendTo(element.closest(".control-group"));
    }
};

roms.common.validatorHighlight  = function(element) {
    $(element).closest('.control-group').removeClass('success').addClass('error');
}

roms.common.validatorSuccess = function(element) {
    element.addClass('valid').closest('.control-group').removeClass('error').addClass('success');
}

roms.common.congregationTypeAheadSource = function(query, process) {
    $.ajax({
        url: roms.common.relativePath +"congregations/search",
        contentType: "application/json",
        dataType: "json",
        data:  {
            name: query
        },
        success: function(data) {
            var results = [];
            if(data.results) {
                $.each(data.results, function() {
                    results.push(this.name);
                });
            }
            return process(results);
        }
    });
}

// store the relative path, used for all the ajax calls
$(document).ready(function() {
    roms.common.relativePath = $("#relative-path").data("relative-path");
});
