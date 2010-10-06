<div class="quote"><span>What the community says:</span> "Fantastic value, content vs expense is unmatched"</div>   
<h2>Speakers</h2>
    <%= image_tag "devnexus_2009/devnexus_2009_2.jpg", :class=> "page-image"  %>
    <ul>
        <% @speakers.each do |speaker| %>
          <li><a href="#<%=h speaker.first_name %>_<%=h speaker.last_name %>"    ><%=h speaker.first_name %> <%=h speaker.last_name %></a></li>
        <% end %>
    </ul>

    <h2>Speaker Biographies</h2>
    <% @speakers.each do |speaker| %>
    <div class="speaker">
        <h3 id="<%=h speaker.first_name %>_<%=h speaker.last_name %>"><%=h speaker.first_name %> <%=h speaker.last_name %></h3>
        <% if !speaker.picture.nil? %>
        <%= image_tag "/static/2010/images/speakers/" + speaker.picture %>
        <% end %>
        <p>
          <%=h speaker.bio %>
        </p>
        <% if !speaker.presentations.nil?%>
            <p class="presentation-header">
            <% if speaker.presentations.size == 1 %>
                Presentation:
            <% else %>
                Presentations:
            <% end %></p>
          <ul>
          <% speaker.presentations.each do |presentation| %>
             <li><%=h presentation.title %></li>
          <% end %>
          </ul>
        <% end %>
        <br style="clear: both;"/>
    </div>
    <% end %>
