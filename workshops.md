---
layout: info-fluid
permalink: /workshops/index.html
---

{% assign workshop_events = site.events | where: "format", "workshop" | sort: "name" %}
{% for session in workshop_events %}

<h1 class="featured-header"><span>— {{session.name}}—</span></h1>
<div class="row">
         {% include presentation_thumb.html details=session track="workshop" %}
</div>
{% endfor %}