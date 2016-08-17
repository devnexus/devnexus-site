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
				<form:input cssClass="form-control" path="firstName" id="firstName" maxlength="255" tabindex="${cfpSubmissionSpeaker.tabIndex()}"/>
				<form:errors path="firstName" cssClass="fieldError"/>
			</div>
		</div>
		<spring:bind path="cfpSubmissionSpeaker.lastName">
			<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
		</spring:bind>
		<div class="form-group${errorClass}">
			<label for="lastName" class="col-lg-2 control-label">Last Name*</label>
			<div class="col-lg-10">
				<form:input cssClass="form-control" path="lastName" id="lastName" maxlength="255" tabindex="${cfpSubmissionSpeaker.tabIndex()}"/>
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
				<form:input type="email" cssClass="form-control" path="email" id="email" tabindex="${cfpSubmissionSpeaker.tabIndex()}"/>
				<form:errors path="email" cssClass="fieldError"/>
			</div>
		</div>

		<spring:bind path="cfpSubmissionSpeaker.company">
			<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
		</spring:bind>
		<div class="form-group${errorClass}">
			<label for="company" class="col-lg-2 control-label">Company*</label>
			<div class="col-lg-10">
				<form:input type="text" cssClass="form-control" path="company" id="company" tabindex="${cfpSubmissionSpeaker.tabIndex()}"/>
				<form:errors path="company" cssClass="fieldError"/>
			</div>
		</div>

		<spring:bind path="cfpSubmissionSpeaker.location">
			<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
		</spring:bind>
		<div class="form-group${errorClass}">
			<label for="location" class="col-lg-2 control-label">Location*</label>
			<div class="col-lg-10">
				<form:input type="text" cssClass="form-control" path="location" id="location" tabindex="${cfpSubmissionSpeaker.tabIndex()}"/>
				<form:errors path="location" cssClass="fieldError"/>
				<span class="help-block">Where do you live?</span>
			</div>
		</div>

		<div class="form-group${errorClass}">
			<div class="col-lg-offset-2 col-lg-10">
				<div class="checkbox">
					<label>
						<form:checkbox path="mustReimburseTravelCost" id="mustReimburseTravelCost" tabindex="${cfpSubmissionSpeaker.tabIndex()}"/> Please check if you require travel cost reimbursement.
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
				<form:input cssClass="form-control" path="phone" id="phone" tabindex="6"/>
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
				<input id="pictureFile" type="file" cssClass="form-control" name="pictureFile" tabindex="${cfpSubmissionSpeaker.tabIndex()}"/>
				<span class="help-block">The absolute minimum size of the picture should be 360px x 360px. The maximum permissible file-size is ${maxFileSize}.</span>
				<span class="help-block">
				<a href="${ctx}${baseSiteUrl}/cfp/speaker-image/${cfpSubmissionSpeaker.id}">Your Image</a></span>
			</div>
		</div>

		<spring:bind path="cfpSubmissionSpeaker.bio">
			<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
		</spring:bind>
		<div class="form-group${errorClass}">
			<label for="biotext" class="col-lg-2 control-label">Bio*</label>
			<div class="col-lg-10">
				<form:textarea cssClass="form-control" path="bio" id="biotext" tabindex="${cfpSubmissionSpeaker.tabIndex()}" rows="10" maxlength="10000"/>
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
				<form:input cssClass="form-control" path="twitterId" id="twitterId" maxlength="255" tabindex="${cfpSubmissionSpeaker.tabIndex()}"/>
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
				<form:input cssClass="form-control" path="googlePlusId" id="googlePlusId" maxlength="255" tabindex="${cfpSubmissionSpeaker.tabIndex()}"/>
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
				<form:input cssClass="form-control" path="linkedInId" id="linkedInId" maxlength="255" tabindex="${cfpSubmissionSpeaker.tabIndex()}"/>
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
				<form:input cssClass="form-control" path="lanyrdId" id="lanyrdId" maxlength="255" tabindex="${cfpSubmissionSpeaker.tabIndex()}"/>
				<form:errors path="lanyrdId" cssClass="fieldError"/>
				<span class="help-block">Please provide just the username in your public profile URL, e.g. (in bold): http://lanyrd.com/profile/<strong>ghillert</strong>/</span>
			</div>
		</div>
		<spring:bind path="cfpSubmissionSpeaker.githubId">
			<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
		</spring:bind>
		<div class="form-group${errorClass}">
			<label for="githubId" class="col-lg-2 control-label">Github Username</label>
			<div class="col-lg-10">
				<form:input cssClass="form-control" path="githubId" id="githubId" maxlength="255" tabindex="${cfpSubmissionSpeaker.tabIndex()}"/>
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
				<form:input cssClass="form-control" path="tshirtSize" id="tshirtSize" tabindex="${cfpSubmissionSpeaker.tabIndex()}"/>
				<form:errors path="tshirtSize" cssClass="fieldError"/>
				<span class="help-block">E.g. S, M, L etc.</span>
			</div>
		</div>
		<h3 style="clear: left;">Your Availability</h3>
		<p>In order to make the creation of the schedule as smooth as possible,
			we would like to ask you about your your availability during the conference</p>
		<p>It would help us tremendously if you are available for the entire (main) conference
		and if you are submitting sessions for the workshop, then of course you should be available
		for it as well. Nevertheless, we do understand that you might have other obligations
		but please be as flexible as possible.</p>

		<fieldset>
			<div class="form-group${errorClass}">
				<div class="col-lg-offset-2 col-lg-10">
					<div class="checkbox">
						<label>
							<form:checkbox path="availableEntireEvent" id="availableEntireEvent" tabindex="${cfpSubmissionSpeaker.tabIndex()}"/>
							I am available <strong>ANY DAY</strong> for <strong>ANY TIME-SLOT</strong>!
						</label>
					</div>
					<form:errors path="availableEntireEvent" cssClass="fieldError"/>
				</div>
			</div>
		</fieldset>
		<hr/>
		<fieldset id="dayAvailability">
			<c:forEach items="${cfpSubmissionSpeaker.availabilityDays}" var="availability" varStatus="status">
				<form:hidden path="availabilityDays[${status.index}].conferenceDay.id"/>
				<h4 class="text-center"><fmt:formatDate pattern="EEEE MMMM d, yyyy" value="${availability.conferenceDay.day}"/> - ${availability.conferenceDay.name}</h4>
				<div class="form-group${errorClass}">
					<div class="col-lg-offset-2 col-lg-10">
						<div class="radio">
							<label>
								<form:radiobutton path="availabilityDays[${status.index}].availabilitySelection" value="ANY_TIME" tabindex="${cfpSubmissionSpeaker.tabIndex()}"/>
								I am fully available that day. Schedule me for any time-slot.
							</label>
						</div>
						<form:errors path="availableEntireEvent" cssClass="fieldError"/>
					</div>
					<div class="col-lg-offset-2 col-lg-10">
						<div class="radio">
							<label>
								<form:radiobutton path="availabilityDays[${status.index}].availabilitySelection" value="NO_AVAILABILITY" tabindex="${cfpSubmissionSpeaker.tabIndex()}"/>
								Sorry, I am not available that day at all.
							</label>
						</div>
						<form:errors path="availableEntireEvent" cssClass="fieldError"/>
					</div>
					<div class="col-lg-offset-2 col-lg-4">
						<div class="radio">
							<label>
								<form:radiobutton path="availabilityDays[${status.index}].availabilitySelection" value="PARTIAL_AVAILABILITY" tabindex="${cfpSubmissionSpeaker.tabIndex()}"/>
								I am partially available
							</label>
						</div>
						<form:errors path="availableEntireEvent" cssClass="fieldError"/>
					</div>
					<div class="col-lg-3">
						<div class="input-group">
						<span class="input-group-addon">From</span>
						<form:input cssClass="form-control" path="availabilityDays[${status.index}].startTime" id="availability_${status.index}.startTime"
							placeholder="e.g. 13:45" tabindex="${cfpSubmissionSpeaker.tabIndex()}"/>
						</div>
						<form:errors path="availabilityDays[${status.index}].startTime" cssClass="fieldError"/>
					</div>
					<div class="col-lg-3">
						<div class="input-group">
							<span class="input-group-addon">To</span>
							<form:input cssClass="form-control" path="availabilityDays[${status.index}].endTime" id="availability_${status.index}.endTime"
							placeholder="e.g. 14:45" tabindex="${cfpSubmissionSpeaker.tabIndex()}"/>
						</div>
						<form:errors path="tshirtSize" cssClass="fieldError"/>
					</div>
				</div>
			</c:forEach>
		</fieldset>
		<div class="form-group">
			<div class="col-lg-offset-2 col-lg-10">
				<button type="submit" class="btn btn-default" name="cancel" tabindex="${cfpSubmissionSpeaker.tabIndex()}"><span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span> Cancel</button>
				<button type="submit" class="btn btn-success" lang="save" tabindex="${cfpSubmissionSpeaker.tabIndex()}"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span> Save</button>
				<button type="submit" class="btn btn-danger" name="delete" tabindex="${cfpSubmissionSpeaker.tabIndex()}"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span> Delete</button>
			</div>
		</div>

		<p>Fields denoted with * are mandatory.
	</form:form>
	</div>
</div>

<%@ include file="/WEB-INF/jsp/cfp/cfp-include-modal.jsp"%>

<div class="modal fade" id="confirm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-body">
				Are you sure?
			</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal" class="btn">Cancel</button>
				<button type="button" data-dismiss="modal" class="btn btn-primary" id="delete">Delete</button>
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

				$("#availableEntireEvent").change(function() {

					if(this.checked) {
						$("#dayAvailability").find("input").prop("disabled", true);
					}
					else {
						$("#dayAvailability").find("input").prop("disabled", false);
					}
				});

				if($("#availableEntireEvent").prop('checked')) {
					$("#dayAvailability").find("input").prop("disabled", true);
				}
				else {
					$("#dayAvailability").find("input").prop("disabled", false);
				}
				<c:forEach items="${cfpSubmissionSpeaker.availabilityDays}" var="availability" varStatus="status">
					$('input[type=radio][name=availabilityDays\\[${status.index}\\]\\.availabilitySelection]').on('change', function() {
						console.log($(this).val())
						switch($(this).val()) {
							case 'PARTIAL_AVAILABILITY':
								console.log($("#availability_${status.index}\\.startTime"))
								$("#availability_${status.index}\\.startTime").prop("disabled", false);
								$("#availability_${status.index}\\.startTime").focus()
								$("#availability_${status.index}\\.endTime").prop("disabled", false);
								break;
							default:
								$("#availability_${status.index}\\.startTime").prop("disabled", true);
								$("#availability_${status.index}\\.endTime").prop("disabled", true);
								break;
						}
					});
				</c:forEach>
				$('button[name="delete"]').on('click', function(e){
					var form=$(this).closest('form');
					e.preventDefault();
					$('#confirm').modal({
						backdrop: 'static', keyboard: false
					})
					.one('click', '#delete', function() {
						var input = $("<input>")
						.attr("type", "hidden")
						.attr("name", "delete");
						form.append($(input));
						form.trigger('submit');
					});
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
