---
layout: info-fluid
---
{% assign day0 = site.data.schedule | where: "index", 0  | first %}
{% assign day2 = site.data.schedule | where: "index", 1  | first %}
{% assign day3 = site.data.schedule | where: "index", 2  | first %}
<h1>Wednesday March 6 </h1>
<h6>* Workshop Ticket Required</h6>
<h3>9:00 - 17:00</h3>
{% include schedule_block.html events=day0.events track="workshop" %}

<h1> Thursday March 7</h1>

{% assign day2_0900 = day2.events | where: "start", "09:00" | first %}
{% assign day2_keynote= site.events | where: "slug", day2_0900.id | first %}
<h3>9:00</h3>
{% include schedule_event.html details=day2_keynote room="keynote" %}

{% assign day2_1000 = day2.events | where: "start", "10:00" %}
<h3>10:00</h3>
{% include schedule_block.html events=day2_1000 %}

{% assign day2_1120= day2.events | where: "start", "11:20" %}
<h3>11:20</h3>
{% include schedule_block.html events=day2_1120 %}

{% assign day2_1200 = day2.events | where: "start", "12:20" | first %}
{% assign day2_lunch= site.events | where: "slug", day2_1220.id | first %}
<h3>12:20</h3>
{% include schedule_event.html details=day2_lunch room="lunch" %}

{% assign day2_1320= day2.events | where: "start", "13:20" %}
<h3>13:20</h3>
{% include schedule_block.html events=day2_1320 %}

{% assign day2_1440= day2.events | where: "start", "14:40" %}
<h3>14:40</h3>
{% include schedule_block.html events=day2_1440 %}

{% assign day2_1600= day2.events | where: "start", "16:00" %}
<h3>16:00</h3>
{% include schedule_block.html events=day2_1600 %}


{% assign day2_1800 = day2.events | where: "start", "18:00" | first %}
{% assign day2_night= site.events | where: "slug", day2_1800.id | first %}
<h3>18:00</h3>
{% include schedule_event.html details=day2_night room="happy-hour" %}


<h1> Friday March 8</h1>

{% assign day3_0900 = day3.events | where: "start", "09:00" | first %}
{% assign day3_keynote= site.events | where: "slug", day3_0900.id | first %}
<h3>9:00</h3>
{% include schedule_event.html details=day3_keynote room="keynote" %}

{% assign day3_1000 = day3.events | where: "start", "10:00" %}
<h3>10:00</h3>
{% include schedule_block.html events=day3_1000 %}

{% assign day3_1120= day3.events | where: "start", "11:20" %}
<h3>11:20</h3>
{% include schedule_block.html events=day3_1120 %}

{% assign day3_1200= day3.events | where: "start", "12:20" | first %}
{% assign day3_lunch= site.events | where: "slug", day3_1200.id | first %}
<h3>12:20</h3>
{% include schedule_event.html details=day3_lunch room="lunch" %}

{% assign day3_1310= day3.events | where: "start", "13:10" %}
<h3>13:10</h3>
{% include schedule_block.html events=day3_1310 %}

{% assign day3_1440= day3.events | where: "start", "14:40" %}
<h3>14:40</h3>
{% include schedule_block.html events=day3_1440 %}

{% assign day3_1600 = day3.events | where: "start", "16:00" %}
<h3>16:00</h3>
{% include schedule_block.html events=day3_1600 %}

{% assign afterparty = site.events | where: "slug", "2113" | first %}
<h3>19:00</h3>
<div class="row schedule-row">
		<div class="col-sm-6 col-xs-12 workshop">
				<a href="{{afterparty.url}}">{{afterparty.title | escape}}</a>
				<br/>
			<span class="small pull-right">
				 JoyStick Gamebar
			</span>
	  </div>
</div>
