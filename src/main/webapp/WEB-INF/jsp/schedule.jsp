<%@page import="com.devnexus.ting.core.model.ScheduleItemType" %>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>
<% pageContext.setAttribute("scheduleItemTypeAdminsitrative", ScheduleItemType.ADMINISTRATIVE); %>
<% pageContext.setAttribute("scheduleItemTypeBreak", ScheduleItemType.BREAK); %>
<% pageContext.setAttribute("scheduleItemTypeKeynote", ScheduleItemType.KEYNOTE); %>
<% pageContext.setAttribute("scheduleItemTypeRegistration", ScheduleItemType.REGISTRATION); %>
<% pageContext.setAttribute("scheduleItemTypeSession", ScheduleItemType.SESSION); %>

<head>
	<title>DevNexus 2015 - Schedule</title>
</head>

<!-- intro -->
<section id="about" class="module parallax parallax-3">
	<div class="container header">
		<div class="row centered">
			<div class="col-md-10 col-md-offset-1">
				<div class="top-intro travel">
					<h4 class="section-white-title decorated"><span>Schedule</span></h4>
					<h5 class="intro-white-lead">1500 Developers, 12 tracks, 116 Presentations, 3 Days.</h5>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- /intro -->

<div class="container">

	<c:forEach items="${scheduleItemList.days}" var="date" varStatus="dateStatus">

			<!-- Example row of columns -->
			<h1 class="text-center">Day ${dateStatus.index + 1}</h1>

			<h4 class="text-center"><fmt:formatDate pattern="EEEE MMMM d, yyyy" value="${date}"/></h4>
			<div id="sessions">
				<div class="row">
					<c:forEach items="${scheduleItemList.findHeaderItemsOnDate(date)}" var="headerItem"
						varStatus="headerStatus">
						<div class="col-md-2">
							<div class="row">
								<div class="col-md-12"><c:choose>
									<c:when test="${not empty headerItem.presentation}">
										<h3 id="green">${headerItem.presentation.presentationType.name}</h3>
										<p>${headerItem.presentation.title}</p>
									</c:when>
									<c:otherwise><h3 id="green">${headerItem.title}</h3></c:otherwise>
								</c:choose>
								</div>
							</div>
							<div id="botttom" class="row">
								<div class="col-md-12">
									<h4><fmt:formatDate pattern="h:mm" value="${headerItem.fromTime}"/>-<fmt:formatDate
										pattern="h:mm" value="${headerItem.toTime}"/><span id="small"><fmt:formatDate
										pattern="a" value="${headerItem.toTime}"/></span></h4>
									<p>${headerItem.room.name}</p>
								</div>
							</div>
						</div>
					</c:forEach>
					<div class="col-md-2">
						<div class="row">
							<div class="col-md-12"><h3 id="green">Lunch</div>
						</div>
						<div id="botttom" class="row">
							<div class="col-md-12">
								<h4>11:45-12:30<span id="small">pm</span></h4>

								<p>Hall A</p>
							</div>
						</div>
					</div>
					<div class="col-md-2">
						<div class="row">
							<div class="col-md-12"><h3 id="green">Dessert</div>
						</div>
						<div id="botttom" class="row">
							<div class="col-md-12">
								<h4>12:30-1:00<span id="small">pm</span></h4>

								<p>Exhibit Area</p>
							</div>
						</div>
					</div>
					<div class="col-md-2">
						<div class="row">
							<div class="col-md-12">
								<h3 id="green">Break<br/><small>(15 min)</small>
								<h4>10:15 <span id="small">am</span></h4>
								<h4>2:15 <span id="small">pm</span></h4>
							</div>
						</div>
						<div id="botttom" class="row">
							<div class="col-md-12">
								<h4>3:45 <span id="small">pm</span></h4>
								<h4>5:15 <span id="small">pm</span></h4>

								<p>Exhibit Area</p>
							</div>
						</div>
					</div>
				</div>
			</div>
			<h4 class="text-center">Breakouts</h4>
			<div id="schedule">
				<c:forEach items="${scheduleItemList.findRooms(date)}" var="room" varStatus="roomStatus">
					<c:if test="${roomStatus.index%4 == 0}">
						<div class="row schedule-row">
					</c:if>
					<div class="col-md-3">
						<c:set var="backgroundStyle" value="background-color:  ${room.color}"/>
						<div id="one-fourth" class="${room.cssStyleName} schedule-item" style="${not empty room.color ? backgroundStyle : ''}">
							<h3>${room.track}<br/>${room.name}</h3>
							<c:forEach items="${scheduleItemList.findBreakoutItemsOnDateInRoom(date, room)}" var="session"
								varStatus="sessionStatus">
								<p><strong><fmt:formatDate pattern="h:mm" value="${session.fromTime}"/>-<fmt:formatDate
												pattern="h:mm" value="${session.toTime}"/> <fmt:formatDate pattern="a"
												value="${session.toTime}"/></strong><br/>
									<c:choose>
										<c:when test="${not empty session.presentation}">
											<c:url var="presentationUrl"
												value="${baseSiteUrl}/presentations#id-${session.presentation.id}"/>
											<a href="${presentationUrl}"><c:out value="${session.presentation.title}"/></a>
										</c:when>
										<c:otherwise>
											<c:out value="${session.title}" default="N/A"/>
										</c:otherwise>
									</c:choose>
									<c:forEach var="speaker" items="${session.presentation.speakers}">
										<br/>${speaker.firstName} ${speaker.lastName}
									</c:forEach>
								</p>
							</c:forEach>
						</div>
					</div>
					<c:if test="${roomStatus.index%4 == 3 or roomStatus.last}">
						</div>
					</c:if>
				</c:forEach>
			</div>
	</c:forEach>
</div>

<jsp:include page="includes/questions.jsp"/>

<content tag='bottom'>
	<script type="text/javascript">
		$(document).ready(function() {
			$('.schedule-row').each(function () {
				var maxHeight = 0;
				$('.schedule-item', this).each(function () {
					var height = $(this).parent().innerHeight();
					if (height > maxHeight) {
						maxHeight = height;
					}
				});
				$('.schedule-item', this).each(function () {
					$(this).outerHeight(maxHeight);
				});
			});
		});
	</script>
</content>
