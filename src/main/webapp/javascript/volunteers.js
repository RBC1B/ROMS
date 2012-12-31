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
        dataType: 'json',
        data:  {forename: forename, surname: surname},
        success: function(data) {
            alert(data);
        }
    });

}