---
layout: info-fluid
permalink: /workshops/index.html
---

{% assign workshop_events = site.events | where: "format", "workshop" | sort: "name" %}
<h1>Full Day Workshops</h1>
{% for session in workshop_events %}

<h2 class="featured-header"><a href="/presentations/{{session.slug}}">{{session.title}}</a></h2>
<div class="row">
    {% for speaker in session.speakers %}
          {% include workshop_speaker.html data = speaker %}
    {% endfor %}
</div>
{% endfor %}