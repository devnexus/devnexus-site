<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<title>Manage Presentations</title>
<div style="margin-top: 20px" class="col-md-10 col-md-offset-1">
	<h2>Manage Presentations</h2>
</div>
<div class="row">
	<div class="col-md-10 col-md-offset-1">
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Action</th><th>Event</th><th>Title</th><th>Speaker</th><th>Track</th>
				</tr>
			</thead>

			<c:forEach items="${presentations}" var="presentation">
				<tr>
					<td><a href="${ctx}${baseSiteUrl}/admin/presentation/${presentation.id}" class="btn btn-default"><span class="glyphicon glyphicon-edit"></span></a></td>
					<td><c:out value="${presentation.event.eventKey}"/></td>
					<td><c:out value="${presentation.title}"/></td>
					<td><c:out value="${presentation.speaker.firstLastName}"/></td>
					<td><c:if test="${not empty presentation.track}">
						<c:out value="${presentation.track.name}"/>
					</c:if></td>
				</tr>
			</c:forEach>
		</table>
		<a class="btn btn-default" href="${ctx}${baseSiteUrl}/admin/presentation" role="button">Add Presentation</a>
		<a class="btn btn-default" href="${ctx}${baseSiteUrl}/admin/index" role="button">Main Menu</a>
	</div>
</div>

