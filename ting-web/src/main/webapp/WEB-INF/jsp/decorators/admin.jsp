<%@include file="/WEB-INF/jsp/includes/taglibs-decorators.jsp"%>
<!doctype html>
<!-- paulirish.com/2008/conditional-stylesheets-vs-css-hacks-answer-neither/ -->
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8" lang="en"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9" lang="en"> <![endif]-->
<!-- Consider adding a manifest.appcache: h5bp.com/d/Offline -->
<!--[if gt IE 8]><!--> <html class="no-js" lang="en"> <!--<![endif]-->
<head>
  <meta charset="utf-8">

  <!-- Use the .htaccess and remove these lines to avoid edge case issues.
       More info: h5bp.com/b/378 -->
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

  <title><sitemesh:write property='title' default="ADMIN"/></title>
  <meta name="description" content="The professional developer conference of the Atlanta Java Users Group">
  <meta name="author" content="Gunnar Hillert">

  <!-- Mobile viewport optimized: h5bp.com/viewport -->
  <meta name="viewport" content="width=device-width,initial-scale=1">

  <!-- Place favicon.ico and apple-touch-icon.png in the root directory: mathiasbynens.be/notes/touch-icons -->

  <jwr:style src="/bundles/screen.css" media="screen, projection"/>
  <jwr:style src="/bundles/jmesa.css"/>

    <!--[if lt IE 9]>
        <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

  <!-- JavaScript -->
  <jwr:script src="/bundles/header.js"/>
  <jwr:script src="/bundles/lib.js"/>
  <jwr:script src="/bundles/jmesa.js"/>

  <!-- For iPhone 4 -->
  <link rel="apple-touch-icon-precomposed" sizes="114x114" href="${ctx}/apple-touch-icon.png">
  <!-- For iPad 1-->
  <link rel="apple-touch-icon-precomposed" sizes="72x72" href="${ctx}/apple-touch-icon.png">
  <!-- For iPhone 3G, iPod Touch and Android -->
  <link rel="apple-touch-icon-precomposed" href="${ctx}/apple-touch-icon-precomposed.png">
  <!-- For Nokia -->
  <link rel="shortcut icon" href="${ctx}/apple-touch-icon.png">
  <!-- For everything else -->
  <link rel="shortcut icon" href="${ctx}/favicon.ico">

  <!--iOS. Delete if not required -->
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent">
  <link rel="apple-touch-startup-image" href="${ctx}/img/splash.png">

  <!--Microsoft. Delete if not required -->
  <meta http-equiv="cleartype" content="on">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

  <sitemesh:write property='head'/>
</head>

<body>

    <div class="container devnexus-logo">
        <header>
            <h1>2012</h1>
          <nav>
              <ul id="menu">
                <c:url var="homeUrl"          value="/"/>
                <c:url var="speakersUrl"      value="/s/speakers"/>
                <c:url var="presentationsUrl" value="/s/presentations"/>
                <c:url var="scheduleUrl"      value="/s/schedule"/>
                <c:url var="organizersUrl"    value="/s/organizers"/>
                <c:url var="travelUrl"        value="/s/travel"/>

                <li><a href="${homeUrl}"><span>Home</span></a></li>
                <li><a href="${speakersUrl}"><span>Speakers</span></a></li>
                <li><a href="${presentationsUrl}"><span>Presentations</span></a></li>
                <li><a href="${scheduleUrl}"><span>Schedule</span></a></li>
                <li class="mega">
                    <h2>
                      <a href="#">Past Conferences...</a>
                    </h2>
                    <div style="width: 450px; z-index: 20000;">
                        <table style="width: 400px;">
                            <c:forEach items="${eventsForMenu}" var="event">
                                <tr>
                                 <td><c:out value="${event.title}"/></td>
                                 <td><a href="<c:url value='/s/${event.eventKey}/speakers'/>">Speakers</a></td>
                                 <td><a href="<c:url value='/s/${event.eventKey}/presentations'/>">Presentations</a></td>
                              </tr>
                          </c:forEach>
                        </table>
                    </div>
                </li>
                <li><a href="${organizersUrl}"><span>Your Organizers</span></a></li>
                <li><a href="${travelUrl}"><span>Travel</span></a></li>
                <li><a href="https://ajug.eventwax.com/devnexus-2012/register" style="color: #F7941E"><span>Register Now!</span></a></li>

                <li><a class="icon-facebook" href="http://www.facebook.com/devnexus">&nbsp;<span>&nbsp;</span></a></li>
                <li><a class="icon-twitter"  href="http://twitter.com/devnexus">&nbsp;<span>&nbsp;</span></a></li>
                <li style="margin-right: 0;"><a href="${currentUrl}?site_preference=mobile"><span>Mobile</span></a></li>
              </ul>
            <security:authorize ifAnyGranted="ADMIN">
              <div style="text-align: right" class="span-22 append-bottom last">You are logged in as
                 <security:authentication property="principal.firstName"/> <security:authentication property="principal.lastName"/> (<security:authentication property="principal.email"/>) |
                 <a href="<c:url value='/s/logout'/>" >Logout</a>
                 <a href="<c:url value='/s/admin/index'/>">Admin Area</a>
              </div>
            </security:authorize>
            <c:if test="${not empty message}">
                <div id="message" class="${message.type} span-22 last">${message.text}</div>
            </c:if>
          </nav>
      </header>
      <div class="main clearfix" role="main">
            <sitemesh:write property='body'/>
      </div>
      <footer class="">
         &copy; 2008-2012 Atlanta Java Users Group (AJUG), powered by <a href="http://code.google.com/p/devnexus/">Ting</a> <spring:message code="ting.build.version"/>.<spring:message code="ting.build.number"/>
      </footer>
  </div>

  <!-- JavaScript at the bottom for fast page loading -->



  <!-- Asynchronous Google Analytics snippet. Change UA-XXXXX-X to be your site's ID.
       mathiasbynens.be/notes/async-analytics-snippet -->
  <script>
    var _gaq=[['_setAccount','UA-177507-7'],['_trackPageview']];
    (function(d,t){var g=d.createElement(t),s=d.getElementsByTagName(t)[0];
    g.src=('https:'==location.protocol?'//ssl':'//www')+'.google-analytics.com/ga.js';
    s.parentNode.insertBefore(g,s)}(document,'script'));
  </script>

  <!-- Prompt IE 6 users to install Chrome Frame. Remove this if you want to support IE 6.
       chromium.org/developers/how-tos/chrome-frame-getting-started -->
  <!--[if lt IE 7 ]>
    <script defer src="//ajax.googleapis.com/ajax/libs/chrome-frame/1.0.3/CFInstall.min.js"></script>
    <script defer>window.attachEvent('onload',function(){CFInstall.check({mode:'overlay'})})</script>
  <![endif]-->

    <script type="text/javascript">

    $(function() {
        $( "button, input:submit").button();
    });

    </script>

</body>
</html>
