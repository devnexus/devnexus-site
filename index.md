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
     {% include event_thumb_background_face.html collection=workshops %}         

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
