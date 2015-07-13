<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<div class="jumbotron call" style="margin-bottom:50px">
	<div class="container">
		<div id="banner">
			<h1><strong>Add/Edit Event</strong></h1>
		</div> <!-- end banner -->
	</div>
</div>
<div class="row">
	<div class="col-md-8 col-md-offset-2">

	<spring:bind path="eventForm.*">
		<c:if test="${not empty status.errorMessages}">
			<div class="alert alert-danger fade in"
				><a href="#" data-dismiss="alert" class="close">&times;</a>
				<c:forEach var="error" items="${status.errorMessages}"
					><c:out value="${error}" escapeXml="false"/><br/>
				</c:forEach>
			</div>
		</c:if>
	</spring:bind>

	<form:form id="eventForm" class="form-horizontal" role="form" method="post" modelAttribute="eventForm" enctype="multipart/form-data">
		<form:hidden path="event.id"/>

		<spring:bind path="eventForm.event.eventKey">
			<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
		</spring:bind>
		<div class="form-group${errorClass}">
			<label for="eventKey" class="col-lg-2 control-label">Event Key*</label>
			<div class="col-lg-10">
				<form:input cssClass="form-control" path="event.eventKey" id="eventKey" maxlength="25" tabindex="1"/>
				<form:errors path="event.eventKey" cssClass="fieldError"/>
			</div>
		</div>

		<spring:bind path="eventForm.event.title">
			<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
		</spring:bind>
		<div class="form-group${errorClass}">
			<label for="title" class="col-lg-2 control-label">Title*</label>
			<div class="col-lg-10">
				<form:input cssClass="form-control" path="event.title" id="title" maxlength="255" tabindex="2"/>
				<form:errors path="event.title" cssClass="fieldError"/>
			</div>
		</div>

		<spring:bind path="eventForm.event.current">
			<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
		</spring:bind>
		<div class="form-group${errorClass}">
			<div class="col-lg-offset-2 col-lg-10">
				<div class="checkbox">
					<label>
						<form:checkbox path="event.current" id="current" tabindex="3"/> Current Event
					</label>
				</div>
				<form:errors path="event.current" cssClass="fieldError"/>
			</div>
		</div>

		<div class="form-group">
			<label class="col-lg-2 control-label">Speakers</label>
			<div class="col-lg-offset-2 col-lg-10">
				<form:checkboxes element="div" path="event.speakers" items="${speakers}" itemValue="id" itemLabel="fullName" tabindex="4"/>
			</div>
		</div>

		<div class="form-group">
			<div class="col-lg-offset-2 col-lg-10">
				<button type="submit" class="btn btn-default" lang="save" tabindex="5">Save</button>
				<button type="submit" class="btn btn-default" name="cancel" tabindex="6">Cancel</button>
				<c:if test="${not empty eventForm.event.id}">
					<button type="submit" class="btn btn-default" name="delete" tabindex="6">Delete</button>
				</c:if>
			</div>
		</div>
	</form:form>
	</div>
</div>
