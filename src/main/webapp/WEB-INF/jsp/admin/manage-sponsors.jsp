<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<title>Manage Sponsors</title>
<div style="margin-top: 20px" class="col-md-10 col-md-offset-1">
	<h2>Manage Sponsors</h2>
</div>
<div class="row">
	<div class="col-md-10 col-md-offset-1">
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Action</th><th>Name</th><th>Sponsor Level</th><th>Order</th>
				</tr>
			</thead>

			<c:forEach items="${sponsors}" var="sponsor">
				<tr>
					<td><a href="${ctx}${baseSiteUrl}/admin/sponsor/${sponsor.id}" class="btn btn-default"><span class="glyphicon glyphicon-edit"></span></a></td>
					<td><c:out value="${sponsor.name}"/></td>
					<td><c:out value="${sponsor.sponsorLevel.name}"/></td>
					<td><c:out value="${sponsor.sortOrder}"/></td>
				</tr>
			</c:forEach>
		</table>
		<a class="btn btn-default" href="${ctx}${baseSiteUrl}/admin/sponsor" role="button">Add Sponsor</a>
		<a class="btn btn-default" href="${ctx}${baseSiteUrl}/admin/index" role="button">Main Menu</a>
	</div>
</div>
