<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<title>${contextEvent.title} | Submit CFP</title>

<!-- intro -->
<section id="about" class="module parallax parallax-3">
	<div class="container header">
		<div class="row centered">
			<div class="col-md-10 col-md-offset-1">
				 <div class="top-intro travel">
					<h4 class="section-white-title decorated"><span>Call for Papers 2017!</span></h4>
					<h5 class="intro-white-lead">
						Thank you for your interest in DevNexus 2017!
					</h5>
					<p>
						We would love to review your session proposals for the South-East's
						largest developer conference. We are planning to cover a wide variety of topics around:
					</p>
					<ul class="list-inline">
						<li>Java/JavaEE/Spring</li>
						<li>HTML5 + JavaScript</li>
						<li>Data + Integration</li>
						<li>Alternative JVM Languages</li>
						<li>User Experience</li>
						<li>Cloud</li>
						<li>Agile + Tools</li>
						<li>Mobile</li>
						<li>Security</li>
					</ul>
					<div class="text-center">
					<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#cfpFAQ">
						FAQ
					</button></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- /intro -->

<div class="row">
	<div class="col-md-8 col-md-offset-2" style="padding-top: 4em;">

	<spring:bind path="cfpSubmissionSpeaker.*">
		<c:if test="${not empty status.errorMessages}">
			<div class="alert alert-danger fade in"
				><a href="#" data-dismiss="alert" class="close">&times;</a>
				<c:forEach var="error" items="${status.errorMessages}"
					><c:out value="${error}" escapeXml="false"/><br/>
				</c:forEach>
			</div>
		</c:if>
	</spring:bind>

	<form:form id="cfpForm" class="form-horizontal" role="form" method="post" modelAttribute="cfpSubmissionSpeaker" enctype="multipart/form-data">
		<form:hidden path="event.id"/>

		<div class="row">
			<div class="col-md-8"><h3 style="margin-top: 0">Speaker Details</h3></div>
		</div>

		<%-- <form:hidden path="cfpSubmissionSpeaker.cfpSubmission.id"/> --%>

		<spring:bind path="cfpSubmissionSpeaker.firstName">
			<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
		</spring:bind>
		<div class="form-group${errorClass}">
			<label for="firstName" class="col-lg-2 control-label">First Name*</label>
			<div class="col-lg-10">
				<form:input cssClass="form-control" path="firstName" id="firstName" maxlength="255"/>
				<form:errors path="firstName" cssClass="fieldError"/>
			</div>
		</div>
		<spring:bind path="cfpSubmissionSpeaker.lastName">
			<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
		</spring:bind>
		<div class="form-group${errorClass}">
			<label for="lastName" class="col-lg-2 control-label">Last Name*</label>
			<div class="col-lg-10">
				<form:input cssClass="form-control" path="lastName" id="lastName" maxlength="255"/>
				<form:errors path="lastName" cssClass="fieldError"/>
			</div>
		</div>

		<p>
			Please provide some contact information. We will use primarily email between
			now and the conference. However, on the days of the conference, it is important
			that we can reach you via cell phone, just in case emergencies arise.
		</p>

		<spring:bind path="cfpSubmissionSpeaker.email">
			<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
		</spring:bind>
		<div class="form-group${errorClass}">
			<label for="email" class="col-lg-2 control-label">Email*</label>
			<div class="col-lg-10">
				<form:input type="email" cssClass="form-control" path="email" id="email" tabindex="3"/>
				<form:errors path="email" cssClass="fieldError"/>
			</div>
		</div>

		<spring:bind path="cfpSubmissionSpeaker.location">
			<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
		</spring:bind>
		<div class="form-group${errorClass}">
			<label for="location" class="col-lg-2 control-label">Location*</label>
			<div class="col-lg-10">
				<form:input type="text" cssClass="form-control" path="location" id="location" tabindex="4"/>
				<form:errors path="location" cssClass="fieldError"/>
				<span class="help-block">Where do you live?</span>
			</div>
		</div>

		<div class="form-group${errorClass}">
			<div class="col-lg-offset-2 col-lg-10">
				<div class="checkbox">
					<label>
						<form:checkbox path="mustReimburseTravelCost" id="mustReimburseTravelCost" tabindex="5"/> Please check if you require travel cost reimbursement.
					</label>
				</div>
				<form:errors path="mustReimburseTravelCost" cssClass="fieldError"/>
			</div>
		</div>

		<spring:bind path="cfpSubmissionSpeaker.phone">
			<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
		</spring:bind>
		<div class="form-group${errorClass}">
			<label for="phone" class="col-lg-2 control-label">Cell Phone*</label>
			<div class="col-lg-10">
				<form:input cssClass="form-control" path="phone" id="phone" tabindex="4"/>
				<form:errors path="phone" cssClass="fieldError"/>
				<span class="help-block">In case we need to urgently reach you during the conference.</span>
			</div>
		</div>
		<p>
			Please submit a decent sized picture. We will add it to the conference site.
		</p>
		<div class="form-group">
			<label for="pictureFile" class="col-lg-2 control-label">Picture*</label>
			<div class="col-lg-10">
				<input id="pictureFile" type="file" cssClass="form-control" name="pictureFile" tabindex="5"/>
				<span class="help-block">The absolute minimum size of the picture should be 360px x 360px.</span>
			</div>
		</div>

		<spring:bind path="cfpSubmissionSpeaker.bio">
			<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
		</spring:bind>
		<div class="form-group${errorClass}">
			<label for="biotext" class="col-lg-2 control-label">Bio*</label>
			<div class="col-lg-10">
				<form:textarea cssClass="form-control" path="bio" id="biotext" tabindex="6" rows="10" maxlength="10000"/>
				<form:errors path="bio" cssClass="fieldError"/>
				<span class="help-block"><a href="http://daringfireball.net/projects/markdown/" target="_blank">
				Markdown</a> is supported for the bio.</span>
			</div>
		</div>

		<h3>Your social links</h3>

		<spring:bind path="cfpSubmissionSpeaker.twitterId">
			<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
		</spring:bind>
		<div class="form-group${errorClass}">
			<label for="twitterId" class="col-lg-2 control-label">Twitter Id</label>
			<div class="col-lg-10">
				<form:input cssClass="form-control" path="twitterId" id="twitterId" maxlength="255" tabindex="12"/>
				<form:errors path="twitterId" cssClass="fieldError"/>
				<span class="help-block">Please provide just the Twitter ID, not a URL.</span>
			</div>
		</div>

		<spring:bind path="cfpSubmissionSpeaker.googlePlusId">
			<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
		</spring:bind>
		<div class="form-group${errorClass}">
			<label for="googlePlusId" class="col-lg-2 control-label">Google Plus Id</label>
			<div class="col-lg-10">
				<form:input cssClass="form-control" path="googlePlusId" id="googlePlusId" maxlength="255" tabindex="13"/>
				<form:errors path="googlePlusId" cssClass="fieldError"/>
				<span class="help-block">Please provide just the Google Plus ID, not a URL, e.g.: 104285850599215045327</span>
			</div>
		</div>

		<spring:bind path="cfpSubmissionSpeaker.linkedInId">
			<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
		</spring:bind>
		<div class="form-group${errorClass}">
			<label for="linkedInId" class="col-lg-2 control-label">LinkedIn Id</label>
			<div class="col-lg-10">
				<form:input cssClass="form-control" path="linkedInId" id="linkedInId" maxlength="255" tabindex="14"/>
				<form:errors path="linkedInId" cssClass="fieldError"/>
				<span class="help-block">Please provide just the username in your public profile URL, e.g. (in bold): http://www.linkedin.com/in/<strong>hillert</strong></span>
			</div>
		</div>
		<spring:bind path="cfpSubmissionSpeaker.lanyrdId">
			<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
		</spring:bind>
		<div class="form-group${errorClass}">
			<label for="lanyrdId" class="col-lg-2 control-label">Lanyrd Id</label>
			<div class="col-lg-10">
				<form:input cssClass="form-control" path="lanyrdId" id="lanyrdId" maxlength="255" tabindex="14"/>
				<form:errors path="lanyrdId" cssClass="fieldError"/>
				<span class="help-block">Please provide just the username in your public profile URL, e.g. (in bold): http://lanyrd.com/profile/<strong>ghillert</strong>/</span>
			</div>
		</div>
		<spring:bind path="cfpSubmissionSpeaker.githubId">
			<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
		</spring:bind>
		<div class="form-group${errorClass}">
			<label for="githubId" class="col-lg-2 control-label">GithubId Username</label>
			<div class="col-lg-10">
				<form:input cssClass="form-control" path="githubId" id="githubId" maxlength="255" tabindex="14"/>
				<form:errors path="githubId" cssClass="fieldError"/>
				<span class="help-block">Please provide just the GitHub username, e.g. (in bold): https://github.com/<strong>ghillert</strong></span>
			</div>
		</div>
		<h3 style="clear: left;">Miscellaneous</h3>
		<p style="clear: left;">Please let us know your T-shirt size, you we can ensure we have the right size.</p>

		<spring:bind path="cfpSubmissionSpeaker.tshirtSize">
			<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
		</spring:bind>
 		<div class="form-group${errorClass}">
			<label for="tshirtSize" class="col-lg-2 control-label">T-Shirt Size*</label>
			<div class="col-lg-10">
				<form:input cssClass="form-control" path="tshirtSize" id="tshirtSize" tabindex="15"/>
				<form:errors path="tshirtSize" cssClass="fieldError"/>
				<span class="help-block">E.g. S, M, L etc.</span>
			</div>
		</div>
		<div class="form-group">
			<div class="col-lg-offset-2 col-lg-10">
				<button type="submit" class="btn btn-default" name="cancel" tabindex="20"><span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span> Cancel</button>
				<button type="submit" class="btn btn-success" lang="save" tabindex="19"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span> Save</button>
			</div>
		</div>
		<p>Fields denoted with * are mandatory.
	</form:form>
	</div>
</div>

<!-- Modal -->
<div class="modal fade" id="cfpFAQ" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
				<h4 class="modal-title" id="myModalLabel">Frequently Asked Questions</h4>
			</div>
			<div class="modal-body">
				<h4>When will the CFP close?</h4>
				<p>The CFP will close on <strong>15th November 2015</strong>.</p>
				<h4>When will I get notified whether I got accepted or not?</h4>
				<p>We will notify you by <strong>December 1, 2015</strong>.</p>
				<h4>When will the schedule be ready?</h4>
				<p>A draft schedule will be ready on <strong>December 1, 2015</strong>.</p>
				<h4>When is the Workshop day?</h4>
				<p>The workshop day is on <strong>February 15</strong>. We will have 5 rooms available,
				each accommodating 25-35 attendees.</p>
				<h4>How long are the sessions for the main conference?</h4>
				<p>
					The main conference days are <strong>February 16 &amp; 17</strong>. Breakout sessions
					(normal sessions) are <strong>75 minutes</strong> each. Keynotes
					are <strong>60 minutes</strong>.
				</p>
				<h4>Do we cover travel expenses?</h4>
				<p>
					We make that determination on a case-by-case basis. For speakers
					that are not affiliated with large organizations, we often do.
					However, if you represent a larger organization we typically don't.
					The reason is that we need to carefully watch our budget as we
					operate as non-profit and keep the ticket prices as low as possible.
					As we grow, we started to loosen the restrictions a bit but
					we rather still do the determination on a case by case basis.
				﻿</p>
				<h4>I have more questions...</h4>
				<p>
					Please don't hesitate and contact us with any other questions
					you may have at:</p>
				<p class="text-center"><strong>info at ajug.org</strong></p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>
	 </div>
</div>

<content tag='bottom'>

		<script src="${ctx}/assets/js/other/bootstrap-maxlength.min.js"></script>
		<script type="text/javascript">

			$(document).ready(function() {
				$("input[type='text']:visible:enabled:first", document.forms['cfpForm']).focus();

				$('textarea').maxlength({
					alwaysShow: true
				});

			});
		</script>
</content>
