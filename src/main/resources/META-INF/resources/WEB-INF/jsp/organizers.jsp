<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>

<!-- intro -->
<section id="about" class="module parallax parallax-3">
	<div class="container header">
		<div class="row centered">
			<div class="col-md-10 col-md-offset-1">
				<div class="top-intro travel">
					<h4 class="section-white-title decorated"><span>Organizers</span></h4>
					<h5 class="intro-white-lead">The team of volunteers behind DevNexus.</h5>
					<br>
					<ul class="list-inline">
						<c:forEach items="${organizerList.organizers}" var="organizer" varStatus="status">
							<li><a class="speaker-link" href="#${organizer.firstName}_${organizer.lastName}"><c:out
								value="${organizer.firstName}"/> <c:out value="${organizer.lastName}"/></a></li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- /intro -->

<section id="speaker" class="bg-light-gray" style="margin-top: 0">

		<c:forEach items="${organizerList.organizers}" var="organizer" varStatus="status">
			<c:choose>
				<c:when test="${status.first && status.index%3 == 0}">
					<div class="row">
				</c:when>
				<c:when test="${not status.first && not status.last && status.index%3 == 0}">
					</div>
					<div class="row">
				</c:when>
			</c:choose>
			<div class="col-sm-4 masonryitem">
				<div id="${organizer.firstName}_${organizer.lastName}" class="speaker-member text-center">
					<c:if test="${organizer.picture != null}">
						<img class="img-responsive img-circle" src="${organizerPictures[organizer.id]}" alt="${organizer.firstName} ${organizer.lastName}" title="${organizer.firstName} ${organizer.lastName}"/>
					</c:if>
					<h4 class="text-center"><c:out value="${organizer.firstName}"/> <c:out value="${organizer.lastName}"/></h4>
					<p class="text-center" style="margin-top: 1em;">
						<c:if test="${!empty organizer.googlePlusId}">
							<a href="https://plus.google.com/<c:out value="${organizer.googlePlusId}" />" target="_blank">
								<button type="button" class="btn btn-default btn" data-toggle="tooltip" data-placement="bottom">
									<span class="fa fa-google"></span>
								</button>
							</a>
						</c:if>
						<c:if test="${!empty organizer.twitterId}">
							<a href="https://twitter.com/<c:out value="${organizer.twitterId}"/>" target="_blank">
								<button type="button" class="btn btn-default btn" data-toggle="tooltip" data-placement="bottom">
									<span class="fa fa-twitter"></span>
								</button>
							</a>
						</c:if>
						<c:if test="${!empty organizer.linkedInId}">
							<a href="http://www.linkedin.com/in/<c:out value="${organizer.linkedInId}" />" target="_blank">
								<button type="button" class="btn btn-default btn" data-toggle="tooltip" data-placement="bottom">
									<span class="fa fa-linkedin"></span>
								</button>
							</a>
						</c:if>
						<c:if test="${!empty organizer.githubId}">
							<a href="http://github.com/<c:out value="${organizer.githubId}" />" target="_blank">
								<button type="button" class="btn btn-default btn" data-toggle="tooltip" data-placement="bottom">
									<span class="fa fa-github"></span>
								</button>
							</a>
						</c:if>
						<c:if test="${!empty organizer.lanyrdId}">
							<a href="http://lanyrd.com/profile/<c:out value="${organizer.lanyrdId}" />" target="_blank">
								<button type="button" class="btn btn-default btn" data-toggle="tooltip" data-placement="bottom">
									<span>L</span>
								</button>
							</a>
						</c:if>
					</p>
					<p><c:out value="${organizer.bioAsHtml}" escapeXml="false"/></p>
				</div>
			</div>
			<c:if test="${status.last}">
				</div>
			</c:if>
		</c:forEach>
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

