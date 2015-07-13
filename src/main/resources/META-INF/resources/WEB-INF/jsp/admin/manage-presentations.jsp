<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<title>Manage Presentations</title>

<!-- intro -->
<section id="about" class="module parallax parallax-3">
	<div class="container header">
		<div class="row centered">
			<div class="col-md-10 col-md-offset-1">
				<div class="top-intro travel">
					<h4 class="section-white-title decorated"><span>Manage ${event.eventKey} Presentations</span></h4>
					<h5 class="intro-white-lead">There are ${presentationList.numberOfPresentations} presentations.</h5>
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
					<th>Action</th><th>Title</th><th>Speaker</th><th>Track</th><th>Tags</th>
				</tr>
			</thead>

			<c:forEach items="${presentationList.presentations}" var="presentation">
				<tr>
					<td><a href="${ctx}${baseSiteUrl}/admin/${event.eventKey}/presentation/${presentation.id}" class="btn btn-default"><span class="glyphicon glyphicon-edit"></span></a></td>
					<td><c:out value="${presentation.title}"/></td>
					<td>
						<c:forEach items="${presentation.speakers}" var="speaker">
							<p><c:out value="${speaker.firstLastName}"/></p>
						</c:forEach>
					</td>
					<td><c:if test="${not empty presentation.track}">
						<c:out value="${presentation.track.name}"/>
					</c:if></td>
					<td><span class="badge"><c:out value="${presentation.presentationTags.size()}"/></span></td>
				</tr>
			</c:forEach>
		</table>
		<a class="btn btn-default" href="${ctx}${baseSiteUrl}/admin/${event.eventKey}/presentation" role="button">Add Presentation</a>
		<a class="btn btn-default" href="${ctx}${baseSiteUrl}/admin/${event.eventKey}/index" role="button">Main Menu</a>
	</div>
</div>

