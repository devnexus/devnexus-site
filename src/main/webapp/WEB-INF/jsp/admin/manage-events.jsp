<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<title>Manage Events</title>
<div style="margin-top: 20px" class="col-md-10 col-md-offset-1">
	<h2>Manage Events</h2>
</div>
<div class="row">
	<div class="col-md-10 col-md-offset-1">
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Action</th><th class="text-center">Current Event</th><th>Key</th><th>Title</th>
				</tr>
			</thead>

			<c:forEach items="${events}" var="event">

				<c:choose>
					<c:when test="${event.current}">
						<c:set var="eventClass" value="success"/>
					</c:when>
				</c:choose>

				<tr class="${eventClass}">
					<td><a href="${ctx}${baseSiteUrl}/admin/event/${event.id}" class="btn btn-default"><span class="glyphicon glyphicon-edit"></span></a></td>
					<td class="text-center">
						<c:choose>
							<c:when test="${event.current}">
								<span class="glyphicon glyphicon-ok"></span>
							</c:when>
							<c:otherwise>
								<span class="glyphicon glyphicon-remove"></span>
							</c:otherwise>
						</c:choose>
					</td>
					<td><c:out value="${event.eventKey}"/></td>
					<td><c:out value="${event.title}"/></td>
				</tr>
			</c:forEach>
		</table>
		<a class="btn btn-default" href="${ctx}${baseSiteUrl}/admin/event" role="button">Add Event</a>
		<a class="btn btn-default" href="${ctx}${baseSiteUrl}/admin/index" role="button">Main Menu</a>
	</div>
</div>

