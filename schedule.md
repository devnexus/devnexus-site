---
layout: info-fluid
---
{% assign day0 = site.data.schedule | where: "index", 0  | first %}
{% assign day2 = site.data.schedule | where: "index", 1  | first %}
{% assign day2_other = day2.events | where: "track", "admin" | sort: "start" %}
{% assign day3 = site.data.schedule | where: "index", 2  | first %}
<div class="row">
<div class="col-xs-8">
<h1 class="day"> Wednesday Feb 19</h1>
<h6>* Workshop Ticket Required</h6>
<h3>9:00 - 17:00</h3>
</div>
 {% assign workshops = site.events | where:"track","full day workshops (wednesday only)" %}
 {% for event in workshops %}
 {% include schedule_workshop.html details=event %}
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
{% include schedule_break.html details=day2_other item=2 room="keynote" %}
{% include schedule_break.html details=day2_other item=3 room="break" %}

{% assign day2_10 = day2.events | where: "start", "10:20" %}
<h3>10:20</h3>
{% include schedule_block.html events=day2_10 %}

{% assign day2_11= day2.events | where: "start", "11:20" %}
<h3>11:20</h3>
{% include schedule_block.html events=day2_11 %}

{% include schedule_break.html details=day2_other item=4 room="lunch" %}

{% assign day2_13= day2.events | where: "start", "13:10" %}
<h3>13:10</h3>
{% include schedule_block.html events=day2_13 %}

{% assign day2_14= day2.events | where: "start", "14:10" %}
<h3>14:10</h3>
{% include schedule_block.html events=day2_14 %}

{% include schedule_break.html details=day2_other item=5 room="break" %}

{% assign day2_15= day2.events | where: "start", "15:30" %}
<h3>15:30</h3>
{% include schedule_block.html events=day2_15 %}

{% include schedule_break.html details=day2_other item=6 room="keynote" %}
{% include schedule_break.html details=day2_other item=7 room="keynote" %}
{% include schedule_break.html details=day2_other item=8 room="break" %}

{% comment %}
{% assign day2_k2= site.events | where: "slug", day2_1710.id | first %}
<h3>17:10</h3>
{% include schedule_event.html details=day2_k2 room="keynote" %}
{% endcomment %}
{% comment %}
{% assign day2_1800 = day2.events | where: "start", "18:00" | first %}
{% assign day2_night= site.events | where: "slug", day2_1800.id | first %}
<h3>18:00</h3>
{% include schedule_event.html details=day2_night room="happy-hour" %}
{% endcomment %}

<h1 class="day"> Friday Feb 21</h1>
{% comment %}
{% assign day3_other = day3.events | where: "track", "Other" | sort: "start" %}

{% include schedule_break.html details=day3_other item=0 room="breakfast" %}
{% endcomment %}

{% comment %}
{% assign day3_0900 = day3.events | where: "start", "09:00" | first %}
{% assign day3_keynote= site.events | where: "slug", day3_0900.id | first %}
<h3>9:00</h3>
{% include schedule_event.html details=day3_keynote room="keynote" %}
{% endcomment %}

{% assign day3_10 = day3.events | where: "start", "10:20" %}
<h3>10:20</h3>
{% include schedule_block.html events=day3_10 %}

{% comment %}
{% include schedule_break.html details=day3_other item=1 room="break" %}
{% endcomment %}

{% assign day3_11= day3.events | where: "start", "11:20" %}
<h3>11:20</h3>
{% include schedule_block.html events=day3_11 %}

{% comment %}
{% include schedule_break.html details=day3_other item=2 room="lunch" %}
{% endcomment %}

{% assign day3_13= day3.events | where: "start", "13:10" %}
<h3>13:10</h3>
{% include schedule_block.html events=day3_13 %}

{% comment %}
{% include schedule_break.html details=day3_other item=3 room="break" %}
{% endcomment %}

{% assign day3_14= day3.events | where: "start", "14:10" %}
<h3>14:10</h3>
{% include schedule_block.html events=day3_14 %}

{% comment %}
{% include schedule_break.html details=day3_other item=4 room="break" %}
{% endcomment %}

{% assign day3_15 = day3.events | where: "start", "15:30" %}
<h3>15:30</h3>
{% include schedule_block.html events=day3_15 %}

{% comment %}
{% assign day3_1700 = day3.events | where: "start", "17:00" | first %}
{% assign day3_close= site.events | where: "slug", day3_1700.id | first %}
<h3>17:00</h3>
{% include schedule_event.html details=day3_close room="keynote" %}

{% assign afterparty = site.events | where: "slug", "3746" | first %}
<h3>19:30</h3>
<div class="row schedule-row">
		<div class="col-sm-6 col-xs-12 workshop">
				<a href="{{afterparty.url}}">{{afterparty.title | escape}}</a>
				<br/>
			<span class="small pull-right">
			   427 Edgewood Ave. SE Atlanta GA 30312
			</span>
	  </div>
</div>
{% endcomment %}
