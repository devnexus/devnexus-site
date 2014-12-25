<%@page import="com.devnexus.ting.core.model.PresentationType"%>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>
<% pageContext.setAttribute("keynoteType", PresentationType.KEYNOTE); %>

<!-- intro -->
<section id="about" class="module parallax parallax-3">
	<div class="container header">
		<div class="row centered">
			<div class="col-md-10 col-md-offset-1">
				 <div class="top-intro travel">
					<h4 class="section-white-title decorated"><span>Presentations</span></h4>
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
					<c:when test="${status.first && status.index%3 == 0}">

					</c:when>
					<c:when test="${not status.first && not status.last && status.index%3 == 0}">

					</c:when>
				</c:choose>
				<div id="id-${presentation.id}" class="col-sm-6 col-md-4 presentation masonryitem">
					<%@ include file="/WEB-INF/jsp/presentations-include.jsp" %>
				</div>
			<c:if test="${status.last}">

			</c:if>
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

			$container.imagesLoaded(function () {
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
