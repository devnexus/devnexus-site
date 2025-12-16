---
layout: default
branding:
  img: /assets/img/dev-nexus-logo-large.png
  alt: Devnexus 2026    
---
<div>
{% include 2026-home-hero.html %}

    {% include video-cta.html %}   
    {% include speaker-logo-scroll.html %}  
    <!-- Two Column Section: Track Details and Latest Posts -->
      <section class="max-w-7xl mx-auto w-full bg-white rounded-xl shadow-lg overflow-hidden md:flex">
        <!-- Left Column - Track Details -->
         <div class="md:w-1/2 bg-indigo-50 p-4">
            <h2 class="text-lg md:text-4xl mb-4">Tracks</h2>
            {% assign tracks_by_theme = site.track-details | group_by: "theme" | sort: "name" %}
            {% for theme_group in tracks_by_theme %}
                  {% for track in theme_group.items %}
                     {{track}}
                  {% endfor %}
            {% endfor %}
          </div>  
          <!-- Right Column - Latest News -->
          <div class="md:w-1/2 bg-indigo-50 p-4">
            <h2 class="text-lg md:text-4xl mb-4">Latest News</h2>
            {% for post in site.posts limit:8 %}
            <div class="flex bg-white rounded-lg shadow-md p-4 hover:shadow-lg transition-shadow mb-4">
              <!-- Left column with thumbnail -->
              <div class="w-1/4 pr-4">
                {% if post.img %}
                  <img src="{{ post.img }}" alt="{{ post.title }}" class="w-full h-auto rounded">
                {% endif %}
              </div>
              
              <!-- Right column with post details -->
              <div class="w-3/4">
                <h3 class="text-lg font-bold text-gray-900 mb-2">{{ post.title }}</h3>
                <p class="text-sm text-gray-600 mb-2">{{ post.date | date: "%B %d, %Y" }}</p>
                <p class="text-gray-700 mb-2">{{ post.excerpt | strip_html | truncatewords: 30 }}</p>
                <a href="{{ post.url }}" class="text-indigo-600 font-medium hover:text-indigo-800">Read more →</a>
              </div>
            </div>    
            
          {% endfor %}   
        </div>
        </section> <!-- Correctly closing the main section div -->

    <section class="container mx-auto py-12 px-4 sm:px-6 lg:px-8 justify-items-center">
     <div class="mb-12">
      {% include sponsor-listing.html %}
     </div> 
    </section>    
</div>