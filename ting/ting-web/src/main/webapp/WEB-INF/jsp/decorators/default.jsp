<%@include file="/WEB-INF/jsp/includes/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Devnexus 2011</title>

     <%= stylesheet_link_tag "main", "menu", :media => "all", :cache => true %>
     <%= javascript_include_tag "jquery-1.3.2.min.js" %>
     <%= javascript_include_tag "jquery.hoverIntent.minified.js" %>
     <%= javascript_include_tag "devnexus.js" %>

    <meta http-equiv="Cache-Control" content="no-store" />
    <meta http-equiv="Pragma"        content="no-cache" />
    <meta http-equiv="Expires"       content="0" />
    <meta http-equiv="content-type"  content="text/html; charset=utf-8" />

    <meta name="author"      content="Gunnar Hillert" />
    <meta name="keywords"    content="AJUG, Java, Conference, Atlanta, 2009, 2010, March" />
    <meta name="description" content="The professional developer conference of the Atlanta Java Users Group" />

    <link rel="icon"          href="/favicon.ico" type="image/x-icon" />
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
</head>

<body id="eventwax" class="ajug devcon-2009">
    <div id="b2">
        <div id="b3">
            <div id="b4">

                <div id="header">
                    <div id="h2">
                    <div id="h3">
                    <div id="h4">
                        <p><%= image_tag "logo_devnexus_600x150.png" %></p>
                    </div>
                    </div>
                    </div>
                </div>
                <div class="menubar">
                    <ul id="menu">
                        <li><%= link_to "Home",          :controller => "site", :action => "index" %></li>
                        <li><%= link_to "Speakers",      :controller => "site", :action => "speakers" %></li>
                        <li><%= link_to "Presentations", :controller => "site", :action => "presentations" %></li>
                        <li><%= link_to "Schedule",      :controller => "site", :action => "schedule" %></li>
                        <li class="mega">
                            <h2>
                              <a href="#">Past Conferences...</a>
                            </h2>
                            <div>
                                  <%= link_to "Devnexus 2009", "/2009/index.html"%><br/>
                                  <%= link_to "Devcon 2006",   "/2006/index.html"%><br/>
                                  <%= link_to "Devcon 2005",   "/2005/index.html"%><br/>
                                  <%= link_to "Devcon 2004",   "/2004/index.html"%><br/>
                            </div>
                        </li>
                        <li><%= link_to "Your Organizers", :controller => "site", :action => "organizers" %></li>
                        <li><a href="http://ajug.eventwax.com/devnexus-2010/register" style="color: #F7941E">SIGN UP!</a></li>
                    </ul>
                </div>
                <div id="content">
                    <div id="c2">
                        <div id="c3">
                            <div id="c4">
                                <% if current_user %>
                                  <div>
                                    <%= link_to "Admin Area",          :controller => "admin",  :action => "index" %> |
                                    <%= link_to "My Account", account_path %> |
                                    <%= link_to "Logout", user_session_path, :method => :delete, :confirm => "Are you sure you want to logout?" %>
                                  </div>
                                <% end %>
                                <decorator:body />
                            </div>
                        </div>
                    </div>
                </div>

                <div id="footer">&nbsp;<!--ads--></div>

                <div id="extraDiv1"><span></span></div>
                <div id="extraDiv2"><span></span></div>
                <div id="extraDiv3"><span></span></div>
                <div id="extraDiv4"><span></span></div>
                <div id="extraDiv5"><span></span></div>
                <div id="extraDiv6"><span></span></div>
            </div>
        </div>
    </div>

    <script type="text/javascript">
    var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
    document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
    </script>
    <script type="text/javascript">
    try {
    var pageTracker = _gat._getTracker("UA-177507-7");
    pageTracker._trackPageview();
    } catch(err) {}</script>
</body>
</html>