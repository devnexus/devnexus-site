<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>
<% pageContext.setAttribute("lf", "\n"); %>

<title>DevNexus 2014 - Call for Paper Submissions</title>
	<h2>Call for Paper Submissions</h2>

	<table class="table table-hover">
		<thead>
			<tr>
				<th>Name</th><th>Title</th><th>Topic</th><th>Type</th><th>Skill Level</th><th>Status</th>
			</tr>
		</thead>

		<c:forEach items="${cfpSubmissionList.cfpSubmissions}" var="cfp">
			<tr>
				<td><c:out value="${cfp.lastName}"/>, <c:out value="${cfp.firstName}"/></td>
				<td><c:out value="${cfp.title}"/></td>
				<td><c:out value="${cfp.topic}"/></td>
				<td><c:out value="${cfp.presentationType}"/></td>
				<td><c:out value="${cfp.skillLevel}"/></td>
				<td>STATUS TBD</td>
			</tr>
		</c:forEach>
	</table>


