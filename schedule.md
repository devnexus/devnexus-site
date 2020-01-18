---
layout: info-fluid
---
{% assign day0 = site.data.schedule | where: "index", 0  | first %}
{% assign day2 = site.data.schedule | where: "index", 1  | first %}
{% assign day2_other = day2.events | where: "track", "admin" | sort: "start" %}
{% assign day2_keynote = day2.events | where: "track", "Keynote" | sort: "start" %}
{% assign day3 = site.data.schedule | where: "index", 2  | first %}
{% assign day3_other = day3.events | where: "track", "admin" | sort: "start" %}
{% assign day3_keynote = day3.events | where: "track", "Keynote" | sort: "start" %}
<div class="row">
<div class="col-xs-8">
<h1 class="day"> Wednesday Feb 19</h1>
<h6>* Workshop Ticket Required</h6>
<h3>9:00 - 17:00</h3>
</div>
 {% assign workshops = day0.events | where:"track","Full day Workshops (Wednesday Only)" %}
 {% for event in workshops %}
 {% assign _room = site.data.cfp_rooms_to_gwwc[event.room]  %}
 {% include schedule_workshop.html details=event room=_room %}
 {% endfor %}

 {% assign wed_other = day0.events | where:"track","admin" %}
 {% for event in wed_other %}
 <h3>{{event.start}}</h3>
 {% assign edetails = site.events | where: "slug", event.id | first%}
 {% include schedule_event.html details=edetails track="community" %}
 {% endfor %}
 
<div class="col-xs-8">
<h1 class="day"> Thursday Feb 20</h1>
</div>
{% comment %}
<div class="col-xs-4 box">
  <div class="ribbon">
    <span><a href="/assets/img/galleria-map.png">ROOM MAP</a></span>
  </div>
</div>
{% endcomment %}
</div>

{% include schedule_break.html details=day2_other item=0 room="breakfast" %}
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



<h1 class="day"> Friday Feb 21</h1>
{% include schedule_break.html details=day3_other item=0 room="breakfast" %}
{% include schedule_keynote.html details=day3_keynote item=0 room="keynote" %}
{% include schedule_break.html details=day3_other item=1 room="break" %}

{% assign day3_10 = day3.events | where: "start", "10:20" %}
<h3>10:20</h3>
{% include schedule_block.html events=day3_10 %}

{% assign day3_11= day3.events | where: "start", "11:20" %}
<h3>11:20</h3>
{% include schedule_block.html events=day3_11 %}

{% include schedule_break.html details=day3_other item=2 room="lunch" %}

{% assign day3_13= day3.events | where: "start", "13:10" %}
<h3>13:10</h3>
{% include schedule_block.html events=day3_13 %}

{% assign day3_14= day3.events | where: "start", "14:10" %}
<h3>14:10</h3>
{% include schedule_block.html events=day3_14 %}

{% include schedule_break.html details=day3_other item=3 room="break" %}

{% assign day3_15 = day3.events | where: "start", "15:30" %}
<h3>15:30</h3>
{% include schedule_block.html events=day3_15 %}

{% include schedule_break.html details=day3_other item=4 room="keynote" %}

{% include schedule_break.html details=day3_other item=5 room="after-party" %}

