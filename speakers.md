---
layout: info-fluid
permalink: /speakers/index.html
---
<div class="row">
{% for speaker in site.speakers %}
{% include speaker_thumb.html speaker = speaker %}
{% endfor %}
</div>
