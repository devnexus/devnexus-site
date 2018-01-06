---
layout: info-fluid
---
{% assign day0 = site.data.schedule | where: "index", 0  | first %}
{% assign day2 = site.data.schedule | where: "index", 1  | first %}
{% assign day3 = site.data.schedule | where: "index", 2  | first %}
<h1>Wednesday February 21 </h1>
<h3>9:00</h3>
<h6>* Workshop Ticket Required</h6>
{% include schedule_block.html events=day0.events %}
<h1> Thursday February 22</h1>

{% assign day2_1000 = day2.events | where: "start", "10:00" %}
<h3>10:00</h3>
{% include schedule_block.html events=day2_1000 %}

{% assign day2_1115= day2.events | where: "start", "11:15" %}
<h3>11:15</h3>
{% include schedule_block.html events=day2_1115 %}

{% assign day2_1315= day2.events | where: "start", "13:15" %}
<h3>13:15</h3>
{% include schedule_block.html events=day2_1315 %}

{% assign day2_1430= day2.events | where: "start", "14:30" %}
<h3>14:30</h3>
{% include schedule_block.html events=day2_1430 %}

{% assign day2_1545= day2.events | where: "start", "15:45" %}
<h3>15:45</h3>
{% include schedule_block.html events=day2_1545 %}

<h1> Friday February 23</h1>

{% assign day3_1000 = day2.events | where: "start", "10:00" %}
<h3>10:00</h3>
{% include schedule_block.html events=day3_1000 %}

{% assign day3_1115= day2.events | where: "start", "11:15" %}
<h3>11:15</h3>
{% include schedule_block.html events=day3_1115 %}

{% assign day3_1315= day2.events | where: "start", "13:15" %}
<h3>13:15</h3>
{% include schedule_block.html events=day3_1315 %}

{% assign day3_1430= day2.events | where: "start", "14:30" %}
<h3>14:30</h3>
{% include schedule_block.html events=day3_1430 %}

{% assign day3_1545= day2.events | where: "start", "15:45" %}
<h3>15:45</h3>
{% include schedule_block.html events=day3_1545 %}
