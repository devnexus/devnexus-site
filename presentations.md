---
layout: info-fluid
permalink: /presentations/index.html
---

{% assign sessions_by_tracks = site.events | group_by: "track" | sort: "name" %}
{% for track in sessions_by_tracks %}

<h1 class="featured-header"><span>— {{track.name}}—</span></h1>
<div class="row">
     {% for session in track.items %}
         {% include presentation_thumb.html details=session track="keynote" %}
     {% endfor %}
</div>
{% endfor %}