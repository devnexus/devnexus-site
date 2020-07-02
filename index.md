---
layout: home
dates: Feb 17-19 2021
registration:
  text: Registration Opens July 13 2020
  status: disabled
  link:
branding:
  img: /assets/img/dev-nexus-logo-large.png
  alt: Devnexus
cfp:
  announce: true
  view: future 
---

{% if page.cfp.announce %}
{% case page.cfp.view %}
  {% when "open" %}
  <div class="featured-header">
    <h1 class="top-intro"><a href="/call-for-papers">Call For Papers Is Open</a></h1>
  </div>
  {% else %}
  <div class="featured-header">
    <h1 class="top-intro"><a href="/call-for-papers">Call For Papers Opens JUL 1 2020</a></h1>
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
        <h1>Thank you 2020 Sponsors</h1>
        <a class="action-header" href="https://ajug.typeform.com/to/BTa7bZ">Interested in Sponsoring Devnexus 2021?</a>
      </div>
{% include sponsor-listing.md rendering="sponsor-thumb.html" %}
</div>
<div>
<a name="timeline"></a>
{% include timeline.html %}
</div>

