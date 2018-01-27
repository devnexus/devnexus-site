---
layout: info-fluid
permalink: /presentations/index.html
tracks:
- keynote
- workshop
- java
- alt-lang
- archictecture
- tools
- frameworks
- security
- performance
- web
- javascript
- mobile
- cloud
- microservices
- serverless
- agile
---
{% for track in page.tracks %}
{% assign track_data = site.data.tracks[track] %}
{% assign events = site.events | where:"track", track_data.trackkey %}
{% if events %}
<h1 class="featured-header"><span>— {{ track_data.title | escape }} —</span></h1>
<div class="row">
{% for event in events %}
 {% include presentation_thumb.html details=event track=track %}
{% endfor %}
</div>
{% endif %}
{% endfor %}
