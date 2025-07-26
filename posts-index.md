---
layout: default
title: Latest News
permalink: /posts/index.html
---

<div class="container mt-32">
    <div class="col-md-8">
        <main>
            {% assign latest_post = site.posts.first %}
            <div class="latest-post">
                <h2><a href="{{ latest_post.url }}">{{latest_post.title}}</a></h2>
                <p class="post-date">{{ latest_post.date | date: "%B %d, %Y" }}</p>
                <div class="post-content">
                    {{ latest_post.content }}
                </div>
            </div>
        </main>    
        <div class="row recent-posts">
                {% for post in site.posts offset:1 limit:5 %}
                <div class="col-md-4 post-thumbnail">
                    <a href="{{ post.url }}">
                        {% if post.thumbnail %}
                        <img src="{{ post.thumbnail }}" alt="{{ post.title }}" class="img-thumbnail">
                        {% elsif post.icon %}   
                         <span class="articons {{ post.icon }}"></span>
                        {% else %}
                        {{ post.track }}
                        {% endif %}
                        <h4>{{ post.title }}</h4>
                    </a>
                    <p class="post-date">{{ post.date | date: "%B %d, %Y" }}</p>
                </div>
                {% endfor %}
        </div>
    </div>
    <sidebar class="col-md-4 sidebar">
                <h3 class="text-xl font-bold mb-4">Posts by Date</h3>
                
                <div class="post-index space-y-2">
                    {% assign postsByYear = site.posts | group_by_exp:"post", "post.date | date: '%Y'" %}
                    {% assign currentYear = 'now' | date: '%Y' %}
                    
                    {% for year in postsByYear %}
                        <div class="border border-gray-200 rounded">
                            <button class="flex justify-between items-center w-full px-4 py-2 text-left font-medium bg-gray-100 hover:bg-gray-200 focus:outline-none"
                                    onclick="toggleAccordion('year-{{ year.name }}')"
                                    aria-expanded="{{ year.name == currentYear }}">
                                <span>{{ year.name }}</span>
                                <svg id="icon-{{ year.name }}" class="w-5 h-5 transform {% if year.name == currentYear %}rotate-180{% endif %}" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 9l-7 7-7-7"></path>
                                </svg>
                            </button>
                            
                            <div id="year-{{ year.name }}" class="{% if year.name != currentYear %}hidden{% endif %} px-4 py-2">
                                {% assign postsByMonth = year.items | group_by_exp:"post", "post.date | date: '%B'" %}
                                {% for month in postsByMonth %}
                                    <div class="mb-2">
                                        <button class="flex justify-between items-center w-full px-3 py-1 text-left font-medium text-sm bg-gray-50 hover:bg-gray-100 focus:outline-none rounded"
                                                onclick="toggleAccordion('month-{{ year.name }}-{{ month.name }}')"
                                                aria-expanded="false">
                                            <span>{{ month.name }}</span>
                                            <svg class="w-4 h-4 transform" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 9l-7 7-7-7"></path>
                                            </svg>
                                        </button>
                                        
                                        <div id="month-{{ year.name }}-{{ month.name }}" class="hidden pl-4 py-1 space-y-1">
                                            {% for post in month.items %}
                                                <div class="text-sm">
                                                    <a href="{{ post.url }}" class="hover:text-blue-600">{{ post.title }}</a>
                                                </div>
                                            {% endfor %}
                                        </div>
                                    </div>
                                {% endfor %}
                            </div>
                        </div>
                    {% endfor %}
                
                <script>
                    function toggleAccordion(id) {
                        const element = document.getElementById(id);
                        element.classList.toggle('hidden');
                        
                        if (id.startsWith('year-')) {
                            const iconId = 'icon-' + id.replace('year-', '');
                            document.getElementById(iconId).classList.toggle('rotate-180');
                        }
                    }
                </script>
            </div>
     </sidebar>           
</div>