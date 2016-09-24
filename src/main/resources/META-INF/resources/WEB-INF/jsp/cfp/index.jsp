<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<title>${contextEvent.title} | Submit CFP</title>


<div class="row">
	<div class="col-md-10 col-md-offset-1" style="padding-top: 4em;">
		<p>Welcome <sec:authentication property="principal.firstName"/>
		<%-- (Logged-in since: <sec:authentication property="principal.lastLoginDate"/>) --%> | <a href="${ctx}${baseSiteUrl}/logout">Logout</a></p>

		<c:if test="${not empty successMessage}">
			<div class="row" style="margin-top: 2em;">
				<div class="col-md-10 col-md-offset-1">
					<div class="alert alert-success text-center" role="alert"><c:out value="${successMessage}"></c:out></div>
				</div>
			</div>
		</c:if>

	<sec:authorize access="hasRole('APP_USER')">
		<div class="row">
			<div class="row">
				<div class="col-md-10 col-md-offset-1">
					<h4>My Speakers</h4>
					<table class="table table-hover">
						<thead>
							<tr>
								<th>Action</th><th>Name</th><th>Company</th><th>Location</th>
							</tr>
						</thead>
						<c:choose>
							<c:when test="${empty cfpSubmissionSpeakers}">
								<tr>
									<td colspan="5">You have not added any speakers yet.</td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach items="${cfpSubmissionSpeakers}" var="speaker">
									<tr>
										<td>
											<a href="${ctx}${baseSiteUrl}/cfp/speaker/${speaker.id}" class="btn btn-default" title="Edit"><span class="glyphicon glyphicon-edit"></span></a>
										</td>
										<td>${speaker.firstLastName}</td>
										<td>${speaker.company}</td>
										<td>${speaker.location}</td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</table>
				</div>
			</div>
			<div class="row">
				<div class="col-md-10 col-md-offset-1">
					<h4>My Abstracts</h4>
					<table class="table table-hover">
						<thead>
							<tr>
								<th>Action</th><th>Speaker(s)</th><th>Title</th><th>Topic</th><th>Type</th><th>Skill Level</th><th>Status</th>
							</tr>
						</thead>
						<c:choose>
							<c:when test="${empty cfpSubmissions}">
								<tr>
									<td colspan="7">You have not added any abstracts yet.</td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach items="${cfpSubmissions}" var="sessionAbstract">
									<tr>
										<td>
											<a href="${ctx}${baseSiteUrl}/cfp/abstract/${sessionAbstract.id}" class="btn btn-default" title="Edit"><span class="glyphicon glyphicon-edit"></span></a>
										</td>
										<td><c:forEach var="speaker" items="${sessionAbstract.cfpSubmissionSpeakers}">
												<p><c:out value="${speaker.firstName}"/> <c:out value="${speaker.lastName}"/></p>
											</c:forEach>
										</td>
										<td>${sessionAbstract.title}</td>
										<td>${sessionAbstract.topic}</td>
										<td>${sessionAbstract.presentationType}</td>
										<td>${sessionAbstract.skillLevel}</td>
										<td>${sessionAbstract.status}</td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</table>
				</div>
			</div>
			<div class="row" style="margin-bottom: 2em;">
				<div class="col-md-5 col-md-offset-1">
					<a href="${ctx}${baseSiteUrl}/cfp/speaker" class=" btn btn-primary btn-block">Add Speaker</a>
				</div>
				<div class="col-md-5">
					<a href="${ctx}${baseSiteUrl}/cfp/abstract" class=" btn btn-primary btn-block">Add Abstract</a>
				</div>
			</div>
		</div>
	</sec:authorize>
	</div>
</div>

<%@ include file="/WEB-INF/jsp/cfp/cfp-include-modal.jsp"%>
