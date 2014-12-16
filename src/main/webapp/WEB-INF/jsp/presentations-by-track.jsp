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
				<div class="col-sm-4 masonryitem">
					<div id="id-${presentation.id}" class="speaker-member">
						<c:choose>
							<c:when test="${not empty presentation.speakers}">
								<c:forEach items="${presentation.speakers}" var="speaker">
									<c:if test="${speaker.picture != null}">
										<img class="img-responsive img-circle" src="${ctx}${baseSiteUrl}/speakers/${speaker.id}.jpg" alt="${speaker.firstLastName}"/>
									</c:if>
									<h4>
										<a href="${siteUrl}/speakers#${speaker.firstName}_${speaker.lastName}">
											<c:out value="${speaker.firstName}"/> <c:out value="${speaker.lastName}"/>
										</a>
									</h4>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<h4>
									TBD
								</h4>
							</c:otherwise>
						</c:choose>
						<p class="text-muted"><c:out value="${presentation.title}"/></p>
						<p><c:out value="${presentation.descriptionAsHtml}" escapeXml="false"/></p>
					</div>
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

			console.log($container);

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
