---
layout: info-fluid
---
{% assign day_events = site.data.schedule | where: "index", 0  | first %}
{% assign event_blocks = day_events.events | group_by: "start" %}

<input class="form-control no-print" id="scheduleSearch" type="text" placeholder="Search..">
<div class="row no-print">
</div>
 <div class="row new-day">
 <div class="col-xs-12">
   <div class="col-xs-8">
     <h2 class="day"> Wednesday Feb 17</h2>
   </div>
   {% comment %}
   <div class="col-xs-4 col-sm-2 box no-print">
    <div class="ribbon">
      <span><a href="/assets/img/conference_map.png">Online Lobby</a></span>
    </div>
   </div>
   {% endcomment %}
</div>

{% for block in event_blocks %}
<h3>{{ block.name }}</h3>
{% assign events = block.items %}
{% include schedule_block.html events=events %}
{% endfor %}
</div>
