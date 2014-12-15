<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<title>Administration</title>

<!-- intro -->
<section id="about" class="module parallax parallax-3">
	<div class="container header">
		<div class="row centered">
			<div class="col-md-10 col-md-offset-1">
				<div class="top-intro travel">
					<h4 class="section-white-title decorated"><span>Administration</span></h4>
					<h5 class="intro-white-lead">File your TPS report on time.</h5>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- /intro -->

<div class="row">
	<div class="col-md-4 col-md-offset-2">
		<p>Welcome <sec:authentication property="principal.username"/> (Logged-in since: <sec:authentication property="principal.lastLoginDate"/>)</p>
	</div>
</div>
<div class="row">
	<div class="col-md-4 col-md-offset-2">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Current Event - <c:out value="${currentEvent.title}"></c:out></h3>
			</div>
			<div class="panel-body">
				<ul>
					<sec:authorize access="hasRole('ADMIN')">
						<li><a href="${ctx}${baseSiteUrl}/admin/speakers?eventId=${currentEvent.id}">Manage Speakers</a></li>
						<li><a href="${ctx}${baseSiteUrl}/admin/cfps">Manage Call for Papers</a></li>
						<li><a href="${ctx}${baseSiteUrl}/admin/sponsors">Manage Sponsors</a></li>
						<li><a href="${ctx}${baseSiteUrl}/admin/presentations?eventId=${currentEvent.id}">Manage Presentations</a></li>
						<li><a href="${ctx}${baseSiteUrl}/admin/manage-schedule?eventId=${currentEvent.id}">Manage Schedule</a></li>
						<li><a href="${ctx}${baseSiteUrl}/admin/evaluations">Show Evaluations for Current Event</a></li>
					</sec:authorize>
				</ul>
			</div>
		</div>
	</div>
	<div class="col-md-4 col-md-offset-0">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">All Events</h3>
			</div>
			<div class="panel-body">
				<ul>
					<sec:authorize access="hasRole('ADMIN')">
						<li><a href="${ctx}${baseSiteUrl}/admin/events">Manage Events</a></li>
						<li><a href="${ctx}${baseSiteUrl}/admin/presentations">Manage Presentations</a></li>
						<li><a href="${ctx}${baseSiteUrl}/admin/speakers">Manage Speakers</a></li>
						<li><a href="${ctx}${baseSiteUrl}/admin/organizers">Manage Organizers</a></li>
					</sec:authorize>
					<sec:authorize access="hasRole('CFP_REVIEWER') or hasRole('ADMIN')">
						<li><a href="${ctx}${baseSiteUrl}/admin/cfps">Manage Call for Papers</a></li>
						<li><a href="${ctx}${baseSiteUrl}/admin/cfps.json">Call for Papers (JSON)</a></li>
						<li><a href="${ctx}${baseSiteUrl}/admin/cfps.xml">Call for Papers (XML)</a></li>
					</sec:authorize>
				</ul>
			</div>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-md-4 col-md-offset-2">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Security</h3>
			</div>
			<div class="panel-body">
				<ul>
					<li><a href="${ctx}${baseSiteUrl}/logout">Logout</a> (<sec:authentication property="principal.username"/>)</li>
				</ul>
			</div>
		</div>
	</div>
	<div class="col-md-4 col-md-offset-0">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Miscellaneous</h3>
			</div>
			<div class="panel-body">
				<ul>
					<li><a href="${ctx}${baseSiteUrl}/admin/update-application-cache">Update HTML5 Application Cache Manifest</a></li>
				</ul>
			</div>
		</div>
	</div>
</div>
