---
layout: info-fluid
permalink: /speakers/index.html
---
<div class="row">
{% assign sortedspeakers = site.speakers | sort: "lastName" %}
{% for speaker in sortedspeakers %}
{% include speaker_thumb.html speaker = speaker %}
{% endfor %}
</div>
