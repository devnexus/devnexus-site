---
layout: info-fluid
---
{% assign day0 = site.data.schedule | where: "index", 0  | first %}

<input class="form-control no-print" id="scheduleSearch" type="text" placeholder="Search..">

<div class="row no-print">
</div>

<div class="row new-day">
  <div class="col-xs-12">
    <div class="col-xs-8">
      <h2 class="day"> Tuesday Apr 12 - Workshops </h2>
      <h3>*** Workshop ticket holders only. ***</h3>
    </div>  
    <div class="col-xs-4 col-sm-2 box no-print">
      <div class="ribbon">
        <span><a href="/workshopinstructions">Get Ready</a></span>
      </div>
    </div>
  </div>
 {% assign workshops = day0.events | where:"track","Full day Workshops" %}
 {% for event in workshops %}
 {% assign _room = site.data.cfp_rooms_to_gwwc[event.room]  %}
 {% include schedule_workshop.html details=event room=_room track="workshop" %}
 {% endfor %}
</div>

