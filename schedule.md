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
     <h2 class="day"> Wednesday Feb 17: Main Event</h2>
   </div>
   <div class="col-xs-4 col-sm-2 box no-print">
    <div class="ribbon">
      <span><a href="https://live.eventinsight.io/1217-devnex21/virtualevent/">Check In</a></span>
    </div>
   </div>
</div>

{% for block in event_blocks %}
<h3>{{ block.name }}</h3>
{% assign events = block.items %}
{% include schedule_block.html events=events %}
{% endfor %}
</div>

{% assign class_events = site.data.schedule | where: "index", 1  | first %}
{% assign class_blocks = class_events.events | group_by: "start" %}
<div class="row no-print">
   <hr/>
</div>
 <div class="row new-day">
 <div class="col-xs-12">
   <div class="col-xs-8">
     <a name="workshops"></a>
     <h2 class="day"> Thursday Feb 18: Workshops</h2>
     <h3>*** Workshop ticket holders only. *** </h3>
   </div>
   <div class="col-xs-4 col-sm-2 box no-print">
    <div class="ribbon">
      <span><a href="/workshopinstructions">Get Ready</a></span>
    </div>
   </div>
</div>

{% for block in class_blocks %}
<h3>{{ block.name }}</h3>
{% assign events = block.items %}
{%for e in events %}
{% include schedule_workshop.html details=e duration=e.duration track="workshop" %}
{%endfor%}
{% endfor %}
</div>
