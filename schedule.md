---
layout: info-fluid
tracks:
- 
  key: cloud-infrastructure
  name: Cloud Infrastructure
- 
  key: jakarta-ee
  name: JakartaEE
- 
  key: web-and-front-end
  name: Web 
- 
  key: core-java
  name: Core Java
- 
  key: security
  name: Security
- 
  key: unobtanium
  name: Unobtanium
- 
  key: cloud-technology
  name: Cloud Technology
- 
  key: frameworks
  name: Frameworks
- 
  key: java-platform
  name: Java Platform
- 
  key: tools-and-techniques
  name: Tools and Techniques
- 
  key: practices-and-other-tech
  name: Practices and other tech
- 
  key: architecture
  name: Architecture
---


{% assign schedule = site.data.schedule %}
{% assign workshops = site.data.schedule[0] %}
{% assign workshop_reg = workshops.timeSlots[0].rooms %}
{% assign workshop_sessions = workshops.timeSlots[1].rooms %}

{% include schedule_filtering.html %}

<div class="row new-day apr04">
  <div class="col-xs-12">
      <div class="col-xs-12 box no-print pull-right" style="margin-top:1em">
        <div class="ribbon">
            <span><a href="/workshopinstructions">Get Ready</a></span>
        </div>
        <div>
          <h2 class="day "> Tuesdsay Apr 4 - Workshops </h2>
          <h3>*** Workshop ticket holders only. ***</h3>
        </div>  
        <div class="until09">
          <h3>09:00</h3>
          {% for room in workshop_sessions %}
            {% include schedule_workshop.html details=room.session %}
          {% endfor %}
        </div>
      </div>
  </div>    
</div>

{% assign day1 = site.data.schedule[1] %}
{% assign day1_times = day1.timeSlots %}
<div class="row new-day apr05">
  <div class="col-xs-12">
      <div class="col-xs-12 box no-print pull-right" style="margin-top:1em">
        <div class="ribbon">
            <span><a href="/gwcc_map.pdf">Rooms</a></span>
        </div>
        <div>
          <h2 class="day "> Wednesday Apr 5 - Day 1 </h2>
        </div>
        {% for slot in day1_times %}
        {% assign rooms = slot.rooms | sort: "name" %}
        <div class="until09">
          <h3>{{slot.slotStart}}</h3>
          {% for room in rooms %}
            {% include schedule_event.html details=room.session %}
          {% endfor %}
        </div>
        {% endfor %}
      </div>
  </div>    
</div>

{% assign day2 = site.data.schedule[2] %}
{% assign day2_times = day2.timeSlots %}
<div class="row new-day apr06">
  <div class="col-xs-12">
      <div class="col-xs-12 box no-print pull-right" style="margin-top:1em">
        <div class="ribbon">
            <span><a href="/gwcc_map.pdf">Rooms</a></span>
        </div>
        <div>
          <h2 class="day "> Thursday Apr 6 - Day 2 </h2>
        </div>
        {% for slot in day2_times %}
        <div class="until09">
          <h3>{{slot.slotStart}}</h3>
          {% assign rooms = slot.rooms | sort: "name" %}
          {% for room in rooms %}
            {% include schedule_event.html details=room.session %}
          {% endfor %}
        </div>
        {% endfor %}
      </div>
  </div>    
</div>

