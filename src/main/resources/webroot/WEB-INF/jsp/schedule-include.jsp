<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="com.devnexus.ting.model.ScheduleItemType" %>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>
<% pageContext.setAttribute("scheduleItemTypeAdminsitrative", ScheduleItemType.ADMINISTRATIVE); %>
<% pageContext.setAttribute("scheduleItemTypeEveningReception", ScheduleItemType.EVENING_RECEPTION); %>
<% pageContext.setAttribute("scheduleItemTypeBreak", ScheduleItemType.BREAK); %>
<% pageContext.setAttribute("scheduleItemTypeKeynote", ScheduleItemType.KEYNOTE); %>
<% pageContext.setAttribute("scheduleItemTypeRegistration", ScheduleItemType.REGISTRATION); %>
<% pageContext.setAttribute("scheduleItemTypeMorningReception", ScheduleItemType.MORNING_RECEPTION); %>
<% pageContext.setAttribute("scheduleItemTypeSession", ScheduleItemType.SESSION); %>

<sec:authorize access="isAuthenticated()" var="authenticated"/>
<c:choose>
	<c:when test="${authenticated && !customSchedule}">
		<c:set var="customColumOffSet" value="1"/>
	</c:when>
	<c:otherwise>
		<c:set var="customColumOffSet" value="0"/>
	</c:otherwise>
</c:choose>

<section class="container-fluid schedule schedule-config" >
	<h1 class="featured-header">${pageTitle}</h1>

<!--    <div class="row hidden-xs">
				<div class="text-center">
						<button class="btn customize"><span class="badge"><img src="/assets/img/google-plus-2.png" class="icon"/></span>custom schedule</button>
						<button class="btn download">download</button>
				</div>
		</div>

		<div class="row hidden-xs" id="schedule-day-filter">
				<div class="text-center">
						<div class="btn-group" role="group" >
								<button type="button" class="btn btn-default "><span class="big">Day 1</span><br><span class="little">02/20/2017</span></button>
								<button type="button" class="btn btn-default unselected "><span class="big">Day 2</span><br><span class="little">02/21/2017</span></button>
								<button type="button" class="btn btn-default unselected"><span class="big">Day 3</span><br><span class="little">02/22/2017</span></button>
						</div>
				</div>
		</div>

		<div class="row visible-xs sub-filter" id="schedule-day-dropdown-filter">

			<div class="btn-group" >
					<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							Day 1 <span class="caret"></span>
					</button>
					<ul class="dropdown-menu">
							<li><a href="#">Day 1</a></li>
							<li><a href="#">Day 2</a></li>
							<li><a href="#">Day 3</a></li>
					</ul>
			</div>

		</div>-->

<!--    <div class="row sub-filter" >
				 Single button
				<div class="btn-group">
						<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								Show All <span class="caret"></span>
						</button>
						<ul class="dropdown-menu">
								<li><a href="#">Keynote, Breaks, etc</a></li>
										<c:forEach var="track" items="${tracks}" varStatus="speakerStatus">
										<li><a href="#">${track.name}</a></li>
								</c:forEach>
						</ul>
				</div>

		</div>-->
</section>

<section class="container-fluid schedule" style="margin-bottom: 1em;">

	<c:set value="" var="loopStartTime"/>
	<c:set value="" var="loopDay"/>

	<c:forEach items="${scheduleItemList.scheduleItems}" var="scheduleItem">
		<fmt:formatDate pattern="H_m_s" value="${scheduleItem.fromTime}" var="currentStartTime"/>
		<fmt:formatDate pattern="d"     value="${scheduleItem.fromTime}" var="currentDay"/>
		<!-- check is we are are a day boundary and create new table -->
		<c:if test="${empty loopDay or (currentDay != loopDay)}">
			<c:if test="${not empty loopDay}">
				</div>
			</c:if>
			<div class="row">
				<div class="row text-center row-eq-height schedule-header">
					<h2><fmt:formatDate pattern="EEEE MMMM d, yyyy" value="${scheduleItem.fromTime}"/></h2>
				</div>
		</c:if>
		<!-- end check is we are are a day boundary and create new table -->

		<c:choose>
			<c:when test="${scheduleItem.scheduleItemType == scheduleItemTypeRegistration}">
				<div class="row schedule-row" style="background: linear-gradient(-90deg, ${scheduleItem.room.color}, ${scheduleItem.room.color}) repeat-y; background-size: 10px 10px;">
					<div class="col-sm-${8-customColumOffSet} col-xs-12 name">
						<c:out value="${scheduleItem.title}"/><br>
					</div>

					<div class="col-sm-2 col-xs-12 text-center">
						${scheduleItem.room.name}
					</div>
					<div class="col-sm-2 col-xs-12 text-center">
						<span class="time"><fmt:formatDate pattern="h:mm" value="${scheduleItem.fromTime}" /></span> - <span class="time"><fmt:formatDate pattern="h:mm a" value="${scheduleItem.toTime}" /></span>
					</div>
				</div>
			</c:when>
			<c:when test="${scheduleItem.scheduleItemType == scheduleItemTypeAdminsitrative}">
				<div class="row schedule-row" style="background: linear-gradient(-90deg, ${scheduleItem.room.color}, ${scheduleItem.room.color}) repeat-y; background-size: 10px 10px;">
					<div class="col-sm-${8-customColumOffSet} col-xs-12 name">
						<c:out value="${scheduleItem.title}"/><br>
					</div>

					<div class="col-sm-2 col-xs-12 text-center">
							${scheduleItem.room.name}
					</div>
					<div class="col-sm-2 col-xs-12 text-center">
						<span class="time"><fmt:formatDate pattern="h:mm a" value="${scheduleItem.fromTime}" /></span> - <span class="time"><fmt:formatDate pattern="h:mm a" value="${scheduleItem.toTime}" /></span>
					</div>
				</div>
			</c:when>
			<c:when test="${scheduleItem.scheduleItemType == scheduleItemTypeBreak}">
				<div class="row schedule-row" style="background: linear-gradient(-90deg, ${scheduleItem.room.color}, ${scheduleItem.room.color}) repeat-y; background-size: 10px 10px;">
					<div class="col-sm-${8-customColumOffSet} col-xs-12 name">
						<c:out value="${scheduleItem.title}"/><br>
					</div>

					<div class="col-sm-2 col-xs-12 text-center">
						${scheduleItem.room.name}
					</div>
					<div class="col-sm-2 col-xs-12 text-center">
						<span class="time"><fmt:formatDate pattern="h:mm a" value="${scheduleItem.fromTime}" /></span> - <span class="time"><fmt:formatDate pattern="h:mm a" value="${scheduleItem.toTime}" /></span>
					</div>
				</div>
			</c:when>
			<c:when test="${scheduleItem.scheduleItemType == scheduleItemTypeSession}">
				<c:set var="showItem" value="${true}"/>
				<c:if test="${customSchedule && !scheduleItem.favorite}">
					<c:set var="showItem" value="${false}"/>
				</c:if>

				<c:if test="${scheduleItem.rowspan > 1}">
				</div>
				<div class="row">
					<div class=" breakouts row text-center row-eq-height schedule-header">
						<h2>Breakouts <fmt:formatDate pattern="h:mm a" value="${scheduleItem.fromTime}"/></h2>
					</div>
				</c:if>

				<c:if test="${showItem}">
				<div class="row schedule-row" style="background: linear-gradient(-90deg, ${scheduleItem.room.color}, ${scheduleItem.room.color}) repeat-y; background-size: 10px 10px;">
					<div class="col-sm-${6-customColumOffSet} col-xs-12 name">
						<c:choose>
							<c:when test="${not empty scheduleItem.presentation}">
								<c:url var="presentationUrl" value="${baseSiteUrl}/${event.eventKey}/presentations/${scheduleItem.presentation.id}"/>
								<a href="${presentationUrl}"><c:out value="${scheduleItem.presentation.title}"/></a>
							</c:when>
							<c:otherwise>
								<c:out value="${scheduleItem.title}" default="N/A"/>
							</c:otherwise>
						</c:choose><br>
						<span class="small">
							<c:choose>
								<c:when test="${not empty scheduleItem.presentation.speakers}">
									<c:forEach var="speaker" items="${scheduleItem.presentation.speakers}" varStatus="speakerStatus">
										<c:if test="${speakerStatus.index ne 0}">,</c:if>
										${speaker.firstName} ${speaker.lastName}
									</c:forEach>
								</c:when>
								<c:otherwise>
									N/A
								</c:otherwise>
							</c:choose>
						</span>
					</div>
					<div class="col-sm-2 col-xs-12 text-center">
						<c:choose>
							<c:when test="${not empty scheduleItem.presentation && not empty scheduleItem.presentation.track}">
								<p><c:out value="${scheduleItem.presentation.track.name}"/></p>
							</c:when>
							<c:when test="${empty scheduleItem.presentation && not empty scheduleItem.room}">
								<p><c:out value="${scheduleItem.room.track}"/></p>
							</c:when>
						</c:choose>
					</div>
					<div class="col-sm-2 col-xs-12 text-center">
						${scheduleItem.room.name}
					</div>
					<div class="col-sm-2 col-xs-12 text-center">
						<span class="time"><fmt:formatDate pattern="h:mm a" value="${scheduleItem.fromTime}" /></span> - <span class="time"><fmt:formatDate pattern="h:mm a" value="${scheduleItem.toTime}" /></span>
					</div>
					<c:if test="${!customSchedule}">
						<sec:authorize access="isAuthenticated()">
							<div class="col-sm-${customColumOffSet} col-xs-12 text-center schedule-favorite-cell">
							<c:choose>
								<c:when test="${scheduleItem.favorite}">
									<a class="session-favorite is-favorite"
										data-schedule-item-id="${scheduleItem.id}" data-is-favorite="${scheduleItem.favorite}"
										href="#" title="Remove as favorite">
										<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
									</a>
								</c:when>
								<c:otherwise>
									<a class="session-favorite"
										data-schedule-item-id="${scheduleItem.id}" data-is-favorite="${scheduleItem.favorite}"
										href="#" title="Add as favorite">
										<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
									</a>
								</c:otherwise>
							</c:choose>
							</div>
						</sec:authorize>
					</c:if>
				</div>
				</c:if>
			</c:when>
			<c:when test="${scheduleItem.scheduleItemType == scheduleItemTypeKeynote}">
				<div class="row schedule-row keynote" style="background: linear-gradient(-90deg, ${scheduleItem.room.color}, ${scheduleItem.room.color}) repeat-y; background-size: 10px 10px;">
					<div class="col-sm-${8-customColumOffSet} col-xs-12 name">
						<c:choose>
							<c:when test="${not empty scheduleItem.presentation}">
								<c:url var="presentationUrl" value="${baseSiteUrl}/${event.eventKey}/presentations/${scheduleItem.presentation.id}"/>
								<a href="${presentationUrl}"><c:out value="${scheduleItem.presentation.title}"/></a>
							</c:when>
							<c:otherwise>
								<c:out value="${scheduleItem.title}" default="N/A"/>
							</c:otherwise>
						</c:choose><br>
						<span class="small">
							<c:choose>
								<c:when test="${not empty scheduleItem.presentation.speakers}">
									<c:forEach var="speaker" items="${scheduleItem.presentation.speakers}" varStatus="speakerStatus">
										<c:if test="${speakerStatus.index ne 0}">,</c:if>
										${speaker.firstName} ${speaker.lastName}
									</c:forEach>
								</c:when>
								<c:otherwise>
									N/A
								</c:otherwise>
							</c:choose>
						</span>
					</div>
					<div class="col-sm-2 col-xs-12 text-center">
						${scheduleItem.room.name}
					</div>
					<div class="col-sm-2 col-xs-12 text-center">
						<span class="time"><fmt:formatDate pattern="h:mm a" value="${scheduleItem.fromTime}" /></span> - <span class="time"><fmt:formatDate pattern="h:mm a" value="${scheduleItem.toTime}" /></span>
					</div>
				</div>
			</c:when>
			<c:when test="${scheduleItem.scheduleItemType == scheduleItemTypeEveningReception}">
				<div class="row schedule-row" style="background: linear-gradient(-90deg, ${scheduleItem.room.color}, ${scheduleItem.room.color}) repeat-y; background-size: 10px 10px;">
					<div class="col-sm-8 col-xs-12 name">
						<c:out value="${scheduleItem.title}"/><br>
					</div>
					<div class="col-sm-2 col-xs-12 text-center">
						${scheduleItem.room.name}
					</div>
					<div class="col-sm-2 col-xs-12 text-center">
						<span class="time"><fmt:formatDate pattern="h:mm" value="${scheduleItem.fromTime}" /></span> - <span class="time"><fmt:formatDate pattern="h:mm a" value="${scheduleItem.toTime}" /></span>
					</div>
				</div>
			</c:when>
		</c:choose>
		<c:set var="loopDay" value="${currentDay}" />
	</c:forEach>
	<c:if test="${not empty loopDay}">
		</div>
	</c:if>
</section>

<content tag='bottom'>
	<script type="text/javascript">
		$(document).ready(function() {
			$('body').on('click', 'a.session-favorite', function() {
				var element = $(this);
				var favorite = element.data('is-favorite');
				var scheduleItemId = element.data('schedule-item-id');
				if (!favorite) {
					console.log('Add schedule item Id: ' + scheduleItemId + ' as favorite.');
					$.ajax({
						method: "POST",
						url: "${ctx}/s/${event.eventKey}/usercalendar/" + scheduleItemId,
						data: {}
					})
					.done(function( msg ) {
						console.log( "Added: ", msg );
						element.addClass('is-favorite');
						element.attr('title', 'Remove as favorite');
						element.data('is-favorite', true);
						element.blur();
					});
				}
				else {
					console.log('Remove schedule item Id: ' + scheduleItemId + ' as favorite.');
					$.ajax({
						method: "DELETE",
						url: "${ctx}/s/${event.eventKey}/usercalendar/" + scheduleItemId,
						data: {}
					})
					.done(function( msg ) {
						console.log( "Removed: " + msg );
						element.removeClass('is-favorite')
						element.attr('title', 'Add as favorite')
						element.data('is-favorite', false);
						element.blur();
					});
				}
				return false;
			});
		});
	</script>
</content>
