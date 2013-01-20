$(document).ready(function() {
    $('#person\\.surname').blur(function() {

        var forename = $('#person\\.forename').val();
        var surname = $('#person\\.surname').val();

        if(!forename || !surname) {
            return;
        }

        findPerson(forename, surname);
    });
});

function findPerson(forename, surname) {
    $.ajax({
        url: '../persons/search',
        contentType: "application/json",
        dataType: 'json',
        data:  {forename: forename, surname: surname},
        success: function(data) {
            // if we have no matches, no need to show selection form
            if (!data.persons) {
                return;
            }

            var template = $("#person-search-form").html();
            var html = Mustache.to_html(template, data);
            
            $("#volunteer-person-modal .modal-body").html(html)
            $("#volunteer-person-modal").modal('show')

        }
    });
}
