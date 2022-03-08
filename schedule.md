---
layout: info-fluid
---

<script>

function applyFilter() {
  let filterDates = false;
  let filterTracks = false;
  let filterTimes = false;

  let dates = ['apr12', 'apr13','apr14']
  let dateFilters = []

  let times = ['until09',
               'until11',
               'until13',
               'until15',
               'until20'
              ];
  let timeFilters = []

  let tracks = ['unobtanium', 
               'agile',
              'architecture',
              'cloud-infrastructure',
              'cloud-technology',
              'tools-and-techniques',
              'core-java',
              'practices-and-other-tech',
              'frameworks',
              'java-platform',
              'security',
              'web-and-front-end',
              'admin','misc']
  let trackFilters = []
  
  //Collect the checked filters
  $("input.form-check-input").each(function(t,e){
    console.log(e.id +"&&"+ e.checked)
    if (e.id && e.checked) {
        if (dates.indexOf(e.id) >= 0) {
          dateFilters.push(e.id);
        } else if  (tracks.indexOf(e.id) >= 0) {
          trackFilters.push(e.id);
        } else if  (times.indexOf(e.id) >= 0) {
          timeFilters.push(e.id);
        } 
    } 
  });
    
    //Apply filters
    times.forEach(element => {
        if (timeFilters.length > 0 ) {
          if (timeFilters.indexOf(element) >=0) {
            $('div.' + element).show();
          } else {
            $('div.' + element).hide();
          }
        } else {
          $('div.' + element).show();
        }
      });

    
    //Apply filters
    tracks.forEach(element => {
        if (trackFilters.length > 0 ) {
          if (trackFilters.indexOf(element) >=0) {
            $('div.' + element).show();
          } else {
            $('div.' + element).hide();
          }
        } else {
          $('div.' + element).show();
        }
      });;
    
    
      dates.forEach(element => {
        if (dateFilters.length > 0 ) {
          if (dateFilters.indexOf(element) >=0) {
            $('div.' + element).show();
          } else {
            $('div.' + element).hide();
          }
        } else {
          $('div.' + element).show();
        }
      });
    
}

function resetFilters() {
  $("input.form-check-input").each(function(t,e){
    e.checked = false;
  });
  applyFilter();
}
</script>

{% assign day0 = site.data.schedule | where: "index", 0  | first %}
<div class="row schedule-container">
  <div class="col-sm-4 col-md-3 row schedule-filters">
    <a href="#schedule-filter" style="color: #eee" data-toggle="collapse">
      <div class="filter-by-header">
            <span>Filter By</span>
            <span class="glyphicon glyphicon-plus  pull-right btn-sm" aria-hidden="true"></span>
      </div>
    </a>
    <div id="schedule-filter" class="collapse in">
      <div class="row">
        <a href="#" onClick="resetFilters()">Clear Filters</a>
      </div>
      <a href="#date-filter" style="color: #eee" data-toggle="collapse">
        <div class="filter-by-section-header">
            <span>Date</span>
              <span class="glyphicon glyphicon-plus pull-right btn-sm" aria-hidden="true"></span> 
        </div>
      </a>
      <div id="date-filter" class="collapse in">
        <div >
          <input class="form-check-input " type="checkbox" id="apr12" value="apr12" onChange="applyFilter()">
            <label class="form-check-label" for="apr12">
              Apr 12 2022
            </label>
        </div>
        <div >
          <input class="form-check-input " type="checkbox" id="apr13" value="apr13" onChange="applyFilter()">
            <label class="form-check-label" for="apr13">
              Apr 13 2022
            </label>
        </div>
        <div >
          <input class="form-check-input " type="checkbox" id="apr14" value="apr14" onChange="applyFilter()">
            <label class="form-check-label" for="apr14">
              Apr 14 2022
            </label>
        </div>
      </div>
      <a href="#track-filter" style="color: #eee" data-toggle="collapse">
        <div class="filter-by-section-header">
            <span>Track</span>
            <span class="glyphicon glyphicon-plus pull-right btn-sm" aria-hidden="true"></span>
        </div>
      </a>
      <div id="track-filter" class="collapse in">
        {% assign tracks = site.data.tracks %}
        {% for track in tracks %}
            {% include schedule_filter_track_item.html data=track  %}
        {% endfor %}
        <div >
            <input class="form-check-input " type="checkbox" id="track-admin" value="admin" onChange="applyFilter()">
            <label class="form-check-label" for="track-admin">
              Misc
            </label>
        </div>
      </div>
      
      <a href="#time-filter" style="color: #eee" data-toggle="collapse">
        <div class="filter-by-section-header">
            <span>Time</span>
            <span class="glyphicon glyphicon-plus pull-right btn-sm" aria-hidden="true"></span>
        </div>
      </a>
      <div id="time-filter" class="collapse in">
        <div >
          <input class="form-check-input " type="checkbox" id="until09" value="until09" onChange="applyFilter()">
            <label class="form-check-label" for="until09">
              00:00-09:00
            </label>
        </div>
        <div >
          <input class="form-check-input " type="checkbox" id="until11" value="until11" onChange="applyFilter()">
            <label class="form-check-label" for="until11">
              09:00-11:00
            </label>
        </div>
        <div >
          <input class="form-check-input " type="checkbox" id="until13" value="until13" onChange="applyFilter()">
            <label class="form-check-label" for="until13">
              11:00-13:00
            </label>
        </div>
        <div >
          <input class="form-check-input " type="checkbox" id="until15" value="until15" onChange="applyFilter()">
            <label class="form-check-label" for="until15">
              13:00-15:00
            </label>
        </div>
        <div >
          <input class="form-check-input " type="checkbox" id="until20" value="until20" onChange="applyFilter()">
            <label class="form-check-label" for="until20">
              15:00-20:00
            </label>
        </div>
      </div><!--time filter collapse -->
      </div><!-- global filter collapse -->
  </div>
  
  <div class="row col-sm-8 col-md-9">
    <div class="row">
       <input class="form-control no-print" id="scheduleSearch" type="text" placeholder="Search..">
    </div>

    <div class="row new-day apr12">
      <div class="col-xs-12">
        <div class="col-xs-12">
          <div class="col-xs-12 box no-print pull-right" style="margin-top:1em">
            <div class="ribbon">
              <span><a href="/workshopinstructions">Get Ready</a></span>
            </div>
          </div>
          <h2 class="day "> Tuesday Apr 12 - Workshops </h2>
          <h3>*** Workshop ticket holders only. ***</h3>
        </div>  
        
      </div>
      <div class="until09">
        {% assign workshops = day0.events | where:"track","Full day Workshops" %}
        {% for event in workshops %}
          {% assign _room = site.data.cfp_rooms_to_gwwc[event.room]  %}
          {% include schedule_workshop.html details=event room=_room track="workshop" %}
        {% endfor %}
      </div>
    </div>

    <div class="row new-day apr13">
      <div class="col-xs-12">
        <div class="col-xs-12">          
          <div class="col-xs-12 box no-print pull-right" style="margin-top:1em">
            <div class="ribbon">
              <span><a href="/assets/img/conference_map.png">ROOM MAP</a></span>
            </div>
          </div>
          <h2 class="day"> Wednesday Apr 13 - Main Conference</h2>
        </div>
      </div>

    {% assign day1 = site.data.schedule | where: "index", 1  | first %}
    {% assign misc = day1.events | where:"track","admin"  %}

    <div class="until09">
      {% assign keynotes = day1.events | where:"track","Keynote" %}
      {% include schedule_break.html details=misc item=0 room="breakfast" %}
      {% include schedule_keynote.html details=keynotes item=0 room="keynote" %}
    </div>
    <div class="until11">
      {% assign day1_10 = day1.events | where: "start", "10:00" %}
      <h3>10:00</h3>
      {% include schedule_block.html events=day1_10 %}

      {% include schedule_break.html details=misc item=1 room="breakfast" %}
    </div>
    <div class="until13">
      {% assign day1_11 = day1.events | where: "start", "11:20" %}
      <h3>11:20</h3>
      {% include schedule_block.html events=day1_11 %}

      {% include schedule_break.html details=misc item=2 room="breakfast" %}
    </div>

<div class="until15">
    
    {% assign day1_13 = day1.events | where: "start", "13:20" %}
    <h3>13:20</h3>
    {% include schedule_block.html events=day1_13 %}

    {% assign day1_14 = day1.events | where: "start", "14:20" %}
    <h3>14:20</h3>
    {% include schedule_block.html events=day1_14 %}
</div>
<div class="until20">

    {% include schedule_break.html details=misc item=3 room="breakfast" %}



    {% assign day1_15 = day1.events | where: "start", "15:40" %}
    <h3>15:40</h3>
    {% include schedule_block.html events=day1_15 %}

    {% include schedule_keynote.html details=keynotes item=1 room="keynote" %}


        {% include schedule_keynote.html details=keynotes item=2 room="keynote" %}

        {% include schedule_break.html details=misc item=4 room="breakfast" %}


        {% assign offheap = day1.events | where: "start", "18:20" %}
        {% include schedule_break.html details=offheap item=0 room="security" %}
    </div>
    </div>
    <div class="row new-day apr14">
    <div class="col-xs-12">
      <div class="col-xs-12">
        <div class="col-xs-12 box no-print pull-right" style="margin-top:1em">
            <div class="ribbon">
              <span><a href="/assets/img/conference_map.png">ROOM MAP</a></span>
            </div>
          </div>
        <h2 class="day"> Wednesday Apr 14 - Main Conference</h2>
      </div>
    </div>

    <div class="until09">
      {% assign day2 = site.data.schedule | where: "index", 2  | first %}
      {% assign keynotes2 = day2.events | where:"track","Keynote" %}
      {% include schedule_keynote.html details=keynotes2 item=0 room="keynote" %}
      {% assign misc2 = day2.events | where:"track","admin" | sort:"start" %}
    </div>

    <div class="until11">
    
      {% assign day2_10 = day2.events | where: "start", "10:00" %}
      <h3>10:00</h3>
      {% include schedule_block.html events=day2_10 %}    
      {% include schedule_break.html details=misc2 item=0 room="breakfast" %}
    </div>
    <div class="until13">
    {% assign day2_11 = day2.events | where: "start", "11:20" %}
    <h3>11:20</h3>
    {% include schedule_block.html events=day2_11 %}

    {% include schedule_break.html details=misc2 item=1 room="breakfast" %}
</div>
<div class="until15">
    {% assign day2_13 = day2.events | where: "start", "13:20" %}
    <h3>13:20</h3>
    {% include schedule_block.html events=day2_13 %}

    {% assign day2_14 = day2.events | where: "start", "14:20" %}
    <h3>14:20</h3>
    {% include schedule_block.html events=day2_14 %}
</div>
<div class="until20">

    {% include schedule_break.html details=misc2 item=2 room="breakfast" %}


    {% assign day2_15 = day2.events | where: "start", "15:40" %}
    <h3>15:40</h3>
    {% include schedule_block.html events=day2_15 %}

        {% include schedule_keynote.html details=misc2 item=3 room="keynote" %}

        {% include schedule_keynote.html details=misc2 item=4 room="keynote" %}
</div>
  </div>
</div>

<!-- 
{% include schedule_break.html details=day2_other item=0 room="breakfast" %}

{% include schedule_break.html details=day2_other item=1 room="keynote" %}
{% include schedule_keynote.html details=day2_keynote item=0 room="keynote" %}
{% include schedule_break.html details=day2_other item=2 room="break" %}

{% assign day2_10 = day2.events | where: "start", "10:20" %}
<h3>10:20</h3>
{% include schedule_block.html events=day2_10 %}

{% assign day2_11= day2.events | where: "start", "11:20" %}
<h3>11:20</h3>
{% include schedule_block.html events=day2_11 %}

{% include schedule_break.html details=day2_other item=3 room="lunch" %}

{% assign day2_13= day2.events | where: "start", "13:10" %}
<h3>13:10</h3>
{% include schedule_block.html events=day2_13 %}

{% assign day2_14= day2.events | where: "start", "14:10" %}
<h3>14:10</h3>
{% include schedule_block.html events=day2_14 %}

{% include schedule_break.html details=day2_other item=4 room="break" %}

{% assign day2_15= day2.events | where: "start", "15:30" %}
<h3>15:30</h3>
{% include schedule_block.html events=day2_15 %}

{% include schedule_keynote.html details=day2_keynote item=1 room="keynote" %}
{% include schedule_keynote.html details=day2_keynote item=2 room="keynote" %}

<h3>17:40</h3>
{% assign offheap = site.events | where: "slug", 4810 | first %}
{% include schedule_event.html details=offheap track="off-heap" room="off-heap" %}

{% include schedule_break.html details=day2_other item=5 track="happy-hour" room="break" %}
</div>  -->