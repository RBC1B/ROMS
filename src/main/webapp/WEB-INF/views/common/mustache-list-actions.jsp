<script id="list-action" type="text/html" charset="utf-8">
    <ul class="list-inline">
        {{#uri}}<li><a class="btn btn-success" href="{{uriBase}}{{uri}}">View</a></li>{{/uri}}
        {{#editUri}}<li><a href="{{uriBase}}{{editUri}}">Edit</a></li>{{/editUri}}
        {{#deleteUri}}<li><a href="{{uriBase}}{{deleteUri}}">Delete</a></li>{{/deleteUri}}
    </ul>
</script>
<script id="read-only-list-action" type="text/html" charset="utf-8">
    <ul class="list-inline">
        {{#uri}}<li><a class="btn btn-success" href="{{uriBase}}{{uri}}">View</a></li>{{/uri}}
    </ul>
</script>
