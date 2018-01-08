---
layout: info-fluid
---
{% assign day0 = site.data.schedule | where: "index", 0  | first %}
{% assign day2 = site.data.schedule | where: "index", 1  | first %}
{% assign day3 = site.data.schedule | where: "index", 2  | first %}
<h1>Wednesday February 21 </h1>
<h6>* Workshop Ticket Required</h6>
<h3>9:00</h3>
{% include schedule_block.html events=day0.events track="workshop" %}

<h1> Thursday February 22</h1>

{% assign day2_0900 = day2.events | where: "start", "09:00" | first %}
{% assign day2_keynote= site.events | where: "slug", day2_0900.id | first %}
<h3>9:00</h3>
{% include schedule_event.html details=day2_keynote room="keynote" %}

{% assign day2_1000 = day2.events | where: "start", "10:00" %}
<h3>10:00</h3>
{% include schedule_block.html events=day2_1000 %}

{% assign day2_1115= day2.events | where: "start", "11:15" %}
<h3>11:15</h3>
{% include schedule_block.html events=day2_1115 %}

{% assign day2_1200 = day2.events | where: "start", "12:00" | first %}
{% assign day2_lunch= site.events | where: "slug", day2_1200.id | first %}
<h3>12:00</h3>
{% include schedule_event.html details=day2_lunch room="lunch" %}

{% assign day2_1315= day2.events | where: "start", "13:15" %}
<h3>13:15</h3>
{% include schedule_block.html events=day2_1315 %}

{% assign day2_1430= day2.events | where: "start", "14:30" %}
<h3>14:30</h3>
{% include schedule_block.html events=day2_1430 %}

{% assign day2_1545= day2.events | where: "start", "15:45" %}
<h3>15:45</h3>
{% include schedule_block.html events=day2_1545 %}

{% assign day2_1700= day2.events | where: "start", "17:00" %}
<h3>17:00</h3>
{% include schedule_block.html events=day2_1700 %}

{% assign day2_1800 = day2.events | where: "start", "18:00" | first %}
{% assign day2_night= site.events | where: "slug", day2_1800.id | first %}
<h3>18:00</h3>
{% include schedule_event.html details=day2_night room="happy-hour" %}


<h1> Friday February 23</h1>

{% assign day3_0900 = day3.events | where: "start", "09:00" | first %}
{% assign day3_keynote= site.events | where: "slug", day3_0900.id | first %}
<h3>9:00</h3>
{% include schedule_event.html details=day3_keynote room="keynote" %}

{% assign day3_1000 = day3.events | where: "start", "10:00" %}
<h3>10:00</h3>
{% include schedule_block.html events=day3_1000 %}

{% assign day3_1115= day3.events | where: "start", "11:15" %}
<h3>11:15</h3>
{% include schedule_block.html events=day3_1115 %}

{% assign day3_1200= day3.events | where: "start", "12:00" | first %}
{% assign day3_lunch= site.events | where: "slug", day3_1200.id | first %}
<h3>12:00</h3>
{% include schedule_event.html details=day3_lunch room="lunch" %}

{% assign day3_1315= day3.events | where: "start", "13:15" %}
<h3>13:15</h3>
{% include schedule_block.html events=day3_1315 %}

{% assign day3_1430= day3.events | where: "start", "14:30" %}
<h3>14:30</h3>
{% include schedule_block.html events=day3_1430 %}

{% assign day3_1545= day3.events | where: "start", "15:45" %}
<h3>15:45</h3>
{% include schedule_block.html events=day3_1545 %}

{% assign day3_1645 = day3.events | where: "start", "16:45" | first %}
{% assign day3_close= site.events | where: "slug", day3_1645.id | first %}
<h3>16:45</h3>
{% include schedule_event.html details=day3_close room="keynote" %}
