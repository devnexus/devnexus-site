---
layout: home
dates: Feb 21-23 2018
registration:
  text: Tickets Available Now!
  status: enabled
  link:
facebook_target: atlantajug
twitter_target: devnexus
google_target: devnexus-conference
---


{% include navigation.html %}

{% include marketing-video.html %}

{% assign workshops = site.events %}
<div class="row">
    <h1 class="featured-header">WORKSHOPS</h1>
     <div class="speakers sessions">
     	{% for event in workshops %}
     	{% assign lead = event.persons[0] %}
        <div class="col-sm-6 col-md-4 col-lg-4">
             <div class="thumbnail">
                 <img class="img-responsive" src="https://cfp.devnexus.com{{ lead.avatar_path }}" alt="{{ event.title }}" >
                 <div class="caption">
                     <h3><a href="{{ event.url }}">{{ event.title | escape }}</a></h3>
                     <p>{{ lead.full_public_name | escape }}</p>
                </div>
             </div>
        </div>
        {% endfor %}           

    </div><!-- sessions -->
<!--
     <a href="presentations.html" class="btn btn-square btn-square btn-speakers center-block">SEE ALL SESSIONS</a> -->

</div>

<div class="row">
      <div class="row featured-header">
        <p>Sponsors</p>
      </div>
      {% include sponsor-listing.md rendering="sponsor-thumb.html" %}
</div>
