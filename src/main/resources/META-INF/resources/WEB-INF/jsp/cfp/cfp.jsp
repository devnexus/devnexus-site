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

	<spring:bind path="cfpSubmission.*">
		<c:if test="${not empty status.errorMessages}">
			<div class="alert alert-danger fade in"
				><a href="#" data-dismiss="alert" class="close">&times;</a>
				<c:forEach var="error" items="${status.errorMessages}"
					><c:out value="${error}" escapeXml="false"/><br/>
				</c:forEach>
			</div>
		</c:if>
	</spring:bind>

	<form:form id="cfpForm" class="form-horizontal" role="form" method="post" modelAttribute="cfpSubmission" enctype="multipart/form-data">
		<form:hidden path="event.id"/>

		<spring:bind path="cfpSubmission.title">
			<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
		</spring:bind>
		<div class="form-group${errorClass}">
			<label for="title" class="col-lg-2 control-label">Presentation Title*</label>
			<div class="col-lg-10">
				<form:input cssClass="form-control" path="title" id="title" tabindex="1"/>
				<form:errors path="title" cssClass="fieldError"/>
			</div>
		</div>

		<spring:bind path="cfpSubmission.description">
			<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
		</spring:bind>
		<div class="form-group${errorClass}">
			<label for="description" class="col-lg-2 control-label">Abstract*</label>
			<div class="col-lg-10">
				<form:textarea cssClass="form-control" path="description" id="description" tabindex="2" rows="10" maxLength="10000"/>
				<form:errors path="description" cssClass="fieldError"/>
				<span class="help-block"><a href="http://daringfireball.net/projects/markdown/" target="_blank">
				Markdown</a> is supported for the abstract.</span>
			</div>
		</div>

		<spring:bind path="cfpSubmission.topic">
			<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
		</spring:bind>
		<div class="form-group${errorClass}">
			<label for="topic" class="col-lg-2 control-label">Topic*</label>
			<div class="col-lg-10">
				<form:input cssClass="form-control" path="topic" id="topic" tabindex="3"/>
				<form:errors path="topic" cssClass="fieldError"/>
				<span class="help-block">For example: Java/JavaEE/Spring, Data, HTML5, Agile, Mobile, Cloud ...</span>
			</div>
		</div>
		<p style="clear: left;">Please help us classify your presentation.</p>

		<spring:bind path="cfpSubmission.skillLevel">
			<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
		</spring:bind>
		<div class="form-group${errorClass}">
			<label for="skill-level" class="col-lg-2 control-label">Skill Level*</label>
			<div class="col-lg-10">
				<form:select cssClass="form-control" path="skillLevel" id="skill-level" tabindex="4">
					<form:option value="" label="Please Select a Skill Level" />
					<form:options items="${skillLevels}" itemLabel="name" itemValue="id"/>
				</form:select>
				<form:errors path="skillLevel" cssClass="fieldError" />
			</div>
		</div>

		<spring:bind path="cfpSubmission.presentationType">
			<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
		</spring:bind>
		<div class="form-group${errorClass}">
			<label for="skill-level" class="col-lg-2 control-label">Presentation Type*</label>
			<div class="col-lg-10">
				<form:select cssClass="form-control" path="presentationType" id="presentation-type"
					tabindex="5">
					<form:option value="" label="Please Select a Presentation Type" />
					<form:options items="${presentationTypes}" itemLabel="name" itemValue="id"/>
				</form:select>
				<form:errors path="presentationType" cssClass="fieldError" />
			</div>
		</div>

		<spring:bind path="cfpSubmission.sessionRecordingApproved">
			<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
		</spring:bind>
		<div class="form-group${errorClass}">
			<div class="col-lg-offset-2 col-lg-10">
				<div class="checkbox">
					<label>
						<form:checkbox path="sessionRecordingApproved" id="sessionRecordingApproved" tabindex="6"/> Can we record your session?
					</label>
				</div>
				<form:errors path="sessionRecordingApproved" cssClass="fieldError"/>
			</div>
		</div>
		<p>
			Please tell us if you have any preferences/requirements for your
			presentation, e.g. if you can only speak on specific days, morning/afternoon
			etc. Also, if you have any comments, please let us know.
		</p>

		<spring:bind path="cfpSubmission.slotPreference">
			<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
		</spring:bind>
		<div class="form-group${errorClass}">
			<label for="slotPreference" class="col-lg-2 control-label">Slot Preference or Comments</label>
			<div class="col-lg-10">
				<form:textarea cssClass="form-control" path="slotPreference" id="slotPreference" tabindex="7" rows="5" maxLength="1000"/>
				<form:errors path="slotPreference" cssClass="fieldError"/>
				<span class="help-block">E.g. Can you only present on one day or have other time-contraints?...</span>
			</div>
		</div>
		<hr/>
		<div class="form-group">
			<label class="col-lg-2 control-label">Speakers</label>
			<div class="col-lg-offset-2 col-lg-10">
				<form:checkboxes element="div" path="cfpSubmissionSpeakers" items="${speakers}" itemValue="id" itemLabel="firstLastName" tabindex="8"/>
			</div>
		</div>
		<hr/>
		<div class="form-group">
			<div class="col-lg-offset-2 col-lg-10">
				<button type="submit" class="btn btn-default" name="cancel" tabindex="9"><span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span> Cancel</button>
				<button type="submit" class="btn btn-success" lang="save" tabindex="10"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span> Add</button>
			</div>
		</div>

		<p>Fields denoted with * are mandatory.
	</form:form>
	</div>
</div>

<%@ include file="/WEB-INF/jsp/cfp/cfp-include-modal.jsp"%>

<content tag='bottom'>

		<script src="${ctx}/assets/js/other/bootstrap-maxlength.min.js"></script>
		<script type="text/javascript">

			$(document).ready(function() {
				$("input[type='text']:visible:enabled:first", document.forms['cfpForm']).focus();

				$('textarea').maxlength({
					alwaysShow: true
				});

				$("#cfpForm").each(function(){
					$(this).find(':input').keydown(function( event ) {
						if ( event.which == 13 ) {
							event.preventDefault();
							console.log("Submitting form ...");
							$("#cfpForm").submit();
						}
					});
				});
			});
		</script>
</content>
