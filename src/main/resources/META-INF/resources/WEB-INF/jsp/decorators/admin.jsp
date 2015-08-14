<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/includes/taglibs-decorators.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width" />

	<title><sitemesh:write property='title'/></title>

	<title><sitemesh:write property='title'/></title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	<link rel="shortcut icon" href="${ctx}/favicon.png">

	<!-- vendor CSS -->
	<link href="${assetsUrl}/css/vendor/bootstrap.min.css" rel="stylesheet">
	<link href="${assetsUrl}/css/vendor/animate.css" rel="stylesheet">
	<link href="${assetsUrl}/css/vendor/font-awesome.css" rel="stylesheet">

	<!-- fonts: external links -->
	<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
	<link href='http://fonts.googleapis.com/css?family=Quicksand' rel='stylesheet' type='text/css'>

	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	<script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
	<![endif]-->

	<sitemesh:write property='head'/>
	<sitemesh:write property='page.top'/>
</head>
<style>
    div#banner {
        padding-top: 20px;
    }
</style>
<body>

<c:url var="homeUrl" value="${baseSiteUrl}/index"/>
<c:url var="speakersUrl" value="${baseSiteUrl}/speakers"/>
<c:url var="presentationsUrl" value="${baseSiteUrl}/presentations"/>
<c:url var="scheduleUrl" value="${baseSiteUrl}/schedule"/>
<c:url var="organizersUrl" value="${baseSiteUrl}/organizers"/>
<c:url var="travelUrl" value="${baseSiteUrl}/travel"/>

	<!-- navigation -->
	<nav class="navbar navbar-custom navbar-fixed-top">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${homeUrl}"><img src="${assetsUrl}/img/DevNexus_logo_small.png"></a>
		</div>
		<div class="collapse navbar-collapse">
		<ul class="nav navbar-nav">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">Web-site&hellip;<b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="${speakersUrl}">Speakers</a></li>
						<li><a href="${presentationsUrl}">Presentations</a></li>
						<li><a href="${scheduleUrl}">Schedule</a></li>
					</ul>
				</li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">Past Conferences&hellip;<b class="caret"></b></a>
					<ul class="dropdown-menu">
						<c:forEach items="${eventsForMenu}" var="event">
							<li><a href="<c:url value='${baseSiteUrl}/${event.eventKey}/speakers'/>"><c:out value="${event.title}"/> Speakers</a></li>
							<li><a href="<c:url value='${baseSiteUrl}/${event.eventKey}/presentations'/>"><c:out value="${event.title}"/> Presentations</a></li>
						</c:forEach>
						<li>
							<a href="http://devnexus.com/static/2012/audio/">DevNexus 2012 All Audio Recordings</a>
						</li>
					</ul>
				</li><security:authorize access="hasRole('CFP_REVIEWER') or hasRole('ADMIN')"><li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">Admin&hellip;<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="<c:url value='${baseSiteUrl}/logout'/>" >Logout</a></li>
							<li><a href="<c:url value='${baseSiteUrl}/admin/index'/>">Admin Area</a></li>
						</ul>
					</li></security:authorize>
			</ul>
		</div><!-- /.navbar-collapse -->
	</nav>


	<sitemesh:write property='body'/>

	<!-- footer -->
	<footer id="colophon" class="site-footer" role="contentinfo">
		<div class="footer-wrapper container">
			<div class="row">
				<div class="sidebar footer-sidebar clearfix">
					<div class="col-lg-3 col-sm-3 col-md-3 widget-1 footer-widget first footer-widget">
						<div id="meta" class="footer-widget">
							<img src="${assetsUrl}/img/DevNexus_logo_small.png">
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
								<li class=""><a href="${ctx}/static/2015/files/promo/devnexus-2015-sponsorship-options.pdf"" target="_blank">Sponsorship (PDF)</a></li>
							</ul>
						</div>
					</div>
					<div class="col-lg-3 col-sm-3 col-md-3 widget-1 footer-widget footer-widget">
						<div id="tweet" class="footer-widget">
							<h3 class="footer-title">DEVNEXUS 2015</h3>
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
	<script src="${assetsUrl}/js/jquery1.11.1.min.js"></script>
	<script src="${assetsUrl}/js/jquery.modernizr.js"></script>
	<script src="${assetsUrl}/js/jquery.scrollTo.js"></script>
	<script src="${assetsUrl}/js/jquery.easing.min.js"></script>
	<script src="${assetsUrl}/js/bootstrap.min.js"></script>

	<script src="${assetsUrl}/js/other/masonry.pkgd.js"></script>
	<script src="${assetsUrl}/js/other/imagesloaded.pkgd.min.js"></script>

	<sitemesh:write property='page.bottom'/>
</body>
</html>
