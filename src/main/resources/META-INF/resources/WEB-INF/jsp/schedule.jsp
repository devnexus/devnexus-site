<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="com.devnexus.ting.model.ScheduleItemType" %>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>
<% pageContext.setAttribute("scheduleItemTypeAdminsitrative", ScheduleItemType.ADMINISTRATIVE); %>
<% pageContext.setAttribute("scheduleItemTypeBreak", ScheduleItemType.BREAK); %>
<% pageContext.setAttribute("scheduleItemTypeKeynote", ScheduleItemType.KEYNOTE); %>
<% pageContext.setAttribute("scheduleItemTypeRegistration", ScheduleItemType.REGISTRATION); %>
<% pageContext.setAttribute("scheduleItemTypeSession", ScheduleItemType.SESSION); %>

<title>${contextEvent.title} | Schedule</title>

<div class="ribbon animated fadeIn delayp1"
	data-50="opacity:1;"
	data-180="opacity:0;">
	<a href="http://nighthacking.com/event/devnexus2015/" target="_blank">Nighthacking</a>
</div>

<!-- intro -->
<section id="about" class="module parallax parallax-3">
	<div class="container header">
		<div class="row centered">
			<div class="col-md-10 col-md-offset-1">
				<div class="top-intro travel">
					<h4 class="section-white-title decorated"><span>Schedule for ${event.title}</span></h4>
					<h5 class="intro-white-lead">13 Tracks, 120+ Presentations, 2+1 Days.</h5>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- /intro -->

<div class="container">

	<c:forEach items="${scheduleItemList.days}" var="date" varStatus="dateStatus">

			<!-- Example row of columns -->
			<c:choose>
				<c:when test="${dateStatus.index eq 0}">
					<h1 class="text-center">Workshop Day</h1>
				</c:when>
				<c:otherwise>
					<h1 class="text-center">Conference Day ${dateStatus.index}</h1>
				</c:otherwise>
			</c:choose>

			<h4 class="text-center"><fmt:formatDate pattern="EEEE MMMM d, yyyy" value="${date}"/></h4>
			<div class="header-item-container">
				<div class="row row-centered">
				<c:forEach items="${scheduleItemList.findRegistrationItemsOnDate(date)}" var="registrationItem"
						varStatus="registrationStatus">
					<div class="col-xs-12  col-centered">
						<div class="registration-item">
							<h3 id="green">${registrationItem.title}</h3>
							<div>
								<h4><fmt:formatDate pattern="h:mm" value="${registrationItem.fromTime}"/>-<fmt:formatDate
									pattern="h:mm" value="${registrationItem.toTime}"/><span id="small"> <fmt:formatDate
									pattern="a" value="${registrationItem.toTime}"/></span> | ${registrationItem.room.name}</h4>
							</div>
						</div>
					</div>
				</c:forEach>
				</div>
				<div class="row row-centered"
					><c:forEach items="${scheduleItemList.findHeaderItemsOnDate(date)}" var="headerItem"
						varStatus="headerStatus"><c:if test="${headerItem.scheduleItemType != scheduleItemTypeRegistration}"><div class="col-xs-6 col-md-3 col-centered">
							<div class="header-item">
								<h3 id="green">${headerItem.title}</h3>
								<div>
									<h4><fmt:formatDate pattern="h:mm" value="${headerItem.fromTime}"/>-<fmt:formatDate
										pattern="h:mm" value="${headerItem.toTime}"/><span id="small"> <fmt:formatDate
										pattern="a" value="${headerItem.toTime}"/></span></h4>
									<p>${headerItem.room.name}</p>
								</div>
								<c:if test="${not empty headerItem.presentation}">

									<c:url var="presentationUrl" value="${baseSiteUrl}/presentations#id-${headerItem.presentation.id}"/>
									<p><a href="${presentationUrl}"><c:out value="${headerItem.presentation.title}"/></a>
									<c:forEach var="speaker" items="${headerItem.presentation.speakers}">
										<br/>${speaker.firstName} ${speaker.lastName}
									</c:forEach></p>
								</c:if>
							</div>
						</div></c:if></c:forEach>
						<c:set var="breaks" value="${scheduleItemList.findBreakItemsOnDate(date)}"/>
						<c:if test="${not empty breaks}">
							<div class="col-xs-6 col-md-3 col-centered">
								<div class="header-item">
									<h3>Breaks</h3>
									<c:forEach items="${breaks}" var="breakItem" varStatus="headerStatus">
									<h4><fmt:formatDate pattern="h:mm" value="${breakItem.fromTime}"/>-<fmt:formatDate
										pattern="h:mm" value="${breakItem.toTime}"/><span id="small"> <fmt:formatDate
										pattern="a" value="${breakItem.toTime}"/></span></h4>
									</c:forEach>
									<p>Exhibit Area</p>
								</div>
							</div>
						</c:if>
				</div>
			</div>
			<h4 class="text-center">Breakouts</h4>
			<div id="schedule">
				<c:forEach items="${scheduleItemList.findRooms(date)}" var="room" varStatus="roomStatus">
					<c:if test="${roomStatus.index%4 == 0}">
						<div class="row schedule-row row-centered">
					</c:if>
					<div class="col-xs-12 col-sm-6 col-md-3 col-centered">
						<c:set var="backgroundStyle" value="background-color:  ${room.color}"/>
						<div id="one-fourth" class="${room.cssStyleName} schedule-item" style="${not empty room.color ? backgroundStyle : ''}">
							<h3 class="track-room-name">${room.track}<br/>${room.name}</h3>
							<c:forEach items="${scheduleItemList.findBreakoutItemsOnDateInRoom(date, room)}" var="session"
								varStatus="sessionStatus">
								<div class="schedule-item-session"><strong><fmt:formatDate pattern="h:mm" value="${session.fromTime}"/>-<fmt:formatDate
												pattern="h:mm" value="${session.toTime}"/> <fmt:formatDate pattern="a"
												value="${session.toTime}"/></strong><br/>
									<c:choose>
										<c:when test="${not empty session.presentation}">
											<c:url var="presentationUrl"
												value="${baseSiteUrl}/presentations#id-${session.presentation.id}"/>
											<span class="session-title"><a
												href="${presentationUrl}"><c:out value="${session.presentation.title}"/></a></span>
										</c:when>
										<c:otherwise>
											<c:out value="${session.title}" default="N/A"/>
										</c:otherwise>
									</c:choose>
									<c:forEach var="speaker" items="${session.presentation.speakers}" varStatus="status">
										<c:if test="${status.first}"><br/></c:if>
										<c:if test="${status.last and not status.first}">&amp;</c:if>
										<c:if test="${not status.last and not status.first}">,</c:if>
										${speaker.firstName} ${speaker.lastName}
									</c:forEach>
								</div>
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

			$('.session-title').succinct({
				size: 100,
				omission: '&hellip;'
			});

			function resizeScheduleItems() {
				var scheduleItemSessionMaxHeight = 0;

				$('.track-room-name').each(function () {
					$(this).outerHeight('auto');
					var height = $(this).outerHeight();
					if (height > scheduleItemSessionMaxHeight) {
						scheduleItemSessionMaxHeight = height;
					}
				}).promise().done(function (item) {
					$('.track-room-name').each(function () {
						$(this).outerHeight(scheduleItemSessionMaxHeight);
					});
				});

				$('.schedule-item-session').each(function () {
					$(this).outerHeight('auto');
					var height = $(this).outerHeight();
					if (height > scheduleItemSessionMaxHeight) {
						scheduleItemSessionMaxHeight = height;
					}
				}).promise().done(function (item) {
					$('.schedule-item-session').each(function () {
						$(this).outerHeight(scheduleItemSessionMaxHeight);
					});
				});

				$('.schedule-row').each(function () {
					var maxHeight = 0;
					$('.schedule-item', this).each(function () {
						$(this).outerHeight('auto');
						var height = $(this).parent().innerHeight();
						if (height > maxHeight) {
							maxHeight = height;
						}
					}).promise().done(function (item) {
						$(item).each(function () {
							$(this).outerHeight(maxHeight);
						});
					});
				});
				$('.header-item-container').each(function () {
					var maxHeight = 0;
					$('.header-item', this).each(function () {
						$(this).outerHeight('auto');
						var height = $(this).parent().innerHeight();
						if (height > maxHeight) {
							maxHeight = height;
							console.log(maxHeight);
						}
					}).promise().done(function (item) {
						$(item).each(function () {
							$(this).outerHeight(maxHeight);
						});
					});;
				});
			}
			resizeScheduleItems();

			var resizeId;
			$(window).resize(function() {
				clearTimeout(resizeId);
				resizeId = setTimeout(doneResizing, 250);
			});

			function doneResizing(){
				resizeScheduleItems();
			}

			var s = skrollr.init({
				forceHeight: false,
				mobileCheck: function() {
					return false;
				}
			});
		});
	</script>
</content>
