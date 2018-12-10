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

<section class="row">
<div class="col-10 col-xs-offset-1">
   <h2>Devnexus teams up with 2GM</h2>
   <p>2GM (GROOVY, GRAILS, AND MICRONAUT) TO BE FEATURED AT DEVNEXUS 2019</p>
   <div class="col-xs-4"><a href="https://grails.org/"><img class="img-responsive" alt="Grails" title="Grails" src="/assets/img/2gm/grails.png" /></a></div>
   <div class="col-xs-4"><a href="http://groovy-lang.org/"><img class="img-responsive" alt="Groovy" title="Groovy" src="/assets/img/2gm/groovy.png" /></a></div>
   <div class="col-xs-4"><a href="http://micronaut.io/"><img class="img-responsive" alt="Micronaut" title="Micronaut" src="/assets/img/2gm/micronaut.png" /></a></div>
   <p>For its 2019 conference, Devnexus has teamed up with the 2GM (Groovy, Grails, and Micronaut) communities, in furtherance of its mission, to promote and support open source values and technologies.</p>
</div>
</section>
<section class="row">
  <hr/>
  <div class="col-10 col-xs-offset-1">
    <h2>Google-Sponsored <em>Pre-Conference</em> Training Event</h2>
    <h1>MICRONAUT FROM IOT TO GCP</h1>
    <h2> Pre-Conference:  March 4-5, 2018   <a class="btn text-right" href="https://objectcomputing.com/resources/events/conferences/devnexus2019/2gm-pre-conference-training-micronaut-from-iot-to-gcp">ENROLL NOW</a></h2>
    <div>
    In this 2-day training experience, which takes place immediately prior to Devnexus 2019, you'll learn how to use Micronaut, a revolutionary new framework for building microservices and serverless applications, in concert with Google Cloud Platform services, such as Cloud SQL, Kubernetes, and Google’s Instance Metadata Server.
    </div>
    <h3>Enroll today to discover an easier way to build fast, lightweight JVM microservices and deploy them to Google Cloud.</h3>
    <div>
    <a class="btn col-xs-10 col-md-4 center-block" href="https://objectcomputing.com/resources/events/conferences/devnexus2019/2gm-pre-conference-training-micronaut-from-iot-to-gcp">ENROLL NOW</a>
    </div>
</div>    
</section>
{% assign events = site.events | where:"track", "2gm" | sort: "title"%}
<section class="row">
  <hr/>
  <div class="col-10 col-xs-offset-1">
  <h1 class="text-center"> DevNexus Sessions  March 6-8 </h1>
  </div>
  <div class="xs-offset-1">
    {% include event_thumb_speaker_background.html collection=events %}
    </div>
  </div>
</section>
