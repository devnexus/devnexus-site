---
layout: home
branding:
  img: /assets/img/dev-nexus-logo-large.png
  alt: Devnexus 2025
---

<div class="row"><a name="sponsorlist"></a><div class="featured-header"><a class="action-header" href="https://ajug.typeform.com/to/BTa7bZ">Interested in Sponsoring Devnexus 2025?</a></div> 

{% for sponsorLevel in site.data.lastyearlevels%}
{% assign sponsorsInLevel = site.data.lastyearsponsors | where:'sponsorlevel', sponsorLevel.category %}
{% assign total = sponsorsInLevel | size %}
{% unless total == 0 %}
{% include sponsor-thumb.html sponsors=sponsorsInLevel level-name=sponsorLevel.title level-tag=sponsorLevel.category %}
{% endunless %}
{% endfor %}
</div>

<div><a name="timeline"></a> {% include timeline.html %}</div>
