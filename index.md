---
layout: home
dates: Mar 4-6, 2025
registration:
  text: Opens Fall 2024
  status: closed
branding:
  img: /assets/img/dev-nexus-logo-large.png
  alt: Devnexus 2025
cfp:
  announce: true
---
{% if site.cfp-is-open %}
<div class="featured-header"><h1 class="top-intro"><a href="{{site.links.cfp}}">CALL FOR PAPERS IS CLOSED</a></h1></div>
{% endif %}

{% include {{ site.active-header }} %}

{% include marketing-video.html %}

{% include keynotes_promo.html %} {% comment %} {% include workshops_promo.html %} {% endcomment %}

<div class="row"><a name="sponsorlist"></a><div class="featured-header"><a class="action-header" href="https://ajug.typeform.com/to/BTa7bZ">Interested in Sponsoring Devnexus 2024?</a></div> 

<div class="featured-header">2024 Devnexus Sponsors</div>

{% include sponsor-listing.md rendering="sponsor-thumb.html" %}
</div>

<div><a name="timeline"></a> {% include timeline.html %}</div>
