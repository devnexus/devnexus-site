---
layout: info-fluid
---

{% assign schedule = site.data.schedule %}
{% assign workshops = site.data.schedule[0] %}
{% assign workshop_reg = workshops.timeSlots[0].rooms %}
{% assign workshop_sessions = workshops.timeSlots[1].rooms %}


<div class="row new-day apr12">
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
