---
layout: home
dates: Apr 12-14 2022
registration:
  text: REGISTRATION OPEN!
  status: enabled
  link: href="https://reg.connectevents.io/ConnectEvents/devnexus2022/" 
branding:
  img: /assets/img/dev-nexus-logo-large.png
  alt: Devnexus 2022
cfp:
  announce: true
  view: open 
---

{% if page.cfp.announce %}
{% case page.cfp.view %}
  {% when "open" %}
  <div class="featured-header">
    <h1 class="top-intro"><a href="/call-for-papers">CLICK HERE TO SUBMIT TO THE CALL FOR PAPERS (CLOSES NOV 15, 2021)</a></h1>
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
        <h1>Thank You  2022 Sponsors</h1>
        <a class="action-header" href="https://ajug.typeform.com/to/BTa7bZ">Interested in Sponsoring Devnexus 2022?</a>
      </div>
{% include sponsor-listing.md rendering="sponsor-thumb.html" %}
</div>
<div>
<a name="timeline"></a>
{% include timeline.html %}
</div>

