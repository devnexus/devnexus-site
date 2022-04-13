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
               'keynote',
               'breakfast',
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
              'keynote']
  let trackFilters = []
  
  //Collect the checked filters
  $("input.form-check-input").each(function(t,e){
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
    
   //If there are no tracks at a timeslot, hide timeslot   
   let fineTimes = [
     'apr130730',
     'apr130900',
     'apr131000',
     'apr131100',
     'apr131120',
     'apr131220',
     'apr131320',
     'apr131420',
     'apr131520',
     'apr131540',
     'apr131650',
     'apr131700',
     'apr131800',
     'apr131820',
     'apr140900',
     'apr141000',
     'apr141100',
     'apr141120',
     'apr141220',
     'apr141320',
     'apr141420',
     'apr141520',
     'apr141540'
   ];

   fineTimes.forEach(fineTime => {
     let showTime = false;
     $('div.' + fineTime).show()
     tracks.forEach(track => {
        if (($('div.' + fineTime + ' div.' + track).is(":visible"))) {
          showTime = true;
        }
     })
     
     if (showTime) {
       $('div.' + fineTime).show()
     } else {
       $('div.' + fineTime).hide()
     }
   })
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
      <div >
        <input class="form-check-input " type="checkbox" id="keynote" value="keynote" onChange="applyFilter()">
        <label class="form-check-label" for="keynote">
          Keynotes
        </label>
        </div>
      <div >
        <input class="form-check-input " type="checkbox" id="unobtanium" value="unobtanium" onChange="applyFilter()">
        <label class="form-check-label" for="unobtanium">
          Red Hat DevNation Day
        </label>
      </div>
        {% assign tracks = site.data.tracks %}
        {% for track in tracks %}
            {% include schedule_filter_track_item.html data=track  %}
        {% endfor %}
        <div >
            <input class="form-check-input " type="checkbox" id="breakfast" value="breakfast" onChange="applyFilter()">
            <label class="form-check-label" for="breakfast">
              Food & Admin
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
        <h3>09:00</h3>
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
    {% assign misc = day1.events | where:"track","admin" | sort:"start" %}

    <div class="until09">
      {% assign keynotes = day1.events | where:"track","Keynote" %}
      <div class="apr130730">
        {% include schedule_break.html details=misc item=0 room="breakfast" %}
        {% include schedule_break.html details=misc item=1 room="open-java" %}
      </div>
      <div class="apr130900">
        {% include schedule_keynote.html details=keynotes item=0 room="keynote" %}
      </div>
    </div>
    <div class="until11">
      {% assign day1_10 = day1.events | where: "start", "10:00" %}
      <div class="apr131000">
        <h3>10:00</h3>
        {% include schedule_block.html events=day1_10 %}
      </div>
      <div class="apr131100">
        {% include schedule_break.html details=misc item=2 room="breakfast" %}
      </div>
    </div>
    <div class="until13">
      {% assign day1_11 = day1.events | where: "start", "11:20" %}
      <div class="apr131120">
        <h3>11:20</h3>
        {% include schedule_block.html events=day1_11 %}
      </div>
      <div class="apr131220">
        {% include schedule_break.html details=misc item=3 room="breakfast" %}
      </div>
    </div>

<div class="until15">
    
    {% assign day1_13 = day1.events | where: "start", "13:20" %}
    <div class="apr131320">
      <h3>13:20</h3>
      {% include schedule_block.html events=day1_13 %}
    </div>
    {% assign day1_14 = day1.events | where: "start", "14:20" %}
    <div class="apr131420">
    <h3>14:20</h3>
    {% include schedule_block.html events=day1_14 %}
    </div>
</div>
<div class="until20">
    <div class="apr131520">
      {% include schedule_break.html details=misc item=4 room="breakfast" %}
    </div>


    {% assign day1_15 = day1.events | where: "start", "15:40" %}
    <div class="apr131540">
    <h3>15:40</h3>
    {% include schedule_block.html events=day1_15 %}
    </div>
    <div class="apr131650">
      {% include schedule_keynote.html details=keynotes item=1 room="keynote" %}
    </div>
    <div class="apr131700">
      {% include schedule_keynote.html details=keynotes item=2 room="keynote" %}
    </div>
    <div class="apr131800">
      {% include schedule_break.html details=misc item=5 room="breakfast" %}
    </div>
    <div class="apr131820">
            {% assign jeypardy = day1.events | where: "id", "7148" %}
      {% include schedule_break.html details=jeypardy  item=0 room="web" %}
      {% assign offheap = day1.events | where: "start", "18:20" %}
      {% include schedule_break.html details=offheap item=0 room="frameworks" %}
    </div>
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
        <h2 class="day"> Thursday Apr 14 - Main Conference</h2>
      </div>
    </div>

    <div class="until09">
      {% assign day2 = site.data.schedule | where: "index", 2  | first %}
      {% assign keynotes2 = day2.events | where:"track","Keynote" %}
      <div class="apr140900">
        {% include schedule_keynote.html details=keynotes2 item=0 room="keynote" %}
      </div>
      {% assign misc2 = day2.events | where:"track","admin" | sort:"start" %}
    </div>

    <div class="until11">
    
      {% assign day2_10 = day2.events | where: "start", "10:00" %}
      <div class="apr141000">
      <h3>10:00</h3>
      {% include schedule_block.html events=day2_10 %}    
      </div>
      <div class="apr141100">
      {% include schedule_break.html details=misc2 item=0 room="breakfast" %}
      </div>
    </div>
    <div class="until13">
    {% assign day2_11 = day2.events | where: "start", "11:20" %}
    <div class="apr141120">
      <h3>11:20</h3>
      {% include schedule_block.html events=day2_11 %}
    </div>
    <div class="apr141220">
      {% include schedule_break.html details=misc2 item=1 room="breakfast" %}
    </div>
</div>
<div class="until15">
    {% assign day2_13 = day2.events | where: "start", "13:20" %}
    <div class="apr141320">
    <h3>13:20</h3>
    {% include schedule_block.html events=day2_13 %}
    </div>
    {% assign day2_14 = day2.events | where: "start", "14:20" %}
    <div class="apr141420">
    <h3>14:20</h3>
    {% include schedule_block.html events=day2_14 %}
    </div>
</div>
<div class="until20">
    <div class="apr141520">
      {% include schedule_break.html details=misc2 item=2 room="breakfast" %}
    </div>

    {% assign day2_15 = day2.events | where: "start", "15:40" %}
    <div class="apr141540">
      <h3>15:40</h3>
      {% include schedule_block.html events=day2_15 %}
    </div>
  
      <div class="apr141640">
        {% include schedule_keynote.html details=misc2 item=3 room="keynote" %}
      </div>
      <div class="apr141930">
      {% include schedule_keynote.html details=misc2 item=4 room="keynote" %}
      </div>
    </div>

  </div>
</div>
