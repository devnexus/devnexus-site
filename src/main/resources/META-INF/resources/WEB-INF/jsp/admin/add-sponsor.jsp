<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<div class="jumbotron call" style="margin-bottom:50px">
	<div class="container">
		<div id="banner">
			<h1><strong>Add/Edit Sponsor</strong></h1>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-md-8 col-md-offset-2">

	<spring:bind path="sponsor.*">
		<c:if test="${not empty status.errorMessages}">
			<div class="alert alert-danger fade in"
				><a href="#" data-dismiss="alert" class="close">&times;</a>
				<c:forEach var="error" items="${status.errorMessages}"
					><c:out value="${error}" escapeXml="false"/><br/>
				</c:forEach>
			</div>
		</c:if>
	</spring:bind>
	<form:form id="form" class="form-horizontal" role="form" method="post" modelAttribute="sponsor" enctype="multipart/form-data">

<form:hidden path="event.id"/>
		<spring:bind path="sponsor.name">
			<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
		</spring:bind>
		<div class="form-group${errorClass}">
			<label for="name" class="col-lg-2 control-label">Name*</label>
			<div class="col-lg-10">
				<form:input cssClass="form-control" path="name" id="name" maxlength="255" tabindex="1"/>
				<form:errors path="name" cssClass="fieldError"/>
			</div>
		</div>
		<div class="form-group${errorClass}">
			<label for="link" class="col-lg-2 control-label">Link*</label>
			<div class="col-lg-10">
				<form:input cssClass="form-control" path="link" id="link" maxlength="255" tabindex="1"/>
				<form:errors path="link" cssClass="fieldError"/>
			</div>
		</div>
		<spring:bind path="sponsor.sortOrder">
			<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
		</spring:bind>
		<div class="form-group${errorClass}">
			<label for="name" class="col-lg-2 control-label">Sort Order*</label>
			<div class="col-lg-10">
				<form:input cssClass="form-control" path="sortOrder" id="sortOrder" maxlength="255" tabindex="2"/>
				<form:errors path="sortOrder" cssClass="fieldError"/>
			</div>
		</div>
		<div class="form-group${errorClass}">
			<label for="sponsorLevel" class="col-lg-2 control-label">Sponsor Level*</label>
			<div class="col-lg-10">
				<form:select cssClass="form-control" path="sponsorLevel" id="sponsorLevel"
					tabindex="3" itemValue="sponsorLevel.id">
					<form:option value="" label="Please Select a Sponsor Level" />
					<form:options items="${sponsorLevels}" itemLabel="name" itemValue="id"/>
				</form:select>
				<form:errors path="sponsorLevel" cssClass="fieldError" />
			</div>
		</div>

			<div class="form-group">
				<c:if test="${sponsor.logo != null}">
					<div class="col-lg-10 col-lg-offset-2">
						<img src="${ctx}${baseSiteUrl}/sponsors/${sponsor.id}.jpg"/>
					</div>
				</c:if>
				<label for="pictureFile" class="col-lg-2 control-label">Logo</label>
				<div class="col-lg-10">
					<input id="pictureFile" type="file" cssClass="form-control" name="pictureFile" tabindex="4"/>
					<span class="help-block">Should be 100px x 133px.</span>
				</div>
			</div>

			<div class="form-group">
				<div class="col-lg-offset-2 col-lg-10">
					<button type="submit" class="btn btn-default" name="save" tabindex="19">Add/Save</button>
					<button type="submit" class="btn btn-default" name="delete" tabindex="20">Delete</button>
					<button type="submit" class="btn btn-default" name="cancel" tabindex="21">Cancel</button>
				</div>
			</div>
		</form:form>
	</div>
</div>

<content tag='bottom'>

		<script src="${ctx}/assets/js/other/bootstrap-maxlength.min.js"></script>
		<script type="text/javascript">

			$(document).ready(function() {
				$("input[type='text']:visible:enabled:first", document.forms['form']).focus();

				$('textarea').maxlength({
					alwaysShow: true
				});

			});
		</script>
</content>
