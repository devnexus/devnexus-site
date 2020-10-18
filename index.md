---
layout: home
dates: Feb 17 2021
registration:
  text: Register here for Free
  status: enabled
  link: href="https://www.accelevents.com/e/devnexus#Agenda" 
branding:
  img: /assets/img/dev-nexus-logo-large.png
  alt: Devnexus
cfp:
  announce: false
  view: open 
---

{% if page.cfp.announce %}
{% case page.cfp.view %}
  {% when "open" %}
  <div class="featured-header">
    <h1 class="top-intro"><a href="/call-for-papers">CLICK HERE TO SUBMIT TO THE CALL FOR PAPERS (CLOSES OCT 1)</a></h1>
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
 {% include workshops_promo.html %} 
{% endcomment %} 

<div class="row">
<a name="sponsorlist"></a>
      <div class="featured-header">
        <h1>Thank You  2021 Sponsors</h1>
        <a class="action-header" href="https://ajug.typeform.com/to/BTa7bZ">Interested in Sponsoring Devnexus 2021?</a>
      </div>
{% include sponsor-listing.md rendering="sponsor-thumb.html" %}
</div>
<div>
<a name="timeline"></a>
{% include timeline.html %}
</div>

