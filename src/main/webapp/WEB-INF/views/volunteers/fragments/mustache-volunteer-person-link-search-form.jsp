<!-- mustache template used to display the emergency contact or spouse person selection form -->
<script id="volunteer-person-link-search-form" type="text/html" charset="utf-8">
    {{#existingPersonId}}
        <p>The name is already linked to {{existingPersonName}}</p>
        <p><a href="#" class="matched-person" data-person-id="{{existingPersonId}}">Leave linked to {{existingPersonName}} (same as ignore)</a></p>
        <p><a href="#" class="matched-person" data-person-id="">Unlink {{existingPersonName}} (create a new person)</a></p>
    {{/existingPersonId}}
    {{#matchedPersons}}
        <p>Link to an existing person:</p>
        {{#results}}
            <p><a href="#" class="matched-person" data-person-id="{{personId}}">{{forename}} {{surname}}{{#congregationName}}, {{congregationName}}{{/congregationName}}</a></p>
        {{/results}}
    {{/matchedPersons}}
</script>
<script id="linked-person-text" type="text/html" charset="utf-8">
    {{forename}} {{surname}}{{#congregationName}}, {{congregationName}}{{/congregationName}}
</script>
