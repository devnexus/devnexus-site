---
layout: info-fluid
day0:
- start: 9:00
  end: 5:00
  "Core Java":
     id: 1982
day1:
- start: 11:15
  end: 12:15
  "Core Java":
     id: 1982
---
{% assign eventList = page.day0 %}
{% include schedule_block.html events=eventList start='9:00' end='5:00' %}
