---
layout: home
permalink: /workshops/index.html
dates: Apr 4 2023
registration:
  text: REGISTER HERE
  status: enabled
  link: https://reg.connectevents.io/ConnectEvents/devnexus2023/
branding:
  img: /assets/img/dev-nexus-logo-large.png
  alt: Devnexus 2023
---
{% assign workshop_events = site.events | where: "format", "workshop" | sort: "name" %}
<div class="featured-header">
   <h1>Full Day Workshops</h1>
</div>

{% for session in workshop_events %}
<div class="workshop-promo-row">
  <h2><a href="/presentations/{{session.slug}}">{{session.title}}</a></h2>
  {% for speaker in session.speakers %}
      {% include workshop_speaker.html data = speaker %}
  {% endfor %}
</div>
{% endfor %}

<a href="/presentations" class="btn btn-square btn-square btn-speakers center-block">SEE ALL SESSIONS</a>