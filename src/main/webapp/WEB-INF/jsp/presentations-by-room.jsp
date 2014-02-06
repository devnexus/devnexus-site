<%@page import="com.devnexus.ting.core.model.PresentationType" %>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>

<head>
	<title><c:out value="${event.title}"/> - Presentations</title>
</head>

<div id="devnex" class="jumbotron">
	<div class="container">
		<div id="banner">
			<h1 id="gray"><c:out value="${event.title}"/></h1>

			<h1 id="white">Presentations</h1>
			<h3>Data + Integration, Java/JavaEE/Spring, HTML5 + JavaScript, Alternative Languages, Cloud, Agile + Tools, Mobile</h3>
		</div>
	</div>
</div>

<div class="container" id="mainContainer">
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
				<c:set var="trackColor" value="background-color: ${presentation.room.color};"/>
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
			<div id="h4wrap"><h4>Presentations</h4></div>
			<div id="speakers">
				<div class="row" id="roomContainer${roomId}">
		</c:if>
		<div id="id-${presentation.id}" class="col-md-4 presentation room${roomId}">
			<%@ include file="/WEB-INF/jsp/presentations-include.jsp" %>
		</div>
	</c:forEach>
</div>
</div>

</div>

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
