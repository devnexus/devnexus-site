---
layout: home
dates: Feb 21-23 2018
registration:
  text: Registration will open October 1 2017
  status: disabled
facebook_target: atlantajug
twitter_target: devnexus
google_target: devnexus-conference
---
<div class="navbar">
<h1 class="top-intro"><a href="cfp-details.html">Call For Papers Is Open</a></h1>
</div>
{% include marketing-video.html %}
<div class="row">
<div class="sponsors col-xs-12">
    <div class="row featured-header">
      <p>Sponsors</p>
    </div>
    {% for sponsorLevel in site.data.sponsorlevels %}
    {% assign sponsorsInLevel = site.sponsors | where:"level", sponsorLevel.category %}
    {% include sponsor-thumb.html sponsors=sponsorsInLevel level-name=sponsorLevel.title %}
    {% endfor %}
</div>
</div>
