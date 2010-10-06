<div class="quote"><span>What the community says:</span> "Great conference to share best practices"</div>  
<h2>Presentations</h2>
<%= image_tag "devnexus_2009/devnexus_2009_1.jpg", :class=> "page-image"  %>
<% @presentations.each do |presentation| %>
    <div class="presentation">
        <h3 class="title"><%=h presentation.title %></h3>
        <% if !presentation.speaker.nil? %>
            <p class="speaker"><%=h presentation.speaker.first_name + ' ' + presentation.speaker.last_name %></p>
        <% else %>
            <p class="speaker">TBD</p>
        <% end %>
        <div class="abstract">
            <%= simple_format(presentation.abstract) %>
        </div>
        <% if !presentation.presentation_link.nil? %>
            <p class="download"><%= link_to "Download Presentation", "/static/2010/slides/" + presentation.presentation_link %></p>
        <% else %>
            <p class="download">Slides not available, yet.</p>
        <% end %>
    </div>
<% end %>

