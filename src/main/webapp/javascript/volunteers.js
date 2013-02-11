$(document).ready(function() {
    $('#surname').blur(function() {

        var forename = $('#forename').val();
        var surname = $('#surname').val();

        if(!forename || !surname) {
            return;
        }

        findPerson(forename, surname);
    });

    $(".datepicker").datepicker({ dateFormat: "dd/mm/yy" });

});

function findPerson(forename, surname) {
    $.ajax({
        url: '../persons/search',
        contentType: "application/json",
        dataType: 'json',
        data:  {
            forename: forename,
            surname: surname,
            checkVolunteer: true
        },
        success: function(data) {
            // if we have no matches, no need to show selection form
            if (!data.results) {
                return;
            }

            // enrich the results, splitting out the volunters from the persons.
            // if we are matchng an existing volunteer we don't want to create a new one
            var volunteers = new Array();
            var persons = new Array();
            for (var i = 0; i < data.results.length; i++) {
                var result = data.results[i];
                if (result.volunteer) {
                    volunteers.push(result);
                } else {
                    persons.push(result);
                }
            }

            if (volunteers.length > 0) {
                data.matchedVolunteers = true;
                data.volunteers = volunteers;
            }

            if (persons.length > 0) {
                data.matchedPersons = true;
                data.persons = persons;
            }

            var template = $("#person-search-form").html();
            var html = Mustache.to_html(template, data);

            $("#volunteer-person-modal .modal-body").html(html)
            var modalElement = $("#volunteer-person-modal")

            modalElement.modal('show')

            // if they select the person id, set it to the hidden volunteer person id field
            $("a.matched-person").on("click", function(event){
               $("input[name='personId']").val($(this).data("person-id"));
               modalElement.modal('hide')
            });
        }
    });
}
