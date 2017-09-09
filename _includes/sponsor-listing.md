{% for sponsorLevel in site.data.sponsorlevels %}
{% assign sponsorsInLevel = site.sponsors | where:"level", sponsorLevel.category %}
{% include {{ include.rendering }} sponsors=sponsorsInLevel level-name=sponsorLevel.title %}
{% endfor %}
