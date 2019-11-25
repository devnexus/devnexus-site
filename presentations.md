---
layout: info-fluid
permalink: /presentations/index.html
tracks:
#- keynote
- workshop
#- unobtanium
- core-java
- java-platform
- open-java
- archictecture
- tools
- frameworks
- jvm-languages
- javascript
- security
- web
- practices-other
- cloud-technology
- cloud-infrastructure
- agile
---
{% comment %}
{% assign adminevents = site.data.schedule[1].events | concat: site.data.schedule[2].events  %}
{% assign keynotes = adminevents | where: "room", "Keynote Room" %}
{% if keynotes %}
<h1 class="featured-header"><span>— Keynotes —</span></h1>
<div class="row">
{% for keyevent in keynotes %}
{% assign eventdetail = site.events | where: "slug", keyevent.id | first %}
 {% include presentation_thumb.html details=eventdetail track="keynote" %}
{% endfor %}
</div>
{% endif %}
{% endcomment %}

{% for track in page.tracks %}
{% assign track_data = site.data.tracks[track] %}
{% assign events = site.events | where:"track", track_data.trackkey | sort: 'title' %}
{% if events %}
<h1 class="featured-header"><span>— {{ track_data.title | escape }} —</span></h1>
<div class="row">
{% for event in events %}
 {% include presentation_thumb.html details=event track=track %}
{% endfor %}
</div>
{% endif %}
{% endfor %}
