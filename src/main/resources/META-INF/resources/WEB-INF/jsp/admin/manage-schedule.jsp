<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="com.devnexus.ting.model.ScheduleItemType"%>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>
<% pageContext.setAttribute("scheduleItemTypeAdminsitrative", ScheduleItemType.ADMINISTRATIVE); %>
<% pageContext.setAttribute("scheduleItemTypeBreak", ScheduleItemType.BREAK); %>
<% pageContext.setAttribute("scheduleItemTypeKeynote", ScheduleItemType.KEYNOTE); %>
<% pageContext.setAttribute("scheduleItemTypeRegistration", ScheduleItemType.REGISTRATION); %>
<% pageContext.setAttribute("scheduleItemTypeSession", ScheduleItemType.SESSION); %>

<<style>
<!--
.schedule {
    width: 100%;
}

.schedule td {
    vertical-align: middle;
    padding: 5px;
}

.schedule td p {
    margin: 2px;
}

.schedule .day {
    background-color: #F7931E;
    padding: 5px;
    text-align: center;
}

.schedule .break {
    background-color: #ED1C24;
}

.schedule .registration {
    background-color: #ED1C24;
}

.schedule .keynote {
    background-color: #FDA102;
}

.schedule .time {

background: rgb(48,47,145); /* Old browsers */
background: -moz-linear-gradient(top,  rgba(48,47,145,1) 0%, rgba(54,54,142,1) 100%); /* FF3.6+ */
background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,rgba(48,47,145,1)), color-stop(100%,rgba(54,54,142,1))); /* Chrome,Safari4+ */
background: -webkit-linear-gradient(top,  rgba(48,47,145,1) 0%,rgba(54,54,142,1) 100%); /* Chrome10+,Safari5.1+ */
background: -o-linear-gradient(top,  rgba(48,47,145,1) 0%,rgba(54,54,142,1) 100%); /* Opera 11.10+ */
background: -ms-linear-gradient(top,  rgba(48,47,145,1) 0%,rgba(54,54,142,1) 100%); /* IE10+ */
background: linear-gradient(top,  rgba(48,47,145,1) 0%,rgba(54,54,142,1) 100%); /* W3C */
filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#302f91', endColorstr='#36368e',GradientType=0 ); /* IE6-9 */

/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

    color: #FFFFFF;
    width: 20px;
}
.schedule .talk {
    background-color: #B5CBDF;
    font-weight: bold;
}

.schedule .breakouts {
   /* background-color: #6D9D31;*/
background: rgb(0,142,176); /* Old browsers */
background: -moz-linear-gradient(top,  rgba(0,142,176,1) 0%, rgba(87,159,175,1) 100%); /* FF3.6+ */
background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,rgba(0,142,176,1)), color-stop(100%,rgba(87,159,175,1))); /* Chrome,Safari4+ */
background: -webkit-linear-gradient(top,  rgba(0,142,176,1) 0%,rgba(87,159,175,1) 100%); /* Chrome10+,Safari5.1+ */
background: -o-linear-gradient(top,  rgba(0,142,176,1) 0%,rgba(87,159,175,1) 100%); /* Opera 11.10+ */
background: -ms-linear-gradient(top,  rgba(0,142,176,1) 0%,rgba(87,159,175,1) 100%); /* IE10+ */
background: linear-gradient(top,  rgba(0,142,176,1) 0%,rgba(87,159,175,1) 100%); /* W3C */
filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#008eb0', endColorstr='#579faf',GradientType=0 ); /* IE6-9 */

}

.schedule .speaker {
    font-weight: normal;
}
.schedule .topic {
    font-weight: bold;
}

.schedule .track-1 { /* A */
    background-color: #8E294A;
    text-shadow: 1px 1px 2px #EEEEEE;
}
.schedule .track-2 { /* B */
    background-color: #5A8E22;
    text-shadow: 1px 1px 2px #EEEEEE;
}
.schedule .track-3 { /* CDF */
    background-color: #FFA200;
    text-shadow: 1px 1px 2px #EEEEEE;
}
.schedule .track-4 { /* E */
    background-color: #008F9C;
    text-shadow: 1px 1px 2px #EEEEEE;
}
.schedule .track-5 { /* 103 */
    background-color: #0072BC;
    text-shadow: 1px 1px 2px #EEEEEE;
}
.schedule .track-6 { /* 104 */
    background-color: #2A3795;
    text-shadow: 1px 1px 2px #EEEEEE;
}
.schedule .track-7 { /* 105 */
    background-color: #622BA0;
    text-shadow: 1px 1px 2px #EEEEEE;
}
.schedule .track-102 { /* 102 */
    background-color: #8DC740;
    text-shadow: 1px 1px 2px #EEEEEE;
}
-->
</style>

<title>Manage Schedule</title>
<div style="margin-top: 20px" class="col-md-10 col-md-offset-1">
	<h2>Manage Schedule</h2>
</div>

<div class="row">
	<div class="col-md-10 col-md-offset-1">
		<ul class="list-group">
			<li class="list-group-item">
				<span class="badge"><c:out value="${scheduleItemList.numberOfSessions}"/></span>
				Number of Sessions
			</li>
			<li class="list-group-item">
				<span class="badge"><c:out value="${scheduleItemList.numberOfKeynoteSessions}"/></span>
				Number of Keynote Sessions
			</li>
			<li class="list-group-item">
				<span class="badge"><c:out value="${scheduleItemList.numberOfBreakoutSessions}"/></span>
				Number of Breakout Sessions
			</li>
			<li class="list-group-item">
				<span class="badge"><c:out value="${scheduleItemList.numberOfSpeakersAssigned}"/></span>
				Number of Speakers Assigned
			</li>
			<li class="list-group-item">
				<span class="badge"><c:out value="${scheduleItemList.numberOfUnassignedSessions}"/></span>
				Number of Unassigned Sessions
			</li>
			<li class="list-group-item">
				<span class="badge"><c:out value="${scheduleItemList.numberOfBreaks}"/></span>
				Number of Breaks
			</li>
			<li class="list-group-item">
				<span class="badge"><c:out value="${scheduleItemList.numberOfRooms}"/></span>
				Number of Rooms
			</li>
			<li class="list-group-item">
				<span class="badge"><c:out value="${scheduleItemList.days.size()}"/></span>
				Number of Days
			</li>
		</ul>
	</div>
</div>
<div class="row">
	<div class="col-md-10 col-md-offset-1">
		<div class="masonry">
				<c:forEach items="${presentationList.presentations}" var="presentation" varStatus="status">
					<div class="panel panel-default preso-box" style="width: 300px" data-presentation-id="${presentation.id}">
						<div class="panel-heading">ID: ${presentation.id}</div>
						${presentation.title}
					</div>
				</c:forEach>
		</div>
	</div>
</div>

<div class="row">
	<div class="col-md-10 col-md-offset-1">
		<c:set value="" var="loopStartTime" />
		<c:set value="" var="loopDay" />

		<c:forEach items="${scheduleItemList.scheduleItems}"
			var="scheduleItem">

			<fmt:formatDate pattern="H_m_s" value="${scheduleItem.fromTime}"
				var="currentStartTime" />
			<fmt:formatDate pattern="d" value="${scheduleItem.fromTime}"
				var="currentDay" />

			<c:if test="${empty loopDay or (currentDay != loopDay)}">

				<c:if test="${not empty loopDay}">
					</table>
				</c:if>
				<table style="border-collapse: collapse; margin-bottom: 1em;"
					class="schedule ">
					<tr>
						<th colspan="5" class="day"><fmt:formatDate
								pattern="EEEE MMMM d, yyyy" value="${scheduleItem.fromTime}" /></th>
					</tr>
					<tr>
						<th>Start</th>
						<th>End</th>
						<th>Session</th>
						<th>Room/Track</th>
						<th>Session</th>
					</tr>
					</c:if>

				<tr>
					<c:choose>
						<c:when test="${currentStartTime ne loopStartTime}">
							<c:set value="${currentStartTime}" var="loopStartTime" />
							<c:choose>
								<c:when test="${scheduleItem.rowspan > 1}">
									<td class="time" rowspan="${scheduleItem.rowspan}"><fmt:formatDate
											pattern="hh:mm" value="${scheduleItem.fromTime}" /></td>
									<td class="time" rowspan="${scheduleItem.rowspan}"><fmt:formatDate
											pattern="hh:mm" value="${scheduleItem.toTime}" /></td>
								</c:when>
								<c:otherwise>
									<td class="time"><fmt:formatDate pattern="hh:mm"
											value="${scheduleItem.fromTime}" /></td>
									<td class="time"><fmt:formatDate pattern="hh:mm"
											value="${scheduleItem.toTime}" /></td>
								</c:otherwise>
							</c:choose>
						</c:when>
					</c:choose>
					<c:choose>
						<c:when
							test="${scheduleItem.scheduleItemType == scheduleItemTypeRegistration}">
							<td colspan="3" class="registration"><c:out
									value="${scheduleItem.title} [${scheduleItem.room.name}]" /></td>
						</c:when>
						<c:when
							test="${scheduleItem.scheduleItemType == scheduleItemTypeAdminsitrative}">
							<td colspan="3" class="keynote"><c:out
									value="${scheduleItem.title} [${scheduleItem.room.name}]" /></td>
						</c:when>
						<c:when
							test="${scheduleItem.scheduleItemType == scheduleItemTypeBreak}">
							<td colspan="3" class="break"><c:out
									value="${scheduleItem.title}" default="Break" />
								[${scheduleItem.room.name}]</td>
						</c:when>
						<c:when
							test="${scheduleItem.scheduleItemType == scheduleItemTypeSession}">
							<c:if test="${scheduleItem.rowspan > 1}">
								<td class="breakouts" rowspan="${scheduleItem.rowspan}">Breakouts</td>
							</c:if>

							<td class="${scheduleItem.room.cssStyleName}">
								<p>
									<strong>${scheduleItem.room.name}</strong>
								</p> <c:if test="${not empty scheduleItem.room.track}">
									<p>
										<c:out value="${scheduleItem.room.track}" />
									</p>
								</c:if>
							</td>
							<td class="talk" data-schedule-item-id="${scheduleItem.id}">
								<p>Schedule Item ID: ${scheduleItem.id}</p>
								<p class="topic">
									<c:choose>
										<c:when test="${not empty scheduleItem.presentation}">
											<c:url var="presentationUrl"
												value="${baseSiteUrl}/presentations#id-${scheduleItem.presentation.id}" />
											<a href="${presentationUrl}"><c:out
													value="${scheduleItem.presentation.title}" /></a>
										</c:when>
										<c:otherwise>
											<c:out value="${scheduleItem.title}" default="N/A" />
										</c:otherwise>
									</c:choose>
								</p>
								<p class="speaker">
									<c:choose>
										<c:when test="${not empty scheduleItem.presentation}">
											<c:url var="speakerUrl" value="${baseSiteUrl}/speakers" />
											<c:forEach var="speaker" items="${scheduleItem.presentation.speakers}">
												<br/>${speaker.firstName} ${speaker.lastName}
											</c:forEach>
										</c:when>
										<c:otherwise>
																																			N/A
																															</c:otherwise>
									</c:choose>
								</p>
							</td>
						</c:when>
						<c:when
							test="${scheduleItem.scheduleItemType == scheduleItemTypeKeynote}">
							<td class="keynote">Keynote</td>
							<td class="keynote">${scheduleItem.room.name}</td>
							<td class="talk">
								<p class="topic">
									<c:choose>
										<c:when test="${not empty scheduleItem.presentation}">
											<c:url var="presentationUrl"
												value="${baseSiteUrl}/presentations#id-${scheduleItem.presentation.id}" />
											<a href="${presentationUrl}"><c:out
													value="${scheduleItem.presentation.title}" /></a>
										</c:when>
										<c:otherwise>
											<c:out value="${scheduleItem.title}" default="N/A" />
										</c:otherwise>
									</c:choose>
								</p>
								<p class="speaker">
									<c:choose>
										<c:when test="${not empty scheduleItem.presentation}">
<%-- 											<c:url var="speakerUrl"
												value="${baseSiteUrl}/speakers#${scheduleItem.presentation.speaker.firstName}_${scheduleItem.presentation.speaker.lastName}" />
											<a href="${speakerUrl}"><c:out
													value="${scheduleItem.presentation.speaker.firstLastName}" /></a> --%>
										</c:when>
										<c:otherwise>
																																			N/A
																															</c:otherwise>
									</c:choose>
								</p>
							</td>
						</c:when>
					</c:choose>
				</tr>

				<c:set var="loopDay" value="${currentDay}" />
			</c:forEach>

			<c:if test="${not empty loopDay}">
				</table>
			</c:if>

			<a class="btn btn-default" href="${ctx}${baseSiteUrl}/admin/${eventKey}/index"
				role="button">Main Menu</a>
	</div>
</div>

<content tag='bottom'>

	<script src="${ctx}/js/websocket/sockjs.js"></script>
	<script src="${ctx}/js/websocket/stomp.js"></script>
	<script src="${ctx}/js/handlebars-v1.1.2.js"></script>
<script src="${ctx}/js/masonry.pkgd.js"></script>
<script src="${ctx}/js/imagesloaded.pkgd.min.js"></script>

<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>

	<script type="text/javascript">
		$(document).ready(function() {

			var $container = $('.masonry');

			$container.imagesLoaded(function () {
					$container.masonry({
							itemSelector: '.preso-box',
							columnWidth: '.preso-box',
							isAnimated: true
					});
			});


			$("body").on('click',".delete-tweet", function(event) {
				var t = $(this).closest(".tweet-box");

				$container.imagesLoaded(function() {
					$container.masonry('remove', t);
					$container.masonry('layout');
				});

				return false;
			});

			$( ".preso-box" ).draggable({ revert: true });
			$( ".talk" ).droppable({
				drop: function( event, ui ) {
					console.log(event);
					console.log(ui.draggable);

					var presentationId = ui.draggable.data('presentationId');
					var scheduleItemId = $(this).data('scheduleItemId');

					console.log("Presentation ID: " + presentationId);
					console.log("Schedule Item ID: " + scheduleItemId);
					$( this ).addClass( "ui-state-highlight" ).html(ui.draggable.html());
					ui.draggable.remove();
				}
			});

		});
	</script>
</content>
