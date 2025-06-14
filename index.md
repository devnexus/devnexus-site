---
layout: home
branding:
  img: /assets/img/dev-nexus-logo-large.png
  alt: Devnexus 2026
---
{% if site.cfp-is-open %}
<div class="featured-header"><h1 class="top-intro"><a href="{{site.links.cfp}}">CALL FOR PAPERS IS OPEN</a></h1></div>
{% endif %}

{% include {{ site.active-header }} %}

{% include marketing-video.html %}

{% comment %}
   {% include keynotes_promo.html %} {% comment %} {% include workshops_promo.html %} {% endcomment %}
{% endcomment %}

<div class="row"><a name="sponsorlist"></a><div class="featured-header"><a class="action-header" href="https://ajug.typeform.com/to/BTa7bZ">Interested in Sponsoring Devnexus 2026?</a></div> 

{% include sponsor-listing.md rendering="sponsor-thumb.html" %}
</div>
{% comment %}
<div><a name="timeline"></a> {% include timeline.html %}</div>
{% endcomment %}