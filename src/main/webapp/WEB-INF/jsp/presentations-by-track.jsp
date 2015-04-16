<%@page import="com.devnexus.ting.model.PresentationType"%>
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

<section id="speaker" class="bg-light-gray" style="margin-top: 0">

<div id="trackContainer" class="container">
		<c:forEach items="${presentationList.presentations}" var="presentation" varStatus="status">
				<c:choose>
					<c:when test="${empty presentation.track}">
						<c:set var="trackStyle" value="defaultTrackStyle"/>
						<c:set var="trackColor" value=""/>
						<c:set var="trackColorFont" value=""/>
					</c:when>
					<c:otherwise>
						<c:set var="trackStyle" value="${presentation.track.cssStyleName}"/>
						<c:set var="trackColor" value="border-color: ${presentation.track.color};"/>
						<c:set var="trackFontColor" value="color: ${presentation.track.color};"/>
					</c:otherwise>
				</c:choose>
				<div id="id-${presentation.id}" class="col-sm-6 col-md-4 presentation masonryitem">
					<%@ include file="/WEB-INF/jsp/presentations-include.jsp" %>
				</div>
			<c:if test="${status.last}">

			</c:if>
		</c:forEach>
	</div>
</section>

<jsp:include page="includes/questions.jsp"/>

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

				var hash = window.location.hash;
				console.log('Hash is: ' + hash);
				if (!(hash === '')) {
					var el = $(hash);
					console.log('Scroll: ', el);
					$('html, body').animate({scrollTop: el.offset().top - 100}, 'slow');
				}
			});
		});
	</script>
</content>
