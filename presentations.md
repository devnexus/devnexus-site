---
layout: info-fluid
permalink: /presentations/index.html
tracks:
# keynote
# workshop
# unobtanium
java:
 tracks:
   - core-java
   - java-platform
   - open-java
cloud:
  tracks:
   - cloud-technology
   - cloud-infrastructure
jvm-frameworks:
  tracks:
   - jvm-languages
   - frameworks 
agile:
 tracks: 
   - agile
archictecture:
 tracks:          
  - archictecture
tools:
 tracks:  
  - tools
security:
 tracks:      
  - security
practices:
 tracks:
   - practices-other
web:
 tracks:
  - web
  - javascript      
---

{% assign keynotes = site.events | where: "track", "keynote" %}
{% if keynotes %}
<h1 class="featured-header"><span>— Keynotes —</span></h1>
<div class="row">
{% for keyevent in keynotes %}
 {% include presentation_thumb.html details=keyevent track="keynote" %}
{% endfor %}
</div>
{% endif %}

<h1 class="featured-header"><span>— Full Day Tracks —</span></h1>

<h1 class="featured-header"><span>JAVA</span></h1>
{% for track in page.java.tracks %}
{% assign track_data = site.data.tracks[track] %}
{% assign events = site.events | where:"track", track_data.trackkey | sort: 'title' %}
<div class="row">
{% for event in events %}
 {% include presentation_thumb.html details=event track="java" %}
{% endfor %}
</div>
{% endfor %}

<h1 class="featured-header"><span>CLOUD</span></h1>
{% for track in page.cloud.tracks %}
{% assign track_data = site.data.tracks[track] %}
{% assign events = site.events | where:"track", track_data.trackkey | sort: 'title' %}
<div class="row">
{% for event in events %}
 {% include presentation_thumb.html details=event track="cloud-technology" %}
{% endfor %}
</div>
{% endfor %}

<h1 class="featured-header"><span>JVM AND FRAMEWORKS </span><h1>
{% for track in page.jvm-frameworks.tracks %}
{% assign track_data = site.data.tracks[track] %}
{% assign events = site.events | where:"track", track_data.trackkey | sort: 'title' %}
<div class="row">
{% for event in events %}
 {% include presentation_thumb.html details=event track="frameworks" %}
{% endfor %}
</div>
{% endfor %}


<h1 class="featured-header"><span>— Half Day Tracks —</span></h1>

<h1 class="featured-header"><span>AGILE </span><h1>
{% for track in page.agile.tracks %}
{% assign track_data = site.data.tracks[track] %}
{% assign events = site.events | where:"track", track_data.trackkey | sort: 'title' %}
<div class="row">
{% for event in events %}
 {% include presentation_thumb.html details=event track="agile" %}
{% endfor %}
</div>
{% endfor %}

<h1 class="featured-header"><span>ARCHITECTURE</span><h1>
{% for track in page.archictecture.tracks %}
{% assign track_data = site.data.tracks[track] %}
{% assign events = site.events | where:"track", track_data.trackkey | sort: 'title' %}
<div class="row">
{% for event in events %}
 {% include presentation_thumb.html details=event track="archictecture" %}
{% endfor %}
</div>
{% endfor %}

<h1 class="featured-header"><span>PRACTICES </span><h1>
{% for track in page.practices.tracks %}
{% assign track_data = site.data.tracks[track] %}
{% assign events = site.events | where:"track", track_data.trackkey | sort: 'title' %}
<div class="row">
{% for event in events %}
 {% include presentation_thumb.html details=event track="practices-other" %}
{% endfor %}
</div>
{% endfor %}

<h1 class="featured-header"><span>SECURITY </span><h1>
{% for track in page.security.tracks %}
{% assign track_data = site.data.tracks[track] %}
{% assign events = site.events | where:"track", track_data.trackkey | sort: 'title' %}
<div class="row">
{% for event in events %}
 {% include presentation_thumb.html details=event track="security" %}
{% endfor %}
</div>
{% endfor %}

<h1 class="featured-header"><span>TOOLS </span><h1>
{% for track in page.tools.tracks %}
{% assign track_data = site.data.tracks[track] %}
{% assign events = site.events | where:"track", track_data.trackkey | sort: 'title' %}
<div class="row">
{% for event in events %}
 {% include presentation_thumb.html details=event track="tools" %}
{% endfor %}
</div>
{% endfor %}

<h1 class="featured-header"><span>WEB AND JAVASCRIPT </span><h1>
{% for track in page.web.tracks %}
{% assign track_data = site.data.tracks[track] %}
{% assign events = site.events | where:"track", track_data.trackkey | sort: 'title' %}
<div class="row">
{% for event in events %}
 {% include presentation_thumb.html details=event track="web" %}
{% endfor %}
</div>
{% endfor %}
