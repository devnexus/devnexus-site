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

<c:set var="pageTitle" value="Schedule"/>
<title>${contextEvent.title} | ${pageTitle}</title>

<section class="container-fluid" >
	<div class="row" style="margin-top: 1em;">
		<div class="col-xs-10 col-xs-offset-1 text-center">
			<sec:authorize access="isAuthenticated()">
				<a class="btn btn-default" href="${ctx}${baseSiteUrl}/logout"><i class="fa fa-sign-out"></i> Logout <sec:authentication property="principal.firstName"/></a>
				<a class="btn btn-default" href="${ctx}/s/${event.eventKey}/user-schedule"><i class="fa fa-user"></i> View User Schedule</a>
			</sec:authorize>
			<sec:authorize access="!isAuthenticated()">
				<a class="btn btn-default btn-social btn-google" href="/auth/google?scope=profile&state=schedule"><i class="fa fa-google"></i> Login with Google to create Custom Schedule</a>
			</sec:authorize>
			<a class="btn btn-default" href="${ctx}/s/${event.eventKey}/schedule.pdf"><i class="fa fa-file-pdf-o"></i> Download Full Schedule as PDF</a>
		</div>
	</div>
</section>
<%@ include file="/WEB-INF/jsp/schedule-include.jsp" %>

