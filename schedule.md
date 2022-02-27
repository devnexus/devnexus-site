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

 <div class="row new-day">
 <div class="col-xs-12">
   <div class="col-xs-8">
     <h2 class="day"> Wednesday Apr 13 - Main Conference</h2>
   </div>
   <div class="col-xs-4 col-sm-2 box no-print">
    <div class="ribbon">
      <span><a href="/assets/img/conference_map.png">ROOM MAP</a></span>
    </div>
   </div>
</div>
{% include schedule_break.html details=day2_other item=0 room="breakfast" %}
{% include schedule_keynote.html details=wit item=0 room="wit" %}
{% include schedule_break.html details=day2_other item=1 room="keynote" %}
{% include schedule_keynote.html details=day2_keynote item=0 room="keynote" %}
{% include schedule_break.html details=day2_other item=2 room="break" %}

{% assign day2_10 = day2.events | where: "start", "10:20" %}
<h3>10:20</h3>
{% include schedule_block.html events=day2_10 %}

{% assign day2_11= day2.events | where: "start", "11:20" %}
<h3>11:20</h3>
{% include schedule_block.html events=day2_11 %}

{% include schedule_break.html details=day2_other item=3 room="lunch" %}

{% assign day2_13= day2.events | where: "start", "13:10" %}
<h3>13:10</h3>
{% include schedule_block.html events=day2_13 %}

{% assign day2_14= day2.events | where: "start", "14:10" %}
<h3>14:10</h3>
{% include schedule_block.html events=day2_14 %}

{% include schedule_break.html details=day2_other item=4 room="break" %}

{% assign day2_15= day2.events | where: "start", "15:30" %}
<h3>15:30</h3>
{% include schedule_block.html events=day2_15 %}

{% include schedule_keynote.html details=day2_keynote item=1 room="keynote" %}
{% include schedule_keynote.html details=day2_keynote item=2 room="keynote" %}

<h3>17:40</h3>
{% assign offheap = site.events | where: "slug", 4810 | first %}
{% include schedule_event.html details=offheap track="off-heap" room="off-heap" %}

{% include schedule_break.html details=day2_other item=5 track="happy-hour" room="break" %}
</div> 