<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width" />

		<title>DevNexus 2016</title>

		<meta property="og:title" content="DevNexus 2016">
		<meta property="og:type" content="company">
		<meta property="og:site_name" content="DevNexus">
		<meta property="og:url" content="http://devnexus.com/s/index">
		<meta property="og:image" content="">
		<meta property="og:image" content="">
		<meta content='devnexus, conference, tech conference, southeast, 2016, atlanta conference' name='keywords' />
		<meta property="og:description" content="">

		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
		<meta name="author" content="">
		<link rel="shortcut icon" href="${ctx}/favicon.png">

		<!-- vendor CSS -->
		<link rel="stylesheet" type="text/css" href="${ctx}/wro/all.css" />

		<!-- fonts: external links -->
		<link href='http://fonts.googleapis.com/css?family=Quicksand' rel='stylesheet' type='text/css'>

		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
		<script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
		<![endif]-->

	</head>
<body>
	<c:url var="homeUrl" value="${baseSiteUrl}/index"/>
	<c:url var="speakersUrl" value="${baseSiteUrl}/speakers"/>
	<c:url var="presentationsUrl" value="${baseSiteUrl}/presentations"/>
	<c:url var="scheduleUrl" value="${baseSiteUrl}/schedule"/>
	<c:url var="organizersUrl" value="${baseSiteUrl}/organizers"/>
	<c:url var="registrationUrl" value="https://devnexus2015.eventbrite.com"/>
	<c:url var="pastConferencesUrl" value="${baseSiteUrl}/past-conferences"/>
	<c:url var="privacyPolicyUrl" value="${baseSiteUrl}/privacy-policy"/>
	<c:url var="codeOfConductUrl" value="${baseSiteUrl}/code-of-conduct"/>

	<!-- Navigation -->
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
				<li><a class="" href="${presentationsUrl}">Presentations</a></li>
				<li><a class="" href="${speakersUrl}">Speakers</a></li>
				<li><a class="" href="${scheduleUrl}">Schedule</a></li>
				<li><a class="page-scroll" href="#travel">Travel</a></li>
			</ul>
		</div>
	</nav>

	<!-- Intro Header -->
	<section id="index--intro" class="module parallax index--parallax-1">
		<div class="container header">
			<div class="row intro-body">
				<div class="col-md-8 col-md-offset-2">
					<img class="logo" src="${assetsUrl}/img/DevNexus_logo_large.png" alt="DevNexus Logo">
					<p class="intro-text">THE PROFESSIONAL DEVELOPERS CONFERENCE<br>ATLANTA, GA - FEBRUARY 15-17, 2016</p>
					<div class="icon">
						<i class="fa fa fa-twitter"></i>
					</div><!--//icon-->
					<p class="intro-text"><a href="https://twitter.com/devnexus">#devnexus</a></p>
				</div>
			</div>
		</div>
	</section>

	<!-- intro -->
	<section id="about" class="white">
		<div class="container">
				<div class="row centered">
					<div class="col-md-10 col-md-offset-1">
						<div id="tagline">Join the <span>&lt;dev/&gt;</span>olution.</div>
					</div>
					<div class="col-md-10 col-md-offset-1">
						<p class="lead">Welcome to the premier conference for professional software developers who want to hear from and interact directly with internationally acclaimed presenters and technologists. While youâre here, youâll also connect with like-minded developers who are mastering their craft in a wide range of todayâs most relevant technologies. Find out why DevNexus has sold out early for the last six years in a row, and why you canât afford to miss it!</p>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-6 col-sm-offset-3 text-center">
						<button disabled="disabled" class="btn btn-primary registerButton">Sold Out</button>
					</div>
				</div>
		</div>
	</section>
	<!-- /intro -->

	<!-- stats -->
	<section id="stats" class="module parallax index--parallax-2">
		<div class="container">
			<div class="row scrollpoint sp-effect3">
				<div class="item col-md-3 text-center">
					<div class="icon animated fadeInUp delayp1">
						<i class="fa fa-group"></i>
					</div><!--//icon-->
					<div class="content">
						<p class="lead counter-stat">1700</p>
						<p class="lead counter-commits">DEVELOPERS</p>
					</div><!--//content-->
				</div>
				<div class="item col-md-3 text-center">
					<div class="icon animated fadeInUp delayp1">
						<i class="fa fa-cogs"></i>
					</div><!--//icon-->
					<div class="content">
						<p class="lead counter-stat">6</p>
						<p class="lead counter-commits">WORKSHOPS</p>
					</div><!--//content-->
				</div>
				<div class="item col-md-3 text-center">
					<div class="icon animated fadeInUp delayp1">
						<i class="fa fa-arrows"></i>
					</div><!--//icon-->
					<div class="content">
						<p class="lead counter-stat">12</p>
						<p class="lead counter-commits">TRACKS</p>
					</div><!--//content-->
				</div>
				<div class="item col-md-3 text-center">
					<div class="icon animated fadeInUp delayp1">
						<i class="fa fa-university"></i>
					</div><!--//icon-->
					<div class="content">
						<p class="lead counter-stat">120</p>
						<p class="lead counter-commits">PRESENTATIONS</p>
					</div><!--//content-->
				</div>
			</div>
		</div>
	</section>

	<!-- featured -->
	<section id="featured" class="white">
		<div class="top-intro scissors">
			<h4 class="section-title decorated"><span>Featured Tracks</span></h4>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-md-4 col-sm-4">
					<div class="icon-box text-center">
					<img src="${assetsUrl}/img/chart.png" alt="Chart">
						<h3 class="lead">DATA + INTEGRATION</h3>
						<p>
						Whether your data is big or small, you need to make it
						âplay nicelyâ with everything else. Whether you SQL or you
						NoSQL, youâll find what you need here, along with the
						techniques and tools to make your data welcome wherever
						it goes!</p>
					</div>
				</div>
				<div class="col-md-4 col-sm-4">
					<div class="icon-box text-center">
					<img src="${assetsUrl}/img/java.png" alt="Cup of Java">
						<h3 class="lead">JAVA/JAVAEE/SPRING</h3>
						<p>
						We like the language, but we love the platform. Whatever
						your flavor of Java (SE, EE, Spring), whether you need to
						refactor or build new, weâll deliver the new features,
						tools and techniques you need to elevate your skills!</p>
					</div>
				</div>
				<div class="col-md-4 col-sm-4">
					<div class="icon-box text-center">
					<img src="${assetsUrl}/img/code.png" alt="Code">
						<h3 class="lead">HTML5 + JAVASCRIPT</h3>
						<p>Overwhelmed by the exploding numbers of .js frameworks
						in this soup du jour of modern web development? Need to
						understand how HTML5 brings native capabilities to the
						browser? Whatever you need, weâve got it here!</p>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4 col-sm-4">
					<div class="icon-box text-center">
					<img src="${assetsUrl}/img/chat.png" alt="Chat">
						<h3 class="lead">ATLERNATIVE LANGUAGES</h3>
						<p>
						Guess what? Java isnât the only language to run on the
						JVMâshocker, right? Meet Scala, Kotlin, Ceylon, Xtend,
						Groovy, Clojure, Fantom and about a gazillion more. Find
						out what all the cool kids are into and why!</p>
					</div>
				</div>
				<div class="col-md-4 col-sm-4">
					<div class="icon-box text-center">
					<img src="${assetsUrl}/img/network.png" alt="Network">
						<h3 class="lead">AGILE + TOOLS</h3>
						<p>
						How far down the Agile rabbit hole have you gone? Whether
						youâre âAgile-ishâ or âfull-on Scrumâ with pair programming
						and bells and whistles, come learn from the best about
						how to open up a can of worms!</p>
					</div>
				</div>
				<div class="col-md-4 col-sm-4">
					<div class="icon-box text-center">
					<img src="${assetsUrl}/img/phone.png" alt="Phone">
						<h3 class="lead">MOBILE</h3>
						<p>
						Designing for mobile first is the hottest ticket in
						townâeven in web development. So whether you roll with
						hardcore Objective C, Java with Android SDK, or a dev
						framework like PhoneGap, Titanium or Xamarinâweâve got
						you covered!</p>
					</div>
				</div>
			</div>
		<!-- ./row -->
		</div><!-- /.container -->
	</section>
=
	<section id="video" class="bkgDarkGrey">
		<div class="container">
			<div class="row">
				<div class="col-md-5 col-sm-6 col-md-push-1 col-sm-6">
					<div id="goiXzB0rMEQ" class="video-container">
						<img src="${ctx}/assets/img/video_thumb.jpg"/>
					</div>
					<div id="video-play-button" class="play"></div>
				</div>
				<div class="col-md-5 col-md-push-1 col-sm-6">
					<h1 class="video-title">See some of the fun from DEVNEXUS 2014</h1>
					<p class="info">MARK YOUR CALENDARS FEBRUARY 15-17 2016</p>

				</div>
			</div>
		</div>
	</section>

	<section id="travel" class="white">
		<div class="top-intro travel">
		<h4 class="section-title decorated"><span>Travel</span></h4>
		</div>
		<div class="container">
			<div class="row travel-row-spacing">
			<h4 class="travel-address">Cobb Galleria Centre | Two Galleria Parkway, Atlanta, GA 30339 | 770-989-5095</h4>
				<div class="col-md-10 col-sm-10 col-md-push-1">
					<a href="https://www.google.com/maps/d/viewer?mid=zMoKxZbA6hII.kNm6qtm2mnkk"
						target="_blank"><div class="devnexus-map block-link" style="height:300px;"></div></a>
				</div>
			</div>
			<div class="row travel-row-spacing">
				<div class="col-md-5 col-sm-6 col-md-push-1 col-sm-6">
					<a href="http://cobbgalleria.com/" target="_blank"><img class="img-feature img-responsive" src="${assetsUrl}/img/cobbgalleria.jpg" alt="Cobb Galleria Centre"></a>
				</div>
				<div class="col-md-5 col-md-push-1 col-sm-6">
					<p>
					The Cobb Galleria website includes <a href="http://cobbgalleria.com/attendees/the-destination/directions-parking/" target="_blank">directions to the conference center</a> from several directions as well as the airport.</p>
					<p>
					<a href="http://itsmarta.com/" target="_blank">MARTA</a>, Atlanta's public transportation  system, may be used to reach the center, but the nearest bus stop is a few blocks away.</p>
				</div>
			</div>
			<div class="row travel-row-spacing">
				<div class="col-md-10 col-sm-10 col-md-push-1">
					<h4 class="travel-address">Cobb Galleria is accessible from several nearby hotels</h4>
				</div>
			</div>
			<div class="row travel-row-spacing">
				<div class="col-md-4 col-sm-4">
					<div class="text-center">
						<a href="http://bit.ly/fWkNVC" title="Visit Website" target="_blank"><img src="${assetsUrl}/img/index/sheraton.png" alt="Sheraton Hotel"></a>
						<div class="text-center"><a class="travel-directions-link" href="http://bit.ly/fNl2Ol">Get Directions</a></div>
					</div>
				</div>
				<div class="col-md-4 col-sm-4">
					<div class="text-center">
						<a href="http://bit.ly/hlU1OO" title="Visit Website" target="_blank"><img src="${assetsUrl}/img/index/renaissance.png" alt="Renaissance Atlanta Waverly Hotel"></a>
						<div><a class="travel-directions-link" href="http://bit.ly/gDzY9N">Get Directions</a></div>
					</div>
				</div>
				<div class="col-md-4 col-sm-4">
					<div class="text-center">
						<a href="http://www.atlantagalleria.embassysuites.com/" title="Visit Website" target="_blank"><img src="${assetsUrl}/img/index/embassy-suites.png" alt="Embassy Suites"></a>
						<div><a class="travel-directions-link" href="http://bit.ly/fKUADx">Get Directions</a></div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<section id="sponsors">
		<div class="row">
			<div class="col-md-8 col-md-push-2">
				<c:forEach items="${sponsorList.sponsors}" var="sponsor" varStatus="status">
					<c:choose>
						<c:when test="${sponsor.sponsorLevel.name ne sponsorLevel}">
							<c:if test="${not status.first}"></div></c:if>
							<c:set value="${sponsor.sponsorLevel.name}" var="sponsorLevel"/>
							<div class="top-intro sponsors ${sponsor.sponsorLevel.cssStyleClass}">
								<h4 class="decorated"><span>
									<c:choose>
										<c:when test="${sponsorList.sponsorLevelCount.get(sponsor.sponsorLevel) > 1}">
											${sponsorLevel}s
										</c:when>
										<c:otherwise>
											${sponsorLevel}
										</c:otherwise>
									</c:choose>
								</span></h4>
								<a href="${sponsor.link}"><img src="${sponsorList.logos[sponsor.id]}" alt="${sponsor.name}" title="${sponsor.name}"></a>
							<c:if test="${status.last}"></div></c:if>
						</c:when>
						<c:otherwise>
							<a href="${sponsor.link}"><img src="${sponsorList.logos[sponsor.id]}" alt="${sponsor.name}" title="${sponsor.name}"></a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</div>
		</div>
	</section>

	<!-- questions -->
	<section class="white">
		<div class="top-intro questions">
			<h4>Questions?</h4>
			<h3>Contact us at info@ajug.org</h3>
		</div>
	</section>

	<!-- footer -->
	<footer id="colophon" class="site-footer" role="contentinfo">
		<div class="footer-wrapper container">
			<div class="row">
				<div class="sidebar footer-sidebar clearfix">
					<div class="col-lg-3 col-sm-3 col-md-3 widget-1 footer-widget first footer-widget">
						<div id="meta" class="footer-widget">
							<img src="${assetsUrl}/img/DevNexus_logo_small.png" alt="DevNexus logo small">
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
								<li class=""><a href="${ctx}/static/2015/files/promo/devnexus-2015-sponsorship-options.pdf" target="_blank">Sponsorship (PDF)</a></li>
							</ul>
						</div>
					</div>
					<div class="col-lg-3 col-sm-3 col-md-3 widget-1 footer-widget footer-widget">
						<div id="tweet2" class="footer-widget">
							<h3 class="footer-title">DEVNEXUS 2016</h3>
							<ul class="footer-social">
								<li class=""><a href="${scheduleUrl}">Schedule</a></li>
								<li class=""><a href="${speakersUrl}">Speakers</a></li>
								<li class=""><a href="${presentationsUrl}">Presentations</a></li>
							</ul>
						</div>
					</div>
					<div class="col-lg-3 col-sm-3 col-md-3 widget-1 footer-widget last footer-widget">
						<div id="get_touch" class="widget widget_get_touch">
							<button disabled="disabled" class="btn btn-primary registerButton">Sold Out</button>
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
	<script src="${assetsUrl}/js/jquery.easing.min.js"></script>
	<script src="${assetsUrl}/js/bootstrap.min.js"></script> --%>
	<script>
		"use strict";
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

		$(function() {
			$(".video-container").each(function() {
				var videoId = this.id;
				$(document).delegate('#video-play-button', 'click', function() {
					var iframe_url = 'https://www.youtube.com/embed/'+ videoId + '?autoplay=1&autohide=1';
					var iframe = $('<iframe/>', {'frameborder': '0', 'src': iframe_url})
					$('#' + videoId).html(iframe).addClass('loaded');
					$('#video-play-button').hide();
				});
			});

			$("#tagline").fitText().fitText(1.05);
		});

	</script>
</body>
</html>
