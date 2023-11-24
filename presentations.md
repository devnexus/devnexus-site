---
layout: info-fluid
permalink: /presentations/index.html
---

{% assign workshop_events = site.events | where: "format", "workshop" | sort: "name" %}
<h1 class="featured-header"><span>— WORKSHOPS —</span></h1>
<div class="row">
     {% for session in workshop_events%}
         {% include presentation_thumb.html details=session track="keynote" %}
     {% endfor %}
</div>

{% assign sessions_by_tracks = site.events | where:"format", "session" | group_by: "track" | sort: "name" %}
{% for track in sessions_by_tracks %}

<h1 class="featured-header"><span>— {{track.name}}—</span></h1>
<div class="row">
     {% for session in track.items %}
         {% include presentation_thumb.html details=session track="keynote" %}
     {% endfor %}
</div>
{% endfor %}