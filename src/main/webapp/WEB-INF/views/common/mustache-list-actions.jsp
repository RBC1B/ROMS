<script id="list-action" type="text/html" charset="utf-8">
    <ul class="inline list-actions">
        {{#uri}}<li><a class="btn btn-success" href="{{uriBase}}{{uri}}">View</a></li>{{/uri}}
        {{#editUri}}<li><a class="list-action" href="{{uriBase}}{{editUri}}">Edit</a></li>{{/editUri}}
        {{#deleteUri}}<li><a class="list-action" href="{{uriBase}}{{deleteUri}}">Delete</a></li>{{/deleteUri}}
    </ul>
</script>
<script id="read-only-list-action" type="text/html" charset="utf-8">
    <ul class="inline list-actions">
        {{#uri}}<li><a class="btn btn-success" href="{{uriBase}}{{uri}}">View</a></li>{{/uri}}
    </ul>
</script>
