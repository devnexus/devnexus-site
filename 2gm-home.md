---
layout: home
dates: March 6-8 2019
title: Devnexus-2GM 2019
excerpt: Special Devnexus track on learning to use Groovy, Grails, Micronaut
branding:
  img: /assets/img/2gm/devnexus-2gm-logo-large.png
  alt: Devnexus-2GM
partner:
    img: /assets/img/dev-nexus-logo-large.png
    alt: DevNexus
    home: /
registration:
    text: Register Here
    status: enabled
---
{% include {{ site.active-header }} %}

{% assign events = site.events | where:"track", "2gm" | sort: "title"%}
<div class="row">
<div class="xs-offset-1">
<h1 class="featured-header">SESSIONS</h1>
{% include event_thumb_speaker_background.html collection=events %}
</div>
</div>
</div>
