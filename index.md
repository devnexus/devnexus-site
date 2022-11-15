---
layout: home
dates: Apr 4-6 2023
registration:
  text: REGISTER HERE
  status: enabled
  link: href="https://reg.connectevents.io/ConnectEvents/devnexus2023/" 
branding:
  img: /assets/img/dev-nexus-logo-large.png
  alt: Devnexus 2023
cfp:
  announce: true
  view: open 
---

{% if page.cfp.announce %}
{% case page.cfp.view %}
  {% when "open" %}
  <div class="featured-header">
    <h1 class="top-intro"><a href="/call-for-papers">CLICK HERE TO SUBMIT TO THE CALL FOR PAPERS (CLOSES NOV 22, 2022)</a></h1>
  </div>
  {% else %}
  <div class="featured-header">
    <h1 class="top-intro"><a href="/call-for-papers"></a>Call For Papers Will Open </h1>
  </div>
{% endcase %}  
{% endif %}

{% include {{ site.active-header }} %}

{% include marketing-video.html %}

{% comment %}
{% include keynotes_promo.html %} 
{% include workshops_promo.html %} 
{% endcomment %}

<div class="row">
<a name="sponsorlist"></a>
      <div class="featured-header">
        <a class="action-header" href="https://ajug.typeform.com/to/BTa7bZ">Interested in Sponsoring Devnexus 2023?</a>
      </div>
{% include sponsor-listing.md rendering="sponsor-thumb.html" %}
</div>
<div>
<a name="timeline"></a>
{% include timeline.html %}
</div>

