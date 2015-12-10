<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="com.devnexus.ting.model.PresentationType"%>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>
<% pageContext.setAttribute("keynoteType", PresentationType.KEYNOTE); %>

<head>
	<title>${contextEvent.title} | Speaker Details for ${speaker.firstLastName}</title>
</head>

	<!-- intro -->
	<section id="about" class="module parallax parallax-3">
		<div class="container header">
				<div class="row centered">
					<div class="col-md-10 col-md-offset-1">
						 <div class="top-intro travel">
							<h4 class="section-white-title decorated"><span>Speaker Details</span></h4>
						</div>
					</div>
				</div>
		</div>
	</section>
	<!-- /intro -->

	<section id="speaker" class="bg-light-gray" style="margin-top: 2em; text-align: left; padding-bottom: 0">
		<div class="row">
			<div class="col-sm-6 col-sm-offset-3">
				<div id="${speaker.firstName}_${speaker.lastName}" class="speaker-member">
					<img src="${speaker.pictureSerialized}" class="img-responsive img-circle" alt="">
					<h4 class="text-center"><c:out value="${speaker.firstName}"/> <c:out value="${speaker.lastName}"/></h4>
					<c:if test="${!empty speaker.presentations}">
						<ul>
						<c:forEach var="presentation" items="${speaker.presentations}" varStatus="loop">
							<li class="text-center">
								<a href="${siteUrl}/presentations#id-${presentation.id}"><c:out
									 value="${presentation.title}"/></a>
								<c:if test="${presentation.presentationType == keynoteType}">
									(Keynote)
								</c:if>
							</li>
						</c:forEach>
						</ul>
					</c:if>
					<p class="text-center">
						<c:if test="${!empty speaker.googlePlusId}">
							<a class="btn btn-default btn" data-toggle="tooltip" data-placement="bottom" href="https://plus.google.com/<c:out value="${speaker.googlePlusId}" />" target="_blank">
								<span class="fa fa-google"></span>
							</a>
						</c:if>
						<c:if test="${!empty speaker.twitterId}">
							<a class="btn btn-default btn" data-toggle="tooltip" data-placement="bottom" href="https://twitter.com/<c:out value="${speaker.twitterId}"/>" target="_blank">
								<span class="fa fa-twitter"></span>
							</a>
						</c:if>
						<c:if test="${!empty speaker.linkedInId}">
							<a class="btn btn-default btn" data-toggle="tooltip" data-placement="bottom" href="https://www.linkedin.com/in/<c:out value="${speaker.linkedInId}" />" target="_blank">
								<span class="fa fa-linkedin"></span>
							</a>
						</c:if>
						<c:if test="${!empty speaker.githubId}">
							<a class="btn btn-default btn" data-toggle="tooltip" data-placement="bottom" href="https://github.com/<c:out value="${speaker.githubId}" />" target="_blank">
								<span class="fa fa-github"></span>
							</a>
						</c:if>
						<c:if test="${!empty speaker.lanyrdId}">
							<a class="btn btn-default btn" data-toggle="tooltip" data-placement="bottom" href="http://lanyrd.com/profile/<c:out value="${speaker.lanyrdId}" />/" target="_blank">
								<span>L</span>
							</a>
						</c:if>
					</p>
					<div><c:out value="${speaker.bioAsHtml}" escapeXml="false"/></div>
				</div>
			</div>
		</div>
	</section>

	<jsp:include page="includes/questions.jsp"/>

