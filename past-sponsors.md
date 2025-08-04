---
layout: default
---
<div class="mt-24 mb-4">
{% include buttons/become-a-sponsor.html %}
</div>

<main class="row">

{% for sponsorLevel in site.data.lastyearlevels%}
{% assign sponsorsInLevel = site.data.lastyearsponsors | where:'sponsorlevel', sponsorLevel.category %}
{% assign total = sponsorsInLevel | size %}
{% unless total == 0 %}
{% include sponsor-thumb.html sponsors=sponsorsInLevel level-name=sponsorLevel.title level-tag=sponsorLevel.category %}
{% endunless %}
{% endfor %}
</main>

<div class="mt-4 mb-16">
{% include buttons/become-a-sponsor.html %}
</div>
