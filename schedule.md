---
layout: info-fluid
day0:
- start: "9:00"
  end: "5:00"
  rooms:
    "Core Java":
      id: 1892
---
{% assign eventList = page.day0[0] %}
{% include schedule_block.html events=eventList.rooms start='9:00' end='5:00' %}
