<%@page import="com.devnexus.ting.core.model.CfpSubmissionStatusType" %>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>
<% pageContext.setAttribute("lf", "\n"); %>

<% pageContext.setAttribute("acceptedCfpStatus", CfpSubmissionStatusType.ACCEPTED); %>
<% pageContext.setAttribute("rejectedCfpStatus", CfpSubmissionStatusType.REJECTED); %>

<title>Manage Call for Paper Submissions</title>
<div style="margin-top: 20px" class="col-md-10 col-md-offset-1">
	<h2>Manage Call for Paper Submissions</h2>
</div>

<div class="row">
	<div class="col-md-10 col-md-offset-1">
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Action</th><th>Name</th><th>Title</th><th>Topic</th><th>Type</th><th>Skill Level</th><th>Status</th>
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

				<tr class="${cfpStatusClass}">
					<td>
						<a href="${ctx}${baseSiteUrl}/admin/cfps/${cfp.id}" class="btn btn-default"><span class="glyphicon glyphicon-edit"></span></a>
						<a href="${ctx}${baseSiteUrl}/admin/cfps/${cfp.id}/accept" class="btn btn-default">Accept</a>
					</td>
					<td>
						<c:forEach var="speaker" items="${cfp.speakers}">
							<p><c:out value="${speaker.lastName}"/>, <c:out value="${speaker.firstName}"/><p>
						</c:forEach>
					</td>
					<td><c:out value="${cfp.title}"/></td>
					<td><c:out value="${cfp.topic}"/></td>
					<td><c:out value="${cfp.presentationType}"/></td>
					<td><c:out value="${cfp.skillLevel}"/></td>
					<td><c:out value="${cfp.status}"/></td>
				</tr>
			</c:forEach>
		</table>
		<a class="btn btn-default" href="${ctx}${baseSiteUrl}/admin/index" role="button">Main Menu</a>
	</div>
</div>

