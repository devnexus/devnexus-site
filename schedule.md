---
layout: info-fluid
---
{% comment %}{% assign day0 = site.data.schedule | where: "index", 0  | first %}{% endcomment %}
{% assign day2 = site.data.schedule | where: "index", 1  | first %}
{% assign day3 = site.data.schedule | where: "index", 2  | first %}
<div class="row">
<div>
 <h1 class="day"> Thursday March 7</h1>
  <div class="ribbon">
    <span><a href="/assets/img/galleria-map.png">ROOM MAP</a></span>
  </div>
</div>
</div>
{% assign day2_other = day2.events | where: "track", "Other" | sort: "start" %}
{% include schedule_break.html details=day2_other item=0 room="breakfast" %}

{% assign day2_wit = site.events | where: "track", "wit" | first %}
<h3>8:00</h3>
{% include schedule_event.html details=day2_wit room="wit"%}

{% assign day2_0900 = day2.events | where: "start", "09:00" | first %}
{% assign day2_keynote= site.events | where: "slug", day2_0900.id | first %}
<h3>9:00</h3>
{% include schedule_event.html details=day2_keynote room="keynote" %}

{% assign day2_1000 = day2.events | where: "start", "10:00" %}
<h3>10:00</h3>
{% include schedule_block.html events=day2_1000 %}

{% include schedule_break.html details=day2_other item=1 room="break" %}

{% assign day2_1120= day2.events | where: "start", "11:20" %}
<h3>11:20</h3>
{% include schedule_block.html events=day2_1120 %}

{% include schedule_break.html details=day2_other item=2 room="lunch" %}

{% assign day2_1320= day2.events | where: "start", "13:20" %}
<h3>13:20</h3>
{% include schedule_block.html events=day2_1320 %}

{% include schedule_break.html details=day2_other item=3 room="break" %}

{% assign day2_1440= day2.events | where: "start", "14:40" %}
<h3>14:40</h3>
{% include schedule_block.html events=day2_1440 %}

{% include schedule_break.html details=day2_other item=4 room="break" %}

{% assign day2_1600= day2.events | where: "start", "16:00" %}
<h3>16:00</h3>
{% include schedule_block.html events=day2_1600 %}

{% include schedule_break.html details=day2_other item=5 room="break" %}

{% assign day2_1710 = day2.events | where: "start", "17:10" | first %}
{% assign day2_k2= site.events | where: "slug", day2_1710.id | first %}
<h3>17:10</h3>
{% include schedule_event.html details=day2_k2 room="keynote" %}


{% assign day2_1800 = day2.events | where: "start", "18:00" | first %}
{% assign day2_night= site.events | where: "slug", day2_1800.id | first %}
<h3>18:00</h3>
{% include schedule_event.html details=day2_night room="happy-hour" %}


<h1 class="day"> Friday March 8</h1>
{% assign day3_other = day3.events | where: "track", "Other" | sort: "start" %}

{% include schedule_break.html details=day3_other item=0 room="breakfast" %}

{% assign day3_0900 = day3.events | where: "start", "09:00" | first %}
{% assign day3_keynote= site.events | where: "slug", day3_0900.id | first %}
<h3>9:00</h3>
{% include schedule_event.html details=day3_keynote room="keynote" %}

{% assign day3_1000 = day3.events | where: "start", "10:00" %}
<h3>10:00</h3>
{% include schedule_block.html events=day3_1000 %}

{% include schedule_break.html details=day3_other item=1 room="break" %}

{% assign day3_1120= day3.events | where: "start", "11:20" %}
<h3>11:20</h3>
{% include schedule_block.html events=day3_1120 %}

{% include schedule_break.html details=day3_other item=2 room="lunch" %}

{% assign day3_1320= day3.events | where: "start", "13:20" %}
<h3>13:20</h3>
{% include schedule_block.html events=day3_1320 %}

{% include schedule_break.html details=day3_other item=3 room="break" %}

{% assign day3_1440= day3.events | where: "start", "14:40" %}
<h3>14:40</h3>
{% include schedule_block.html events=day3_1440 %}

{% include schedule_break.html details=day3_other item=4 room="break" %}

{% assign day3_1600 = day3.events | where: "start", "16:00" %}
<h3>16:00</h3>
{% include schedule_block.html events=day3_1600 %}

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
				 Exhibition Hall
			</span>
	  </div>
</div>
