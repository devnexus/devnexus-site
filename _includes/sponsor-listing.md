{% for sponsorLevel in site.data.sponsorlevels %}
{% assign sponsorsInLevel = site.data.sponsors | where:'sponsorlevel', sponsorLevel.category %}
{% assign total = sponsorsInLevel | size %}
{% unless total == 0 %}
{% include {{ include.rendering }} sponsors=sponsorsInLevel level-name=sponsorLevel.title level-tag=sponsorLevel.category %}
{% endunless %}
{% endfor %}
