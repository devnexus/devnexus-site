<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="com.devnexus.ting.model.CfpSubmissionStatusType" %>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>
<% pageContext.setAttribute("lf", "\n"); %>

<% pageContext.setAttribute("acceptedCfpStatus", CfpSubmissionStatusType.ACCEPTED); %>
<% pageContext.setAttribute("rejectedCfpStatus", CfpSubmissionStatusType.REJECTED); %>
<% pageContext.setAttribute("pendingCfpStatus", CfpSubmissionStatusType.PENDING); %>

<title>Manage Call for Paper Submissions</title>

<!-- intro -->
<section id="about" class="module parallax parallax-3">
	<div class="container header">
		<div class="row centered">
			<div class="col-md-10 col-md-offset-1">
				<div class="top-intro travel">
					<h4 class="section-white-title decorated"><span>Manage Call for Paper Submissions</span></h4>
					<h5 class="intro-white-lead">There are ${cfpSubmissionList.numberOfCfps} CFPs.</h5>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- /intro -->

<div class="row" style="margin-top: 1em;">
	<div class="col-md-10 col-md-offset-1">
		<table class="table table-striped table-bordered table-hover">
			<tbody>
				<tr>
					<td>Number of CFPs</td>
					<td>${cfpSubmissionList.numberOfCfps}</td>
				</tr>
				<tr>
					<td>Number of Accepted CFPs</td>
					<td>${cfpSubmissionList.numberOfAcceptedCfps}</td>
				</tr>
				<tr>
					<td>Number of Rejected CFPs</td>
					<td>${cfpSubmissionList.numberOfRejectedCfps}</td>
				</tr>
				<tr>
					<td>Number of Pending CFPs</td>
					<td>${cfpSubmissionList.numberOfPendingCfps}</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>
<div class="row">
	<div class="col-md-12">
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Action</th><th>Name</th><th>Title</th><th>Topic</th><th>Type</th><th>Skill Level</th><th>Status</th><th>Cost</th><th>Location(s)</th>
				</tr>
			</thead>

			<c:forEach items="${cfpSubmissionList.cfpSubmissions}" var="cfp">

				<c:choose>
					<c:when test="${cfp.status == acceptedCfpStatus}">
						<c:set var="cfpStatusClass" value="success"/>
					</c:when>
					<c:when test="${cfp.status == rejectedCfpStatus}">
						<c:set var="cfpStatusClass" value="warning"/>
					</c:when>
					<c:otherwise>
						<c:set var="cfpStatusClass" value=""/>
					</c:otherwise>
				</c:choose>

				<tr id="cfp_${cfp.id}" class="${cfpStatusClass}">
					<td style="width: 150px;">
						<a href="${ctx}${baseSiteUrl}/admin/${event.eventKey}/cfps/${cfp.id}" class="btn btn-default" title="Edit"><span class="glyphicon glyphicon-edit"></span></a>
						<c:if test="${cfp.status == pendingCfpStatus or empty cfp.status}">
							<a href="${ctx}${baseSiteUrl}/admin/${event.eventKey}/cfps/${cfp.id}/accept" class="btn btn-default" title="Accept"><span class="glyphicon glyphicon-ok"></span></a>
						</c:if>
					</td>
					<td>
						<c:forEach var="speaker" items="${cfp.speakers}">
							<p><c:out value="${speaker.lastName}"/>, <c:out value="${speaker.firstName}"/> <a href="${ctx}${baseSiteUrl}/admin/${event.eventKey}/cfps/speaker-image/${speaker.id}">Image</a><p>
						</c:forEach>
					</td>
					<td><c:out value="${cfp.title}"/></td>
					<td><small><c:out value="${cfp.topic}"/></small></td>
					<td><c:out value="${cfp.presentationType}"/></td>
					<td><c:out value="${cfp.skillLevel}"/></td>
					<td><c:out value="${cfp.status}"/></td>
					<td>
						<c:if test="${cfp.speakerRequiresTravelCostReimburment()}"><span class="glyphicon glyphicon-usd text-danger"></span></c:if>
						<c:if test="${not empty cfp.slotPreference}"><span class="glyphicon glyphicon-info-sign text-success"></span></c:if>
					</td>
					<td><c:out value="${cfp.speakerLocation}"/></td>
				</tr>
			</c:forEach>
		</table>
		<a class="btn btn-default" href="${ctx}${baseSiteUrl}/admin/${event.eventKey}/index" role="button">Main Menu</a>
	</div>
</div>

