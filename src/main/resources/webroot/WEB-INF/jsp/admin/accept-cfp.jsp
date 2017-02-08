<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>
<div class="jumbotron call" style="margin-bottom:50px">
	<div class="container">
		<div id="banner">
			<h1><strong>Accept CFP</strong></h1>
		</div>
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
		<form:hidden path="id"/>

		<h3>Presentation</h3>

		<div class="form-group">
			<label for="title" class="col-lg-2 control-label">Presentation Title</label>
			<div class="col-lg-10">
				<p class="form-control-static"><c:out value="${cfpSubmission.title}"/></p>
			</div>
		</div>

		<div class="form-group">
			<label for="description" class="col-lg-2 control-label">Abstract</label>
			<div class="col-lg-10">
				<p class="form-control-static"><c:out value="${cfpSubmission.description}"/></p>
			</div>
		</div>

		<div class="form-group">
			<label for="topic" class="col-lg-2 control-label">Topic</label>
			<div class="col-lg-10">
				<p class="form-control-static"><c:out value="${cfpSubmission.topic}"/></p>
			</div>
		</div>

		<div class="form-group">
			<label for="skill-level" class="col-lg-2 control-label">Skill Level</label>
			<div class="col-lg-10">
				<p class="form-control-static"><c:out value="${cfpSubmission.skillLevel.name}"/></p>
			</div>
		</div>

		<div class="form-group">
			<label for="skill-level" class="col-lg-2 control-label">Presentation Type</label>
			<div class="col-lg-10">
				<p class="form-control-static"><c:out value="${cfpSubmission.presentationType.name}"/></p>
			</div>
		</div>

		<div class="form-group">
			<div class="col-lg-offset-2 col-lg-10">
				<div class="checkbox">
					<label>
						<form:checkbox disabled="true" path="sessionRecordingApproved" id="sessionRecordingApproved"/> Can we record your session?
					</label>
				</div>
			</div>
		</div>

		<div class="form-group">
			<label for="slotPreference" class="col-lg-2 control-label">Slot Preference or Comments</label>
			<div class="col-lg-10">
				<p class="form-control-static"><c:out value="${cfpSubmission.slotPreference}"/></p>
			</div>
		</div>

		<div class="form-group">
			<label for="cfpSubmissionStatusType" class="col-lg-2 control-label">Status</label>
			<div class="col-lg-10">
				<p class="form-control-static"><c:out value="${cfpSubmission.status.name}"/></p>
			</div>
		</div>

		<h3>Speaker Details</h3>
		<c:forEach items="${cfpSubmission.cfpSubmissionSpeakers}" var="speaker" varStatus="status">
			<form:hidden path="cfpSubmissionSpeakers[${status.index}].id"/>

			<div class="row">
				<div class="col-md-8"><h4 style="margin-top: 0"><h4>Speaker ${status.index+1}</h4></div>
				<div class="col-md-4 text-right">
				<c:if test="${not status.first}">
				<button type="submit" class="btn btn-danger" value="${status.index}" name="removeSpeaker" tabindex="20" title="Remove Speaker">
					<span class="glyphicon glyphicon-trash"></span></button>
				</c:if></div>
			</div>

			<div class="form-group">
				<label for="cfpSubmissionStatusType" class="col-lg-2 control-label">Use existing speaker</label>
				<div class="col-lg-10">
					<form:select cssClass="form-control" path="cfpSubmissionSpeakers[${status.index}].speaker.id" id="cfpSubmissionStatusTypes"
						itemValue="status.key">
						<form:option value="-1" label="Create New Speaker" />
						<form:options items="${allSpeakers}" itemLabel="fullName" itemValue="id"/>
					</form:select>
					<form:errors path="presentationType" cssClass="fieldError" />
				</div>
			</div>

			<div class="form-group">
				<label class="col-lg-2 control-label">First Name</label>
				<div class="col-lg-10">
					<p class="form-control-static"><c:out value="${speaker.firstName}"/></p>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-2 control-label">Last Name</label>
				<div class="col-lg-10">
					<p class="form-control-static"><c:out value="${speaker.lastName}"/></p>
				</div>
			</div>

			<div class="form-group${errorClass}">
				<label class="col-lg-2 control-label">Email</label>
				<div class="col-lg-10">
					<p class="form-control-static"><c:out value="${speaker.email}"/></p>
				</div>
			</div>

			<div class="form-group${errorClass}">
				<label class="col-lg-2 control-label">Location</label>
				<div class="col-lg-10">
					<p class="form-control-static"><c:out value="${speaker.location}"/></p>
				</div>
			</div>

			<div class="form-group${errorClass}">
				<div class="col-lg-offset-2 col-lg-10">
					<div class="checkbox">
						<label>
							<form:checkbox path="cfpSubmissionSpeakers[${status.index}].mustReimburseTravelCost" id="speakers[${status.index}].mustReimburseTravelCost" tabindex="5"/> Please check if you require travel cost reimbursement.
						</label>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="col-lg-2 control-label">Cell Phone</label>
				<div class="col-lg-10">
					<p class="form-control-static"><c:out value="${speaker.phone}"/></p>
				</div>
			</div>

			<h3>Picture</h3>

			<div class="form-group${errorClass}">
				<label class="col-lg-2 control-label">Bio*</label>
				<div class="col-lg-10">
					<p class="form-control-static"><c:out value="${speaker.bio}"/></p>
				</div>
			</div>


			<h3>Social links</h3>

			<div class="form-group${errorClass}">
				<label class="col-lg-2 control-label">Twitter Id</label>
				<div class="col-lg-10">
					<p class="form-control-static"><c:out value="${speaker.twitterId}"/></p>
				</div>
			</div>

			<div class="form-group${errorClass}">
				<label class="col-lg-2 control-label">Google Plus Id</label>
				<div class="col-lg-10">
					<p class="form-control-static"><c:out value="${speaker.googlePlusId}"/></p>
				</div>
			</div>

			<div class="form-group${errorClass}">
				<label class="col-lg-2 control-label">LinkedIn Id</label>
				<div class="col-lg-10">
					<p class="form-control-static"><c:out value="${speaker.linkedInId}"/></p>
				</div>
			</div>

			<div class="form-group${errorClass}">
				<label class="col-lg-2 control-label">Lanyrd Id</label>
				<div class="col-lg-10">
					<p class="form-control-static"><c:out value="${speaker.lanyrdId}"/></p>
				</div>
			</div>

			<div class="form-group${errorClass}">
				<label class="col-lg-2 control-label">GithubId Username</label>
				<div class="col-lg-10">
					<p class="form-control-static"><c:out value="${speaker.githubId}"/></p>
				</div>
			</div>

			<h3 style="clear: left;">Miscellaneous</h3>

	 		<div class="form-group">
				<label class="col-lg-2 control-label">T-Shirt Size</label>
				<div class="col-lg-10">
					<p class="form-control-static"><c:out value="${speaker.tshirtSize}"/></p>
				</div>
			</div>
		</c:forEach>

		<div class="form-group">
			<div class="col-lg-offset-2 col-lg-10">
				<button type="submit" class="btn btn-default" name="cancel">Cancel</button>
				<button type="submit" class="btn btn-default" lang="save">Accept</button>
			</div>
		</div>
	</form:form>
	</div>
</div>

<div class="modal fade" id="confirm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-body">
				Are you sure?
			</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal" class="btn btn-primary" id="delete">Delete</button>
				<button type="button" data-dismiss="modal" class="btn">Cancel</button>
			</div>
		</div>
	</div>
</div>

<content tag='bottom'>
<script src="${ctx}/assets/js/other/bootstrap-maxlength.min.js"></script>
<script src="${ctx}/assets/js/jquery-plugins/jquery.raty.js"></script>
<script type="text/javascript">

	$('button[name="delete"]').on('click', function(e){
		var form=$(this).closest('form');
		e.preventDefault();
		$('#confirm').modal({
			backdrop: 'static', keyboard: false
		})
		.one('click', '#delete', function() {
			form.trigger('submit');
		});
	});

	$(document).ready(function() {

		$('textarea').maxlength({
			alwaysShow: true
		});

	});
<!--
	$(function(){
		var tabindex = 1;
		$('input,select,textarea').each(function() {
			if (this.type != "hidden") {
				var $input = $(this);
				$input.attr("tabindex", tabindex);
				tabindex++;
			}
		});
		$('input,select,textarea').first().focus();
	});
//-->
</script>
</content>
