<%@page import="com.devnexus.ting.core.model.ScheduleItemType"%>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>
<% pageContext.setAttribute("lf", "\n"); %>
<% pageContext.setAttribute("scheduleItemTypeAdminsitrative", ScheduleItemType.ADMINISTRATIVE); %>
<% pageContext.setAttribute("scheduleItemTypeBreak", ScheduleItemType.BREAK); %>
<% pageContext.setAttribute("scheduleItemTypeKeynote", ScheduleItemType.KEYNOTE); %>
<% pageContext.setAttribute("scheduleItemTypeRegistration", ScheduleItemType.REGISTRATION); %>
<% pageContext.setAttribute("scheduleItemTypeSession", ScheduleItemType.SESSION); %>

<!-- Start of first page -->
<div data-role="page" id="schedule-index" data-theme="b">

	<div data-role="header">
		<a rel="external" href="${ctx}${baseSiteUrl}/index">Back</a>
		<h1>Schedule</h1>
	</div>
	<!-- /header -->

	<div data-role="content">

		<ul data-role="listview" data-inset="true" data-theme="c"
			data-dividertheme="b">
			<c:forEach items="${scheduleItemList.days}" var="day">
				<fmt:formatDate pattern="yyyy_MM_dd"     value="${day}" var="dayId"/>
				<li><a href="#schedule-${dayId}">
					<img class="ui-li-icon" alt="Day 1" src="${ctx}/img/icons/crystal/vcalendar.png" />
					<fmt:formatDate pattern="EEEE MMMM d, yyyy" value="${day}" />
				</a></li>
			</c:forEach>
		</ul>
	</div>
	<!-- /content -->

	<div data-role="footer">
		<h4>&copy; 2013 AJUG</h4>
	</div>
	<!-- /header -->
</div>
	<c:set value="" var="loopStartTime"/>
	<c:set value="" var="loopDay"/>
	<c:forEach items="${scheduleItemList.scheduleItems}" var="scheduleItem">

		<fmt:formatDate pattern="H_m_s" value="${scheduleItem.fromTime}" var="currentStartTime"/>
		<fmt:formatDate pattern="d"     value="${scheduleItem.fromTime}" var="currentDay"/>

		<c:if test="${empty loopDay or (currentDay != loopDay)}">
			<c:if test="${not empty loopDay}">
						</ul>
					</div>
					<div data-role="footer">
						<h4>&copy; 2013 AJUG</h4>
					</div>
				</div>
				</div>
			</c:if>
			<fmt:formatDate pattern="yyyy_MM_dd"     value="${scheduleItem.fromTime}" var="dayId"/>
			<div data-role="page" id="schedule-${dayId}" data-theme="b">
				<div data-role="header">
					<a href="#schedule-index">Back</a>
					<h1><fmt:formatDate pattern="EEEE MMMM d, yyyy" value="${scheduleItem.fromTime}"/></h1>
				</div>
				<div data-role="content" style="background-color: white;">
					<ul data-role="listview" data-inset="true" data-theme="c" data-dividertheme="b" data-filter="true">
		</c:if>

		<c:choose>
			<c:when test="${currentStartTime ne loopStartTime}">
				<c:set value="${currentStartTime}" var="loopStartTime"/>
				<li data-role="list-divider"><fmt:formatDate pattern="hh:mm" value="${scheduleItem.fromTime}" />-<fmt:formatDate pattern="hh:mm" value="${scheduleItem.toTime}" /></li>
			</c:when>
		</c:choose>
		<c:choose>
			<c:when test="${scheduleItem.scheduleItemType == scheduleItemTypeRegistration}">
				<li>
					<h3><c:out value="${scheduleItem.title}"/></h3>
					<p class="ui-li-aside">
						${scheduleItem.room.name}
					</p>
				</li>
			</c:when>
			<c:when test="${scheduleItem.scheduleItemType == scheduleItemTypeAdminsitrative}">
				<li>
					<h3><c:out value="${scheduleItem.title}"/></h3>
					<p class="ui-li-aside">
						${scheduleItem.room.name}
					</p>
				</li>
			</c:when>
			<c:when test="${scheduleItem.scheduleItemType == scheduleItemTypeBreak}">
				<li data-theme="e">Break
					<p class="ui-li-aside">
						${scheduleItem.room.name}
					</p>
				</li>
			</c:when>
			<c:when test="${scheduleItem.scheduleItemType == scheduleItemTypeSession}">
				<li>
					<h3><c:choose>
						<c:when test="${not empty scheduleItem.presentation}">
							<c:out value="${scheduleItem.presentation.title}"/>
						</c:when>
						<c:otherwise>
							<c:out value="${scheduleItem.title}" default="N/A"/>
						</c:otherwise>
					</c:choose></h3>
					<h3><c:choose>
						<c:when test="${not empty scheduleItem.presentation}">
							<c:out value="${scheduleItem.presentation.speaker.firstLastName}"/>
						</c:when>
						<c:otherwise>
							N/A
						</c:otherwise>
					</c:choose></h3>
					<p class="ui-li-aside">
						<strong>${scheduleItem.room.name}</strong>
						<c:if test="${not empty scheduleItem.room.track}">
							(<c:out value="${scheduleItem.room.track}"/>)
						</c:if>
					</p>
				</li>
			</c:when>
			<c:when test="${scheduleItem.scheduleItemType == scheduleItemTypeKeynote}">
				<li>
					<h3><c:choose>
						<c:when test="${not empty scheduleItem.presentation}">
							<c:out value="${scheduleItem.presentation.title}"/>
						</c:when>
						<c:otherwise>
							<c:out value="${scheduleItem.title}" default="N/A"/>
						</c:otherwise>
					</c:choose></h3>
					<h3><c:choose>
						<c:when test="${not empty scheduleItem.presentation}">
							<c:out value="${scheduleItem.presentation.speaker.firstLastName}"/>
						</c:when>
						<c:otherwise>
							N/A
						</c:otherwise>
					</c:choose></h3>
					<p class="ui-li-aside">
						${scheduleItem.room.name}
					</p>
				</li>
			</c:when>
		</c:choose>
		<c:set var="loopDay" value="${currentDay}"
	/></c:forEach>
	<c:if test="${not empty loopDay}">
			</ul>
		</div>
		</div>
		<div data-role="footer">
			<h4>&copy; 2013 AJUG</h4>
		</div>
	</c:if>
