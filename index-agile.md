---
layout: home
permalink: /presentations/agile
dates: Apr 4 2023
branding:
  img: /assets/img/dev-nexus-logo-large.png
  alt: Devnexus 2024
---
{% assign events = site.events | where: "track", "Agile" | sort: "name" %}
<div class="featured-header">
   <h1>AGILE</h1>
</div>

{% for session in events %}
<div class="workshop-promo-row">
  <h2><a href="/presentations/{{session.slug}}">{{session.title}}</a></h2>
  {% for speaker in session.speakers %}
      {% include workshop_speaker.html data = speaker %}
  {% endfor %}
</div>
{% endfor %}

<a href="/presentations" class="btn btn-square btn-square btn-speakers center-block">SEE ALL SESSIONS</a>