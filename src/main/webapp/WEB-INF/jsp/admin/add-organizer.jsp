<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<div class="jumbotron call" style="margin-bottom:50px">
	<div class="container">
		<div id="banner">
			<h1><strong>Add/Edit Organizer</strong></h1>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-md-8 col-md-offset-2">

	<spring:bind path="organizer.*">
		<c:if test="${not empty status.errorMessages}">
			<div class="alert alert-danger fade in"
				><a href="#" data-dismiss="alert" class="close">&times;</a>
				<c:forEach var="error" items="${status.errorMessages}"
					><c:out value="${error}" escapeXml="false"/><br/>
				</c:forEach>
			</div>
		</c:if>
	</spring:bind>
	<form:form id="form" class="form-horizontal" role="form" method="post" modelAttribute="organizer" enctype="multipart/form-data">

		<spring:bind path="organizer.firstName">
			<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
		</spring:bind>
		<div class="form-group${errorClass}">
			<label for="firstName" class="col-lg-2 control-label">First Name*</label>
			<div class="col-lg-10">
				<form:input cssClass="form-control" path="firstName" id="firstName" maxlength="255" tabindex="1"/>
				<form:errors path="firstName" cssClass="fieldError"/>
			</div>
		</div>

		<spring:bind path="organizer.firstName">
			<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
		</spring:bind>
		<div class="form-group${errorClass}">
			<label for="firstName" class="col-lg-2 control-label">Last Name*</label>
			<div class="col-lg-10">
				<form:input cssClass="form-control" path="lastName" id="lastName" maxlength="255" tabindex="2"/>
				<form:errors path="lastName" cssClass="fieldError"/>
			</div>
		</div>

		<div class="form-group">
			<c:if test="${organizer.picture != null}">
				<div class="col-lg-10 col-lg-offset-2">
					<img src="${ctx}${baseSiteUrl}/organizers/${organizer.id}.jpg"/>
				</div>
			</c:if>
			<label for="pictureFile" class="col-lg-2 control-label">Picture</label>
			<div class="col-lg-10">
				<input id="pictureFile" type="file" cssClass="form-control" name="pictureFile" tabindex="3"/>
				<span class="help-block">Should be 100px x 133px.</span>
			</div>
		</div>
			<spring:bind path="organizer.bio">
				<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
			</spring:bind>
			<div class="form-group${errorClass}">
				<label for="biotext" class="col-lg-2 control-label">Bio</label>
				<div class="col-lg-10">
					<form:textarea cssClass="form-control" path="bio" id="biotext" tabindex="4" rows="10" maxlength="10000"/>
					<form:errors path="bio" cssClass="fieldError"/>
					<span class="help-block"><a href="http://daringfireball.net/projects/markdown/" target="_blank">
					Markdown</a> is supported for the bio.</span>
				</div>
			</div>
			<spring:bind path="organizer.twitterId">
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

			<spring:bind path="organizer.googlePlusId">
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

			<spring:bind path="organizer.linkedInId">
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

			<spring:bind path="organizer.lanyrdId">
				<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
			</spring:bind>
			<div class="form-group${errorClass}">
				<label for="lanyrdId" class="col-lg-2 control-label">Lanyrd Id</label>
				<div class="col-lg-10">
					<form:input cssClass="form-control" path="lanyrdId" id="lanyrdId" maxlength="255" tabindex="15"/>
					<form:errors path="lanyrdId" cssClass="fieldError"/>
					<span class="help-block">Please provide just the username in your public profile URL, e.g. (in bold): http://lanyrd.com/profile/<strong>ghillert</strong>/</span>
				</div>
			</div>

			<spring:bind path="organizer.githubId">
				<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
			</spring:bind>
			<div class="form-group${errorClass}">
				<label for="githubId" class="col-lg-2 control-label">GitHub Id</label>
				<div class="col-lg-10">
					<form:input cssClass="form-control" path="githubId" id="githubId" maxlength="255" tabindex="16"/>
					<form:errors path="githubId" cssClass="fieldError"/>
					<span class="help-block">Please provide just the username in your public profile URL, e.g. (in bold): https://github.com/<strong>ghillert</strong></span>
				</div>
			</div>

			<spring:bind path="organizer.sortOrder">
				<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
			</spring:bind>
			<div class="form-group${errorClass}">
				<label for="sortOrder" class="col-lg-2 control-label">Sort Order*</label>
				<div class="col-lg-10">
					<form:input type="number" cssClass="form-control" path="sortOrder" id="sortOrder" maxlength="255" tabindex="2"/>
					<form:errors path="sortOrder" cssClass="fieldError"/>
				</div>
			</div>

			<div class="form-group">
				<div class="col-lg-offset-2 col-lg-10">
					<button type="submit" class="btn btn-default" lang="save" tabindex="19">Add/Save</button>
					<button type="submit" class="btn btn-default" lang="delete" tabindex="20">Delete</button>
					<button type="submit" class="btn btn-default" name="cancel" tabindex="21">Cancel</button>
				</div>
			</div>
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
