<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="com.devnexus.ting.model.ScheduleItemType"%>
<%@page import="com.devnexus.ting.model.PresentationType"%>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>
<% pageContext.setAttribute("scheduleItemTypeAdminsitrative", ScheduleItemType.ADMINISTRATIVE); %>
<% pageContext.setAttribute("scheduleItemTypeBreak", ScheduleItemType.BREAK); %>
<% pageContext.setAttribute("scheduleItemTypeKeynote", ScheduleItemType.KEYNOTE); %>
<% pageContext.setAttribute("scheduleItemTypeRegistration", ScheduleItemType.REGISTRATION); %>
<% pageContext.setAttribute("scheduleItemTypeSession", ScheduleItemType.SESSION); %>
<content tag='top'>
	<link href="${assetsUrl}/css/nav-pills.css" rel="stylesheet">
	<link href="${assetsUrl}/css/schedule.css" rel="stylesheet">
</content>


		<!-- intro -->
		<section id="about" class="module parallax parallax-3">
				<div class="container header">
						<div class="row centered">
								<div class="col-md-10 col-md-offset-1">
									 <div class="top-intro travel">
										<h4 class="section-white-title decorated"><span>Schedule</span></h4>
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
<div class="container schedule">
	<div class="row">
		<div class="col-md-10-offset-1">

	<c:set value="" var="loopStartTime"/>
	<c:set value="" var="loopDay"/>

	<c:forEach items="${scheduleItemList.scheduleItems}" var="scheduleItem">

		<fmt:formatDate pattern="H_m_s" value="${scheduleItem.fromTime}" var="currentStartTime"/>
		<fmt:formatDate pattern="d"     value="${scheduleItem.fromTime}" var="currentDay"/>

		<c:if test="${empty loopDay or (currentDay != loopDay)}">

			<c:if test="${not empty loopDay}">
				</div>
			</c:if>
			<div style="border: 1px solid red; margin-bottom: 1em;" class="schedule ">
				<h1 class="day"><fmt:formatDate pattern="EEEE MMMM d, yyyy" value="${scheduleItem.fromTime}"/></h1>
		</c:if>

		<div>
			<c:choose>
				<c:when test="${currentStartTime ne loopStartTime}">
					<c:set value="${currentStartTime}" var="loopStartTime"/>
				</c:when>
			</c:choose>
			<c:choose>
				<c:when test="${scheduleItem.scheduleItemType == scheduleItemTypeRegistration}">
					<div class="registration">REGISTRATION <c:out value="${scheduleItem.title} [${scheduleItem.room.name}]"/></div>
				</c:when>
				<c:when test="${scheduleItem.scheduleItemType == scheduleItemTypeAdminsitrative}">
					<div class="keynote"></div>
					<div class="row">
						<div class="col-md-10">
							<p class="topic">
								<c:out value="${scheduleItem.title}"/>
							</p>
						</div>
						<div class="col-md-2">
							<p><strong>${scheduleItem.room.name}</strong></p>
						</div>
					</div>
				</c:when>

				<c:when test="${scheduleItem.scheduleItemType == scheduleItemTypeSession}">
					<c:if test="${scheduleItem.rowspan > 1}">
						<div class="time"><fmt:formatDate pattern="hh:mm" value="${scheduleItem.fromTime}" /> - <fmt:formatDate pattern="hh:mm" value="${scheduleItem.toTime}" /></div>
						<div class="breakouts" rowspan="${scheduleItem.rowspan}">Breakouts</div>
					</c:if>

					<div class="talk">
						<c:if test="${scheduleItem.scheduleItemType == scheduleItemTypeBreak}">
							<div class="break"><c:out value="${scheduleItem.title}" default="Break"/></div>
						</c:if>

						<div class="row">
							<div class="col-md-10">
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
												<p>${speaker.firstName} ${speaker.lastName}</p>
											</c:forEach>
										</c:when>
										<c:otherwise>
											N/A
										</c:otherwise>
									</c:choose>
								</p>
							</div>
							<div class="col-md-2">
								<div class="${scheduleItem.room.cssStyleName}">
									<p><strong>${scheduleItem.room.name}</strong></p>
									<c:if test="${not empty scheduleItem.room.track}">
										<p><c:out value="${scheduleItem.room.track}"/></p>
									</c:if>
								</div>
							</div>
						</div>
					</div>
				</c:when>
				<c:when test="${scheduleItem.scheduleItemType == scheduleItemTypeKeynote}">
					<div class="row">
						<div class="col-md-10">
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
						</div>
						<div class="col-md-2">
							<div class="${scheduleItem.room.cssStyleName}">
								<p><strong>${scheduleItem.room.name}</strong></p>
								<c:if test="${not empty scheduleItem.room.track}">
									<p><c:out value="${scheduleItem.room.track}"/></p>
								</c:if>
							</div>
						</div>
					</div>
				</c:when>
			</c:choose>
		</div>

		<c:set var="loopDay" value="${currentDay}" />

	</c:forEach>

	<c:if test="${not empty loopDay}">
			</div>
	</c:if>


		</div>
	</div>
</div>

		<!-- questions -->
		<section class="white">
			<div class="top-intro questions">
				<h4>Questions?</h4>
				<h3>Contact us at info@ajug.org</h3>
			</div>
		</section>
