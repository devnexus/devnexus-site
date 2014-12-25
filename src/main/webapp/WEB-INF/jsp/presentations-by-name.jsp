<%@page import="com.devnexus.ting.core.model.PresentationType" %>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>

<head>
	<title><c:out value="${event.title}"/> - Presentations</title>
</head>

<!-- intro -->
<section id="about" class="module parallax parallax-3">
	<div class="container header">
		<div class="row centered">
			<div class="col-md-10 col-md-offset-1">
				 <div class="top-intro travel">
					<h4 class="section-white-title decorated"><span><c:out value="${event.title}"/> Presentations</span></h4>
					<h5 class="intro-white-lead">Discover how the industry's best minds use the latest technologies to build solutions.</h5>
					<ul class="list-inline">
						<li>Data + Integration</li>
						<li>Java/JavaEE/Spring</li>
						<li>HTML5 + Javascript</li>
						<li>Alternative Languages</li>
						<li>Cloud</li>
						<li>Agile + Tools</li>
						<li>Mobile</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- /intro -->

<div class="row" style="margin-top: 2em;">
	<div class="col-md-10 col-md-offset-1">
		<div class="alert alert-info text-center" role="alert">Please be aware that more sessions are still being added!</div>
	</div>
</div>
<section id="speaker" class="bg-light-gray" style="margin-top: 0">
	<div id="trackContainer" class="container">
		<c:forEach items="${presentationList.presentations}" var="presentation" varStatus="status">
			<c:choose>
				<c:when test="${empty presentation.track}">
					<c:set var="trackStyle" value="defaultTrackStyle"/>
					<c:set var="trackColor" value=""/>
				</c:when>
				<c:otherwise>
					<c:set var="trackStyle" value="${presentation.track.cssStyleName}"/>
					<c:set var="trackColor" value="background-color: ${presentation.track.color};"/>
				</c:otherwise>
			</c:choose>
			<div id="id-${presentation.id}" class="col-sm-6 col-md-4 presentation masonryitem" style="${trackColor}">
				<%@ include file="/WEB-INF/jsp/presentations-include.jsp" %>
			</div>
		</c:forEach>
	</div>
</section>

<!-- questions -->
<section class="white">
	<div class="top-intro questions">
		<h4 class="section-title">Questions?</h4>
		<h3>Contact us at info@ajug.org</h3>
	</div>
</section>

<content tag='bottom'>
	<script type="text/javascript">
		$(document).ready(function() {
			var $container = $('#speaker');

			$container.imagesLoaded(function () {
				$container.masonry({
						itemSelector: '.masonryitem',
						columnWidth: '.masonryitem',
						isAnimated: true
				});
			});

			var container = $('#mainContainer');
			container.imagesLoaded(function () {
				var hash = window.location.hash;
				console.log('Hash is: ' + hash);
				if (!(hash === '')) {
					console.log('Scroll: ' + hash);
					$('html, body').animate({scrollTop: $(hash).offset().top - 100}, 'slow');
				}
			});

		});
	</script>
</content>

