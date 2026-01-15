---
layout: schedule
title: Devnexus 2026 Schedule
---

{% assign day0 = site.data.schedule[0] %}
{% assign day0_times = day0.timeSlots | sort: "slotStart" %}
<section class="day-content day1" id="day1">
<h2 class="text-2xl font-bold px-4 py-5 bg-gray-100 border-b-2 border-gray-300 text-center">Wednesday Mar 4 - Day 0</h2>
  {% assign rooms = day0_times[0].rooms | sort: "name" %}
    <div class="timeline-slot mb-5" data-time="{{ slot.slotStart }}" data-day="day0">
        <div class="time-header bg-black text-white px-4 py-2.5 font-bold rounded-t-md">
            <span>Workshops 9.00 am - 5.00 pm *** Workshop Tickets Required ***</span>
        </div>
        <div class="border border-gray-300 border-t-0 rounded-b-md p-4">
            {% for e in rooms %} 
                <div class="session-card mb-4">
                    {% include schedule_event.html details=e.session %}
                </div>
            {% endfor %}
        </div>
    </div>
    {% assign networking = day0_times[2].rooms | sort: "name" %}
    <div class="timeline-slot mb-5"  data-day="day0">
        <div class="time-header bg-black text-white px-4 py-2.5 font-bold rounded-t-md">
            <span>{{day0_times[2].slotStart}}</span>
        </div>
        <div class="border border-gray-300 border-t-0 rounded-b-md p-4">
            {% for e in networking %} 
                <div class="session-card mb-4">
                    {% include schedule_event.html details=e.session %}
                </div>
            {% endfor %}
        </div>
    </div>
</section>

{% assign day1 = site.data.schedule[1] %}
{% assign day1_times = day1.timeSlots | sort: "slotStart" %}
<section class="day-content day1" id="day1">
  <h2 class="text-2xl font-bold px-4 py-5 bg-gray-100 border-b-2 border-gray-300 text-center">Thursday Mar 5 - Day 1</h2>
  {% for slot in day1_times %}
  {% assign rooms = slot.rooms | sort: "name" %}
    <div class="timeline-slot mb-5" data-time="{{ slot.slotStart }}" data-day="day1">
        <div class="time-header bg-black text-white px-4 py-2.5 font-bold rounded-t-md">
            <span>{{ slot.slotStart }}</span>
        </div>
        <div class="border border-gray-300 border-t-0 rounded-b-md p-4">
            {% for e in rooms %} 
                <div class="session-card mb-4">
                    {% include schedule_event.html details=e.session %}
                </div>
            {% endfor %}
        </div>
    </div>
  {% endfor %}
</section>

{% assign day2 = site.data.schedule[2] %}
{% assign day2_times = day2.timeSlots | sort: "slotStart" %}
<section class="day-content day1" id="day1">
  <h2 class="text-2xl font-bold px-4 py-5 bg-gray-100 border-b-2 border-gray-300 text-center">Friday Mar 5 - Day 2</h2>
  {% for slot in day2_times %}
  {% assign rooms = slot.rooms | sort: "name" %}
    <div class="timeline-slot mb-5" data-time="{{ slot.slotStart }}" data-day="day1">
        <div class="time-header bg-black text-white px-4 py-2.5 font-bold rounded-t-md">
            <span>{{ slot.slotStart }}</span>
        </div>
        <div class="border border-gray-300 border-t-0 rounded-b-md p-4">
            {% for e in rooms %} 
                <div class="session-card mb-4">
                    {% include schedule_event.html details=e.session %}
                </div>
            {% endfor %}
        </div>
    </div>
  {% endfor %}
</section>
