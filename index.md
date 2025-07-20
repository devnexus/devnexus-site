---
layout: default
branding:
  img: /assets/img/dev-nexus-logo-large.png
  alt: Devnexus 2026
---
<div>
{% include 2026-home-hero.html %}

    {% include video-cta.html %}

    <!-- Scrolling Content Blocks -->
    <section class="container mx-auto py-12 px-4 sm:px-6 lg:px-8">

        <!-- Combined Section: Featured Blog Post & Twitter Highlights -->
        <div class="grid grid-cols-1 md:grid-cols-2 gap-8 mb-12">
        {% for post in site.posts limit:4 %}
            {{post}}
        {% endfor %}
        </div>
    </section>    
</div>