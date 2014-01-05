<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<title>Manage Organizers</title>
<div style="margin-top: 20px" class="col-md-10 col-md-offset-1">
	<h2>Manage Organizers</h2>
</div>
<div class="row">
	<div class="col-md-10 col-md-offset-1">
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Action</th><th>First Name</th><th>Last Name</th><th>Order</th>
				</tr>
			</thead>

			<c:forEach items="${organizers}" var="organizer">
				<tr>
					<td><a href="${ctx}${baseSiteUrl}/admin/organizer/${organizer.id}" class="btn btn-default"><span class="glyphicon glyphicon-edit"></span></a></td>
					<td><c:out value="${organizer.firstName}"/></td>
					<td><c:out value="${organizer.lastName}"/></td>
					<td><c:out value="${organizer.sortOrder}"/></td>
				</tr>
			</c:forEach>
		</table>
		<a class="btn btn-default" href="${ctx}${baseSiteUrl}/admin/organizer" role="button">Add Organizer</a>
		<a class="btn btn-default" href="${ctx}${baseSiteUrl}/admin/index" role="button">Main Menu</a>
	</div>
</div>
