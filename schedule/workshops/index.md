---
layout: schedule
title: Devnexus Workshop Day
tracks:
 - Workshop
--- 
{% assign workshopDay = site.data.schedule[0] %}
{% assign workshop_sessions = workshopDay.timeSlots | sort: "slotStart" %}
<div class="day-content mar04" id="mar04">
  <h2 style="padding: 20px 15px;">Wednesday Mar 4 - Workshops 9.00 am - 5.00 pm</h2>
  <h3 style="padding: 0 15px; color: var(--text-dim);">*** Workshop ticket holders only. ***</h3>
  {% for slot in workshop_sessions %}
  {% assign events = slot.rooms 
  | where_exp: "item", "item.session.isServiceSession != true" 
  | sort: "name" 
  %}
  {% if events.size > 0 %}
    <div class="timeline-slot mb-5" data-time="{{ slot.slotStart }}" data-day="mar04">
        <div class="time-header bg-black text-white px-4 py-2.5 font-bold rounded-t-md">
            <span>{{ slot.slotStart }}</span>
        </div>
        <div class="border border-gray-300 border-t-0 rounded-b-md p-4">
            {% for e in events %} 
                <div class="session-card mb-4">
                    {% include schedule_event.html details=e.session %}
                </div>
            {% endfor %}
        </div>
    </div>
  {% endif %}
  {% endfor %}

</div>

