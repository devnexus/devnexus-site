<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<title>Manage Speakers</title>
<div style="margin-top: 20px" class="col-md-10 col-md-offset-1">
	<h2>Manage Speakers</h2>
</div>
<div class="row">
	<div class="col-md-10 col-md-offset-1">
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Action</th><th>First Name</th><th>Last Name</th>
				</tr>
			</thead>

			<c:forEach items="${speakers}" var="speaker">
				<tr>
					<td><a href="${ctx}${baseSiteUrl}/admin/speaker/${speaker.id}" class="btn btn-default"><span class="glyphicon glyphicon-edit"></span></a></td>
					<td><c:out value="${speaker.firstName}"/></td>
					<td><c:out value="${speaker.lastName}"/></td>
				</tr>
			</c:forEach>
		</table>
		<a class="btn btn-default" href="${ctx}${baseSiteUrl}/admin/speaker" role="button">Add Speaker</a>
		<a class="btn btn-default" href="${ctx}${baseSiteUrl}/admin/index" role="button">Main Menu</a>
	</div>
</div>
