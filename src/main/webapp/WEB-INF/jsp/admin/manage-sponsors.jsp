<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<title>Manage Sponsors</title>

<!-- intro -->
<section id="about" class="module parallax parallax-3">
	<div class="container header">
		<div class="row centered">
			<div class="col-md-10 col-md-offset-1">
				<div class="top-intro travel">
					<h4 class="section-white-title decorated"><span>Manage Sponsors</span></h4>
					<h5 class="intro-white-lead">Thank you!</h5>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- /intro -->

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
					<td><a href="${ctx}${baseSiteUrl}/admin/${eventKey}/sponsor/${sponsor.id}" class="btn btn-default"><span class="glyphicon glyphicon-edit"></span></a></td>
					<td><c:out value="${sponsor.name}"/></td>
					<td><c:out value="${sponsor.sponsorLevel.name}"/></td>
					<td><c:out value="${sponsor.sortOrder}"/></td>
				</tr>
			</c:forEach>
		</table>
		<a class="btn btn-default" href="${ctx}${baseSiteUrl}/admin/${eventKey}/sponsor" role="button">Add Sponsor</a>
		<a class="btn btn-default" href="${ctx}${baseSiteUrl}/admin/${eventKey}/index" role="button">Main Menu</a>
	</div>
</div>
