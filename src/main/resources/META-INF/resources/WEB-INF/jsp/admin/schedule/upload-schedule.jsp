<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<title>${contextEvent.title} | Upload Schedule</title>

<!-- intro -->
<section id="about" class="module parallax parallax-3">
	<div class="container header">
		<div class="row centered">
			<div class="col-md-10 col-md-offset-1">
				 <div class="top-intro travel">
					<h4 class="section-white-title decorated"><span>Upload Schedule for ${contextEvent.title}</span></h4>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- /intro -->

<div class="row">
	<div class="col-md-8 col-md-offset-2" style="padding-top: 4em;">

	<spring:bind path="uploadScheduleForm.*">
		<c:if test="${not empty status.errorMessages}">
			<div class="alert alert-danger fade in"
				><a href="#" data-dismiss="alert" class="close">&times;</a>
				<c:forEach var="error" items="${status.errorMessages}"
					><c:out value="${error}" escapeXml="false"/><br/>
				</c:forEach>
			</div>
		</c:if>
	</spring:bind>

	<form:form id="uploadScheduleForm" class="form-horizontal" role="form" method="post" modelAttribute="uploadScheduleForm" enctype="multipart/form-data">

		<div class="row">
			<div class="col-md-8"><h3 style="margin-top: 0">Upload Schedule Form</h3></div>
		</div>

		<p>
			Please select a valid CSV file.
		</p>
		<pre>
id,eventId,fromTime,toTime,type,title,presentationId,roomId
8691,8666,2016-02-15 09:00,2016-02-15 17:30,SESSION,Update Session with room and presentation,123,123
,8666,2016-02-15 09:00,2016-02-15 17:30,SESSION,New Session without room,123,
,8666,2016-02-15 09:00,2016-02-15 17:30,SESSION,New Session with neither room not presentations,,
		</pre>
		<div class="form-group">
			<label for="scheduleFile" class="col-lg-2 control-label">Schedule CSV*</label>
			<div class="col-lg-10">
				<input id="scheduleFile" type="file" cssClass="form-control" name="scheduleFile" tabindex="1"/>
				<span class="help-block">Text CSV File.</span>
			</div>
		</div>
		<div class="form-group">
			<div class="col-lg-offset-2 col-lg-10">
				<div class="checkbox">
					<label>
						<form:checkbox path="replaceAll" id="replaceAll" tabindex="2"/> Check to replace existing Schedule Items.
					</label>
				</div>
				<form:errors path="replaceAll" cssClass="fieldError"/>
			</div>
		</div>

		<div class="form-group">
			<div class="col-lg-offset-2 col-lg-10">
				<button type="submit" class="btn btn-default" name="cancel" tabindex="3"><span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span> Cancel</button>
				<button type="submit" class="btn btn-success" lang="save" tabindex="4"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span> Upload</button>
			</div>
		</div>
		<p>Fields denoted with * are mandatory.
	</form:form>
	</div>
</div>

<content tag='bottom'>

		<script type="text/javascript">

			$(document).ready(function() {
				$("input[type='text']:visible:enabled:first", document.forms['uploadScheduleForm']).focus();

				$("#uploadScheduleForm").each(function(){
					$(this).find(':input:not(textarea)').keydown(function( event ) {
						if ( event.which == 13 ) {
							event.preventDefault();
							console.log("Submitting form ...");
							$("#uploadScheduleForm").submit();
						}
					});
				});
			});
		</script>
</content>
