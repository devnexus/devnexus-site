<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<head>
<title>${contextEvent.title}| Organizer Details for
	${organizer.firstLastName}</title>
</head>
<section id="speaker" class="bg-light-gray details"
	style="margin-top: 2em; text-align: left; padding-bottom: 0">
	<div class="row">
		<div class="col-sm-10 col-sm-offset-1">
			<div id="${organizer.firstName}_${organizer.lastName}"
				class="speaker-member row">
				<img src="${ctx}/s/organizers/${organizer.id}.jpg"
					class="img-responsive col-xs-3 col-xs-offset-1" alt="">
				<h4>
					<c:out value="${organizer.firstName}" />
					<c:out value="${organizer.lastName}" />
				</h4>

				<c:if test="${!empty organizer.twitterId}">
					<div>
						<span class="fa fa-twitter"></span>
						<a href="https://twitter.com/<c:out value="${organizer.twitterId}"/>"
							target="_blank">${organizer.twitterId}</a>
					</div>
				</c:if>
				<c:if test="${!empty organizer.linkedInId}">
					<div>
						<span class="fa fa-linkedin"></span>
						<a
							href="https://www.linkedin.com/in/<c:out value="${organizer.linkedInId}" />"
							target="_blank">${organizer.linkedInId}</a>
					</div>
				</c:if>
				<c:if test="${!empty organizer.githubId}">
					<div>
						<span class="fa fa-github"></span>
						<a
							href="https://github.com/<c:out value="${organizer.githubId}" />"
							target="_blank">${organizer.githubId}</a>
					</div>
				</c:if>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-10 col-sm-offset-1 ">
			<div class="biography-header">Biography</div>
			<div class="biography-body"><c:out value="${organizer.bioAsHtml}" escapeXml="false"/></div>
		</div>
	</div>
</section>


