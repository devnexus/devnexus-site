<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<title>Manage Speakers</title>

<!-- intro -->
<section id="about" class="module parallax parallax-3">
	<div class="container header">
		<div class="row centered">
			<div class="col-md-10 col-md-offset-1">
				<div class="top-intro travel">
					<h4 class="section-white-title decorated"><span>Manage Speakers</span></h4>
					<h5 class="intro-white-lead">There are ${speakerList.numberOfSpeakers} speakers.</h5>
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
					<th>Action</th><th>First Name</th><th>Last Name</th><th class="text-center">Has Picture?</th>
				</tr>
			</thead>

			<c:forEach items="${speakerList.speakers}" var="speaker">
				<tr>
					<td><a href="${ctx}${baseSiteUrl}/admin/${event.eventKey}/speaker/${speaker.id}" class="btn btn-default"><span class="glyphicon glyphicon-edit"></span></a></td>
					<td><c:out value="${speaker.firstName}"/></td>
					<td><c:out value="${speaker.lastName}"/></td>
					<td class="text-center">
						<c:if test="${not empty speaker.picture}"><span class="glyphicon glyphicon-user text-success"></span></c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
		<a class="btn btn-default" href="${ctx}${baseSiteUrl}/admin/speaker" role="button">Add Speaker</a>
		<a class="btn btn-default" href="${ctx}${baseSiteUrl}/admin/${event.eventKey}/index" role="button">Main Menu</a>
	</div>
</div>
