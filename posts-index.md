---
layout: default
title: Latest News
permalink: /posts/index.html
---

<div class="container mt-32">
    <div class="flex flex-col md:flex-row md:gap-8">
        <main class="mb-16 md:w-2/3">
            {% assign latest_post = site.posts.first %}
            <div class="latest-post">
                <h2 class="text-6xl"><a href="{{ latest_post.url }}">{{latest_post.title}}</a></h2>
                <p class="post-date">{{ latest_post.date | date: "%B %d, %Y" }}</p>
                <div class="post-content prose">
                    {{ latest_post.content }}
                </div>
            </div>
        </main>    
        <aside class="md:w-1/3 recent-posts">
            {% for post in site.posts offset:1 limit:5 %}
            <div class="mb-6 post-thumbnail text-blue-500">
                <a href="{{ post.url }}">
                    {% if post.thumbnail %}
                    <img src="{{ post.thumbnail }}" alt="{{ post.title }}" class="img-thumbnail">
                    {% elsif post.icon %}   
                     <span class="articons {{ post.icon }}"></span>
                    {% else %}
                    {{ post.track }}
                    {% endif %}
                    <h4 class="text-2xl">{{ post.title }}</h4>
                </a>
                <p class="post-date">{{ post.date | date: "%B %d, %Y" }}</p>
            </div>
            {% endfor %}
        </aside>
    </div>
</div>