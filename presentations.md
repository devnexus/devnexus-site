---
layout: info-fluid
permalink: /presentations/index.html
---

{% assign sessions_by_tracks = site.events | group_by: "track" | sort: "name" %}
{% for track in sessions_by_tracks %}
     {{track.name}}
     {% for session in track.items %}
         {{session.title}}
     {% endfor %}
{% endfor %}