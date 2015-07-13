<%@page import="com.devnexus.ting.model.PresentationType" %>
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

<section id="speaker" class="bg-light-gray" style="margin-top: 0">
	<div id="trackContainer" class="container">
		<c:set var="roomName" value="nill"/>
		<c:forEach items="${presentationList.presentations}" var="presentation" varStatus="status">
			<c:choose>
				<c:when test="${empty presentation.room}">
					<c:set var="localRoomName" value="Room not Assigned"/>
					<c:set var="trackStyle" value="defaultTrackStyle"/>
					<c:set var="trackColor" value=""/>
					<c:set var="trackFontColor" value=""/>
					<c:set var="roomId" value="na"/>
				</c:when>
				<c:otherwise>
					<c:set var="localRoomName" value="${presentation.room.name}"/>
					<c:set var="trackStyle" value="${presentation.room.cssStyleName}"/>
					<c:set var="trackColor" value="border-color: ${presentation.room.color};"/>
					<c:set var="trackFontColor" value="color: ${presentation.room.color};"/>
					<c:set var="roomId" value="${presentation.room.id}"/>
				</c:otherwise>
			</c:choose>
			<c:if test="${roomName ne localRoomName}">
				<c:set var="roomName" value="${localRoomName}"/>
				<c:if test="${!status.first}">
						</div>
						</div>
				</c:if>
				<h1 class="${trackStyle}" style="${trackFontColor}"><strong>${roomName}</strong><br/></h1>
				<div id="speakers">
					<div class="row" id="roomContainer${roomId}">
			</c:if>
			<div id="id-${presentation.id}" class="col-sm-6 col-md-4 presentation room${roomId}">
				<%@ include file="/WEB-INF/jsp/presentations-include.jsp" %>
			</div>
		</c:forEach>
	</div>
</section>

<jsp:include page="includes/questions.jsp"/>

<content tag='bottom'>
	<script type="text/javascript">
		$(document).ready(function() {

			<c:forEach items="${presentationList.roomIdsAsString}" var="roomId">

			var $container${roomId} = $('#roomContainer${roomId}');

			console.log($container${roomId});

			$container${roomId}.imagesLoaded(function () {
				$container${roomId}.masonry({
						itemSelector: '.room${roomId}',
						columnWidth: '.room${roomId}',
						isAnimated: true
				});
			});

			</c:forEach>

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
