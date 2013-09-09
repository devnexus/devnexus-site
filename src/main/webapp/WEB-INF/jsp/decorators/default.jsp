<%@include file="/WEB-INF/jsp/includes/taglibs-decorators.jsp" %>
<!doctype html>
<!-- paulirish.com/2008/conditional-stylesheets-vs-css-hacks-answer-neither/ -->
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if IE 7]> <html class="no-js lt-ie9 lt-ie8" lang="en"> <![endif]-->
<!--[if IE 8]> <html class="no-js lt-ie9" lang="en"> <![endif]-->
<!-- Consider adding a manifest.appcache: h5bp.com/d/Offline manifest="${ctx}/s/appcache.manifest" -->
<!--[if gt IE 8]><!-->
<html class="no-js" lang="en"> <!--<![endif]-->
<head>
    <meta charset="utf-8">

    <!-- Use the .htaccess and remove these lines to avoid edge case issues.
         More info: h5bp.com/b/378 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <script src="${ctx}/js/header.js"></script>


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

    <title><sitemesh:write property='title' default="DevNexus 2013 Atlanta"/></title>
    <meta name="description" content="The professional developer conference of the Atlanta Java Users Group">
    <link href="${ctx}/images/favicon.ico" rel="shortcut icon" type="image/x-icon"/>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">

    <!-- Optional theme -->
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-theme.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>

    <link rel="stylesheet" type="text/css" href="${ctx}/css/global.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/navbar.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/jumbotron.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/footer.css"/>

    <sitemesh:write property='head'/>
</head>

<body>

<<<<<<< HEAD
    <div class="container devnexus-logo">
        <header>
            <h1>2014</h1>
          <nav>
              <ul id="menu">
                <c:url var="homeUrl"          value="${baseSiteUrl}/index"/>
                <c:url var="speakersUrl"      value="${baseSiteUrl}/speakers"/>
                <c:url var="presentationsUrl" value="${baseSiteUrl}/presentations"/>
                <c:url var="scheduleUrl"      value="${baseSiteUrl}/schedule"/>
                <c:url var="organizersUrl"    value="${baseSiteUrl}/organizers"/>
                <c:url var="travelUrl"        value="${baseSiteUrl}/travel"/>

                <li><a href="${homeUrl}"><span>Home</span></a></li>
                <li><a href="${speakersUrl}"><span>Speakers</span></a></li>
                <li><a href="${presentationsUrl}"><span>Presentations</span></a></li>
                <li><a href="${scheduleUrl}"><span>Schedule</span></a></li>
                <li class="mega">
                    <a href="#">Past Conferences...</a>
                    <div style="width: 450px; z-index: 20000;display: none">
                        <table style="width: 400px; margin-bottom: 1em;">
                            <c:forEach items="${eventsForMenu}" var="event">
                                <tr>
                                    <td><c:out value="${event.title}"/></td>
                                    <td><a href="<c:url value='${baseSiteUrl}/${event.eventKey}/speakers'/>">Speakers</a></td>
                                    <td><a href="<c:url value='${baseSiteUrl}/${event.eventKey}/presentations'/>">Presentations</a></td>
                                </tr>
                            </c:forEach>
                        </table>
                        <p>
                            DevNexus 2012 - <a href="http://devnexus.com/static/2012/audio/">All Audio Recordings</a>
                        </p>
                    </div>
                </li>
                <li><a href="https://ajug.eventwax.com/devnexus-2013/register">Register</a></li>
            </ul>
        </div>
        <!-- end top nav -->
        <div style="clear: both"></div>
        <div id="banner" class="jumbotron">
            <h1 id="gray">DevNexus 2014</h1>

            <h1 id="white"><c:out default="Atlanta, GA" value="${headerTitle}"/></h1>

            <h3><c:out default="The professional developer conference." value="${tag}"/></h3>
        </div>
        <!-- end banner -->
    </div>
    <!-- end headerwrapper -->
</div>
<!-- end header nav -->

<%--<div class="container devnexus-logo">--%>
<%--<header>--%>
<%--<h1>2013</h1>--%>
<%--<nav>--%>
<%--<ul id="menu">--%>

<%--<li><a href=""><span>Home</span></a></li>--%>
<%--<li><a href=""><span>Speakers</span></a></li>--%>
<%--<li><a href=""><span>Presentations</span></a></li>--%>
<%--<li><a href=""><span>Schedule</span></a></li>--%>

<%--<li><a href="${organizersUrl}"><span>Your Organizers</span></a></li>--%>
<%--<li><a href="${travelUrl}"><span>Travel</span></a></li>--%>
<%--<li><a href="" style="color: #F7941E"><span>Register</span></a></li>--%>

<%--<li><a class="icon-facebook" href="http://www.facebook.com/devnexus">&nbsp;<span>&nbsp;</span></a></li>--%>
<%--<li><a class="icon-twitter"  href="http://twitter.com/devnexus">&nbsp;<span>&nbsp;</span></a></li>--%>

<%--<c:choose>--%>
<%--<c:when test="${currentDevice.mobile}">--%>
<%--<li style="margin-right: 0;"><a href="${ctx}/s/index"><span>Mobile</span></a></li>--%>
<%--</c:when>--%>
<%--<c:otherwise>--%>
<%--<li style="margin-right: 0;"><a href="${ctx}/mobile/index"><span>Mobile</span></a></li>--%>
<%--</c:otherwise>--%>
<%--</c:choose>--%>
<%--</ul>--%>
<%--<security:authorize ifAnyGranted="ADMIN">--%>
<%--<div style="text-align: right" class="span-22 append-bottom last">You are logged in as--%>
<%--<security:authentication property="principal.firstName"/> <security:authentication property="principal.lastName"/> (<security:authentication property="principal.email"/>) |--%>
<%--<a href="<c:url value='${baseSiteUrl}/logout'/>" >Logout</a>--%>
<%--<a href="<c:url value='${baseSiteUrl}/admin/index'/>">Admin Area</a>--%>
<%--</div>--%>
<%--</security:authorize>--%>
<%--<c:if test="${not empty message}">--%>
<%--<div id="message" class="${message.type} span-22 last">${message.text}</div>--%>
<%--</c:if>--%>
<%--</nav>--%>
<%--</header>--%>
<sitemesh:write property='body'/>
<div style="clear:both;"></div>

<div id="footer">
    <div id="footerwrapper">
        <div class="cols">
            <h3>Learn More</h3>
            <ul id="col">
                <li><a href="#">About DN</a></li>
                <li><a href="#">Organizers</a></li>
                <li><a href="#">Past Conferences</a></li>
            </ul>
        </div> <!-- end cols -->
        <div class="cols">
            <h3>DevNexus 2014</h3>
            <ul id="col">
                <li><a href="#">Schedule</a></li>
                <li><a href="#">Speakers</a></li>
                <li><a href="#">Presentations</a></li>
            </ul>
        </div> <!-- end cols -->
        <div class="cols">
            <h3>Attend</h3>
            <ul id="col">
                <li><a href="#">Registration Info</a></li>
                <li><a href="#">Travel</a></li>
            </ul>
        </div> <!-- end cols -->
        <div class="cols">
            <h3>DevNexus 2014</h3>
            <ul id="col">
                <li><a href="#">Facebook</a></li>
                <li><a href="#">Twitter</a></li>
            </ul>
        </div> <!-- end cols -->
        <div id="flogo"><a href="#"><img src="images/devnexus-logo.jpg" border="0"></a></div>
        <div style="clear: both;"></div>
        <div id="copy">&copy; 2008-2013 Atlanta Java Users Group (AJUG), powered by
            <a href="https://github.com/devnexus/ting">Ting</a>
            <spring:message code="ting.build.version"/>.<spring:message code="ting.build.number"/>
            (<span id="network-status">Online</span>)
            <sitemesh:write property='page.bottom'/><div>
        </div> <!-- end footer wrapper -->
        </div> <!-- end footer --></div></div>


<div id="dialog-confirm" title="New Version Available" style="display: none;">
    <p>A new version of this site is available. Load it?</p>
</div>



<!-- Asynchronous Google Analytics snippet. Change UA-XXXXX-X to be your site's ID.
     mathiasbynens.be/notes/async-analytics-snippet -->
<script>
    var _gaq = [
        ['_setAccount', 'UA-177507-7'],
        ['_trackPageview']
    ];
    (function (d, t) {
        var g = d.createElement(t), s = d.getElementsByTagName(t)[0];
        g.src = ('https:' == location.protocol ? '//ssl' : '//www') + '.google-analytics.com/ga.js';
        s.parentNode.insertBefore(g, s)
    }(document, 'script'));
</script>

<!-- Prompt IE 6 users to install Chrome Frame. Remove this if you want to support IE 6.
     chromium.org/developers/how-tos/chrome-frame-getting-started -->
<!--[if lt IE 7 ]>
<script defer src="//ajax.googleapis.com/ajax/libs/chrome-frame/1.0.3/CFInstall.min.js"></script>
<script defer>window.attachEvent('onload', function () {
    CFInstall.check({mode: 'overlay'})
})</script>
<![endif]-->



</body>
</html>
