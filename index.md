---
layout: home
dates: Feb 17-19 2021
registration:
  text: OPENS JUL 1st
  status: disabled
  link:
branding:
  img: /assets/img/dev-nexus-logo-large.png
  alt: Devnexus
---

{% if site.cfp-is-open %}
  <div class="featured-header">
    <h1 class="top-intro"><a href="/call-for-papers">Call For Papers Is Open</a></h1>
  </div>
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

