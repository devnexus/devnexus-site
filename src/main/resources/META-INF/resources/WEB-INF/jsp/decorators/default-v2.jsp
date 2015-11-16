<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/includes/taglibs-decorators.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width" />

	<meta property="og:title" content="DevNexus 2016">
	<meta property="og:type" content="company">
	<meta property="og:site_name" content="DevNexus">
	<meta property="og:url" content="http://devnexus.com/s/index">
	<meta property="og:image" content="">
	<meta property="og:image" content="">
	<meta content='devnexus, conference, tech conference, southeast, 2015, atlanta conference' name='keywords' />
	<meta property="og:description" content="">

	<title><sitemesh:write property='title'/></title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	<link rel="shortcut icon" href="${ctx}/favicon.png">

	<link rel="stylesheet" type="text/css" href="${ctx}/wro/all.css" />

<%-- 	<!-- vendor CSS -->
	<link href="${assetsUrl}/css/vendor/bootstrap.min.css" rel="stylesheet">
	<link href="${assetsUrl}/css/vendor/animate.css" rel="stylesheet">
	<link href="${assetsUrl}/css/vendor/font-awesome.css" rel="stylesheet">
 --%>

	<!-- fonts: external links -->
	<link href='http://fonts.googleapis.com/css?family=Quicksand' rel='stylesheet' type='text/css'>

	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	<script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
	<![endif]-->

	<sitemesh:write property='head'/>
	<sitemesh:write property='page.top'/>
</head>

<body>
	<c:url var="homeUrl" value="${baseSiteUrl}/index"/>
	<c:url var="speakersUrl" value="${baseSiteUrl}/speakers"/>
	<c:url var="presentationsUrl" value="${baseSiteUrl}/presentations"/>
	<c:url var="scheduleUrl" value="${baseSiteUrl}/schedule"/>
	<c:url var="organizersUrl" value="${baseSiteUrl}/organizers"/>
	<c:url var="aboutUrl" value="${baseSiteUrl}/about"/>
	<c:url var="socialUrl" value="${baseSiteUrl}/social"/>
	<c:url var="travelUrl" value="${baseSiteUrl}/travel"/>
	<c:url var="registrationUrl" value="${baseSiteUrl}/register-overview"/>
	<c:url var="pastConferencesUrl" value="${baseSiteUrl}/past-conferences"/>
	<c:url var="privacyPolicyUrl" value="${baseSiteUrl}/privacy-policy"/>
	<c:url var="codeOfConductUrl" value="${baseSiteUrl}/code-of-conduct"/>

	<!-- navigation -->
	<nav class="navbar navbar-custom navbar-fixed-top">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${homeUrl}"><img src="${assetsUrl}/img/DevNexus_logo_small.png" alt="DevNexus Logo"></a>
		</div>

		<div class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
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
				<li><a class="" href="${speakersUrl}">Speakers</a></li>
				<li><a class="" href="${scheduleUrl}">Schedule</a></li>
				<li><a class="" href="${registrationUrl}">Register Now!</a></li>
				<li><a class="page-scroll" href="${homeUrl}#travel">Travel</a></li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">More <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="${aboutUrl}">About</a></li>
						<li><a href="${organizersUrl}">Organizers</a></li>
						<li><a href="${socialUrl}">Social</a></li>
					</ul>
				</li>
			</ul>
		</div><!-- /.navbar-collapse -->
	</nav>

	<sitemesh:write property='body'/>

	<!-- footer -->
	<footer id="colophon" class="site-footer">
		<div class="footer-wrapper container">
			<div class="row">
				<div class="sidebar footer-sidebar clearfix">
					<div class="col-lg-3 col-sm-3 col-md-3 widget-1 footer-widget first footer-widget">
						<div id="meta" class="footer-widget">
							<img alt="DevNexus Logo" src="${assetsUrl}/img/DevNexus_logo_small.png">
							<ul class="footer-social">
								<li class=""><a href="https://twitter.com/devnexus" target="_blank"><i class="fa fa-twitter"></i>#devnexus</a></li>
							</ul>
						</div>
					</div>
					<div class="col-lg-3 col-sm-3 col-md-3 widget-1 footer-widget footer-widget">
						<div id="tweet" class="footer-widget">
							<h3 class="footer-title">Learn More</h3>
							<ul class="footer-social">
								<li class=""><a href="${organizersUrl}" target="_blank">Organizers</a></li>
								<li class=""><a href="${pastConferencesUrl}">Past Conferences</a></li>
								<li class=""><a href="${ctx}/static/2016/files/promo/devnexus-2016-sponsorship-options.pdf" target="_blank">Sponsorship (PDF)</a></li>
							</ul>
						</div>
					</div>
					<div class="col-lg-3 col-sm-3 col-md-3 widget-1 footer-widget footer-widget">
						<div id="devnexus-links" class="footer-widget">
							<h3 class="footer-title">DEVNEXUS 2016</h3>
							<ul class="footer-social">
								<li class=""><a href="${scheduleUrl}" target="_blank">Schedule</a></li>
								<li class=""><a href="${speakersUrl}" target="_blank">Speakers</a></li>
								<li class=""><a href="${presentationsUrl}" target="_blank">Presentations</a></li>
							</ul>
						</div>
					</div>
					<div class="col-lg-3 col-sm-3 col-md-3 widget-1 footer-widget last footer-widget">
						<div id="get_touch" class="widget widget_get_touch">
							<a href="${registrationUrl}" class="btn btn-primary registerButton">Register Now!</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-md-10-offset-1 legal">
					<p>&copy; 2004-2015 <a href="http://www.ajug.org/">Atlanta Java Users Group</a> (AJUG)
						<a href="${privacyPolicyUrl}"><span class="label">Privacy Policy</span></a>
						<a href="${codeOfConductUrl}"><span class="label">Code of Conduct</span></a>
					</p>
				</div>
			</div>
		</div>
	</footer>
	<!-- /footer -->

	<!-- javascipt -->
	<script src="${ctx}/wro/all.js"></script>
<%-- 	<script src="${assetsUrl}/js/jquery1.11.1.min.js"></script>
	<script src="${assetsUrl}/js/jquery.modernizr.js"></script>
	<script src="${assetsUrl}/js/jquery.scrollTo.js"></script>
	<script src="${assetsUrl}/js/jquery.easing.min.js"></script>
	<script src="${assetsUrl}/js/bootstrap.min.js"></script>

	<script src="${assetsUrl}/js/other/masonry.pkgd.js"></script>
	<script src="${assetsUrl}/js/other/imagesloaded.pkgd.min.js"></script> --%>

	<sitemesh:write property='page.bottom'/>
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
</body>
</html>
