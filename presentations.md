---
layout: info-fluid
permalink: /presentations/index.html
---
{% for track in site.data.tracks %}
{% assign events = site.events | where:"trackid", track.trackid %}
<h1 class="featured-header"><span>— {{ track.title | escape }} —</span></h1>
<div class="row">
{% for event in events %}
 {% include presentation_thumb.html details=event track=track.id %}
{% endfor %}
</div>
{% endfor %}
