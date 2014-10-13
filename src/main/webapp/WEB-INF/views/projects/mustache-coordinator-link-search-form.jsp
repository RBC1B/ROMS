<!-- mustache template used to display the linked person selection form -->
<script id="coordinator-link-search-form" type="text/html" charset="utf-8">
    {{#existingCoordinatorId}}
        <p>The name is already linked to {{existingCoordinatorName}}</p>
        <p><a href="#" class="matched-coordinator" data-coordinator-id="{{existingCoordinatorId}}">Leave linked to {{existingCoordinatorName}} (same as ignore)</a></p>
        <p><a href="#" class="matched-coordinator" data-coordinator-id="">Unlink {{existingCoordinatorName}} (assign another coordinator)</a></p>
    {{/existingCoordinatorId}}
    {{#matchedPersons}}
        <p>Link to an existing person:</p>
        {{#results}}
            <p><a href="#" class="matched-coordinator" data-coordinator-id="{{personId}}">{{forename}} {{surname}}{{#congregationName}}, {{congregationName}}{{/congregationName}}</a></p>
        {{/results}}
    {{/matchedPersons}}
</script>
<script id="linked-coordinator-text" type="text/html" charset="utf-8">
    {{forename}} {{surname}}{{#congregationName}}, {{congregationName}}{{/congregationName}}
</script>
<script id="linked-coordinator-text-no-congregation" type="text/html" charset="utf-8">
    {{forename}} {{surname}}
</script>