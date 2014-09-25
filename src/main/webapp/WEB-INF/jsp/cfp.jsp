<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<div class="jumbotron call" style="margin-bottom:50px">
	<div class="container">
		<div id="banner">
			<h1><strong>Call for Papers 2015!</strong></h1>
			<h3>Thank you for your interest in DevNexus 2015! We would love to review your session proposals for the South-East's
				largest developer conference. We are planning to cover a wide variety of topics around: </h3>
			<ul class="double">
				<li>Java/JavaEE/Spring</li>
				<li>HTML5 + JavaScript</li>
				<li>Data + Integration</li>
				<li>Alternative Languages on the JVM</li>
				<li>User Experience</li>
				<li>Cloud</li>
				<li>Agile + Tools</li>
				<li>Mobile</li>
			</ul>
		</div> <!-- end banner -->
	</div>
</div>
<div class="row">
	<div class="col-md-8 col-md-offset-2">

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
				<form:input cssClass="form-control" path="title" id="title" tabindex="7"/>
				<form:errors path="title" cssClass="fieldError"/>
			</div>
		</div>

		<spring:bind path="cfpSubmission.description">
			<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
		</spring:bind>
		<div class="form-group${errorClass}">
			<label for="description" class="col-lg-2 control-label">Abstract*</label>
			<div class="col-lg-10">
				<form:textarea cssClass="form-control" path="description" id="description" tabindex="8" rows="10" maxLength="10000"/>
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
				<form:input cssClass="form-control" path="topic" id="topic" tabindex="9"/>
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
				<form:select cssClass="form-control" path="skillLevel" id="skill-level" tabindex="10">
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
					tabindex="11">
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
						<form:checkbox path="sessionRecordingApproved" id="sessionRecordingApproved" tabindex="16"/> Can we record your session?
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
				<form:textarea cssClass="form-control" path="slotPreference" id="slotPreference" tabindex="17" rows="5" maxLength="1000"/>
				<form:errors path="slotPreference" cssClass="fieldError"/>
				<span class="help-block">E.g. Can you only present on one day or have other time-contraints?...</span>
			</div>
		</div>
		<hr/>
		<div class="row">
			<div class="col-md-8"><h3 style="margin-top: 0">Speaker Details</h3></div>
			<div class="col-md-4 text-right">
				<button type="submit" class="btn btn-info" name="addSpeaker" tabindex="20" title="Add Speaker">
					<span class="glyphicon glyphicon-plus"></span></button></div>
		</div>
		<c:forEach items="${cfpSubmission.speakers}" var="speaker" varStatus="status">
			<form:hidden path="speakers[${status.index}].cfpSubmission.id"/>
			<div class="row">
				<div class="col-md-8"><h4 style="margin-top: 0"><h4>Speaker ${status.index+1}</h4></div>
				<div class="col-md-4 text-right">
				<c:if test="${not status.first}">
				<button type="submit" class="btn btn-danger" value="${status.index}" name="removeSpeaker" tabindex="20" title="Remove Speaker">
					<span class="glyphicon glyphicon-trash"></span></button>
				</c:if></div>

			</div>
			<spring:bind path="speakers[${status.index}].firstName">
				<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
			</spring:bind>
			<div class="form-group${errorClass}">
				<label for="speakers[${status.index}].firstName" class="col-lg-2 control-label">First Name*</label>
				<div class="col-lg-10">
					<form:input cssClass="form-control" path="speakers[${status.index}].firstName" id="speakers[${status.index}].firstName" maxlength="255"/>
					<form:errors path="speakers[${status.index}].firstName" cssClass="fieldError"/>
				</div>
			</div>
			<spring:bind path="speakers[${status.index}].lastName">
				<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
			</spring:bind>
			<div class="form-group${errorClass}">
				<label for="speakers[${status.index}].lastName" class="col-lg-2 control-label">Last Name*</label>
				<div class="col-lg-10">
					<form:input cssClass="form-control" path="speakers[${status.index}].lastName" id="speakers[${status.index}].lastName" maxlength="255"/>
					<form:errors path="speakers[${status.index}].lastName" cssClass="fieldError"/>
				</div>
			</div>

			<p>
				Please provide some contact information. We will use primarily email between
				now and the conference. However, on the days of the conference, it is important
				that we can reach you via cell phone, just in case emergencies arise.
			</p>

			<spring:bind path="speakers[${status.index}].email">
				<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
			</spring:bind>
			<div class="form-group${errorClass}">
				<label for="speakers[${status.index}].email" class="col-lg-2 control-label">Email*</label>
				<div class="col-lg-10">
					<form:input type="email" cssClass="form-control" path="speakers[${status.index}].email" id="speakers[${status.index}].email" tabindex="3"/>
					<form:errors path="speakers[${status.index}].email" cssClass="fieldError"/>
				</div>
			</div>

			<spring:bind path="speakers[${status.index}].location">
				<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
			</spring:bind>
			<div class="form-group${errorClass}">
				<label for="speakers[${status.index}].location" class="col-lg-2 control-label">Location*</label>
				<div class="col-lg-10">
					<form:input type="text" cssClass="form-control" path="speakers[${status.index}].location" id="speakers[${status.index}].location" tabindex="4"/>
					<form:errors path="speakers[${status.index}].location" cssClass="fieldError"/>
					<span class="help-block">Where do you live?</span>
				</div>
			</div>

			<div class="form-group${errorClass}">
				<div class="col-lg-offset-2 col-lg-10">
					<div class="checkbox">
						<label>
							<form:checkbox path="speakers[${status.index}].mustReimburseTravelCost" id="speakers[${status.index}].mustReimburseTravelCost" tabindex="5"/> Please check if you require travel cost reimbursement.
						</label>
					</div>
					<form:errors path="speakers[${status.index}].mustReimburseTravelCost" cssClass="fieldError"/>
				</div>
			</div>

			<spring:bind path="speakers[${status.index}].phone">
				<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
			</spring:bind>
			<div class="form-group${errorClass}">
				<label for="speakers[${status.index}].phone" class="col-lg-2 control-label">Cell Phone*</label>
				<div class="col-lg-10">
					<form:input cssClass="form-control" path="speakers[${status.index}].phone" id="speakers[${status.index}].phone" tabindex="4"/>
					<form:errors path="speakers[${status.index}].phone" cssClass="fieldError"/>
					<span class="help-block">In case we need to urgently reach you during the conference.</span>
				</div>
			</div>
			<p>
				Please submit a decent sized picture. We will add it to the conference site.
			</p>
			<div class="form-group">
				<label for="pictureFiles[${status.index}]" class="col-lg-2 control-label">Picture*</label>
				<div class="col-lg-10">
					<input id="pictureFiles[${status.index}]" type="file" cssClass="form-control" name="pictureFiles[${status.index}]" tabindex="5"/>
					<span class="help-block">The absolute minimum size of the picture should be 100px x 133px.</span>
				</div>
			</div>

			<spring:bind path="speakers[${status.index}].bio">
				<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
			</spring:bind>
			<div class="form-group${errorClass}">
				<label for="speakers[${status.index}].biotext" class="col-lg-2 control-label">Bio*</label>
				<div class="col-lg-10">
					<form:textarea cssClass="form-control" path="speakers[${status.index}].bio" id="speakers[${status.index}].biotext" tabindex="6" rows="10" maxlength="10000"/>
					<form:errors path="speakers[${status.index}].bio" cssClass="fieldError"/>
					<span class="help-block"><a href="http://daringfireball.net/projects/markdown/" target="_blank">
					Markdown</a> is supported for the bio.</span>
				</div>
			</div>


			<h3>Your social links</h3>

			<spring:bind path="speakers[${status.index}].twitterId">
				<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
			</spring:bind>
			<div class="form-group${errorClass}">
				<label for="speakers[${status.index}].twitterId" class="col-lg-2 control-label">Twitter Id</label>
				<div class="col-lg-10">
					<form:input cssClass="form-control" path="speakers[${status.index}].twitterId" id="speakers[${status.index}].twitterId" maxlength="255" tabindex="12"/>
					<form:errors path="speakers[${status.index}].twitterId" cssClass="fieldError"/>
					<span class="help-block">Please provide just the Twitter ID, not a URL.</span>
				</div>
			</div>

			<spring:bind path="speakers[${status.index}].googlePlusId">
				<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
			</spring:bind>
			<div class="form-group${errorClass}">
				<label for="speakers[${status.index}].googlePlusId" class="col-lg-2 control-label">Google Plus Id</label>
				<div class="col-lg-10">
					<form:input cssClass="form-control" path="speakers[${status.index}].googlePlusId" id="speakers[${status.index}].googlePlusId" maxlength="255" tabindex="13"/>
					<form:errors path="speakers[${status.index}].googlePlusId" cssClass="fieldError"/>
					<span class="help-block">Please provide just the Google Plus ID, not a URL, e.g.: 104285850599215045327</span>
				</div>
			</div>

			<spring:bind path="speakers[${status.index}].linkedInId">
				<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
			</spring:bind>
			<div class="form-group${errorClass}">
				<label for="speakers[${status.index}].linkedInId" class="col-lg-2 control-label">LinkedIn Id</label>
				<div class="col-lg-10">
					<form:input cssClass="form-control" path="speakers[${status.index}].linkedInId" id="speakers[${status.index}].linkedInId" maxlength="255" tabindex="14"/>
					<form:errors path="speakers[${status.index}].linkedInId" cssClass="fieldError"/>
					<span class="help-block">Please provide just the username in your public profile URL, e.g. (in bold): http://www.linkedin.com/in/<strong>hillert</strong></span>
				</div>
			</div>
			<h3 style="clear: left;">Miscellaneous</h3>
			<p style="clear: left;">Please let us know your T-shirt size, you we can ensure we have the right size.</p>

			<spring:bind path="speakers[${status.index}].tshirtSize">
				<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
			</spring:bind>
	 		<div class="form-group${errorClass}">
				<label for="speakers[${status.index}].tshirtSize" class="col-lg-2 control-label">T-Shirt Size*</label>
				<div class="col-lg-10">
					<form:input cssClass="form-control" path="speakers[${status.index}].tshirtSize" id="speakers[${status.index}].tshirtSize" tabindex="15"/>
					<form:errors path="speakers[${status.index}].tshirtSize" cssClass="fieldError"/>
					<span class="help-block">E.g. S, M, L etc.</span>
				</div>
			</div>
		</c:forEach>
		<hr/>

		<c:if test="${reCaptchaEnabled}">
			<label class="col-lg-2 control-label">Are you human?</label>
			<div class="col-lg-10" style="margin-bottom: 1em;">
				<c:out value="${reCaptchaHtml}" escapeXml="false"/>
			</div>
		</c:if>

		<div class="form-group">
			<div class="col-lg-offset-2 col-lg-10">
				<button type="submit" class="btn btn-default" name="cancel" tabindex="20">Cancel</button>
				<button type="submit" class="btn btn-default" lang="save" tabindex="19">Add</button>
			</div>
		</div>

		<p>Fields denoted with * are mandatory.
	</form:form>
	</div>
</div>
<content tag='bottom'>

		<script src="${ctx}/js/bootstrap-maxlength.min.js"></script>
		<script type="text/javascript">

			$(document).ready(function() {
				$("input[type='text']:visible:enabled:first", document.forms['cfpForm']).focus();

				$('textarea').maxlength({
					alwaysShow: true
				});

			});
		</script>
</content>