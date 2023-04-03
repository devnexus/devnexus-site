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
  
timeslots:  
- 
  key: 07-30-00
  name: Morning Socials
  slots:
  - 07-30-00
  - 07-45-00
  - 08-00-00
- key: 09-00-00
  name: 09:00 - 10:00
  slots:
  - 09-00-00
- key: 10-00-00
  name: 10:00 - 11:00
  slots:
  - 10-00-00
  - 11-00-00
- key: 11-30-00
  name: 11:30 - 12:30
  slots: 
  - 11-00-00
  - 11-30-00
- key: 12-30-00
  name: 12:30 - 13:30
  slots: 
  - 12-30-00
- key: 13-30-00
  name: 13:30 - 14:30
  slots: 
  - 13-30-00
  - 14-30-00
- key: 14-45-00
  name: 14:45 - 15:45
  slots: 
  - 14-45-00
- key: 15-00-00
  name: 15:00 - 16:00
  slots: 
  - 15-00-00
- key: 15-45-00
  name: 15:45 - 16-45
  slots:
  - 15-45-00
- key: 16-00-00
  name: 16:00 - 17:00
  slots:
  - 16-00-00
- key: 17-00-00
  name: Evening Socials
  slots: 
  - 17-00-00
  - 17-30-00
  - 16-45-00
  - 20-00-00

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
        <div class="{{slot.slotStart | slugify}}">
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
        <div class="{{slot.slotStart | slugify}}">
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

