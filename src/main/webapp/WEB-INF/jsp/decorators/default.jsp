<%@include file="/WEB-INF/jsp/includes/taglibs-decorators.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title><sitemesh:write property='title'/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="${ctx}/favicon.ico">

    <!-- Bootstrap core CSS -->
    <link href="${ctx}/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${ctx}/css/devnexus.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <!--<script src="${ctx}/js/html5shiv.js"></script>-->
    <!--<script src="${ctx}/js/respond.min.js"></script>-->
    <![endif]-->
    <sitemesh:write property='head'/>
	<style>
		div#banner {
			padding-top: 20px;
		}
	</style>
</head>
<body>

<c:url var="homeUrl" value="${baseSiteUrl}/index"/>
<c:url var="speakersUrl" value="${baseSiteUrl}/speakers"/>
<c:url var="presentationsUrl" value="${baseSiteUrl}/presentations"/>
<c:url var="scheduleUrl" value="${baseSiteUrl}/schedule"/>
<c:url var="organizersUrl" value="${baseSiteUrl}/organizers"/>
<c:url var="travelUrl" value="${baseSiteUrl}/travel"/>
<c:url var="registrationUrl" value="${baseSiteUrl}/index"/>
<c:url var="devnexusLogoUrl" value="/images/devnexus-logo.jpg"/>
<c:url var="pastConferencesUrl" value="${baseSiteUrl}/past-conferences"/>

<div class="navbar navbar-inverse navbar-fixed-top" style="border:none">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${homeUrl}"><img id="logo" src="${ctx}/images/devnexus-logo.jpg"></a>
        </div>

		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li><a href="${speakersUrl}">Speakers</a></li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">Presentations <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="${presentationsUrl}?order=track">Presentations by Track</a></li>
						<li><a href="${presentationsUrl}?order=room">Presentations by Room</a></li>
						<li><a href="${presentationsUrl}?order=name">Presentations by Name</a></li>
						<li><a href="${ctx}/s/tracks">Show Tracks</a></li>
						<li><a href="${ctx}/s/tags">Show Presentation Tags</a></li>
						<li><a href="${ctx}/s/rooms">Show Rooms</a></li>
					</ul>
				</li>
				<li><a href="${scheduleUrl}">Schedule</a></li>
				<li><a href="${travelUrl}">Travel</a></li>
				<c:choose>
					<c:when test="${registrationState eq 'closed'}"><li><a>Registration Closed</a></li></c:when>
					<c:when test="${registrationState eq 'soldout'}"><li><a><strong style="color: #f7cb59;">Soon!</strong></a></li></c:when>
					<c:when test="${registrationState eq 'hide'}"></c:when>
					<c:otherwise>
						<li><a href="${registrationUrl}">Register</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	<!-- end top nav -->
    </div>
    <!-- end headerwrapper -->
</div>
<div style="clear: both"></div>
<sitemesh:write property='body'/>

<div style="clear:both;"></div>
<div id="devnex" class="jumbotron" style="margin-bottom:0">
    <div class="container">
        <h2>Questions? Contact us at info@ajug.org</h2>
    </div>
</div>

<div id="footer" class="footer-inverse">
    <div class="container">
        <div class="row">
            <div class="col-md-9">
                <div class="row">
                    <div class="col-md-3">
                        <h3>Learn More</h3>
                        <ul id="col">
                            <li><a href="${ctx}${baseSiteUrl}/index">About DN</a></li>
                            <li><a href="${organizersUrl}">Organizers</a></li>
                            <li><a href="${pastConferencesUrl}">Past Conferences</a></li>
                            <li><a href="${ctx}/static/2015/files/promo/devnexus-2015-sponsorship-options.pdf">Sponsorship
                                (Pdf)</a></li>
                        </ul>
                    </div>
                    <div class="col-md-3">
                        <h3>DevNexus 2015</h3>
                        <ul id="col">
                            <li><a href="${scheduleUrl}">Schedule</a></li>
                            <li><a href="${speakersUrl}">Speakers</a></li>
                            <li><a href="${presentationsUrl}">Presentations</a></li>
                        </ul>
                    </div>
                    <div class="col-md-3">
                        <h3>Attend</h3>
                        <ul id="col">
                            <li><a href="${registrationUrl}">Sold Out!</a></li>
                            <li><a href="${travelUrl}">Travel</a></li>
                        </ul>
                    </div>
                    <div class="col-md-3">
                        <h3>Stay Connected</h3>
                        <ul id="col">
                            <li><a href="<c:url value="${baseSiteUrl}/social"/>">Social</a></li>
                            <ul id="col">
                                <li><a href="https://facebook.com/devnexus">Facebook</a></li>
                                <li><a href="https://twitter.com/devnexus">Twitter</a></li>
                            </ul>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <img id="logo" src="${devnexusLogoUrl}" border="0">

                <p id="copy">&copy; 2008-2015 <a href="http://ajug.org">Atlanta Java Users Group (AJUG)</a></p>
            </div>
        </div>

    </div>
</div>
<script>
    (function (i, s, o, g, r, a, m) {
        i['GoogleAnalyticsObject'] = r;
        i[r] = i[r] || function () {
            (i[r].q = i[r].q || []).push(arguments)
        }, i[r].l = 1 * new Date();
        a = s.createElement(o),
                m = s.getElementsByTagName(o)[0];
        a.async = 1;
        a.src = g;
        m.parentNode.insertBefore(a, m)
    })(window, document, 'script', '//www.google-analytics.com/analytics.js', 'ga');

    ga('create', 'UA-44984422-1', 'devnexus.com');
    ga('send', 'pageview');

</script>
<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="${ctx}/js/jquery.js"></script>
<script src="${ctx}/js/bootstrap.min.js"></script>
<script src="${ctx}/js/masonry.pkgd.js"></script>
<script src="${ctx}/js/imagesloaded.pkgd.min.js"></script>

<sitemesh:write property='page.bottom'/>
</body>
</html>
