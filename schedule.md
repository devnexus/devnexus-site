---
layout: info-fluid
tracks:
- 
  key: cloud-infrastructure
  name: Cloud Infrastructure
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
- 
  key: artificial-intelligence
  name: AI
-
  key: cloud-native
  name: Cloud Native
- 
  key: agile
  name: Agile
-
  key: jakartaee
  name: JakartaEE
- 
  key: keynote
  name: Keynotes         
timeslots:  
- 
  key: 07-30-00
  name: Morning Socials
  slots:
  - 07-30-00
  - 08-15-00
  - 08-00-00
- key: 09-00-00
  name: 09:00 - 10:00
  slots:
  - 09-00-00
  - 09-10-00
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
  name: 13:30 - 15:00
  slots: 
  - 13-30-00
  - 14-00-00
  - 14-30-00
- key: 15-00-00
  name: 15:00 - 16:00
  slots: 
  - 15-00-00
  - 15-30-00
- key: 16-00-00
  name: 16:00 - 17:00
  slots: 
  - 15-30-00
  - 16-00-00
- key: 17-00-00
  name: 17:00 - 18-00
  slots:
  - 17-00-00
- key: socials
  name: Evening Socials
  slots: 
  - 18-00-00
  - 17-30-00
  - 16-45-00
  - 19-30-00

---



{% assign workshopDay = site.data.schedule[0] %}
{% assign workshop_sessions = workshopDay.timeSlots | sort-by: "slotStart" %}
<div class="row new-day mar04">
  <div class="col-xs-12">
      <div class="col-xs-12 box no-print pull-right" style="margin-top:1em">
        <div>
          <h2 class="day "> Wednesday Mar 4 - Workshops 9.00 am - 5.00 pm </h2>
          <h3>*** Workshop ticket holders only. ***</h3>
        </div>  
        <div class="until09">
          {% for slot in workshop_sessions %}
             <h3>{{ slot.slotStart }}</h3>
             {% assign events = slot.rooms %}
             {% for e in events %} 
                {% include schedule_event.html details=e.session %}
             {% endfor %}
          {% endfor %}
        </div>
      </div>
  </div>    
</div>

{% assign day1 = site.data.schedule[1] %}
{% assign day1_times = day1.timeSlots | sort-by: "slotStart" %}
<div class="row new-day mar05">
  <div class="col-xs-12">
      <div class="col-xs-12 box no-print pull-right" style="margin-top:1em">
        <div>
          <h2 class="day "> Thursday Mar 5 - Day 1 </h2>
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
<div class="row new-day mar06">
  <div class="col-xs-12">
      <div class="col-xs-12 box no-print pull-right" style="margin-top:1em">
        <div>
          <h2 class="day "> Friday Mar 6 - Day 2 </h2>
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

