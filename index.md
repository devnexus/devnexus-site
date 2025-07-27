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
              {% include post-home-thumbnail.html post=post %}
        {% endfor %}
        </div>
    </section>
    {% assign sponsors_by_level = site.data.sponsors | group_by: "sponsorlevel" %}
    <section class="container mx-auto py-12 px-4 sm:px-6 lg:px-8">
      <div class="grid grid-cols-3 md:grid-cols-6 gap-8 mb-12">
        {% for level in sponsors_by_level %}
          <h4 class="col-span-3 md:col-span-6">Level: {{level.name}}</h4>
          {% for item in level.items %}
            <a href="{{item.home_path_url}}"><img class="img-responsive" src="{{item.logo_image}}" alt="{{item.name}}" title="{{item.name}}"></a>
          {% endfor %}
        {% endfor %}
      </div>
    </section>    
</div>