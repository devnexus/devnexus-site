<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<div class="jumbotron call" style="margin-bottom:50px">
	<div class="container">
		<div id="banner">
			<h1><strong>Change Password</strong></h1>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-md-8 col-md-offset-2">

		<spring:bind path="passwordForm.*">
			<c:if test="${not empty status.errorMessages}">
				<div class="alert alert-danger fade in"
					><a href="#" data-dismiss="alert" class="close">&times;</a>
					<c:forEach var="error" items="${status.errorMessages}"
						><c:out value="${error}" escapeXml="false"/><br/>
					</c:forEach>
				</div>
			</c:if>
		</spring:bind>
		<form:form id="form" class="form-horizontal" role="form" method="post" modelAttribute="passwordForm" enctype="multipart/form-data">

			<spring:bind path="password1">
				<c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
			</spring:bind>
			<div class="form-group${errorClass}">
				<label for="password1" class="col-lg-2 control-label">Password*</label>
				<div class="col-lg-10">
					<form:input cssClass="form-control" path="password1" id="password1" maxlength="255" tabindex="1"/>
					<form:errors path="password1" cssClass="fieldError"/>
				</div>
			</div>
			<div class="form-group${errorClass}">
				<label for="password1" class="col-lg-2 control-label">Retype Password*</label>
				<div class="col-lg-10">
					<form:input cssClass="form-control" path="password2" id="password2" maxlength="255" tabindex="2"/>
					<form:errors path="password2" cssClass="fieldError"/>
				</div>
			</div>

			<div class="form-group">
				<div class="col-lg-offset-2 col-lg-10">
					<button type="submit" class="btn btn-default" lang="save" tabindex="19">Change Password</button>
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
			});
		</script>
</content>
