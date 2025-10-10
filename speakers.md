---
layout: info-fluid
permalink: /speakers/index.html
---
<div class="grid grid-cols-2 sm:grid-cols-3 md:grid-cols-4 lg:grid-cols-5 gap-2">
{% assign sortedspeakers = site.speakers | sort: "lastName" %}
{% for speaker in sortedspeakers %}
{% include speaker_thumb.html speaker = speaker %}
{% endfor %}
</div>
