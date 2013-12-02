<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<title>Main Admin Area</title>

<div id="devnex" class="jumbotron">
	<div class="container">
		<div id="banner">
			<h1><strong>Administration</strong></h1>
		</div>
	</div>
</div>

<div class="row">
	<div class="col-md-8 col-md-offset-2">
		<ul>
			<sec:authorize access="hasRole('ADMIN')">
				<li><a href="${ctx}${baseSiteUrl}/admin/events">Manage Events</a></li>
				<li><a href="${ctx}${baseSiteUrl}/admin/presentations">Manage Presentations</a></li>
				<li><a href="${ctx}${baseSiteUrl}/admin/speakers">Manage Speakers</a></li>
				<li><a href="${ctx}${baseSiteUrl}/admin/organizers">Manage Organizers</a></li>
				<li><a href="${ctx}${baseSiteUrl}/admin/organizers">Manage Organizers</a></li>
				<li><a href="${ctx}${baseSiteUrl}/admin/evaluations">Show Evaluations for Current Event</a></li>
				<li><a href="${ctx}${baseSiteUrl}/admin/update-application-cache">Update HTML5 Application Cache Manifest</a></li>
			</sec:authorize>
			<sec:authorize access="hasRole('CFP_REVIEWER') or hasRole('ADMIN')">
				<li><a href="${ctx}${baseSiteUrl}/admin/cfps">Manage Call for Papers</a></li>
				<li><a href="${ctx}${baseSiteUrl}/admin/cfps.json">Call for Papers (JSON)</a></li>
				<li><a href="${ctx}${baseSiteUrl}/admin/cfps.xml">Call for Papers (XML)</a></li>
			</sec:authorize>
			<li><a href="${ctx}${baseSiteUrl}/logout">Logout</a></li>
		</ul>
	</div>
</div>
