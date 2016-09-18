<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<title>Administration</title>

<!-- intro -->
<section id="about" class="module parallax parallax-3 index--parallax">
	<div class="container header">
		<div class="row centered">
			<div class="col-md-10 col-md-offset-1">
				<div class="top-intro travel">
					<h4 class="section-white-title decorated"><span>Administration</span></h4>
					<h5 class="intro-white-lead">${event.title}
					<c:if test="${event.current}">
						(current)
					</c:if>
					</h5>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- /intro -->

<div class="row">
	<div class="col-md-8 col-md-offset-2">
		<p>Welcome <sec:authentication property="principal.firstName"/> (Logged-in since: <sec:authentication property="principal.lastLoginDate"/>)</p>
		<form:form id="event" class="form-horizontal" role="form" method="post" modelAttribute="event" action="${ctx}/s/admin/index">
			<spring:bind path="event.id">
				<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
			</spring:bind>
			<div class="form-group${errorClass}">
				<label for="eventId" class="col-lg-4 control-label">Select Event</label>
				<div class="col-lg-4">
					<div class="input-group">
						<form:select cssClass="form-control" path="id" id="eventId" tabindex="1">
							<form:option value="" label="Please Select an Event" />
							<form:options items="${events}" itemLabel="title" itemValue="id"/>
						</form:select>
					</div>
					<form:errors path="id" cssClass="fieldError" />
				</div>
				<div class="col-lg-2">
					<button class="btn btn-default" type="submit">Go!</button>
				</div>
			</div>
		</form:form>
	</div>
</div>

<c:if test="${not empty successMessage}">
	<div class="row" style="margin-top: 2em;">
		<div class="col-md-10 col-md-offset-1">
			<div class="alert alert-success text-center" role="alert"><c:out value="${successMessage}"></c:out></div>
		</div>
	</div>
</c:if>

<sec:authorize access="hasRole('CFP_REVIEWER') or hasRole('ADMIN')">
<div class="row">
	<div class="col-md-4 col-md-offset-2">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Manage - <c:out value="${event.title}"></c:out></h3>
			</div>
			<div class="panel-body">
				<ul>
					<sec:authorize access="hasRole('ADMIN')">

						<li><a href="${ctx}${baseSiteUrl}/admin/${eventKey}/speakers">Manage Speakers</a></li>
						<li><a href="${ctx}${baseSiteUrl}/admin/${eventKey}/sponsors">Manage Sponsors</a></li>
						<li><a href="${ctx}${baseSiteUrl}/admin/${eventKey}/presentations">Manage Presentations</a></li>
						<li><a href="${ctx}${baseSiteUrl}/admin/${eventKey}/manage-schedule">Manage Schedule</a></li>
						<li><a href="${ctx}${baseSiteUrl}/admin/${eventKey}/evaluations">Show Evaluations</a></li>
						<li><a href="${ctx}${baseSiteUrl}/admin/${eventKey}/download-accepted-speakers">Download Accepted Speakers (CSV)</a></li>
						<li><a href="${ctx}${baseSiteUrl}/admin/${eventKey}/download-rejected-speakers">Download Rejected Speakers (CSV)</a></li>
						<li><a href="${ctx}${baseSiteUrl}/admin/${eventKey}/download-schedule">Download Schedule (CSV)</a></li>
						<li><a href="${ctx}${baseSiteUrl}/admin/${eventKey}/upload-schedule">Upload Schedule (CSV)</a></li>
					</sec:authorize>
					<sec:authorize access="hasRole('CFP_REVIEWER') or hasRole('ADMIN')">
						<li><a href="${ctx}${baseSiteUrl}/admin/${eventKey}/cfps">Manage Call for Papers</a></li>
						<li><a href="${ctx}${baseSiteUrl}/admin/${eventKey}/cfps.json">Call for Papers (JSON)</a></li>
						<li><a href="${ctx}${baseSiteUrl}/admin/${eventKey}/cfps.xml">Call for Papers (XML)</a></li>
					</sec:authorize>
				</ul>
			</div>
		</div>
	</div>
	<div class="col-md-4 col-md-offset-0">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Other Tasks</h3>
			</div>
			<div class="panel-body">
				<ul>
					<sec:authorize access="hasRole('ADMIN')">
						<li><a href="${ctx}${baseSiteUrl}/admin/events">Manage All Events</a></li>
						<li><a href="${ctx}${baseSiteUrl}/admin/presentations">Manage All Presentations</a></li>
						<li><a href="${ctx}${baseSiteUrl}/admin/speakers">Manage All Speakers</a></li>
						<li><a href="${ctx}${baseSiteUrl}/admin/organizers">Manage Organizers</a></li>
					</sec:authorize>
				</ul>
			</div>
		</div>
	</div>
		<div class="col-md-4 col-md-offset-0">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title"><c:out value="${event.title}"/>  Registration</h3>
			</div>
			<div class="panel-body">
				<ul>
					<sec:authorize access="hasRole('ADMIN')">
						<li><a href="${ctx}${baseSiteUrl}/admin/${eventKey}/registration">Manage Tickets For Sale</a></li>
						<li><a href="${ctx}${baseSiteUrl}/admin/${eventKey}/dashboard">Dashboard</a></li>
						<li><a href="${ctx}${baseSiteUrl}/admin/${eventKey}/reporting">Reporting</a></li>
						<li><a href="${ctx}${baseSiteUrl}/admin/${eventKey}/editRegistration">Edit Registrations</a></li>
					</sec:authorize>
				</ul>
			</div>
		</div>
	</div>
</div>
</sec:authorize>

<div class="row">
	<div class="col-md-4 col-md-offset-2">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Security</h3>
			</div>
			<div class="panel-body">
				<ul>
					<li><a href="${ctx}${baseSiteUrl}/admin/change-password">Change Password</a></li>
					<li><a href="${ctx}${baseSiteUrl}/logout">Logout</a> (<sec:authentication property="principal.username"/>)</li>
				</ul>
			</div>
		</div>
	</div>
	<sec:authorize access="hasRole('ADMIN')">
		<div class="col-md-4 col-md-offset-0">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">Miscellaneous</h3>
				</div>
				<div class="panel-body">
					<ul>
						<li><a href="${ctx}${baseSiteUrl}/admin/update-application-cache">Update HTML5 Application Cache Manifest</a></li>
						<li><a href="${ctx}${baseSiteUrl}/admin/reset-spring-cache">Reset Spring Cache</a></li>
					</ul>
				</div>
			</div>
		</div>
	</sec:authorize>
	<sec:authorize access="hasRole('APP_USER')">
		<div class="row">
			<div class="col-md-8 col-md-offset-2">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">My Tasks</h3>
					</div>
					<div class="panel-body">
						<ul>
							<li><a href="${ctx}${baseSiteUrl}/cfp/index">My CFPs</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</sec:authorize>
	<sec:authorize access="hasRole('ADMIN')">
	<div class="row">
		<div class="col-md-8 col-md-offset-2">
			<h4>Cache Status</h4>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Cachename</th><th>Hit Count</th><th>Hit Rate</th><th>Eviction Count</th><th>Miss Count</th>
					</tr>
				</thead>
				<c:forEach items="${cacheStats}" var="cacheStat">
					<tr>
						<td>${cacheStat.key}</td>
						<td>${cacheStat.value.hitCount()}</td>
						<td>${cacheStat.value.hitRate()}</td>
						<td>${cacheStat.value.evictionCount()}</td>
						<td>${cacheStat.value.missCount()}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	</sec:authorize>
</div>
