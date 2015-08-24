<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="com.devnexus.ting.model.ScheduleItemType"%>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>
<% pageContext.setAttribute("scheduleItemTypeAdminsitrative", ScheduleItemType.ADMINISTRATIVE); %>
<% pageContext.setAttribute("scheduleItemTypeBreak", ScheduleItemType.BREAK); %>
<% pageContext.setAttribute("scheduleItemTypeKeynote", ScheduleItemType.KEYNOTE); %>
<% pageContext.setAttribute("scheduleItemTypeRegistration", ScheduleItemType.REGISTRATION); %>
<% pageContext.setAttribute("scheduleItemTypeSession", ScheduleItemType.SESSION); %>

<title>DevNexus 2016 - Schedule</title>
<div id="content" class="span-22 last">
  <div class="quote"><span>What the community says:</span> "Best Dev Education bargain in Atlanta"</div>
  <h2>Schedule</h2>

	<c:set value="" var="loopStartTime"/>
	<c:set value="" var="loopDay"/>

	<c:forEach items="${scheduleItemList.scheduleItems}" var="scheduleItem">

		<fmt:formatDate pattern="H_m_s" value="${scheduleItem.fromTime}" var="currentStartTime"/>
		<fmt:formatDate pattern="d"     value="${scheduleItem.fromTime}" var="currentDay"/>

		<c:if test="${empty loopDay or (currentDay != loopDay)}">

			<c:if test="${not empty loopDay}">
				</table>
			</c:if>
			<table class="table" style="border-collapse: collapse; margin-bottom: 1em;" class="schedule ">
			<tr>
				<th colspan="5" class="day"><fmt:formatDate pattern="EEEE MMMM d, yyyy" value="${scheduleItem.fromTime}"/></th>
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
					<c:set value="${currentStartTime}" var="loopStartTime"/>
					<c:choose>
						<c:when test="${scheduleItem.rowspan > 1}">
							<td class="time" rowspan="${scheduleItem.rowspan}"><fmt:formatDate pattern="hh:mm" value="${scheduleItem.fromTime}" /></td>
							<td class="time" rowspan="${scheduleItem.rowspan}"><fmt:formatDate pattern="hh:mm" value="${scheduleItem.toTime}" /></td>
						</c:when>
						<c:otherwise>
							<td class="time"><fmt:formatDate pattern="hh:mm" value="${scheduleItem.fromTime}" /></td>
							<td class="time"><fmt:formatDate pattern="hh:mm" value="${scheduleItem.toTime}" /></td>
						</c:otherwise>
					</c:choose>
				</c:when>
			</c:choose>
			<c:choose>
				<c:when test="${scheduleItem.scheduleItemType == scheduleItemTypeRegistration}">
					<td colspan="3" class="registration"><c:out value="${scheduleItem.title} [${scheduleItem.room.name}]"/></td>
				</c:when>
				<c:when test="${scheduleItem.scheduleItemType == scheduleItemTypeAdminsitrative}">
					<td colspan="3" class="keynote"><c:out value="${scheduleItem.title} [${scheduleItem.room.name}]"/></td>
				</c:when>
				<c:when test="${scheduleItem.scheduleItemType == scheduleItemTypeBreak}">
					<td colspan="3" class="break"><c:out value="${scheduleItem.title}" default="Break"/> [${scheduleItem.room.name}]</td>
				</c:when>
				<c:when test="${scheduleItem.scheduleItemType == scheduleItemTypeSession}">
					<c:if test="${scheduleItem.rowspan > 1}">
						<td class="breakouts" rowspan="${scheduleItem.rowspan}">Breakouts</td>
					</c:if>

					<td class="${scheduleItem.room.cssStyleName}">
						<p><strong>${scheduleItem.room.name}</strong></p>
						<c:if test="${not empty scheduleItem.room.track}">
							<p><c:out value="${scheduleItem.room.track}"/></p>
						</c:if>
					</td>
					<td class="talk">
						<p class="topic">
							<c:choose>
								<c:when test="${not empty scheduleItem.presentation}">
									<c:url var="presentationUrl" value="${baseSiteUrl}/presentations#id-${scheduleItem.presentation.id}"/>
									<a href="${presentationUrl}"><c:out value="${scheduleItem.presentation.title}"/></a>
								</c:when>
								<c:otherwise>
									<c:out value="${scheduleItem.title}" default="N/A"/>
								</c:otherwise>
							</c:choose>
						</p>
						<p class="speaker">
							<c:choose>
								<c:when test="${not empty scheduleItem.presentation.speakers}">
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
				<c:when test="${scheduleItem.scheduleItemType == scheduleItemTypeKeynote}">
					<td class="keynote">Keynote</td>
					<td class="keynote">${scheduleItem.room.name}</td>
					<td class="talk">
						<p class="topic">
							<c:choose>
								<c:when test="${not empty scheduleItem.presentation}">
									<c:url var="presentationUrl" value="${baseSiteUrl}/presentations#id-${scheduleItem.presentation.id}"/>
									<a href="${presentationUrl}"><c:out value="${scheduleItem.presentation.title}"/></a>
								</c:when>
								<c:otherwise>
									<c:out value="${scheduleItem.title}" default="N/A"/>
								</c:otherwise>
							</c:choose>
						</p>

						<p class="speaker">
							<c:choose>
								<c:when test="${not empty scheduleItem.presentation.speakers}">
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
			</c:choose>
		</tr>

		<c:set var="loopDay" value="${currentDay}" />

	</c:forEach>

	<c:if test="${not empty loopDay}">
			</table>
	</c:if>

</div>

<content tag='foo'>
  <script type="text/javascript">
  $(document).ready(function()
      {
        $("tr:even td.talk").css("background-color", "#D1D8DF");
      });
  </script>
</content>
