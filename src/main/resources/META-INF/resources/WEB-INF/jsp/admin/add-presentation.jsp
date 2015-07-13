<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<div class="jumbotron call" style="margin-bottom:50px">
	<div class="container">
		<div id="banner">
			<h1><strong>Add/Edit Presentation</strong></h1>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-md-8 col-md-offset-2">

	<spring:bind path="presentation.*">
		<c:if test="${not empty status.errorMessages}">
			<div class="alert alert-danger fade in"
				><a href="#" data-dismiss="alert" class="close">&times;</a>
				<c:forEach var="error" items="${status.errorMessages}"
					><c:out value="${error}" escapeXml="false"/><br/>
				</c:forEach>
			</div>
		</c:if>
	</spring:bind>

	<form:form id="presentationForm" class="form-horizontal" role="form" method="post" modelAttribute="presentation" enctype="multipart/form-data">

		<form:hidden path="event.id"/>

		<div class="form-group">
			<label class="col-lg-2 control-label">Event</label>
			<div class="col-lg-10">
				<p class="form-control-static"><c:out value="${presentation.event.eventKey}"/></p>
			</div>
		</div>

		<spring:bind path="presentation.speakers">
			<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
		</spring:bind>
		<div class="form-group${errorClass}">
			<label for="speaker" class="col-lg-2 control-label">Speaker*</label>
			<div class="col-lg-10">
				<form:select path="speakers" multiple="true" items="${speakers}" itemLabel="fullName" itemValue="id" tabindex="1" style="width: 300px;" size="10">
					<form:option value="" label="Please Select a Speaker" />
				</form:select>
				<form:errors path="speakers" cssClass="fieldError" />
			</div>
		</div>

		<spring:bind path="presentation.title">
			<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
		</spring:bind>
		<div class="form-group${errorClass}">
			<label for="title" class="col-lg-2 control-label">Title*</label>
			<div class="col-lg-10">
				<form:input cssClass="form-control" path="title" id="title" maxlength="255" tabindex="2"/>
				<form:errors path="title" cssClass="fieldError"/>
			</div>
		</div>

		<spring:bind path="presentation.description">
			<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
		</spring:bind>
		<div class="form-group${errorClass}">
			<label for="description" class="col-lg-2 control-label">Description*</label>
			<div class="col-lg-10">
				<form:textarea cssClass="form-control" path="description" id="description" tabindex="3" rows="10" maxlength="10000"/>
				<form:errors path="description" cssClass="fieldError"/>
				<span class="help-block"><a href="http://daringfireball.net/projects/markdown/" target="_blank">
				Markdown</a> is supported for the description.</span>
			</div>
		</div>

		<spring:bind path="presentation.presentationLink">
			<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
		</spring:bind>
		<div class="form-group${errorClass}">
			<label for="presentationLink" class="col-lg-2 control-label">Presentation Link</label>
			<div class="col-lg-10">
				<form:input cssClass="form-control" path="presentationLink" id="presentationLink" maxlength="255" tabindex="4"/>
				<form:errors path="presentationLink" cssClass="fieldError"/>
			</div>
		</div>

		<div class="form-group">
			<label for="uploadedFile" class="col-lg-2 control-label">Presentation</label>
			<div class="col-lg-10">
				<input id="uploadedFile" type="file" cssClass="form-control" name="uploadedFile" tabindex="5"/>
			</div>
		</div>

		<spring:bind path="presentation.audioLink">
			<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
		</spring:bind>
		<div class="form-group${errorClass}">
			<label for="audioLink" class="col-lg-2 control-label">Audio Link</label>
			<div class="col-lg-10">
				<form:input cssClass="form-control" path="audioLink" id="audioLink" maxlength="255" tabindex="6"/>
				<form:errors path="audioLink" cssClass="fieldError"/>
			</div>
		</div>

		<spring:bind path="presentation.skillLevel">
			<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
		</spring:bind>
		<div class="form-group${errorClass}">
			<label for="skill-level" class="col-lg-2 control-label">Skill Level*</label>
			<div class="col-lg-10">
				<form:select cssClass="form-control" path="skillLevel" id="skill-level" tabindex="7">
					<form:option value="" label="Please Select a Skill Level" />
					<form:options items="${skillLevels}" itemLabel="name" itemValue="id"/>
				</form:select>
				<form:errors path="skillLevel" cssClass="fieldError" />
			</div>
		</div>

		<div class="form-group${errorClass}">
			<label for="presentation-type" class="col-lg-2 control-label">Presentation Type*</label>
			<div class="col-lg-10">
				<form:select cssClass="form-control" path="presentationType" id="presentation-type"
					tabindex="8" itemValue="presentationType.id">
					<form:option value="" label="Please Select a Presentation Type" />
					<form:options items="${presentationTypes}" itemLabel="name" itemValue="id"/>
				</form:select>
				<form:errors path="presentationType" cssClass="fieldError" />
			</div>
		</div>

		<spring:bind path="presentation.track.id">
			<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
		</spring:bind>
		<div class="form-group${errorClass}">
			<label for="track" class="col-lg-2 control-label">Track</label>
			<div class="col-lg-10">
				<form:select cssClass="form-control" path="track.id" id="track" tabindex="9">
					<form:option value="" label="Please Select a Track" />
					<form:options items="${tracks}" itemLabel="name" itemValue="id"/>
				</form:select>
				<form:errors path="track" cssClass="fieldError" />
			</div>
		</div>

		<spring:bind path="presentation.tagsAsText">
			<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
		</spring:bind>
		<div class="form-group${errorClass}">
			<label for="tagsAsText" class="col-lg-2 control-label">Tags</label>
			<div class="col-lg-10">
				<form:textarea cssClass="form-control" path="tagsAsText" id="tagsAsText" tabindex="10" rows="3" maxlength="10000"/>
				<form:errors path="tagsAsText" cssClass="fieldError"/>
				<span class="help-block">Comma-separated list of tags</span>
			</div>
		</div>

		<div class="form-group">
			<div class="col-lg-offset-2 col-lg-10">
				<button type="submit" class="btn btn-default" name="delete" tabindex="11">Delete</button>
				<button type="submit" class="btn btn-default" lang="save" tabindex="12">Save</button>
				<button type="submit" class="btn btn-default" name="cancel" tabindex="13">Cancel</button>
			</div>
		</div>
	</form:form>
	</div>
</div>

<content tag='bottom'>
		<script src="${ctx}/assets/js/other/bootstrap-maxlength.min.js"></script>
		<script type="text/javascript">

			$(document).ready(function() {
				$("select:visible:enabled:first", document.forms['presentationForm']).focus();

				$('textarea').maxlength({
					alwaysShow: true
				});

			});
		</script>
</content>
