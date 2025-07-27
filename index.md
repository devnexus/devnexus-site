---
layout: default
branding:
  img: /assets/img/dev-nexus-logo-large.png
  alt: Devnexus 2026    
---
<div>
{% include 2026-home-hero.html %}

    {% include video-cta.html %}

    <!-- Two Column Section: Track Details and Latest Posts -->
    <section class="max-w-7xl mx-auto w-full bg-white rounded-xl shadow-lg overflow-hidden md:flex">
        <!-- Left Column -->
         <div class="md:w-1/2 bg-indigo-50 flex items-center justify-center p-4">
            <div class="grid grid-cols-1 gap-4 p-4">
              {% for track in site.track-details%}
              <div class="bg-white rounded-lg shadow-md p-4 hover:shadow-lg transition-shadow">
                <h3 class="text-lg font-bold text-gray-900 mb-2">{{ track.title }}</h3>
                <p class="text-gray-700 mb-3">{{ track.excerpt }}</p>
              </div>
              {% endfor %}
            </div>    
        </div>

        <!-- Right Column - Image or Graphic -->
        <div class="md:w-1/2 bg-indigo-50 flex items-center justify-center p-4">
            <div class="grid grid-cols-1 gap-4 p-4">
              {% for post in site.posts limit:4 %}
              <div class="bg-white rounded-lg shadow-md p-4 hover:shadow-lg transition-shadow">
                <h3 class="text-lg font-bold text-gray-900 mb-2">{{ post.title }}</h3>
                <p class="text-sm text-gray-600 mb-3">{{ post.date | date: "%B %d, %Y" }}</p>
                <p class="text-gray-700 mb-3">{{ post.excerpt | strip_html | truncatewords: 25 }}</p>
                <a href="{{ post.url }}" class="text-indigo-600 font-medium hover:text-indigo-800">Read more →</a>
              </div>
              {% endfor %}
            </div>    
        </div>
      </section> <!-- Correctly closing the main section div -->  


    {% assign sponsors_by_level = site.data.sponsors | group_by: "sponsorlevel" %}
    <section class="container mx-auto py-12 px-4 sm:px-6 lg:px-8 justify-items-center">
      {% assign levels_order = "platinum,gold,silver,bronze,startup,community" | split: "," %}
        <div class="mb-12  ">
          {% for level_name in levels_order %}
            {% assign level = sponsors_by_level | where: "name", level_name | first %}
            {% if level and level.items.size > 0 %}
              <h4 class="text-6xl text-center bg-gray-200 py-4">{{level.name | capitalize }} Sponsors</h4>
              <div class="grid grid-cols-3 md:grid-cols-6 gap-8 items-justify-center">
              {% for item in level.items %}
              <a href="{{item.home_path_url}}"><img class="img-responsive" src="{{item.logo_image}}" alt="{{item.name}}" title="{{item.name}}"></a>
              {% endfor %}
              </div>
            {% endif %}
          {% endfor %}
        </div>
    </section>    
</div>