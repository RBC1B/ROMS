<!-- mustache template used to display the linked person selection form -->
<script id="person-link-search-form" type="text/html" charset="utf-8">
    {{#existingPersonId}}
        <p>The name is already linked to {{existingPersonName}}</p>
        <p><a href="#" class="matched-person" data-person-id="{{existingPersonId}}">Leave linked to {{existingPersonName}} (same as ignore)</a></p>
        <p><a href="#" class="matched-person" data-person-id="">Unlink {{existingPersonName}} (create a new person)</a></p>
    {{/existingPersonId}}
    {{#matchedPersons}}
        <p>Link to an existing person:</p>
        {{#results}}
            {{#user}}
                <p>Person already an Edifice user: {{forename}} {{surname}}{{#congregationName}}, {{congregationName}}{{/congregationName}}, {{userName}}{{#volunteer}}, Active Volunteer{{/volunteer}}{{^volunteer}}, Not a Volunteer {{/volunteer}}</p>
            {{/user}}{{^user}}
                <p><a href="#" class="matched-person" data-person-id="{{personId}}">
                {{forename}} {{surname}}{{#congregationName}}, {{congregationName}}{{/congregationName}}{{#volunteer}}, Active Volunteer{{/volunteer}}{{^volunteer}}, Not a Volunteer {{/volunteer}}</a></p>
            {{/user}}
        {{/results}}
    {{/matchedPersons}}
</script>
<script id="linked-person-text" type="text/html" charset="utf-8">
    {{forename}} {{surname}}{{#congregationName}}, {{congregationName}}{{/congregationName}}
</script>
<script id="linked-person-text-no-congregation" type="text/html" charset="utf-8">
    {{forename}} {{surname}}
</script>