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
		<style type="text/css">
			#chart-tooltip {
				position: absolute;
				width: 400px;
				height: auto;
				padding: 5px;
				z-index: 9999999;
				background-color: #ffffff;
				-webkit-border-radius: 4px;
				-moz-border-radius: 4px;
				border-radius: 4px;
				-webkit-box-shadow: 4px 4px 10px rgba(0, 0, 0, 0.2);
				-moz-box-shadow: 4px 4px 10px rgba(0, 0, 0, 0.2);
				box-shadow: 4px 4px 10px rgba(0, 0, 0, 0.2);
				pointer-events: none;
			}

			#chart-tooltip.hidden {
				display: none;
			}

			#chart-tooltip p {
				margin: 0;
				font-family: sans-serif;
				font-size: 16px;
				line-height: 20px;
			}
		</style>
	</head>
<body>
	<c:url var="homeUrl" value="${baseSiteUrl}/index"/>
	<c:url var="speakersUrl" value="${baseSiteUrl}/speakers"/>
	<c:url var="presentationsUrl" value="${baseSiteUrl}/presentations"/>
	<c:url var="scheduleUrl" value="${baseSiteUrl}/schedule"/>
	<c:url var="organizersUrl" value="${baseSiteUrl}/organizers"/>
	<c:url var="registrationUrl" value="https://www.devnexus.com/s/register"/>
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
			</ul>
		</div>
	</nav>
	<div id="chart-tooltip" class="hidden">
		<p><strong><span id="value">123</span> Attendees</strong></p>
		<p><span id="session-title">title</span></p>
		<p><span id="session-speaker">speaker</span></p>
	</div>
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
					<div class="col-md-8 col-md-offset-2">
						<p class="lead">MARK YOUR CALENDARS FEBRUARY 15-17 2016!
							Our ninth DevNexus will be at the Georgia World Congress Center in Downtown Atlanta, GA.
							We are looking forward to hosting our largest event yet and can't wait for you to join.
							Check out out <a href="past-conferences">previous years'</a> content, bookmark this site to stay
							up to date, or <a href="manager">learn to convince your manager to let you come.</a></p>
					</div>
				</div>
				<div class="row centered">
					<div class="col-md-8 col-md-offset-2 col-xs-12" style="margin-bottom: 1em;">
						<a href="${registrationUrl}" class="btn btn-primary btn-block">Register Now!</a>
					</div>
				</div>
				<div class="row centered">
					<div class="col-md-4 col-md-offset-2" style="margin-bottom: 1em;">
						<a href="${ctx}/s/cfp" class="btn btn-primary btn-block">Call For Papers</a>
					</div>
					<div class="col-md-4" style="margin-bottom: 1em;">
						<a href="${ctx}/static/2016/files/promo/devnexus-2016-sponsorship-options.pdf" class="btn btn-primary btn-block">Sponsor DevNexus!</a>
					</div>
				</div>
				<div class="row" style="margin-top: 1em;">
					<div id="d3chart" class="col-md-10 col-md-offset-1">
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
						‘play nicely’ with everything else. Whether you SQL or you
						NoSQL, you’ll find what you need here, along with the
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
						refactor or build new, we’ll deliver the new features,
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
						browser? Whatever you need, we’ve got it here!</p>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4 col-sm-4">
					<div class="icon-box text-center">
					<img src="${assetsUrl}/img/chat.png" alt="Chat">
						<h3 class="lead">ATLERNATIVE LANGUAGES</h3>
						<p>
						Guess what? Java isn’t the only language to run on the
						JVM—shocker, right? Meet Scala, Kotlin, Ceylon, Xtend,
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
						you’re ‘Agile-ish’ or ‘full-on Scrum’ with pair programming
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
						town—even in web development. So whether you roll with
						hardcore Objective C, Java with Android SDK, or a dev
						framework like PhoneGap, Titanium or Xamarin—we’ve got
						you covered!</p>
					</div>
				</div>
			</div>
		<!-- ./row -->
		</div><!-- /.container -->
	</section>

	<section id="video" class="bkgDarkGrey">
		<div class="container">
			<div class="row">
				<div class="col-md-5 col-sm-6 col-md-push-1 col-sm-6">
					<div id="goiXzB0rMEQ" class="video-container">
						<img src="${ctx}/assets/img/video_thumb.png"/>
					</div>
					<div id="video-play-button" class="play"></div>
				</div>
				<div class="col-md-5 col-md-push-1 col-sm-6">
					<h1 class="video-title">See some of the fun from DEVNEXUS 2014</h1>
					<p class="info"> MARK YOUR CALENDARS FEBRUARY 15-17 2016</p>

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
				<h4 class="travel-address"><a href="https://www.google.com/maps/place/Georgia+World+Congress+Center/@33.76042,-84.3980223,17z/data=!4m2!3m1!1s0x0000000000000000:0x0072f65a339b8777">Georgia World Congress Center | 285 Andrew Young International Blvd NW, Atlanta, GA 30303
						| 404-223-4000</a></h4>

			</div>
			<div class="row travel-row-spacing">
				<div class="col-md-5 col-sm-6 col-md-push-1 col-sm-6">
					<a href="http://www.gwcc.com/" target="_blank"><img class="img-feature img-responsive" src="${assetsUrl}/img/gwcc.jpg" alt="Georgia World Congress Center"></a>
				</div>
				<div class="col-md-5 col-md-push-1 col-sm-6">
					<p>
					The Georgia World Conress Center website includes <a href="http://www.gwcc.com/directions/Default.aspx" target="_blank">directions to the conference center</a> from several directions as well as the airport.</p>
					<p>
					<a href="http://itsmarta.com/" target="_blank">MARTA</a>, Atlanta's public transportation  system, may be used to reach the conference, and the nearest train stop adjacent to the GWCC.</p>
				</div>
			</div>
			<div class="row travel-row-spacing">
				<div class="col-md-10 col-sm-10 col-md-push-1">
					<h4 class="travel-address">Georgia World Conress Center is accessible from several nearby hotels</h4>
				</div>
			</div>
			<div class="row travel-row-spacing">
				<div class="col-md-4 col-sm-4">
					<div class="text-center">
						<a class="travel-directions-link" href="http://www.omnihotels.com/hotels/atlanta-cnn-center" title="Visit Website" target="_blank"><img src="${assetsUrl}/img/omni.jpg" alt="Omni Hotel"><div>Omni Hotel</div></a>

					</div>
				</div>
				<div class="col-md-4 col-sm-4">
					<div class="text-center">
						<a class="travel-directions-link" href="http://embassysuites3.hilton.com/en/hotels/georgia/embassy-suites-atlanta-at-centennial-olympic-park-ATLESES/index.html" title="Visit Website" target="_blank"><img src="${assetsUrl}/img/embassy.jpg" alt="Embassy Suites"><div>Embassy Suites</div></a>
<!--						<div><a class="travel-directions-link" href="http://bit.ly/gDzY9N">Get Directions</a></div>-->
					</div>
				</div>
				<div class="col-md-4 col-sm-4">
					<div class="text-center">
						<a class="travel-directions-link" href="http://hiltongardeninn3.hilton.com/en/hotels/georgia/hilton-garden-inn-atlanta-downtown-ATLDOGI/index.html" title="Visit Website" target="_blank"><img src="${assetsUrl}/img/hilton.jpg" alt="Hilton Garden Inn"><div>Hilton Garden Inn</div></a>
					</div>
				</div>
			</div><div class="row travel-row-spacing">
				<div class="col-md-10 col-sm-10 col-md-push-1">
					<h4 class="travel-address">Downtown Atlanta features many attractions if you plan to extend your stay</h4>
				</div>
			</div>
			<div class="row travel-row-spacing">
				<div class="col-md-4 col-sm-4">
					<div class="text-center">
						<a class="travel-directions-link" href="https://www.civilandhumanrights.org/" title="Visit Website" target="_blank"><img src="${assetsUrl}/img/index/civilrights.jpg" alt="Center for Civil and Human Rights"><div>Center for Civil and Human Rights</div></a>

					</div>
				</div>
				<div class="col-md-4 col-sm-4">
					<div class="text-center">
						<a class="travel-directions-link" href="http://www.cfbhall.com/" title="Visit Website" target="_blank"><img src="${assetsUrl}/img/index/college_fhof.jpg" alt="College Football Hall of Fame"><div>College Football Hall of Fame</div></a>
<!--						<div><a class="travel-directions-link" href="http://bit.ly/gDzY9N">Get Directions</a></div>-->
					</div>
				</div>
				<div class="col-md-4 col-sm-4">
					<div class="text-center">
						<a class="travel-directions-link" href="http://www.georgiaaquarium.org/" title="Visit Website" target="_blank"><img src="${assetsUrl}/img/index/aquarium.jpg" alt="Georgia Aquarium"><div>Georgia Aquarium</div></a>
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
								<li class=""><a href="${ctx}/static/2016/files/promo/devnexus-2016-sponsorship-options.pdf" target="_blank">Sponsorship (PDF)</a></li>
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
					<div class="col-sm-3 col-md-3 widget-1 footer-widget last footer-widget">
						<div id="get_touch" class="widget widget_get_touch">
							<a href="${registrationUrl}" ><button class="btn btn-primary registerButton">Register Now!</button></a>
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
		(function(i, s, o, g, r, a, m) {
				i['GoogleAnalyticsObject'] = r;
				i[r] = i[r] || function() {
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
					var iframe_url = 'https://www.youtube.com/embed/' + videoId + '?autoplay=1&autohide=1';
					var iframe = $('<iframe/>', {'frameborder': '0', 'src': iframe_url})
					$('#' + videoId).html(iframe).addClass('loaded');
					$('#video-play-button').hide();
				});
			});

			$("#tagline").fitText().fitText(1.05);

			var container = d3.select("#d3chart")

			var width = $("#d3chart").width();
			var height = 120

			var svg = container.append("svg");
			svg.attr("height", height);
			svg.attr("width", width);
			svg.attr("viewBox", "0 0 " + width + " " + height);
			svg.attr("perserveAspectRatio", "xMinYMid");

			$(window).on("resize", function() {
				svg.attr("width", $("#d3chart").width());
				svg.attr("height", 120);
			});

			var barWidth = 4;

			function processData(data) {

				var maxValue = d3.max(data, function(el) {
					return parseInt(el.attendees)
				});
				var minValue = d3.min(data, function(el) {
					return parseInt(el.attendees)
				});
				var meanValue = d3.mean(data, function(el) {
					return parseInt(el.attendees)
				});

				var xScale = d3.scale.linear().domain([0, data.length]).range([0, width]);
				var yScale = d3.scale.linear().domain([0, maxValue]).range([0, height]);

				svg.selectAll('rect').data(data).enter()
				.append('rect')
				.attr('width', barWidth)
				.attr('height', function(d) {
					return yScale(d.attendees);
				})
				.attr("x", function(d, i) {return xScale(i)})
				.attr("y", function(d, i) {return height - yScale(d.attendees)})
				.style("fill", function(d, i) {return d.color})

				.on("mouseover", function(d) {
					d3.select(this).style("fill", "orange");
					d3.select("#chart-tooltip")
					.style("left", $('#d3chart').offset().left + ($('#d3chart').width() / 2) - ($('#chart-tooltip').width() / 2) + "px")
					.style("top", $('#d3chart').offset().top + "px")

					d3.select("#value").text(d.attendees);
					d3.select("#session-title").text(d.session_title);
					d3.select("#session-speaker").text(d.speaker);
					d3.select("#chart-tooltip")
					.style("opacity", 0)
					.classed("hidden", false)
					.transition()
					.duration(250)
					.style("opacity", 1);
				})
				.on("mouseout", function(d) {
					d3.select("#chart-tooltip")
					.transition()
					.duration(250)
					.style("opacity", 0)
					d3.select(this)
					.transition()
					.duration(250)
					.style("fill", function(d,i) {return d.color});
				})
				.style("opacity", 1)
				.append("title").text(function(d) {
					return d.session_title + ': ' + d.attendees + ' attendees.';
				});

			}

			d3.csv('${ctx}/static/2015/devnexus-stats.csv', function(data) {
				processData(data);
			});

		});

	</script>
</body>
</html>
