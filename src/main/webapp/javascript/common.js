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
