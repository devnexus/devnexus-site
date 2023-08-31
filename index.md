---
layout: home
dates: Apr 9-11, 2024
registration:
  text: SUPER EARLY BIRD SALE!
  status: enabled
  link: https://reg.connectevents.io/ConnectEvents/devnexus2024/
branding:
  img: /assets/img/dev-nexus-logo-large.png
  alt: Devnexus 2024
cfp:
  announce: false
  view: open
---
{% if page.cfp.announce %} {% case page.cfp.view %} {% when "open" %}

<div class="featured-header"><h1 class="top-intro"><a href="{{site.links.cfp}}">CLICK HERE TO SUBMIT TO THE CALL FOR PAPERS</a></h1></div>

{% else %}

<div class="featured-header"><h1 class="top-intro">Call For Papers Will Open</h1></div>

{% endcase %}<br>{% endif %}

{% include {{ site.active-header }} %}

{% include marketing-video.html %}

{% comment %} {% include keynotes_promo.html %} {% include workshops_promo.html %} {% endcomment %}

<div class="row"><a name="sponsorlist"></a><div class="featured-header"><a class="action-header" href="https://ajug.typeform.com/to/BTa7bZ">Interested in Sponsoring Devnexus 2024?</a></div> 

<div class="featured-header">2023 Devnexus Sponsors</div>

{% include sponsor-listing.md rendering="sponsor-thumb.html" %}
</div>

<div><a name="timeline"></a> {% include timeline.html %}</div>